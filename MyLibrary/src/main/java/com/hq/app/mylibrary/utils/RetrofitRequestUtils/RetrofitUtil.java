package com.hq.app.mylibrary.utils.RetrofitRequestUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Message;
import android.util.Log;

import com.cwy.retrofitdownloadlib.http.DownloadHelper;
import com.cwy.retrofitdownloadlib.http.FileDownloadCallback;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 */
public class RetrofitUtil {

    private static final String TAG = "RetrofitUtil";

    public static final String KEY_NAME = "RetrofitUtil";//IP key值
    public static final String KEY_IP = "ip";//IP key值
    public static final String KEY_PORT = "port";//端口 key值
    public static final String SERVICEIP = "172.16.0.241";//服务器默认IP地址
    public static final String SERVICEPORT = "8080";//服务器默认端口

    private static RetrofitUtil mInstance = null;
    private static Retrofit mRetrofit = null;
    private static boolean requesting = false;//是否正在请求，保证每次只能请求一个接口

    private static String baseUrl;

    public static RetrofitUtil getInstance(Context context) {
        synchronized (RetrofitUtil.class) {
            if (mInstance == null) {
                mInstance = new RetrofitUtil(context);
            }
        }
        return mInstance;
    }

    private RetrofitUtil(final Context context) {
        //在此处设置IP和端口
        SharedPreferences preferences = context.getSharedPreferences(
                KEY_NAME, Context.MODE_PRIVATE);
        String ip = preferences.getString(KEY_IP, SERVICEIP);
        String port = preferences.getString(KEY_PORT, SERVICEPORT);
        baseUrl = "http://" + ip + ":" + port;
        resetApp(baseUrl);
        Log.v(TAG, "进入RetrofitUtil");
        preferences.registerOnSharedPreferenceChangeListener(mListener);
    }

    //监听IP和端口变化，并重新赋值
    private SharedPreferences.OnSharedPreferenceChangeListener mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

