package edu.jiabao;

import android.app.Application;

import org.xutils.x;

import edu.jiabao.database.DaoMaster;
import edu.jiabao.database.DaoSession;
import edu.jiabao.database.DeviceDao;
import edu.jiabao.database.OperatorDao;
import edu.jiabao.database.TimingDao;
import edu.jiabao.database.UserDao;
import edu.jiabao.database.folderDao;

public class ControlApplication extends Application {
    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        initDatabaseDao();
        x.Ext.init(this);
    }

    public DaoSession getDaoSession(){ return daoSession; }

    public void setDaoSession(DaoSession dao){ daoSession=dao;}

    public UserDao getUserDao(){
        return daoSession.getUserDao();
    }

    public DeviceDao getDeviceDao(){
        return daoSession.getDeviceDao();
    }

    public folderDao getFolderDao(){
        return daoSession.getFolderDao();
    }

    public OperatorDao getOperatorDao(){
        return daoSession.getOperatorDao();
    }

    public TimingDao getTimingDao(){
        return daoSession.getTimingDao();
    }

    public void initDatabaseDao(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "control.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        setDaoSession(daoMaster.newSession());
    }
}
