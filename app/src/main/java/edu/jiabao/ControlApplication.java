package edu.jiabao;

import android.app.Application;

import org.xutils.x;

public class ControlApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
