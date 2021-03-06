package com.example.mousegame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.SqLiteDB.DBOperation;

import java.io.File;

public class GameActivity extends Activity implements View.OnClickListener{
    private TextView tvCount,tvRemainTime,goOut;
    private Button btnPlay;
    private ImageView[] images=new ImageView[20];
    private int[] ids={R.id.img1,R.id.img2,R.id.img3,
            R.id.img4,R.id.img5,R.id.img6,R.id.img7,R.id.img8,
            R.id.img9,R.id.img10,R.id.img11,R.id.img12,R.id.img13,
            R.id.img14,R.id.img15,R.id.img16,R.id.img17,R.id.img18,
            R.id.img19,R.id.img20};
    boolean flag=true;
    int count=0;
    int oldID=0,newID=0;
    int time=1000;
    String name;
    private SQLiteDatabase sqLiteDatabase;
    private DBOperation dbOperation;
    Thread thread1;
    int startFlag = 0;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            images[oldID].setBackgroundResource(R.drawable.crystalno1);
            newID= (int) (Math.random()*20);
            images[newID].setBackgroundResource(R.drawable.ds2);
            oldID=newID;
        }
    };

    Runnable runnable= () -> {
        while (flag) {
            try{
                Thread.sleep(time);
                Message message=Message.obtain();
                handler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.mygame);
        initView();
        btnPlay.setOnClickListener(this);
        for(int i=0;i<images.length;i++){
            images[i].setOnClickListener(this);
        }
        //???????????????
        DBTest();
        dbOperation=new DBOperation(sqLiteDatabase);

        //????????????
        Intent intent=getIntent();
        int grage= Integer.parseInt(intent.getStringExtra("message"));
        name = intent.getStringExtra("username");
        System.out.println("GameActivity"+grage+","+name);
        if(grage == 1){
            time=1500;
        }
        if(grage == 2){
            time=1000;
        }
        if(grage == 3){
            time=500;
        }
        //????????????
        goOut.setOnClickListener(view -> {
            Intent intent2=new Intent(GameActivity.this, IndexActivity.class);
            intent2.putExtra("user",name);
            GameActivity.this.startActivity(intent2);
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                if(startFlag==0){
                    new MyCount(60*1000,1000).start();
                    thread1=new Thread(runnable);
                    thread1.start();
                    startFlag=1;
                }
                break;
            default:
                if(images[oldID].getId() == view.getId() && flag){
                    count++;
                    tvCount.setText("????????????"+count);
                }
        }
    }
    private class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {//??????????????????
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long l) {
            long hour=l/1000/3600;
            long minute=l/1000%3600/60;
            long second=l/1000%3600%60;
            tvRemainTime.setText("????????????"+hour+":"+minute+":"+second);
        }
        @Override
        public void onFinish() {
            dbOperation.insertInfo(name,count);//??????????????????
            tvRemainTime.setText("??????????????????");
            flag=false;
            images[oldID].setBackgroundResource(R.drawable.crystalno1);
        }
    }
    void initView(){
        goOut=this.findViewById(R.id.cb_fh);//??????
        tvCount=this.findViewById(R.id.tvCount);//?????????
        tvRemainTime=this.findViewById(R.id.tvRemainTime);//?????????
        btnPlay=this.findViewById(R.id.btn_start);//??????
        for(int i=0;i<images.length;i++){
            images[i]=this.findViewById(ids[i]);
        }
    }
    void DBTest(){
        File file=this.getDatabasePath("database").getParentFile();
        if(!file.exists()){
            file.mkdir();
        }
        sqLiteDatabase=openOrCreateDatabase(file.toString() + File.separator + "testDB.db",MODE_PRIVATE, null);
    }
}