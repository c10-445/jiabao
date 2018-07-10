package edu.jiabao.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class folder {
    @Id(autoincrement = true)
    private Long folder_id;
    @Property
    private String folder_name;
    @Property
    private Long folder_type;
    @Property
    private Long folder_parents;
    @Property
    private Long device_id;
    @Property
    private Long user_id;
    @Generated(hash = 228883004)
    public folder(Long folder_id, String folder_name, Long folder_type,
            Long folder_parents, Long device_id, Long user_id) {
        this.folder_id = folder_id;
        this.folder_name = folder_name;
        this.folder_type = folder_type;
        this.folder_parents = folder_parents;
        this.device_id = device_id;
        this.user_id = user_id;
    }
    @Generated(hash = 159136574)
    public folder() {
    }
    public Long getFolder_id() {
        return this.folder_id;
    }
    public void setFolder_id(Long folder_id) {
        this.folder_id = folder_id;
    }
    public String getFolder_name() {
        return this.folder_name;
    }
    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }
    public Long getFolder_type() {
        return this.folder_type;
    }
    public void setFolder_type(Long folder_type) {
        this.folder_type = folder_type;
    }
    public Long getFolder_parents() {
        return this.folder_parents;
    }
    public void setFolder_parents(Long folder_parents) {
        this.folder_parents = folder_parents;
    }
    public Long getDevice_id() {
        return this.device_id;
    }
    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }
    public Long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

}
