package com.theonepiano.smartpiano.presenter.mine.interfaces;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.theonepiano.smartpiano.base.BaseModel;
import com.theonepiano.smartpiano.base.BasePresenter;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.mine.bean.MyBluetoothDevice;

import java.util.List;

/**
 * Created by jim on 2017/7/18.
 */

public interface MineBluetoothContract {
    interface View extends BaseView {
        Context getMyContext();

        void onDeviceUpdated(List<MyBluetoothDevice> devices);

        void onDeviceScanStatusChanged(boolean isScanning);

        void onMidiDataReceived(byte[] data);
    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void init();

        public abstract void startScan();

        public abstract void stopScan();

        public abstract boolean isBluetoothDeviceConnected();

        public abstract void connect(String devId, String name);

        public abstract void disconnect();

        public abstract void sendMidiMessage(final byte data[]);
    }
}
