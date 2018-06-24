package edu.jiabao.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class folder {
    @Id
    private int folder_id;
    @Property
    private String folder_name;
    @Property
    private int folder_type;
    @Property
    private int folder_parents;
    @Property
    private int device_id;
    @Generated(hash = 420436254)
    public folder(int folder_id, String folder_name, int folder_type,
            int folder_parents, int device_id) {
        this.folder_id = folder_id;
        this.folder_name = folder_name;
        this.folder_type = folder_type;
        this.folder_parents = folder_parents;
        this.device_id = device_id;
    }
    @Generated(hash = 159136574)
    public folder() {
    }
    public int getFolder_id() {
        return this.folder_id;
    }
    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }
    public String getFolder_name() {
        return this.folder_name;
    }
    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }
    public int getFolder_type() {
        return this.folder_type;
    }
    public void setFolder_type(int folder_type) {
        this.folder_type = folder_type;
    }
    public int getFolder_parents() {
        return this.folder_parents;
    }
    public void setFolder_parents(int folder_parents) {
        this.folder_parents = folder_parents;
    }
    public int getDevice_id() {
        return this.device_id;
    }
    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }
}
