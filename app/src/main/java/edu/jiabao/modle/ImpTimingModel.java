package edu.jiabao.modle;

import java.util.List;

import edu.jiabao.dao.Dao;
import edu.jiabao.database.Timing;
import edu.jiabao.entry.TimingEntry;
import edu.jiabao.modle.inteface.ItimingModel;

public class ImpTimingModel implements ItimingModel {
    @Override
    public void addTiming(TimingEntry entry) {
        Dao.TimingAddTiming(entry.toTiming());
        refleshModel();
    }

    @Override
    public void deleteTiming(TimingEntry entry) {
        Dao.TimingDeleteTiming(entry.toTiming());
        refleshModel();
    }

    @Override
    public void turnOnTiminng(TimingEntry entry) {
        TimingEntry.setTimingEntryIsOn(entry,true);
        TimingEntry.EntryToDao();
    }

    @Override
    public void turnDownTiming(TimingEntry entry) {
        TimingEntry.setTimingEntryIsOn(entry,false);
        TimingEntry.setTimingEntryTaskListNull(entry);
        TimingEntry.EntryToDao();
    }

    @Override
    public void modifyTiming() {

    }

    private void refleshModel(){
        Long userId=ImpUserModel.getUser().getUser_id();
        List<Timing> list= Dao.TimingQueryAllTiming(userId);
        TimingEntry.DaoToEntry(list);
    }
}
