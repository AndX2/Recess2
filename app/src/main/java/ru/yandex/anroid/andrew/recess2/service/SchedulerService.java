package ru.yandex.anroid.andrew.recess2.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import ru.yandex.anroid.andrew.recess2.model.StateModel;


public class SchedulerService extends Service {

    private StateModel model;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


}
