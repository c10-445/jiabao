package edu.jiabao.view.userManagement;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import edu.jiabao.R;
import edu.jiabao.floatWindow.FlowWindowService;

public class SettingActivity extends AppCompatActivity {
    public int height;
    public int width;
    private Switch float_switch;
    private TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        height = dm.heightPixels;
        width = dm.widthPixels;
        float_switch =findViewById(R.id.float_switch);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        float_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (Settings.canDrawOverlays(SettingActivity.this)) {

                        Intent intent = new Intent(SettingActivity.this, FlowWindowService.class);
                        Log.i("activity", "h" + height);
                        intent.putExtra("height", height);
                        intent.putExtra("width", width);
                        Toast.makeText(SettingActivity.this, "已开启Toucher", Toast.LENGTH_SHORT).show();
                        startService(intent);
                        //finish();
                    } else {
                        //若没有权限，提示获取.
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        Log.i("activity", "h" + height);
                        intent.putExtra("height", height);
                        intent.putExtra("width", width);
                        Toast.makeText(SettingActivity.this, "需要取得权限以使用悬浮窗", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        //finish();
                    }
                }else {
                    Intent intent = new Intent(SettingActivity.this, FlowWindowService.class);
                    intent.putExtra("height", height);
                    intent.putExtra("width", width);
                    stopService(intent);
                }
            }
        });

    }
}
