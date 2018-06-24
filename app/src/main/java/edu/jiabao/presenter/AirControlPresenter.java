package edu.jiabao.presenter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.jiabao.modle.ImpDecicePackageModel;
import edu.jiabao.modle.inteface.IdevicePackageModel;
import edu.jiabao.view.inteface.IAirControlView;

public class AirControlPresenter {
    IAirControlView view;
    IdevicePackageModel model;

    public AirControlPresenter(IAirControlView view){
        super();
        model=new ImpDecicePackageModel();
        this.view=view;
    }

    public void turnSwitchBtn() {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("deviceId","2");
        jsonObject.addProperty("switchState",1);
        jsonObject.addProperty("userId",1);
        JsonArray jsonArray=new JsonArray();
        jsonArray.add(jsonObject);
        view.turnSwitchBtn();
        model.operateDevice(jsonArray);
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
