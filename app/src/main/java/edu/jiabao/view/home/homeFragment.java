package edu.jiabao.view.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.modle.ImpUserModel;
import edu.jiabao.presenter.HomePresenter;
import edu.jiabao.view.MainActivity;
import edu.jiabao.view.adapter.HomeListAdapter;
import edu.jiabao.view.inteface.IHomeView;
import edu.jiabao.view.label.deviceFragment;

import static android.app.Activity.RESULT_OK;


public class homeFragment extends Fragment implements IHomeView {
    private int REQUEST_CODE_SCAN = 111;
    HomePresenter homePresenter;
    Button userButton;
    TextView moreButton;
    DrawerLayout drawerLayout;
    ArrayList<String> list=new ArrayList<>();

    public ControlApplication getApp(){
        return (ControlApplication) getActivity().getApplicationContext();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("大厅");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);

        init(view);

        return view;
    }


    void init(View view){
        homePresenter=new HomePresenter(this,getApp().getFolderDao(),getApp().getDeviceDao());
        ListView listView=view.findViewById(R.id.listView);
        listView.setAdapter(new HomeListAdapter(getActivity(),list,homePresenter));
        userButton=view.findViewById(R.id.userManagement);
        drawerLayout=getActivity().findViewById(R.id.drawerLayout);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserMsg();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //我们需要的内容，跳转页面或显示详细信息
                Log.d("ooo","213213");
                        Fragment devices = new deviceFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.fragment_layout, devices, "device");
                        fragmentTransaction.hide(fragmentManager.findFragmentByTag("home")).show(devices);
                        fragmentTransaction.commit();
            }
        });


        moreButton=view.findViewById(R.id.more_btn);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu( getActivity(),view);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.home_menu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.add_device:
                                homePresenter.showSwipWindow();
                                break;
                            case R.id.add_package:
                                homePresenter.addPackage();
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {

            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                homePresenter.addDevice(ImpUserModel.getUser().getUser_id().intValue(),content);
                new AlertDialog.Builder(getActivity())
                        .setMessage("添加设备成功！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();
            }

        }

    }

    @Override
    public void showSwipWindow() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        ZxingConfig config = new ZxingConfig();
        config.setPlayBeep(true);//是否播放扫描声音 默认为true
        config.setShake(true);//是否震动  默认为true
        config.setDecodeBarCode(false);//是否扫描条形码 默认为true
        config.setReactColor(R.color.white);//设置扫描框四个角的颜色 默认为淡蓝色
        config.setFrameLineColor(R.color.white);//设置扫描框边框颜色 默认无色
        config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }


    public void checkUserMsg() {
        MainActivity activity=  (MainActivity)getActivity();
        activity.setName();
        drawerLayout.openDrawer(Gravity.LEFT);
    }

}
