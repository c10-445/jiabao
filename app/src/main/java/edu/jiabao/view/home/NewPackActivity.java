package edu.jiabao.view.home;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.time.Instant;

import edu.jiabao.R;

public class NewPackActivity extends AppCompatActivity {

    public Button btn_cancel=findViewById(R.id.btn_cancel);

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pack);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.height = dp2px(this,150);
        layoutParams.width = dp2px(this,300);
        layoutParams.y = dp2px(this,100);
        layoutParams.gravity = Gravity.TOP;
        getWindow().setAttributes(layoutParams);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WindowManager.LayoutParams lp=homeFragment.instance.getActivity().getWindow().getAttributes();
                lp.alpha=1f;
                lp.dimAmount=0f;
                homeFragment.instance.getActivity().getWindow().setAttributes(lp);
                finish();
            }
        });
    }
}
