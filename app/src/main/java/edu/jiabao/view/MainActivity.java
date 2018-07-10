package edu.jiabao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.modle.ImpDecicePackageModel;
import edu.jiabao.modle.ImpDeviceModel;
import edu.jiabao.modle.ImpUserModel;
import edu.jiabao.modle.inteface.IDeviceModel;
import edu.jiabao.modle.inteface.IdevicePackageModel;
import edu.jiabao.presenter.MainPresenter;
import edu.jiabao.view.home.homeFragment;
import edu.jiabao.view.inteface.IMainView;
import edu.jiabao.view.label.labelFragment;
import edu.jiabao.view.timing.timingFragment;
import edu.jiabao.view.userManagement.AccountActivity;
import edu.jiabao.view.userManagement.LoginActivity;
import edu.jiabao.view.userManagement.SettingActivity;

public class MainActivity extends AppCompatActivity implements IMainView {
    private MainPresenter presenter;
    private int REQUEST_CODE_SCAN = 111;
    private FragmentManager fragmentManager;
    private Fragment homeFragment;
    private Fragment labelFragment;
    private Fragment timingFragment;
    private TextView name;
    private View headView;
    private NavigationView.OnNavigationItemSelectedListener leftNav=
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.userMag:
                            presenter.accountManage();
                            return true;
                        case R.id.help:
                            return true;
                        case R.id.logout:
                            presenter.logout();
                            return true;
                        case R.id.setting:
                            presenter.settingManage();
                            return true;
                    }
                    return false;
                }
            };

    private BottomNavigationView.OnNavigationItemSelectedListener bottonNav
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment("home");
                    return true;
                case R.id.navigation_label:
                    showFragment("label");
                    return true;
                case R.id.navigation_timing:
                    showFragment("timing");
                    return true;
            }
            return false;
        }
    };
    public ControlApplication getApp(){
        return (ControlApplication) getApplicationContext();
    }

    void showFragment(String name){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch (name){
            case "home":
                homeFragment=new homeFragment();
                fragmentTransaction.replace(R.id.fragment_layout,homeFragment,"home");
                fragmentTransaction.show(homeFragment);
                fragmentTransaction.commit();
                break;
            case "label":
                labelFragment=new labelFragment();
                fragmentTransaction.replace(R.id.fragment_layout,labelFragment,"label");
                fragmentTransaction.show(labelFragment);
                fragmentTransaction.commit();
                break;
            case "timing":
                timingFragment=new timingFragment();
                fragmentTransaction.replace(R.id.fragment_layout,timingFragment,"timing");
                fragmentTransaction.show(timingFragment);
                fragmentTransaction.commit();
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_container);

        init();


    }

    void init(){
        loginCheckAndInit();
        NavigationView leftnavigation=(NavigationView) findViewById(R.id.nav);
        leftnavigation.setNavigationItemSelectedListener(leftNav);

        headView= leftnavigation.inflateHeaderView(R.layout.nav_head);
        name=headView.findViewById(R.id.name);
        setName();

        presenter=new MainPresenter(this);

        homeFragment=new homeFragment();
        labelFragment=new labelFragment();
        timingFragment=new timingFragment();

        fragmentManager=this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout,homeFragment,"home");
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(bottonNav);
        navigation.setSelectedItemId(R.id.navigation_home);;
        navigation.setBackgroundColor(getResources().getColor(R.color.colorHoloBlue));
        navigation.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorHoloBlue));



    }

    public void setName(){
        if(ImpUserModel.getUser().getUser_id()!=-1) {
            name.setText(ImpUserModel.getUser().getNick_name());
        }
    }

    @Override
    public void logout() {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void accountManage() {
        Intent intent=new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void settingManage() {
        Intent intent=new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void loginCheckAndInit(){
        ImpUserModel impUserModel=new ImpUserModel();
        if(impUserModel.checkOnlineUser().intValue()==-1){//初始化用户
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        IDeviceModel deviceModel=new ImpDeviceModel();
        IdevicePackageModel packageModel=new ImpDecicePackageModel();
    }

}
