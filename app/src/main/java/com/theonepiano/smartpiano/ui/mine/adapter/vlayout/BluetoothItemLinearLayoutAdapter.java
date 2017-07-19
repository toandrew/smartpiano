package com.theonepiano.smartpiano.ui.mine.adapter.vlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.model.bean.Song;
import com.theonepiano.smartpiano.model.mine.bean.BluetoothDevice;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.VlayoutAdapterType;

import java.util.List;

/**
 * Created by jim on 2017/6/24.
 */

public class BluetoothItemLinearLayoutAdapter extends DelegateAdapter.Adapter<BluetoothItemLinearLayoutAdapter.MyViewHolder> {
    private LayoutHelper mLayoutHelper;

    private List<BluetoothDevice> devices;

    private LayoutInflater mLayoutInflater;

    String[] mDeviceStatus;

    public BluetoothItemLinearLayoutAdapter(Context context, LayoutHelper helper, List<BluetoothDevice> devices) {
        mLayoutHelper = helper;

        this.devices = devices;

        mLayoutInflater = LayoutInflater.from(context);

        mDeviceStatus = context.getResources().getStringArray(R.array.bluetooth_status);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.w("HAHA", "onCreateViewHolder!!!!");
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.vlayout_item_bluetooth, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyViewHolder myViewHolder = holder;
        myViewHolder.mName.setText(devices.get(position).name);

        myViewHolder.mStatus.setText(getDeviceStatus(devices.get(position).status));
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    @Override
    public int getItemViewType(int position) {
        return VlayoutAdapterType.VLAYOUT_ADAPTER_TYPE_SONG_ITEM;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;

        private TextView mStatus;

        public MyViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.item_name);

            mStatus = (TextView) itemView.findViewById(R.id.item_status);
        }
    }

    private String getDeviceStatus(int status) {
        if (status >= mDeviceStatus.length || status < 0) {
            return "";
        }

        return mDeviceStatus[status];
    }
}
