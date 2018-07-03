package edu.jiabao.view.label;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.x;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.presenter.AirControlPresenter;
import edu.jiabao.view.inteface.IAirControlView;

public class ControlAirConditioning extends AppCompatActivity implements IAirControlView{
    private Button switch_btn;
    private AirControlPresenter presenter;
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

    }

    public void back(View view){
        finish();
    }


    public void TurnSwitchBtn(View view){
        presenter.turnSwitchBtn();
    }

    public void TurnRunModel(View view){

    }

    public void ChangeWindSize(View view){

    }


    public void ChangeWindDirection(View view){

    }

    public void ChangeWindSwip(View view){

    }

    public void AddTemperature(View view){

    }


    public void ReduceTemperature(View view){

    }

    public void SetTimming(View view){

    }

    public void SetSleeping(View view){

    }

    public void More(View view){

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
