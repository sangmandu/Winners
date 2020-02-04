package com.example.winners_app.fragments.login_reg_pw;

import android.app.Activity;
import android.content.Context;
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
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.winners_app.MyVolley;
import com.example.winners_app.R;
import com.example.winners_app.Request.Forgetpw1Request;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPw1 extends Fragment {
    public static ForgetPw1 newInstance() {
        return new ForgetPw1();
    }

    private EditText et_id, et_answer;
    private Context context;
    private Activity act;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        act = getActivity();
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_forgetpw1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_id = view.findViewById(R.id.et_fg_id);
        et_answer = view.findViewById(R.id.et_fg_answer);
        ImageView image = act.findViewById(R.id.iv_btn_next);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle bundle = new Bundle();

                String id = et_id.getText().toString();
                String answer = et_answer.getText().toString();

                bundle.putString("forget_id", id);
                bundle.putString("fotget_answer", answer);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success"); //PHP에 등록된 코드, 코드가 성공적으로 실행되었는가
                            if(success){ // 회원등록에 성공한 경우
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                ForgetPw2 fragment = new ForgetPw2();
                                fragment.setArguments(bundle);
                                transaction.addToBackStack(null);
                                transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
                                transaction.replace(R.id.frame_forgetpw, fragment);
                                transaction.commit();
                            }
                            else {  // 회원등록에 실패한 경우
                                Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                Forgetpw1Request forgetpwrequest = new Forgetpw1Request(id, answer, responseListener);
                RequestQueue queue = MyVolley.getInstance(getActivity()).getRequestQueue();
                queue.add(forgetpwrequest);


            }
        });
    }
}
