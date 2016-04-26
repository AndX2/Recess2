package ru.yandex.anroid.andrew.recess2.pojo;


public class SyllabusEntry {

    public static final int ENABLED = 1;
    public static final int DISABLED = -1;

    private long dbKey = -1;
    private int day;
    private long beginTime;
    private long endTime;
    private String title;
    private int isEnabled;

    public SyllabusEntry(int day, long beginTimeOfDay, long endTimeOfDay) {
        this.day = day;
        this.beginTime = beginTimeOfDay;
        this.endTime = endTimeOfDay;
        title = "default title";
        isEnabled = ENABLED;
    }

    public SyllabusEntry(String title, int day, long beginTimeOfDay, long endTimeOfDay) {
        this(day, beginTimeOfDay, endTimeOfDay);
        this.title = title;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public int getEnabled() {
        return isEnabled;
    }

    public int getDay(){
        return day;
    }

    public String getTitle(){
        return title;
    }


}
