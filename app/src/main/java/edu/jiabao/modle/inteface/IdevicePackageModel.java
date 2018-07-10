package edu.jiabao.modle.inteface;

import com.google.gson.JsonArray;

import java.util.List;

import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.PackageEntry;

public interface IdevicePackageModel {
    //添加包
    public void addPackage(PackageEntry entry);
    //删除包
    public void deletePackage(PackageEntry packageEntry);
    //添加设备
    public void addDevice(PackageEntry packageEntry);
    //删除设备
    public void deleteDevice(DeviceEntry entry);
    //操作设备
    public void operateDevice(JsonArray jsonObjects);
    //添加操作
    public void addOperator();
    //删除操作
    public void deleteOperator();
    //操作包
    public void operatePackage();

    public List<PackageEntry> getPackages();

    public List<PackageEntry> getPackagesInPackage(PackageEntry entry);

    public List<PackageEntry> getPackagesInRoot();

    public PackageEntry getPackageById(int id);

    public List<PackageEntry> getPackagesByParentsId(int parentsId);
}


