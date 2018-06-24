package edu.jiabao.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.jiabao.R;
import edu.jiabao.presenter.AirControlPresenter;
import edu.jiabao.view.inteface.IAirControlView;

public class ControlAirConditioning extends AppCompatActivity implements IAirControlView{
    private TextView switch_btn;
    private AirControlPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_air_conditioning);

        init();
    }

    void init(){
        presenter=new AirControlPresenter(this);
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
