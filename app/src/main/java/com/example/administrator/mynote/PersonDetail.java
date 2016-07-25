package com.example.administrator.mynote;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.mynote.DAO.userDAO;
import com.example.administrator.mynote.Model.User;

/**
 * Created by Administrator on 2016/7/2.
 */
public class PersonDetail extends Activity {
    private TextView name;
    private TextView email;
    private User user;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);
        name=(TextView)findViewById(R.id.manager_person_name);
        email=(TextView)findViewById(R.id.manager_person_email);
        sp=getSharedPreferences("user",MODE_PRIVATE);
        String username=sp.getString("LOGINNAME","");
        userDAO userdao=new userDAO(PersonDetail.this);
        user=userdao.PersonDetail(username);
        name.setText(user.getUserName());
        email.setText(user.getEmail());
    }
}
