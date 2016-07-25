package com.example.administrator.mynote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.mynote.DAO.DailyDAO;

/**
 * Created by Administrator on 2016/7/2.
 */
public class WriteDaily extends Activity {
    private EditText date;
    private EditText weather;
    private EditText daily;
    private Button add;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writedaily);
        date=(EditText) findViewById(R.id.writedaily_date);
        weather=(EditText)findViewById(R.id.writedaily_weather);
        daily=(EditText)findViewById(R.id.writedaily_daily);
        add=(Button)findViewById(R.id.writedaily_done);
        sp=getSharedPreferences("user",MODE_PRIVATE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=sp.getString("LOGINNAME","");
                String content=daily.getText().toString();
                String Date=date.getText().toString();
                String Weather=weather.getText().toString();
                DailyDAO dailyDAO=new DailyDAO(WriteDaily.this);
                dailyDAO.addDaily(userName,content,Date,Weather);
                Intent intent = new Intent();
                intent.setAction("action.refreshFriend");
                sendBroadcast(intent);
                finish();
            }
        });
    }
}
