package com.example.eatswunee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);

        // 초기 화면 : FrameLayout에 fragment.xml 띄우기
        getSupportFragmentManager().beginTransaction().add(R.id.mainFrameLayout, new orderFragment()).commit();

        // 바텀 네비게이션 기본 선택 화면
        bottomNavigationView.setSelectedItemId(R.id.item_cafeteria);

        // 바텀 네비게이션 뷰 안의 아이템 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    // item 클릭 시 id 값을 가져와 FrameLayout에 fragment.xml 띄우기
                    case R.id.item_community:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, new communityFragment()).commit();
                        break;
                    case R.id.item_cafeteria:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, new orderFragment()).commit();
                        break;
                    case R.id.item_mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, new mypageFragment()).commit();
                }
                return true;
            }
        });
    }
}