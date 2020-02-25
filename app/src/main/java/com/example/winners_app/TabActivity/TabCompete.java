package com.example.winners_app.TabActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winners_app.R;
import com.example.winners_app.adapter.CompAdapter;
import com.example.winners_app.models.Competition;
import com.example.winners_app.models.User;

import java.util.ArrayList;

public class TabCompete extends Fragment {
    private Button add_comp;
    private Button del_comp;
    private Button upd_comp;
    public static Boolean comp_delete;
    public static ArrayList<Competition> mComps = new ArrayList<>();
    public static ArrayList<User> mUsers = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> mPollTable = new ArrayList<>();

    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_compete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        comp_delete = false;

        add_comp = view.findViewById(R.id.add_comp);
        del_comp = view.findViewById(R.id.del_comp);
        upd_comp = view.findViewById(R.id.upd_comp);

        add_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> mPoll = new ArrayList<>();
                mPoll.clear();
                for(int i = 0; i < mUsers.size(); i++){
                    mPoll.add(0);
                }
                mPollTable.add(mPoll);
                startActivityForResult(new Intent(getContext(), CompSetting.class), 1);
            }
        });

        del_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comp_delete){
                    comp_delete = false;
                    initRecyclerView();
                }
                else{
                    comp_delete = true;
                    initRecyclerView();
                }
            }
        });

        upd_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRecyclerView();
                Toast.makeText(getActivity(), "대회목록 새로고침", Toast.LENGTH_SHORT).show();
            }
        });

        initUsers();

        initRecyclerView();
    }

    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        CompAdapter adapter = new CompAdapter(getContext(), mComps);
        recyclerView.setAdapter(adapter);
    }

    private void initUsers(){
        User user0 = new User("김재성","https://images.unsplash.com/photo-1548161955-6fbe48d1ef32?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=400&q=60");
        mUsers.add(user0);
        User user1 = new User("전상민","https://images.unsplash.com/photo-1507568237455-03228e5ddb7e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=400&q=60");
        mUsers.add(user1);
        User user2 = new User("장현준","https://images.unsplash.com/photo-1535123268188-3276dc455e77?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=400&q=60");
        mUsers.add(user2);
        User user3 = new User("김하나","https://images.unsplash.com/photo-1568316110152-a877d7f74f86?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=400&q=60");
        mUsers.add(user3);
        User user4 = new User("이두리","https://images.unsplash.com/photo-1448222993383-a2fff2914d27?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=60");
        mUsers.add(user4);
        User user5 = new User("박세이","https://images.unsplash.com/photo-1513451713350-dee890297c4a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=400&q=60");
        mUsers.add(user5);
        User user6 = new User("허네이","https://images.unsplash.com/photo-1495365043435-a7d3df121147?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=400&q=60");
        mUsers.add(user6);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            initRecyclerView();
        }
    }
}