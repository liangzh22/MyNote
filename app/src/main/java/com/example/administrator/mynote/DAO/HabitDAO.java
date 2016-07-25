package com.example.administrator.mynote.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import com.example.administrator.mynote.DB.DBHelper;
import com.example.administrator.mynote.Model.HabitDate;

/**
 * Created by Administrator on 2016/7/4.
 */
public class HabitDAO {
    private DBHelper dbhelper;
    private SQLiteDatabase db;
    private SharedPreferences sp;
    public HabitDAO(Context context){dbhelper=new DBHelper(context);}

    public HabitDate HabitDetail(String name) {
        String sql = "SELECT * FROM habit WHERE name =" + "'" + name + "'";
        db=dbhelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        HabitDate habitDate=new HabitDate();
        while (c.moveToNext()) {
            habitDate.setYear(c.getString(c.getColumnIndex("year")));
            habitDate.setMonth(c.getString(c.getColumnIndex("month")));
            habitDate.setDay(c.getString(c.getColumnIndex("day")));
            habitDate.setSigned(c.getInt(c.getColumnIndex("signed")));
        }
        c.close();
        return habitDate;
    }
    public boolean update(String name) {
        Time t = new Time();
        t.setToNow();
        String syear=String.valueOf(t.year);
        String smonth=String.valueOf(t.month+1);
        String sday=String.valueOf(t.monthDay);
        String date=t.year+"年"+t.month+"月"+t.monthDay+"日";
        SQLiteDatabase sdb=dbhelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("year",syear);
        values.put("month",smonth);
        values.put("day",sday);
        values.put("signed",0);
        values.put("state","false");
        values.put("date",date);
        sdb.update("habit",values,"name=?",new String[]{name});
        return false;
    }
    public boolean addHabit(String name) {
        Time t = new Time();
        t.setToNow();
        String year=""+t.year;
        String month=""+(t.month+1);
        String day=""+t.monthDay;
        String str=t.year+"年"+month+"月"+t.monthDay+"日";
        int signed=0;
        String state="false";
        String date=str;
        SQLiteDatabase sdb = dbhelper.getWritableDatabase();
        String sql = "insert into habit(name,year,month,day,signed,state,date) values(?,?,?,?,?,?,?)";
        Object obj[] = {name,year,month,day,signed,state,date};
        sdb.execSQL(sql, obj);
        return true;

    }
    public boolean sign(String name) {
        setDate(name);
        SQLiteDatabase sdb = dbhelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        String sql = "SELECT * FROM habit WHERE name =" + "'" + name + "'";
        Cursor c = sdb.rawQuery(sql, null);
        while (c.moveToNext()) {
            int signed = c.getInt(c.getColumnIndex("signed"));
            ++signed;
            values.put("signed", signed);
            values.put("state","true");
        }
        sdb.update("habit", values, "name=?", new String[]{name});
        c.close();
        return true;
    }
    public void state(String name) {
        SQLiteDatabase sdb=dbhelper.getReadableDatabase();
        String sql = "SELECT * FROM habit WHERE name =" + "'" + name + "'";
        Cursor c = sdb.rawQuery(sql, null);
        ContentValues values=new ContentValues();
        values.put("state", "false");
        sdb.update("habit", values, "name=?", new String[]{name});
        c.close();

    }
    public HabitDate checkState(String name) {
        SQLiteDatabase sdb=dbhelper.getReadableDatabase();
        String sql = "SELECT * FROM habit WHERE name =" + "'" + name + "'";
        Cursor c = sdb.rawQuery(sql, null);
        HabitDate habitDate=new HabitDate();
        while (c.moveToNext()){
            habitDate.setState(c.getString(c.getColumnIndex("state")));
        }
        c.close();
        return habitDate;
    }
    private void setDate(String name) {
        Time t=new Time();
        t.setToNow();
        String month=""+(t.month+1);
        String str=t.year+"年"+month+"月"+t.monthDay+"日";
        SQLiteDatabase sdb=dbhelper.getReadableDatabase();
        String sql = "SELECT * FROM habit WHERE name =" + "'" + name + "'";
        Cursor c = sdb.rawQuery(sql, null);
        ContentValues values=new ContentValues();
        values.put("date", str);
        sdb.update("habit", values, "name=?", new String[]{name});
        c.close();

    }
    public HabitDate checkDate(String name) {
        SQLiteDatabase sdb = dbhelper.getReadableDatabase();
        String sql = "SELECT * FROM habit WHERE name =" + "'" + name + "'";
        Cursor c = sdb.rawQuery(sql, null);
        HabitDate habitDate=new HabitDate();
        while (c.moveToNext()) {
            habitDate.setDate(c.getString(c.getColumnIndex("date")));
        }
        c.close();
        return habitDate;
    }


}
