package com.example.winners_app.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Forgetpw2Request extends StringRequest {
    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://wns8363.dothome.co.kr/Forgetpw2.php";
    private Map<String, String> map;

    public Forgetpw2Request(String userID, String NewPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", NewPassword);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}