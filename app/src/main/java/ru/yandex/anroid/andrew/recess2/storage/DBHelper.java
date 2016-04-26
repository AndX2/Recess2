package ru.yandex.anroid.andrew.recess2.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.CalendarContract;

import java.util.Iterator;
import java.util.List;

import ru.yandex.anroid.andrew.recess2.pojo.EventEntry;
import ru.yandex.anroid.andrew.recess2.pojo.SyllabusEntry;
import ru.yandex.anroid.andrew.recess2.utils.Utils;

import static ru.yandex.anroid.andrew.recess2.storage.DBContract.*;


public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance;

    private Context context;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) instance = new DBHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Syllabus.SQL_CREATE_SYLLABUS_TABLE);
        sqLiteDatabase.execSQL(Events.SQL_CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO
        //Code upgrade db scheme (drop tables syllabus and event)
    }

    public Cursor getSyllabusCursor() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] resultColumns = {Syllabus.ID, Syllabus.DAY,
                Syllabus.BEGIN_TIME, Syllabus.END_TIME};
        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;
        Cursor syllabusCursor = sqLiteDatabase.query(Syllabus.TABLE_NAME,
                resultColumns, where,
                whereArgs, groupBy, having, order);
        return syllabusCursor;
    }

    public void addAllSyllabus(List<SyllabusEntry> list){
        SQLiteDatabase database = this.getWritableDatabase();
        SyllabusEntry tmp;
        Iterator<SyllabusEntry> iterator = list.iterator();
        while (iterator.hasNext()){
            tmp = iterator.next();
            ContentValues values = new ContentValues();
            values.put(Syllabus.DAY, tmp.getDay());
            values.put(Syllabus.BEGIN_TIME, tmp.getBeginTime());
            values.put(Syllabus.END_TIME, tmp.getEndTime());
            values.put(Syllabus.ENABLED, tmp.getEnabled());
            values.put(Syllabus.TITLE, tmp.getTitle());
            database.insert(Syllabus.TABLE_NAME, null, values);
        }
    }

    public void deleteAllSyllabus(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(Syllabus.TABLE_NAME, null, null);
    }

    public Cursor getEventsCursor() {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] resultColumns = {Events.ID, Events.SYLLABUS_ENTRY_ID, Events.UTC_TIME,
                Events.ACTION, Events.INTENT_KEY};
        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;
        Cursor eventsCursor = database.query(Events.TABLE_NAME,
                resultColumns, where,
                whereArgs, groupBy, having, order);
        return eventsCursor;
    }

    public void addAllEvents(List<EventEntry> list){
        SQLiteDatabase database = this.getWritableDatabase();
        Iterator<EventEntry> iterator = list.iterator();
        EventEntry tmp;
        while (iterator.hasNext()){
            tmp = iterator.next();
            ContentValues values = new ContentValues();
            values.put(Events.ACTION, tmp.getAction());
            values.put(Events.UTC_TIME, tmp.getUtcTime().toString());
            values.put(Events.INTENT_KEY, tmp.getIntentKey());
            database.insert(Events.TABLE_NAME, null, values);
        }
    }

    public void deleteEvent(int intentKey){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(Events.TABLE_NAME, " " + Events.INTENT_KEY + " = " + intentKey, null);
    }

    public void deleteAllEvents(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(Events.TABLE_NAME, null, null);
    }

}
