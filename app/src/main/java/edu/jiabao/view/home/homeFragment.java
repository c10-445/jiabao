package edu.jiabao.view.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.modle.ImpUserModel;
import edu.jiabao.presenter.HomePresenter;
import edu.jiabao.view.MainActivity;
import edu.jiabao.view.adapter.HomeListAdapter;
import edu.jiabao.view.inteface.IHomeView;
import edu.jiabao.view.label.ControlAirConditioning;

import static android.app.Activity.RESULT_OK;


public class homeFragment extends Fragment implements IHomeView {
    private int REQUEST_CODE_SCAN = 111;
    private int ADDPACK=1;
    private int ADDDEVICE=11;
    private int parentsId=-1;
    String content;
    HomePresenter homePresenter;
    Button userButton;
    TextView moreButton;
    DrawerLayout drawerLayout;
    List<PackageEntry> list=new ArrayList<PackageEntry>();
    HomeListAdapter adapter;
    ListView listView;
    public static homeFragment instance;

    public ControlApplication getApp(){
        return (ControlApplication) getActivity().getApplicationContext();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);
        init(view);
        return view;
    }

    void init(View view){
        homePresenter=new HomePresenter(this);
        listView=view.findViewById(R.id.listView);
        homePresenter.initHomeView();

        userButton=view.findViewById(R.id.userManagement);
        drawerLayout=getActivity().findViewById(R.id.drawerLayout);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserMsg();
            }
        });

        instance=this;

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
                                //弹窗并将界面变暗
                                Intent i=new Intent(getActivity(),NewPackActivity.class);
                                startActivityForResult(i,ADDPACK);
                                WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                                lp.alpha = 0.5f;
                                lp.dimAmount = 0.5f;
                                getActivity().getWindow().setAttributes(lp);
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public void onResume() {
        homePresenter.initHomeView();
        //Log.i("OnResume","ok");
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {//二维码

            if (data != null) {
                content = data.getStringExtra(Constant.CODED_CONTENT);
                //弹窗并将界面变暗
                Intent i=new Intent(getActivity(),NewDeviceActivity.class);
                startActivityForResult(i,ADDDEVICE);
                WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                lp.alpha = 0.5f;
                lp.dimAmount = 0.5f;
                getActivity().getWindow().setAttributes(lp);
            }

        }
        else if(requestCode==ADDPACK&&resultCode==RESULT_OK){//输入设备夹名
            String n= data.getStringExtra("name");
            Log.i("onActivity",n);
            homePresenter.addPackage(new PackageEntry(n,parentsId));
        }
        else if(requestCode==ADDDEVICE&&resultCode==RESULT_OK){//输入设备名
            String n= data.getStringExtra("name");
            if(content!=null&&!content.isEmpty()) {
                int userId=ImpUserModel.getUser().getUser_id().intValue();
                homePresenter.addDevice(content,new DeviceEntry(userId,n),parentsId);
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

    @Override
    public void refleshListView(List<PackageEntry> _l){
        adapter.reflesh(_l);
    }

    @Override
    public void initHomeListView(List<PackageEntry> _l){
        list=_l;
        adapter=new HomeListAdapter(getActivity(),list,homePresenter);
        listView.setAdapter(adapter);
    }


    @Override
    public void showDeviceActivity(DeviceEntry entry) {
        Intent intent=new Intent(getActivity(),ControlAirConditioning.class);
        intent.putExtra("deviceId",entry.getDevice_id());
        startActivity(intent);
    }

    @Override
    public void showPackageFragment(PackageEntry entry) {
        showFragment(entry);
    }

    public void checkUserMsg() {
        MainActivity activity=  (MainActivity)getActivity();
        activity.setName();
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void showFragment(PackageEntry entry){
        Fragment packageFragment = new PackageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("folderId",entry.getFolder_id());
        packageFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout, packageFragment, "package"+String.valueOf(entry.getFolder_id()));
        fragmentTransaction.hide(this).show(packageFragment);
        fragmentTransaction.commit();

    }


}
