package edu.jiabao.presenter;

import edu.jiabao.modle.inteface.IMainModel;
import edu.jiabao.modle.MainModel;
import edu.jiabao.view.inteface.IMainView;

public class MainPresenter {
    IMainView view;
    IMainModel model;

    public MainPresenter(IMainView mainView){
        view=mainView;
        model=new MainModel();
    }
}
