package com.example.administrator.mynote.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.mynote.DB.DBHelper;
import com.example.administrator.mynote.Model.Note;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/1.
 */
public class NoteDAO {
    private DBHelper dbhelper;
    private SQLiteDatabase db;
    public NoteDAO(Context context) {
        dbhelper=new DBHelper(context);
        db = dbhelper.getReadableDatabase();
    }
    public boolean addNote(String content,String username){
        ContentValues values=new ContentValues();
        values.put("name",username);
        values.put("note",content);
        db.insert("note",null,values);
        return true;
    }
    public boolean deleteNote(String username,int noteid){
        String sql = "delete from note where noteid=? and name=?";
        db.execSQL(sql, new Object [] {noteid,username});
        db.close();
        return true;
    }
    public ArrayList<Note> queryCollect(String name) {
        String sql = "select * from note where name='"+name+"'";
        return ExecSQLForNewsInfo(sql);
    }
    private ArrayList<Note> ExecSQLForNewsInfo(String sql) {
        ArrayList<Note> list = new ArrayList<Note>();
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            Note note = new Note();
            note.setNoteid(c.getInt(c.getColumnIndex("noteid")));
            note.setContent(c.getString(c.getColumnIndex("note")));
            list.add(note);
        }
        c.close();
        return list;
    }

}
