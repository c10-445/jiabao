package edu.jiabao.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Device {
    @Id
    private Long device_id;
    @Property
    private boolean turn_on;
    @Property
    private Long degree;
    @Property
    private Long run_model;
    @Property
    private Long fan_state;
    @Property
    private Long wind_size;;
    @Property
    private Long user_id;
    @Property
    private String device_name;
    @Generated(hash = 1515899771)
    public Device(Long device_id, boolean turn_on, Long degree, Long run_model,
            Long fan_state, Long wind_size, Long user_id, String device_name) {
        this.device_id = device_id;
        this.turn_on = turn_on;
        this.degree = degree;
        this.run_model = run_model;
        this.fan_state = fan_state;
        this.wind_size = wind_size;
        this.user_id = user_id;
        this.device_name = device_name;
    }
    @Generated(hash = 1469582394)
    public Device() {
    }
    public Long getDevice_id() {
        return this.device_id;
    }
    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }
    public boolean getTurn_on() {
        return this.turn_on;
    }
    public void setTurn_on(boolean turn_on) {
        this.turn_on = turn_on;
    }
    public Long getDegree() {
        return this.degree;
    }
    public void setDegree(Long degree) {
        this.degree = degree;
    }
    public Long getRun_model() {
        return this.run_model;
    }
    public void setRun_model(Long run_model) {
        this.run_model = run_model;
    }
    public Long getFan_state() {
        return this.fan_state;
    }
    public void setFan_state(Long fan_state) {
        this.fan_state = fan_state;
    }
    public Long getWind_size() {
        return this.wind_size;
    }
    public void setWind_size(Long wind_size) {
        this.wind_size = wind_size;
    }
    public Long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public String getDevice_name() {
        return this.device_name;
    }
    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }
   
   
}
