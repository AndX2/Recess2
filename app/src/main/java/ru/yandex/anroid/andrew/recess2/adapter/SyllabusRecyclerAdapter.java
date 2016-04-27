package ru.yandex.anroid.andrew.recess2.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import ru.yandex.anroid.andrew.recess2.R;
import ru.yandex.anroid.andrew.recess2.pojo.SyllabusEntry;
import ru.yandex.anroid.andrew.recess2.utils.Utils;


public class SyllabusRecyclerAdapter extends RecyclerView.Adapter<SyllabusRecyclerAdapter.ViewHolder> {

    List<SyllabusEntry> syllabusEntryList;

    public SyllabusRecyclerAdapter(List<SyllabusEntry> syllabusEntryList) {
        this.syllabusEntryList = syllabusEntryList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBeginTime;
        TextView tvEndTime;
        ImageView isEnabled;



        public ViewHolder(View view) {
            super(view);
            tvBeginTime = (TextView) view.findViewById(R.id.begin_time);
            tvEndTime = (TextView) view.findViewById(R.id.end_time);
            isEnabled = (ImageView) view.findViewById(R.id.image_enabled);


        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.syllabus_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String tmp = String.format("%1$02d", Utils.getHourFromDBPresentation(syllabusEntryList.get(position).getBeginTime())) +
                " : " + String.format("%1$02d", Utils.getMinuteFromDBPresentation(syllabusEntryList.get(position).getBeginTime()));
        holder.tvBeginTime.setText(tmp);
        holder.tvEndTime.setText(String.format("%1$02d", Utils.getHourFromDBPresentation(syllabusEntryList.get(position).getEndTime())) +
                " : " + String.format("%1$02d", Utils.getMinuteFromDBPresentation(syllabusEntryList.get(position).getEndTime())));
        if (syllabusEntryList.get(position).getEnabled() == 1) {
            holder.isEnabled.setImageResource(R.drawable.ic_flash_on_white_24dp);
        }else holder.isEnabled.setImageResource(R.drawable.ic_flash_off_white_24dp);
    }

    @Override
    public int getItemCount() {
        return syllabusEntryList.size();
    }




}
