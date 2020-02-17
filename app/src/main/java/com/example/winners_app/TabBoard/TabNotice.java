package com.example.winners_app.TabBoard;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winners_app.NoticeRecycler;
import com.example.winners_app.NoticeRecyclerAdapter;
import com.example.winners_app.R;

import java.util.ArrayList;

public class TabNotice extends Fragment {

    RecyclerView mRecyclerView = null ;
    NoticeRecyclerAdapter mAdapter = null ;
    ArrayList<NoticeRecycler> mList = new ArrayList<NoticeRecycler>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_notice, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler_notice) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new NoticeRecyclerAdapter(mList) ;
        mRecyclerView.setAdapter(mAdapter) ;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())) ;

        // 아이템 추가.
        addItem(getResources().getDrawable(R.drawable.recentphoto1),
                "Box", "Account Box Black 36dp", "20200217") ;
        // 두 번째 아이템 추가.
        addItem(getResources().getDrawable(R.drawable.recentphoto1),
                "Circle", "Account Circle Black 36dp", "20200217") ;
        // 세 번째 아이템 추가.
        addItem(getResources().getDrawable(R.drawable.recentphoto1),
                "Ind", "Assignment Ind Black 36dp", "20200217") ;

        mAdapter.notifyDataSetChanged() ;
    }

    public void addItem(Drawable icon, String title, String contents, String created) {
        NoticeRecycler item = new NoticeRecycler();

        item.setIcon(icon);
        item.setTitle(title);
        item.setContents(contents);
        item.setCreated(created);

        mList.add(item);
    }
}