package com.example.utils;

import android.os.Handler;
import android.os.Message;

public class StartThread implements Runnable {
    private static StartThread thread = new StartThread();
    private Handler handler;
    private boolean flag;
    private StartThread(){}

    public Handler getHandler() {
        return handler;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public static StartThread startThread(){
        return thread;
    }
    @Override
    public void run() {
        while (flag) {
            try{
                Thread.sleep(1000);
                Message message=Message.obtain();
                handler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
