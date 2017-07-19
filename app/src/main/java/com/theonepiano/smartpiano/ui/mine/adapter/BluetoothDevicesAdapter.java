package com.theonepiano.smartpiano.ui.mine.adapter;

import android.content.Context;
import android.graphics.Color;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.theonepiano.smartpiano.model.bean.Album;
import com.theonepiano.smartpiano.model.bean.Song;
import com.theonepiano.smartpiano.model.home.bean.RecommendAlbumSong;
import com.theonepiano.smartpiano.model.mine.bean.BluetoothDevice;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.AlbumSongGridAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.BannerAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.RushKaraColumnLayoutAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.SongItemLinearLayoutAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.TitleLinearLayoutAdapter;
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

    public void update(List<BluetoothDevice> items) {
        mAdapters.clear();

        initBluetoothDevices(items);

        this.setAdapters(mAdapters);
    }

    private void initBluetoothDevices(List<BluetoothDevice> items) {
        LinearLayoutHelper deviceHelper = new LinearLayoutHelper();
        deviceHelper.setItemCount(items.size());
        BluetoothItemLinearLayoutAdapter deviceAdapter = new BluetoothItemLinearLayoutAdapter(mContext, deviceHelper, items);

        mAdapters.add(deviceAdapter);
    }
}
