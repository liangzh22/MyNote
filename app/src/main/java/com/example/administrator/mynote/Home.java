package com.example.administrator.mynote;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

/**
 * Created by Administrator on 2016/6/30.
 */
public class Home extends TabActivity implements View.OnClickListener {
    public static TabHost tabHost;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_biji;
    private RadioButton rb_riji;
    private RadioButton rb_xiguan;
    private RadioButton rb_guanli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_biji = (RadioButton) findViewById(R.id.rb_biji);
        rb_riji = (RadioButton) findViewById(R.id.rb_riji);
        rb_xiguan = (RadioButton) findViewById(R.id.rb_xiguan);
        rb_guanli = (RadioButton) findViewById(R.id.rb_guanli);
        rb_biji.setOnClickListener(this);
        rb_riji.setOnClickListener(this);
        rb_xiguan.setOnClickListener(this);
        rb_guanli.setOnClickListener(this);
        rb_biji.setChecked(true);
        initTab();


    }
    public void initTab(){
        tabHost=getTabHost();
        tabHost.addTab(tabHost.newTabSpec("note").setIndicator("note")
                .setContent(new Intent(this, NoteActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("daily").setIndicator("daily")
                .setContent(new Intent(this, DailyActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("habit").setIndicator("habit")
                .setContent(new Intent(this, HabitActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("manage").setIndicator("manage")
                .setContent(new Intent(this, ManageActivity.class)));
    }
    @Override
    public void onClick(View v) {
        rg_tab_bar.clearCheck();
        switch (v.getId()) {
            case R.id.rb_biji:
                rb_biji.setChecked(true);
                tabHost.setCurrentTabByTag("note");
                break;
            case R.id.rb_riji:
                rb_riji.setChecked(true);
                tabHost.setCurrentTabByTag("daily");
                break;
            case R.id.rb_xiguan:
                rb_xiguan.setChecked(true);
                tabHost.setCurrentTabByTag("habit");
                break;
            case R.id.rb_guanli:
                rb_guanli.setChecked(true);
                tabHost.setCurrentTabByTag("manage");
                break;
            default:
                break;
        }

    }
}

