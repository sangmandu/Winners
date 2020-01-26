package com.example.winners_app;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.winners_app.fragments.HomeFragment;
import com.example.winners_app.fragments.ActivityFragment;
import com.example.winners_app.fragments.BoardFragment;
import com.example.winners_app.fragments.GalleryFragment;
import com.example.winners_app.fragments.PeopleFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // BottomNavView와 NavDrawer 간의 연동 상수
    private int Menu_Pos;
    private DrawerLayout drawer;
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = HomeFragment.newInstance();
    private ActivityFragment activityFragment = ActivityFragment.newInstance();
    private BoardFragment boardFragment = BoardFragment.newInstance();
    private GalleryFragment galleryFragment = GalleryFragment.newInstance();
    private PeopleFragment peopleFragment = PeopleFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BotNavView NavDrawer UI연결 및 초기화
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView = findViewById(R.id.navigation);

        // BotNavView에 따른 NavDrawer 연동
        navigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.action_announces:
                    Menu_Pos = 0;
                    selectedFragment = homeFragment;
                    break;
                case R.id.action_account:
                    Menu_Pos = 1;
                    selectedFragment = activityFragment;
                    break;
                case R.id.action_sell:
                    Menu_Pos = 2;
                    selectedFragment = boardFragment;
                    break;
                case R.id.action_chat:
                    Menu_Pos = 3;
                    selectedFragment = galleryFragment;
                    break;
                case R.id.action_notifications:
                    Menu_Pos = 4;
                    selectedFragment = peopleFragment;
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (selectedFragment != null) {
                transaction.replace(R.id.f_container, selectedFragment);
                transaction.commit();
                navigationView.getMenu().getItem(Menu_Pos).setChecked(true);
            }
            return true;
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_container, homeFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return true;
    }

    // NavDrawer에 따른 BotNavView 연동
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawers();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_announces:
                bottomNavigationView.setSelectedItemId(R.id.action_announces);
                ft.replace(R.id.f_container, homeFragment);
                break;
            case R.id.nav_account:
                bottomNavigationView.setSelectedItemId(R.id.action_account);
                ft.replace(R.id.f_container, activityFragment);
                break;
            case R.id.nav_sell:
                bottomNavigationView.setSelectedItemId(R.id.action_sell);
                ft.replace(R.id.f_container, boardFragment);
                break;
            case R.id.nav_chat:
                bottomNavigationView.setSelectedItemId(R.id.action_chat);
                ft.replace(R.id.f_container, galleryFragment);
                break;
            case R.id.nav_notifications:
                bottomNavigationView.setSelectedItemId(R.id.action_notifications);
                ft.replace(R.id.f_container, peopleFragment);
                break;
        }

        ft.commit();
        return true;
    }
}
