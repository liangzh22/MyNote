package com.example.administrator.mynote.Model;

/**
 * Created by Administrator on 2016/7/1.
 */
public class Note {
    private int noteid;
    private String content;

    public Note(){};

    public Note(int noteid,String content){
        this.noteid=noteid;
        this.content=content;
    }


    public void setContent(String content) {

        this.content = content;
    }

    public void setNoteid(int noteid) {

        this.noteid = noteid;
    }


    public String getContent() {

        return content;
    }

    public int getNoteid() {

        return noteid;
    }

}
