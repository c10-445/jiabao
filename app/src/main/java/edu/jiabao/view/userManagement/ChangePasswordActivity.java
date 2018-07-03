package edu.jiabao.view.userManagement;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.http.Http;
import edu.jiabao.jsonObject.HttpRequest;
import edu.jiabao.modle.ImpUserModel;
import edu.jiabao.modle.inteface.IuserModel;

public class ChangePasswordActivity extends AppCompatActivity {
    @ViewInject(value=R.id.old_password_edit)
    private EditText oldpsw;
    @ViewInject(value = R.id.new_password_edit)
    private EditText newpsw;
    @ViewInject(value = R.id.new_password_again_edit)
    private EditText newpswAgain;
    @ViewInject(value = R.id.falseMsg)
    private TextView falseMsg;
    @ViewInject(value = R.id.password_falseMsg)
    private TextView pswFalseMsg;


    public ControlApplication getApp(){
        return (ControlApplication) getApplicationContext();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        x.view().inject(this);
    }

    public void back(View view){
        finish();
    }

    public void enter(View view){
        String oldpass= oldpsw.getText().toString();
        String newpass= newpsw.getText().toString();
        String newpassagain= newpswAgain.getText().toString();
        if(!newpass.equals(newpassagain)){
            pswFalseMsg.setVisibility(View.INVISIBLE);
            falseMsg.setVisibility(View.VISIBLE);
            newpsw.setText("");
            newpswAgain.setText("");
            newpsw.setSelectAllOnFocus(true);
            return;
        }

        IuserModel userModel=new ImpUserModel(getApp().getUserDao());
        int userid= userModel.getUserId().intValue();
        Http.modifyPassword(userid, oldpass, newpass, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                HttpRequest rootbean= new Gson().fromJson(result, HttpRequest.class);
                if(rootbean.getStatus().equals("SUCCESS")){
                    new AlertDialog.Builder(ChangePasswordActivity.this)
                            .setMessage("修改密码成功！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .create().show();
                }else {
                    pswFalseMsg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {
                falseMsg.setVisibility(View.INVISIBLE);
            }
        });
    }


}
