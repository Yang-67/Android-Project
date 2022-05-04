package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtName,edtPwd;
    private Button btnLogin;
    private CheckBox chkSave,chkLogin;
    private String userName,userPwd;
    private boolean isSave,isAuto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        read();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean save=chkSave.isChecked()?true:false;
                boolean auto=chkLogin.isChecked()?true:false;
                String username=edtName.getText().toString();
                String userpwd=edtPwd.getText().toString();
                if(save){
                    write(username,userpwd,save,auto);
                    Toast.makeText(MainActivity.this,"登录信息已经保存",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }
                Intent intent=new Intent(MainActivity.this,IndexActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
    void initView(){
        edtName=this.findViewById(R.id.edtName);
        edtPwd=this.findViewById(R.id.edtPwd);
        btnLogin=this.findViewById(R.id.btnLogin);
        chkLogin=this.findViewById(R.id.chkLogin);//自动保存
        chkSave=this.findViewById(R.id.chkSave);
    }
    void read(){
        SharedPreferences sharedPreferences=getSharedPreferences("userconfig",MODE_PRIVATE);
        userName=sharedPreferences.getString("loginname","");
        userPwd=sharedPreferences.getString("loginpwd","");
        isSave=sharedPreferences.getBoolean("loginsave",false);
        isAuto=sharedPreferences.getBoolean("loginauto",false);
        if(isSave){
            edtName.setText(userName);
            edtPwd.setText(userPwd);
        }
        if(isAuto){
            Intent intent=new Intent(MainActivity.this,IndexActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }
    void write(String userName,String userPwd,boolean isSave,boolean isAuto){
        SharedPreferences sharedPreferences=getSharedPreferences("userconfig",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("loginname",userName);
        editor.putString("loginpwd",userPwd);
        editor.putBoolean("loginsave",isSave);
        editor.putBoolean("loginauto",isAuto);
        editor.commit();
    }
}
