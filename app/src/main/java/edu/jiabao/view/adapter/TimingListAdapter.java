package edu.jiabao.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.jiabao.R;
import edu.jiabao.entry.TimingEntry;
import edu.jiabao.presenter.TimingPresenter;

public class TimingListAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private List<TimingEntry> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器
    TimingPresenter presenter;
    public final class ListItemView{                //自定义控件集合
        public TextView title;
        public Switch switch_timing;
        public TextView time;
        public Button delete;
    }


    public TimingListAdapter(Context context, List<TimingEntry> listItems, TimingPresenter presenter) {
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

    public void reflesh(List<TimingEntry> list){
        refleshData(list);
        notifyDataSetChanged();
    }

    private void refleshData(List<TimingEntry> list){
        listItems=list;
    }


    /**
     * ListView Item设置
     */
    public View getView(int position, View convertView, final ViewGroup parent) {
        final int selectID = position;
        //自定义视图
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.item_timing_list, null);
            //获取控件对象
            listItemView.title = (TextView)convertView.findViewById(R.id.timing_item_title);
            listItemView.switch_timing =convertView.findViewById(R.id.switch_timing);
            listItemView.time=convertView.findViewById(R.id.txt_time);
            listItemView.delete=convertView.findViewById(R.id.tv_delete);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (ListItemView)convertView.getTag();
        }

        //设置文字
        final TimingEntry entry=listItems.get(position);
        listItemView.title.setText(entry.getTiming_name());
        listItemView.time.setText(DateToString(entry.getTime()));
        if(entry.getTime().before(getCurdate())) {
           TimingEntry.setTimingEntryIsOn(entry,false);
        }
        listItemView.switch_timing.setChecked(entry.isIs_on());
        listItemView.switch_timing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {//发送定时请求
                    presenter.sentTiming(entry);
                }else {//发送定时取消请求
                    presenter.cancelTiming(entry);
                }
            }
        });

        listItemView.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteTiming(entry);
            }
        });

        return convertView;
    }

    public static String DateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String time = formatter.format(date);
        return time;
    }

    public static Date getCurdate() {
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return curDate;
    }
}
