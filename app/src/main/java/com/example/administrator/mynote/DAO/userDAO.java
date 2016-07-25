package com.example.administrator.mynote.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.mynote.DB.DBHelper;
import com.example.administrator.mynote.Model.User;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/30.
 */
public class userDAO {
    private DBHelper dbhelper;
    private SQLiteDatabase db;
    public userDAO(Context context){dbhelper=new DBHelper(context);}

    public boolean register(User user) {
        if (!searchData(user.getUserName()))
            return false;
        SQLiteDatabase sdb = dbhelper.getReadableDatabase();
        String sql = "insert into user(username,password,email) values(?,?,?)";
        Object obj[] = {user.getUserName(), user.getPassword(),user.getEmail()};
        sdb.execSQL(sql, obj);
        return true;
    }
    public boolean searchData(String name) {
        String sql = "SELECT * FROM user WHERE username =" + "'" + name + "'";
        ArrayList<User> list=ExecSQLForUserInfo(sql);
        return list.size()==0?true:false;
    }

    private ArrayList<User> ExecSQLForUserInfo(String sql) {
        ArrayList<User> list = new ArrayList<User>();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            User user = new User();
            user.setUserName(c.getString(c.getColumnIndex("username")));
            user.setPassword(c.getString(c.getColumnIndex("password")));
            user.setEmail(c.getString(c.getColumnIndex("email")));
            list.add(user);
        }
        c.close();
        return list;
    }
    private Cursor ExecSQLForCursor(String sql) {
        db = dbhelper.getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public boolean login(String userName,String password){
        db=dbhelper.getWritableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=db.rawQuery(sql,new String[]{userName,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }else {
            return false;
        }
    }
    public User PersonDetail(String name) {
        String sql = "SELECT * FROM user WHERE username =" + "'" + name + "'";
        Cursor c = ExecSQLForCursor(sql);
        User user = new User();
        while (c.moveToNext()) {
            user.setUserName(c.getString(c.getColumnIndex("username")));
            user.setEmail(c.getString(c.getColumnIndex("email")));
            user.setPassword(c.getString(c.getColumnIndex("password")));
        }
        return user;
    }
    public boolean update(String name,String pass1,String pass2) {
        String sql = "update user set password=? where username=? and password=?";
        SQLiteDatabase sdb=dbhelper.getReadableDatabase();
        Object obj[]={pass2,name,pass1};
        sdb.execSQL(sql, obj);
        return false;
    }
}
