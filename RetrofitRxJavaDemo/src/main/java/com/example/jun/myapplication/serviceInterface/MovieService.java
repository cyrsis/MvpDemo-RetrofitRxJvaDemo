package com.example.jun.myapplication.serviceInterface;

import com.example.jun.myapplication.entity.HttpRequest;
import com.example.jun.myapplication.entity.MovieEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by jun on 2016/10/31.
 */

public interface MovieService {

    /**
     * 单纯用 Retrofit
     * @param start
     * @param conut
     * @return
     */
    @GET("top250")
    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int conut);

    /**
     * 使用 Retrofit + RxJava
     * @param start
     * @param count
     * @return
     */
    @GET("top250")
    Observable<MovieEntity> getTopMovie2(@Query("start") int start, @Query("count") int count);

    /**
     * 使用全路径
     * @param url
     * @return
     */
    @GET
    Observable<MovieEntity> getTopMovie3(@Url String url);


    /**
     * 对 Http 请求进行相同格式的请求封装
     * @param start
     * @param count
     * @return
     */
    @GET("top250")
    Observable<HttpRequest<List<MovieEntity>>> getTopMovie4(@Query("start") int start, @Query("count") int count);


}
