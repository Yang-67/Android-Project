package com.example.mousegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.MainActivity;

public class SignActivity extends AppCompatActivity {
    private TextView goOut;
    private Button btnSign;
    private EditText userName,userPwd1,userPwd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        initView();

        //确认注册
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }
        });

        //返回登录
        goOut.setOnClickListener(view -> {
            Intent intent=new Intent(SignActivity.this, MainActivity.class);
            SignActivity.this.startActivity(intent);
        });
    }

    void initView(){
        goOut=this.findViewById(R.id.cb_rm);//返回
        userName=this.findViewById(R.id.username);//账号
        userPwd1=this.findViewById(R.id.userpwd);//密码
        userPwd2=this.findViewById(R.id.userpwd2);//确认密码
        btnSign=this.findViewById(R.id.btn_sign);//注册
    }
}