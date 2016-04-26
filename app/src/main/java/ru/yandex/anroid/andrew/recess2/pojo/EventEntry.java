package ru.yandex.anroid.andrew.recess2.pojo;

import java.util.Calendar;


public class EventEntry {

    public static final int ACTOIN_ON = 1;
    public static final int ACTION_OFF = -1;

    private int action;
    private int intentKey;

    public int getAction() {
        return action;
    }

    public int getIntentKey() {
        return intentKey;
    }

    public Calendar getUtcTime() {
        return utcTime;
    }

    private Calendar utcTime;


}
