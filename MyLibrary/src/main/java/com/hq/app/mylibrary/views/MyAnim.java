package com.hq.app.mylibrary.views;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * 自定义动画view
 *
 * 位移动画
 * 缩放动画
 * 旋转动画
 * 透明度
 * 组合动画
 */
public class MyAnim extends Animation {

    private Animation animation;

    private long duration = 1000;//默认1秒
    private boolean fillAfter = false;//设置动画结束后效果保留最后一帧
    private boolean fillBefore = false;//设置动画结束后效果保留第一帧
    private int repeatCount = 0;//重复次数
    private int repeatMode = Animation.RESTART;//重复类型，有reverse和restart两个值，reverse表示倒序回放，restart表示重新放一遍，必须与repeatCount一起使用
    private Interpolator interpolator = new LinearInterpolator();//定义动画播放的速度，默认匀速

    /**
     * 设置动画持续时间
     * @param duration
     */
    @Override
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * 设置动画结束后效果是否保留最后一帧
     * 如果设置为true，控件动画结束时，将保持动画最后时的状态
     * @param fillAfter
     */
    @Override
    public void setFillAfter(boolean fillAfter) {
        this.fillAfter = fillAfter;
    }

    /**
     * 设置动画结束后效果是否保留第一帧
     * 如果设置为true,控件动画结束时，还原到开始动画前的状态
     * @param fillBefore
     */
    @Override
    public void setFillBefore(boolean fillBefore) {
        this.fillBefore = fillBefore;
    }

    /**
     * 重复次数
     * @param repeatCount
     */
    @Override
    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

     /**
     * 重复类型，有reverse和restart两个值，reverse表示倒序回放，restart表示重新放一遍，必须与repeatCount一起使用
     * @param repeatMode
     */
    @Override
    public void setRepeatMode(int repeatMode) {
        this.repeatMode = repeatMode;
    }

    /**
     * 定义动画播放的速度
     * @param interpolator AccelerateInterpolator()  越来越快
     *                       DecelerateInterpolator()  越来越慢
     *                       AccelerateDecelerateInterpolator()  先快后慢
     *                       AnticipateInterpolator()  先后退一小步然后向前加速
     *                       OvershootInterpolator()  快速到达终点超出一小步然后回到终点
     *                       AnticipateOvershootInterpolator()  到达终点超出一小步然后回到终点
     *                       BounceInterpolator()  弹球效果，弹几下回到终点
     *                       LinearInterpolator()  均匀速度
     *                       CycleInterpolator(2)  循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2* mCycles* Math.PI* input)
     */
    @Override
    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    /**
     * 动画参数配置内容
     */
    private void setConfigure() {
        if (animation != null) {
            animation.setDuration(duration);
            animation.setFillAfter(fillAfter);
            animation.setFillBefore(fillBefore);
            animation.setRepeatCount(repeatCount);
            animation.setRepeatMode(repeatMode);
            animation.setInterpolator(interpolator);
        }
    }

    /**
     * 位移动画
     * @param fromXDelta  起始点X轴坐标，可以是数值、百分数、百分数p 三种样式
     * @param toXDelta  起始点Y轴从标，可以是数值、百分数、百分数p 三种样式
     * @param fromYDelta   结束点X轴坐标
     * @param toYDelta  结束点Y轴坐标
     * @return
     */
    public Animation setTranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        setConfigure();
        return animation;
    }

    /**
     * 缩放动画
     * @param fromX  起始的X方向上相对自身的缩放比例，浮点值，比如1.0代表自身无变化，0.5代表起始时缩小一倍，2.0代表放大一倍；
     * @param toX  结尾的X方向上相对自身的缩放比例，浮点值；
     * @param fromY  起始的Y方向上相对自身的缩放比例，浮点值
     * @param toY  结尾的Y方向上相对自身的缩放比例，浮点值
     * @return
     */
    public Animation setScaleAnimation(float fromX, float toX, float fromY, float toY) {
        animation = new ScaleAnimation(fromX, toX, fromY, toY);
        setConfigure();
        return animation;
    }

    /**
     * 缩放动画
     * @param fromX  起始的X方向上相对自身的缩放比例，浮点值，比如1.0代表自身无变化，0.5代表起始时缩小一倍，2.0代表放大一倍；
     * @param toX  结尾的X方向上相对自身的缩放比例，浮点值；
     * @param fromY  起始的Y方向上相对自身的缩放比例，浮点值
     * @param toY  结尾的Y方向上相对自身的缩放比例，浮点值
     * @param pivotX  动画起始位置X坐标，浮点值
     * @param pivotY  动画起始位置Y坐标，浮点值
     * @return
     */
    public Animation setScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY) {
        animation = new ScaleAnimation(fromX, toX, fromY, toY, pivotX, pivotY);
        setConfigure();
        return animation;
    }

    /**
     * 旋转动画
     * @param fromDegrees  为开始旋转的角度，正值代表顺时针方向度数，负值代码逆时针方向度数
     * @param toDegrees  为结束时旋转角度，取值同fromDegrees
     * @return
     */
    public Animation setRotateAnimation(float fromDegrees, float toDegrees) {
        animation = new RotateAnimation(fromDegrees, toDegrees);
        setConfigure();
        return animation;
    }

    /**
     * 旋转动画
     * @param fromDegrees  为开始旋转的角度，正值代表顺时针方向度数，负值代码逆时针方向度数
     * @param toDegrees  为结束时旋转角度，取值同fromDegrees
     * @param pivotX  动画起始位置X坐标
     * @param pivotY  动画起始位置Y坐标
     * @return
     */
    public Animation setRotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY) {
        animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        setConfigure();
        return animation;
    }

    /**
     * 透明度动画
     * @param fromAlpha  动画开始的透明度，从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
     * @param toAlpha  动画结束时的透明度，也是从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
     * @return
     */
    public Animation setAlphaAnimation(float fromAlpha, float toAlpha) {
        animation = new AlphaAnimation(fromAlpha, toAlpha);
        setConfigure();
        return animation;
    }

    /**
     * 组合动画
     * @param shareInterpolator true所有的animtion共用同一个Interpolator
     * @param anims  动画集合
     * @return
     */
    public Animation setAnimationSet(boolean shareInterpolator, Animation... anims) {
        animation = new AnimationSet(shareInterpolator);
        for (Animation anim: anims) {
            ((AnimationSet) animation).addAnimation(anim);
        }
        setConfigure();
        return animation;
    }

}
