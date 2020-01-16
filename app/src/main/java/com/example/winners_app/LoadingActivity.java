package com.example.winners_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class LoadingActivity extends AppCompatActivity {

    AnimationDrawable animation;
    private Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // 스플래쉬 에니메이션
        ImageView loading = (ImageView) findViewById(R.id.imageView);
        animation = (AnimationDrawable) loading.getDrawable();
        animation.start();

        // 3초 대기 (쓰레드 이용)
        splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        // Wait given period of time or exit on touch
                        wait(3000);
                    }
                } catch (InterruptedException ex) {
                }
                finish();
                // 다음 액티비티 실행
                Intent intent = new Intent();
                intent.setClass(LoadingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
        splashThread.start();
    }
}
