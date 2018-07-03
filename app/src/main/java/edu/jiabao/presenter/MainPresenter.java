package edu.jiabao.presenter;

import edu.jiabao.database.UserDao;
import edu.jiabao.modle.ImpUserModel;
import edu.jiabao.modle.inteface.IuserModel;
import edu.jiabao.view.inteface.IMainView;

public class MainPresenter {
    IMainView view;
    IuserModel userModel;


    public MainPresenter(IMainView mainView, UserDao userDao){
        view=mainView;
        userModel=new ImpUserModel(userDao);
    }

    public void logout(){
        userModel.logout();
        view.logout();
    }

    public void settingManage(){
        view.settingManage();
    }

    public void accountManage(){
        view.accountManage();
    }
}
