package edu.jiabao.view.userManagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.x;

import java.lang.reflect.Type;

import edu.jiabao.ClientPermission;
import edu.jiabao.ControlApplication;
import edu.jiabao.R;
import edu.jiabao.http.Http;
import edu.jiabao.jsonObject.HttpRequest;
import edu.jiabao.jsonObject.UserObject;
import edu.jiabao.presenter.LoginPresenter;
import edu.jiabao.view.MainActivity;
import edu.jiabao.view.inteface.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    public static LoginActivity instance;
    TextView falseMsg;
    EditText phoneNum;
    EditText password;
    TextView register;
    Button enter;
    LoginPresenter presenter;

    public ControlApplication getApp(){
        return (ControlApplication) getApplicationContext();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ClientPermission.permissionManagement(this);

        instance=this;

        falseMsg=findViewById(R.id.falseMsg);
        phoneNum=findViewById(R.id.phoneNum);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        enter=findViewById(R.id.ok);
        presenter=new LoginPresenter(this,getApp().getUserDao());

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("onClick","onclick");
                enter.setEnabled(false);
                enter.setBackground(getResources().getDrawable(R.drawable.round_shape_16_gray,null));
                String pNum= phoneNum.getText().toString();
                String psw=password.getText().toString();
                if(pNum.equals("")||psw.equals("")) {
                    return;
                }
                Http.login(pNum, psw, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Type jsontype=new TypeToken<HttpRequest<UserObject>>(){}.getType();
                        HttpRequest<UserObject> rootbean=new Gson().fromJson(result,jsontype);
                        if(rootbean.getStatus().equals("SUCCESS")) {
                            UserObject userObject = rootbean.getData();
                            presenter.loginSuccess(userObject);
                        }else {
                            presenter.loginFalse();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ex.printStackTrace();
                        Toast.makeText(x.app(), "err", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        cex.printStackTrace();
                    }

                    @Override
                    public void onFinished() {
                        enter.setEnabled(true);
                        enter.setBackground(getResources().getDrawable(R.drawable.round_shape_16_blue,null));
                    }
                });
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.register();
            }
        });
    }


    @Override
    public void loginSucess() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFalse() {
        falseMsg.setVisibility(View.VISIBLE);
    }

    @Override
    public void register() {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }

}
