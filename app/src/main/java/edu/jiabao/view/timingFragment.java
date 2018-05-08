package edu.jiabao.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.jiabao.R;
import edu.jiabao.view.adapter.TimingListAdapter;


public class timingFragment extends ListFragment {
    ArrayList<String> list=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("回家");
        list.add("吃饭");
        list.add("上学");
        setHasOptionsMenu(true);
        setListAdapter (new TimingListAdapter(getActivity(),list));
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedtanceState) {
        View view = inflater.inflate(R.layout.fragment_timing_list, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.timing_actionbar, menu);
    }

}
