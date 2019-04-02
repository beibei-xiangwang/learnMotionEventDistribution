package com.beibei.view.ViewGroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * project_name:   view
 * package_name:   com.beibei.view
 * author:   beibei
 * create_time:    2019/4/2 15:30
 * class_desc:
 * remarks:
 */
public class EventViewGroupA extends LinearLayout {
    private static final String TAG = "event";

    public EventViewGroupA(Context context) {
        super(context);
    }

    public EventViewGroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "------------------dispatchTouchEvent--------------groupA");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "------------------onInterceptTouchEvent--------------groupA");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "------------------onTouchEvent--------------groupA");
        return super.onTouchEvent(event);
    }
}
