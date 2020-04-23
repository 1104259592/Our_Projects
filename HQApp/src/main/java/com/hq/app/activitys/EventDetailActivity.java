package com.hq.app.activitys;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hq.app.R;

public class EventDetailActivity extends AppCompatActivity {
    public static final String EVENT_NAME = "event_name";
    public static final String EVENT_IMAGE_ID = "event_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Intent intent = getIntent();
        String eventName = intent.getStringExtra(EVENT_NAME);
        String eventImage = intent.getStringExtra(EVENT_IMAGE_ID);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout eventToolbar = (CollapsingToolbarLayout)findViewById(R.id.eventDetail_collapsing_toolbar);
        ImageView eventIv = findViewById(R.id.eventDetail_iv);
        TextView eventContentTv = findViewById(R.id.eventDetail_content_tv);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        eventToolbar.setTitle(eventName);
        Glide.with(this).load(eventImage).into(eventIv);
        String s = generateEventContent(eventName);
        eventContentTv.setText(s);
    }

    private String generateEventContent(String eventName) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append(eventName);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
