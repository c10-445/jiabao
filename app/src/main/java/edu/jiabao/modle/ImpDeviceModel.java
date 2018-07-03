package edu.jiabao.modle;

import java.util.List;

import edu.jiabao.database.Device;
import edu.jiabao.database.DeviceDao;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.modle.inteface.IDeviceModel;

public class ImpDeviceModel implements IDeviceModel {
    private DeviceDao deviceDao;
    public ImpDeviceModel(DeviceDao deviceDao){
        this.deviceDao=deviceDao;
    }
    @Override
    public List<DeviceEntry> getDevices(Long userId) {
        List<Device> list= deviceDao.queryBuilder().where(DeviceDao.Properties.User_id.eq(userId)).list();
        DeviceEntry.DaoToEntry(list);
        return DeviceEntry.getDevices();
    }

    @Override
    public void addDevice(DeviceEntry entry) {
        deviceDao.insertOrReplace(entry.toDevice());
    }
}
