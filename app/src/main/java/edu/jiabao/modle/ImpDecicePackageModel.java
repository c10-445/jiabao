package edu.jiabao.modle;

import com.google.gson.JsonArray;

import edu.jiabao.database.folderDao;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.modle.inteface.IDeviceModel;
import edu.jiabao.modle.inteface.IdevicePackageModel;

public class ImpDecicePackageModel implements IdevicePackageModel {
    private folderDao folderDao;
    public ImpDecicePackageModel(folderDao folderDao){
        this.folderDao=folderDao;
    }

    @Override
    public void addPackage(PackageEntry entry) {
        folderDao.insert(entry.toFolder());
        PackageEntry.addPackage(entry);
    }

    @Override
    public void deletePackage(IDeviceModel deviceModel,int packzge_id) {
        folderDao.deleteByKey(new Long(packzge_id));
        PackageEntry.deletePackage(packzge_id);
    }

    @Override
    public void addDevice() {

    }

    @Override
    public void deleteDevice() {

    }

    @Override
    public void operateDevice(JsonArray jsonObjects) {

    }

    @Override
    public void addOperator() {

    }

    @Override
    public void deleteOperator() {

    }

    @Override
    public void operatePackage() {

    }
}
