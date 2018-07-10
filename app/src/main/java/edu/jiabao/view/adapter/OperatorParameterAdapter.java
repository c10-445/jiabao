package edu.jiabao.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.jiabao.R;


public class OperatorParameterAdapter extends BaseAdapter {
    private Context context;
    private int para_type;

    public OperatorParameterAdapter(Context context, int para_type){
        this.context=context;
        this.para_type=para_type;
    }
    @Override
    public int getCount() {
        int count=0;
        switch (para_type){
            case -1://包操作
                count=0;
                break;
            case 0://开关
                count=2;
                break;
            case 1://模式
                count=5;
                break;
            case 2://风速
                count=5;
                break;
            case 3://温度
                count=11;
                break;
        }
        return count;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater _LayoutInflater = LayoutInflater.from(context);
        view = _LayoutInflater.inflate(R.layout.item_text, null);

        if (view != null) {
            TextView textView = view.findViewById(R.id.item_txt);
            switch (para_type){
                case 0:
                    if(i==0) {
                        textView.setText("关");
                    }else {
                        textView.setText("开");
                    }
                    break;
                case 1:
                    switch (i){
                        case 0:
                            textView.setText("自动");
                            break;
                        case 1:
                            textView.setText("制热");
                            break;
                        case 2:
                            textView.setText("除湿");
                            break;
                        case 3:
                            textView.setText("制冷");
                            break;
                        case 4:
                            textView.setText("送风");
                            break;
                    }
                    break;
                case 2:
                    switch (i){
                        case 0:
                            textView.setText("自动");
                            break;
                        case 1:
                            textView.setText("低速");
                            break;
                        case 2:
                            textView.setText("中速");
                            break;
                        case 3:
                            textView.setText("高速");
                            break;
                        case 4:
                            textView.setText("其他");
                            break;
                    }
                    break;
                case 3:
                    textView.setText(String.valueOf(i+20));
            }
        }
        return view;
    }
}
