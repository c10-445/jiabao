package edu.jiabao.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;

import java.lang.reflect.Type;

import edu.jiabao.database.DeviceDao;
import edu.jiabao.database.folderDao;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.http.Http;
import edu.jiabao.jsonObject.DeviceId;
import edu.jiabao.jsonObject.HttpRequest;
import edu.jiabao.modle.ImpDecicePackageModel;
import edu.jiabao.modle.ImpDeviceModel;
import edu.jiabao.modle.inteface.IDeviceModel;
import edu.jiabao.modle.inteface.IdevicePackageModel;
import edu.jiabao.view.inteface.IHomeView;


public class HomePresenter {
    IHomeView view;
    IdevicePackageModel packageModel;
    IDeviceModel deviceModel;

    public HomePresenter(IHomeView view, folderDao folderDao, DeviceDao deviceDao){
        super();
        packageModel =new ImpDecicePackageModel(folderDao);
        deviceModel=new ImpDeviceModel(deviceDao);
        this.view=view;
    }

    public void showSwipWindow(){
        view.showSwipWindow();
    }

    public void addDevice(final int userId, String deviceCode){
        Http.addDevice(userId, deviceCode, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type jsonType = new TypeToken<HttpRequest<DeviceId>>() {}.getType();
                HttpRequest<DeviceId> rootBean = new Gson().fromJson(result, jsonType);
                int device_id=rootBean.getData().getDeviceId();
                packageModel.addDevice();
                deviceModel.addDevice(new DeviceEntry(device_id,userId));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void addPackage(){

    }

    public void deletePackage(){
        packageModel.deletePackage(deviceModel);
    }

    public void showDevices(){

    }

    public void turnOnPackage(){

    }



}
