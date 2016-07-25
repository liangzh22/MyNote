package com.example.administrator.mynote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.mynote.DAO.NoteDAO;

/**
 * Created by Administrator on 2016/7/1.
 */
public class AddNoteActivity extends Activity implements View.OnClickListener {
    private EditText econtent;
    private Button add;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnote);
        econtent=(EditText) findViewById(R.id.addnote_note);
        add=(Button)findViewById(R.id.addnote_add);
        add.setOnClickListener(this);
        sp=getSharedPreferences("user",MODE_PRIVATE);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.addnote_add:
                String content=econtent.getText().toString();
                String userName = sp.getString("LOGINNAME", "");
                NoteDAO notedao=new NoteDAO(AddNoteActivity.this);
                notedao.addNote(content,userName);
                Intent intent = new Intent();
                intent.setAction("action.refreshFriend");
                sendBroadcast(intent);
                finish();
        }
    }
}
