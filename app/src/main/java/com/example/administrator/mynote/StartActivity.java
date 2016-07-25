package com.example.administrator.mynote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynote.DAO.userDAO;

/**
 * Created by Administrator on 2016/6/30.
 */
public class StartActivity extends Activity implements View.OnClickListener{
    private EditText account;
    private EditText password;
    private CheckBox rem;
    private CheckBox auto;
    private Button login;
    private TextView register;
    private TextView forget;
    private Button out;
    private SharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        account=(EditText)findViewById(R.id.login_account);
        password=(EditText)findViewById(R.id.login_password);
        rem=(CheckBox)findViewById(R.id.rem_checked);
        auto=(CheckBox)findViewById(R.id.auto_login);
        login=(Button)findViewById(R.id.login_ok);
        register=(TextView)findViewById(R.id.register);
        forget=(TextView)findViewById(R.id.forget_password);
        out=(Button)findViewById(R.id.start_out);
        login.setOnClickListener(this);
        out.setOnClickListener(this);
        register.setOnClickListener(this);
        forget.setOnClickListener(this);
        sp=getSharedPreferences("user",MODE_PRIVATE);
        if(sp.getBoolean("ISCHECK", false)) {
            rem.setChecked(true);
            account.setText(sp.getString("USER_NAME", ""));
            password.setText(sp.getString("PASS_WORD", ""));
            if(sp.getBoolean("AUTO_ISCHECK", false)) {
                auto.setChecked(true);
                Intent intent = new Intent(StartActivity.this,Home.class);
                startActivity(intent);
            }
        }
        auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (auto.isChecked()) {
                    System.out.println("自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                } else {
                    System.out.println("自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_ok:
                String l_account=account.getText().toString();
                String l_password=password.getText().toString();
                userDAO userdao=new userDAO(StartActivity.this);
                boolean Y=userdao.login(l_account,l_password);
                if(Y){
                    if(rem.isChecked()){
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("USER_NAME",l_account);
                        editor.putString("PASS_WORD",l_password);
                        editor.putBoolean("ISCHECK",true);
                        editor.commit();
                    }
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("LOGINNAME",l_account);
                    editor.putString("PASSWORD",l_password);
                    editor.commit();
                    Toast.makeText(StartActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(StartActivity.this,Home.class);
                    intent.setAction("action.refreshUserState");
                    sendBroadcast(intent);
                    intent.setAction("action.refreshHabit");
                    sendBroadcast(intent);
                    intent.setAction("action.refreshFriend");
                    sendBroadcast(intent);
                    startActivity(intent);
                    finish();
                }else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("LOGINNAME", "");
                    editor.putString("PASSWORD", "");
                    editor.putBoolean("STATE", false);
                    editor.commit();
                    Toast.makeText(StartActivity.this,"用户名或密码错误，请重新登录！",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.start_out:
                finish();
                break;
            case R.id.register:
                Intent intent=new Intent(this,register.class);
                startActivity(intent);
                break;
            case R.id.forget_password:
                Intent intent2=new Intent(this,ForgetPass.class);
                startActivity(intent2);
                break;
        }
    }
}
