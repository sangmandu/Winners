package com.example.winners_app.TabActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winners_app.PointRankItem;
import com.example.winners_app.R;
import com.example.winners_app.adapter.PointRankAdapter;

import java.util.ArrayList;

public class TabPoint extends Fragment {

    private RecyclerView mRecyclerView = null;
    private PointRankAdapter mAdapter = null;
    ArrayList<PointRankItem> mList = new ArrayList<PointRankItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_point, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_pointrank);

        mAdapter = new PointRankAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        addItem(getResources().getDrawable(R.drawable.ic_people), "전상민", "300");
        addItem(getResources().getDrawable(R.drawable.ic_people), "김재성", "200");
        addItem(getResources().getDrawable(R.drawable.ic_people), "장현준", "10000");

        mAdapter.notifyDataSetChanged() ;
    }

    public void addItem(Drawable icon, String name, String point){
        PointRankItem item = new PointRankItem();

        item.setIcon(icon);
        item.setName(name);
        item.setPoint(point);

        mList.add(item);
    }
}