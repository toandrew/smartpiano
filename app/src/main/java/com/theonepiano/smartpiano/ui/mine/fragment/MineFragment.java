package com.theonepiano.smartpiano.ui.mine.fragment;

import android.os.Bundle;

import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.mine.MineModel;
import com.theonepiano.smartpiano.presenter.mine.impl.MinePresenter;
import com.theonepiano.smartpiano.presenter.mine.interfaces.MineContract;

/**
 * Created by jim on 2017/6/11.
 */

public class MineFragment extends BaseFragment<MineModel, MinePresenter> implements MineContract.View{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
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
