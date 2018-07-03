package edu.jiabao.view.userManagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.jiabao.R;

public class AccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

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
}
