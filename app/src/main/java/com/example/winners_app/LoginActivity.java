package com.example.winners_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button register;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로딩화면(로딩시간, 다음화면 설정, 고정)
                Loading(3000, MainActivity.class, getApplicationContext());
            }
        });

        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }
        });
    }

    public void Loading(final int time, final Class cls,final Context context){
        // 로딩화면(다이얼로그) 설정
        final Dialog dialog = new Dialog(LoginActivity.this, R.style.CustomDialogTheme);
        LinearLayout contentView = (LinearLayout) (LoginActivity.this).getLayoutInflater().inflate(R.layout.activity_loading_popup, null);
        dialog.setContentView(contentView);

        ImageView image = (ImageView) contentView.findViewById(R.id.imageView);
        final AnimationDrawable animation = (AnimationDrawable) image.getDrawable();
        dialog.setCancelable(false);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                animation.start();
            }
        });
        dialog.show();

        // 작업&로딩 쓰레드 분리
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        // Wait given period of time or exit on touch
                        wait(time);
                    }
                } catch (InterruptedException ex) {
                }
                // 끝나면,
                dialog.dismiss();
                // 다음화면으로
                Intent intent = new Intent();
                intent.setClass(context.getApplicationContext(), cls);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                finish();
            }
        };
        thread.start();
    }
}
