package com.example.winners_app.TabActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winners_app.R;
import com.example.winners_app.adapter.UserAdapter;
import com.example.winners_app.models.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.winners_app.TabActivity.TabCompete.mComps;
import static com.example.winners_app.TabActivity.TabCompete.mPollTable;
import static com.example.winners_app.TabActivity.TabCompete.mUsers;

public class CompActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView comp_name;
    private TextView comp_date;
    private TextView comp_web;
    private String webpage;
    private TextView comp_notice;
    private LinearLayout layout_notice;
    private GoogleMap mMap;
    private LatLng latlng;
    private String comp_addr;
    private String comp_loc;
    private int userID = 0;

    private TextView yes_btn;
    private TextView no_btn;
    private TextView idk_btn;
    private TextView yes_num;
    private TextView no_num;
    private TextView idk_num;
    private TextView comp_mystat;
    private RecyclerView yes_list;
    private RecyclerView no_list;
    private RecyclerView idk_list;
    private boolean yes_bool;
    private boolean no_bool;
    private boolean idk_bool;
    private Button yes_i_can;
    private Button no_i_cant;
    private ArrayList<Integer> polls = new ArrayList<>();
    private ArrayList<User> yes_users = new ArrayList<>();
    private ArrayList<User> no_users = new ArrayList<>();
    private ArrayList<User> idk_users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_detail);

        Intent intent = getIntent();
        final int current_pos = intent.getIntExtra("clicked", 0);

        comp_name = findViewById(R.id.comp_name);
        comp_name.setText(mComps.get(current_pos).getName());
        comp_date = findViewById(R.id.comp_date);
        comp_date.setText(String.format(Locale.KOREA, "%d년 %d월 %d일 %d:%d",
                mComps.get(current_pos).getYear(),
                mComps.get(current_pos).getMonth(),
                mComps.get(current_pos).getDay(),
                mComps.get(current_pos).getHr(),
                mComps.get(current_pos).getMin()));

        comp_web = findViewById(R.id.comp_web);
        webpage = mComps.get(current_pos).getWeb();
        if (webpage.equals("")){
            comp_web.setVisibility(View.GONE);
        }
        else{
            comp_web.setText(mComps.get(current_pos).getWeb());
        }

        comp_loc = mComps.get(current_pos).getLoc_name();
        comp_addr = mComps.get(current_pos).getLocation();
        latlng = getLocationFromAddress(CompActivity.this, comp_addr+" "+comp_loc);

        layout_notice = findViewById(R.id.notice);
        comp_notice = findViewById(R.id.comp_notice);
        if (mComps.get(current_pos).getNote().equals("")){
            layout_notice.setVisibility(View.GONE);
        }
        else{
            comp_notice.setText(mComps.get(current_pos).getNote());
        }

        yes_i_can = findViewById(R.id.yes_i_can);
        no_i_cant = findViewById(R.id.no_i_cant);

        comp_mystat = findViewById(R.id.comp_mystat);

        yes_btn = findViewById(R.id.yes_btn);
        no_btn = findViewById(R.id.no_btn);
        idk_btn = findViewById(R.id.idk_btn);

        yes_num = findViewById(R.id.yes_num);
        no_num = findViewById(R.id.no_num);
        idk_num = findViewById(R.id.idk_num);

        yes_list = findViewById(R.id.yes_list);
        yes_list.setNestedScrollingEnabled(false);
        no_list = findViewById(R.id.no_list);
        no_list.setNestedScrollingEnabled(false);
        idk_list = findViewById(R.id.idk_list);
        idk_list.setNestedScrollingEnabled(false);

        yes_bool = false;
        no_bool = false;
        idk_bool = false;

        polls = mPollTable.get(current_pos);



        setUserStatus();

        initList();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        yes_i_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comp_mystat.setText("참가");
                comp_mystat.setTextColor(getResources().getColor(R.color.blue));
                polls.set(userID, 2);
                mPollTable.set(current_pos, polls);
                initList();

            }
        });
        no_i_cant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comp_mystat.setText("불참");
                comp_mystat.setTextColor(getResources().getColor(R.color.red));
                polls.set(userID, 1);
                mPollTable.set(current_pos, polls);
                initList();
            }
        });

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!yes_bool){
                    yes_bool = true;
                    yes_btn.setText("축소");
                    yes_list.setVisibility(View.VISIBLE);
                }
                else{
                    yes_bool = false;
                    yes_btn.setText("확장");
                    yes_list.setVisibility(View.GONE);
                }
            }
        });
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!no_bool){
                    no_bool = true;
                    no_btn.setText("축소");
                    no_list.setVisibility(View.VISIBLE);
                }
                else{
                    no_bool = false;
                    no_btn.setText("확장");
                    no_list.setVisibility(View.GONE);
                }
            }
        });
        idk_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!idk_bool){
                    idk_bool = true;
                    idk_btn.setText("축소");
                    idk_list.setVisibility(View.VISIBLE);
                }
                else{
                    idk_bool = false;
                    idk_btn.setText("확장");
                    idk_list.setVisibility(View.GONE);
                }
            }
        });
    }

    public void setUserStatus(){
        if(polls.get(userID) == 2){
            comp_mystat.setText("참가");
            comp_mystat.setTextColor(getResources().getColor(R.color.blue));
        }
        else if(polls.get(userID) == 1){
            comp_mystat.setText("불참");
            comp_mystat.setTextColor(getResources().getColor(R.color.red));
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if(latlng != null) {
            mMap = googleMap;
            googleMap.getUiSettings().setScrollGesturesEnabled(false);
            googleMap.getUiSettings().setZoomGesturesEnabled(false);

            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latlng)
                    .title(comp_loc)
                    .snippet(comp_addr));
            marker.showInfoWindow();

            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
        else{
            LinearLayout map_box = findViewById(R.id.map_box);
            map_box.setVisibility(View.GONE);
        }
    }

    private void initList(){
        yes_users.clear();
        no_users.clear();
        idk_users.clear();

        int index = 0;
        for(Integer poll: polls){
            if(poll == 2){
                yes_users.add(mUsers.get(index));
            }
            else if(poll == 1){
                no_users.add(mUsers.get(index));
            }
            else if(poll == 0){
                idk_users.add(mUsers.get(index));
            }
            index++;
        }

        initYesList();
        initNoList();
        initIdkList();
    }

    private void initYesList(){
        yes_num.setText(""+yes_users.size());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.yes_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        UserAdapter adapter = new UserAdapter(getApplicationContext(), yes_users);
        recyclerView.setAdapter(adapter);
    }

    private void initNoList(){
        no_num.setText(""+no_users.size());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.no_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        UserAdapter adapter = new UserAdapter(getApplicationContext(), no_users);
        recyclerView.setAdapter(adapter);
    }

    private void initIdkList(){
        idk_num.setText(""+idk_users.size());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.idk_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        UserAdapter adapter = new UserAdapter(getApplicationContext(), idk_users);
        recyclerView.setAdapter(adapter);
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address.size() == 0) {
                return null;
            }
            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return p1;
    }
}
