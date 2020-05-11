package com.ostrov.sampleviewpager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private final int COUNT = 3;

    SimpleFragmentPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CatFragment();
            case 1:
                return new DogFragment();
            default:
                return new OtherPetFragment();
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_1);
            case 1:
                return mContext.getString(R.string.fragment_2);
            default:
                return mContext.getString(R.string.fragment_3);
        }
    }
}
