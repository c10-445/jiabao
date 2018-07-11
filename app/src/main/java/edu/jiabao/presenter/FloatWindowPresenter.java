package edu.jiabao.presenter;

import org.xutils.common.Callback;

import edu.jiabao.entry.OperatorEntry;
import edu.jiabao.http.Http;
import edu.jiabao.modle.ImpDecicePackageModel;
import edu.jiabao.modle.ImpDeviceModel;
import edu.jiabao.modle.inteface.IDeviceModel;

public class FloatWindowPresenter {
    public void operator(final OperatorEntry entry){
        Http.usePackageOperator(entry, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                IDeviceModel deviceModel=new ImpDeviceModel();
                deviceModel.setDevicesByOperator(entry);
                //Log.i("operatorSize",String.valueOf(entry.getContent_list().size()));
                //Log.i("http","success!");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Log.i("http","err!");
                ex.printStackTrace();

            }

            @Override
            public void onCancelled(CancelledException cex) {
                //Log.i("http","cancel!");
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {
                ImpDecicePackageModel impDecicePackageModel=new ImpDecicePackageModel();
                impDecicePackageModel.refleshModel();
            }
        });
    }
}
