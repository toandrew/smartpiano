package com.theonepiano.smartpiano.ui.mine.event;

/**
 * Created by jim on 2017/7/23.
 */

public class BluetoothClickedEvent {
    public static final int CONNECT_BLUETOOTH_DEVICE_EVENT = 1;
    public static final int DISCONNECT_BLUETOOTH_DEVICE_EVENT = 2;

    public String id;
    public String name ;
    public int status;
}
