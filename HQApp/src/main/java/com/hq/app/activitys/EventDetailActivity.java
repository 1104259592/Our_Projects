package com.hq.app.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hq.app.R;
import com.hq.app.mylibrary.activitys.BaseActivity;

/**
 * 产品详情页
 */
public class EventDetailActivity extends BaseLocalActivity {

    private static final String EVENT_NAME = "event_name";
    private static final String EVENT_IMAGE_ID = "event_image_id";
    private ImageView mEventDetailIv;
    private Toolbar mEventDetailToolbar;
    private TextView mEventDetailContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        initView();
        init();
    }

    private void initView() {
        mEventDetailIv = (ImageView) findViewById(R.id.eventDetail_iv);
        mEventDetailToolbar = (Toolbar) findViewById(R.id.eventDetail_toolbar);
        mEventDetailContentTv = (TextView) findViewById(R.id.eventDetail_content_tv);
    }

    private void init() {
        Intent intent = getIntent();
        String eventName = intent.getStringExtra(EVENT_NAME);
        String eventImage = intent.getStringExtra(EVENT_IMAGE_ID);

        setSupportActionBar(mEventDetailToolbar);
        setTitle(eventName);
        mEventDetailToolbar.setNavigationIcon(R.mipmap.back);
        mEventDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseFinish();
            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Glide.with(this).load(eventImage).into(mEventDetailIv);
        String s = generateEventContent(eventName);
        mEventDetailContentTv.setText(s);
    }

    private String generateEventContent(String eventName) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append(eventName);
        }
        return stringBuilder.toString();
    }

    //启动产品详情页
    public static Intent startEventDetailActivity(Context context, String eventName, String eventImage) {
        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra(EVENT_NAME, eventName);
        intent.putExtra(EVENT_IMAGE_ID, eventImage);
        return intent;
    }
}
