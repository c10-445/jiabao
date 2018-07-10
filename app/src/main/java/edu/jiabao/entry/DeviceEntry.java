package edu.jiabao.entry;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.jiabao.dao.Dao;
import edu.jiabao.database.Device;
import edu.jiabao.jsonObject.DeviceDataBean;

public class DeviceEntry {
    private static List<DeviceEntry> devices;
    private int device_id;
    private boolean turn_on;
    private int degree;
    private int run_model;

    public DeviceEntry(int device_id, boolean turn_on, int degree, int run_model, int fan_state, int wind_size, int user_id, String name) {
        this.device_id = device_id;
        this.turn_on = turn_on;
        this.degree = degree;
        this.run_model = run_model;
        this.fan_state = fan_state;
        this.wind_size = wind_size;
        this.user_id = user_id;
        this.name = name;
    }

    private int fan_state;
    private int wind_size;
    private int user_id;
    private String name;

    public static boolean checkDeviceIsOn(int id){
        return getDevice(id).getTurn_on();
    }

    public static void DaoToEntry( List<Device> device){
        if(devices!=null&&!devices.isEmpty())
            devices.clear();
        else {
            getDevices();
        }
        DeviceEntry entry=new DeviceEntry();
        for (int i=0;i<device.size();i++){
            Device d= device.get(i);
            entry=toEntry(d);
            devices.add(entry);
        }
    }

    public static void EntryToDao(){
        Dao.DeviceInsertDevices(devices);
    }

    public static List<DeviceEntry> getDevices(){
        if(devices==null) {
            devices=new ArrayList<DeviceEntry>();
        }
        return devices;

    }

    public PackageEntry toPackage(int folder_parents){
        return new PackageEntry(-1,name,1,folder_parents,device_id,user_id,0,0);
    }

    public static void addDevice(DeviceEntry deviceEntry){
        devices.add(deviceEntry);
    }

    public static void setDevices(List<DeviceEntry> devices) {
        DeviceEntry.devices = devices;
    }

    public static void setDeviceByMap(Integer id,Map<Integer,DeviceDataBean> map){
        int d_id=id.intValue();
        DeviceEntry deviceEntry= getDevice(d_id);
        deviceEntry.setDevice(map.get(id));

    }

    public static void  setDevicesByOperator(OperatorEntry en){
        List<OperatorItemBean> beans=OperatorEntry.getBaseOperator(en);
        Log.i("baseOperator",String.valueOf(beans.size()));
        for (int i=0;i<beans.size();i++){
            OperatorItemBean bean=beans.get(i);
            int p_id=bean.getObjectId();
            int o_id=bean.getOperatorId();
            int para=bean.getParameter();
            int d_id= PackageEntry.getPackageById(p_id).getDevice_id();
            DeviceEntry entry= DeviceEntry.getDevice(d_id);
            switch (o_id){
                case 0:
                    boolean t_on=true;
                    if (para==0)
                        t_on=false;
                    entry.setTurn_on(t_on);
                    break;
                case 1:
                    entry.setRun_model(para);
                    break;
                case 2:
                    entry.setWind_size(para);
                    break;
                case 3:
                    entry.setDegree(para+20);
                    break;
            }
        }
    }

    public static DeviceEntry getDevice(int id) {
        for(int i=0;i<devices.size();i++){
            if(devices.get(i).getDevice_id()==id)
                return devices.get(i);
        }
        return new DeviceEntry();
    }

    public void setDevice(DeviceDataBean bean){
        setWind_size(bean.getFanState());
        setRun_model(bean.getRunMode());
        setDegree((int)bean.getOutWind());
        if(bean.getSwitchState()==0)
            setTurn_on(false);
        else
            setTurn_on(true);
    }

    public Device toDevice(){
        return new Device(new Long(device_id),
                        turn_on,
                        new Long(degree),
                        new Long(run_model),
                        new Long(fan_state),
                        new Long(wind_size),
                        new Long(user_id),
                        name);
    }

    public static DeviceEntry toEntry(Device d){
        return new DeviceEntry(d.getDevice_id().intValue(),
                                d.getTurn_on(),
                                d.getDegree().intValue(),
                                d.getRun_model().intValue(),
                                d.getFan_state().intValue(),
                                d.getWind_size().intValue(),
                                d.getUser_id().intValue(),
                                d.getDevice_name());
    }

    public DeviceEntry() {
    }

    public DeviceEntry(int _device_id,int _user_id,String name){
        this.device_id=_device_id;
        this.turn_on = false;
        this.degree = 26;
        this.run_model = 0;
        this.fan_state=0;
        this.wind_size=1;
        this.user_id = _user_id;
        this.name=name;
    }

    public DeviceEntry(int _user_id,String name){
        this.device_id=-1;
        this.turn_on = false;
        this.degree = 26;
        this.run_model = 0;
        this.fan_state=0;
        this.wind_size=1;
        this.user_id = _user_id;
        this.name=name;
    }
    public int getDevice_id() {
        return this.device_id;
    }
    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }
    public boolean getTurn_on() {
        return this.turn_on;
    }
    public void setTurn_on(boolean turn_on) {
        this.turn_on = turn_on;
    }
    public int getDegree() {
        return this.degree;
    }
    public void setDegree(int degree) {
        this.degree = degree;
    }
    public int getUser_id() {
        return this.user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public boolean isTurn_on() {
        return turn_on;
    }
    public int getRun_model() {
        return run_model;
    }
    public void setRun_model(int run_model) {
        this.run_model = run_model;
    }
    public int getFan_state() {
        return fan_state;
    }
    public void setFan_state(int fan_state) {
        this.fan_state = fan_state;
    }
    public int getWind_size() {
        return wind_size;
    }
    public void setWind_size(int wind_size) {
        this.wind_size = wind_size;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
