package edu.jiabao.presenter;

import edu.jiabao.database.UserDao;
import edu.jiabao.jsonObject.UserObject;
import edu.jiabao.modle.ImpUserModel;
import edu.jiabao.modle.inteface.IuserModel;
import edu.jiabao.view.inteface.ILoginView;

public class LoginPresenter {
    ILoginView view;
    IuserModel model;

    public LoginPresenter(ILoginView view, UserDao userDao){
        this.view=view;
        model=new ImpUserModel(userDao);
    }

    public void loginSuccess(UserObject userObject){
        Long user_id=userObject.getUserId();
        String phone_num=userObject.getPhoneNum();
        String nick_name=userObject.getNickname();
        boolean is_online=true;
        model.login(user_id,phone_num,nick_name,is_online);
        view.loginSucess();
    }

    public void loginFalse() {
        view.loginFalse();
    }

    public void register(){
        view.register();
    }
}
