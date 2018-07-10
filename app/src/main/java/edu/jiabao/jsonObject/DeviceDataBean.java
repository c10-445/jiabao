package edu.jiabao.jsonObject;

public  class DeviceDataBean {
    /**
     * switchState : 1
     * runMode : 1
     * fanState : 2
     * outWind : 25.5
     */

    private int switchState;
    private int runMode;
    private int fanState;
    private double outWind;

    public int getSwitchState() {
        return switchState;
    }

    public void setSwitchState(int switchState) {
        this.switchState = switchState;
    }

    public int getRunMode() {
        return runMode;
    }

    public void setRunMode(int runMode) {
        this.runMode = runMode;
    }

    public int getFanState() {
        return fanState;
    }

    public void setFanState(int fanState) {
        this.fanState = fanState;
    }

    public double getOutWind() {
        return outWind;
    }

    public void setOutWind(double outWind) {
        this.outWind = outWind;
    }
}
