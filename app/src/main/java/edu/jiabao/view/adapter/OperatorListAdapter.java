package edu.jiabao.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.jiabao.R;
import edu.jiabao.entry.OperatorItemBean;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.modle.ImpDecicePackageModel;
import edu.jiabao.modle.ImpOperatorModel;

public class OperatorListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater listContainer;           //视图容器
    private List<OperatorItemBean> operatorList;
    private PackageEntry owner;
    private List<PackageEntry> packageEntries;
    private static final int ITEM_OPERATOR =2;
    private static final int ITEM_ADD =0;
    private static final int ITEM_SHOW=1;
    public final class OperatorListItemView {
        Button btn_add;
        Spinner spinner_object;
        Spinner spinner_operator;
        Spinner spinner_parameter;
    }

    public final class OperatorShowItemView{
        Button btn_reduce;
        TextView txt_object;
        TextView txt_operator;
    }

    public OperatorListAdapter(Context context,PackageEntry entry) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        owner=entry;
        operatorList =new ArrayList<>();
        operatorList.add(new OperatorItemBean());
        packageEntries=PackageEntry.getAllPackagesInPackage(owner);
    }

    public List<OperatorItemBean> getOperatorList() {
        operatorList.remove(getCount()-1);
        return operatorList;
    }

    @Override
    public int getCount() {
        Log.i("size",String.valueOf(operatorList.size()));
        return operatorList.size();
    }

    @Override
    public Object getItem(int i) {
        return operatorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_OPERATOR;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("ITEM_COUNT",String.valueOf(getCount()));
        Log.i("ITEM_POSIT",String.valueOf(position));
        return position!=getCount()-1? ITEM_SHOW : ITEM_ADD;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OperatorListItemView listItemView = null;
        OperatorShowItemView showItemView = null;
        int item_type = getItemViewType(position);
        Log.i("ITEM_TYPE",String.valueOf(item_type));
        if (convertView == null) {
            switch (item_type) {
                case ITEM_ADD:
                    listItemView = new OperatorListItemView();
                    convertView = listContainer.inflate(R.layout.item_operation_list, null);

                    listItemView.spinner_object = convertView.findViewById(R.id.spinner_object);
                    listItemView.spinner_operator = convertView.findViewById(R.id.spinner_operator);
                    listItemView.spinner_parameter=convertView.findViewById(R.id.spinner_parameter);
                    listItemView.btn_add = convertView.findViewById(R.id.btn_add_operator_item);

                    convertView.setTag(listItemView);
                    break;
                case ITEM_SHOW:
                    showItemView = new OperatorShowItemView();
                    convertView = listContainer.inflate(R.layout.item_operator_over_list, null);
                    showItemView.btn_reduce = convertView.findViewById(R.id.btn_reducce_operator_item);
                    showItemView.txt_object = convertView.findViewById(R.id.text_operator_object);
                    showItemView.txt_operator = convertView.findViewById(R.id.text_operator_operator);
                    convertView.setTag(showItemView);
            }
        } else {
            switch (item_type) {
                case ITEM_ADD:
                    listItemView = (OperatorListItemView) convertView.getTag();
                    break;
                case ITEM_SHOW:
                    showItemView = (OperatorShowItemView) convertView.getTag();
            }
        }

        switch (item_type) {
            case ITEM_ADD: {//添加操作项
                final Spinner s_object = listItemView.spinner_object;
                final Spinner s_op = listItemView.spinner_operator;
                final Spinner s_para = listItemView.spinner_parameter;
                listItemView.spinner_object.setAdapter(new ObjectListAdapter(context, packageEntries));
                listItemView.spinner_object.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        PackageEntry ee = (PackageEntry) adapterView.getSelectedItem();
                        ImpOperatorModel model = new ImpOperatorModel();
                        operatorList.get(position).setObjectId(ee.getFolder_id());
                        s_op.setAdapter(new OperatorItemAdapter(context, ee, model.getOperatorByPackage(ee)));//设置二级操作表

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        PackageEntry ee = packageEntries.get(0);
                        ImpOperatorModel model = new ImpOperatorModel();
                        operatorList.get(position).setObjectId(ee.getFolder_id());
                        s_op.setAdapter(new OperatorItemAdapter(context, ee, model.getOperatorByPackage(ee)));//设置二级操作表

                    }
                });

                listItemView.spinner_operator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        int op_id = (int) adapterView.getSelectedItem();
                        operatorList.get(position).setOperatorId(op_id);
                        ImpDecicePackageModel model = new ImpDecicePackageModel();
                        PackageEntry en = model.getPackageById(operatorList.get(position).getObjectId());
                        if (en.getFolder_type() == 0)
                            op_id = -1;
                        s_para.setAdapter(new OperatorParameterAdapter(context, op_id));//设置三级操作表
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        int op_id = (int) adapterView.getSelectedItem();
                        operatorList.get(position).setOperatorId(op_id);
                        ImpDecicePackageModel model = new ImpDecicePackageModel();
                        PackageEntry en = model.getPackageById(operatorList.get(position).getObjectId());
                        if (en.getFolder_type() == 0)
                            op_id = -1;
                        s_para.setAdapter(new OperatorParameterAdapter(context, op_id));//设置三级操作表
                    }
                });

                listItemView.spinner_parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //todo
                        int op_para = (int) adapterView.getSelectedItem();
                        operatorList.get(position).setParameter(op_para);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        //todo
                        int op_para = (int) adapterView.getSelectedItem();
                        operatorList.get(position).setParameter(op_para);
                    }
                });


                listItemView.btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Log.i("ListSize1",String.valueOf(list.size()));
                        operatorList.add(new OperatorItemBean());
                        //Log.i("ListSize", String.valueOf(operatorList.size()));
                        notifyDataSetChanged();
                        //Log.i("ListSize1", String.valueOf(operatorList.size()));
                    }
                });
            }
                break;
            case ITEM_SHOW://展示操作项
                int object_id= operatorList.get(position).getObjectId();
                int operatorId=operatorList.get(position).getOperatorId();
                int para=operatorList.get(position).getParameter();
                ImpDecicePackageModel model=new ImpDecicePackageModel();
                PackageEntry en= model.getPackageById(object_id);
                showItemView.txt_object.setText(en.getFolder_name());
                showItemView.txt_operator.setText(OperatorTagShow(en,operatorId,para));
                showItemView.btn_reduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        operatorList.remove(position);
                        notifyDataSetChanged();
                    }
                });
        }


        return convertView;
    }

    private String OperatorTagShow(PackageEntry entry,int operatorId,int para){
        String s="";
        if(entry.getFolder_type()==0) {
            ImpOperatorModel model = new ImpOperatorModel();
            s = model.getOperatorByPackage(entry).get(0).getOperator_name();
        }else {
            switch (operatorId){
                case 0:
                    if(para==0) {
                        s="关";
                    }else {
                        s="开";
                    }
                    break;
                case 1:
                    switch (para){
                        case 0:
                            s="模式自动";
                            break;
                        case 1:
                            s="模式制热";
                            break;
                        case 2:
                            s="模式除湿";
                            break;
                        case 3:
                            s="模式制冷";
                            break;
                        case 4:
                            s="模式送风";
                            break;
                    }
                    break;
                case 2:
                    switch (para){
                        case 0:
                            s="风速自动";
                            break;
                        case 1:
                            s="风速低速";
                            break;
                        case 2:
                            s="风速中速";
                            break;
                        case 3:
                            s="风速高速";
                            break;
                        case 4:
                            s="风速其他";
                            break;
                    }
                    break;
                case 3:
                    s="温度"+String.valueOf(para+20)+"℃";
            }
        }

        return s;
    }


}
