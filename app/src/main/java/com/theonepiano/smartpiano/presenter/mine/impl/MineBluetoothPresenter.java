package com.theonepiano.smartpiano.presenter.mine.impl;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.util.Log;

import com.theonepiano.smartpiano.model.mine.bean.MyBluetoothDevice;
import com.theonepiano.smartpiano.presenter.mine.interfaces.MineBluetoothContract;
import com.wanaka.ble.midi.BleMidiManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.kshoji.blemidi.listener.OnMidiDeviceFoundListener;
import jp.kshoji.blemidi.listener.OnMidiDeviceStatusListener;
import jp.kshoji.blemidi.listener.OnMidiScanStatusListener;

import static jp.kshoji.blemidi.listener.OnMidiDeviceStatusListener.DEVICE_IDLE;
import static jp.kshoji.driver.midi.util.Constants.TAG;

/**
 * Created by jim on 2017/7/18.
 */

public class MineBluetoothPresenter extends MineBluetoothContract.Presenter {
    BleMidiManager mBleMidiManager;

    private List<MyBluetoothDevice> mBluetoothDeviceList = new ArrayList<>();

    private HashMap<String, BluetoothDevice> mBluetoothDevicesHashMap = new HashMap<>();

    private HashMap<BluetoothDevice, Integer> mBluetoothDeviceStatusHashMap = new HashMap<>();

    @Override
    public void init() {
        mBleMidiManager.getInstance().init(mView.getMyContext());
    }

    @Override
    public void startScan() {
        reset();

        initEventListeners();

        mBleMidiManager.getInstance().open();
    }

    @Override
    public void stopScan() {
        mBleMidiManager.getInstance().close();

        // after close
        reset();
    }

    @Override
    public boolean isBluetoothDeviceConnected() {
        return mBleMidiManager.getInstance().isConnected();
    }

    @Override
    public void connect(String devId, String name) {
        mBleMidiManager.getInstance().connect(mBluetoothDevicesHashMap.get(devId.toLowerCase()));
    }

    @Override
    public void disconnect() {
        mBleMidiManager.getInstance().disconnect();
    }

    private MyBluetoothDevice getMyBluetoothDevice(@NonNull BluetoothDevice device, int status) {
        MyBluetoothDevice d = new MyBluetoothDevice();
        d.id = device.getAddress();
        d.name = device.getName();
        d.info = device.getAddress() + "(" + device.getType() + ")";
        d.status = status;

        return d;
    }

    private List<MyBluetoothDevice> updateDevices(@NonNull BluetoothDevice device) {
        Log.w(TAG, "updateDevices![" + mBluetoothDeviceList.size() + "]");

        synchronized (mBluetoothDeviceList) {
            if (mBluetoothDeviceList.size() == 0) {

                MyBluetoothDevice m = getMyBluetoothDevice(device, getDeviceStatus(device));
                mBluetoothDeviceList.add(m);

                mBluetoothDevicesHashMap.put(m.id.toLowerCase(), device);

                return mBluetoothDeviceList;
            }

            BluetoothDevice d = mBluetoothDevicesHashMap.get(device.getAddress().toLowerCase());
            if (d == null) {
                MyBluetoothDevice m = getMyBluetoothDevice(device, getDeviceStatus(device));
                mBluetoothDevicesHashMap.put(m.id.toLowerCase(), device);

                mBluetoothDeviceList.add(m);
            } else {
                for (MyBluetoothDevice m : mBluetoothDeviceList) {
                    if (m.id.equalsIgnoreCase(device.getAddress())) {
                        m.status = getDeviceStatus(device);

                        Log.w(TAG, "device: " + device + "]status[ " + m.status + "]");
                    }
                }
            }
        }

        return mBluetoothDeviceList;
    }

    /**
     * reset all
     */
    private void reset() {
        mBluetoothDeviceList.clear();
        mBluetoothDevicesHashMap.clear();
        mBluetoothDeviceStatusHashMap.clear();

        mBleMidiManager.getInstance().setOnBluetoothDeviceFoundListener(null);
        mBleMidiManager.getInstance().setOnMidiScanStatusListener(null);
        mBleMidiManager.getInstance().setOnMidiDeviceStatusListener(null);
    }

    /**
     * init device event listeners
     */
    private void initEventListeners() {
        mBleMidiManager.getInstance().setOnBluetoothDeviceFoundListener(new OnMidiDeviceFoundListener() {
            @Override
            public void onDeviceFound(@NonNull BluetoothDevice device) {
                Log.w(TAG, "onDeviceFound[" + device + "]");
                mView.onDeviceUpdated(updateDevices(device));
            }
        });

        mBleMidiManager.getInstance().setOnMidiScanStatusListener(new OnMidiScanStatusListener() {
            @Override
            public void onMidiScanStatusChanged(boolean isScanning) {
                mView.onDeviceScanStatusChanged(isScanning);
            }
        });

        mBleMidiManager.getInstance().setOnMidiDeviceStatusListener(new OnMidiDeviceStatusListener() {
            @Override
            public void onDeviceStatusChanged(@NonNull BluetoothDevice device, int status) {
                mBluetoothDeviceStatusHashMap.put(device, status);
                Log.w(TAG, "device:" + device + " status[" + status + "]");
                mView.onDeviceUpdated(updateDevices(device));
            }
        });
    }

    /**
     * Get current ble device's status
     *
     * @param device
     * @return
     */
    private int getDeviceStatus(@NonNull BluetoothDevice device) {
        if (mBluetoothDeviceStatusHashMap.containsKey(device)) {
            return mBluetoothDeviceStatusHashMap.get(device);
        }

        return DEVICE_IDLE;
    }
}
