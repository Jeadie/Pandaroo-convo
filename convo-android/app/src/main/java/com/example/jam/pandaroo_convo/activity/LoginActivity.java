package com.example.jam.pandaroo_convo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jam.pandaroo_convo.R;

public class LoginActivity extends AppCompatActivity {
    private static boolean isExit=false;
    private AutoCompleteTextView login_un;
    private EditText login_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_un = findViewById(R.id.login_un);
        login_pwd = findViewById(R.id.login_pwd);
        login_pwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(id == R.id.login ||id == EditorInfo.IME_NULL){
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button sign_in_btn = findViewById(R.id.sign_in_btn);
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        Button reg_btn = findViewById(R.id.reg_btn);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegistActivity.class);
                //Just using a hashcode of the username - change to something from the database later
                intent.putExtra("EXTRA_USER", login_un.getText().hashCode());
                LoginActivity.this.startActivity(intent);
            }
        });
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit=false;
        }
    };
    private void attemptLogin() {

        // init error message is null
        login_un.setError(null);
        login_pwd.setError(null);

        // get input value
        String username = login_un.getText().toString();
        String password = login_pwd.getText().toString();

        boolean cancel = false;//check Illegal information
        View focusView = null;

        // check password
        if ( !TextUtils.isEmpty(password) && !isPasswordValid(password) ) {
            login_pwd.setError(getString(R.string.error_invalid_password));
            focusView = login_pwd;
            cancel = true;
        }

        //  check Username
        if ( TextUtils.isEmpty(username) ) {
            login_un.setError(getString(R.string.error_field_required));
            focusView = login_un;
            cancel = true;
        }

        if ( cancel ) {//Illegal information
            focusView.requestFocus();//Focus on View。
        } else {
            //登陆跳转逻辑
            //PersonDAO personDAO=new PersonDAO();
           // boolean sussess=personDAO.chechLogin(email,password);
           // if(sussess){  //信息合法
                //APPglobal.NAME=PersonDAO.findNameByUsername(email);//保存用户登录信息到全局变量中
               // APPglobal.USERNAME=email;
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
           // }
            //else {
                //Toast.makeText(LoginActivity.this, "UserName or Password is Wrong!", Toast.LENGTH_SHORT).show();
           // }
        }
    }
    private boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }
}
