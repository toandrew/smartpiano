package com.theonepiano.smartpiano.model.mine.bean;

/**
 * Created by jim on 2017/7/19.
 */

public class BluetoothDevice {
    public static final int BLUETOOTH_DEVICE_STATUS_IDLE = 0;
    public static final int BLUETOOTH_DEVICE_STATUS_CONNECTING = 1;
    public static final int BLUETOOTH_DEVICE_STATUS_CONNECTED = 2;
    public static final int BLUETOOTH_DEVICE_STATUS_OFFLINE = 3;

    public int status;

    public String name;

    public String info;
}
