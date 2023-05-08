package com.example.eatswunee.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.eatswunee.MainActivity;
import com.example.eatswunee.R;
import com.example.eatswunee.mypageFragment;

public class profile_editActivity extends AppCompatActivity {

    ImageButton backBtn;
    Button logout, withdrawal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        backBtn = findViewById(R.id.edit_backBtn);
        logout = findViewById(R.id.logout_btn);
        withdrawal = findViewById(R.id.withdrawal_btn);

        // 로그아웃, 회원탈퇴 버튼 밑줄 표현
        logout.setPaintFlags(logout.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        withdrawal.setPaintFlags(withdrawal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile_editActivity.this, MainActivity.class);
                intent.putExtra("selectedItem", "mypage");
                startActivity(intent);
            }
        });
    }
}