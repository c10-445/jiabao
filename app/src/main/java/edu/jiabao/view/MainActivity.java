package edu.jiabao.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import edu.jiabao.R;

public class MainActivity extends AppCompatActivity implements IMainView{

    private FragmentManager fragmentManager;
    private Fragment homeFragment;
    private Fragment labelFragment;
    private Fragment timingFragment;
    //private DrawerLayout drawerLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment("home");
                    return true;
                case R.id.navigation_label:
                    showFragment("label");
                    return true;
                case R.id.navigation_timing:
                    showFragment("timing");
                    return true;
            }
            return false;
        }
    };

    void showFragment(String name){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch (name){
            case "home":
                setTitle("首页");
                fragmentTransaction.hide(labelFragment).hide(timingFragment).show(homeFragment);
                fragmentTransaction.commit();
                break;
            case "label":
                setTitle("分类");
                fragmentTransaction.hide(labelFragment).hide(homeFragment).show(labelFragment);
                fragmentTransaction.commit();
                break;
            case "timing":
                setTitle("定时");
                fragmentTransaction.hide(labelFragment).hide(homeFragment).show(timingFragment);
                fragmentTransaction.commit();
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_container);

        init();


    }

    void init(){
        homeFragment=new homeFragment();
        labelFragment=new labelFragment();
        timingFragment=new timingFragment();

        fragmentManager=this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout,homeFragment,"home");
        fragmentTransaction.add(R.id.fragment_layout,labelFragment,"label");
        fragmentTransaction.add(R.id.fragment_layout,timingFragment,"timing");
        fragmentTransaction.hide(labelFragment).hide(timingFragment).show(homeFragment);
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);;
        navigation.setBackgroundColor(getResources().getColor(R.color.colorHoloBlue));
        navigation.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorHoloBlue));
    }
}
