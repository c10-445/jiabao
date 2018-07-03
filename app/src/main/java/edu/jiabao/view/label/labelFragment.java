package edu.jiabao.view.label;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.presenter.LabelPresenter;
import edu.jiabao.view.MainActivity;
import edu.jiabao.view.adapter.LabelListAdapter;
import edu.jiabao.view.inteface.ILabelView;

public class labelFragment extends Fragment implements ILabelView {
    LabelPresenter labelPresenter;
    ArrayList<String> list=new ArrayList<>();
    DrawerLayout drawerLayout;
    Button userButton;

    public ControlApplication getApp(){
        return (ControlApplication) getActivity().getApplicationContext();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("温控类");
        list.add("安防类");
        list.add("照明类");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_label_list, container, false);

        init(view);

        return view;
    }

    void init(View view){
        labelPresenter=new LabelPresenter(this,getApp().getUserDao());
        ListView listView=view.findViewById(R.id.listView);
        listView.setAdapter(new LabelListAdapter(getActivity(),list));


        userButton=view.findViewById(R.id.userManagement);
        drawerLayout=getActivity().findViewById(R.id.drawerLayout);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labelPresenter.checkUserMsg();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ooo","213213");
                Fragment devices = new deviceFragment();
                Bundle bundle= new Bundle();
                bundle.putString("type","label");
                devices.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_layout, devices, "device");
                fragmentTransaction.hide(fragmentManager.findFragmentByTag("label")).show(devices);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void checkUserMsg() {
         MainActivity activity=  (MainActivity)getActivity();
         activity.setName();
        drawerLayout.openDrawer(Gravity.LEFT);
    }


}
