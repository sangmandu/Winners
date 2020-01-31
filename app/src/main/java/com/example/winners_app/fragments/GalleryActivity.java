package com.example.winners_app.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.winners_app.R;
import com.example.winners_app.adapter.MyPagerAdapter;
import com.example.winners_app.models.Cat;
import com.example.winners_app.resources.Cats;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private ViewPager mMyViewPager;
    private String filter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mMyViewPager = findViewById(R.id.view_pager);
        filter = GalleryFragment.query;

        init();

    }

    private void init(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        Cat[] cats = Cats.getCats();
        for(Cat cat: cats){
            if(cat.getTitle().toLowerCase().contains(filter)){
                GalleryItemFragment fragment = GalleryItemFragment.getInstance(cat);
                fragments.add(fragment);
            }
        }
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        mMyViewPager.setAdapter(pagerAdapter);

        // 선택한 이미지에서 뷰어 시작
        if(getIntent().hasExtra("current_pos")){
            int current_pos = getIntent().getIntExtra("current_pos", 0);
            mMyViewPager.setCurrentItem(current_pos);
        }
    }
}
