package edu.jiabao.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.jiabao.R;
import edu.jiabao.view.adapter.TimingListAdapter;


public class timingFragment extends Fragment {
    ArrayList<String> list=new ArrayList<>();
    DrawerLayout drawerLayout;
    Button userButton;
    TextView textView;
    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("回家");
        list.add("吃饭");
        list.add("上学");

        //setListAdapter (new TimingListAdapter(getActivity(),list));
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedtanceState) {
        View view = inflater.inflate(R.layout.fragment_timing_list, container, false);

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
        textView= view.findViewById(R.id.tittle);
        listView=view.findViewById(R.id.listView);
        listView.setAdapter(new TimingListAdapter(getActivity(),list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("lihui","ok");
            }
        });
    }


}
