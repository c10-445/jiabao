package edu.jiabao.jsonObject;

import java.util.List;

import edu.jiabao.database.Device;
import edu.jiabao.database.Operator;
import edu.jiabao.database.User;
import edu.jiabao.database.folder;

public class HttpDataBase {
    private User user;
    private List<Device> devices;
    private List<folder> folders;
    private List<Operator> operators;

    public HttpDataBase(User user, List<Device> devices, List<folder> folders, List<Operator> operators) {
        this.user = user;
        this.devices = devices;
        this.folders = folders;
        this.operators = operators;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<folder> getFolders() {
        return folders;
    }

    public void setFolders(List<folder> folders) {
        this.folders = folders;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

}
