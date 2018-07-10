package edu.jiabao.entry;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.jiabao.database.folder;
import edu.jiabao.modle.ImpUserModel;

public class PackageEntry {
    private static List<PackageEntry> packageEntries;
    private int folder_id;
    private String folder_name;
    private int folder_type;
    private int folder_parents;
    private int device_id;
    private int user_id;
    private int folder_device_num;
    private int folder_device_on_num;
    //ID为-1时置空让数据库自增

    public PackageEntry(int folder_id, String folder_name, int folder_type, int folder_parents, int device_id, int user_id, int folder_device_num, int folder_device_on_num) {
        this.folder_id = folder_id;
        this.folder_name = folder_name;
        this.folder_type = folder_type;
        this.folder_parents = folder_parents;
        this.device_id = device_id;
        this.user_id = user_id;
        this.folder_device_num = folder_device_num;
        this.folder_device_on_num = folder_device_on_num;
    }

    public PackageEntry(String folder_name, int folder_parents) {
        ImpUserModel userModel=new ImpUserModel();
        this.folder_id = -1;
        this.folder_name = folder_name;
        this.folder_type = 0;
        this.folder_parents = folder_parents;
        this.device_id = -1;
        this.user_id = userModel.getUserId().intValue();
        this.folder_device_num = 0;
        this.folder_device_on_num = 0;
    }

    public folder toFolder(){
        if(folder_id==-1){
            return new folder(null,
                    folder_name,
                    new Long(folder_type),
                    new Long(folder_parents),
                    new Long(device_id),
                    new Long( user_id));
        }else {
            return new folder(new Long(folder_id),
                    folder_name,
                    new Long(folder_type),
                    new Long(folder_parents),
                    new Long(device_id),
                    new Long(user_id));
        }
    }

    public static void daoToEntry(List<folder> list){
        if(packageEntries!=null&&!packageEntries.isEmpty())
            packageEntries.clear();
        else
            getPackageEntries();
        for (int i=0;i<list.size();i++){
             packageEntries.add(toEntry(list,i));
             int a= packageEntries.get(0).getFolder_device_num();
             //Log.i("DaoToEntry_name",packageEntries.get(0).getFolder_name());
            // Log.i("DaoToEntry",String.valueOf(a));
        }
    }

    public static List<PackageEntry> getPackageEntries() {
        if(packageEntries==null) {
            packageEntries=new ArrayList<PackageEntry>();
        }
        return packageEntries;
    }

    public static List<PackageEntry> getPackageEntrysInPackage(PackageEntry entry){
        List<PackageEntry> list=new ArrayList<PackageEntry>();
        int folderId=entry.getFolder_id();
        for (int i=0;i<packageEntries.size();i++){
            if(packageEntries.get(i).getFolder_parents()==folderId)
                list.add(packageEntries.get(i));
        }
        return list;
    }

    public static List<PackageEntry> getPackageInRoot(){
        List<PackageEntry> list=new ArrayList<PackageEntry>();
        for (int i=0;i<packageEntries.size();i++){
            if(packageEntries.get(i).getFolder_parents()==-1) {
                list.add(packageEntries.get(i));
            }
        }
        return list;
    }

    public static List<PackageEntry> getPackagesByParentsId(int parents_id){
        List<PackageEntry> list=new ArrayList<PackageEntry>();
        for (int i=0;i<packageEntries.size();i++){
            if(packageEntries.get(i).getFolder_parents()==parents_id) {
                list.add(packageEntries.get(i));
            }
        }
        return list;
    }

    public static List<PackageEntry> getAllPackagesInPackage(PackageEntry entry){
        List<PackageEntry> l=new ArrayList<>();
        LinkedList<PackageEntry> linkedList=new LinkedList<>();
        linkedList.add(entry);
        while (!linkedList.isEmpty()){
            PackageEntry e=linkedList.removeFirst();
            List<PackageEntry> lists= PackageEntry.getPackagesByParentsId(e.getFolder_id());
            Iterator<PackageEntry>iterator = lists.iterator();
            while (iterator.hasNext()){
                PackageEntry temp = iterator.next();
                l.add(temp);
                if(!PackageEntry.getPackagesByParentsId(temp.getFolder_id()).isEmpty())
                    linkedList.add(temp);
            }

        }
        return l;
    }

    public static PackageEntry getPackageById(int id){
        for (int i=0;i<packageEntries.size();i++){
            if(packageEntries.get(i).getFolder_id()==id)
                return packageEntries.get(i);
        }
        return null;
    }

