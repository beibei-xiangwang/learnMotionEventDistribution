package com.beibei.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * project_name:   view
 * package_name:   com.beibei.view
 * author:   beibei
 * create_time:    2019/4/2 15:37
 * class_desc:
 * remarks:
 */
public class EventActivity extends AppCompatActivity {
    private static final String TAG = "event";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        findViewById(R.id.A).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        findViewById(R.id.B).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "------------------onClick--------------viewB");
            }
        });
    }
//    三个方法关系伪代码
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean consume = false;
//        if(onInterceptTouchEvent(ev)){
//            consume = onTouchEvent(ev);
//        }else {
//            consume = child.dispatchTouchEvent(ev);
//        }
//        return consume;
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "------------------dispatchTouchEvent--------------EventActivity");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "------------------onTouchEvent--------------EventActivity");
        return super.onTouchEvent(event);
    }

}
