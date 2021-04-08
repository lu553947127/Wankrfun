package com.wankrfun.crania.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.EventsCreateBean;
import com.wankrfun.crania.bean.UserEventsListBean;
import com.wankrfun.crania.bean.UserInfoBean;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.http.retrofit.BaseRepository;
import com.wankrfun.crania.utils.JsonUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.viewmodel
 * @ClassName: MineViewModel
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/10/21 8:07 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/10/21 8:07 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineViewModel extends BaseRepository {

    public MutableLiveData<String> pageStateLiveData;
    public MutableLiveData<String> failStateLiveData;
    public MutableLiveData<EventsEvent> userEventsListCreatedLiveData;
    public MutableLiveData<EventsEvent> userEventsListAppliedLiveData;
    public MutableLiveData<EventsEvent> userEventsListFavLiveData;
    public MutableLiveData<UserInfoBean> userInfoLiveData;
    public MutableLiveData<EventsCreateBean> userUploadNameLiveData;
    public MutableLiveData<EventsCreateBean> userUploadPhotoLiveData;
    public MutableLiveData<EventsCreateBean> userUploadAddressLiveData;
    public MutableLiveData<EventsCreateBean> userUploadJobLiveData;
    public MutableLiveData<EventsCreateBean> userUploadImagesLiveData;
    public MutableLiveData<EventsCreateBean> userFeedbackLiveData;
    private final String userId;

    public MineViewModel() {
        userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        userEventsListCreatedLiveData = new MutableLiveData<>();
        userEventsListAppliedLiveData = new MutableLiveData<>();
        userEventsListFavLiveData = new MutableLiveData<>();
        userInfoLiveData = new MutableLiveData<>();
        userUploadNameLiveData = new MutableLiveData<>();
        userUploadPhotoLiveData = new MutableLiveData<>();
        userUploadAddressLiveData = new MutableLiveData<>();
        userUploadJobLiveData = new MutableLiveData<>();
        userUploadImagesLiveData = new MutableLiveData<>();
        userFeedbackLiveData = new MutableLiveData<>();
    }

    /**
     * 获取个人中心我发起的活动
     */
    public void getUserEventsListCreated(String targetId){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);//提交信息的当前用户id
        params.put("targetId", targetId);//查询信息的目标用户id
        params.put("scope", "created");//String enum, 'created' 发起的 , 'applied' 报名的 , 'fav' 想去 的, 'joined' (参与的，报名的的子集 + 参与的） 必选
        ParseCloud.callFunctionInBackground("get-user-profile-events-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUserEventsList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    UserEventsListBean userEventsListBean = new Gson().fromJson(new Gson().toJson(object), UserEventsListBean.class);
                    if (JsonUtils.getJsonString(new Gson().toJson(object), "code").equals("0")){
                        userEventsListCreatedLiveData.postValue(new EventsEvent(JsonUtils.getJsonStringList(new Gson().toJson(object), "expiredList"), JsonUtils.getJsonStringMap(new Gson().toJson(object), "expiredList"), userEventsListBean));
                    }else {
                        ToastUtils.showShort(JsonUtils.getJsonString(new Gson().toJson(object), "error"));
                    }
                }else {
                    LogUtils.e("getUserEventsList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取个人中心我申请的活动
     */
    public void getUserEventsListApplied(String targetId){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);//提交信息的当前用户id
        params.put("targetId", targetId);//查询信息的目标用户id
        params.put("scope", "applied");//String enum, 'created' 发起的 , 'applied' 报名的 , 'fav' 想去 的, 'joined' (参与的，报名的的子集 + 参与的） 必选
        ParseCloud.callFunctionInBackground("get-user-profile-events-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUserEventsList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    UserEventsListBean userEventsListBean = new Gson().fromJson(new Gson().toJson(object), UserEventsListBean.class);
                    if (userEventsListBean.getCode() == 0){
                        userEventsListAppliedLiveData.postValue(new EventsEvent(JsonUtils.getJsonStringList(new Gson().toJson(object), "expiredList"), JsonUtils.getJsonStringMap(new Gson().toJson(object), "expiredList"), userEventsListBean));
                    }else {
                        ToastUtils.showShort(userEventsListBean.getError());
                    }
                }else {
                    LogUtils.e("getUserEventsList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取个人中心我想去的活动
     */
    public void getUserEventsListFav(String targetId){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);//提交信息的当前用户id
        params.put("targetId", targetId);//查询信息的目标用户id
        params.put("scope", "fav");//String enum, 'created' 发起的 , 'applied' 报名的 , 'fav' 想去 的, 'joined' (参与的，报名的的子集 + 参与的） 必选
        ParseCloud.callFunctionInBackground("get-user-profile-events-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUserEventsList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    UserEventsListBean userEventsListBean = new Gson().fromJson(new Gson().toJson(object), UserEventsListBean.class);
                    if (userEventsListBean.getCode() == 0){
                        userEventsListFavLiveData.postValue(new EventsEvent(JsonUtils.getJsonStringList(new Gson().toJson(object), "expiredList"), JsonUtils.getJsonStringMap(new Gson().toJson(object), "expiredList"), userEventsListBean));
                    }else {
                        ToastUtils.showShort(userEventsListBean.getError());
                    }
                }else {
                    LogUtils.e("getUserEventsList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取个人详情
     */
    public void getUserInfo(String targetId){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);//提交信息的当前用户id
        params.put("targetId", targetId);//查询信息的目标用户id
        ParseCloud.callFunctionInBackground("get-user-profile-basic-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUserInfo: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    UserInfoBean userInfoBean = new Gson().fromJson(new Gson().toJson(object), UserInfoBean.class);
                    if (userInfoBean.getCode() == 0){
                        userInfoLiveData.postValue(userInfoBean);
                    }else {
                        ToastUtils.showShort(userInfoBean.getError());
                    }
                }else {
                    LogUtils.e("getUserInfo: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 修改个人昵称
     */
    public void getUploadName(String content){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("content", content);//新昵称，必选 （对应 “name”）
        ParseCloud.callFunctionInBackground("edit-user-profile-name", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUploadName: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean bean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (bean.getCode() == 0){
                        userUploadNameLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getUploadName: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 修改个人头像
     */
    public void getUploadPhoto(ParseFile parseFile, String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("content", parseFile); //ParseFile, 新头像，必选
        ParseCloud.callFunctionInBackground("edit-user-profile-photo", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUploadPhoto: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean bean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (bean.getCode() == 0){
                        userUploadPhotoLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getUploadPhoto: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 修改个人位置
     */
    public void getUploadAddress(String content){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("content", content); //新家乡位置，必选 （对应"address"）
        ParseCloud.callFunctionInBackground("edit-user-profile-address", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUploadAddress: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean bean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (bean.getCode() == 0){
                        userUploadAddressLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getUploadAddress: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 修改个人职业
     */
    public void getUploadJob(String content){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("content", content); //新家乡位置，必选 （对应"address"）
        ParseCloud.callFunctionInBackground("edit-user-profile-job", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUploadJob: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean bean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (bean.getCode() == 0){
                        userUploadJobLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getUploadJob: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 修改个人相册图片
     */
    public void getUploadImages(List<Object> images, String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("content", images); //新相册图片array，必选
        ParseCloud.callFunctionInBackground("edit-user-profile-images", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getUploadImages: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean bean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (bean.getCode() == 0){
                        userUploadImagesLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getUploadImages: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 意见反馈
     */
    public void getFeedback(String content){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("type", 9);
        params.put("content", content);
        ParseCloud.callFunctionInBackground("applyForm", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getFeedback: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean bean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (bean.getCode() == 0){
                        userFeedbackLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getFeedback: " + e.getMessage());
                }
            }
        });
    }
}
