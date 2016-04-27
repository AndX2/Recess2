package ru.yandex.anroid.andrew.recess2.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import ru.yandex.anroid.andrew.recess2.pojo.SyllabusEntry;
import ru.yandex.anroid.andrew.recess2.storage.DBContract;
import ru.yandex.anroid.andrew.recess2.storage.DBHelper;

/**
 * Created by savos on 26.04.2016.
 */
public class Utils {

    public static Calendar calculateDateForDayFragment(int numberDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        int deltaDay = numberDayOfWeek - calendar.get(Calendar.DAY_OF_WEEK) + calendar.getFirstDayOfWeek();
        //if day of week late - send this day to next week
        if (deltaDay < 0) deltaDay += 7;
        long tmp = calendar.getTimeInMillis();
        calendar.setTimeInMillis(tmp + deltaDay * Consts.DAY);
        return calendar;
    }

    //TODO delete this method
    public static void createMockDBSyllabusData(Context context) {
        for (int day = 1; day <= 7; day++) {
            for (int lesson = 0; lesson < 5; lesson++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBContract.Syllabus.DAY, day);
                contentValues.put(DBContract.Syllabus.BEGIN_TIME, getTimeForDB(8 + lesson, 0 + day));
                contentValues.put(DBContract.Syllabus.END_TIME, getTimeForDB(8 + lesson, 10 + day));
                contentValues.put(DBContract.Syllabus.ENABLED, 1);
                SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(context).getWritableDatabase();
                sqLiteDatabase.insert(DBContract.Syllabus.TABLE_NAME, null, contentValues);
            }
        }
    }

    public static ArrayList<SyllabusEntry> createSyllabusListEntry(Cursor cursor, int dayOfWeek) {
        ArrayList<SyllabusEntry> list = new ArrayList<>();
        int dayColumnIndex = cursor.getColumnIndex(DBContract.Syllabus.DAY);
        int beginTimeColumnIndex = cursor.getColumnIndex(DBContract.Syllabus.BEGIN_TIME);
        int endTimeColumnIndex = cursor.getColumnIndex(DBContract.Syllabus.END_TIME);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            if (cursor.getInt(dayColumnIndex) == dayOfWeek)
                list.add(new SyllabusEntry(dayOfWeek, cursor.getLong(beginTimeColumnIndex),
                        cursor.getLong(endTimeColumnIndex)));
        }
        return list;
    }

    public static int getTimeForDB(int hour, int minute) {
        return minute * Consts.MINUTE + hour * Consts.HOUR;
    }


    public static int getHourFromDBPresentation(long timeDBPresentation) {
        return (int) (timeDBPresentation % Consts.DAY) / Consts.HOUR;
    }

    public static int getMinuteFromDBPresentation(long timeDBPresentation) {
        return (int) ((timeDBPresentation % Consts.DAY) % Consts.HOUR / Consts.MINUTE);
    }
}
