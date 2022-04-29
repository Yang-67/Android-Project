package com.example.mousegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.MainActivity;

public class IndexActivity extends AppCompatActivity {
    private TextView goOut;
    private Button btnJd,btnPt,btnKn,btnMyInfo;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        initView();

        Intent intent1=getIntent();
        name = intent1.getStringExtra("user");
        System.out.println("IndexActivity:"+name);
        //退出登录
        goOut.setOnClickListener(view -> {
            Intent intent=new Intent(IndexActivity.this, MainActivity.class);
            IndexActivity.this.startActivity(intent);
        });

        //简单模式
        btnJd.setOnClickListener(view -> {
            Intent intent=new Intent(IndexActivity.this, GameActivity.class);
            intent.putExtra("username", name);//设置参数
            intent.putExtra("message", "1");//设置参数
            IndexActivity.this.startActivity(intent);
        });

        //普通模式
        btnPt.setOnClickListener(view -> {
            Intent intent=new Intent(IndexActivity.this, GameActivity.class);
            intent.putExtra("username", name);//设置参数
            intent.putExtra("message", "2");//设置参数
            IndexActivity.this.startActivity(intent);
        });

        //困难模式
        btnKn.setOnClickListener(view -> {
            Intent intent=new Intent(IndexActivity.this, GameActivity.class);
            intent.putExtra("username", name);//设置参数
            intent.putExtra("message", "3");//设置参数
            IndexActivity.this.startActivity(intent);
        });

        //我的战绩
        btnMyInfo.setOnClickListener(view -> {
            Intent intent=new Intent(IndexActivity.this, MyActivity.class);
            intent.putExtra("username", name);//设置参数
            IndexActivity.this.startActivity(intent);
        });
    }

    void initView(){
        goOut=this.findViewById(R.id.cb_goOut);//退出登录
        btnJd=this.findViewById(R.id.btn_jd);//简单模式
        btnPt=this.findViewById(R.id.btn_pt);//普通模式
        btnKn=this.findViewById(R.id.btn_kn);//困难模式
        btnMyInfo=this.findViewById(R.id.btn_zj);//我的战绩
    }
}