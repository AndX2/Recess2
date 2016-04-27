package ru.yandex.anroid.andrew.recess2.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import ru.yandex.anroid.andrew.recess2.R;
import ru.yandex.anroid.andrew.recess2.adapter.SyllabusRecyclerAdapter;
import ru.yandex.anroid.andrew.recess2.storage.DBHelper;
import ru.yandex.anroid.andrew.recess2.utils.Consts;
import ru.yandex.anroid.andrew.recess2.utils.Utils;

/**
 * Created by savos on 27.04.2016.
 */
public class DayFragment extends Fragment {

    private static final int LAYOUT = R.layout.day_fragment;
    private static Context context;
    private String dayOfWeek;
    private String month;
    private Calendar currentTime;
    private TextView tvDayOfWeek;
    private TextView tvDayOfMonth;
    private TextView tvMonth;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;

    public static DayFragment getInstance(Context context, int numberDayOfWeek){
        DayFragment fragment = new DayFragment();
        Bundle bundle = new Bundle();
        Calendar calendar = Calendar.getInstance();
        bundle.putSerializable(Consts.CURRENT_TIME_KEY_DAYFRAGMENT, calendar);
        bundle.putInt(Consts.NUMBER_DAY_OF_WEEK_KEY_DAYFRAGMENT, numberDayOfWeek);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        tvDayOfWeek = (TextView)view.findViewById(R.id.day_of_week);
        tvDayOfMonth = (TextView)view.findViewById(R.id.day_of_month);
        tvMonth = (TextView) view.findViewById(R.id.month);
        currentTime = Utils.calculateDateForDayFragment(this.getArguments()
                .getInt(Consts.NUMBER_DAY_OF_WEEK_KEY_DAYFRAGMENT));
        context = getActivity();
        setDateLabel();
        recyclerView = (RecyclerView) view.findViewById(R.id.syllabus_recycler_view);
        recyclerLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new SyllabusRecyclerAdapter(Utils.createSyllabusListEntry(DBHelper.getInstance(context).getSyllabusCursor(),
                currentTime.get(Calendar.DAY_OF_WEEK)));
        recyclerView.setAdapter(recyclerAdapter);

        return  view;
    }


    private void setDateLabel() {
        //
        dayOfWeek = context.getResources().getStringArray(R.array.daysOfWeek)[currentTime.get(Calendar.DAY_OF_WEEK) - 1];
        month = context.getResources().getStringArray(R.array.months)[currentTime.get(Calendar.MONTH)];
        tvDayOfWeek.setText(dayOfWeek);
        tvDayOfMonth.setText(String.format("%1$02d", currentTime.get(Calendar.DAY_OF_MONTH)));
        tvMonth.setText(month);

    }
}
