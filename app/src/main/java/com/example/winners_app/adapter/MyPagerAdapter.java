package com.example.winners_app.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

// FragmentStatePagerAdapter 동적(많거나 양을 모를때) = 메모리에 저장되지 않음 e.g. Viewer
// FragmentPagerAdapter 정적(많거나 양을 모를때) = 메모리에 저장됨 e.g. Tabs
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
