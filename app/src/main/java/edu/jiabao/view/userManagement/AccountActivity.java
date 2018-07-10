package edu.jiabao.view.userManagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.xutils.common.Callback;

import java.io.File;

import edu.jiabao.R;
import edu.jiabao.dao.Dao;
import edu.jiabao.entry.UserEntry;
import edu.jiabao.http.Http;

public class AccountActivity extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        context=this;

    }

    public void back(View view){
        finish();
    }

    public void historyCheck(View view){
        Intent intent=new Intent(this,HistoryActivity.class);
        startActivity(intent);
    }

    public void modifyPassword(View view){
        Intent intent=new Intent(this,ChangePasswordActivity.class);
        startActivity(intent);
    }


    public void download(View view){
        Http.downFile(UserEntry.getUserEntry().getUser_id().intValue(),context,new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Dao.initDatabaseByFile(result);
                new AlertDialog.Builder(context)
                        .setMessage("下载文件成功！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("onerr","ok");
                ex.printStackTrace();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.i("oncan","ok");
                cex.printStackTrace();
            }
            @Override
            public void onFinished() {

            }
            //网络请求之前回调
            @Override
            public void onWaiting() {

            }
            //网络请求开始的时候回调
            @Override
            public void onStarted() {
            }
            //下载的时候不断回调的方法
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //当前进度和文件总大小
                Log.i("JAVA","current："+ current +"，total："+total);
            }
        });
    }


    public void upload(View view){
        Http.uploadFile(UserEntry.getUserEntry().getUser_id().intValue(),context, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("onSuccess","ok");
                new AlertDialog.Builder(context)
                        .setMessage("上传成功！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("onerr","ok");
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i("oncan","ok");
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {
                Log.i("onfinish","ok");
            }
        });
    }
}
