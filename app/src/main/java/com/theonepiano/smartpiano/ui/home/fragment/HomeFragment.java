package com.theonepiano.smartpiano.ui.home.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.home.HomeModel;
import com.theonepiano.smartpiano.presenter.home.impl.HomePresenter;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeContract;
import com.theonepiano.smartpiano.ui.home.adapter.HomeFragmentListAdapter;

import butterknife.BindView;

/**
 * Created by jim on 2017/6/11.
 */

public class HomeFragment extends BaseFragment<HomeModel, HomePresenter> implements HomeContract.View {

    @BindView(R.id.sliding_tab)
    SlidingTabLayout mSlidingTab;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private HomeFragmentListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(Bundle bundle) {
        final String[] titles = getResources().getStringArray(R.array.home_fragments);

        mViewPager.setOffscreenPageLimit(titles.length);
        mAdapter = new HomeFragmentListAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mSlidingTab.setViewPager(mViewPager, titles);
    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }

    @Override
    protected void lazyFetchData() {
    }
}
