package edu.jiabao.modle.inteface;

import edu.jiabao.entry.TimingEntry;

public interface ItimingModel {
    //添加定时操作
    public void addTiming(TimingEntry entry);
    //删除定时操作
    public void deleteTiming(TimingEntry entry);
    //开启定时操作
    public void turnOnTiminng(TimingEntry entry);
    //关闭定时操作
    public void turnDownTiming(TimingEntry entry);
    //编辑定时操作
    public void modifyTiming();
}
