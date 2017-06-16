package com.theonepiano.smartpiano.ui.home.fragment;

import android.os.Bundle;

import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.home.HomeRecommendModel;
import com.theonepiano.smartpiano.presenter.home.impl.HomeRecommendPresenter;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeRecommendContract;

/**
 * Created by jim on 2017/6/13.
 */

public class HomeRecommendFragment extends BaseFragment<HomeRecommendModel, HomeRecommendPresenter> implements HomeRecommendContract.View {
    public static HomeRecommendFragment getInstance() {
        return new HomeRecommendFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_recommend;
    }

    @Override
    protected void initViews(Bundle bundle) {

    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }

    @Override
    protected void lazyFetchData() {

    }
}
