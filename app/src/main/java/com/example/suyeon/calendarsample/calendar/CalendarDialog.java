package com.example.suyeon.calendarsample.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.suyeon.calendarsample.R;
import com.example.suyeon.calendarsample.util.SwipeControlViewPager;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarDialog extends DialogFragment {

    public static final String EVENT_DIALOG = "event_dialog";

    private static final int CALENDAR_CURRENT = 13;

    private SwipeControlViewPager mVpContent;
    private PagerAdapter mPagerAdapter;

    private Button mBtnLeft;
    private Button mBtnRight;

    private Calendar sDate;
    private Calendar eDate;

    private int year;
    private int month;
    private int lastDay;

    int pagerPosition = 0;

    public CalendarDialog() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_calendar, container, false);

        mVpContent = view.findViewById(R.id.vp_content);
        mBtnLeft = view.findViewById(R.id.btn_left);
        mBtnRight = view.findViewById(R.id.btn_right);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setView();
    }

    private void setView() {

        mPagerAdapter = new PagerAdapter(getChildFragmentManager());

        mVpContent.setAdapter(mPagerAdapter);
        mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pagerPosition > 0) {
                    pagerPosition--;
                }

                setCurrentItem(pagerPosition);
            }
        });
        mBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pagerPosition < 25) {
                    pagerPosition++;
                }

                setCurrentItem(pagerPosition);
            }
        });
    }

    /**
     * 프래그먼트 당 날짜 설정
     * @param position
     * @return
     */
    private CalendarData getCalendarData(int position) {

        //오늘 날짜로 설정
        sDate = Calendar.getInstance();
        eDate = Calendar.getInstance();

        year = sDate.get(Calendar.YEAR);
        month = sDate.get(Calendar.MONTH);

        //현재에서 이동할 월 설정
        int moveMonth;

        if (position == CALENDAR_CURRENT) {
            moveMonth = 0;
        } else {
            moveMonth = position - CALENDAR_CURRENT;
        }

        sDate.add(Calendar.MONTH, moveMonth);

        year = sDate.get(Calendar.YEAR);
        month = sDate.get(Calendar.MONTH);

        //이동할 월의 시작과 끝 날짜
        sDate.set(year, month, 1);
        eDate.set(year, month, sDate.getActualMaximum(Calendar.DATE));

        lastDay = sDate.getActualMaximum(Calendar.DATE);

        sDate.add(Calendar.DATE, -sDate.get(Calendar.DAY_OF_WEEK)+1);
        eDate.add(Calendar.DATE, 7-eDate.get(Calendar.DAY_OF_WEEK));

        //리스트에 한달치 담기
        ArrayList<Integer> dayList = new ArrayList<>();

        int currentSDay = 0;
        int currentEDay = 0;

        for (int i=0; sDate.before(eDate) || sDate.equals(eDate); sDate.add(Calendar.DATE, 1), i++) {

            int mon = sDate.get(Calendar.MONTH);
            int day = sDate.get(Calendar.DATE);

            if (mon == month && day == 1) {
                currentSDay = i;
            } else if (mon == month && day == lastDay) {
                currentEDay = i;
            }

            dayList.add(sDate.get(Calendar.DATE));
        }

        return new CalendarData(dayList, currentSDay, currentEDay, year, month);
    }

    /**
     * 뷰페이저 보여질 화면
     *
     * @param position 보여질 화면 위치
     */
    private void setCurrentItem(int position) {
        try {
            mVpContent.setCurrentItem(position, false);
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    /**
     * 달력 PagerAdapter
     * {@link CalendarFragment }
     */
    private class PagerAdapter extends FragmentStatePagerAdapter {

        //전후로 1년씩(12개월+현재월+12개월)
        private int fragmentCount = 25;
        private Fragment[] mFragments = new Fragment[fragmentCount];

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (mFragments[position] == null) {

                mFragments[position] = CalendarFragment.newInstance(getCalendarData(position));
            }

            return mFragments[position];
        }

        @Override
        public int getCount() {
            return fragmentCount;
        }
    }
}
