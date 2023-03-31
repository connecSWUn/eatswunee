package com.example.eatswunee.bistro;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyPageAdapter extends FragmentStatePagerAdapter {
    private int numberOfFragment;
    public MyPageAdapter(FragmentManager fm, int numberOfFragment){
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numberOfFragment = numberOfFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1: return new phoini_bistroFragment();
            case 2: return new bunsik_bistroFragment();
            case 3: return new mankwon_bistroFragment();
            case 4: return new choigodang_bistroFragment();
            default: return new total_bistroFragment();
        }
    }

    @Override
    public int getCount() {
        return numberOfFragment;
    }
}
