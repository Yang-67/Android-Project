package com.example.mousegame;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.MainActivity;
import com.example.SqLiteDB.DBOperation;

import java.io.File;
import java.util.List;

public class MyActivity extends AppCompatActivity {
    private TextView goOut;
    private EditText info;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        initView();
        DBTest();
        Intent intent0 = getIntent();
        String name = intent0.getStringExtra("username");
        System.out.println(name+"------");
        DBOperation dbOperation = new DBOperation(sqLiteDatabase);
        List<String> list = dbOperation.selectAll(name);
        String arr = "";
        for(String str:list){
            arr=arr+str+"\n";
        }
        info.setText(arr);
//        info.setText(dbOperation.selectAll(name).toString());
        //返回
        goOut.setOnClickListener(view -> {
            Intent intent=new Intent(MyActivity.this, IndexActivity.class);
            intent.putExtra("user",name);
            MyActivity.this.startActivity(intent);
        });
    }

    private void initView(){
        goOut=this.findViewById(R.id.cb_fh);//返回
        info=this.findViewById(R.id.et_info);//战绩
        info.setEnabled(false);//不可编辑
    }
    void DBTest(){
        File file=this.getDatabasePath("database").getParentFile();
        if(!file.exists()){
            file.mkdir();
        }
        sqLiteDatabase=openOrCreateDatabase(file.toString() + File.separator + "testDB.db",MODE_PRIVATE, null);
    }
}