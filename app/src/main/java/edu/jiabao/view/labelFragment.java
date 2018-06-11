package edu.jiabao.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import edu.jiabao.R;

public class labelFragment extends Fragment {
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
        userButton=view.findViewById(R.id.userManagement);
        drawerLayout=getActivity().findViewById(R.id.drawerLayout);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
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
        Log.d("ooo","213213");
        Fragment devices = new diviceFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout, devices, "device");
        fragmentTransaction.hide(fragmentManager.findFragmentByTag("label")).show(devices);
        fragmentTransaction.commit();
        super.onListItemClick(l, v, position, id);

    }*/


}
