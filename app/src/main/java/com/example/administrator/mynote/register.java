package com.example.administrator.mynote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.mynote.DAO.HabitDAO;
import com.example.administrator.mynote.DAO.userDAO;
import com.example.administrator.mynote.Model.User;

/**
 * Created by Administrator on 2016/6/30.
 */
public class register extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private EditText agpassword;
    private EditText email;
    private Button register;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        account=(EditText)findViewById(R.id.register_account);
        password=(EditText)findViewById(R.id.register_password);
        agpassword=(EditText)findViewById(R.id.register_password_ag);
        email=(EditText)findViewById(R.id.register_email);
        register=(Button)findViewById(R.id.register_ok);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=new User();
                String r_account=account.getText().toString();
                String r_email=email.getText().toString();
                String r_password=password.getText().toString();
                String r_agpassword=agpassword.getText().toString();
                user.setUserName(r_account);
                user.setPassword(r_password);
                user.setEmail(r_email);
                userDAO userdao=new userDAO(register.this);
                if(r_password.equals(r_agpassword)){
                    if(userdao.register(user)){
                        HabitDAO habitDAO=new HabitDAO(register.this);
                        habitDAO.addHabit(r_account);
                        Toast.makeText(register.this,"注册成功！", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(register.this, "用户已存在！", Toast.LENGTH_LONG).show();
                    }
                }else{Toast.makeText(register.this,"密码不正确！",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
