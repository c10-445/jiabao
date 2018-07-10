package edu.jiabao.modle;

import java.util.List;

import edu.jiabao.dao.Dao;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.modle.inteface.IOperatorModel;

public class ImpOperatorModel implements IOperatorModel {
    public ImpOperatorModel(){

    }

    private void refleshModel(){
        Long userId=ImpUserModel.getUser().getUser_id();
        OperatorEntry.setOperatorEntries(Dao.OperatorQueryAllOperator(userId));
    }

    @Override
    public void addOperator(OperatorEntry entry) {
        Dao.OperatorAddOperator(entry.toOperator());
        refleshModel();
    }

    @Override
    public void deleteOperatorByPackage(PackageEntry entry) {
        refleshModel();
        List<OperatorEntry> list= OperatorEntry.getOpratorByPackage(entry);
        Dao.OperatorDeleteOperatorByPackage(list);
    }

    @Override
    public List<OperatorEntry> getOperatorByPackage(PackageEntry entry){
        refleshModel();
        List<OperatorEntry> list= OperatorEntry.getOpratorByPackage(entry);
        return list;
    }
}
