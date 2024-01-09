package com.example.ass_nc_duytien.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;


import com.example.ass_nc_duytien.dao.DangKyKhoaHocDAO;
import com.example.ass_nc_duytien.model.MonHoc;

import java.util.ArrayList;

public class KHDangKySercive extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");
        boolean isAll  = bundle.getBoolean("isAll");

        DangKyKhoaHocDAO dangKyKhoaHocDAO = new DangKyKhoaHocDAO(this);
        ArrayList<MonHoc> list = dangKyKhoaHocDAO.getdsMonHoc(id, isAll);

        Intent intentBR = new Intent();
        Bundle bundleBR = new Bundle();
        bundleBR.putSerializable("list", list);
        intentBR.putExtras(bundleBR);
        intentBR.setAction("DSMonHoc");
        sendBroadcast(intentBR);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
