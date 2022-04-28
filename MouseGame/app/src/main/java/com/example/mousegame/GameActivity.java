package com.example.mousegame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.utils.StartThread;

public class GameActivity extends Activity implements View.OnClickListener{
    private TextView tvCount,tvRemainTime;
    private Button btnReplay,btnPlay;
    private ImageView[] images=new ImageView[20];
    private int[] ids={R.id.img1,R.id.img2,R.id.img3,
            R.id.img4,R.id.img5,R.id.img6,R.id.img7,R.id.img8,
            R.id.img9,R.id.img10,R.id.img11,R.id.img12,R.id.img13,
            R.id.img14,R.id.img15,R.id.img16,R.id.img17,R.id.img18,
            R.id.img19,R.id.img20};
    boolean flag=true;
    int count=0;
    int oldID=0,newID=0;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            images[oldID].setBackgroundResource(R.drawable.crystalno1);
            newID= (int) (Math.random()*21);
            images[newID].setBackgroundResource(R.drawable.ds2);
            oldID=newID;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.mygame);
        initView();
        btnPlay.setOnClickListener(this);
        btnReplay.setOnClickListener(this);
        for(int i=0;i<images.length;i++){
            images[i].setOnClickListener(this);
        }
    }
    StartThread startThread = StartThread.startThread();
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPlay:
                new MyCount(60*1000,1000).start();
                startThread.setFlag(flag);
                startThread.setHandler(handler);
                Thread thread1=new Thread(startThread);
                thread1.start();
                break;
            case R.id.btnReplay:
                flag=true;
                new MyCount(60*1000,1000).start();
                startThread.setFlag(flag);
                startThread.setHandler(handler);
                Thread thread2=new Thread(startThread);
                thread2.start();
                break;
            default:
                if(images[oldID].getId()== view.getId() && flag){
                    count++;
                    tvCount.setText("击中数："+count);
                }
        }
    }
    private class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {//创建构造方法
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long l) {
            long hour=l/1000/3600;
            long minute=l/1000%3600/60;
            long second=l/1000%3600%60;
            tvRemainTime.setText("倒计时："+hour+":"+minute+":"+second);
        }
        @Override
        public void onFinish() {
            tvRemainTime.setText("游戏时间到！");
            flag=false;
            images[oldID].setBackgroundResource(R.drawable.crystalno1);
        }
    }
    void initView(){
        tvCount=this.findViewById(R.id.tvCount);
        tvRemainTime=this.findViewById(R.id.tvRemainTime);
        btnPlay=this.findViewById(R.id.btnPlay);
        btnReplay=this.findViewById(R.id.btnReplay);
        for(int i=0;i<images.length;i++){
            images[i]=this.findViewById(ids[i]);
        }
    }
}