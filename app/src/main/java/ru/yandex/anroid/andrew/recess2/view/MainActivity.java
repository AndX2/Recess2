package ru.yandex.anroid.andrew.recess2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.yandex.anroid.andrew.recess2.R;
import ru.yandex.anroid.andrew.recess2.storage.DBHelper;
import ru.yandex.anroid.andrew.recess2.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = DBHelper.getInstance(this);
        if(dbHelper.getSyllabusCursor().getCount() < 1) Utils.createMockDBSyllabusData(this);
    }

}
