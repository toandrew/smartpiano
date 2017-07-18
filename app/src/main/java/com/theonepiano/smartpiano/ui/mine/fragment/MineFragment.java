package com.theonepiano.smartpiano.ui.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.mine.MineModel;
import com.theonepiano.smartpiano.presenter.mine.impl.MinePresenter;
import com.theonepiano.smartpiano.presenter.mine.interfaces.MineContract;
import com.theonepiano.smartpiano.ui.home.activity.HomeMoreAlbumActivity;
import com.theonepiano.smartpiano.ui.mine.activity.BluetoothSettingsActivity;

import butterknife.OnClick;

/**
 * Created by jim on 2017/6/11.
 */

public class MineFragment extends BaseFragment<MineModel, MinePresenter> implements MineContract.View {
    private static final String TAG = MineFragment.class.getSimpleName();

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

    @OnClick(R.id.rl_bluetooth_settings)
    public void onBluetoothSettingsClicked(View view) {
        Log.w(TAG, "onBluetoothSettingsClicked clicked!");

        Intent intent = new Intent(this.getContext(), BluetoothSettingsActivity.class);
        this.getContext().startActivity(intent);
    }
}
