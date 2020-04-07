package com.hq.app.activitys;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.hq.app.R;
import com.hq.app.mylibrary.activitys.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Class: MapActivity
 * @Description:
 * @author: zhangqi
 * @Date: 2020/3/31
 */
public class MapActivity extends BaseActivity {
    public LocationClient mLocationClient;
//    @BindView(R.id.position_text_view)
    TextView positionText;
    private MapView mapView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());//创建LocationClient实例，获取一个全局的Context参数传入
        mLocationClient.registerLocationListener(new MyLocationListener());//注册一个定位监听器，当获取到位置信息的时候，会回调这个定位监听器
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidumap);
        mapView = findViewById(R.id.bmapView);
         positionText = findViewById(R.id.position_text_view);
        //运行时权限，在运行时一次性申请3个权限
        List<Object> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this,permissions,1);
        } else {
            requestLocation();
        }
    }
    //开始定位功能
    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    //回调的注册的监听器
    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuilder currentPostion = new StringBuilder();
            currentPostion.append("纬度: ").append(location.getLatitude()).//获取维度
                    append("\n");
            currentPostion.append("经度: ").append(location.getLongitude()).//获取经度
                    append("\n");
            currentPostion.append("国家: ").append(location.getCountry())
                    .append("\n");
            currentPostion.append("省: ").append(location.getProvince())
                    .append("\n");
            currentPostion.append("市: ").append(location.getCity())
                    .append("\n");
            currentPostion.append("区: ").append(location.getDistrict())
                    .append("\n");
            currentPostion.append("街道: ").append(location.getStreet())
                    .append("\n");
            currentPostion.append("定位方式:");//获取定位方式
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                currentPostion.append("GPS");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
                currentPostion.append("网络");
            }
                positionText.setText(currentPostion);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
    }

    //启动主题设置页
    public static Intent startMapActivity(Context c) {
        Intent intent = new Intent(c, MapActivity.class);
//        intent.putExtra("themeStyle", themeStyle);
        return intent;
    }
}
