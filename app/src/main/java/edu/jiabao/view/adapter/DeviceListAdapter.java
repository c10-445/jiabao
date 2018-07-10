package edu.jiabao.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import edu.jiabao.R;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.presenter.DevicePresenter;
import edu.jiabao.view.label.ControlAirConditioning;

public class DeviceListAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private List<DeviceEntry> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器
    DevicePresenter presenter;
    public final class ListItemView{                //自定义控件集合
        public TextView name_txt;
        public ImageButton turn_on_btn;
        public TextView tem_txt;
        public TextView back_txt;
        public Button delete;
    }


    public DeviceListAdapter(Context context,DevicePresenter presenter, List<DeviceEntry> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.presenter=presenter;
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

    public void reflesh(){
        refleshData();
        notifyDataSetChanged();
    }

    private void refleshData(){
        listItems= DeviceEntry.getDevices();
    }


    /**
     * ListView Item设置
     */
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final int selectID = position;
        final DeviceEntry entry=listItems.get(position);
        //自定义视图
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.item_device_list, null);
            //获取控件对象
            listItemView.name_txt = (TextView)convertView.findViewById(R.id.device_name);
            listItemView.turn_on_btn=convertView.findViewById(R.id.turn_on_button);
            listItemView.tem_txt=convertView.findViewById(R.id.tem_text);
            listItemView.back_txt=convertView.findViewById(R.id.background_txt);
            listItemView.delete=convertView.findViewById(R.id.tv_delete);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (ListItemView)convertView.getTag();
        }

        //设置
        listItemView.name_txt.setText(String.valueOf(entry.getName()));
        if(entry.getTurn_on()) {
            listItemView.tem_txt.setText((String.valueOf(entry.getDegree()))+"℃");
        }else {
            listItemView.tem_txt.setText("--"+"℃");
        }

        listItemView.turn_on_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.turnOnDevice(entry);
            }
        });

        listItemView.back_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ControlAirConditioning.class);
                intent.putExtra("deviceId", entry.getDevice_id());
                context.startActivity(intent);
            }
        });

        listItemView.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteDevice(entry);
            }
        });

        return convertView;
    }
}
