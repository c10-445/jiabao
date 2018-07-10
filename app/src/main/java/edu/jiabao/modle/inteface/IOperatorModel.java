package edu.jiabao.modle.inteface;

import java.util.List;

import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.PackageEntry;

public interface IOperatorModel {
    public void addOperator(OperatorEntry entry);

    public void deleteOperatorByPackage(PackageEntry entry);

    public List<OperatorEntry> getOperatorByPackage(PackageEntry entry);
}
