package com.hq.app.mylibrary.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraUtils implements SurfaceHolder.Callback {

    private static final String TAG = "CameraUtils";

    private Context context;
    private Camera mCamera;// Camera对象
    private SurfaceHolder holder;// SurfaceView的控制器

    public CameraUtils(Context context, SurfaceView surfaceView) {
        this.context = context;
        if (isPermission() && checkCameraHardware()) {
            holder = surfaceView.getHolder();
        }
    }

    //启动相机
    public void startCamera() {
        if (holder != null) {
            holder.addCallback(this);
        }
    }

    // 重写SurfaceHolder.Callback接口的方法，
    // 在创建面板的时候调用的方法
    @Override
    public void surfaceCreated(SurfaceHolder surfaceholder) {
        openCamera();
    }

    // 在面板改变的时候调用的方法
    @Override
    public void surfaceChanged(SurfaceHolder surfaceholder, int format, int w, int h) {
        initCamera(w, h);
    }

    // 销毁面板时的方法
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceholder) {
        stopCamera();
    }

    // 检测摄像头是否存在
    private boolean checkCameraHardware() {
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // 摄像头存在
            return true;
        } else {
            // 摄像头不存在
            return false;
        }
    }

    /*打开相机*/
    private void openCamera() {
        try {
            mCamera = null;
            try {
                mCamera = Camera.open(0);
            } catch (Exception e) {
                Log.v(TAG, "摄像头被占用");
            }
            if (mCamera == null) {
                Log.v(TAG, "摄像机为空");
            } else {
                mCamera.setPreviewDisplay(holder);//设置显示面板控制器
                mCamera.startPreview();//开始预览，这步操作很重要
            }
        } catch (IOException exception) {
            mCamera.release();
            mCamera = null;
        }
    }

    /* 相机初始化 */
    private void initCamera(int w, int h) {
        if (mCamera != null) {
            try {
                Camera.Parameters parameters = mCamera.getParameters();
                parameters.setPictureSize(w, h);
                mCamera.setParameters(parameters);
                /* 打开预览画面 */
                mCamera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* 停止相机 */
    private void stopCamera() {
        if (mCamera != null) {
            try {
                /* 停止预览 */
                mCamera.stopPreview();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mCamera.release();
                mCamera = null;
            }
        }
    }

    //是否有权限
    public boolean isPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ToastUtil.showShort(context, "无相机权限");
            return false;
        } else {
            return true;
        }
    }
}
