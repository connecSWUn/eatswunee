package com.example.eatswunee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.eatswunee.bistro.MyPageAdapter;
import com.example.eatswunee.bistro.total_bistroFragment;
import com.google.android.material.tabs.TabLayout;


public class orderFragment extends Fragment {

    private MyPageAdapter myPageAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int tabCurrentIdx = 0;

    EditText search_bar;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_order, container, false);

        tabLayout = v.findViewById(R.id.myPagetabLayout);
        viewPager = v.findViewById(R.id.myPageviewPager);

        //피드 구성하는 탭레이아웃 + 뷰페이저
        tabLayout.addTab(tabLayout.newTab().setText("전체"));
        tabLayout.addTab(tabLayout.newTab().setText("포아이니"));
        tabLayout.addTab(tabLayout.newTab().setText("분식대첩"));
        tabLayout.addTab(tabLayout.newTab().setText("만권화밥"));
        tabLayout.addTab(tabLayout.newTab().setText("최고당\n돈가스"));

        //커스텀 어댑터 생성
        myPageAdapter = new MyPageAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(myPageAdapter);
        viewPager.setCurrentItem(tabCurrentIdx);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabCurrentIdx = tab.getPosition();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }
}