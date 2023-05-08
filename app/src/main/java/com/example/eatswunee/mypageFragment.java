package com.example.eatswunee;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.eatswunee.mypage.order_listActivity;
import com.example.eatswunee.mypage.profile_editActivity;
import com.example.eatswunee.mypage.settingActivity;


public class mypageFragment extends Fragment {

    Button profileBtn, listBtn, settingBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);

        profileBtn = v.findViewById(R.id.profile_edit_btn);
        listBtn = v.findViewById(R.id.list_btn);
        settingBtn = v.findViewById(R.id.setting_btn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), profile_editActivity.class);
                startActivity(intent);
            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), order_listActivity.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), settingActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}