package com.hc.home.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hc.home.R;
import com.hc.home.entity.User;

public class MyInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        TextView tvMyInfo = (TextView) findViewById(R.id.tv_show_my_info);

    }

    public User getUser(){
        User user = new User();
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        user.setUsername(sharedPreferences.getString("userName",""));
        return user;
    }

}
