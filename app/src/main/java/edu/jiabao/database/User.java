package edu.jiabao.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id
    private Long user_id;
    @Property
    private String phone_Num;
    @Property
    private String nick_name;
    @Property
    private boolean is_online;
    @Generated(hash = 24539)
    public User(Long user_id, String phone_Num, String nick_name,
            boolean is_online) {
        this.user_id = user_id;
        this.phone_Num = phone_Num;
        this.nick_name = nick_name;
        this.is_online = is_online;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public String getPhone_Num() {
        return this.phone_Num;
    }
    public void setPhone_Num(String phone_Num) {
        this.phone_Num = phone_Num;
    }
    public String getNick_name() {
        return this.nick_name;
    }
    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
    public boolean getIs_online() {
        return this.is_online;
    }
    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

}
