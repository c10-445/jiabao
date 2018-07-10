package edu.jiabao.view.label;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.entry.DeviceEntry;
import edu.jiabao.presenter.AirControlPresenter;
import edu.jiabao.view.inteface.IAirControlView;

public class ControlAirConditioning extends AppCompatActivity implements IAirControlView{
    private int deviceId=1;
    private DeviceEntry entry;
    private AirControlPresenter presenter;
    @ViewInject(value = R.id.tem_text)
    private TextView tem_text;
    @ViewInject(value = R.id.model_text)
    private TextView model_text;
    @ViewInject(value = R.id.wind_size_text)
    private TextView wind_size_text;
    @ViewInject(value = R.id.wind_model_text)
    private TextView wind_dir_text;
    @ViewInject(value = R.id.device_name)
    private TextView device_name_text;

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
        deviceId=getIntent().getExtras().getInt("deviceId");
        entry= DeviceEntry.getDevice(deviceId);
        refleshView();
        presenter=new AirControlPresenter(this,entry);

    }

    public void back(View view){
        finish();
    }


    public void TurnSwitchBtn(View view){
        presenter.turnSwitchBtn();
    }

    public void TurnRunModel(View view){
        presenter.turnModelBtn();
    }

    public void ChangeWindSize(View view){
        presenter.changeWindSize();
    }


    public void ChangeWindDirection(View view){

    }

    public void ChangeWindSwip(View view){

    }

    public void AddTemperature(View view){
        presenter.addTemperature();
    }


    public void ReduceTemperature(View view){
        presenter.reduceTemperature();
    }

    public void SetTimming(View view){

    }

    public void SetSleeping(View view){

    }

    public void More(View view){

    }

    @Override
    public void refleshView(){
        entry= DeviceEntry.getDevice(deviceId);
        if(entry.getTurn_on())
            tem_text.setText(String.valueOf(entry.getDegree()));
        else
            tem_text.setText("--");
        switch (entry.getRun_model()){
            case 0:
                model_text.setText("自动");
                break;
            case 1:
                model_text.setText("制热");
                break;
            case 2:
                model_text.setText("除湿");
                break;
            case 3:
                model_text.setText("制冷");
                break;
            case 4:
                model_text.setText("送风");
                break;
        }
        switch(entry.getWind_size()){
            case 0:
                wind_size_text.setText("自动");
                break;
            case 1:
                wind_size_text.setText("低速");
                break;
            case 2:
                wind_size_text.setText("中速");
                break;
            case 3:
                wind_size_text.setText("高速");
                break;
            case 4:
                wind_size_text.setText("其他");
                break;
        }
        device_name_text.setText( String.valueOf(entry.getName()));
    }

}
