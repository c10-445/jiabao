package edu.jiabao.presenter;

import android.util.Log;

import org.xutils.common.Callback;

import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.http.Http;
import edu.jiabao.modle.ImpDeviceModel;
import edu.jiabao.modle.inteface.IDeviceModel;
import edu.jiabao.view.inteface.IAirControlView;

public class AirControlPresenter {
    DeviceEntry device;
    IAirControlView view;
    IDeviceModel model;

    public AirControlPresenter(IAirControlView view, DeviceEntry entry){
        super();
        model=new ImpDeviceModel();
        this.view=view;
        device=entry;
    }

    public void turnSwitchBtn() {
        int switchState=1;
        if(device.getTurn_on()){//获取当前开关状态，若是开就关闭，反之亦然
            switchState=0;
        }
        Http.turnSwitchBtn(device.getDevice_id(),device.getUser_id(),switchState,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                device.setTurn_on(!device.getTurn_on());//修正现在的device开关状态
                model.refleshDeviceInfo(device);
                view.refleshView();
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

    }

    public void turnModelBtn() {
        int run_model=device.getRun_model()+1;
        if(run_model==5){
            run_model=0;
        }
        Http.turnModelBtn(device.getDevice_id(),device.getUser_id(),run_model,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                int run_model_=device.getRun_model()+1;
                if(run_model_==5){
                    run_model_=0;
                }
                device.setRun_model(run_model_);
                model.refleshDeviceInfo(device);
                view.refleshView();
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
    }

    public void changeWindSize() {
        int windSize=device.getWind_size()+1;
        if(windSize==5){
            windSize=0;
        }
        Http.changeWindSize(device.getDevice_id(),device.getUser_id(),windSize,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                int wind=device.getWind_size()+1;
                if(wind==5){
                    wind=0;
                }
                device.setWind_size(wind);
                model.refleshDeviceInfo(device);
                view.refleshView();
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

    public void changeWindDirection() {

    }

    public void changeWindSwip() {

    }

    public void addTemperature() {
        int tem=device.getDegree()+1;
        if(tem==31){
            tem=30;
        }
        Http.setTemperature(device.getDevice_id(),device.getUser_id(),tem,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                int tem_=device.getDegree()+1;
                if(tem_==31){
                    tem_=30;
                }
                device.setDegree(tem_);
                model.refleshDeviceInfo(device);
                view.refleshView();
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

    public void reduceTemperature() {
        int tem=device.getDegree()-1;
        if(tem==19){
            tem=20;
        }
        Http.setTemperature(device.getDevice_id(),device.getUser_id(),tem,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                int tem_=device.getDegree()-1;
                if(tem_==19){
                    tem_=20;
                }
                device.setDegree(tem_);
                model.refleshDeviceInfo(device);
                view.refleshView();
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

    public void setTiming() {

    }

    public void setSleeping() {

    }

    public void more() {

    }
}
