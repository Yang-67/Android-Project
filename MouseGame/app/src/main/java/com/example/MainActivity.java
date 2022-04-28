package com.example;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.example.mousegame.IndexActivity;
import com.example.mousegame.R;
import com.example.mousegame.SignActivity;

public class MainActivity extends AppCompatActivity {
    private EditText edtName,edtPwd;
    private Button btnLogin;
    private TextView btnSign;
    private CheckBox chkSave;
    private String userName,userPwd;
    private boolean isSave,isAuto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, IndexActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        btnSign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SignActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
//        read();
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean save=chkSave.isChecked()?true:false;
//                boolean auto=chkLogin.isChecked()?true:false;
//                String username=edtName.getText().toString();
//                String userpwd=edtPwd.getText().toString();
//                if(save){
//                    write(username,userpwd,save,auto);
//                    Toast.makeText(MainActivity.this,"登录信息已经保存",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
//                }
//                Intent intent=new Intent(MainActivity.this,IndexActivity.class);
//                MainActivity.this.startActivity(intent);
//            }
//        });
    }
    void initView(){
        edtName=this.findViewById(R.id.et_username);//账号
        edtPwd=this.findViewById(R.id.et_userpwd);//密码
        chkSave=this.findViewById(R.id.cb_rm);//记住密码
        btnSign=this.findViewById(R.id.btn_register);//注册
        btnLogin=this.findViewById(R.id.btn_login);//登录
    }
//    void read(){
//        SharedPreferences sharedPreferences=getSharedPreferences("userconfig",MODE_PRIVATE);
//        userName=sharedPreferences.getString("loginname","");
//        userPwd=sharedPreferences.getString("loginpwd","");
//        isSave=sharedPreferences.getBoolean("loginsave",false);
//        isAuto=sharedPreferences.getBoolean("loginauto",false);
//        if(isSave){
//            edtName.setText(userName);
//            edtPwd.setText(userPwd);
//        }
//        if(isAuto){
//            Intent intent=new Intent(MainActivity.this,IndexActivity.class);
//            MainActivity.this.startActivity(intent);
//        }
//    }
//    void write(String userName,String userPwd,boolean isSave,boolean isAuto){
//        SharedPreferences sharedPreferences=getSharedPreferences("userconfig",MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString("loginname",userName);
//        editor.putString("loginpwd",userPwd);
//        editor.putBoolean("loginsave",isSave);
//        editor.putBoolean("loginauto",isAuto);
//        editor.commit();
//    }
}