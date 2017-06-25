package com.theonepiano.smartpiano.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.theonepiano.smartpiano.ui.home.fragment.category.HomeCategoryFragment;
import com.theonepiano.smartpiano.ui.home.fragment.recommend.HomeRecommendFragment;

/**
 * Created by jim on 2017/6/13.
 */

public class HomeFragmentListAdapter extends FragmentStatePagerAdapter {
    private static final int HOME_RECOMMEND_INDEX = 0;
    private static final int HOME_CATEGORY_INDEX = 1;

    private static final int HOME_FRAGMENT_COUNT = 2;

    public HomeFragmentListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == HOME_RECOMMEND_INDEX) {
            return HomeRecommendFragment.getInstance();
        } else if (position == HOME_CATEGORY_INDEX) {
            return HomeCategoryFragment.getInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return HOME_FRAGMENT_COUNT;
    }
}
