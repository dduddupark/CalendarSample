package com.example.suyeon.calendarsample.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suyeon.calendarsample.Const;
import com.example.suyeon.calendarsample.R;

public class CalendarFragment extends Fragment {

    private RecyclerView mRvList;
    private CalendarAdapter mAdapter;
    private TextView mTvTitle;

    private CalendarData mData;

    /**
     * LoungeFragment 생성자
     *
     * @return Fragment
     */
    public static Fragment newInstance(CalendarData calendarData) {
        Fragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putParcelable(Const.ARGUMENT_CALENDAR_DATA, calendarData);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        mRvList = view.findViewById(R.id.rv_list);
        mTvTitle = view.findViewById(R.id.tv_title);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mData = getArguments().getParcelable(Const.ARGUMENT_CALENDAR_DATA);

        setView();
    }

    private void setView() {

        mAdapter = new CalendarAdapter(mData);
        mRvList.setAdapter(mAdapter);

        mTvTitle.setText(getString(R.string.calendar_title, mData.getYear(), (mData.getMonth()+1)));
    }
}
