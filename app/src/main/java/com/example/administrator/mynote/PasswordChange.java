package com.example.administrator.mynote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.mynote.DAO.userDAO;
import com.example.administrator.mynote.Model.User;

/**
 * Created by Administrator on 2016/7/2.
 */
public class PasswordChange extends Activity {
    private EditText oldpass;
    private EditText newpass;
    private Button ok;
    private User user;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordchange);
        oldpass=(EditText)findViewById(R.id.manager_oldpass);
        newpass=(EditText)findViewById(R.id.manager_newpass);
        ok=(Button)findViewById(R.id.manager_passchange_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp=getSharedPreferences("user",MODE_PRIVATE);
                String username=sp.getString("LOGINNAME","");
                userDAO userdao=new userDAO(PasswordChange.this);
                user=userdao.PersonDetail(username);
                String password=user.getPassword();
                String newpassword=newpass.getText().toString();
                String oldpassword=oldpass.getText().toString();
                if(password.equals(oldpassword)){
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putBoolean("ISCHECK",false);
                    editor.commit();
                    userdao.update(username,oldpassword,newpassword);
                    Toast.makeText(PasswordChange.this,"密码更改成功...",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(PasswordChange.this,StartActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    oldpass.setText("");
                    newpass.setText("");
                    Toast.makeText(PasswordChange.this,"原密码错误...",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
