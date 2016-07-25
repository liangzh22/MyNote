package com.example.administrator.mynote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mynote.DAO.DailyDAO;

/**
 * Created by Administrator on 2016/7/2.
 */
public class ShowDailyActivity extends Activity {
    private TextView date;
    private TextView weather;
    private TextView content;
    private SharedPreferences sp;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdaily);
        date=(TextView)findViewById(R.id.showdaily_date);
        weather=(TextView)findViewById(R.id.showdaily_weather);
        content=(TextView)findViewById(R.id.showdaily_daily);
        delete=(Button)findViewById(R.id.showdaily_delete);
        sp=getSharedPreferences("user",MODE_PRIVATE);
        Intent intent=getIntent();
        final int dailyid=intent.getIntExtra("dailyid",0);
        String Date=intent.getStringExtra("date");
        String Weather=intent.getStringExtra("weather");
        String content1=intent.getStringExtra("content");
        content.setText(content1);
        date.setText(Date);
        weather.setText(Weather);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=sp.getString("LOGINNAME","");
                DailyDAO dailyDAO=new DailyDAO(ShowDailyActivity.this);
                dailyDAO.deleteDaily(userName,dailyid);
                Intent intent = new Intent();
                intent.setAction("action.refreshFriend");
                sendBroadcast(intent);
                finish();
            }
        });
    }
}
