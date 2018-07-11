package edu.jiabao.entry;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.jiabao.dao.Dao;
import edu.jiabao.database.Timing;

public class TimingEntry {
    public static List<TimingEntry> getTimingEntries() {
        if(timingEntries==null) {
            timingEntries=new ArrayList<TimingEntry>();
        }
        return timingEntries;
    }

    public static void setTimingEntries(List<TimingEntry> timingEntries) {
        TimingEntry.timingEntries = timingEntries;
    }

    public int getTiming_id() {
        return timing_id;
    }

    public void setTiming_id(int timing_id) {
        this.timing_id = timing_id;
    }

    public String getTiming_name() {
        return timing_name;
    }

    public void setTiming_name(String timing_name) {
        this.timing_name = timing_name;
    }

    public boolean isIs_on() {
        return is_on;
    }

    public void setIs_on(boolean is_on) {
        this.is_on = is_on;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<OperatorItemBean> getOperator_list() {
        return operator_list;
    }

    public void setOperator_list(List<OperatorItemBean> operator_list) {
        this.operator_list = operator_list;
    }

    public List<Integer> getTask_id_list() {
        return task_id_list;
    }

    public void setTask_id_list(List<Integer> task_id_list) {
        this.task_id_list = task_id_list;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public TimingEntry(){}

    public TimingEntry(int timing_id, String timing_name, boolean is_on, int user_id, List<OperatorItemBean> operator_list, List<Integer> task_id_list, Date time) {
        this.timing_id = timing_id;
        this.timing_name = timing_name;
        this.is_on = is_on;
        this.user_id = user_id;
        this.operator_list = operator_list;
        this.task_id_list = task_id_list;
        this.time = time;
    }

    private static List<TimingEntry> timingEntries;
    private int timing_id;
    private String timing_name;
    private boolean is_on;
    private int user_id;
    private List<OperatorItemBean> operator_list;
    private List<Integer> task_id_list;
    private Date time;

    public static void setTimingEntryIsOn(TimingEntry entry,boolean is_on){
        for (int i=0;i<timingEntries.size();i++)
            if (timingEntries.get(i).getTiming_id()==entry.getTiming_id())
                timingEntries.get(i).setIs_on(is_on);

    }

    public static void setTimingEntryTaskListNull(TimingEntry entry){
        for (int i=0;i<timingEntries.size();i++)
            if (timingEntries.get(i).getTiming_id()==entry.getTiming_id())
                timingEntries.get(i).getTask_id_list().clear();

    }

    public static void DaoToEntry( List<Timing> timing){
        if(timingEntries!=null&&!timingEntries.isEmpty())
            timingEntries.clear();
        else {
            getTimingEntries();
        }
        TimingEntry entry=new TimingEntry();
        for (int i=0;i<timing.size();i++){
            Timing d= timing.get(i);
            entry=toEntry(d);
            timingEntries.add(entry);
        }
    }

    public static void EntryToDao(){
        Dao.TimingInsertTimings(timingEntries);
    }

    public Timing toTiming(){
        List<String> list=new ArrayList<>();
        List<String> list1=new ArrayList<>();
        for (int i=0;i<operator_list.size();i++){
            list.add(operator_list.get(i).toString());
        }

        for (int i=0;i<task_id_list.size();i++){
            list1.add(task_id_list.get(i).toString());
        }

        if(timing_id==-1)
            return new Timing(null,
                    timing_name,
                    is_on,
                    new Long(user_id),
                    list,
                    list1,
                    time);
        else
            return  new Timing(new Long(timing_id),
                timing_name,
                is_on,
                new Long(user_id),
                list,
                list1,
                time);
    }

    public static TimingEntry toEntry(Timing t) {
        List<OperatorItemBean> list = new ArrayList<>();
        List<String> l = t.getOperator_list();
        List<Integer> list1=new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            list.add(OperatorItemBean.getBeanByString(l.get(i)));
        }
        List<String> l1 = t.getTask_id_list();
        Log.i("Interge",String.valueOf(l1.size()));
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i).equals(""))
                break;
            else
                list1.add(new Integer(l1.get(i)));
        }
        return new TimingEntry(t.getTiming_id().intValue(),
                t.getTiming_name(),
                t.getIs_on(),
                t.getUser_id().intValue(),
                list,
                list1,
                t.getTime());
    }

}
