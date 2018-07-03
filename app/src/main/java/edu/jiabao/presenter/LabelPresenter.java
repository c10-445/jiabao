package edu.jiabao.presenter;

import edu.jiabao.database.UserDao;
import edu.jiabao.modle.ImpUserModel;
import edu.jiabao.modle.inteface.IuserModel;
import edu.jiabao.view.inteface.ILabelView;

public class LabelPresenter {
    ILabelView view;
    IuserModel userModel;

    public LabelPresenter(ILabelView view,UserDao userDao){
        this.view=view;
        this.userModel=new ImpUserModel(userDao);
    }

    public void checkUserMsg() {
        view.checkUserMsg();
    }
}
