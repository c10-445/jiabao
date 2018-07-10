package edu.jiabao.http;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;

import edu.jiabao.dao.Dao;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.OperatorItemBean;
import edu.jiabao.entry.PackageEntry;

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
        //Log.i("json_on",jsonArray.toString());
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
        x.http().post(params,callback);
    }

    public static void turnOnDevicesInPackage(List<Integer> deviceIds, int userId,int switchState, Callback.CommonCallback<String> callback){
        if (deviceIds.isEmpty())
            return;
        JsonArray jsonArray = new JsonArray();

        for (int i=0;i<deviceIds.size();i++) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("deviceId", deviceIds.get(i));
            jsonObject.addProperty("switchState", switchState);
            jsonObject.addProperty("userId", userId);
            jsonArray.add(jsonObject);

        }
        RequestParams params=new RequestParams(ip+"/control/device/controlDevice");
        params.setAsJsonContent(true);
        params.setBodyContent(jsonArray.toString());
        params.addHeader("Content-Type", "application/json-rpc");
        x.http().post(params,callback);
    }

    public static void changeWindSize(int deviceId,int userId,int windSize,Callback.CommonCallback<String> callback) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("deviceId",deviceId);
        jsonObject.addProperty("fanModel",windSize);
        jsonObject.addProperty("userId",userId);

        JsonArray jsonArray=new JsonArray();
        jsonArray.add(jsonObject);

        RequestParams params=new RequestParams(ip+"/control/device/controlDevice");
        params.setAsJsonContent(true);
        params.setBodyContent(jsonArray.toString());
        params.addHeader("Content-Type", "application/json-rpc");
        x.http().post(params,callback);
    }

    public static void setTemperature(int deviceId,int userId,int tem,Callback.CommonCallback<String> callback) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("deviceId",deviceId);
        jsonObject.addProperty("temperature",tem);
        jsonObject.addProperty("userId",userId);

        JsonArray jsonArray=new JsonArray();
        jsonArray.add(jsonObject);

        RequestParams params=new RequestParams(ip+"/control/device/controlDevice");
        params.setAsJsonContent(true);
        params.setBodyContent(jsonArray.toString());
        params.addHeader("Content-Type", "application/json-rpc");
        x.http().post(params,callback);
    }

    public static void usePackageOperator(OperatorEntry entry,Callback.CommonCallback<String> callback){
        JsonArray jsonArray=new JsonArray();
        List<OperatorItemBean> list= OperatorEntry.getBaseOperator(entry);
        for (int i=0;i<list.size();i++){
            OperatorItemBean bean= list.get(i);
            JsonObject jsonObject=new JsonObject();
            PackageEntry packageEntry=PackageEntry.getPackageById(bean.getObjectId());
            jsonObject.addProperty("userId",entry.getUser_id());
            jsonObject.addProperty("deviceId",packageEntry.getDevice_id());
            switch (bean.getOperatorId()){
                case 0:
                    jsonObject.addProperty("switchState", bean.getParameter());
                    Log.i("Http_Switch",String.valueOf(bean.getParameter()));
                    break;
                case 1:
                    jsonObject.addProperty("runMode",bean.getParameter());
                    break;
                case 2:
                    jsonObject.addProperty("fanModel",bean.getParameter());
                    break;
                case 3:
                    jsonObject.addProperty("temperature",bean.getParameter()+20);
                    break;
            }
            jsonArray.add(jsonObject);
        }

        RequestParams params=new RequestParams(ip+"/control/device/controlDevice");
        params.setAsJsonContent(true);
        params.setBodyContent(jsonArray.toString());
        params.addHeader("Content-Type", "application/json-rpc");
        //Log.i("http",jsonArray.toString());
        x.http().post(params,callback);
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

    public static void getDevicesInfo(List<Integer> list, Callback.CommonCallback<String> callback){
        if(list.isEmpty()){
            return;
        }
        RequestParams params=new RequestParams(ip+"/control/device/getDeviceInfo");
        for (int i=0;i<list.size();i++) {
            params.addBodyParameter("deviceIds", String.valueOf(list.get(i).intValue()));
            //Log.i("json_check",String.valueOf(list.get(i).intValue()));
        }

        x.http().get(params,callback);
    }

    public static void uploadFile(int userId,Context context, Callback.CommonCallback<String> callback){
        RequestParams params=new RequestParams(ip+"/control/device/uploadFile");
        params.setHeader("Content-Type","form-data");
        params.addBodyParameter("userId",String.valueOf(userId));

        File file= Dao.getDatabase(userId, context);
        params.addBodyParameter("file",file);
        x.http().post(params, callback);
    }

    public static void downFile(int userId,Context context,Callback.ProgressCallback<File> callback){
        RequestParams params = new RequestParams("https://control-1253647932.cosgz.myqcloud.com/"+String.valueOf(userId)+".json");
        params.setSaveFilePath(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)+String.valueOf(userId)+".json");
        Log.i("packageName","https://control-1253647932.cosgz.myqcloud.com/"+String.valueOf(userId)+".json");
        params.setAutoRename(true);
        x.http().get(params,callback);
    }

}
