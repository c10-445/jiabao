package edu.jiabao.view;

import android.content.Intent;
import android.os.Bundle;
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
import edu.jiabao.view.adapter.TimingListAdapter;

public class diviceFragment extends ListFragment {
    ArrayList<String> list=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("空调");
        list.add("灯");
        setHasOptionsMenu(true);
        setListAdapter(new TimingListAdapter(getActivity(),list));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timing_list, container, false);
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
        if(position==1){
            Intent intent=new Intent(getActivity(),ControlLightActivity.class);
            startActivity(intent);
            super.onListItemClick(l, v, position, id);
        }else{
            Intent intent=new Intent(getActivity(),ControlAirConditioning.class);
            startActivity(intent);
            super.onListItemClick(l, v, position, id);
        }

    }


}
