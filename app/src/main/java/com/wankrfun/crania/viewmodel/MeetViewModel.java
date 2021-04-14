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
import com.wankrfun.crania.bean.ChallengeListBean;
import com.wankrfun.crania.bean.ChallengeStatusBean;
import com.wankrfun.crania.bean.ChallengeTemplateBean;
import com.wankrfun.crania.bean.EventsCreateBean;
import com.wankrfun.crania.bean.MeetListBean;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.http.retrofit.BaseRepository;
import com.wankrfun.crania.utils.JsonUtils;

import java.util.HashMap;
import java.util.List;

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
    public MutableLiveData<EventsEvent> whoLikeMeListLiveData;
    public MutableLiveData<ChallengeListBean> challengeListLiveData;
    public MutableLiveData<ChallengeTemplateBean> challengeTemplateLiveData;
    public MutableLiveData<ChallengeStatusBean> challengeStatusLiveData;
    public MutableLiveData<ChallengeStatusBean> challengeOpenOfCloseLiveData;
    public MutableLiveData<ChallengeStatusBean> challengeDeleteLiveData;
    public MutableLiveData<ChallengeStatusBean> challengeCreateLiveData;
    public MutableLiveData<ChallengeStatusBean> challengeEditLiveData;
    private final String userId;
    public int page = 0;
    public double longitude, latitude;

    public MeetViewModel() {
        userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        meetListLiveData = new MutableLiveData<>();
        meetUserCardLiveData = new MutableLiveData<>();
        matchingListLiveData = new MutableLiveData<>();
        unMatchingLiveData = new MutableLiveData<>();
        whoLikeMeListLiveData = new MutableLiveData<>();
        challengeListLiveData = new MutableLiveData<>();
        challengeTemplateLiveData = new MutableLiveData<>();
        challengeStatusLiveData = new MutableLiveData<>();
        challengeOpenOfCloseLiveData = new MutableLiveData<>();
        challengeDeleteLiveData = new MutableLiveData<>();
        challengeCreateLiveData = new MutableLiveData<>();
        challengeEditLiveData = new MutableLiveData<>();
    }

    /**
     * 获取遇见任务卡片
     * 测试数据：get-daily-personcards-helper
     * 正式地址：get-daily-personcards-v003
     */
    public void getMeetList(){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        ParseCloud.callFunctionInBackground("get-daily-personcards-v003", params, new FunctionCallback<Object>(){
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
    public void getMeetOperateLike(String newUserId, String operate, String image, String wish){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("newUserId", newUserId);//新用户id, 必选
        params.put("operate", operate);//String,喜欢: 'LIKE', 不喜欢: 'DISLIKE', 必选
        params.put("wish", wish);// String, 当前用户勾选的感兴趣的目标用户的心愿 的文字内容， 可选
        ParseCloud.callFunctionInBackground("v001-operateLike", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getMeetOperateLike: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean bean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    bean.getData().setImage(image);
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

    /**
     * 谁喜欢我的列表
     */
    public void getWhoLikeMe(){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("pageIndex", page);
        params.put("pageSize", 10);
        ParseCloud.callFunctionInBackground("v002-belikedList", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getWhoLikeMe: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    if (JsonUtils.getJsonString(new Gson().toJson(object), "code").equals("0")){
                        whoLikeMeListLiveData.postValue(new EventsEvent(JsonUtils.getJsonStringList(new Gson().toJson(object), "list"), JsonUtils.getJsonStringMap(new Gson().toJson(object), "list")));
                    }else {
                        ToastUtils.showShort(JsonUtils.getJsonString(new Gson().toJson(object), "error"));
                    }
                }else {
                    LogUtils.e("getWhoLikeMe: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 提交默契结果
     */
    public void getSubmitChallenge(String creatorId, List<String> challengeIds, List<Integer> progress, int score){
        HashMap<String, Object> params = new HashMap();
        params.put("interactorId", userId);//默契卡片创建者用户id,必选
        params.put("creatorId", creatorId);//默契卡片创建者用户id,必选
        params.put("challengeIds", challengeIds);//回答的默契卡片id数组， 必选
        params.put("progress", progress);//每题答对情况数组，为0表示对应题目未答对，为1表示答对，必选
        params.put("score", score);//0 或 1 或 2 或 3 ; 答题结果，必选
        params.put("platform", "Android");//用户所用设备系统, “iOS" : "Android", 必选
        ParseCloud.callFunctionInBackground("submit-match-interaction-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getSubmitChallenge: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                }else {
                    LogUtils.e("getSubmitChallenge: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取默契挑战开启状态
     */
    public void getChallengeStatus(){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        ParseCloud.callFunctionInBackground("check-match-challenge-status-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getChallengeStatus: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        challengeStatusLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getChallengeStatus: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 默契卡片列表
     */
    public void getChallengeList(){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("isLimit", "off");
        ParseCloud.callFunctionInBackground("get-match-challenge-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getChallengeList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeListBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeListBean.class);
                    if (bean.getCode() == 0){
                        challengeListLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getChallengeList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 开启/关闭默契挑战
     */
    public void getChallengeOpenOfClose(String setTo){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("setTo", setTo);//"on", "off" ; 开启或关闭 ,必选
        ParseCloud.callFunctionInBackground("toggle-match-challenge-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getChallengeOpenOfClose: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        challengeOpenOfCloseLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getChallengeOpenOfClose: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 创建默契挑战卡片
     */
    public void getChallengeCreate(String question, String choiceA, String choiceB, String chosen){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);//创建者用户id,必选
        params.put("question", question);//问题文字,必选
        params.put("choiceA", choiceA);//选项a文字,必选
        params.put("choiceB", choiceB);//选项b文字,必选
        params.put("chosen", chosen);//"A", "B" ; 创建者选择的答案a或b，必选
        params.put("isActive", "on");//是否为激活状态
        params.put("platform", "Android");//用户所用设备系统, “iOS" : "Android", 必选
        ParseCloud.callFunctionInBackground("create-match-challenge-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getChallengeCreate: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        challengeCreateLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getChallengeCreate: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 编辑默契挑战卡片
     */
    public void getChallengeEdit(String objectId, String question, String choiceA, String choiceB, String chosen){
        HashMap<String, Object> params = new HashMap();
        params.put("objectId", objectId);//目标默契卡片id,必选
        params.put("question", question);//问题文字,必选
        params.put("choiceA", choiceA);//选项a文字,必选
        params.put("choiceB", choiceB);//选项b文字,必选
        params.put("chosen", chosen);//"A", "B" ; 创建者选择的答案a或b，必选
        params.put("isActive", "on");//是否为激活状态
        ParseCloud.callFunctionInBackground("edit-match-challenge-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getChallengeEdit: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        challengeEditLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getChallengeEdit: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 删除默契挑战卡片
     */
    public void getChallengeDelete(String objectId){
        HashMap<String, Object> params = new HashMap();
        params.put("objectId", objectId);
        ParseCloud.callFunctionInBackground("delete-match-challenge-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getChallengeDelete: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeStatusBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeStatusBean.class);
                    if (bean.getCode() == 0){
                        challengeDeleteLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getChallengeDelete: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取默契卡片模版
     */
    public void getChallengeTemplate(){
        HashMap<String, Object> params = new HashMap();
        ParseCloud.callFunctionInBackground("get-template-challenge-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getChallengeTemplate: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    ChallengeTemplateBean bean = new Gson().fromJson(new Gson().toJson(object), ChallengeTemplateBean.class);
                    if (bean.getCode() == 0){
                        challengeTemplateLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getChallengeTemplate: " + e.getMessage());
                }
            }
        });
    }
}
