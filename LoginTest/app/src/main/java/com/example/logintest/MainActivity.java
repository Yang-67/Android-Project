package com.example.logintest;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.weixin.MainWeixin;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sign);
//    }

    private Button btnLogin,btnsign;
    private EditText edtTel,edtPwd;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_rtlayout);
        initView();
        //注册
        btnsign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Sign.class);
                startActivity(intent);
            }
        });
        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel=edtTel.getText().toString();
                String pwd=edtPwd.getText().toString();
                if(tel.equals("120") && pwd.equals("120")){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, MainWeixin.class);
                    startActivity(intent);
                }else{
                    LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
                    View layout=inflater.inflate(R.layout.infono,null,false);
                    toast=new Toast(getApplicationContext());
                    toast.setView(layout);
                    toast.setGravity(Gravity.TOP,10,20);
                    toast.show();
                }
            }
        });
    }
    void initView(){
        btnsign = (Button) this.findViewById(R.id.btnsign);
        btnLogin= (Button) this.findViewById(R.id.btnLgn);
        edtTel = (EditText) this.findViewById(R.id.edtUserName);
        edtPwd = (EditText) this.findViewById(R.id.edtPassword);
    }

}