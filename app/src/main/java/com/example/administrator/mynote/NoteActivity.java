package com.example.administrator.mynote;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.mynote.Adapter.NoteAdapter;
import com.example.administrator.mynote.DAO.NoteDAO;
import com.example.administrator.mynote.Model.Note;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class NoteActivity extends Activity implements AdapterView.OnItemClickListener,
        View.OnClickListener{
    private ListView listView;
    private Button add;
    private String userName;
    private SharedPreferences sp;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        listView=(ListView)findViewById(R.id.mynote_listview);
        add=(Button)findViewById(R.id.mynote_add);
        sp=getSharedPreferences("user",MODE_PRIVATE);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.refreshFriend");
        registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        add.setOnClickListener(this);
        init();
    }

    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.refreshFriend"))
            {
                reFreshFrinedList();
            }
        }

        private void reFreshFrinedList() {
            init();
        }
    };
    public void init() {
        userName = sp.getString("LOGINNAME", "");

        if(userName != null && !userName.equals("")) {
            NoteDAO notedao = new NoteDAO(NoteActivity.this);
            noteList = notedao.queryCollect(userName);

            if (noteList.size() != 0) {
                noteAdapter = new NoteAdapter(NoteActivity.this, R.layout.note_item, noteList);
                listView.setAdapter(noteAdapter);
                listView.setOnItemClickListener(this);
            }
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = noteList.get(position);
        Intent intent = new Intent(NoteActivity.this,ShowNoteActivity.class);
        intent.putExtra("noteid", note.getNoteid());
        intent.putExtra("content",note.getContent());
        startActivity(intent);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.mynote_add:
                Intent intent=new Intent(NoteActivity.this,AddNoteActivity.class);
                startActivity(intent);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mRefreshBroadcastReceiver);
    }
}

