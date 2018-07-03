package edu.jiabao.modle;


import java.util.List;

import edu.jiabao.database.User;
import edu.jiabao.database.UserDao;
import edu.jiabao.entry.UserEntry;
import edu.jiabao.modle.inteface.IuserModel;

public class ImpUserModel implements IuserModel {
    private UserDao userDao;

    public static UserEntry getUser() {
        return UserEntry.getUserEntry();
    }

    public static void setUser(UserEntry userEntry) {
        UserEntry.setUserEntry(userEntry);
    }

    private UserEntry DaoToEntry(User user){
        return getUser().DaoToEntry(user);

    }

    private void EntryToDao(){
        userDao.insertOrReplace(getUser().EntryToDao());
    }

    public Long checkOnlineUser(){
        List<edu.jiabao.database.User> list= userDao.queryBuilder().where(UserDao.Properties.Is_online.eq(true)).list();
        if (!list.isEmpty()){
            DaoToEntry(list.get(0));
        }

        return getUser().getUser_id();
    }


    public ImpUserModel(UserDao userDao) {
        setUserDao(userDao);
    }
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
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
        UserEntry.setUser(user_id,phone_Num,nick_name,is_online);
        //写进数据库
        EntryToDao();
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
        EntryToDao();
        getUser().setUser_id(new Long(-1));
    }

}
