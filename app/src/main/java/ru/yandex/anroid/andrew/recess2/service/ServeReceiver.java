package ru.yandex.anroid.andrew.recess2.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import ru.yandex.anroid.andrew.recess2.utils.Consts;

/**
 * Created by savos on 26.04.2016.
 */
public class ServeReceiver extends BroadcastReceiver {

    AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Restart myself every Consts.TIMEOUT_RESTART_SERVICE_HOUR
        Intent reBootIntent = new Intent(context, ServeReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Consts.REQUEST_CODE_REBOOT_RECEIVER, reBootIntent, 0);
        alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        alarmManager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + Consts.HOUR * Consts.TIMEOUT_RESTART_SERVICE_HOUR,
                pendingIntent);
        //Start SchedulerService.onStartCommand for handle StateModel
        context.startService(new Intent(context, SchedulerService.class));
    }
}

