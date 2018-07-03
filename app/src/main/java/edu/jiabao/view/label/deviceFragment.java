package edu.jiabao.view.label;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.UserEntry;
import edu.jiabao.presenter.DevicePresenter;
import edu.jiabao.view.MainActivity;
import edu.jiabao.view.adapter.DeviceListAdapter;
import edu.jiabao.view.inteface.IDeviceView;

public class deviceFragment extends Fragment implements IDeviceView {
    private ListView listView;
    private TextView tittle;
    private DevicePresenter presenter;

    public ControlApplication getApp(){
        return (ControlApplication) getActivity().getApplicationContext();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        presenter=new DevicePresenter(this,getApp().getDeviceDao(), UserEntry.getUserEntry().getUser_id());
        listView=view.findViewById(R.id.listView);
        tittle=view.findViewById(R.id.tittle);
        if (getArguments() != null) {
            if (getArguments().getString("type").equals("label")){
                tittle.setText("温控类");
                presenter.initListView("label");
            }else {
                tittle.setText(getArguments().getString("name"));
                presenter.initListView("home");
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("onclick","ok!!!!");
            }
        });


    }

    @Override
    public void initListView(List<DeviceEntry> list) {
        listView.setAdapter(new DeviceListAdapter(getActivity(),list));
    }

    public void userManage(View view){
        MainActivity activity=  (MainActivity)getActivity();
        activity.setName();
        DrawerLayout drawerLayout=getActivity().findViewById(R.id.drawerLayout);
        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
