package com.example.newsnow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.example.adapter.MyAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyAdapter myapater;
    private TabLayout.Tab recommendTab,hotSpotTab,fuzhouTab,societyTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initview();
        FragmentManager fm=getSupportFragmentManager();
        myapater=new MyAdapter(fm);
        viewPager.setAdapter(myapater);
        tabLayout.setupWithViewPager(viewPager);
        recommendTab=tabLayout.getTabAt(0);
        fuzhouTab=tabLayout.getTabAt(2);
        hotSpotTab=tabLayout.getTabAt(1);
        societyTab=tabLayout.getTabAt(3);

    }

    void initview()
    {
        viewPager=(ViewPager) this.findViewById(R.id.viewPager);
        tabLayout=(TabLayout) this.findViewById(R.id.tabLayout);
    }
}
