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

/**
 * Created by Administrator on 2016/7/4.
 */
public class SetNewPass extends Activity {
    private EditText code1;
    private EditText code2;
    private Button ok;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setnewpass);
        code1=(EditText)findViewById(R.id.setpass_code1);
        code2=(EditText)findViewById(R.id.setpass_code2);
        ok=(Button)findViewById(R.id.setpass_confirm);
        sp=getSharedPreferences("user",MODE_PRIVATE);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setcode1=code1.getText().toString();
                String setcode2=code2.getText().toString();
                String username=sp.getString("LOGINNAME","");
                String oldpass=sp.getString("PASSWORD","");
                if(setcode1.equals(setcode2)){
                    userDAO userDAO=new userDAO(SetNewPass.this);
                    userDAO.update(username,oldpass,setcode1);
                    Intent intent=new Intent(SetNewPass.this,StartActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putBoolean("ISCHECK",false);
                    editor.commit();
                    finish();
                }else {
                    Toast.makeText(SetNewPass.this,"密码不正确！",Toast.LENGTH_SHORT).show();
                    code1.setText("");
                    code2.setText("");
                }
            }
        });
    }
}
