package edu.jiabao.view.label;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.UserEntry;
import edu.jiabao.presenter.DevicePresenter;
import edu.jiabao.view.adapter.DeviceListAdapter;
import edu.jiabao.view.inteface.IDeviceView;

public class deviceFragment extends Fragment implements IDeviceView {
    private ListView listView;
    private TextView tittle;
    private DevicePresenter presenter;
    private Button back;
    private DeviceListAdapter adapter;

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
        presenter=new DevicePresenter(this, UserEntry.getUserEntry().getUser_id());
        listView=view.findViewById(R.id.listView);
        tittle=view.findViewById(R.id.tittle);
        if (getArguments() != null) {
            if (getArguments().getString("type").equals("tem")){
                tittle.setText("温控类");
                presenter.initListView("label");
            }else if (getArguments().getString("type").equals("protect")){
                tittle.setText("安防类");
            }
            else if (getArguments().getString("type").equals("light")){
                tittle.setText("照明类");
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("onclick","ok!!!!");
            }
        });

        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragmentManager.findFragmentByTag("device"));
                fragmentTransaction.show(fragmentManager.findFragmentByTag("label"));
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void initListView(List<DeviceEntry> list) {
        adapter=new DeviceListAdapter(getActivity(),presenter,list);
        listView.setAdapter(adapter);
    }

    @Override
    public void reflesh(){
        adapter.reflesh();
    }


}
