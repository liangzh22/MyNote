package com.example.administrator.mynote.Model;

/**
 * Created by Administrator on 2016/7/4.
 */
public class HabitDate {
    private String year;
    private String month;
    private String day;
    private int signed;
    private String date;
    private String state;

    public HabitDate(){}

    public int getSigned() {
        return signed;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {

        this.month = month;
    }

    public void setYear(String year) {

        this.year = year;
    }

    public String getDay() {

        return day;
    }

    public String getMonth() {

        return month;
    }

    public String getYear() {

        return year;
    }

    public void setSigned(int signed) {
        this.signed = signed;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {

        return date;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {

        return state;
    }
}
