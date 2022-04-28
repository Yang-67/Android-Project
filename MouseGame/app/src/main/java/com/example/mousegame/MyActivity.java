package com.example.mousegame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.MainActivity;

public class MyActivity extends AppCompatActivity {
    private TextView goOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        initView();

        //返回
        goOut.setOnClickListener(view -> {
            Intent intent=new Intent(MyActivity.this, IndexActivity.class);
            MyActivity.this.startActivity(intent);
        });
    }

    private void initView(){
        goOut=this.findViewById(R.id.cb_fh);//返回
    }
}