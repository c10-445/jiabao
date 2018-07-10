package edu.jiabao.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.http.Http;
import edu.jiabao.jsonObject.DeviceDataBean;
import edu.jiabao.jsonObject.DeviceId;
import edu.jiabao.jsonObject.DeviceState;
import edu.jiabao.jsonObject.HttpRequest;
import edu.jiabao.modle.ImpDecicePackageModel;
import edu.jiabao.modle.ImpDeviceModel;
import edu.jiabao.modle.ImpOperatorModel;
import edu.jiabao.modle.inteface.IDeviceModel;
import edu.jiabao.modle.inteface.IOperatorModel;
import edu.jiabao.modle.inteface.IdevicePackageModel;
import edu.jiabao.view.inteface.IHomeView;


public class HomePresenter {
    IHomeView view;
    IdevicePackageModel packageModel;
    IDeviceModel deviceModel;
    IOperatorModel operatorModel;

    public HomePresenter(IHomeView view){
        super();
        packageModel =new ImpDecicePackageModel();
        deviceModel=new ImpDeviceModel();
        operatorModel=new ImpOperatorModel();
        this.view=view;
    }

    public void showSwipWindow(){
        view.showSwipWindow();
    }

    public void addDevice(String deviceCode, final DeviceEntry entry, final int parents_id){
        Http.addDevice(entry.getUser_id(), deviceCode, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type jsonType = new TypeToken<HttpRequest<DeviceId>>() {}.getType();
                HttpRequest<DeviceId> rootBean = new Gson().fromJson(result, jsonType);
                int device_id=rootBean.getData().getDeviceId();
                DeviceEntry e=new DeviceEntry(device_id,entry.getUser_id(),entry.getName());
                packageModel.addDevice(e.toPackage(parents_id));
                deviceModel.addDevice(e);
                view.refleshListView(packageModel.getPackagesByParentsId(parents_id));
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

    public void addPackage(PackageEntry entry){
        packageModel.addPackage(entry);
        view.refleshListView(packageModel.getPackagesByParentsId(entry.getFolder_parents()));
    }

    public void deletePackage(PackageEntry entry){
        if(entry.getFolder_type()==1) {
            deviceModel.deleteDevice(entry);
        }
        packageModel.deletePackage(entry);//包含该包子操作的操作未被删除
        operatorModel.deleteOperatorByPackage(entry);
        view.refleshListView(packageModel.getPackagesByParentsId(entry.getFolder_parents()));
    }

    public List<OperatorEntry> getOperatorByPackage(PackageEntry entry){
        return operatorModel.getOperatorByPackage(entry);
    }

    public void showDevices(final DeviceEntry entry){
        final List<Integer> i_list=new ArrayList<Integer>();
        i_list.add(new Integer(entry.getDevice_id()));
        Http.getDevicesInfo(i_list, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type jsonType = new TypeToken<HttpRequest<DeviceState<Map<Integer,DeviceDataBean>>>>() {}.getType();
                HttpRequest<DeviceState<Map<Integer,DeviceDataBean>>> rootBean = new Gson().fromJson(result, jsonType);
                Map<Integer,DeviceDataBean> map= rootBean.getData().getDeviceStatus();
                deviceModel.initDevcie(i_list,map);
                view.showDeviceActivity(entry);
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

    public void turnOnPackage(final PackageEntry entry){
        List<Integer> list= PackageEntry.getDeviceIdsInPackage(entry);
        Log.i("treeList",String.valueOf(list.size()));
        int userId= entry.getUser_id();
        int switchState=1;
        if(entry.getFolder_device_on_num()>0)
            switchState=0;
        Http.turnOnDevicesInPackage(list, userId, switchState, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<DeviceEntry> l=PackageEntry.getDevicesInPackage(entry);
                boolean _switchState=true;
                if(entry.getFolder_device_on_num()>0)
                    _switchState=false;
                for (int i=0;i<l.size();i++){
                    l.get(i).setTurn_on(_switchState);
                    deviceModel.refleshDeviceInfo(l.get(i));
                }
                view.refleshListView(packageModel.getPackagesByParentsId(entry.getFolder_parents()));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void turnOnDevice(final DeviceEntry entry, final PackageEntry packageEntry){
        int switchState=1;
        if(entry.getTurn_on()){//获取当前开关状态，若是开就关闭，反之亦然
            switchState=0;
        }
        Http.turnSwitchBtn(entry.getDevice_id(),entry.getUser_id(),switchState,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                DeviceEntry device= DeviceEntry.getDevice(entry.getDevice_id());
                device.setTurn_on(!device.getTurn_on());//修正现在的device开关状态
                deviceModel.refleshDeviceInfo(device);
                view.refleshListView(packageModel.getPackagesByParentsId(packageEntry.getFolder_parents()));
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

    public void turnOnOperator(final OperatorEntry entry){
        Http.usePackageOperator(entry, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                deviceModel.setDevicesByOperator(entry);
                Log.i("operatorSize",String.valueOf(entry.getContent_list().size()));
                Log.i("http","success!");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("http","err!");
                ex.printStackTrace();

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i("http","cancel!");
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {
                ImpDecicePackageModel impDecicePackageModel=new ImpDecicePackageModel();
                impDecicePackageModel.refleshModel();
                PackageEntry entry1=PackageEntry.getPackageById(entry.getFolder_id());
                view.refleshListView(PackageEntry.getPackagesByParentsId(entry1.getFolder_parents()));
            }
        });
    }


    public void initHomeView(){
        List<DeviceEntry> deviceEntries= deviceModel.getDevices();
        final List<Integer> i_list=new ArrayList<Integer>();
        for (int i=0;i<deviceEntries.size();i++){
            i_list.add(new Integer(deviceEntries.get(i).getDevice_id()));
        }
        Http.getDevicesInfo(i_list, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Log.i("Http","ok");
                Type jsonType = new TypeToken<HttpRequest<DeviceState<Map<Integer,DeviceDataBean>>>>() {}.getType();
                HttpRequest<DeviceState<Map<Integer,DeviceDataBean>>> rootBean = new Gson().fromJson(result, jsonType);
                Map<Integer,DeviceDataBean> map= rootBean.getData().getDeviceStatus();
                deviceModel.initDevcie(i_list,map);

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
                view.initHomeListView(packageModel.getPackagesInRoot());
            }
        });
        view.initHomeListView(packageModel.getPackagesInRoot());
    }


    public void showPackage(PackageEntry entry){
        view.showPackageFragment(entry);
    }


}
