package com.example.winners_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.winners_app.fragments.login_reg_pw.ForgetPw1;

public class ForgetPwActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpw);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ForgetPw1 forgetpw1 = new ForgetPw1();
        transaction.add(R.id.frame_forgetpw, forgetpw1);
        transaction.commit();

    }
}
