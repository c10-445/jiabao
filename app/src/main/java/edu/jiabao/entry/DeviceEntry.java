package edu.jiabao.entry;


import java.util.ArrayList;
import java.util.List;

import edu.jiabao.database.Device;

public class DeviceEntry {
    private static List<DeviceEntry> devices;
    private int device_id;
    private boolean turn_on;
    private int degree;
    private int run_model;
    private int fan_state;


    private int wind_size;
    private int user_id;

    public static void DaoToEntry( List<Device> device){
        if(devices!=null&&!devices.isEmpty())
            devices.clear();
        else {
            getDevices();
        }
        DeviceEntry entry=new DeviceEntry();
        for (int i=0;i<device.size();i++){
            Device d= device.get(i);
            entry.setDevice_id(d.getDevice_id().intValue());
            entry.setDegree(d.getDegree().intValue());
            entry.setRun_model(d.getRun_model().intValue());
            entry.setFan_state(d.getFan_state().intValue());
            entry.setTurn_on(d.getTurn_on());
            entry.setUser_id(d.getUser_id().intValue());
            devices.add(entry);
        }
    }

    public static List<DeviceEntry> getDevices(){
        if(devices==null) {
            devices=new ArrayList<DeviceEntry>();
        }
        return devices;

    }

    public static void addDevice(DeviceEntry deviceEntry){
        devices.add(deviceEntry);
    }

    public static void setDevices(List<DeviceEntry> devices) {
        DeviceEntry.devices = devices;
    }

    public static DeviceEntry getDevice(int id) {
        for(int i=0;i<devices.size();i++){
            if(devices.get(i).getDevice_id()==id)
                return devices.get(i);
        }
        return new DeviceEntry();
    }

    public Device toDevice(){
        return new Device(new Long(device_id),
                        turn_on,
                        new Long(degree),
                        new Long(run_model),
                        new Long(fan_state),
                        new Long(wind_size),
                        new Long(user_id));
    }

    public DeviceEntry(int device_id, boolean turn_on, int degree, int pattern,
                  int user_id) {
        this.device_id = device_id;
        this.turn_on = turn_on;
        this.degree = degree;
        this.run_model = pattern;
        this.user_id = user_id;
    }
    public DeviceEntry() {
    }

    public DeviceEntry(int _device_id,int _user_id){
        this.device_id=_device_id;
        this.turn_on = false;
        this.degree = 26;
        this.run_model = 0;
        this.fan_state=0;
        this.wind_size=1;
        this.user_id = _user_id;
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

}
