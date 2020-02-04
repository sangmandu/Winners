package com.example.winners_app.fragments.login_reg_pw;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.winners_app.R;


public class Regist1 extends Fragment {

    private EditText et_id, et_name,et_pw, et_pwcon;
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
        return inflater.inflate(R.layout.fragment_regist1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        et_id = view.findViewById(R.id.et_id);
        et_name = view.findViewById(R.id.et_name);
        et_pw = getView().findViewById(R.id.et_password);
        et_pwcon = getView().findViewById(R.id.et_pwconfirm);
        ImageView image = act.findViewById(R.id.iv_btn_next);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                String userID = et_id.getText().toString();
                String userName = et_name.getText().toString();
                String userPass = et_pw.getText().toString();
                String userPassCon = et_pwcon.getText().toString();
                if(!userPass.equals(userPassCon))
                {
                    Toast toast = Toast.makeText(context, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                bundle.putString("reg_id", userID);
                bundle.putString("reg_name", userName);
                bundle.putString("reg_pass", userPass);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Regist2 fragment2 = new Regist2();
                fragment2.setArguments(bundle);
                transaction.addToBackStack(null);
                transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
                transaction.replace(R.id.frame, fragment2);
                transaction.commit();
            }
        });
    }


}
