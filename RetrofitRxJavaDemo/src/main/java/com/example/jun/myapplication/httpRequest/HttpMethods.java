package com.example.jun.myapplication.httpRequest;

import com.example.jun.myapplication.entity.HttpRequest;
import com.example.jun.myapplication.entity.MovieEntity;
import com.example.jun.myapplication.serviceInterface.MovieService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jun on 2016/11/1.
 */
public class HttpMethods {

    //请求地址
    public static String baseUrl = "https://api.douban.com/v2/movie/";

    //超时时间
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService movieService;

    private static HttpMethods ourInstance = new HttpMethods();

    public static HttpMethods getInstance() {
        return ourInstance;
    }

    //构造私有
    private HttpMethods() {
        //手动设置一个 OkHttpClient ,并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieService = retrofit.create(MovieService.class);

    }


    /**
     * 封装过的 Http请求
     * 获取豆瓣Top电影
     *
     * @param movieEntitySubscriber
     * @param start
     * @param count
     */
    public void getTopMovies(Subscriber<MovieEntity> movieEntitySubscriber, int start, int count) {
        movieService.getTopMovie2(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieEntitySubscriber);
    }


    /**
     * 封装过对于 相同格式的HTTP请求
     * 获取豆瓣Top电影
     * @param subscriber
     * @param start
     * @param count
     */
    public void getTopMovies2(Subscriber<HttpRequest<List<MovieEntity>>> subscriber, int start, int count) {
        movieService.getTopMovie4(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
