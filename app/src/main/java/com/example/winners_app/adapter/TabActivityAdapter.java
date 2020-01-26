package com.example.winners_app.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.winners_app.TabActivity.TabActivity;
import com.example.winners_app.TabActivity.TabPoint;
import com.example.winners_app.TabActivity.TabCompete;

public class TabActivityAdapter extends FragmentPagerAdapter {

    public TabActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TabActivity();
                break;
            case 1:
                fragment = new TabPoint();
                break;
            case 2:
                fragment = new TabCompete();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "활동 신청";
            case 1:
                return "포인트 관리";
            case 2:
                return "대회 참가";
        }
        return null;
    }

}
