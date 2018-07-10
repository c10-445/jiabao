package edu.jiabao.jsonObject;

public class DeviceState<T> {
    public T getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(T deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    /**
     * deviceStatus : {"1":{"switchState":1,"runMode":1,"fanState":2,"outWind":25.5},"2":{"switchState":1,"runMode":1,"fanState":0,"outWind":26},"3":{"switchState":1,"runMode":1,"fanState":2,"outWind":25},"4":{"switchState":0,"runMode":0,"fanState":0,"outWind":26}}
     */

    private T deviceStatus;

}
