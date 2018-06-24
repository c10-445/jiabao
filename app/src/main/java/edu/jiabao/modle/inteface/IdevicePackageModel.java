package edu.jiabao.modle.inteface;

import com.google.gson.JsonArray;

public interface IdevicePackageModel {
    //添加包
    public void addPackage();
    //删除包
    public void deletePackage();
    //添加设备
    public void addDevice();
    //删除设备
    public void deleteDevice();
    //操作设备
    public void operateDevice(JsonArray jsonObjects);
    //添加操作
    public void addOperator();
    //删除操作
    public void deleteOperator();
    //操作包
    public void operatePackage();
}
