package com.example.eatswunee.mypage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.eatswunee.MainActivity;
import com.example.eatswunee.R;
import com.example.eatswunee.mypageFragment;

public class profile_editActivity extends AppCompatActivity {

    Button logout, withdrawal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_edit_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

        logout = findViewById(R.id.logout_btn);
        withdrawal = findViewById(R.id.withdrawal_btn);

        // 로그아웃, 회원탈퇴 버튼 밑줄 표현
        logout.setPaintFlags(logout.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        withdrawal.setPaintFlags(withdrawal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}