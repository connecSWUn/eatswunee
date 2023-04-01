package com.example.eatswunee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class communityFragment extends Fragment {

    //사용할 컴포넌트 선언
    Button reg_button;
    EditText title_et, content_et;

    // 유저아이디 변수
    String userid = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_community);

    }

    private void setContentView(int fragment_community) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_community, container, false);

        // 컴포넌트 초기화
        reg_button = v.findViewById(R.id.reg_button);

        // 버튼 이벤트 추가
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommunityUploaded.class);
                startActivity(intent);
            }
        });

        return v;
    }
}