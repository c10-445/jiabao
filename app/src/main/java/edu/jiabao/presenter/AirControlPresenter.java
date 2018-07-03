package edu.jiabao.presenter;

import android.util.Log;

import org.xutils.common.Callback;

import edu.jiabao.database.Device;
import edu.jiabao.database.folderDao;
import edu.jiabao.http.Http;
import edu.jiabao.modle.ImpDecicePackageModel;
import edu.jiabao.modle.inteface.IdevicePackageModel;
import edu.jiabao.view.inteface.IAirControlView;

public class AirControlPresenter {
    int deviceId;
    int userId;
    Device device;
    IAirControlView view;
    IdevicePackageModel model;

    public AirControlPresenter(IAirControlView view,folderDao foldDao){
        super();
        model=new ImpDecicePackageModel(foldDao);
        this.view=view;
        deviceId=2;//ID需要初始化
        userId=1;
    }

    public void turnSwitchBtn() {
        int switchState=1;
        Http.turnSwitchBtn(deviceId,userId,switchState,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("success", "ok!");
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

        view.turnSwitchBtn();
       // model.operateDevice(jsonArr);
    }

    public void turnModelBtn() {

    }

    public void changeWindSize() {

    }

    public void changeWindDirection() {

    }

    public void changeWindSwip() {

    }

    public void addTemperature() {

    }

    public void reduceTemperature() {

    }

    public void setTiming() {

    }

    public void setSleeping() {

    }

    public void more() {

    }
}
