package edu.jiabao.database;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import java.util.List;

@Entity
public class Timing {
    @Id(autoincrement = true)
    private Long timing_id;
    @Property
    private String timing_name;
    @Property
    private boolean is_on;
    @Property
    private Long user_id;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> operator_list;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> task_id_list;
    @Property
    private Date time;
    @Generated(hash = 1304218388)
    public Timing(Long timing_id, String timing_name, boolean is_on, Long user_id,
            List<String> operator_list, List<String> task_id_list, Date time) {
        this.timing_id = timing_id;
        this.timing_name = timing_name;
        this.is_on = is_on;
        this.user_id = user_id;
        this.operator_list = operator_list;
        this.task_id_list = task_id_list;
        this.time = time;
    }
    @Generated(hash = 804033153)
    public Timing() {
    }
    public Long getTiming_id() {
        return this.timing_id;
    }
    public void setTiming_id(Long timing_id) {
        this.timing_id = timing_id;
    }
    public String getTiming_name() {
        return this.timing_name;
    }
    public void setTiming_name(String timing_name) {
        this.timing_name = timing_name;
    }
    public boolean getIs_on() {
        return this.is_on;
    }
    public void setIs_on(boolean is_on) {
        this.is_on = is_on;
    }
    public Long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public List<String> getOperator_list() {
        return this.operator_list;
    }
    public void setOperator_list(List<String> operator_list) {
        this.operator_list = operator_list;
    }
    public List<String> getTask_id_list() {
        return this.task_id_list;
    }
    public void setTask_id_list(List<String> task_id_list) {
        this.task_id_list = task_id_list;
    }
    public Date getTime() {
        return this.time;
    }
    public void setTime(Date time) {
        this.time = time;
    }

}
