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

import com.example.administrator.mynote.Adapter.DailyAdapter;
import com.example.administrator.mynote.DAO.DailyDAO;
import com.example.administrator.mynote.Model.Daily;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/30.
 */
public class DailyActivity extends Activity{
    private ListView listView;
    private Button add;
    private String userName;
    private SharedPreferences sp;
    private DailyAdapter dailyAdapter;
    private ArrayList<Daily>dailylist=new ArrayList<Daily>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily);
        listView = (ListView) findViewById(R.id.daily_listview);
        add = (Button) findViewById(R.id.daily_add);
        sp=getSharedPreferences("user",MODE_PRIVATE);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.refreshFriend");
        registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DailyActivity.this,WriteDaily.class);
                startActivity(intent);
            }
        });
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
            DailyDAO dailyDAO=new DailyDAO(DailyActivity.this);
            dailylist = dailyDAO.queryCollect(userName);
        }
        if(dailylist.size()!=0){
            dailyAdapter = new DailyAdapter(DailyActivity.this,R.layout.daily_item,dailylist);
            listView.setAdapter(dailyAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Daily daily=dailylist.get(i);
                    Intent intent = new Intent(DailyActivity.this,ShowDailyActivity.class);
                    intent.putExtra("dailyid", daily.getDailyid());
                    intent.putExtra("content",daily.getDaily());
                    intent.putExtra("weather",daily.getWeather());
                    intent.putExtra("date",daily.getDate());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mRefreshBroadcastReceiver);
    }

}
