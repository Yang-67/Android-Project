package com.example.SqLiteDB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBOperation extends AppCompatActivity {
    private SQLiteDatabase sqLiteDatabase;

    public DBOperation() {
//        File file=this.getDatabasePath("database").getParentFile();
//        if(!file.exists()){
//            file.mkdir();
//        }
//        this.sqLiteDatabase=openOrCreateDatabase(file.toString() + File.separator + "testDB.db",MODE_PRIVATE, null);
    }
    public DBOperation(SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase=sqLiteDatabase;
    }

    //建表建库
    public void dBTest(){
        String createSQL1="create table if not exists user(id integer primary key autoincrement,sname varchar(20),spwd varchar(20))";
        String createSQL2="create table if not exists score(id integer primary key autoincrement,sname varchar(20),timestamp datetime default current_timestamp,sscore integer)";
        sqLiteDatabase.execSQL(createSQL1);
        sqLiteDatabase.execSQL(createSQL2);
    }

    //根据账号查询是否重复
    public boolean getIdByName(String name){
        Cursor cursor = sqLiteDatabase.query("user",new String[]{"id,sname,spwd"},"sname=?",new String[]{name},null,null,null,null);
        return cursor.moveToNext();
    }
    //注册插入信息
    public long insertUser(String name,String pwd){
        ContentValues values=new ContentValues();
        values.put("sname",name);
        values.put("spwd",pwd);
        return sqLiteDatabase.insert("user",null,values);
    }
    //判断密码是否正确
    public boolean getPwdByUser(String name,String pwd){
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user where sname =? and spwd=?", new String[]{name,pwd});
        return cursor.moveToNext();
    }
    //插入游戏记录
    public long insertInfo(String sname,int sscore){
        ContentValues values=new ContentValues();
        values.put("sname",sname);
        values.put("sscore",sscore);
        return sqLiteDatabase.insert("score",null,values);
    }
    //根据账号查询游戏记录
    public List<String> selectAll(String name){
        List<String> list = new ArrayList<>();
        list.add("序号  "+"     姓名"+"                  时间"+"                      成绩");
        Cursor cursor = sqLiteDatabase.rawQuery("select * from score where sname=?",new String[]{name});
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String sname = cursor.getString(1);
            String timestamp = cursor.getString(2).substring(0,16);
            int sscore = cursor.getInt(3);
            list.add("  "+id+"           "+sname+"         "+timestamp+"           "+sscore);
        }
        return list;
    }
}