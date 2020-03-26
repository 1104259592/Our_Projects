package com.hq.app.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hq.app.R;
import com.hq.app.mylibrary.activitys.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 引导页
 */
public class LogoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = MainActivity.startMainActivity(LogoActivity.this);
                baseStartIntent(intent);
            }
        };
        timer.schedule(task, 1000);
    }
}
