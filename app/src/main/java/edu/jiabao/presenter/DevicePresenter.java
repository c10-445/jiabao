package edu.jiabao.presenter;

import edu.jiabao.database.DeviceDao;
import edu.jiabao.modle.ImpDeviceModel;
import edu.jiabao.modle.inteface.IDeviceModel;
import edu.jiabao.view.inteface.IDeviceView;
public class DevicePresenter {
    IDeviceModel deviceModel;
    IDeviceView deviceView;
    Long userId;
    public DevicePresenter(IDeviceView view, DeviceDao deviceDao,Long userId){
        deviceView=view;
        deviceModel=new ImpDeviceModel(deviceDao);
        this.userId=userId;
    }

    public void initListView(String type){
        if(type.equals("label")) {
            deviceView.initListView(deviceModel.getDevices(userId));
        }
    }
}
