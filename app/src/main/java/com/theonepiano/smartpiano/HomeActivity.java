package com.theonepiano.smartpiano;

import android.os.Bundle;

import com.theonepiano.smartpiano.base.BaseFragmentActivity;
import com.theonepiano.smartpiano.ui.course.fragment.CourseFragment;
import com.theonepiano.smartpiano.ui.home.fragment.HomeFragment;
import com.theonepiano.smartpiano.ui.mine.fragment.MineFragment;
import com.theonepiano.smartpiano.view.home.NavigateTabBar;

import butterknife.BindView;

/**
 * Created by jim on 2017/6/4.
 */

public class HomeActivity extends BaseFragmentActivity {
    @BindView(R.id.mainTabBar)
    NavigateTabBar mNavigateTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNavigateTabBar.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mNavigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void init() {
        final String[] titles = getResources().getStringArray(R.array.navigation_titles);
        mNavigateTabBar.addTab(HomeFragment.class, new NavigateTabBar.TabParam(R.drawable.home_pressed, R.drawable.home_selected, titles[0]));
        mNavigateTabBar.addTab(CourseFragment.class, new NavigateTabBar.TabParam(R.drawable.course_pressed, R.drawable.course_selected, titles[1]));
        mNavigateTabBar.addTab(MineFragment.class, new NavigateTabBar.TabParam(R.drawable.mine_pressed, R.drawable.mine_selected, titles[2]));

        mNavigateTabBar.setTabSelectedListener(new NavigateTabBar.OnTabSelectedListener() {

            @Override
            public void onTabSelected(NavigateTabBar.ViewHolder holder) {
                mNavigateTabBar.showFragment(holder);
            }
        });
    }
}
