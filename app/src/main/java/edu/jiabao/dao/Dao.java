package edu.jiabao.dao;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import edu.jiabao.database.DaoSession;
import edu.jiabao.database.Device;
import edu.jiabao.database.DeviceDao;
import edu.jiabao.database.Operator;
import edu.jiabao.database.OperatorDao;
import edu.jiabao.database.TimingDao;
import edu.jiabao.database.User;
import edu.jiabao.database.UserDao;
import edu.jiabao.database.folder;
import edu.jiabao.database.folderDao;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.jsonObject.HttpDataBase;

public class Dao {

    public static Dao getDao() {
        return dao;
    }

    public static void setDao(Dao dao) {
        Dao.dao = dao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    public edu.jiabao.database.folderDao getFolderDao() {
        return folderDao;
    }

    public void setFolderDao(edu.jiabao.database.folderDao folderDao) {
        this.folderDao = folderDao;
    }

    public OperatorDao getOperatorDao() {
        return operatorDao;
    }

    public void setOperatorDao(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }

    public TimingDao getTimingDao() {
        return timingDao;
    }

    public void setTimingDao(TimingDao timingDao) {
        this.timingDao = timingDao;
    }




    private static Dao dao;
    private UserDao userDao;
    private DeviceDao deviceDao;
    private folderDao folderDao;
    private OperatorDao operatorDao;
    private TimingDao timingDao;

    public static void initDao(DaoSession daoSession){
        dao=new Dao();
        getDao().setDeviceDao(daoSession.getDeviceDao());
        getDao().setFolderDao(daoSession.getFolderDao());
        getDao().setOperatorDao(daoSession.getOperatorDao());
        getDao().setTimingDao(daoSession.getTimingDao());
        getDao().setUserDao(daoSession.getUserDao());
    }

    private Dao(){}

    public static List<User> UserQueryOnline(){
        return dao.getUserDao().queryBuilder().where(UserDao.Properties.Is_online.eq(true)).list();
    }

    public static void UserInsertUser(User user){
        dao.getUserDao().insertOrReplace(user);
    }

    public static List<Device> DeviceQueryUserDevice(Long userId){
        return dao.getDeviceDao().queryBuilder().where(DeviceDao.Properties.User_id.eq(userId)).list();
    }

    public static void DeviceInsertDevices(List<DeviceEntry> devices){
        for(int i=0;i<devices.size();i++){
            dao.getDeviceDao().insertOrReplace(devices.get(i).toDevice());
        }
    }

    public static void DeviceAddDevice(Device device){
        dao.getDeviceDao().insertOrReplace(device);
    }


    public static void DeviceDeleteDevice(Device device){
        dao.getDeviceDao().delete(device);
    }

    public static void DeviceRefleshDeviceInfo(DeviceEntry entry){
        dao.getDeviceDao().insertOrReplace(entry.toDevice());
    }

    public static void DeviceDeleteDevice(PackageEntry entry){
        dao.getDeviceDao().deleteByKey(new Long(entry.getDevice_id()));
    }

    public static boolean DeviceQueryDeviceIsOn(Long deviceId){
        return dao.getDeviceDao().queryBuilder().where(DeviceDao.Properties.Device_id.eq(deviceId)).list().get(0).getTurn_on();
    }

    public static void PackInsertPack(folder folder){
        if(folder.getFolder_type().equals(new Long(1))) {
            int i= dao.getFolderDao().queryBuilder().where(edu.jiabao.database.folderDao.Properties.Device_id.eq(folder.getDevice_id())).list().size();
            if(i!=0){
                return;
            }
        }
        dao.getFolderDao().insert(folder);
    }

    public static void PackDeleteDevice(folder folder){
        dao.getFolderDao().delete(folder);
    }

    public static List<folder> PackQueryByDevice(DeviceEntry entry){
        return dao.getFolderDao().queryBuilder().where(edu.jiabao.database.folderDao.Properties.Device_id.eq(new Long(entry.getDevice_id()))).list();
    }


    public static void PackDeleteByKey(PackageEntry entry){
        Long fold_id=new Long(entry.getFolder_id());
        Long fold_parents=new Long(entry.getFolder_parents());
        List<folder> list= dao.getFolderDao().queryBuilder().where(edu.jiabao.database.folderDao.Properties.Folder_parents.eq(fold_id)).list();
        if(!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setFolder_parents(fold_parents);
            }
            dao.getFolderDao().updateInTx(list);
        }

        dao.getFolderDao().deleteByKey(fold_id);
    }

    public static List<folder> PackQueryAllPack(Long userId){
        return dao.getFolderDao().queryBuilder().where(edu.jiabao.database.folderDao.Properties.User_id.eq(userId)).list();
    }

    public static List<Operator> OperatorQueryAllOperator(Long userId){
        return dao.getOperatorDao().queryBuilder().where(OperatorDao.Properties.User_id.eq(userId)).list();
    }

    public static void OperatorAddOperator(Operator operator){
        dao.getOperatorDao().insertOrReplace(operator);
    }

    public static void OperatorDeleteOperatorByPackage(List<OperatorEntry> list){
        for (int i=0;i<list.size();i++){
            dao.getOperatorDao().delete(list.get(i).toOperator());
        }

    }

    public static File getDatabase(int userId,Context context){
        List<Device> devices= Dao.DeviceQueryUserDevice(new Long(userId));
        List<folder> folders=Dao.PackQueryAllPack(new Long(userId));
        User user=Dao.UserQueryOnline().get(0);
        List<Operator> operators=Dao.OperatorQueryAllOperator(new Long(userId));
        HttpDataBase httpDataBase=new HttpDataBase(user,devices,folders,operators);
        Gson gson=new Gson();

        String s=gson.toJson(httpDataBase,HttpDataBase.class);
        Log.i("json_s",s);
        FileOutputStream out;
        File file=new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),String.valueOf(userId)+".json");
        try {
            out= new FileOutputStream(file);
            byte [] bytes = s.getBytes();
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return file;
    }

    public static void initDatabaseByFile(File file) {
        String res="";
        try {
            FileInputStream fin = new FileInputStream(file);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            res = new String(buffer);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson=new Gson();
        HttpDataBase dataBase=gson.fromJson(res,HttpDataBase.class);
        Log.i("json",res);

        dao.getUserDao().deleteAll();
        dao.getDeviceDao().deleteAll();
        dao.getFolderDao().deleteAll();
        dao.getOperatorDao().deleteAll();

        dao.getUserDao().insertOrReplace(dataBase.getUser());
        dao.getDeviceDao().insertOrReplaceInTx(dataBase.getDevices());
        dao.getFolderDao().insertOrReplaceInTx(dataBase.getFolders());
        dao.getOperatorDao().insertOrReplaceInTx(dataBase.getOperators());
    }

}
