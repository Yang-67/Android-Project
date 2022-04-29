package com.example;

import android.database.sqlite.SQLiteDatabase;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.example.SqLiteDB.DBOperation;
import com.example.mousegame.IndexActivity;
import com.example.mousegame.R;
import com.example.mousegame.SignActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private EditText edtName,edtPwd;
    private Button btnLogin;
    private TextView btnSign;
    private CheckBox chkSave;
    private String userName,userPwd;
    private boolean isSave;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        read();
        DBTest();
        DBOperation dbOperation = new DBOperation(sqLiteDatabase);
        dbOperation.dBTest();//创建库、表

        btnLogin.setOnClickListener(view -> {
            String name = edtName.getText().toString().trim();
            String pwd = edtPwd.getText().toString().trim();
            boolean save= chkSave.isChecked();
            if(name.equals("") || pwd.equals("")){
                Toast.makeText(MainActivity.this,"请输入完整信息登录!",Toast.LENGTH_SHORT).show();
            } else{
                if(dbOperation.getPwdByUser(name,pwd)){
                    if(save){
                        write(name,pwd,save);
                    }
                    Intent intent=new Intent(MainActivity.this, IndexActivity.class);
                    System.out.println("登录者："+name);
                    intent.putExtra("user", name);//设置参数
                    MainActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,"账号或密码错误!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSign.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, SignActivity.class);
            MainActivity.this.startActivity(intent);
        });
    }
    void initView(){
        edtName=this.findViewById(R.id.et_username);//账号
        edtPwd=this.findViewById(R.id.et_userpwd);//密码
        chkSave=this.findViewById(R.id.cb_rm);//记住密码
        btnSign=this.findViewById(R.id.btn_register);//注册
        btnLogin=this.findViewById(R.id.btn_login);//登录
    }
    void read(){
        SharedPreferences sharedPreferences=getSharedPreferences("userconfig",MODE_PRIVATE);
        userName=sharedPreferences.getString("loginname","");
        userPwd=sharedPreferences.getString("loginpwd","");
        isSave=sharedPreferences.getBoolean("loginsave",false);
        if(isSave){
            edtName.setText(userName);
            edtPwd.setText(userPwd);
            chkSave.setChecked(isSave);
        }
    }
    void write(String userName,String userPwd,boolean isSave){
        SharedPreferences sharedPreferences=getSharedPreferences("userconfig",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("loginname",userName);
        editor.putString("loginpwd",userPwd);
        editor.putBoolean("loginsave",isSave);
        editor.apply();
    }
    void DBTest(){
        File file=this.getDatabasePath("database").getParentFile();
        if(!file.exists()){
            file.mkdir();
        }
        sqLiteDatabase=openOrCreateDatabase(file.toString() + File.separator + "testDB.db",MODE_PRIVATE, null);
    }
}