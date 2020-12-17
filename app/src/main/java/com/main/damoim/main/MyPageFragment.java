package com.main.damoim.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.main.damoim.R;
import com.main.damoim.login.LoginActivity;
import com.main.damoim.policyViewActivity;

public class MyPageFragment extends Fragment {

    Button logout;
    Button policyView;
    private boolean SAVE_LOGIN_DATA;
    String userId, serverURL, mainClass;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.fragment_mypage, container, false);

        SAVE_LOGIN_DATA = getArguments().getBoolean("SAVE_LOGIN_DATA");

        userId = getArguments().getString("userId");
        serverURL = getArguments().getString("serverURL");
        mainClass = getArguments().getString("mainClass");

        final SharedPreferences appData = this.getActivity().getSharedPreferences("appData", Activity.MODE_PRIVATE);

        logout = (Button) view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext()).setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getContext(), LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                SAVE_LOGIN_DATA = false;
                                i.putExtra("SAVE_LOGIN_DATA", false);
                                startActivity(i);

                                SharedPreferences.Editor editor = appData.edit();
                                editor.clear();
                                editor.commit();
                            }
                        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        policyView = (Button) view.findViewById(R.id.viewUnPopular);

        policyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), policyViewActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("serverURL", serverURL);
                intent.putExtra("mainClass", mainClass);
                startActivity(intent);
            }
        });

        return view;
    }
}
