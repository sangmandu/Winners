package com.example.winners_app.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Forgetpw1Request extends StringRequest {
    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://wns8363.dothome.co.kr/Forgetpw1.php";
    private Map<String, String> map;

    public Forgetpw1Request(String userID,String userAnswer, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userAnswer", userAnswer);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}