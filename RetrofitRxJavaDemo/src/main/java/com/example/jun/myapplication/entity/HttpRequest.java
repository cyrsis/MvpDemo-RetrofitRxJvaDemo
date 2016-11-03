package com.example.jun.myapplication.entity;

/**
 * Created by jun on 2016/11/1.
 */

/**
 * 封装相同格式的Http请求
 *
 */
public class HttpRequest<T> {
    private int start;
    private int conut;
    private int total;
    private String title;

    //用来模仿Data
    private T datas;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getConut() {
        return conut;
    }

    public void setConut(int conut) {
        this.conut = conut;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "start=" + start +
                ", conut=" + conut +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", datas=" + datas.toString() +
                '}';
    }
}
