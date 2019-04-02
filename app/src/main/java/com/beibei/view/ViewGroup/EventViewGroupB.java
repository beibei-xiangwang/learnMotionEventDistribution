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
public class EventViewGroupB extends LinearLayout {
    private static final String TAG = "event";

    public EventViewGroupB(Context context) {
        super(context);
    }

    public EventViewGroupB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "------------------dispatchTouchEvent--------------groupB");
        return super.dispatchTouchEvent(ev);
//        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "------------------onInterceptTouchEvent--------------groupB");
        return super.onInterceptTouchEvent(ev);
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "------------------onTouchEvent--------------groupB");
        return super.onTouchEvent(event);
    }
}
