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
import com.wankrfun.crania.bean.EventsCreateBean;
import com.wankrfun.crania.bean.MeetListBean;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.http.retrofit.BaseRepository;
import com.wankrfun.crania.utils.JsonUtils;

import java.util.HashMap;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.viewmodel
 * @ClassName: MeetViewModel
 * @Description: 遇见
 * @Author: 鹿鸿祥
 * @CreateDate: 2/4/21 3:33 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/4/21 3:33 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetViewModel extends BaseRepository {

    public MutableLiveData<String> pageStateLiveData;
    public MutableLiveData<String> failStateLiveData;
    public MutableLiveData<MeetListBean> meetListLiveData;
    public MutableLiveData<EventsCreateBean> meetUserCardLiveData;
    public MutableLiveData<EventsEvent> matchingListLiveData;
    public MutableLiveData<EventsCreateBean> unMatchingLiveData;
    private final String userId;
    public int page = 0;

    public MeetViewModel() {
        userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        meetListLiveData = new MutableLiveData<>();
        meetUserCardLiveData = new MutableLiveData<>();
        matchingListLiveData = new MutableLiveData<>();
        unMatchingLiveData = new MutableLiveData<>();
    }

    /**
     * 获取遇见任务卡片
     */
    public void getMeetList(){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("longitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)));
        params.put("latitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)));
        ParseCloud.callFunctionInBackground("getDailyUserCard-abbv", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getMeetList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    MeetListBean meetListBean = new Gson().fromJson(new Gson().toJson(object), MeetListBean.class);
                    if (meetListBean.getCode() == 0){
                        meetListLiveData.postValue(meetListBean);
                    }else {
                        ToastUtils.showShort(meetListBean.getError());
                    }
                }else {
                    LogUtils.e("getMeetList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 更新或生成人物卡片
     */
    public void getMeetUploadCard(){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("longitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)));
        params.put("latitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)));
        ParseCloud.callFunctionInBackground("v001-upsertCard", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getMeetUploadCard: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                }else {
                    LogUtils.e("getMeetUploadCard: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 查看人物卡片后更新每日剩余查看次数
     */
    public void getMeetUserCard(){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        ParseCloud.callFunctionInBackground("viewedUserCard", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getMeetUserCard: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                }else {
                    LogUtils.e("getMeetUserCard: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 手势操作: 喜欢或不喜欢
     */
    public void getMeetOperateLike(String newUserId, String operate){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("newUserId", newUserId);//新用户id, 必选
        params.put("operate", operate);//String,喜欢: 'LIKE', 不喜欢: 'DISLIKE', 必选
        ParseCloud.callFunctionInBackground("v001-operateLike", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getMeetOperateLike: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean bean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (bean.getCode() == 0){
                        meetUserCardLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getMeetOperateLike: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 我的匹配列表
     */
    public void getMatchingList(){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("pageIndex", page);
        params.put("pageSize", 10);
        ParseCloud.callFunctionInBackground("v002-matchingList", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getMatchingList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    if (JsonUtils.getJsonString(new Gson().toJson(object), "code").equals("0")){
                        matchingListLiveData.postValue(new EventsEvent(JsonUtils.getJsonStringList(new Gson().toJson(object), "list"), JsonUtils.getJsonStringMap(new Gson().toJson(object), "list")));
                    }else {
                        ToastUtils.showShort(JsonUtils.getJsonString(new Gson().toJson(object), "error"));
                    }
                }else {
                    LogUtils.e("getMatchingList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 解除匹配
     */
    public void getUnMatching(String matchedUserId){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("newUserId", matchedUserId);//匹配方用户id,必选
        ParseCloud.callFunctionInBackground("card-unMatch", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUnMatching: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean bean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (bean.getCode() == 0){
                        unMatchingLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getUnMatching: " + e.getMessage());
                }
            }
        });
    }
}
