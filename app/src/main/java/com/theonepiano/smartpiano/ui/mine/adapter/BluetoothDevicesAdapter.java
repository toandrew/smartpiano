package com.theonepiano.smartpiano.ui.mine.adapter;

import android.content.Context;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.theonepiano.smartpiano.model.mine.bean.MyBluetoothDevice;
import com.theonepiano.smartpiano.ui.mine.adapter.vlayout.BluetoothItemLinearLayoutAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jim on 2017/6/25.
 */

public class BluetoothDevicesAdapter extends DelegateAdapter {
    List<Adapter> mAdapters;
    private Context mContext;

    public BluetoothDevicesAdapter(Context context, VirtualLayoutManager layoutManager) {
        super(layoutManager, true);

        mContext = context;

        mAdapters = new LinkedList<>();
    }

    public void update(List<MyBluetoothDevice> items) {
        mAdapters.clear();

        initBluetoothDevices(items);

        this.setAdapters(mAdapters);
    }

    private void initBluetoothDevices(List<MyBluetoothDevice> items) {
        LinearLayoutHelper deviceHelper = new LinearLayoutHelper();
        deviceHelper.setItemCount(items.size());
        BluetoothItemLinearLayoutAdapter deviceAdapter = new BluetoothItemLinearLayoutAdapter(mContext, deviceHelper, items);

        mAdapters.add(deviceAdapter);
    }
}
