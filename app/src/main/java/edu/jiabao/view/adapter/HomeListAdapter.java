package edu.jiabao.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import edu.jiabao.R;
import edu.jiabao.presenter.HomePresenter;

public class HomeListAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private ArrayList<String> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器
    private HomePresenter presenter;
    public final class ListItemView{                //自定义控件集合
        public TextView title;
        public TextView background;
        public ImageButton btn_on;
        public TextView device_num;
        public TextView delete;
    }


    public HomeListAdapter(Context context, ArrayList<String> listItems,HomePresenter presenter) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
        this.presenter=presenter;
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

    public void addItem(String a){
        listItems.add(a);
        notifyDataSetChanged();
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
            convertView = listContainer.inflate(R.layout.item_home_list, null);
            //获取控件对象
            listItemView.title = (TextView)convertView.findViewById(R.id.home_item_title);
            listItemView.background=(TextView)convertView.findViewById(R.id.home_background);
            listItemView.btn_on=convertView.findViewById(R.id.btn_on);
            listItemView.device_num=convertView.findViewById(R.id.device_name);
            listItemView.delete=convertView.findViewById(R.id.tv_delete);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (ListItemView)convertView.getTag();
        }

        //设置文字
        listItemView.title.setText((String) listItems.get(position));

        listItemView.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showDevices();
            }
        });

        listItemView.btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.turnOnPackage();
            }
        });

        listItemView.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deletePackage();
            }
        });

        return convertView;
    }
}
