package edu.jiabao.modle.inteface;

public interface IuserModel {
    //注册
    public void register();
    //登陆
    public void login(Long user_id, String phone_Num, String nick_name,
                      boolean is_online) ;
    //修改密码
    public void changePassword();
    //查询历史信息
    public void checkHistoryMsg();
    //退出登陆
    public void logout();

    public Long getUserId();

    public String getUserNickName();

    public String getUserPhoneNum();


}