        @Override
        public void onSharedPreferenceChanged(
                SharedPreferences sharedPreferences, String key) {
            Log.v(TAG, "改变 " + key);
            String ip = sharedPreferences.getString(KEY_IP, SERVICEIP);
            String port = sharedPreferences.getString(KEY_PORT, SERVICEPORT);
            baseUrl = "http://" + ip + ":" + port;
            resetApp(baseUrl);
        }
    };

    private void resetApp(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    //请求
    public static void request(String url, final int key, final HttpRetrofitCallback callback, final Activity a) {
        if (a.isDestroyed()) {
            return;
        }
        if (requesting) {
            if (!a.isDestroyed() && callback != null) {
                callback.onNotify("正在请求，请稍后...");
            }
            return;
        }
        requesting = true;
        RetrofitInterface retrofitInterface = mRetrofit.create(RetrofitInterface.class);
        retrofitInterface.getHttpRetrofit(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v(TAG, "onSubscribe " + d);
                    }

                    @Override
                    public void onNext(ResponseBody data) {
                        try {
                            String result = data.string();
                            Log.v(TAG, "onNext " + result);
                            if (!a.isDestroyed() && callback != null) {
                                Message message = new Message();
                                message.what = key;
                                message.obj = result;
                                callback.onSuccess(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        requesting = false;
                        String msg = e.toString();
                        Log.v(TAG, "onError " + msg);
                        if (!a.isDestroyed() && callback != null) {
                            if (msg.contains("java.net.SocketTimeoutException")) {
                                callback.onError("请求超时");
                            } else if (msg.contains("org.apache.http.conn.HttpHostConnectException")) {
                                callback.onError("网络异常");
                            } else if (msg.contains("Internal Server Error")) {
                                callback.onError("网络异常，请重新操作");
                            } else if (msg.contains("Not Found")) {
                                callback.onError("服务器未启动");
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.v(TAG, "onComplete");
                        requesting = false;
                    }
                });

    }

    //map参数方式请求
    public static void request(String url, Map<String, String> map, final int key, final HttpRetrofitCallback callback, final Activity a) {
        if (a.isDestroyed()) {
            return;
        }
        if (requesting) {
            if (!a.isDestroyed() && callback != null) {
                callback.onNotify("正在请求，请稍后...");
            }
            return;
        }
        requesting = true;
        RetrofitInterface retrofitInterface = mRetrofit.create(RetrofitInterface.class);
        retrofitInterface.getHttpRetrofit(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v(TAG, "onSubscribe " + d);
                    }

                    @Override
                    public void onNext(ResponseBody data) {
                        try {
                            String result = data.string();
                            Log.v(TAG, "onNext " + result);
                            if (!a.isDestroyed() && callback != null) {
                                Message message = new Message();
                                message.what = key;
                                message.obj = result;
                                callback.onSuccess(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        requesting = false;
                        String msg = e.toString();
                        Log.v(TAG, "onError " + msg);
                        if (!a.isDestroyed() && callback != null) {
                            if (msg.contains("java.net.SocketTimeoutException")) {
                                callback.onError("请求超时");
                            } else if (msg.contains("org.apache.http.conn.HttpHostConnectException")) {
                                callback.onError("网络异常");
                            } else if (msg.contains("Internal Server Error")) {
                                callback.onError("网络异常，请重新操作");
                            } else if (msg.contains("Not Found")) {
                                callback.onError("服务器未启动");
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.v(TAG, "onComplete");
                        requesting = false;
                    }
                });

    }

    //文件下载
    public static void download(final String url, final String fileType, String fileName, final MyFileDownloadCallback callback, final Activity a) {
        if (a.isDestroyed()) {
            return;
        }
        Log.v(TAG, "baseUrl " + baseUrl + url);
        DownloadHelper.getInstance()
                .downloadFile(baseUrl + url, Environment.getExternalStorageDirectory() + "/EjkjUpdateVerson", fileName,
                        new FileDownloadCallback<File>() {
                            @Override
                            public void onDownLoadSuccess(File file) {
                                Log.v(TAG, "onDownLoadSuccess " + file.getAbsolutePath());
                                if (!a.isDestroyed() && callback != null) {
                                    callback.onSuccess(fileType, file);
                                }
                                if (a.isDestroyed()) {
                                    DownloadHelper.getInstance().release();
                                }
                            }

                            @Override
                            public void onDownLoadFail(Throwable e) {
                                String msg = e.toString();
                                Log.v(TAG, "onDownLoadFail " + msg);
                                if (!a.isDestroyed() && callback != null) {
                                    callback.onError(fileType, msg);
                                }
                                if (a.isDestroyed()) {
                                    DownloadHelper.getInstance().release();
                                }
                            }

                            @Override
                            public void onProgress(int progress, int total) {
                                Log.v(TAG, "onProgress " + progress);
                                if (!a.isDestroyed() && callback != null) {
                                    callback.onDownProgress(fileType, progress);
                                }
                            }
                        });
    }

    /**
     * http请求监听
     */
    public interface HttpRetrofitCallback {

        /**
         * 请求成功
         *
         * @param message 请求返回数据
         */
        void onSuccess(Message message);

        /**
         * 请求失败
         *
         * @param msg 失败信息
         */
        void onError(String msg);

        /**
         * 消息提示
         *
         * @param notify 提示信息
         */
        void onNotify(String notify);
    }

    /**
     * 文件下载请求监听
     */
    public interface MyFileDownloadCallback {

        /**
         * 下载成功
         *
         * @param file 下载的文件
         */
        void onSuccess(String type, File file);

        /**
         * 下载失败
         *
         * @param msg 失败信息
         */
        void onError(String type, String msg);

        /**
         * 下载进度
         *
         * @param progress 下载进度
         */
        void onDownProgress(String type, int progress);
    }
}
