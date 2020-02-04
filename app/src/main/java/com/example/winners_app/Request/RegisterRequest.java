package com.example.winners_app.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://wns8363.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userName,String userPassword, int userAge, String userMajor, String userAnswer, Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userName", userName);
        map.put("userPassword",userPassword);
        map.put("userAge", userAge + ""); // 인트형이기 때문에 문자열로 눈속임
        map.put("userMajor", userMajor);
        map.put("userAnswer", userAnswer);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
