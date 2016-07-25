package com.example.administrator.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.mynote.DAO.userDAO;
import com.example.administrator.mynote.Model.User;

/**
 * Created by Administrator on 2016/7/4.
 */
public class ForgetPass extends Activity {
    private EditText account;
    private EditText email;
    private Button ok;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpass);
        account=(EditText)findViewById(R.id.forgetpass_name);
        email=(EditText)findViewById(R.id.forgetpass_email_1);
        ok=(Button)findViewById(R.id.forgetpass_confirm);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDAO userDAO=new userDAO(ForgetPass.this);
                String email_get=email.getText().toString();
                String name_get=account.getText().toString();
                user=userDAO.PersonDetail(name_get);
                if(email_get.equals(user.getEmail())){
                    Intent intent=new Intent(ForgetPass.this,SetNewPass.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ForgetPass.this,"邮箱不正确！",Toast.LENGTH_SHORT).show();
                    email.setText("");
                }
            }
        });

    }
}
