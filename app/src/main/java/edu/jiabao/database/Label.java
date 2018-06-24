package edu.jiabao.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Label {
    @Id
    private int label_id;
    @Property
    private String label_name;
    @Property
    private int label_type;
    @Property
    private int label_parents;
    @Property
    private int device_id;
    @Generated(hash = 158799033)
    public Label(int label_id, String label_name, int label_type, int label_parents,
            int device_id) {
        this.label_id = label_id;
        this.label_name = label_name;
        this.label_type = label_type;
        this.label_parents = label_parents;
        this.device_id = device_id;
    }
    @Generated(hash = 2137109701)
    public Label() {
    }
    public int getLabel_id() {
        return this.label_id;
    }
    public void setLabel_id(int label_id) {
        this.label_id = label_id;
    }
    public String getLabel_name() {
        return this.label_name;
    }
    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }
    public int getLabel_type() {
        return this.label_type;
    }
    public void setLabel_type(int label_type) {
        this.label_type = label_type;
    }
    public int getLabel_parents() {
        return this.label_parents;
    }
    public void setLabel_parents(int label_parents) {
        this.label_parents = label_parents;
    }
    public int getDevice_id() {
        return this.device_id;
    }
    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }
}
