package com.example.winners_app.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetImageRequest extends StringRequest {
    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://wns8363.dothome.co.kr/getimage.php";
    private Map<String, String> map;

    public GetImageRequest(Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
