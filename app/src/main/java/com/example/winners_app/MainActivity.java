package com.example.winners_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.winners_app.NavigationPages.NavAdmin;
import com.example.winners_app.fragments.HomeFragment;
import com.example.winners_app.fragments.ActivityFragment;
import com.example.winners_app.fragments.BoardFragment;
import com.example.winners_app.fragments.GalleryFragment;
import com.example.winners_app.fragments.PeopleFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private long backKeyPressedTime;
    private int Menu_Pos;
    private Toast backToast;

    // BottomNavView와 NavDrawer 간의 연동 상수
    private DrawerLayout drawer;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private NavigationView navigationView;

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
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView = findViewById(R.id.navigation);

        frameLayout = findViewById(R.id.f_container);

        // 핸드폰 토큰 서버로 저장
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Main", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.d("Main", token);
                        sendTokentoServer(token);
                        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                    }
                });

        // BotNavView에 따른 NavDrawer 연동
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.drawer_home:
                    Menu_Pos = 0;
                    selectedFragment = homeFragment;
                    break;
                case R.id.drawer_activity:
                    Menu_Pos = 1;
                    selectedFragment = activityFragment;
                    break;
                case R.id.drawer_board:
                    Menu_Pos = 2;
                    selectedFragment = boardFragment;
                    break;
                case R.id.drawer_gallery:
                    Menu_Pos = 3;
                    selectedFragment = galleryFragment;
                    break;
                case R.id.drawer_people:

                    Menu_Pos = 4;
                    selectedFragment = peopleFragment;
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (selectedFragment != null) {
                transaction.replace(R.id.f_container, selectedFragment);

                transaction.addToBackStack(null).commit();
                navigationView.getMenu().getItem(Menu_Pos).setChecked(true);
            }
            return true;
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_container, homeFragment);

        navigationView.getMenu().getItem(0).setChecked(true);
        transaction.commit();
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
            case R.id.nav_home:
                bottomNavigationView.setSelectedItemId(R.id.drawer_home);
                ft.replace(R.id.f_container, homeFragment);
                break;
            case R.id.nav_activity:
                bottomNavigationView.setSelectedItemId(R.id.drawer_activity);
                ft.replace(R.id.f_container, activityFragment);
                break;
            case R.id.nav_board:
                bottomNavigationView.setSelectedItemId(R.id.drawer_board);
                ft.replace(R.id.f_container, boardFragment);
                break;
            case R.id.nav_gallery:
                bottomNavigationView.setSelectedItemId(R.id.drawer_gallery);
                ft.replace(R.id.f_container, galleryFragment);
                break;
            case R.id.nav_people:
                bottomNavigationView.setSelectedItemId(R.id.drawer_people);
                ft.replace(R.id.f_container, peopleFragment);
                break;
            case R.id.nav_setting:
                Toast.makeText(this, "설정", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.nav_share:
                Toast.makeText(this, "공유", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.nav_star:
                Toast.makeText(this, "앱 평가하기", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.nav_info:
                Toast.makeText(this, "앱 정보", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.nav_admin:
                Toast.makeText(this, "관리자 모드", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, NavAdmin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
                return true;
        }

        ft.addToBackStack(null).commit();
        return true;
    }

    // 서버로 토큰 보내기 요청
    public void sendTokentoServer(final String token) {
        StringRequest request = new StringRequest(Request.Method.POST, "http://wns8363.dothome.co.kr/message/register.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Token", token);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.f_container);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (!(fragment instanceof OnBackPressedListener) || !((OnBackPressedListener) fragment).onBackPressed()){

            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                backToast = Toast.makeText(this, "\'종료\' 하려면 한번 더 눌러주세요", Toast.LENGTH_SHORT);
                backToast.show();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                finish();
                backToast.cancel();
            }
        }
    }
}
