package edu.jiabao.view.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import edu.jiabao.R;
import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.entry.OperatorItemBean;
import edu.jiabao.entry.PackageEntry;
import edu.jiabao.modle.ImpOperatorModel;
import edu.jiabao.modle.inteface.IOperatorModel;
import edu.jiabao.view.adapter.OperatorListAdapter;

public class NewOperationActivity extends AppCompatActivity {
    private PackageEntry entry;
    private ListView listView;
    private EditText name;
    private OperatorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_operation);

        int id=getIntent().getExtras().getInt("packageId");
        entry=PackageEntry.getPackageById(id);
        adapter=new OperatorListAdapter(this,entry);

        listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);
        name=findViewById(R.id.edit_name);

    }

    public void back(View view){
        finish();
    }

    public void ok(View view){
        String n=name.getText().toString();
        if (n.isEmpty()||n.equals("")){
            return;
        }
        else {
            List<OperatorItemBean> l= adapter.getOperatorList();
            if(l.isEmpty())
                return;
            else {
                IOperatorModel model=new ImpOperatorModel();
                model.addOperator(new OperatorEntry(-1,
                        n,
                        entry.getFolder_id(),
                        entry.getUser_id(),
                        l));
            }
        }
        finish();
    }
}
