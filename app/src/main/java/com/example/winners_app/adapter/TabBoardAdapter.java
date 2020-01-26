package com.example.winners_app.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.winners_app.TabBoard.TabFree;
import com.example.winners_app.TabBoard.TabGame;
import com.example.winners_app.TabBoard.TabInfo;
import com.example.winners_app.TabBoard.TabNotice;

public class TabBoardAdapter extends FragmentPagerAdapter {

    public TabBoardAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TabNotice();
                break;
            case 1:
                fragment = new TabGame();
                break;
            case 2:
                fragment = new TabFree();
                break;
            case 3:
                fragment = new TabInfo();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
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
