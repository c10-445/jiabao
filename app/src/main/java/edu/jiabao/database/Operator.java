package edu.jiabao.database;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.List;

@Entity
public class Operator {
    @Id
    private int operator_id;
    @Property
    private String operator_name;
    @Property
    private int folder_id;
    @Property
    private int user_id;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> content_list;
    @Generated(hash = 1366637317)
    public Operator(int operator_id, String operator_name, int folder_id,
            int user_id, List<String> content_list) {
        this.operator_id = operator_id;
        this.operator_name = operator_name;
        this.folder_id = folder_id;
        this.user_id = user_id;
        this.content_list = content_list;
    }
    @Generated(hash = 1412551650)
    public Operator() {
    }
    public int getOperator_id() {
        return this.operator_id;
    }
    public void setOperator_id(int operator_id) {
        this.operator_id = operator_id;
    }
    public String getOperator_name() {
        return this.operator_name;
    }
    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }
    public int getFolder_id() {
        return this.folder_id;
    }
    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }
    public List<String> getContent_list() {
        return this.content_list;
    }
    public void setContent_list(List<String> content_list) {
        this.content_list = content_list;
    }
    public int getUser_id() {
        return this.user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
