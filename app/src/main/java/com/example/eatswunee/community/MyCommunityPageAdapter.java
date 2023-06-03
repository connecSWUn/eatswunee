package com.example.eatswunee.community;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyCommunityPageAdapter extends FragmentStatePagerAdapter {
    private int numberOfFragment;
    public MyCommunityPageAdapter(FragmentManager fm, int numberOfFragment){
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numberOfFragment = numberOfFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1: return new connecting_articlesFragment();
            case 2: return new found_articlesFragment();
            default: return new finding_articlesFragment();
        }
    }

    @Override
    public int getCount() {
        return numberOfFragment;
    }
}
