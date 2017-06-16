package com.theonepiano.smartpiano.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.theonepiano.smartpiano.model.home.bean.HomeCateList;
import com.theonepiano.smartpiano.ui.home.fragment.HomeCategoryFragment;
import com.theonepiano.smartpiano.ui.home.fragment.HomeRecommendFragment;

import java.util.List;

/**
 * Created by jim on 2017/6/13.
 */

public class HomeAllListAdapter extends FragmentStatePagerAdapter {
    private static final int HOME_RECOMMEND_INDEX = 0;
    private static final int HOME_CATEGORY_INDEX = 1;

    private List<HomeCateList> mHomeCateList;

    private String[] mTitles;

    public HomeAllListAdapter(FragmentManager fm, List<HomeCateList> cateList, String[] titles) {
        super(fm);

        mHomeCateList = cateList;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == HOME_RECOMMEND_INDEX) {
            return HomeRecommendFragment.getInstance();
        }

        return HomeCategoryFragment.getInstance(mHomeCateList.get(position - 1), position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
