package edu.jiabao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.jiabao.R;
import edu.jiabao.presenter.LabelPresenter;
import edu.jiabao.view.adapter.LabelListAdapter;
import edu.jiabao.view.inteface.ILabelView;

public class labelFragment extends Fragment implements ILabelView {
    LabelPresenter labelPresenter;
    ArrayList<String> list=new ArrayList<>();
    DrawerLayout drawerLayout;
    Button userButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("温控类");
        list.add("安防类");
        list.add("照明类");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_label_list, container, false);

        init(view);

        return view;
    }

    void init(View view){
        ListView listView=view.findViewById(R.id.listView);
        listView.setAdapter(new LabelListAdapter(getActivity(),list));
        userButton=view.findViewById(R.id.userManagement);
        drawerLayout=getActivity().findViewById(R.id.drawerLayout);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ooo","213213");
                /*Fragment devices = new diviceFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_layout, devices, "device");
                fragmentTransaction.hide(fragmentManager.findFragmentByTag("label")).show(devices);
                fragmentTransaction.commit();*/
                Intent intent=new Intent(getActivity(),ControlAirConditioning.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.label_actionbar, menu);
    }
/*
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


    }*/


}
