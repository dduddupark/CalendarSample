package com.example.suyeon.calendarsample.util;

import androidx.core.content.ContextCompat;

public class ColorUtil {

    /**
     * 컬러 가져오기
     *
     * @param context  Context
     * @param colorRes 컬러 리소스
     * @return 컬러값
     */
    public static int getColor(android.content.Context context, int colorRes) {
        return ContextCompat.getColor(context, colorRes);
    }
}
