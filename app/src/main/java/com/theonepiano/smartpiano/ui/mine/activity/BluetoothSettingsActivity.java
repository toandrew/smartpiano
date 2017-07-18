package com.theonepiano.smartpiano.ui.mine.activity;

import android.os.Bundle;

import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseSwipeBackActivity;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.mine.MineBluetoothModel;
import com.theonepiano.smartpiano.presenter.mine.impl.MineBluetoothPresenter;
import com.theonepiano.smartpiano.presenter.mine.interfaces.MineBluetoothContract;

/**
 * Created by jim on 2017/7/18.
 */

public class BluetoothSettingsActivity extends BaseSwipeBackActivity<MineBluetoothModel, MineBluetoothPresenter> implements MineBluetoothContract.View {
    private static final String TAG = BluetoothSettingsActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_bluetooth_settings;
    }

    @Override
    protected void initViews(Bundle bundle) {

    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }
}
