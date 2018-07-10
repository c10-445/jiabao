package edu.jiabao.modle;


import java.util.List;

import edu.jiabao.dao.Dao;
import edu.jiabao.entry.UserEntry;
import edu.jiabao.modle.inteface.IuserModel;

public class ImpUserModel implements IuserModel {
    public static UserEntry getUser() {
        return UserEntry.getUserEntry();
    }

    public static void setUser(UserEntry userEntry) {
        UserEntry.setUserEntry(userEntry);
    }



    public Long checkOnlineUser(){
        List<edu.jiabao.database.User> list= Dao.UserQueryOnline();
        if (!list.isEmpty()){
            UserEntry.setUserEntry(list.get(0));
        }

        return getUser().getUser_id();
    }


    public ImpUserModel() {
        getUser();
    }

    @Override
    public Long getUserId(){
        return UserEntry.getUserEntry().getUser_id();
    }
    @Override
    public String getUserNickName(){
        return UserEntry.getUserEntry().getNick_name();
    }
    @Override
    public String getUserPhoneNum(){
        return UserEntry.getUserEntry().getPhone_Num();
    }


    @Override
    public void register() {

    }

    //成功登陆
    @Override
    public void login(Long user_id, String phone_Num, String nick_name,
                      boolean is_online) {
        UserEntry.setUserEntry(user_id,phone_Num,nick_name,is_online);
        //写进数据库
        Dao.UserInsertUser(getUser().toUser());
    }

    @Override
    public void changePassword() {

    }

    @Override
    public void checkHistoryMsg() {

    }

    @Override
    public void logout() {
        getUser().setIs_online(false);
        Dao.UserInsertUser(getUser().toUser());
        getUser().setUser_id(new Long(-1));
    }

}
