package edu.jiabao.presenter;

import edu.jiabao.modle.ImpUserModel;
import edu.jiabao.modle.inteface.IuserModel;
import edu.jiabao.view.inteface.ILabelView;

public class LabelPresenter {
    ILabelView view;
    IuserModel userModel;

    public LabelPresenter(ILabelView view){
        this.view=view;
        this.userModel=new ImpUserModel();
    }

    public void checkUserMsg() {
        view.checkUserMsg();
    }
}
