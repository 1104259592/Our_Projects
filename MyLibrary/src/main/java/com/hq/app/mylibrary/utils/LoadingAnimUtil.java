package com.hq.app.mylibrary.utils;

import android.content.Context;
import android.support.annotation.StringDef;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.Pulse;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.github.ybq.android.spinkit.style.Wave;
import com.hq.app.mylibrary.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 加载动画工具类
 * 动画（Sprite）类型
 * RotatingPlane   翻转平面
 * DoubleBounce    二重弹跳
 * Wave            电波
 * WanderingCubes  移动立方体
 * Pulse           脉冲
 * ChasingDots     追赶圆点
 * ThreeBounce     三个点弹跳
 * Circle          旋转
 * CubeGrid        立方体格子
 * FadingCircle    淡入旋转
 * FoldingCube     折叠立方体
 * RotatingCircle  翻转圆
 */
public class LoadingAnimUtil {

    private Context context;
    private PopupWindow popWindow;
    private Sprite sprite;

    public LoadingAnimUtil(Context context) {
        this.context = context;
    }

    //加载动画弹出框
    public void loadingAnim(View v) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_loading_anim, null);
        popWindow = new PopupWindow(view);
        popWindow.setWindowLayoutMode(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        SpinKitView spin_kit = view.findViewById(R.id.spin_kit);
        spin_kit.setIndeterminateDrawable(sprite);

        popWindow.setFocusable(true);
//        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popWindow.setOutsideTouchable(false);
    }

    //关闭弹出框
    public void dismiss() {
        if (popWindow != null && popWindow.isShowing()) {
            popWindow.dismiss();
        }
    }

    //设置动画类型
    public void setSprite(@LoadingAnim String s) {
        Sprite mSprite = new Circle();
        if (s != null) {
            switch (s) {
                case LoadingAnim.RotatingPlane:
                    mSprite = new RotatingPlane();
                    break;
                case LoadingAnim.DoubleBounce:
                    mSprite = new DoubleBounce();
                    break;
                case LoadingAnim.Wave:
                    mSprite = new Wave();
                    break;
                case LoadingAnim.WanderingCubes:
                    mSprite = new WanderingCubes();
                    break;
                case LoadingAnim.Pulse:
                    mSprite = new Pulse();
                    break;
                case LoadingAnim.ChasingDots:
                    mSprite = new ChasingDots();
                    break;
                case LoadingAnim.ThreeBounce:
                    mSprite = new ThreeBounce();
                    break;
                case LoadingAnim.Circle:
                    mSprite = new Circle();
                    break;
                case LoadingAnim.CubeGrid:
                    mSprite = new CubeGrid();
                    break;
                case LoadingAnim.FadingCircle:
                    mSprite = new FadingCircle();
                    break;
                case LoadingAnim.FoldingCube:
                    mSprite = new FoldingCube();
                    break;
                case LoadingAnim.RotatingCircle:
                    mSprite = new RotatingCircle();
                    break;
            }
        }
        this.sprite = mSprite;
    }

    @StringDef({LoadingAnim.RotatingPlane, LoadingAnim.DoubleBounce, LoadingAnim.Wave, LoadingAnim.WanderingCubes, LoadingAnim.Pulse, LoadingAnim.ChasingDots,
            LoadingAnim.ThreeBounce, LoadingAnim.Circle, LoadingAnim.CubeGrid, LoadingAnim.FadingCircle, LoadingAnim.FoldingCube, LoadingAnim.RotatingCircle})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LoadingAnim {
        String RotatingPlane = "RotatingPlane";
        String DoubleBounce = "DoubleBounce";
        String Wave = "Wave";
        String WanderingCubes = "WanderingCubes";
        String Pulse = "Pulse";
        String ChasingDots = "ChasingDots";
        String ThreeBounce = "ThreeBounce";
        String Circle = "Circle";
        String CubeGrid = "CubeGrid";
        String FadingCircle = "FadingCircle";
        String FoldingCube = "FoldingCube";
        String RotatingCircle = "RotatingCircle";
    }

}
