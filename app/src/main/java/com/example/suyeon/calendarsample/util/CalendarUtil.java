package com.example.suyeon.calendarsample.util;

import android.content.Context;

import com.example.suyeon.calendarsample.R;

public class CalendarUtil {

    public static int getColor(Context context, int day) {

        int color;

        if((day%7)==0) { //일요일
            color = ColorUtil.getColor(context, R.color.red_eb6f6f_ff);
        } else if((day%7)==6) { //토요일
            color = ColorUtil.getColor(context, R.color.blue_749bfa_ff);
        } else {
            color = ColorUtil.getColor(context, R.color.gray_757f92_ff);
        }

        return color;
    }
}
