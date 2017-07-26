package com.theonepiano.smartpiano.ui.mine.adapter.vlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.model.mine.bean.MyBluetoothDevice;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.VlayoutAdapterType;
import com.theonepiano.smartpiano.ui.mine.event.BluetoothClickedEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static jp.kshoji.blemidi.listener.OnMidiDeviceStatusListener.DEVICE_CHAR_ENABLE_WRITE;
import static jp.kshoji.blemidi.listener.OnMidiDeviceStatusListener.DEVICE_CHAR_RECV_DATA;
import static jp.kshoji.blemidi.listener.OnMidiDeviceStatusListener.DEVICE_DISCONNECTED;
import static jp.kshoji.blemidi.listener.OnMidiDeviceStatusListener.DEVICE_IDLE;
import static jp.kshoji.driver.midi.util.Constants.TAG;

/**
 * Created by jim on 2017/6/24.
 */

public class BluetoothItemLinearLayoutAdapter extends DelegateAdapter.Adapter<BluetoothItemLinearLayoutAdapter.MyViewHolder> {
    private LayoutHelper mLayoutHelper;

    private List<MyBluetoothDevice> devices;

    private LayoutInflater mLayoutInflater;

    private static String[] sDeviceStatus;

    public BluetoothItemLinearLayoutAdapter(Context context, LayoutHelper helper, List<MyBluetoothDevice> devices) {
        mLayoutHelper = helper;

        this.devices = devices;

        mLayoutInflater = LayoutInflater.from(context);

        sDeviceStatus = context.getResources().getStringArray(R.array.bluetooth_status);
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

        myViewHolder.mInfo.setText(devices.get(position).info);

        myViewHolder.mId.setText(devices.get(position).id);

        if (devices.get(position).status == DEVICE_IDLE || devices.get(position).status == DEVICE_DISCONNECTED
                ||  devices.get(position).status == DEVICE_CHAR_ENABLE_WRITE
                || devices.get(position).status == DEVICE_CHAR_RECV_DATA) {
            myViewHolder.mSearchingProgressBar.setVisibility(View.INVISIBLE);
        } else {
            myViewHolder.mSearchingProgressBar.setVisibility(View.VISIBLE);
        }
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

        private TextView mInfo;

        private TextView mId;

        private ProgressBar mSearchingProgressBar;

        public MyViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.item_name);

            mStatus = (TextView) itemView.findViewById(R.id.item_status);

            mInfo = (TextView) itemView.findViewById(R.id.item_info);

            mId = (TextView) itemView.findViewById(R.id.item_id);

            mSearchingProgressBar = (ProgressBar) itemView.findViewById(R.id.searching_progress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.w(TAG, "onClick!!!!!" + mId.getText().toString());
                    BluetoothClickedEvent event = new BluetoothClickedEvent();
                    event.id = mId.getText().toString();
                    event.name = mName.getText().toString();
                    event.status = getConnectedStatus(mStatus.getText().toString());

                    EventBus.getDefault().post(event);
                }
            });
        }
    }

    private static String getDeviceStatus(int status) {
        if (status >= sDeviceStatus.length || status < 0) {
            return "";
        }

        return sDeviceStatus[status];
    }

    private static int getConnectedStatus(String info) {
        for (int i = 0; i < sDeviceStatus.length; i++) {
            if (sDeviceStatus[i].equals(info)) {
                return i;
            }
        }

        return DEVICE_IDLE;
    }
}
