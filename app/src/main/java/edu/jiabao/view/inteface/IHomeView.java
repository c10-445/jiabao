package edu.jiabao.view.inteface;

import java.util.List;

import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.PackageEntry;

public interface IHomeView {
    public void showSwipWindow();
    public void refleshListView(List<PackageEntry> _l);
    public void initHomeListView(List<PackageEntry> _l);
    public void showDeviceActivity(DeviceEntry entry);
    public void showPackageFragment(PackageEntry entry);
}
