package edu.jiabao.view.adapter;

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
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.presenter.HomePresenter;
import edu.jiabao.view.home.NewOperationActivity;

public class HomeListAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private List<PackageEntry> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器
    private HomePresenter presenter;
    private static final int ITEM_DEVICE =0;
    private static final int ITEM_PACKAGE =1;
    private static final int ITEM_COUNT=2;
    public final class PackageListItemView {                //自定义控件集合
        public TextView title;
        public TextView background;
        public ImageButton btn_on;
        public TextView device_num;
        public Button delete;
        public Button operator;
    }

    public final class DeviceListItemView{                //自定义控件集合
        public TextView name_txt;
        public ImageButton turn_on_btn;
        public TextView tem_txt;
        public TextView back_txt;
        public Button delete;
    }


    public HomeListAdapter(Context context, List<PackageEntry> listItems,HomePresenter presenter) {
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

    public void reflesh(List<PackageEntry> list){
        refleshData(list);
        notifyDataSetChanged();
    }

    private void refleshData(List<PackageEntry> list){
        listItems=list;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return listItems.get(position).getFolder_type()==1? ITEM_DEVICE : ITEM_PACKAGE;
    }

    /**
     * ListView Item设置
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        final PackageEntry packageEntry = listItems.get(position);
        final DeviceEntry device=DeviceEntry.getDevice(listItems.get(position).getDevice_id());
        int item_type = getItemViewType(position);
        PackageListItemView packageListItemView = null;
        DeviceListItemView deviceListItemView=null;
        if (convertView == null) {
            switch (item_type) {//初始化包
                case ITEM_PACKAGE:
                    packageListItemView = new PackageListItemView();
                    //获取list_item布局文件的视图
                    convertView = listContainer.inflate(R.layout.item_home_list, null);
                    //获取控件对象
                    packageListItemView.title = (TextView) convertView.findViewById(R.id.home_item_title);
                    packageListItemView.background = (TextView) convertView.findViewById(R.id.home_background);
                    packageListItemView.btn_on = convertView.findViewById(R.id.btn_on);
                    packageListItemView.device_num = convertView.findViewById(R.id.txt_device_num);
                    packageListItemView.delete = convertView.findViewById(R.id.tv_delete);
                    packageListItemView.operator=convertView.findViewById(R.id.tv_operator);

                     //设置控件集到convertView
                    convertView.setTag(packageListItemView);
                    break;
                case ITEM_DEVICE:
                    deviceListItemView = new DeviceListItemView();
                    //获取list_item布局文件的视图
                    convertView = listContainer.inflate(R.layout.item_device_list, null);
                    //获取控件对象
                    deviceListItemView.name_txt = (TextView) convertView.findViewById(R.id.device_name);
                    deviceListItemView.turn_on_btn = convertView.findViewById(R.id.turn_on_button);
                    deviceListItemView.tem_txt = convertView.findViewById(R.id.tem_text);
                    deviceListItemView.back_txt = convertView.findViewById(R.id.background_txt);
                    deviceListItemView.delete = convertView.findViewById(R.id.tv_delete);
                    //设置控件集到convertView
                    convertView.setTag(deviceListItemView);
                    break;
            }
        } else {
            switch (item_type) {
                case ITEM_PACKAGE:
                    packageListItemView = (PackageListItemView) convertView.getTag();
                    break;
                case ITEM_DEVICE:
                    deviceListItemView = (DeviceListItemView) convertView.getTag();
                    break;
            }
        }

        switch (item_type) {
            case ITEM_PACKAGE:
                //设置文字
                packageListItemView.title.setText(packageEntry.getFolder_name());
                packageListItemView.device_num.setText(String.valueOf(packageEntry.getFolder_device_on_num()) + "/" + String.valueOf(packageEntry.getFolder_device_num()));


                packageListItemView.background.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.showPackage(packageEntry);
                    }
                });
                packageListItemView.btn_on.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.turnOnPackage(packageEntry);
                    }
                });
                packageListItemView.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {//删除包
                        presenter.deletePackage(packageEntry);
                    }
                });
                List<OperatorEntry> list= presenter.getOperatorByPackage(packageEntry);
                if(list.isEmpty()) {
                    packageListItemView.operator.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(context, NewOperationActivity.class);
                            intent.putExtra("packageId", packageEntry.getFolder_id());
                            context.startActivity(intent);
                        }
                    });
                }else {
                    packageListItemView.operator.setText(list.get(0).getOperator_name());
                    packageListItemView.operator.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.turnOnOperator(OperatorEntry.getOpratorByPackage(packageEntry).get(0));
                        }
                    });
                }
                break;
            case ITEM_DEVICE:
                //设置文字
                deviceListItemView.name_txt.setText(String.valueOf(device.getName()));
                if(device.getTurn_on()) {
                    deviceListItemView.tem_txt.setText((String.valueOf(device.getDegree())) + "℃");
                }else {
                    deviceListItemView.tem_txt.setText("--" + "℃");
                }
                deviceListItemView.back_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.showDevices(device);
                    }
                });

                deviceListItemView.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.deletePackage(packageEntry);
                    }
                });

                deviceListItemView.turn_on_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.turnOnDevice(device,packageEntry);
                    }
                });
                break;
        }

        return convertView;
    }
}
