package edu.jiabao.view.label;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.presenter.AirControlPresenter;
import edu.jiabao.view.inteface.IAirControlView;

public class ControlAirConditioning extends AppCompatActivity implements IAirControlView{
    private Button switch_btn;
    private AirControlPresenter presenter;
    @ViewInject(value=R.id.model_btn)
    private TextView model_btn;

    private TextView wind_size_btn;
    private TextView wind_dir_btn;
    private TextView wind_swip_btn;
    private TextView tem_dec_btn;
    private TextView tem_inc_btn;
    private TextView sleep_btn;
    private TextView more_btn;
    private TextView timing_btn;
    private TextView tem_text;
    private TextView model_text;
    private TextView wind_size_text;
    private TextView wind_dir_text;
    private TextView timing_opened_text;
    private TextView device_name_text;
    private TextView device_management_btn;

    public ControlApplication getApp(){
        return (ControlApplication) getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_air_conditioning);

        init();
    }

    void init(){
        x.view().inject(this);
        presenter=new AirControlPresenter(this,getApp().getFolderDao());
        //返回键
        TextView back=(TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //开关键
        switch_btn=findViewById(R.id.switch_btn);
        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("onClick","ok1");
                presenter.turnSwitchBtn();
            }
        });

        model_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public void turnSwitchBtn() {

    }

    @Override
    public void turnModelBtn() {

    }

    @Override
    public void changeWindSize() {

    }

    @Override
    public void changeWindDirection() {

    }

    @Override
    public void changeWindSwip() {

    }

    @Override
    public void addTemperature() {

    }

    @Override
    public void reduceTemperature() {

    }

    @Override
    public void setTiming() {

    }

    @Override
    public void setSleeping() {

    }

    @Override
    public void more() {

    }
}
