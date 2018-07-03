package edu.jiabao.entry;

import java.util.List;

import edu.jiabao.database.folder;

public class PackageEntry {
    private static List<PackageEntry> packageEntries;
    private int folder_id;
    private String folder_name;
    private int folder_type;
    private int folder_parents;
    private int device_id;
    private int user_id;

    public static void addPackage(PackageEntry entry){
        packageEntries.add(entry);
    }

    public static void deletePackage(int id){
        for(int i=0;i<packageEntries.size();i++){
            if(packageEntries.get(i).folder_id==id){
                packageEntries.remove(i);
            }
        }
    }

    public folder toFolder(){
        return new folder(new Long(folder_id),
                            folder_name,
                            new Long(folder_type),
                            new Long(folder_parents),
                            new Long(device_id),
                            new Long( user_id));
    }

    public static List<PackageEntry> getPackageEntries() {
        return packageEntries;
    }
    public static void setPackageEntries(List<PackageEntry> packageEntries) {
        PackageEntry.packageEntries = packageEntries;
    }

}
