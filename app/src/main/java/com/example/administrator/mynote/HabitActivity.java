package com.example.administrator.mynote;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynote.DAO.HabitDAO;
import com.example.administrator.mynote.Model.HabitDate;

import java.util.Calendar;


/**
 * Created by Administrator on 2016/6/30.
 */
public class HabitActivity extends Activity {
    private TextView year;
    private TextView month;
    private TextView day;
    private Button reset;
    private TextView suce;
    private TextView fail;
    private ImageView sign;
    private String TodayTime="TodayTime";
    private String Total="Total";
    private SharedPreferences sp;
    private int stotal;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit);
        year=(TextView)findViewById(R.id.habit_start_year);
        month=(TextView)findViewById(R.id.habit_start_month);
        day=(TextView)findViewById(R.id.habit_start_day);
        reset=(Button)findViewById(R.id.habit_reset);
        suce=(TextView)findViewById(R.id.habit_jianchi);
        fail=(TextView)findViewById(R.id.habit_stop);
        sign=(ImageView)findViewById(R.id.habit_sign);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.refreshHabit");
        registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        sp=getSharedPreferences("user",MODE_PRIVATE);
        init();

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp=getSharedPreferences("user",MODE_PRIVATE);
                String username=sp.getString("LOGINNAME","");
                HabitDAO habitDAO=new HabitDAO(HabitActivity.this);
                HabitDate habitDate=habitDAO.checkState(username);
                if("true".equals(habitDate.getState())){
                    Toast.makeText(HabitActivity.this,"今天已签到！",Toast.LENGTH_SHORT).show();
                }else {
                    if(habitDAO.sign(username)){
                    sign.setImageResource(R.mipmap.sign_unpressed);
                    Intent intent = new Intent();
                    intent.setAction("action.refreshHabit");
                    sendBroadcast(intent);
                    Toast.makeText(HabitActivity.this,"签到成功！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp=getSharedPreferences("user",MODE_PRIVATE);
                String name=sp.getString("LOGINNAME","");
                HabitDAO habitDAO=new HabitDAO(HabitActivity.this);
                habitDAO.update(name);
                Intent intent = new Intent();
                intent.setAction("action.refreshHabit");
                sendBroadcast(intent);

            }
        });
    }
    private  String getMiss(int year,int month,int day,int total){
        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,day);
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        long intervalMilli =now.getTimeInMillis()-cal.getTimeInMillis();
        int days= (int) (intervalMilli / (24 * 60 * 60 * 1000));
        int smiss=days-total+2;
        String miss=""+smiss;
        return miss;
    }
    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.refreshHabit"))
            {
                reFreshFrinedList();
            }
        }
        private void reFreshFrinedList() {
            init();
        }
    };
    public void init(){
        sp=getSharedPreferences("user",MODE_PRIVATE);
        HabitDAO habitDAO=new HabitDAO(HabitActivity.this);
        String name=sp.getString("LOGINNAME","");
        HabitDate habitDate=habitDAO.checkDate(name);
        HabitDate habitDate1=habitDAO.checkState(name);
        Time t=new Time();
        t.setToNow();
        int lastmonth=t.month+1;
        final String str=t.year+"年"+lastmonth+"月"+t.monthDay+"日";
        if(str.equals(habitDate.getDate())){
            if("true".equals(habitDate1.getState())){
                sign.setImageResource(R.mipmap.sign_unpressed);
            }else{
                sign.setImageResource(R.mipmap.sign_pressed);
            }
        }else{
            habitDAO.state(name);
            sign.setImageResource(R.mipmap.sign_pressed);
        }
        HabitDate date=habitDAO.HabitDetail(name);
        String syear=date.getYear();
        String smonth=date.getMonth();
        String sday=date.getDay();
        int signed=date.getSigned();
        year.setText(syear);
        month.setText(smonth);
        day.setText(sday);
        int ssyear=Integer.parseInt(syear);
        int ssmonth=Integer.parseInt(smonth);
        int ssday=Integer.parseInt(sday);
        fail.setText(getMiss(ssyear,ssmonth,ssday,signed));
        String total=String.valueOf(signed);
        suce.setText(total);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mRefreshBroadcastReceiver);
    }


}
