package edu.jiabao.modle.inteface;

import java.util.List;

import edu.jiabao.entry.DeviceEntry;

public interface IDeviceModel {
    public List<DeviceEntry> getDevices(Long userId);

    public void addDevice(DeviceEntry entry);
}
