package com.example.administrator.mynote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mynote.DAO.NoteDAO;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ShowNoteActivity extends Activity implements View.OnClickListener {
    private TextView content;
    private Button delete;
    private int noteid;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shownote);
        content=(TextView)findViewById(R.id.shownote_note);
        delete=(Button)findViewById(R.id.shownote_delete);
        delete.setOnClickListener(this);
        Intent intent=getIntent();
        noteid=intent.getIntExtra("noteid",0);
        String content1=intent.getStringExtra("content");
        content.setText(content1);
        sp=getSharedPreferences("user",MODE_PRIVATE);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.shownote_delete:
                String userName = sp.getString("LOGINNAME", "");
                NoteDAO notedao=new NoteDAO(ShowNoteActivity.this);
                notedao.deleteNote(userName,noteid);
                Intent intent = new Intent();
                intent.setAction("action.refreshFriend");
                sendBroadcast(intent);
                finish();
        }
    }


}
