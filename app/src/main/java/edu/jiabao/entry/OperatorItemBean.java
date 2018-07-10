package edu.jiabao.entry;

import java.util.Arrays;
import java.util.List;

public class OperatorItemBean {
    private int objectId;
    private int operatorId;
    private int parameter;

    @Override
    public String toString(){
        String s=String.valueOf(objectId)+","+String.valueOf(operatorId)+":"+String.valueOf(parameter);
        return s;
    }

    public static OperatorItemBean getBeanByString(String s){
        List<String> object = Arrays.asList(s.split(","));
        List<String> operator=Arrays.asList(object.get(1).split(":"));
        OperatorItemBean bean=new OperatorItemBean();
        try {
            if (operator.size()==2)
                bean= new OperatorItemBean(Integer.valueOf(object.get(0)).intValue(),Integer.valueOf(operator.get(0)).intValue(),Integer.valueOf(operator.get(1)).intValue());
            else
                bean= new OperatorItemBean(Integer.valueOf(object.get(0)).intValue(),Integer.valueOf(operator.get(0)).intValue(),new Integer(null));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public OperatorItemBean(){

    }

    public OperatorItemBean(int objectId, int operatorId,int parameter) {
        this.objectId = objectId;
        this.operatorId = operatorId;
        this.parameter=parameter;
    }
    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }


}
