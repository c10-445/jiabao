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
import android.widget.ListView;

import java.util.ArrayList;

import edu.jiabao.R;
import edu.jiabao.view.adapter.HomeListAdapter;


public class homeFragment extends Fragment {
    Button userButton;
    DrawerLayout drawerLayout;
    ArrayList<String> list=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("大厅");
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);

        init(view);

        return view;
    }


    void init(View view){
        ListView listView=view.findViewById(R.id.listView);
        listView.setAdapter(new HomeListAdapter(getActivity(),list));
        userButton=view.findViewById(R.id.userManagement);
        drawerLayout=getActivity().findViewById(R.id.drawerLayout);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }
/*
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d("ooo","213213");
        Fragment devices = new diviceFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout, devices, "device");
        fragmentTransaction.hide(fragmentManager.findFragmentByTag("home")).show(devices);
        fragmentTransaction.commit();
        super.onListItemClick(l, v, position, id);


    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.home_actionbar, menu);
    }


}
