package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.tabFragment.FuZhouFragment;
import com.example.tabFragment.HotSpotFragment;
import com.example.tabFragment.RecommentFragment;
import com.example.tabFragment.SocietyFragment;

public class MyAdapter extends FragmentPagerAdapter {
    private String[] titles={"推荐","热点","福州","社会"};

    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:return new RecommentFragment();
            case 1:return new HotSpotFragment();
            case 2:return new FuZhouFragment();
            case 3:return new SocietyFragment();
        }
        return new RecommentFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
