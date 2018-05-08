package edu.jiabao.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.jiabao.R;

public class TimingListAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private ArrayList<String> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器
    public final class ListItemView{                //自定义控件集合
        public TextView title;
        public Button switch2;
    }


    public TimingListAdapter(Context context, ArrayList<String> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return listItems.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    /**
     * ListView Item设置
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        final int selectID = position;
        //自定义视图
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.timing_list_item, null);
            //获取控件对象
            listItemView.title = (TextView)convertView.findViewById(R.id.timing_item_title);
            listItemView.switch2=convertView.findViewById(R.id.switch2);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (ListItemView)convertView.getTag();
        }

        //设置文字
        listItemView.title.setText((String) listItems.get(position));
        listItemView.switch2.setFocusable(false);
        listItemView.switch2.setFocusableInTouchMode(false);

        return convertView;
    }
}
