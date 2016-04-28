package com.hc.home.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.hc.home.R;
import com.hc.home.entity.User;

import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView mPhoneView;
    private EditText etPsw;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        findViewById(R.id.btn_sign_in).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPhoneView.getText().toString() == null || etPsw.getText().toString() ==null){
                    Toast.makeText(LoginActivity.this,"账号或密码为空",Toast.LENGTH_LONG).show();
                }
                login();
            }
        });
        findViewById(R.id.btn_register).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

    }

    private void login() {
        showProgress(true);
        final User user = new User();
        user.setUsername(mPhoneView.getText().toString());
        user.setPassword(etPsw.getText().toString());
        user.login(LoginActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                showProgress(false);
                SharedPreferences setting = getSharedPreferences("headlines", MODE_PRIVATE);
                setting.edit().putBoolean("isLogin", true).commit();
                //保存登陆成功的用户信息
                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName",user.getUsername());
                //。。。。。。。。
                editor.commit();
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }

            @Override
            public void onFailure(int i, String s) {
                showProgress(false);
                Toast.makeText(LoginActivity.this,"登录失败，原因："+s,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView() {
        mPhoneView = (AutoCompleteTextView) findViewById(R.id.tv_phone);
        etPsw = (EditText) findViewById(R.id.tv_password);
        mLoginFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

