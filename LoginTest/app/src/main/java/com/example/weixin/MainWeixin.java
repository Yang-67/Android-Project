package com.example.weixin;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.example.logintest.R;

public class MainWeixin extends Activity implements View.OnClickListener {
    private LinearLayout mTabWeiXin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabContacts;
    private LinearLayout mTabSettings;

    private ImageButton mImgWeiXin;
    private ImageButton mImgFrd;
    private ImageButton mImgContacts;
    private ImageButton mImgSettings;

    private Fragment mTab01=new weixinFragment();
    private Fragment mTab02=new frdFragment();
    private Fragment mTab03=new contactsFragment();
    private Fragment mTab04=new settingsFragment();

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.infoyes);

        initView();
        initFragment();
        initEvent();
        setSelect(0);  //初始值设置为0，默认weixinFragment为首页


    }

    /**
     * 事件启动函数
     * 全屏监听太耗内存
     * 限制只监听4个LinearLayout
     */
    private void initEvent(){
        mTabWeiXin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabContacts.setOnClickListener(this);
        mTabSettings.setOnClickListener(this);

    }
    private void initFragment(){
        fm=getFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.add(R.id.id_content,mTab01);
        transaction.add(R.id.id_content,mTab02);
        transaction.add(R.id.id_content,mTab03);
        transaction.add(R.id.id_content,mTab04);
        transaction.commit();

    }
    private void initView(){
        mTabWeiXin=(LinearLayout)findViewById(R.id.id_tab_WeChat);
        mTabFrd=(LinearLayout)findViewById(R.id.id_tab_Frd);
        mTabContacts=(LinearLayout)findViewById(R.id.id_tab_Contacts);
        mTabSettings=(LinearLayout)findViewById(R.id.id_tab_Settings);

        mImgWeiXin=(ImageButton)findViewById(R.id.img_WeChat);
        mImgFrd=(ImageButton)findViewById(R.id.img_Frd);
        mImgContacts=(ImageButton)findViewById(R.id.img_Contacts);
        mImgSettings=(ImageButton)findViewById(R.id.img_Settings);
    }

    private void setSelect(int i){
        FragmentTransaction transaction=fm.beginTransaction();
        hideFragment(transaction);
        //把图片设置为亮的
        //设置内容区域
        switch (i){
            case 0:
                Log.d("setSelect","1");
                transaction.show(mTab01);
                mImgWeiXin.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                transaction.show(mTab02);
                mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                transaction.show(mTab03);
                mImgContacts.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                transaction.show(mTab04);
                mImgSettings.setImageResource(R.drawable.tab_settings_pressed);
                break;
            default:break;
        }
        transaction.commit();
    }


    /**
     * 将4个fragment先全部隐藏
     */
    private void hideFragment(FragmentTransaction transaction){
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);

    }

    public void onClick(View v){
        Log.d("onClick","2");
        resetImgs();
        switch (v.getId()){
            case R.id.id_tab_WeChat:
                setSelect(0);
                break;
            case R.id.id_tab_Frd:
                setSelect(1);
                break;
            case R.id.id_tab_Contacts:
                setSelect(2);
                break;
            case R.id.id_tab_Settings:
                setSelect(3);
                break;
            default:
                break;
        }

    }

    /**
     * 切换图片至暗色
     */
    public void resetImgs(){
        mImgWeiXin.setImageResource(R.drawable.tab_weixin_normal);
        mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
        mImgContacts.setImageResource(R.drawable.tab_address_normal);
        mImgSettings.setImageResource(R.drawable.tab_settings_normal);

    }
}
