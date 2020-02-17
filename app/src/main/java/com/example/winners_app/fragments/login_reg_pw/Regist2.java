package com.example.winners_app.fragments.login_reg_pw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.winners_app.LoginActivity;
import com.example.winners_app.R;
import com.example.winners_app.Request.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class Regist2 extends Fragment {

    private EditText et_major, et_age, et_answer;
    String id, name, pass;
    private Context context;
    private Activity act;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        act = getActivity();
        context = container.getContext();
        if(getArguments() != null){
            id = getArguments().getString("reg_id");
            name = getArguments().getString("reg_name");
            pass = getArguments().getString("reg_pass");
        }
        return inflater.inflate(R.layout.fragment_regist2, container, false);
    }

    public void onViewCreated(View view, final Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        et_major = getView().findViewById(R.id.et_major);
        et_age = getView().findViewById(R.id.et_age);
        et_answer = getView().findViewById(R.id.et_pwanswer);


        ImageView image = act.findViewById(R.id.iv_btn_next);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = id;
                String userName = name;
                String userPass = pass;
                int userAge;
                if(!et_age.getText().toString().equals("")){
                    userAge = Integer.parseInt(et_age.getText().toString());
                }
                else{
                    return;
                }
                String userMajor = et_major.getText().toString();
                String userAnswer = et_answer.getText().toString();
                //volley 구문
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success"); //PHP에 등록된 코드, 코드가 성공적으로 실행되었는가
                            if(success){ // 회원등록에 성공한 경우
                                Toast.makeText(context, "회원 등록에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else {  // 회원등록에 실패한 경우
                                Toast.makeText(context, "회원 등록에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                RegisterRequest registerRequest = new RegisterRequest(userID, userName, userPass, userAge, userMajor, userAnswer, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(registerRequest);


            }
        });
    }
}
