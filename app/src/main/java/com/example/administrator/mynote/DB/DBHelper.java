package com.example.administrator.mynote.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/30.
 */
public class DBHelper extends SQLiteOpenHelper {
    private final static int DB_VERSION=1;
    private final static String DB_NAME="MyNote.db";
    public static final String CREATE_USER="create table user("
            +"userid integer primary key autoincrement,"
            +"username text,"+"password text,"+"email text)";
    public static final String CREATE_DAILY="create table daily("
            +"dailyid integer primary key autoincrement,"
            +"date text,"
            +"weather text,"
            +"name text,"
            +"content text)";
    public static final String CREATE_NOTE="create table note("
            +"noteid integer primary key autoincrement,"
            +"name text,"+"note text)";
    public static final String CREATE_HABIT="create table habit("
            +"habitid integer primary key autoincrement,"
            +"year text,"
            +"month text,"
            +"name text,"
            +"signed integer,"
            +"state text,"
            +"date text,"
            +"day text)";
    private Context mcontext;
    public DBHelper(Context context) {
        super(context,DB_NAME , null, DB_VERSION);
        mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_DAILY);
        db.execSQL(CREATE_NOTE);
        db.execSQL(CREATE_HABIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
