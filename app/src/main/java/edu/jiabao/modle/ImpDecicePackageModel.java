package edu.jiabao.modle;

import com.google.gson.JsonArray;

import java.util.List;

import edu.jiabao.dao.Dao;
import edu.jiabao.database.folder;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.modle.inteface.IdevicePackageModel;

public class ImpDecicePackageModel implements IdevicePackageModel {
    public ImpDecicePackageModel(){
        refleshModel();
    }

    @Override
    public List<PackageEntry> getPackages() {
        refleshModel();
        return PackageEntry.getPackageEntries();
    }

    @Override
    public List<PackageEntry> getPackagesInPackage(PackageEntry entry) {
        refleshModel();
        return PackageEntry.getPackageEntrysInPackage(entry);
    }

    @Override
    public List<PackageEntry> getPackagesByParentsId(int parentsId) {
        refleshModel();
        return PackageEntry.getPackagesByParentsId(parentsId);
    }

    @Override
    public List<PackageEntry> getPackagesInRoot() {
        refleshModel();
        return PackageEntry.getPackageInRoot();
    }

    @Override
    public PackageEntry getPackageById(int id) {
        refleshModel();
        return PackageEntry.getPackageById(id);
    }

    public void refleshModel(){
        Long userId=ImpUserModel.getUser().getUser_id();
        PackageEntry.daoToEntry(Dao.PackQueryAllPack(userId));
    }

    @Override
    public void addPackage(PackageEntry entry) {
        Dao.PackInsertPack(entry.toFolder());
        refleshModel();
    }

    @Override
    public void deletePackage(PackageEntry entry) {
        Dao.PackDeleteByKey(entry);
        refleshModel();
    }

    @Override
    public void addDevice(PackageEntry packageEntry) {
        Dao.PackInsertPack(packageEntry.toFolder());
        refleshModel();
    }

    @Override
    public void deleteDevice(DeviceEntry entry) {
        List<folder> list=Dao.PackQueryByDevice(entry);
        if(list!=null&&!list.isEmpty()) {
            Dao.PackDeleteDevice(list.get(0));
        }
        refleshModel();
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
