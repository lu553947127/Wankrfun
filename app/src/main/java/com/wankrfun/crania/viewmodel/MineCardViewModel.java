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
import com.wankrfun.crania.bean.ChallengeStatusBean;
import com.wankrfun.crania.bean.EventsJoinedListBean;
import com.wankrfun.crania.bean.MineEventsListBean;
import com.wankrfun.crania.bean.MineLifeListBean;
import com.wankrfun.crania.bean.QuestionListBean;
import com.wankrfun.crania.bean.QuestionTemplateListBean;
import com.wankrfun.crania.bean.WishListBean;
import com.wankrfun.crania.http.retrofit.BaseRepository;

import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.viewmodel
 * @ClassName: MineCardViewModel
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/9/21 1:47 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/9/21 1:47 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineCardViewModel extends BaseRepository {
    public MutableLiveData<String> pageStateLiveData;
    public MutableLiveData<String> failStateLiveData;
    public MutableLiveData<WishListBean> wishListLiveData;
    public MutableLiveData<ChallengeStatusBean> wishCreateLiveData;
    public MutableLiveData<ChallengeStatusBean> wishEditLiveData;
    public MutableLiveData<ChallengeStatusBean> wishDeleteLiveData;
    public MutableLiveData<MineLifeListBean> lifeListLiveData;
    public MutableLiveData<ChallengeStatusBean> lifeCreateLiveData;
    public MutableLiveData<MineEventsListBean> eventsListLiveData;
    public MutableLiveData<EventsJoinedListBean> eventsJoinedListLiveData;
    public MutableLiveData<ChallengeStatusBean> eventsCreateLiveData;
    public MutableLiveData<QuestionListBean> questionListLiveData;
    public MutableLiveData<ChallengeStatusBean> questionDeleteLiveData;
    public MutableLiveData<QuestionTemplateListBean> questionTemplateLiveData;
    public MutableLiveData<ChallengeStatusBean> questionCreateLiveData;
    public MutableLiveData<ChallengeStatusBean> questionEditLiveData;
    private final String userId;

    public MineCardViewModel() {
        userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        wishListLiveData = new MutableLiveData<>();
        wishCreateLiveData = new MutableLiveData<>();
        wishEditLiveData = new MutableLiveData<>();
        wishDeleteLiveData = new MutableLiveData<>();
        lifeListLiveData = new MutableLiveData<>();
        lifeCreateLiveData = new MutableLiveData<>();
        eventsListLiveData = new MutableLiveData<>();
        eventsJoinedListLiveData = new MutableLiveData<>();
        eventsCreateLiveData = new MutableLiveData<>();
        questionListLiveData = new MutableLiveData<>();
        questionDeleteLiveData = new MutableLiveData<>();
        questionTemplateLiveData = new MutableLiveData<>();
        questionCreateLiveData = new MutableLiveData<>();
        questionEditLiveData = new MutableLiveData<>();
    }

    /**
     * 获取心愿列表
     */
    public void getWishList(String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        ParseCloud.callFunctionInBackground("fetch-wishes-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getWishList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    WishListBean bean = new Gson().fromJson(new Gson().toJson(object), WishListBean.class);
                    if (bean.getCode() == 0){
                        wishListLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getWishList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 创建心愿
     */
    public void getWishCreate(String content, String color){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        params.put("content", content);//心愿内容,必选
        params.put("color", color);//生产的心愿颜色hex string,必选
        ParseCloud.callFunctionInBackground("create-wish-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getWishCreate: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        wishCreateLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getWishCreate: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 修改心愿
     */
    public void getWishEdit(String objectId, String content){
        HashMap<String, Object> params = new HashMap();
        params.put("objectId", objectId);//目标心愿id,必选
        params.put("content", content);//目标心愿content,必选
        ParseCloud.callFunctionInBackground("edit-wish-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getWishEdit: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        wishEditLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getWishEdit: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 删除心愿
     */
    public void getWishDelete(String objectId){
        HashMap<String, Object> params = new HashMap();
        params.put("objectId", objectId);//目标心愿id,必选
        ParseCloud.callFunctionInBackground("delete-wish-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getWishDelete: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        wishDeleteLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getWishDelete: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取生活瞬间列表
     */
    public void getLifeList(String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        params.put("limit", 9);
        ParseCloud.callFunctionInBackground("fetch-life-moments-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getLifeList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    MineLifeListBean bean = new Gson().fromJson(new Gson().toJson(object), MineLifeListBean.class);
                    if (bean.getCode() == 0){
                        lifeListLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getLifeList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 创建生活瞬间
     */
    public void getLifeCreate(String icon, String content, List<ParseFile> images){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        params.put("icon", icon);
        params.put("content", content);
        params.put("images", images);
        ParseCloud.callFunctionInBackground("create-life-moment-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getLifeCreate: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        lifeCreateLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getLifeCreate: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取活动瞬间列表
     */
    public void getEventsList(String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        params.put("limit", 9);
        ParseCloud.callFunctionInBackground("fetch-event-moments-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    MineEventsListBean bean = new Gson().fromJson(new Gson().toJson(object), MineEventsListBean.class);
                    if (bean.getCode() == 0){
                        eventsListLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getEventsList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取所有参与的活动列表
     */
    public void getEventsJoinedList(){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        ParseCloud.callFunctionInBackground("get-user-joined-events-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsJoinedList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsJoinedListBean bean = new Gson().fromJson(new Gson().toJson(object), EventsJoinedListBean.class);
                    if (bean.getCode() == 0){
                        eventsJoinedListLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getEventsJoinedList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 创建活动瞬间
     */
    public void getEventsCreate(String creatorId, String eventId, String eventType, String eventIcon, String content, List<ParseFile> images){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", creatorId);
        params.put("eventId", eventId);
        params.put("eventType", eventType);
        params.put("eventIcon", eventIcon);
        params.put("content", content);
        params.put("images", images);//images 图片内容, ParseFile array, 必选
        ParseCloud.callFunctionInBackground("create-event-moment-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsCreate: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        eventsCreateLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getEventsCreate: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取问答卡片列表
     */
    public void getQuestionList(String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        params.put("limit", 9);
        ParseCloud.callFunctionInBackground("fetch-bioQAs-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getQuestionList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    QuestionListBean bean = new Gson().fromJson(new Gson().toJson(object), QuestionListBean.class);
                    if (bean.getCode() == 0){
                        questionListLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getQuestionList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 删除问答
     */
    public void getQuestionDelete(String objectId){
        HashMap<String, Object> params = new HashMap();
        params.put("objectId", objectId);//目标QAid,必选
        ParseCloud.callFunctionInBackground("delete-bioQA-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getQuestionDelete: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        questionDeleteLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getQuestionDelete: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取问答模版列表
     */
    public void getQuestionTemplateList(String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        params.put("limit", 9);
        ParseCloud.callFunctionInBackground("get-template-bio-QA-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getQuestionTemplateList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    QuestionTemplateListBean bean = new Gson().fromJson(new Gson().toJson(object), QuestionTemplateListBean.class);
                    if (bean.getCode() == 0){
                        questionTemplateLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getQuestionTemplateList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 创建问答
     */
    public void getQuestionCreate(String question, String answer){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);//创建者用户id,必选
        params.put("question", question);//问题,必选
        params.put("answer", answer);//回答,必选
        ParseCloud.callFunctionInBackground("create-bioQA-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getQuestionCreate: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        questionCreateLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getQuestionCreate: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 编辑问答
     */
    public void getQuestionEdit(String objectId, String answer){
        HashMap<String, Object> params = new HashMap();
        params.put("objectId", objectId);//目标问答id,必选
        params.put("answer", answer);//回答,必选
        ParseCloud.callFunctionInBackground("edit-bioQA-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getQuestionEdit: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        questionEditLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getQuestionEdit: " + e.getMessage());
                }
            }
        });
    }
}
