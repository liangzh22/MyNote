package com.example.administrator.mynote.Model;

/**
 * Created by Administrator on 2016/7/2.
 */
public class Daily {
    private int dailyid;
    private String date;
    private String weather;
    private String daily;

    public Daily() {
    }

    public Daily(int dailyid, String date, String weather, String daily) {
        this.dailyid = dailyid;
        this.date = date;
        this.weather = weather;
        this.daily = daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    public void setWeather(String weather) {

        this.weather = weather;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public void setDailyid(int dailyid) {

        this.dailyid = dailyid;
    }

    public String getDaily() {

        return daily;
    }

    public String getWeather() {

        return weather;
    }

    public String getDate() {

        return date;
    }

    public int getDailyid() {

        return dailyid;
    }
}
