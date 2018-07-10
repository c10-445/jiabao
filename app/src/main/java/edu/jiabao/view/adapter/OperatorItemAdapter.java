package edu.jiabao.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.jiabao.R;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.PackageEntry;

public class OperatorItemAdapter extends BaseAdapter {
    private Context context;
    private PackageEntry entry;
    private List<OperatorEntry> list;

    public OperatorItemAdapter(Context context,PackageEntry entry,List<OperatorEntry> list){
        this.context=context;
        this.list=list;
        this.entry=entry;
    }

    @Override
    public int getCount() {
        if (entry.getFolder_type()==0)
            return list.size();
        else
            return 4;
    }

    @Override
    public Object getItem(int i) {
        if (entry.getFolder_type()==0)
            return list.get(i).getOperator_id();
        else
            return  i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater _LayoutInflater = LayoutInflater.from(context);
        view = _LayoutInflater.inflate(R.layout.item_text, null);

        if (view != null) {
            TextView textView = view.findViewById(R.id.item_txt);
            if(entry.getFolder_type()==0) {
                textView.setText(list.get(i).getOperator_name());
            }else {
                switch (i){
                    case 0:
                        textView.setText("开关");
                        break;
                    case 1:
                        textView.setText("模式");
                        break;
                    case 2:
                        textView.setText("风速");
                        break;
                    case 3:
                        textView.setText("温度");
                }
            }
        }

        return view;
    }
}