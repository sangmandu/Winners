package com.example.winners_app.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.winners_app.R;
import com.example.winners_app.adapter.TabActivityAdapter;
import com.example.winners_app.adapter.TabViewPager;
import com.google.android.material.tabs.TabLayout;


public class ActivityFragment extends Fragment {

    private TabLayout tabLayout;
    private TabViewPager viewPager;
    private int point = 10;

    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = (TabLayout) getView().findViewById(R.id.tablayout_activity);
        viewPager = (TabViewPager) getView().findViewById(R.id.viewPager_activity);
        TabActivityAdapter pagerAdapter = new TabActivityAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setEnableSwipe(false);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
                // 아무것도 안함
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // 아무것도 안함
            }
        });
    }
}
