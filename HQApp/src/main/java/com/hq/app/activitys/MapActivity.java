package com.hq.app.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.LocationClient;


/**
 * @Class: MapActivity
 * @Description:
 * @author: zhangqi
 * @Date: 2020/3/31
 */
public class MapActivity extends AppCompatActivity {
    public LocationClient mLocationClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
