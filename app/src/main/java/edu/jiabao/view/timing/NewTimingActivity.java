package edu.jiabao.view.timing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.jiabao.R;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.OperatorItemBean;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.entry.TimingEntry;
import edu.jiabao.entry.UserEntry;
import edu.jiabao.modle.ImpTimingModel;
import edu.jiabao.modle.inteface.ItimingModel;
import edu.jiabao.view.adapter.OperatorListAdapter;

public class NewTimingActivity extends AppCompatActivity {
    private ListView listView;
    private EditText name;
    private EditText time;
    private OperatorListAdapter adapter;
    private TextView btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timing);

        adapter=new OperatorListAdapter(this, PackageEntry.getPackageEntries());

        listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);
        name=findViewById(R.id.edit_name);
        time=findViewById(R.id.edit_time);
        time.setText(getCurdate());
        btn_ok=findViewById(R.id.btn_timing_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String d=time.getText().toString();
                Date date=getDate(getCurdatefore()+" "+d);
                if (n.isEmpty()||n.equals("")||d.isEmpty()||d.equals("")){
                    return;
                }
                else {
                    List<OperatorItemBean> l= adapter.getOperatorList();
                    Log.i("l_size",String.valueOf(l.size()));
                    List<OperatorItemBean> list= OperatorEntry.getBaseOperator(new OperatorEntry(-1,
                            n,
                            -1,
                            UserEntry.getUserEntry().getUser_id().intValue(),
                            l));

                    if(list.isEmpty())
                        return;
                    else {
                        Log.i("size",String.valueOf(list.size()));
                        List<Integer> in=new ArrayList<>();
                        ItimingModel model=new ImpTimingModel();
                        model.addTiming(new TimingEntry(-1,
                                n,
                                false,
                                UserEntry.getUserEntry().getUser_id().intValue(),
                                list,
                                in,
                                date));
                    }
                }
                finish();
            }
        });
    }

    public void back(View view){
        finish();
    }

    public static String getCurdate() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = formatter.format(curDate);
        return time;
    }


    public static String getCurdatefore() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = formatter.format(curDate);
        return time;
    }
    public static Date getDate(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
