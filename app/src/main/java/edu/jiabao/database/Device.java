package edu.jiabao.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Device {
    @Id
    private int device_id;
    @Property
    private boolean turn_on;
    @Property
    private int degree;
    @Property
    private int pattern;
    @Generated(hash = 815876759)
    public Device(int device_id, boolean turn_on, int degree, int pattern) {
        this.device_id = device_id;
        this.turn_on = turn_on;
        this.degree = degree;
        this.pattern = pattern;
    }
    @Generated(hash = 1469582394)
    public Device() {
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
    public int getPattern() {
        return this.pattern;
    }
    public void setPattern(int pattern) {
        this.pattern = pattern;
    }
}
