package edu.jiabao.database;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Timing {
    @Id
    private int timing_id;
    @Property
    private String timing_name;
    @Property
    private int Timing_type;
    @Property
    private int device_id;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> operator_list;
    @Property
    private Date time;
    @Generated(hash = 918883012)
    public Timing(int timing_id, String timing_name, int Timing_type, int device_id,
            List<String> operator_list, Date time) {
        this.timing_id = timing_id;
        this.timing_name = timing_name;
        this.Timing_type = Timing_type;
        this.device_id = device_id;
        this.operator_list = operator_list;
        this.time = time;
    }
    @Generated(hash = 804033153)
    public Timing() {
    }
    public int getTiming_id() {
        return this.timing_id;
    }
    public void setTiming_id(int timing_id) {
        this.timing_id = timing_id;
    }
    public String getTiming_name() {
        return this.timing_name;
    }
    public void setTiming_name(String timing_name) {
        this.timing_name = timing_name;
    }
    public int getTiming_type() {
        return this.Timing_type;
    }
    public void setTiming_type(int Timing_type) {
        this.Timing_type = Timing_type;
    }
    public int getDevice_id() {
        return this.device_id;
    }
    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }
    public List<String> getOperator_list() {
        return this.operator_list;
    }
    public void setOperator_list(List<String> operator_list) {
        this.operator_list = operator_list;
    }
    public Date getTime() {
        return this.time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
}
