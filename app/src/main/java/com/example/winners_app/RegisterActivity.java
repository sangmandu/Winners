package com.example.winners_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button back;
    Button reg;

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
                // 로딩 팝업창
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoadingPopup.class);
                startActivity(intent);

                // DB 연동...

                finish();
            }
        });
    }
}
