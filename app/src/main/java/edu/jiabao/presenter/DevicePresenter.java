package edu.jiabao.presenter;

import org.xutils.common.Callback;

import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.http.Http;
import edu.jiabao.modle.ImpDecicePackageModel;
import edu.jiabao.modle.ImpDeviceModel;
import edu.jiabao.modle.inteface.IDeviceModel;
import edu.jiabao.modle.inteface.IdevicePackageModel;
import edu.jiabao.view.inteface.IDeviceView;
public class DevicePresenter {
    IDeviceModel deviceModel;
    IdevicePackageModel packageModel;
    IDeviceView deviceView;
    Long userId;
    public DevicePresenter(IDeviceView view,Long userId){
        deviceView=view;
        deviceModel=new ImpDeviceModel();
        packageModel=new ImpDecicePackageModel();
        this.userId=userId;
    }

    public void initListView(String type){
        if(type.equals("label")) {
            deviceView.initListView(deviceModel.getDevices());
        }
    }

    public void deleteDevice(DeviceEntry entry){
        packageModel.deleteDevice(entry);
        deviceModel.deleteDevice(entry);
        deviceView.reflesh();
    }

    public void turnOnDevice(final DeviceEntry entry){
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
                deviceView.reflesh();
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
}
