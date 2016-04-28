package com.hc.home.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.hc.home.R;
import com.hc.home.entity.User;

import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    private AutoCompleteTextView etPhone;
    private EditText etNickName,etArea,etPswSure, etPsw;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        findViewById(R.id.btn_register).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPsw.getText().toString().equals(etPswSure.getText().toString())){
                    register();
                }else{
                    etPswSure.setError("两次密码输入不一致".toString());
                }
            }
        });

    }

    private void register() {
        final User user = new User();
        showProgress(true);
        if(!TextUtils.isEmpty(etArea.getText().toString())){
            user.setAreaName(etArea.getText().toString());
        }
        user.setUsername(etPhone.getText().toString());
        user.setNickName(etNickName.getText().toString());
        user.setPassword(etPsw.getText().toString());
        user.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                showProgress(false);
                Toast.makeText(RegisterActivity.this, user.getUsername()+"注册成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                showProgress(false);
                Toast.makeText(RegisterActivity.this, user.getUsername()+"注册失败，原因："+s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        etPhone = (AutoCompleteTextView) findViewById(R.id.et_register_phone);
        etPsw = (EditText) findViewById(R.id.et_password);
        etPswSure = (EditText) findViewById(R.id.et_password_sure);
        etNickName = (EditText) findViewById(R.id.et_nickname);
        etArea = (EditText) findViewById(R.id.et_area);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.register_progress);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
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

