package com.example.winners_app.NavigationPages;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.winners_app.R;

import java.util.HashMap;
import java.util.Map;

public class NavAdmin extends AppCompatActivity {

    private EditText et_title;
    private EditText et_message;
    private Button push_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_admin);
        et_title = findViewById(R.id.et_title);
        et_message = findViewById(R.id.et_message);
        push_btn = findViewById(R.id.push_btn);



        push_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, "http://wns8363.dothome.co.kr/message/push_notification.php",
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
                        params.put("title", et_title.getText().toString());
                        params.put("body", et_message.getText().toString());

                        return params;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);

                Toast.makeText(getApplicationContext(), "푸시 알림 보냄", Toast.LENGTH_SHORT).show();

                // 키보드 숨기기
                hideSoftKeyboard(NavAdmin.this);
            }
        });
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
