package com.hq.app.mylibrary.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.hq.app.mylibrary.R;
import com.hq.app.mylibrary.utils.DataUtil;
import com.hq.app.mylibrary.utils.MyAppUtil;
import com.hq.app.mylibrary.utils.RetrofitRequestUtils.RetrofitUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络设置 修改IP和端口号
 */
public class InternetSettingActivity extends BaseActivity {

    private EditText setting_ip, setting_port;
    private TextView local_ip, local_mac;
    private Toolbar internet_setting_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_setting);
        setTitle("网络设置");

        init();
    }

    private void init() {
        internet_setting_toolbar = (Toolbar) findViewById(R.id.internet_setting_toolbar);
        local_ip = (TextView) findViewById(R.id.local_ip);
        local_mac = (TextView) findViewById(R.id.local_mac);
        setting_ip = (EditText) findViewById(R.id.setting_ip);
        setting_port = (EditText) findViewById(R.id.setting_port);

        setSupportActionBar(internet_setting_toolbar);

        internet_setting_toolbar.setNavigationIcon(R.mipmap.back);
        internet_setting_toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                baseFinish();
            }
        });

        local_ip.setText(MyAppUtil.getIpAddress());
        local_mac.setText(MyAppUtil.getMACAddress());

        SharedPreferences preferences = getSharedPreferences(
                RetrofitUtil.KEY_NAME, Context.MODE_PRIVATE);
        String ip = preferences.getString(RetrofitUtil.KEY_IP, RetrofitUtil.SERVICEIP);
        String port = preferences.getString(RetrofitUtil.KEY_PORT, RetrofitUtil.SERVICEPORT);
        setting_ip.setText(ip);
        setting_port.setText(port);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_internet_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_save) {
            String strIP = setting_ip.getText().toString();
            String regEx = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(strIP);
            boolean isok = matcher.matches();
            if (isok
                    && !"".equals(DataUtil.NoNull(setting_port.getText().toString(), ""))) {
                SharedPreferences preferences = getSharedPreferences(
                        RetrofitUtil.KEY_NAME, Context.MODE_PRIVATE);
                Editor editor = preferences.edit();
                editor.putString(RetrofitUtil.KEY_IP, setting_ip.getText().toString());
                editor.putString(RetrofitUtil.KEY_PORT, setting_port.getText().toString());
                editor.commit();
                showMessage("保存成功");
            } else if (!isok) {
                showMessage("IP格式错误");
            } else if ("".equals(DataUtil.NoNull(setting_port.getText().toString(), ""))) {
                showMessage("请输入端口");
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        baseFinish();
    }

    //启动网络设置页面
    public static Intent startInternetSettingActivity(Context context) {
        Intent intent = new Intent(context, InternetSettingActivity.class);
        return intent;
    }
}
