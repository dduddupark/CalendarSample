package com.example.suyeon.calendarsample.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.suyeon.calendarsample.R;

/**
 * Swipe 안되는 ViewPager
 */
public class SwipeControlViewPager extends ViewPager {

    /**
     * 스와이프 구동 유무
     */
    public boolean mSwipeEnable;

    public SwipeControlViewPager(@NonNull Context context) {
        super(context);
    }

    public SwipeControlViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SwipeControlViewPager);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        mSwipeEnable = typedArray.getBoolean(R.styleable.SwipeControlViewPager_enable, false);
    }

    public void setSwipeEnable(boolean swipeEnable) {
        mSwipeEnable = swipeEnable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mSwipeEnable) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mSwipeEnable) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

}

