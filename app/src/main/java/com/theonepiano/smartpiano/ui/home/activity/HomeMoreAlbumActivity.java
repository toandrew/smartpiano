package com.theonepiano.smartpiano.ui.home.activity;

import android.os.Bundle;

import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseSwipeBackActivity;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.home.HomeMoreAlbumModel;
import com.theonepiano.smartpiano.presenter.home.impl.HomeMoreAlbumPresenter;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeMoreAlbumContract;

/**
 * Created by jim on 2017/6/25.
 */

public class HomeMoreAlbumActivity extends BaseSwipeBackActivity<HomeMoreAlbumModel, HomeMoreAlbumPresenter> implements HomeMoreAlbumContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_more_album;
    }

    @Override
    protected void initViews(Bundle bundle) {

    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }
}