    public static void setPackageEntries(List<PackageEntry> packageEntries) {
        PackageEntry.packageEntries = packageEntries;
    }
    public int getFolder_id() {
        return folder_id;
    }
    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }
    public String getFolder_name() {
        return folder_name;
    }
    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }
    public int getFolder_type() {
        return folder_type;
    }
    public void setFolder_type(int folder_type) {
        this.folder_type = folder_type;
    }
    public int getFolder_parents() {
        return folder_parents;
    }
    public void setFolder_parents(int folder_parents) {
        this.folder_parents = folder_parents;
    }
    public int getDevice_id() {
        return device_id;
    }
    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getFolder_device_num() {
        return folder_device_num;
    }
    public void setFolder_device_num(int folder_device_num) {
        this.folder_device_num = folder_device_num;
    }
    public int getFolder_device_on_num() {
        return folder_device_on_num;
    }
    public void setFolder_device_on_num(int folder_device_on_num) {
        this.folder_device_on_num = folder_device_on_num;
    }

    public static List<DeviceEntry> getDevicesInPackage(PackageEntry entry){
        List<PackageEntry> list=getPackageEntries();
        List<PackageEntry> leaf=new ArrayList<>();
        List<PackageEntry> child=new ArrayList<>();
        //取出子节点
        for (int a = 0; a < list.size(); a++) {
            boolean is_leaf=true;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getFolder_parents()==list.get(a).getFolder_id()) {
                    is_leaf=false;
                    break;
                }
            }
            if(is_leaf){
                leaf.add(list.get(a));
            }
        }
        //Log.i("leaf",String.valueOf(leaf.size()));
        //判断叶节点是否为设备
        for (int x=0;x<leaf.size();x++){
            if (leaf.get(x).getFolder_type()==0)
                leaf.remove(x);//不是设备则移除
        }
        //检索子节点，判断它是否为包的子节点
        for(int a=0;a<leaf.size();a++){
            int leaf_parents=-2;
            PackageEntry temp=leaf.get(a);
            while(leaf_parents!=-1&&leaf_parents!=entry.getFolder_id()){
                int _l=temp.getFolder_parents();
                leaf_parents=_l;
                temp=PackageEntry.getPackageById(_l);
            }
            if(leaf_parents!=-1){
                child.add(leaf.get(a));
            }
        }
       // Log.i("Child",String.valueOf(child.size()));
        List<DeviceEntry> childDevices=new ArrayList<>();
        //已获取底层设备列表
        for (int i=0;i<child.size();i++){
            childDevices.add(DeviceEntry.getDevice(child.get(i).getDevice_id()));
        }

        //Log.i("ChildDevice",String.valueOf(childDevices.size()));
        return childDevices;
    }

    public static List<Integer> getDeviceIdsInPackage(PackageEntry entry){
        List<DeviceEntry> list= getDevicesInPackage(entry);
        List<Integer> ids=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            ids.add(new Integer(list.get(i).getDevice_id()));
        }
        return ids;
    }

    private static PackageEntry toEntry(List<folder> list,int i){
        folder fold=list.get(i);
        if(fold.getFolder_type().equals(new Long(1)))//为设备，直接返回设备的包形式
            return new PackageEntry(fold.getFolder_id().intValue(),
                                    fold.getFolder_name(),
                                    fold.getFolder_type().intValue(),
                                    fold.getFolder_parents().intValue(),
                                    fold.getDevice_id().intValue(),
                                    fold.getUser_id().intValue(),
                                    0, 0);
        else {//为包，计算其子节点的设备数和 正在启动的设备数
            List<folder> leaf=new ArrayList<>();
            List<folder> child=new ArrayList<>();
            int d_num=0;
            int d_on_num=0;
            //取出子节点
            for (int a = 0; a < list.size(); a++) {
                boolean is_leaf=true;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getFolder_parents().equals(list.get(a).getFolder_id())) {
                        is_leaf=false;
                        break;
                    }
                }
                if(is_leaf){
                    leaf.add(list.get(a));
                }
            }
            //判断子节点是否为设备
            for (int x=0;x<leaf.size();x++){
                if (leaf.get(x).getFolder_type().equals(new Long(0)))
                    leaf.remove(x);
            }
            //检索子节点，判断它是否为包的子节点
            for(int a=0;a<leaf.size();a++){
                int leaf_parents=-2;
                folder temp=leaf.get(a);
                while(leaf_parents!=-1&&leaf_parents!=fold.getFolder_id().intValue()){
                    Long _l=temp.getFolder_parents();
                    leaf_parents=_l.intValue();
                    temp=findFolderById(list,_l);
                }
                if(leaf_parents!=-1){
                    child.add(leaf.get(a));
                }
            }
            //已获取底层设备列表
            d_num=child.size();
            Log.i("DeviceNUM:",String.valueOf(d_num));
            for (int x=0;x<child.size();x++){
                int id= child.get(x).getDevice_id().intValue();
                if (DeviceEntry.checkDeviceIsOn(id)){
                    d_on_num++;
                }
            }

            return new PackageEntry(fold.getFolder_id().intValue(),
                    fold.getFolder_name(),
                    fold.getFolder_type().intValue(),
                    fold.getFolder_parents().intValue(),
                    fold.getDevice_id().intValue(),
                    fold.getUser_id().intValue(),
                    d_num, d_on_num);
        }
    }

    private static folder findFolderById(List<folder> list, Long id){
        for(int a=0;a<list.size();a++){
            if (list.get(a).getFolder_id().equals(id))
                return list.get(a);
        }
        return null;
    }
}
