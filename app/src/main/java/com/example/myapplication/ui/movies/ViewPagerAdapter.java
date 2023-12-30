package com.example.myapplication.ui.movies;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DramaFragment();
            case 1:
                return new ComedyFragment();
            case 2:
                return new ActionFragment();
            case 3:
                return new RomanceFragment();
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Number of tabs
    }
}
