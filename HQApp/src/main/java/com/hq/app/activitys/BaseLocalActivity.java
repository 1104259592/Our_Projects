package com.hq.app.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.hq.app.mylibrary.activitys.BaseActivity;

public class BaseLocalActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //判断是否已登录
    protected boolean isLoging() {
        boolean isloging = false;
        if (!isloging) {
            Snackbar.make(getWindow().getDecorView(), "请先登录", Snackbar.LENGTH_LONG)
                    .setAction("登录", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = LoginActivity.startLoginActivity(BaseLocalActivity.this);
                            baseStartIntent(intent);
                        }
                    }).show();
        }
        return isloging;
    }
}
