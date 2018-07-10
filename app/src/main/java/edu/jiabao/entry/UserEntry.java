package edu.jiabao.entry;


import edu.jiabao.database.User;

public class UserEntry {
    private static UserEntry userEntry;

    private Long user_id;
    private String phone_Num;
    private String nick_name;
    private boolean is_online;

    public User toUser(){
        return new User(userEntry.getUser_id(),
                userEntry.getPhone_Num(),
                    userEntry.getNick_name(),
                userEntry.getIs_online());
    }

    public static UserEntry getUserEntry() {
        if (userEntry == null) {
            userEntry = new UserEntry();
        }
        return userEntry;
    }

    public static void setUserEntry(Long user_id, String phone_Num, String nick_name,
                                    boolean is_online) {
        userEntry.user_id = user_id;
        userEntry.phone_Num = phone_Num;
        userEntry.nick_name = nick_name;
        userEntry.is_online = is_online;
    }

    public static void setUserEntry(UserEntry _userEntry){
        userEntry = _userEntry;
    }

    public static void setUserEntry(User user){
        userEntry.user_id = user.getUser_id();
        userEntry.phone_Num = user.getPhone_Num();
        userEntry.nick_name = user.getNick_name();
        userEntry.is_online = user.getIs_online();
    }

    private UserEntry(Long user_id, String phone_Num, String nick_name,
                      boolean is_online) {
        this.user_id = user_id;
        this.phone_Num = phone_Num;
        this.nick_name = nick_name;
        this.is_online = is_online;
    }

    private UserEntry() {
        user_id=new Long(-1);
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
