package edu.jiabao.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.List;

import edu.jiabao.dao.Dao;
import edu.jiabao.entry.TimingEntry;
import edu.jiabao.entry.UserEntry;
import edu.jiabao.http.Http;
import edu.jiabao.jsonObject.HttpRequest;
import edu.jiabao.jsonObject.tasksInformation;
import edu.jiabao.modle.ImpTimingModel;
import edu.jiabao.modle.inteface.ItimingModel;
import edu.jiabao.view.inteface.ITimingView;

public class TimingPresenter {
    ITimingView view;
    ItimingModel model;

    public TimingPresenter(ITimingView view ){
        this.view=view;
        model=new ImpTimingModel();
    }

    public List<TimingEntry> getTimingEntrises(){
        TimingEntry.DaoToEntry(Dao.TimingQueryAllTiming(UserEntry.getUserEntry().getUser_id()));
        return TimingEntry.getTimingEntries();
    }

    public void sentTiming(final TimingEntry entry){
        Http.sentTiming(entry, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type jsonType = new TypeToken<HttpRequest<tasksInformation>>() {}.getType();
                HttpRequest<tasksInformation> rootBean = new Gson().fromJson(result, jsonType);
                tasksInformation task= rootBean.getData();
                for (int i=0;i<task.getTasksInfo().size();i++){
                    entry.getTask_id_list().add(new Integer(task.getTasksInfo().get(i).getTaskId()));
                }
                model.turnOnTiminng(entry);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void cancelTiming(final TimingEntry entry){
        model.turnDownTiming(entry);
        Http.sentCancelTiming(entry, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                model.turnDownTiming(entry);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void deleteTiming(TimingEntry entry){
        model.deleteTiming(entry);
        view.refleshList();
    }
}
