package com.example.jun.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.myapplication.entity.HttpRequest;
import com.example.jun.myapplication.entity.MovieEntity;
import com.example.jun.myapplication.httpRequest.HttpMethods;
import com.example.jun.myapplication.serviceInterface.MovieService;
import com.example.jun.myapplication.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    //建议以 / 结尾
    private String baseUrl = "https://api.douban.com/v2/movie/";

    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.fab)
    public void onClick() {

        //仅仅使用 Retrofit 请求
//        getMovieWithRetrofit();


        //使用 Retrofit + RxJava
//        getMovieWithRxJava();


        //使用 完全路径
//        getTopMovie3();


        //使用封装过的Http请求
//        getTopMovie2();


        //使用封装过的并且是相同HTTP请求
        getTopMovie4();
    }

    //使用封装过的并且是相同HTTP请求
    public void getTopMovie4() {
        Subscriber<HttpRequest<List<MovieEntity>>> movieEntitySubscriber = new Subscriber<HttpRequest<List<MovieEntity>>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                //这里会报错，因为是模拟的数据
                tv.setText(e.getMessage());
            }

            @Override
            public void onNext(HttpRequest<List<MovieEntity>> listHttpRequest) {

                tv.setText(listHttpRequest.getDatas().toString());
            }
        };

        HttpMethods.getInstance().getTopMovies2(movieEntitySubscriber, 0, 5);

    }

    //使用封装过的Http请求
    public void getTopMovie2() {

        //使用封装过的请求
        Subscriber<MovieEntity> movieEntitySubscriber = new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                tv.setText(e.getMessage());
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                tv.setText(movieEntity.toString());

            }
        };
        HttpMethods.getInstance().getTopMovies(movieEntitySubscriber, 0, 5);

    }


    //直接使用全路径 注意用 / 结尾
    public void getTopMovie3() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);

        movieService.getTopMovie3("top250?start=0&count=10/")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onError(Throwable e) {
                        tv.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        tv.setText(movieEntity.toString());
                    }
                });
    }


    //加入RxJava
    public void getMovieWithRxJava() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);

        movieService.getTopMovie2(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onError(Throwable e) {
                        tv.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        tv.setText(movieEntity.toString());
                    }
                });


    }

    //只用Retrofit
    public void getMovieWithRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);

        Call<MovieEntity> call = movieService.getTopMovie(0, 10);

        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                tv.setText("" + response.body().getCount());

            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });


    }


}
