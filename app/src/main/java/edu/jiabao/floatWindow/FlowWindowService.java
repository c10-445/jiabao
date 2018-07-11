package edu.jiabao.floatWindow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.jiabao.R;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.presenter.FloatWindowPresenter;

public class FlowWindowService extends Service {
    int heigth = 0;
    int width = 0;
    private static final String TAG = "MainService";
    List<OperatorEntry> operatorEntries;
    FloatWindowPresenter presenter;
    ConstraintLayout toucherLayout;
    WindowManager.LayoutParams params;
    WindowManager.LayoutParams params2;
    WindowManager windowManager;
    Button imageButton1;
    //状态栏高度.
    int statusBarHeight = -1;

    //不与Activity进行绑定.
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(TAG,"MainService Created");

    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId){
        operatorEntries=new ArrayList<>();
        heigth= intent.getIntExtra("height",0);
        width=intent.getIntExtra("width",0);
        int id1=intent.getIntExtra("operatorId1",-1);
        int id2=intent.getIntExtra("operatorId2",-1);
        int id3=intent.getIntExtra("operatorId3",-1);
        /*
        if (id1!=-1)
            operatorEntries.add(OperatorEntry.getOperatorEntryById(id1));
        if (id2!=-1)
            operatorEntries.add(OperatorEntry.getOperatorEntryById(id2));
        if (id3!=-1)
            operatorEntries.add(OperatorEntry.getOperatorEntryById(id3));
*/
      //  presenter =new FloatWindowPresenter();

        createToucher();
        return super.onStartCommand(intent,flags,startId);
    }

    private void createToucher()
    {
        //赋值WindowManager&LayoutParam.
        params = new WindowManager.LayoutParams();
        windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        //设置type.系统提示型窗口，一般都在应用程序窗口之上.
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //设置效果为背景透明.
        params.format = PixelFormat.RGBA_8888;
        //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置窗口初始停靠位置.
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = 0;
        params.y = 0;

        //设置悬浮窗口长宽数据.
        params.width = 300;
        params.height = 300;


        //赋值WindowManager&LayoutParam.
        params2 = new WindowManager.LayoutParams();
        params2.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //设置效果为背景透明.
        params2.format = PixelFormat.RGBA_8888;
        //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
        params2.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置窗口初始停靠位置.
        params2.gravity = Gravity.CENTER;
        params2.x = 0;
        params2.y = 0;

        //设置悬浮窗口长宽数据.
        params2.width = width;
        params2.height = heigth;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局.
        toucherLayout = (ConstraintLayout) inflater.inflate(R.layout.toucher_layout,null);

         final ConstraintLayout window=(ConstraintLayout) inflater.inflate(R.layout.float_window,null);

        //添加toucherlayout
        windowManager.addView(toucherLayout,params);
        windowManager.addView(window,params2);

        window.setVisibility(View.INVISIBLE);
        TextView background=window.findViewById(R.id.txt_operator_back);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        Button fold_one=window.findViewById(R.id.btn_fold_one);
        Button op_one=window.findViewById(R.id.btn_operator_one);
        Button fold_two=window.findViewById(R.id.btn_fold_two);
        Button op_two=window.findViewById(R.id.btn_operator_two);
        Button fold_three=window.findViewById(R.id.btn_fold_three);
        Button op_three=window.findViewById(R.id.btn_operator_three);

        Button open_app=window.findViewById(R.id.btn_open_app);

        //开启应用
        open_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent=new Intent(homeFragment.instance.getActivity(),MainActivity.class);
                startActivity(intent);*/
            }
        });

        fold_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //window.setVisibility(View.INVISIBLE);
            }
        });
/*
        if (!operatorEntries.isEmpty()) {
            PackageEntry entry= PackageEntry.getPackageById(operatorEntries.get(0).getFolder_id());
            fold_one.setText(entry.getFolder_name());
        }

        if (operatorEntries.size()>=1){
            PackageEntry entry= PackageEntry.getPackageById(operatorEntries.get(1).getFolder_id());
            fold_one.setText(entry.getFolder_name());
        }

        if (operatorEntries.size()>=2){
            PackageEntry entry= PackageEntry.getPackageById(operatorEntries.get(2).getFolder_id());
            fold_one.setText(entry.getFolder_name());
        }*/

        //第一个设备夹操作
        op_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (!operatorEntries.isEmpty())
                    presenter.operator(operatorEntries.get(0));*/
            }
        });

        //第二个设备夹操作
        op_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (operatorEntries.size()>=1)
                    presenter.operator(operatorEntries.get(1));*/
            }
        });

        //第三个设备夹操作
        op_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (operatorEntries.size()>=2)
                presenter.operator(operatorEntries.get(2));*/
            }
        });

        TextView imageButton=window.findViewById(R.id.image_float);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.setVisibility(View.INVISIBLE);
            }
        });
        //主动计算出当前View的宽高信息.
        toucherLayout.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);


        //用于检测状态栏高度.
        int resourceId = getResources().getIdentifier("status_bar_height","dimen","android");
        if (resourceId > 0)
        {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        Log.i(TAG,"状态栏高度为:" + statusBarHeight);


        //浮动窗口按钮.
        imageButton1 =  toucherLayout.findViewById(R.id.Image_button);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            long[] hints = new long[2];
            @Override
            public void onClick(View v) {
               window.setVisibility(View.VISIBLE);
            }
        });


        imageButton1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                params.x = (int) event.getRawX()-40;
                params.y = (int) event.getRawY()-40 - statusBarHeight;
                windowManager.updateViewLayout(toucherLayout,params);
                return false;
            }
        });
    }



    @Override
    public void onDestroy()
    {
        if (imageButton1 != null)
        {
            windowManager.removeView(toucherLayout);
        }
        super.onDestroy();
    }
}
