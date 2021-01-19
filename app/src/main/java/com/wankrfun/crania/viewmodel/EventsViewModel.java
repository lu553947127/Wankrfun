package com.wankrfun.crania.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.event.BaseEvent;
import com.wankrfun.crania.event.ParseEvent;
import com.wankrfun.crania.http.retrofit.BaseRepository;

import java.util.HashMap;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.viewmodel
 * @ClassName: EventsViewModel
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/18/21 8:37 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/18/21 8:37 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsViewModel extends BaseRepository {

    public MutableLiveData<String> pageStateLiveData;
    public MutableLiveData<String> failStateLiveData;
    public MutableLiveData<ParseEvent> eventsLiveData;
    private String userId;
    private int page = 1;

    public EventsViewModel() {
        userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        eventsLiveData = new MutableLiveData<>();
    }

    /**
     * 获取活动列表
     *
     * @param longitude
     * @param latitude
     */
    public void getEventsList(double longitude,double latitude){
        page = 1;
        HashMap<String, Object> params = new HashMap();
        params.put("pageIndex", page);
        params.put("pageSize", 1);
        params.put("userId", userId);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        ParseCloud.callFunctionInBackground("v001-getEventCards", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    BaseEvent baseEvent = new Gson().fromJson(new Gson().toJson(object),BaseEvent.class);
//                    if (baseEvent.getCode() == 0){
//                        eventsLiveData.postValue(new ParseEvent(object));
//                    }else {
//                        ToastUtils.showShort(baseEvent.getData().getMsg());
//                    }
                }else {
//                    eventsLiveData.postValue(new ParseEvent(e.getMessage()));
                    LogUtils.e( "getEventsList: " + e.getMessage());
                }
            }
        });
    }
}
