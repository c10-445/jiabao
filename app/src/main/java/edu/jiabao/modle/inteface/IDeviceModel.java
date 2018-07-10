package edu.jiabao.modle.inteface;

import java.util.List;
import java.util.Map;

import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.jsonObject.DeviceDataBean;

public interface IDeviceModel {
    public List<DeviceEntry> getDevices();

    public void addDevice(DeviceEntry entry);

    public void deleteDevice(DeviceEntry entry);
    public void deleteDevice(PackageEntry entry);

    public void refleshDeviceInfo(DeviceEntry entry);

    public void setDevicesByOperator(OperatorEntry operator);

    public void initDevcie(List<Integer> i_list,Map<Integer,DeviceDataBean> map);
}
