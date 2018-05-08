package edu.jiabao.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.jiabao.R;
import edu.jiabao.view.adapter.HomeListAdapter;


public class homeFragment extends ListFragment {
    ArrayList<String> list=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("大厅");
        setHasOptionsMenu(true);
        setListAdapter (new HomeListAdapter(getActivity(),list));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);

        return view;
    }

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


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.home_actionbar, menu);
    }


}
