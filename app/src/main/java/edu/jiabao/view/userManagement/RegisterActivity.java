package edu.jiabao.view.userManagement;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Type;

import edu.jiabao.R;
import edu.jiabao.http.Http;
import edu.jiabao.jsonObject.HttpRequest;
import edu.jiabao.jsonObject.UserObject;

public class RegisterActivity extends AppCompatActivity {
    @ViewInject(value = R.id.ok)
    private Button ok_btn;
    @ViewInject(value = R.id.phoneNum_edit)
    private EditText ph_num_edit;
    @ViewInject(value = R.id.password_edit)
    private EditText password_edit;
    @ViewInject(value = R.id.password_again_edit)
    private EditText password_again_edit;
    @ViewInject(value = R.id.nick_name_edit)
    private EditText nick_name_edit;
    @ViewInject(value = R.id.psw_err_msg)
    private TextView psw_err_msg;
    @ViewInject(value = R.id.err_msg)
    private TextView ph_err_msg;
    @ViewInject(value = R.id.back)
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init(){
        x.view().inject(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ok_btn.setEnabled(false);
                ok_btn.setBackground(getResources().getDrawable(R.drawable.round_shape_16_gray,null));
                String phNum= ph_num_edit.getText().toString();
                final String nick=nick_name_edit.getText().toString();
                String psw=password_edit.getText().toString();
                String pswAgain=password_again_edit.getText().toString();

                if (!psw.equals(pswAgain)){
                    //Log.i("onPassErr","111");
                    PswErr();
                    return;
                }
                Http.register(nick, phNum, psw, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Type type=new TypeToken< HttpRequest< UserObject>>(){}.getType();
                        HttpRequest<UserObject> rootbean=new Gson().fromJson(result,type);
                        if(rootbean.getStatus().equals("SUCCESS")) {
                           RegisterSuccess(rootbean);
                        }else {
                            PhoneErr();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        //Log.i("onErr","111");
                        ex.printStackTrace();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                       // Log.i("onCancel","111");
                        cex.printStackTrace();

                    }
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onFinished() {
                       // Log.i("onFinish","111");
                        ok_btn.setEnabled(true);
                        ok_btn.setBackground(getResources().getDrawable(R.drawable.round_shape_16_blue,null));
                    }
                });
            }
        });

    }

    private void PswErr(){
        ok_btn.setEnabled(true);
        ok_btn.setBackground(getResources().getDrawable(R.drawable.round_shape_16_blue,null));
        ph_err_msg.setVisibility(View.INVISIBLE);
        psw_err_msg.setVisibility(View.VISIBLE);
        password_edit.setText("");
        password_again_edit.setText("");
    }

    private void PhoneErr(){
        ph_err_msg.setVisibility(View.VISIBLE);
        psw_err_msg.setVisibility(View.INVISIBLE);
        ph_num_edit.setText("");
        password_edit.setText("");
        password_again_edit.setText("");
        nick_name_edit.setText("");
    }

    private void RegisterSuccess(HttpRequest<UserObject> rootbean){
        UserObject userObject = rootbean.getData();
        new AlertDialog.Builder(RegisterActivity.this)
                .setMessage("注册成功！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create().show();
    }
}
