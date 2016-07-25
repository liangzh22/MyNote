package com.example.administrator.mynote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/30.
 */
public class ManageActivity extends Activity implements View.OnClickListener {
    private Button logoutBtn;
    private TextView username;
    private TableRow person_detail;
    private TableRow pass_change;
    private TableRow set;
    private String loginName,password;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guanli);
        logoutBtn=(Button)findViewById(R.id.manager_logout);
        username=(TextView)findViewById(R.id.manager_show_username);
        person_detail=(TableRow)findViewById(R.id.manager_detail);
        pass_change=(TableRow)findViewById(R.id.manager_pass_change);
        set=(TableRow)findViewById(R.id.manager_set);
        sp=getSharedPreferences("user",MODE_PRIVATE);
        loginName=sp.getString("LOGINNAME","");
        password=sp.getString("PASSWORD","");
        username.setText(loginName);

        logoutBtn.setOnClickListener(this);
        person_detail.setOnClickListener(this);
        pass_change.setOnClickListener(this);
        set.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.manager_detail:
                Intent intent1=new Intent(ManageActivity.this, PersonDetail.class);
                startActivity(intent1);
                break;
            case R.id.manager_pass_change:
                Intent intent2=new Intent(ManageActivity.this, PasswordChange.class);
                startActivity(intent2);
                break;
            case R.id.manager_set:
                Toast.makeText(ManageActivity.this,"没做...",Toast.LENGTH_SHORT).show();
                break;
            case R.id.manager_logout:
                SharedPreferences.Editor editor=sp.edit();
                editor.putBoolean("ISCHECK",false);
                editor.putString("TodayTime","");
                editor.commit();
                Intent intent3=new Intent(ManageActivity.this,StartActivity.class);
                startActivity(intent3);
                finish();
                break;

        }
    }

}
