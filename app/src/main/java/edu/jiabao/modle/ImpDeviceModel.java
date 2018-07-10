package edu.jiabao.modle;

import java.util.List;
import java.util.Map;

import edu.jiabao.dao.Dao;
import edu.jiabao.database.Device;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.jsonObject.DeviceDataBean;
import edu.jiabao.modle.inteface.IDeviceModel;

public class ImpDeviceModel implements IDeviceModel {
    public ImpDeviceModel(){
        refleshModel();
    }
    @Override
    public List<DeviceEntry> getDevices() {
        refleshModel();
        return DeviceEntry.getDevices();
    }

    @Override
    public void addDevice(DeviceEntry entry) {
        Dao.DeviceAddDevice(entry.toDevice());
        refleshModel();
    }

    @Override
    public void deleteDevice(DeviceEntry entry) {
        Dao.DeviceDeleteDevice(entry.toDevice());
        refleshModel();
    }

    @Override
    public void deleteDevice(PackageEntry entry) {
        Dao.DeviceDeleteDevice(entry);
        refleshModel();
    }

    @Override
    public void refleshDeviceInfo(DeviceEntry entry) {
        Dao.DeviceRefleshDeviceInfo(entry);
        refleshModel();
    }

    @Override
    public void initDevcie(List<Integer> i_list,Map<Integer,DeviceDataBean> map) {
        for(int i=0;i<i_list.size();i++) {
            DeviceEntry.setDeviceByMap(i_list.get(i),map);
        }
        DeviceEntry.EntryToDao();
    }

    @Override
    public void setDevicesByOperator(OperatorEntry entry){
        DeviceEntry.setDevicesByOperator(entry);
        DeviceEntry.EntryToDao();
    }

    private void refleshModel(){
        Long userId=ImpUserModel.getUser().getUser_id();
        List<Device> list= Dao.DeviceQueryUserDevice(userId);
        DeviceEntry.DaoToEntry(list);
    }
}
