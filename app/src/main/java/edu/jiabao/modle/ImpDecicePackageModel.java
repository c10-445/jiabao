package edu.jiabao.modle;

import android.util.Log;

import com.google.gson.JsonArray;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import edu.jiabao.modle.inteface.IdevicePackageModel;

public class ImpDecicePackageModel implements IdevicePackageModel {
    @Override
    public void addPackage() {

    }

    @Override
    public void deletePackage() {

    }

    @Override
    public void addDevice() {

    }

    @Override
    public void deleteDevice() {

    }

    @Override
    public void operateDevice(JsonArray jsonObjects) {
        RequestParams params=new RequestParams("https://www.cjtellyou.xyz/control/device/controlDevice");//
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObjects.toString());
        params.addHeader("Content-Type", "application/json-rpc");
        //params.addBodyParameter("phoneNum","13427521110");
      // params.addBodyParameter("passwd","1");
        Log.i("http",jsonObjects.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("success","ok!");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Log.i("success","ok1");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i("success","ok2");
            }

            @Override
            public void onFinished() {
                Log.i("success","ok3");
            }
        });
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
