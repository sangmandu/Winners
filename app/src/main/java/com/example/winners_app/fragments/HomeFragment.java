package com.example.winners_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.winners_app.adapter.ListViewAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import com.example.winners_app.R;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("   속보 : 위너스 어플 개발 연기");
        adapter.addItem("   공지 : 전국대회 입상자 다수");
        adapter.addItem("   공지 : 안드로이드 어려운 것으로 판명...");

        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.recentphoto1),
                ContextCompat.getDrawable(getActivity(), R.drawable.recentphoto2),
                ContextCompat.getDrawable(getActivity(), R.drawable.recentphoto3));

        // 세 번째 아이템 추가.
        adapter.addItem("활동신청", 0);
        adapter.addItem("현재 포인트 : ", 0);
        adapter.addItem("클린데이", 0);
    }
}



