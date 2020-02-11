package com.example.winners_app.resources;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.winners_app.LoginActivity;
import com.example.winners_app.MainActivity;
import com.example.winners_app.R;
import com.example.winners_app.Request.GetImageRequest;
import com.example.winners_app.Request.LoginRequest;
import com.example.winners_app.models.Cat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Cats {

    public static Cat[] getCats(Activity act){ return Cat_Collection;};

    public static final Cat cat_001 = new Cat("kUuSzz795VXRpzA6", "http://wns8363.dothome.co.kr/Cats/cat1.jpg");
    public static final Cat cat_002 = new Cat("3fZdPI9yvM57jt0x", "http://wns8363.dothome.co.kr/Cats/cat2.jpg");
    public static final Cat cat_003 = new Cat("t285PlXkB3V1ma82", "http://wns8363.dothome.co.kr/Cats/cat3.jpg");
    public static final Cat cat_004 = new Cat("2No03fTP5vl1AkTb", "http://wns8363.dothome.co.kr/Cats/cat4.jpg");
    public static final Cat cat_005 = new Cat("89Of7hsvBr5X34Bb", "http://wns8363.dothome.co.kr/Cats/cat5.jpg");
    public static final Cat cat_006 = new Cat("Q4emOb8Leea6fotM", "http://wns8363.dothome.co.kr/Cats/cat6.jpg");
    public static final Cat cat_007 = new Cat("nJ7UN7wr394z9Zb4", "http://wns8363.dothome.co.kr/Cats/cat7.jpg");
    public static final Cat cat_008 = new Cat("70RjWsW7su1ML13f", "http://wns8363.dothome.co.kr/Cats/cat8.jpg");
    public static final Cat cat_009 = new Cat("9K4CAexSSc7ur1Ty", "http://wns8363.dothome.co.kr/Cats/cat9.jpg");
    public static final Cat cat_010 = new Cat("1l1X4U7Q8z6PPRIT", "http://wns8363.dothome.co.kr/Cats/cat10.jpg");
    public static final Cat cat_011 = new Cat("i6Ba4SXL8CY3xDMc", "http://wns8363.dothome.co.kr/Cats/cat11.jpg");
    public static final Cat cat_012 = new Cat("h2ux34KWuSObb06m", "http://wns8363.dothome.co.kr/Cats/cat12.jpg");
    public static final Cat cat_013 = new Cat("CCT89H1mP16D6Jop", "http://wns8363.dothome.co.kr/Cats/cat13.jpg");
    public static final Cat cat_014 = new Cat("KBt2F3QA2dSmE6a9", "http://wns8363.dothome.co.kr/Cats/cat14.jpg");
    public static final Cat cat_015 = new Cat("l32nA70qkRUCz5MK", "http://wns8363.dothome.co.kr/Cats/cat15.jpg");
    public static final Cat cat_016 = new Cat("j7U8qIpn314G2g58", "http://wns8363.dothome.co.kr/Cats/cat16.jpg");
    public static final Cat cat_017 = new Cat("WY1U7J3EH6q3XHN0", "http://wns8363.dothome.co.kr/Cats/cat17.jpg");
    public static final Cat cat_018 = new Cat("rAx2TS16u07DQUu9", "http://wns8363.dothome.co.kr/Cats/cat18.jpg");

    public static final Cat[] Cat_Collection = {
            cat_001, cat_002, cat_003, cat_004, cat_005, cat_006, cat_007, cat_008,
            cat_009, cat_010, cat_011, cat_012, cat_013, cat_014, cat_015, cat_016,
            cat_017, cat_018
    };
}