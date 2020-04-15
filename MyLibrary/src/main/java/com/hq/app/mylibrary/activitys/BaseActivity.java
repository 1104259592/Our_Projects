package com.hq.app.mylibrary.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hq.app.mylibrary.R;
import com.hq.app.mylibrary.utils.ThemeUtil;
import com.hq.app.mylibrary.utils.ToastUtil;
import com.hq.app.mylibrary.views.CircularAnim;
import com.hq.app.mylibrary.views.MyAnim;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置主题
        setTheme(ThemeUtil.getTheme(this));
        //知晓当前是在哪一个活动
        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityConllector.addActivity(this);
        init();
    }


    //初始化
    private void init() {
//        ButterKnife.bind(this);
    }

    //一键退出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityConllector.removeActivity(this);
    }

    //消息提示
    protected void showMessage(String msg) {
        ToastUtil.showShort(this, msg);
    }

    //获取当前主题风格
    protected int getThemeStyle() {
        return ThemeUtil.getThemeStyle(this);
    }

    //设置主题
    protected void setThemeStyle(@ThemeUtil.ThemeStyle int style) {
        ThemeUtil.setThemeStyle(this, style);
    }

    /**
     * 结束活动页动画
     */
    protected void baseFinish() {
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    /**
     * 跳转活动页动画
     */
    protected void baseStartIntent(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 带回调的跳转活动页动画
     */
    protected void baseStartIntentForResult(Intent intent, final int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    protected static final int REQUESTCODE = 0;//intent请求码
    protected static final int RESULTCODE = 1;//intent回调码

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULTCODE) {
            boolean change = data.getBooleanExtra("change", false);
            if (change) {
                recreate();
            }
        }
    }

    /**
     * 跳转活动页动画
     */
    protected void baseStartIntent(final Intent intent, final int colorOrImageRes) {
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                baseStartIntent(getWindow().getDecorView(), intent, colorOrImageRes);
            }
        });

    }

    /**
     * 跳转活动页动画
     */
    protected void baseStartIntent(View view, final Intent intent, int colorOrImageRes) {
        CircularAnim.fullActivity(this, view)
                .colorOrImageRes(colorOrImageRes)
                .go(new CircularAnim.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        startActivity(intent);
                    }
                });
    }

    //初始动画
    protected void initAnim() {
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                CircularAnim.show(getWindow().getDecorView()).go();
            }
        });
    }

    @Override
    public void onBackPressed() {
        baseFinish();
    }
}
