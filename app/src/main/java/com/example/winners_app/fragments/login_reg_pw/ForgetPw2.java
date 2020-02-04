package com.example.winners_app.fragments.login_reg_pw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.winners_app.LoginActivity;
import com.example.winners_app.MyVolley;
import com.example.winners_app.R;
import com.example.winners_app.Request.Forgetpw2Request;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPw2 extends Fragment {
    public static ForgetPw2 newInstance() {
        return new ForgetPw2();
    }

    private EditText et_newpw, et_newpwcon;
    private String fg_id;
    private Context context;
    private Activity act;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        act = getActivity();
        context = container.getContext();
        if (getArguments() != null) {
            fg_id = getArguments().getString("forget_id");
        }
        return inflater.inflate(R.layout.fragment_forgetpw2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_newpw = view.findViewById(R.id.et_fg_newpw);
        et_newpwcon = view.findViewById(R.id.et_fg_newpwcon);
        ImageView image = act.findViewById(R.id.iv_btn_next);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newpw = et_newpw.getText().toString();
                String newpwcon = et_newpwcon.getText().toString();
                String forget_id = fg_id;
                if (!newpw.equals(newpwcon)) {
                    Toast.makeText(context, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }
                //volley 구문
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success"); //PHP에 등록된 코드, 코드가 성공적으로 실행되었는가
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(context, "비밀번호 변경에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            } else {  // 회원등록에 실패한 경우
                                Toast.makeText(context, "비밀번호 변경에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                Forgetpw2Request forgetpwrequest = new Forgetpw2Request(forget_id, newpw, responseListener);
                RequestQueue queue = MyVolley.getInstance(getActivity()).getRequestQueue();
                queue.add(forgetpwrequest);

            }
        });
    }
}
