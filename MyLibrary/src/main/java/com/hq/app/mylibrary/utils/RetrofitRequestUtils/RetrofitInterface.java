package com.hq.app.mylibrary.utils.RetrofitRequestUtils;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitInterface {

//    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    @POST
    Observable<ResponseBody> getHttpRetrofit(@Url String url);
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // 采用Observable<...>接口
    // getCall()是接受网络请求数据的方法

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> getHttpRetrofit(@Url String url, @FieldMap Map<String, String> map);
}
