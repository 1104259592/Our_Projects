package com.hq.app.mylibrary.utils.RetrofitRequestUtils;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitInterface {

    @GET
    Observable<ResponseBody> getHttpRetrofit(@Url String url, @QueryMap Map<String, String> map);

    //添加动态请求头
    @GET
    Observable<ResponseBody> getHttpRetrofit(@Url String url, @HeaderMap Map<String, String> headers, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postHttpRetrofit(@Url String url, @FieldMap Map<String, String> map);

    //添加动态请求头
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postHttpRetrofit(@Url String url, @HeaderMap Map<String, String> headers, @FieldMap Map<String, String> map);
}
