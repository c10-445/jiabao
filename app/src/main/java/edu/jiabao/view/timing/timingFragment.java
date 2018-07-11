package edu.jiabao.view.timing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.jiabao.R;
import edu.jiabao.entry.TimingEntry;
import edu.jiabao.presenter.TimingPresenter;
import edu.jiabao.view.MainActivity;
import edu.jiabao.view.adapter.TimingListAdapter;
import edu.jiabao.view.inteface.ITimingView;


public class timingFragment extends Fragment implements ITimingView {
    TimingPresenter timingPresenter;
    List<TimingEntry> list;
    DrawerLayout drawerLayout;
    Button userButton;
    TextView time;
    ListView listView;
    TextView btn_add;
    TimingListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedtanceState) {
        View view = inflater.inflate(R.layout.fragment_timing_list, container, false);

        init(view);

        return view;
    }

    void init(View view){
        timingPresenter=new TimingPresenter(this);
        list=timingPresenter.getTimingEntrises();

        time=view.findViewById(R.id.txt_time);
        time.setText(getCurdate());

        btn_add=view.findViewById(R.id.btn_add_timing);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),NewTimingActivity.class);
                startActivity(intent);
            }
        });


        userButton=view.findViewById(R.id.userManagement);
        drawerLayout=getActivity().findViewById(R.id.drawerLayout);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserMsg();
            }
        });
        listView=view.findViewById(R.id.listView);
        adapter=new TimingListAdapter(getActivity(),list,timingPresenter);
        listView.setAdapter(adapter);
    }

    public void checkUserMsg() {
        MainActivity activity=  (MainActivity)getActivity();
        activity.setName();
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void refleshList(){
        adapter.reflesh(TimingEntry.getTimingEntries());
    }

    public static String getCurdate() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = formatter.format(curDate);
        return time;
    }


}
