package edu.jiabao.entry;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.jiabao.database.Operator;

public class OperatorEntry {
    public static List<OperatorEntry> operatorEntries;
    private int operator_id;
    private String operator_name;
    private int folder_id;
    private int user_id;
    private List<OperatorItemBean> content_list;

    public static List<OperatorEntry> getOperatorEntries() {
        if(operatorEntries==null) {
            operatorEntries=new ArrayList<OperatorEntry>();
        }
        return operatorEntries;
    }

    public static void setOperatorEntries(List<Operator> list){
        if(operatorEntries!=null&&!operatorEntries.isEmpty())
            operatorEntries.clear();
        else
            getOperatorEntries();
        for (int i=0;i<list.size();i++){
            operatorEntries.add(toEntry(list.get(i)));
        }
    }

    public static List<OperatorEntry> getOpratorByPackage(PackageEntry entry){
        List<OperatorEntry> list=new ArrayList<>();
        for (int i=0;i<getOperatorEntries().size();i++){
            if (operatorEntries.get(i).getFolder_id()==entry.getFolder_id()){
                list.add(operatorEntries.get(i));
            }
        }
        return list;
    }

    public Operator toOperator(){
        List<String> list=new ArrayList<>();
        for (int i=0;i<content_list.size();i++){
            list.add(content_list.get(i).toString());
        }

        if(operator_id==-1)
            return new Operator(null,
                            operator_name,
                            new Long(folder_id),
                            new Long(user_id),
                            list);
        else
            return new Operator(new Long(operator_id),
                    operator_name,
                    new Long(folder_id),
                    new Long(user_id),
                    list);
    }

    public static OperatorEntry toEntry(Operator operator){
        List<OperatorItemBean> list=new ArrayList<>();
        List<String> l=operator.getContent_list();
        for (int i=0;i<l.size();i++){
            list.add(OperatorItemBean.getBeanByString(l.get(i)));
        }
        return new OperatorEntry(operator.getOperator_id().intValue(),
                operator.getOperator_name(),
                operator.getFolder_id().intValue(),
                operator.getUser_id().intValue(),
                list);
    }

    public static List<OperatorItemBean> getBaseOperator(OperatorEntry entry){//获取底层操作
        List<OperatorItemBean> tag=new ArrayList<>();
        LinkedList<OperatorItemBean> linkedList=new LinkedList<>();
        linkedList.addAll(entry.getContent_list());
        Log.i("link_size",String.valueOf(linkedList.size()));
        while (!linkedList.isEmpty()){
            OperatorItemBean tem=linkedList.removeFirst();
            PackageEntry packageEntry=PackageEntry.getPackageById(tem.getObjectId());
            if (packageEntry.getFolder_type()==1)
                tag.add(tem);
            else {//如果是包
                List<OperatorItemBean> itemBeans= OperatorEntry.getOpratorByPackage(packageEntry).get(0).getContent_list();//获取子操作列表
                linkedList.addAll(itemBeans);
            }
        }
        return tag;
    }



    public OperatorEntry(int operator_id, String operator_name, int folder_id, int user_id, List<OperatorItemBean> content_list) {
        this.operator_id = operator_id;
        this.operator_name = operator_name;
        this.folder_id = folder_id;
        this.user_id = user_id;
        this.content_list = content_list;
    }

    public int getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(int operator_id) {
        this.operator_id = operator_id;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public int getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<OperatorItemBean> getContent_list() {
        return content_list;
    }

    public void setContent_list(List<OperatorItemBean> content_list) {
        this.content_list = content_list;
    }



}
