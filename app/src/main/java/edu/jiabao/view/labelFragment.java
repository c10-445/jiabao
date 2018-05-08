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
import edu.jiabao.view.adapter.LabelListAdapter;

public class labelFragment extends ListFragment {
    ArrayList<String> list=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("温控类");
        list.add("安防类");
        list.add("照明类");
        setHasOptionsMenu(true);
        setListAdapter(new LabelListAdapter(getActivity(),list));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_label_list, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.label_actionbar, menu);
    }

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

    }


}
