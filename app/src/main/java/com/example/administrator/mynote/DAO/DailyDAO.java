package com.example.administrator.mynote.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.mynote.DB.DBHelper;
import com.example.administrator.mynote.Model.Daily;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/2.
 */
public class DailyDAO {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DailyDAO(Context context){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    public boolean addDaily(String username,String content,String date,String weather){
        String sql="insert into daily(name,content,date,weather) values(?,?,?,?)";
        Object obj[]={username,content,date,weather};
        db.execSQL(sql, obj);
        return true;
    }
    public boolean deleteDaily(String username,int dailyId){
        String sql = "delete from daily where dailyid=? and name=?";
        db.execSQL(sql, new Object [] {dailyId,username});
        db.close();
        return true;
    }
    public ArrayList<Daily> queryCollect(String name) {
        String sql = "select * from daily where name=?";
        ArrayList<Daily> list = new ArrayList<Daily>();
        Cursor c = db.rawQuery(sql, new String[]{name});
        while (c.moveToNext()) {
            Daily daily=new Daily();
            daily.setDailyid(c.getInt(c.getColumnIndex("dailyid")));
            daily.setDaily(c.getString(c.getColumnIndex("content")));
            daily.setDate(c.getString(c.getColumnIndex("date")));
            daily.setWeather(c.getString(c.getColumnIndex("weather")));
            list.add(daily);
        }
        c.close();
        return list;
    }
    private ArrayList<Daily> ExecSQLForNewsInfo(String sql) {
        ArrayList<Daily> list = new ArrayList<Daily>();
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            Daily daily=new Daily();
            daily.setDailyid(c.getInt(c.getColumnIndex("dailyid")));
            daily.setDaily(c.getString(c.getColumnIndex("content")));
            daily.setDate(c.getString(c.getColumnIndex("date")));
            daily.setWeather(c.getString(c.getColumnIndex("weather")));
            list.add(daily);
        }
        c.close();
        return list;
    }
}
