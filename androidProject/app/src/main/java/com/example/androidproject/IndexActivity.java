package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class IndexActivity extends AppCompatActivity {
    private Button btnInsert,btnDel,btnUpdate,btnSql;
    private EditText edtNo,edtName,edtScore,edtSqlContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        initView();
        File file=this.getDatabasePath("database").getParentFile();
        if(file.exists()==false){
            file.mkdir();
        }
        final SQLiteDatabase sqLiteDatabase=openOrCreateDatabase(file.toString() + File.separator + "testDB.db",MODE_PRIVATE, null);
        String createSQL="create table student(sno text,sname text,sscore float)";
        if(file.exists()==false){
            sqLiteDatabase.execSQL(createSQL);
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sno=edtNo.getText().toString();
                String sname=edtName.getText().toString();
                float sscore=Float.parseFloat(edtScore.getText().toString());
                ContentValues values=new ContentValues();
                values.put("sno",sno);
                values.put("sname",sname);
                values.put("sscore",sscore);
                long m=sqLiteDatabase.insert("student",null,values);
                if(m>0){
                    Toast.makeText(IndexActivity.this,"插入成功",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whereno="sno=?";
                String sno=edtNo.getText().toString();
                String [] args=new String[]{sno};
                long m = sqLiteDatabase.delete("student",whereno,args);
                if(m>0){
                    Toast.makeText(IndexActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                String sname=edtName.getText().toString();
                float sscore=Float.parseFloat(edtScore.getText().toString());
                values.put("sscore",sscore);
                String whereno="sname=?";
                String[] args=new String[]{sname};
                long m =sqLiteDatabase.update("student",values,whereno,args);
                if(m>0){
                    Toast.makeText(IndexActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findno=edtNo.getText().toString();
                if(!findno.equals("")){
                    Cursor cursor=sqLiteDatabase.query("student",new String[]{"*"},"sno=?",new String[]{findno},null,null,null,null);
                    while(cursor.moveToNext()){
                        String sno=cursor.getString(0);
                        String sname=cursor.getString(1);
                        String sscore=cursor.getString(2);
                        String content=edtSqlContent.getText().toString()+"\n";
                        edtSqlContent.setText(content+sno+":"+sname+":"+sscore);
                    }
                }
                else{
                    Cursor cursor=sqLiteDatabase.query("student",new String[]{"*"},null,null,null,null,null);
                    while(cursor.moveToNext()){
                        String sno=cursor.getString(0);
                        String sname=cursor.getString(1);
                        String sscore=cursor.getString(2);
                        String content=edtSqlContent.getText().toString()+"\n";
                        edtSqlContent.setText(content+sno+":"+sname+":"+sscore);
                    }
                }
                Toast.makeText(IndexActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void initView(){
        btnInsert= (Button) this.findViewById(R.id.btnInsert);
        edtName= (EditText) this.findViewById(R.id.edtName);
        edtNo= (EditText) this.findViewById(R.id.edtNo);
        edtScore= (EditText) this.findViewById(R.id.edtScore);
        edtSqlContent= (EditText) this.findViewById(R.id.edtSqlContent);
        btnDel= (Button) this.findViewById(R.id.btnDel);
        btnUpdate= (Button) this.findViewById(R.id.btnUpdate);
        btnSql= (Button) this.findViewById(R.id.btnSql);
    }
}

