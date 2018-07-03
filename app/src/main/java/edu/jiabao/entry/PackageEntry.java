package edu.jiabao.entry;

import java.util.List;

public class PackageEntry {
    private static List<PackageEntry> packageEntries;
    private int folder_id;
    private String folder_name;
    private int folder_type;
    private int folder_parents;
    private int device_id;
    private int user_id;

    public void addPackage(){
        
    }

    public static List<PackageEntry> getPackageEntries() {
        return packageEntries;
    }
    public static void setPackageEntries(List<PackageEntry> packageEntries) {
        PackageEntry.packageEntries = packageEntries;
    }

}
