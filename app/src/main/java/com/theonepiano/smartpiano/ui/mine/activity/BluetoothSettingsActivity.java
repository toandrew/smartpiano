package com.theonepiano.smartpiano.ui.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseSwipeBackActivity;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.mine.MineBluetoothModel;
import com.theonepiano.smartpiano.model.mine.bean.BluetoothDevice;
import com.theonepiano.smartpiano.presenter.mine.impl.MineBluetoothPresenter;
import com.theonepiano.smartpiano.presenter.mine.interfaces.MineBluetoothContract;
import com.theonepiano.smartpiano.ui.mine.adapter.BluetoothDevicesAdapter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jim on 2017/7/18.
 */

public class BluetoothSettingsActivity extends BaseSwipeBackActivity<MineBluetoothModel, MineBluetoothPresenter> implements MineBluetoothContract.View {
    private static final String TAG = BluetoothSettingsActivity.class.getSimpleName();

    @BindView(R.id.bluetooth_content_view)
    RecyclerView mContentView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_bluetooth_settings;
    }

    @Override
    protected void initViews(Bundle bundle) {
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mContentView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mContentView.setLayoutManager(manager);

        BluetoothDevicesAdapter delegateAdapter = new BluetoothDevicesAdapter(this, manager);
        delegateAdapter.update(getBluetoothDeviceItems());

        mContentView.setAdapter(delegateAdapter);
    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }

    private List<BluetoothDevice> getBluetoothDeviceItems() {
        List<BluetoothDevice> items = new LinkedList<>();

        BluetoothDevice sample = new BluetoothDevice();
        sample.status = BluetoothDevice.BLUETOOTH_DEVICE_STATUS_IDLE;
        sample.name = "The ONE";
        sample.info = "Hello world!";

        items.add(sample);

        return items;
    }

    @OnClick(R.id.img_back)
    public void onBackClicked(View view) {
        finish();
    }
}
