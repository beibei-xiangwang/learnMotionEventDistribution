package com.beibei.view.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * project_name:   view
 * package_name:   com.beibei.view.View
 * author:   beibei
 * create_time:    2019/4/2 15:35
 * class_desc:
 * remarks:
 */
public class EventViewA extends View {
    private static final String TAG = "event";
    public EventViewA(Context context) {
        super(context);
    }

    public EventViewA(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "------------------dispatchTouchEvent--------------viewA");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "------------------onTouchEvent--------------viewA");
        return super.onTouchEvent(event);
    }
}
