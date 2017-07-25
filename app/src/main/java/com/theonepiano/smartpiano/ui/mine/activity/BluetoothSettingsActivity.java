package com.theonepiano.smartpiano.ui.mine.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseSwipeBackActivity;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.mine.MineBluetoothModel;
import com.theonepiano.smartpiano.model.mine.bean.MyBluetoothDevice;
import com.theonepiano.smartpiano.presenter.mine.impl.MineBluetoothPresenter;
import com.theonepiano.smartpiano.presenter.mine.interfaces.MineBluetoothContract;
import com.theonepiano.smartpiano.ui.mine.adapter.BluetoothDevicesAdapter;
import com.theonepiano.smartpiano.ui.mine.event.BluetoothClickedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static jp.kshoji.blemidi.listener.OnMidiDeviceStatusListener.DEVICE_DISCONNECTED;
import static jp.kshoji.blemidi.listener.OnMidiDeviceStatusListener.DEVICE_IDLE;

/**
 * Created by jim on 2017/7/18.
 */

public class BluetoothSettingsActivity extends BaseSwipeBackActivity<MineBluetoothModel, MineBluetoothPresenter> implements MineBluetoothContract.View {
    private static final String TAG = BluetoothSettingsActivity.class.getSimpleName();

    @BindView(R.id.bluetooth_content_view)
    RecyclerView mContentView;

    @BindView(R.id.start_scan)
    Button mStartScanBtn;

    @BindView(R.id.stop_scan)
    Button mStopScanBtn;

    @BindView(R.id.scanning_progress)
    ProgressBar mScanningProgressBar;

    BluetoothDevicesAdapter mBluetoothDevicesAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_bluetooth_settings;
    }

    @Override
    protected void initViews(Bundle bundle) {
        checkPermissions();
    }

    private void checkPermissions() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(this, deniedPermissions, 12);
        }
    }

    private void onPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                mPresenter.init();
                break;
        }
    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }

    @OnClick(R.id.img_back)
    public void onBackClicked(View view) {
        finish();
    }

    @OnClick(R.id.start_scan)
    public void onStartScanClicked(View view) {
        mScanningProgressBar.setVisibility(View.VISIBLE);

        mPresenter.startScan();
    }

    @OnClick(R.id.stop_scan)
    public void onStopScanClicked(View view) {
        mScanningProgressBar.setVisibility(View.GONE);

        mPresenter.stopScan();
    }

    @Override
    public Context getMyContext() {
        return this.getApplicationContext();
    }

    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.stopScan();
    }

    @Override
    public void onDeviceUpdated(final List<MyBluetoothDevice> devices) {
        Log.w(TAG, "onDeviceUpdated![" + devices + "]!!!!");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mBluetoothDevicesAdapter == null && !BluetoothSettingsActivity.this.isDestroyed()) {
                    RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
                    mContentView.setRecycledViewPool(viewPool);
                    viewPool.setMaxRecycledViews(0, 10);

                    VirtualLayoutManager manager = new VirtualLayoutManager(BluetoothSettingsActivity.this);
                    mContentView.setLayoutManager(manager);

                    mBluetoothDevicesAdapter = new BluetoothDevicesAdapter(BluetoothSettingsActivity.this, manager);
                }

                if (mContentView != null && mBluetoothDevicesAdapter != null) {
                    mBluetoothDevicesAdapter.update(devices);
                    mContentView.setAdapter(mBluetoothDevicesAdapter);
                }
            }
        });
    }

    @Override
    public void onDeviceScanStatusChanged(final boolean isScanning) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mStartScanBtn != null && mStopScanBtn != null) {
                    mStartScanBtn.setEnabled(!isScanning);
                    mStopScanBtn.setEnabled(isScanning);

                    if (isScanning) {
                        mScanningProgressBar.setVisibility(View.VISIBLE);
                    } else {
                        mScanningProgressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BluetoothClickedEvent event) {
        Log.w(TAG, "onMessageEvent[" + mPresenter.isBluetoothDeviceConnected() + "]id[" + event.id + "]name[" + event.name + "]status[" + event.status + "]");

        mPresenter.disconnect();

        if (event.status == DEVICE_IDLE || event.status == DEVICE_DISCONNECTED) {
            mPresenter.connect(event.id, event.name);
        }
    }
}
