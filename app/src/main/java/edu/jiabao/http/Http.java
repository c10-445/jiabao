package edu.jiabao.http;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Http {
    private static String ip="https://www.cjtellyou.xyz";

    public Http(){

    }

    public static void turnSwitchBtn(int deviceId, int userId,int switchState, Callback.CommonCallback<String> callback){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("deviceId",deviceId);
        jsonObject.addProperty("switchState",switchState);
        jsonObject.addProperty("userId",userId);

        JsonArray jsonArray=new JsonArray();
        jsonArray.add(jsonObject);

        RequestParams params=new RequestParams(ip+"/control/device/controlDevice");
        params.setAsJsonContent(true);
        params.setBodyContent(jsonArray.toString());
        params.addHeader("Content-Type", "application/json-rpc");
      //  Log.i("http",jsonArray.toString());
        x.http().post(params,callback);
    }


    public static void turnModelBtn(int deviceId, int userId,int model, Callback.CommonCallback<String> callback){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("deviceId",deviceId);
        jsonObject.addProperty("runMode",model);
        jsonObject.addProperty("userId",userId);

        JsonArray jsonArray=new JsonArray();
        jsonArray.add(jsonObject);

        RequestParams params=new RequestParams(ip+"/control/device/controlDevice");
        params.setAsJsonContent(true);
        params.setBodyContent(jsonArray.toString());
        params.addHeader("Content-Type", "application/json-rpc");
        //  Log.i("http",jsonArray.toString());
        x.http().post(params,callback);
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

    public static void addDevice(int userId,String deviceCode, Callback.CommonCallback<String> callback){
        String _userId=String.valueOf(userId);
        RequestParams params=new RequestParams(ip+"/control/device/addDevice");
        params.addBodyParameter("userId",_userId);
        params.addBodyParameter("deviceCode",deviceCode);

        x.http().post(params,callback);
    }


    public static void login(String phoneNum,String password, Callback.CommonCallback<String> callback){
        RequestParams params=new RequestParams(ip+"/control/user/login");
        params.addBodyParameter("phoneNum",phoneNum);
        params.addBodyParameter("passwd",password);

        x.http().get(params,callback);
    }

    public static void register(String nickname,String phoneNum,String password, Callback.CommonCallback<String> callback){
        RequestParams params=new RequestParams(ip+"/control/user/register");
        params.addBodyParameter("nickname",nickname);
        params.addBodyParameter("phoneNum",phoneNum);
        params.addBodyParameter("passwd",password);

        x.http().get(params,callback);
    }

    public static void modifyPassword(int userId,String oldPassword,String newPassword, Callback.CommonCallback<String> callback){
        RequestParams params=new RequestParams(ip+"/control/user/resetPassword");
        params.addBodyParameter("userId",String.valueOf(userId));
        params.addBodyParameter("oldPassword",oldPassword);
        params.addBodyParameter("newPassword",newPassword);

        x.http().get(params,callback);
    }

}
