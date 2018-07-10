package edu.jiabao.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.jiabao.R;
import edu.jiabao.entry.PackageEntry;

public class ObjectListAdapter extends BaseAdapter {
    private Context context;
    private List<PackageEntry> list;

    public ObjectListAdapter(Context context,List<PackageEntry> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getFolder_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater _LayoutInflater = LayoutInflater.from(context);
        view = _LayoutInflater.inflate(R.layout.item_text, null);

        if (view != null) {
            TextView textView=view.findViewById(R.id.item_txt);
            textView.setText(list.get(i).getFolder_name());
        }

        return view;
    }
}
