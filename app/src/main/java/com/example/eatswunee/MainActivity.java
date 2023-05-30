package com.example.eatswunee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.eatswunee.community.articlesActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_format_list_bulleted_24);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();

                int id = item.getItemId();

                if(id == R.id.item_info){
                    Intent intent = new Intent(MainActivity.this, articlesActivity.class);
                    startActivity(intent);
                }
                else if(id == R.id.item_report){

                }

                return true;
            }
        });

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}