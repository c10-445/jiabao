package edu.jiabao.view.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import edu.jiabao.R;

public class NewPackActivity extends AppCompatActivity {

    @ViewInject(value = R.id.btn_cancel)
    public Button btn_cancel;
    @ViewInject(value = R.id.btn_ok)
    public Button btn_ok;
    @ViewInject(value = R.id.edit_name)
    public EditText name;

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pack);

        x.view().inject(this);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.height = dp2px(this,150);
        layoutParams.width = dp2px(this,300);
        layoutParams.y = dp2px(this,150);
        layoutParams.gravity = Gravity.TOP;
        getWindow().setAttributes(layoutParams);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                WindowManager.LayoutParams lp=homeFragment.instance.getActivity().getWindow().getAttributes();
                lp.alpha=1f;
                lp.dimAmount=0f;
                homeFragment.instance.getActivity().getWindow().setAttributes(lp);
                finish();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n= name.getText().toString();
                if(n.equals("")){
                    return;
                }else {
                    Intent i = new Intent();
                    i.putExtra("name", n);
                    setResult(RESULT_OK,i);
                    WindowManager.LayoutParams lp=homeFragment.instance.getActivity().getWindow().getAttributes();
                    lp.alpha=1f;
                    lp.dimAmount=0f;
                    homeFragment.instance.getActivity().getWindow().setAttributes(lp);
                    finish();
                }
            }
        });
    }
}
