package edu.jiabao.jsonObject;

import java.util.List;

public class tasksInformation {
    private List<TasksInfoBean> tasksInfo;

    public List<TasksInfoBean> getTasksInfo() {
        return tasksInfo;
    }

    public void setTasksInfo(List<TasksInfoBean> tasksInfo) {
        this.tasksInfo = tasksInfo;
    }

    public static class TasksInfoBean {
        /**
         * deviceId : 2
         * switchState : 1
         * runMode : 1
         * fanMode : 2
         * temperature : 26.26
         * taskId : 5
         * taskName : aaaa
         * executeTime : 1529738200
         */

        private int deviceId;
        private String switchState;
        private String runMode;
        private String fanMode;
        private double temperature;
        private int taskId;
        private String taskName;
        private int executeTime;

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public String getSwitchState() {
            return switchState;
        }

        public void setSwitchState(String switchState) {
            this.switchState = switchState;
        }

        public String getRunMode() {
            return runMode;
        }

        public void setRunMode(String runMode) {
            this.runMode = runMode;
        }

        public String getFanMode() {
            return fanMode;
        }

        public void setFanMode(String fanMode) {
            this.fanMode = fanMode;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public int getExecuteTime() {
            return executeTime;
        }

        public void setExecuteTime(int executeTime) {
            this.executeTime = executeTime;
        }
    }
}
