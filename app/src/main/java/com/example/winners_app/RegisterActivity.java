package com.example.winners_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class RegisterActivity extends AppCompatActivity {

    Button back;
    Button reg;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        back = (Button)findViewById(R.id.back);
        reg = (Button)findViewById(R.id.register);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(RegisterActivity.this);
                LinearLayout contentView = (LinearLayout) (RegisterActivity.this).getLayoutInflater().inflate(R.layout.activity_loading_popup, null);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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

                // DB 연동 시간 설정 (일단 5초)
                thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            synchronized (this) {
                                // Wait given period of time or exit on touch
                                wait(5000);
                            }
                        } catch (InterruptedException ex) {
                        }
                        // 끝나면,
                        finish();
                        // 로그인 화면으로 돌아가기
                        Intent intent = new Intent();
                        intent.setClass(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                };
                thread.start();

                //
                // DB 연동...
                //
            }
        });
    }
}

