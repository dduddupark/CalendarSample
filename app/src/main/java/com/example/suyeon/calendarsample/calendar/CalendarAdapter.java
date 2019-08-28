package com.example.suyeon.calendarsample.calendar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suyeon.calendarsample.R;
import com.example.suyeon.calendarsample.util.CalendarUtil;
import com.example.suyeon.calendarsample.util.ColorUtil;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    CalendarData mData;

    public CalendarAdapter(CalendarData data) {
        mData = data;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);

        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        Context context = holder.itemView.getContext();
        Integer day = mData.getDayList().get(position);
        int sday = mData.getSday();
        int eday = mData.getEday();

        holder.mTvDay.setText(String.valueOf(day));

        if (position >= sday && position <= eday) {
            holder.mTvDay.setTextColor(CalendarUtil.getColor(context, position));
        } else {
            holder.mTvDay.setTextColor(ColorUtil.getColor(context, R.color.gray_dadcef_ff));
        }
    }

    @Override
    public int getItemCount() {
        if (mData.getDayList() == null) {
            return 0;
        } else {
            return mData.getDayList().size();
        }
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {

        TextView mTvDay;

        public CalendarViewHolder(View view) {
            super(view);

            mTvDay = view.findViewById(R.id.tv_day);
        }
    }
}
