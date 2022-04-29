package com.example.mousegame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.MainActivity;
import com.example.SqLiteDB.DBOperation;

import java.io.File;

public class SignActivity extends AppCompatActivity {
    private TextView goOut;
    private Button btnSign;
    private EditText userName,userPwd1,userPwd2;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        initView();
        DBTest();
        //确认注册
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(userName.getText().toString().trim()) ||
                        TextUtils.isEmpty(userPwd1.getText().toString().trim())  ||
                        TextUtils.isEmpty(userPwd2.getText().toString().trim())){
                    Toast.makeText(SignActivity.this,"请输入完整信息再注册!",Toast.LENGTH_SHORT).show();
                } else if(!userPwd1.getText().toString().trim().equals(userPwd2.getText().toString().trim())){
                    Toast.makeText(SignActivity.this,"两次密码需一致，请重新输入!",Toast.LENGTH_SHORT).show();
                } else{
                    String name = userName.getText().toString().trim();
                    String pwd = userPwd2.getText().toString().trim();
                    //查询账号是否重复
                    DBOperation dbOperation = new DBOperation(sqLiteDatabase);
                    if(!dbOperation.getIdByName(name)){
                        if(dbOperation.insertUser(name,pwd) != -1){
                            Toast.makeText(SignActivity.this,"插入成功!",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignActivity.this,"插入失败!",Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(SignActivity.this,"此账号已被注册!",Toast.LENGTH_SHORT).show();
                    }
                }
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
    void DBTest(){
        File file=this.getDatabasePath("database").getParentFile();
        if(!file.exists()){
            file.mkdir();
        }
        sqLiteDatabase=openOrCreateDatabase(file.toString() + File.separator + "testDB.db",MODE_PRIVATE, null);
    }
}