package com.example.winners_app;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로딩화면(로딩시간, 다음화면 설정, 고정)
                Loading(5000, LoginActivity.class, getApplicationContext());

                //
                // 로딩 할 작업 (DB 연동)
                //
            }
        });
    }

    public void Loading(final int time, final Class cls,final Context context){
        // 로딩화면(다이얼로그) 설정
        final Dialog dialog = new Dialog(RegisterActivity.this, R.style.CustomDialogTheme);
        LinearLayout contentView = (LinearLayout) (RegisterActivity.this).getLayoutInflater().inflate(R.layout.activity_loading_popup, null);
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
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
                finish();
            }
        };
        thread.start();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }
}

