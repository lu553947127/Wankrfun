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
import com.wankrfun.crania.bean.EventsAcceptBean;
import com.wankrfun.crania.bean.EventsApplyBean;
import com.wankrfun.crania.bean.EventsBean;
import com.wankrfun.crania.bean.EventsCommentsBean;
import com.wankrfun.crania.bean.EventsCreateBean;
import com.wankrfun.crania.bean.EventsDetailBean;
import com.wankrfun.crania.bean.EventsParticipantsBean;
import com.wankrfun.crania.bean.EventsStateBean;
import com.wankrfun.crania.http.retrofit.BaseRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public MutableLiveData<EventsBean> eventsLiveData;
    public MutableLiveData<EventsDetailBean> eventsDetailLiveData;
    public MutableLiveData<EventsParticipantsBean> eventsSponsorLiveData;
    public MutableLiveData<EventsParticipantsBean> eventsParticipantsLiveData;
    public MutableLiveData<EventsCommentsBean> eventsCommentsLiveData;
    public MutableLiveData<EventsStateBean> eventsStateLiveData;
    public MutableLiveData<EventsApplyBean> eventsApplyLiveData;
    public MutableLiveData<EventsAcceptBean> eventsAcceptLiveData;
    public MutableLiveData<EventsCreateBean> eventsCreateLiveData;
    public MutableLiveData<EventsCreateBean> eventsEditLiveData;
    public MutableLiveData<EventsCreateBean> eventsEventDeleteLiveData;
    public MutableLiveData<EventsCreateBean> eventsEventEndLiveData;
    public MutableLiveData<EventsCreateBean> eventsAddCommentLiveData;
    public MutableLiveData<EventsCreateBean> eventsAddReplyLiveData;
    public MutableLiveData<EventsCreateBean> eventsDeleteCommentLiveData;
    public MutableLiveData<EventsCreateBean> eventsDeleteReplyLiveData;
    private final String userId;
    public int page = 0;
    public double longitude, latitude;
    public String menu = "NEWEST";
    public List<String> eventsTypeList = new ArrayList<>();

    public EventsViewModel() {
        userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        eventsLiveData = new MutableLiveData<>();
        eventsDetailLiveData = new MutableLiveData<>();
        eventsSponsorLiveData = new MutableLiveData<>();
        eventsParticipantsLiveData = new MutableLiveData<>();
        eventsCommentsLiveData = new MutableLiveData<>();
        eventsStateLiveData = new MutableLiveData<>();
        eventsApplyLiveData = new MutableLiveData<>();
        eventsAcceptLiveData = new MutableLiveData<>();
        eventsCreateLiveData = new MutableLiveData<>();
        eventsEditLiveData = new MutableLiveData<>();
        eventsEventDeleteLiveData = new MutableLiveData<>();
        eventsEventEndLiveData = new MutableLiveData<>();
        eventsAddCommentLiveData = new MutableLiveData<>();
        eventsAddReplyLiveData = new MutableLiveData<>();
        eventsDeleteCommentLiveData = new MutableLiveData<>();
        eventsDeleteReplyLiveData = new MutableLiveData<>();
    }

    /**
     * 获取活动列表
     */
    public void getEventsList(List<String> eventsTypeList){
        page = 0;
        HashMap<String, Object> params = new HashMap();
        params.put("pageIndex", page);//页数
        params.put("pageSize", 10);//条数
        params.put("userId", userId);//用户id
        params.put("longitude", longitude);//经度
        params.put("latitude", latitude);//纬度
        params.put("distance", 250);//活动与用户的距离(该参数不设置时,表示不限制距离),可选
        params.put("menu", menu);//'NEWEST': 最新发布; 'CLOSEST': 离我最近; 'POPULAR': 附近最热; 'FAST': '离我最快'
        params.put("toggledEventTypes", eventsTypeList);//Array[String],勾选活动类型列表，为空时不作筛选,可选
        ParseCloud.callFunctionInBackground("v002-getEventCards-Abbv", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsBean eventsBean = new Gson().fromJson(new Gson().toJson(object), EventsBean.class);
                    if (eventsBean.getCode() == 0){
                        eventsLiveData.postValue(eventsBean);
                    }else {
                        ToastUtils.showShort(eventsBean.getError());
                    }
                }else {
                    LogUtils.e("getEventsList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取活动列表
     */
    public void getEventsListMore(){
        page ++;
        HashMap<String, Object> params = new HashMap();
        params.put("pageIndex", page);
        params.put("pageSize", 10);
        params.put("userId", userId);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("menu", menu);
        params.put("toggledEventTypes", eventsTypeList);
        ParseCloud.callFunctionInBackground("v002-getEventCards-Abbv", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsBean eventsBean = new Gson().fromJson(new Gson().toJson(object), EventsBean.class);
                    if (eventsBean.getCode() == 0){
                        eventsLiveData.postValue(eventsBean);
                    }else {
                        ToastUtils.showShort(eventsBean.getError());
                    }
                }else {
                    LogUtils.e("getEventsList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取活动详情
     */
    public void getEventsDetail(String eventId){
        HashMap<String, Object> params = new HashMap();
        params.put("eventId", eventId);
        params.put("longitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)));
        params.put("latitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)));
        ParseCloud.callFunctionInBackground("fetch-event-detail-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsDetail: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsDetailBean eventsDetailBean = new Gson().fromJson(new Gson().toJson(object), EventsDetailBean.class);
                    if (eventsDetailBean.getCode() == 0){
                        eventsDetailLiveData.postValue(eventsDetailBean);
                    }else {
                        ToastUtils.showShort(eventsDetailBean.getError());
                    }
                }else {
                    LogUtils.e("getEventsDetail: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取活动参与人信息
     */
    public void getEventsSponsor(String eventId){
        HashMap<String, Object> params = new HashMap();
        params.put("eventId", eventId);
        params.put("front", false);
        params.put("detailed", true);
        params.put("participantType", "joiner");//'applier'申请人 或 ‘joiner', 参与人
        params.put("longitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)));
        params.put("latitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)));
        ParseCloud.callFunctionInBackground("fetch-event-participants-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsSponsor: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsParticipantsBean eventsParticipantsBean = new Gson().fromJson(new Gson().toJson(object), EventsParticipantsBean.class);
                    if (eventsParticipantsBean.getCode() == 0){
                        eventsSponsorLiveData.postValue(eventsParticipantsBean);
                    }else {
                        ToastUtils.showShort(eventsParticipantsBean.getError());
                    }
                }else {
                    LogUtils.e("getEventsSponsor: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取活动申请人信息
     */
    public void getEventsParticipants(String eventId){
        HashMap<String, Object> params = new HashMap();
        params.put("eventId", eventId);
        params.put("front", false);
        params.put("detailed", true);
        params.put("participantType", "applier");//'applier'申请人 或 ‘joiner', 参与人
        params.put("longitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)));
        params.put("latitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)));
        ParseCloud.callFunctionInBackground("fetch-event-participants-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsParticipants: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsParticipantsBean eventsParticipantsBean = new Gson().fromJson(new Gson().toJson(object), EventsParticipantsBean.class);
                    if (eventsParticipantsBean.getCode() == 0){
                        eventsParticipantsLiveData.postValue(eventsParticipantsBean);
                    }else {
                        ToastUtils.showShort(eventsParticipantsBean.getError());
                    }
                }else {
                    LogUtils.e("getEventsParticipants: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取活动评论
     */
    public void getEventsComments(String eventId){
        HashMap<String, Object> params = new HashMap();
        params.put("eventId", eventId);
        params.put("longitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)));
        params.put("latitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)));
        ParseCloud.callFunctionInBackground("fetch-event-comments-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsComments: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCommentsBean eventsCommentsBean = new Gson().fromJson(new Gson().toJson(object), EventsCommentsBean.class);
                    if (eventsCommentsBean.getCode() == 0){
                        eventsCommentsLiveData.postValue(eventsCommentsBean);
                    }else {
                        ToastUtils.showShort(eventsCommentsBean.getError());
                    }
                }else {
                    LogUtils.e("getEventsComments: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 查询活动可报名状态
     */
    public void getEventsState(String eventId){
        HashMap<String, Object> params = new HashMap();
        params.put("eventId", eventId);
        params.put("userId", userId);
        ParseCloud.callFunctionInBackground("check-event-state-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsState: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsStateBean eventsStateBean = new Gson().fromJson(new Gson().toJson(object), EventsStateBean.class);
                    if (eventsStateBean.getCode() == 0){
                        eventsStateLiveData.postValue(eventsStateBean);
                    }else {
                        ToastUtils.showShort(eventsStateBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getEventsState: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 申请活动
     */
    public void getEventsApply(String eventId){
        HashMap<String, Object> params = new HashMap();
        params.put("eventId", eventId);
        params.put("userId", userId);
        params.put("longitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)));
        params.put("latitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)));
        ParseCloud.callFunctionInBackground("apply-event-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsApply: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsApplyBean eventsBean = new Gson().fromJson(new Gson().toJson(object), EventsApplyBean.class);
                    if (eventsBean.getCode() == 0){
                        eventsApplyLiveData.postValue(eventsBean);
                    }else {
                        ToastUtils.showShort(eventsBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getEventsApply: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 通过活动申请
     */
    public void getEventsAccept(String creatorId, String applierId, String eventId){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", creatorId);//活动创建者id, 必选
        params.put("applierId", applierId);//活动申请者（当前用户）id,  必选
        params.put("eventId", eventId);//活动id, 必选
        params.put("longitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)));
        params.put("latitude", Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)));
        ParseCloud.callFunctionInBackground("accept-event-applier-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsAccept: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsAcceptBean eventsAcceptBean = new Gson().fromJson(new Gson().toJson(object), EventsAcceptBean.class);
                    if (eventsAcceptBean.getCode() == 0){
                        eventsAcceptLiveData.postValue(eventsAcceptBean);
                    }else {
                        ToastUtils.showShort(eventsAcceptBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getEventsAccept: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 创建活动
     *
     * @param eventType
     * @param eventTypeIcon
     * @param event_contents
     * @param eventDesc
     * @param eventImage
     * @param images
     * @param isLocUnlimited
     * @param longitude
     * @param latitude
     * @param event_address
     * @param isTimeUnlimited
     * @param activity_time
     * @param max_num
     * @param eventSex
     */
    public void getEventsCreate(String eventType, String eventTypeIcon, String event_contents, String eventDesc
            , ParseFile eventImage, List<ParseFile> images
            , boolean isLocUnlimited, double longitude, double latitude, String event_address
            , boolean isTimeUnlimited, String activity_time
            , int max_num, String eventSex){

        LogUtils.e(activity_time);

        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);//活动创建者id,
        params.put("eventType", eventType);//活动类型
        params.put("eventTypeIcon", eventTypeIcon);//活动类型下的图标类型（二级类别）
        params.put("event_contents", event_contents);//活动标题
        params.put("eventDesc", eventDesc);//活动详情全部文字
        params.put("eventImage", eventImage);//活动图像（头图）的ParseFile 的objectId
        params.put("images", images);//活动图像（头图）的ParseFile 的objectId
        params.put("isLocUnlimited", isLocUnlimited);//活动是否为不限地点,必选,
        params.put("longitude", longitude);//当活动地点为不限地点时，传入用户所在经度
        params.put("latitude", latitude);//当活动地点为不限地点时，传入用户所在纬度
        params.put("event_address", event_address);//活动地址
        params.put("isTimeUnlimited", isTimeUnlimited);//活动是否为不限时间,必选,
        params.put("activity_time", activity_time);//活动开始时间，必选，为不限时间时传入当前时间
        params.put("max_num", max_num);//0 为不限人数,必选,其他表示最大活动允许人数
        params.put("eventSex", eventSex);//f:女性; m: 男性; x: 不限性别,必选
//        params.put("event_num_of_questions", event_num_of_questions);//活动合拍问答题数
//        params.put("event_questions", event_questions);//活动合拍问答

        ParseCloud.callFunctionInBackground("create-event-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsCreate: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean eventsCreateBean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (eventsCreateBean.getCode() == 0){
                        eventsCreateLiveData.postValue(eventsCreateBean);
                    }else {
                        ToastUtils.showShort(eventsCreateBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getEventsCreate: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 活动修改
     *
     * @param eventId
     * @param creatorId
     * @param eventType
     * @param eventTypeIcon
     * @param event_contents
     * @param eventDesc
     * @param eventImage
     * @param images
     * @param isLocUnlimited
     * @param longitude
     * @param latitude
     * @param event_address
     * @param isTimeUnlimited
     * @param activity_time
     * @param max_num
     * @param eventSex
     */
    public void getEventsEdit(String eventId, String creatorId, String eventType, String eventTypeIcon
            , String event_contents, String eventDesc
            , ParseFile eventImage, List<Object> images
            , boolean isLocUnlimited, double longitude, double latitude, String event_address
            , boolean isTimeUnlimited, String activity_time
            , int max_num, String eventSex){
        HashMap<String, Object> params = new HashMap();
        params.put("eventId", eventId);//活动id,
        params.put("userId", userId);//请求者id,
        params.put("creatorId", creatorId);//活动创建者id,
        params.put("eventType", eventType);//活动类型
        params.put("eventTypeIcon", eventTypeIcon);//活动类型下的图标类型（二级类别）
        params.put("event_contents", event_contents);//活动标题
        params.put("eventDesc", eventDesc);//活动详情全部文字
        params.put("eventImage", eventImage);//活动图像（头图）的ParseFile 的objectId
        params.put("images", images);//活动图像（头图）的ParseFile 的objectId
        params.put("isLocUnlimited", isLocUnlimited);//活动是否为不限地点,必选,
        params.put("longitude", longitude);//当活动地点为不限地点时，传入用户所在经度
        params.put("latitude", latitude);//当活动地点为不限地点时，传入用户所在纬度
        params.put("event_address", event_address);//活动地址
        params.put("isTimeUnlimited", isTimeUnlimited);//活动是否为不限时间,必选,
        params.put("activity_time", activity_time);//活动开始时间，必选，为不限时间时传入当前时间
        params.put("max_num", max_num);//0 为不限人数,必选,其他表示最大活动允许人数
        params.put("eventSex", eventSex);//f:女性; m: 男性; x: 不限性别,必选
//        params.put("event_num_of_questions", event_num_of_questions);//活动合拍问答题数
//        params.put("event_questions", event_questions);//活动合拍问答

        ParseCloud.callFunctionInBackground("edit-event-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsEdit: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean eventsCreateBean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (eventsCreateBean.getCode() == 0){
                        eventsEditLiveData.postValue(eventsCreateBean);
                    }else {
                        ToastUtils.showShort(eventsCreateBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getEventsEdit: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 删除活动
     */
    public void getEventsDelete(String eventId, boolean deleteChat){
        HashMap<String, Object> params = new HashMap();
        params.put("eventId", eventId);//活动id， 必选
        params.put("userId", userId);//请求者id,必选，用于验证权限
        params.put("deleteChat", deleteChat);//true:删除;false:不删除
        ParseCloud.callFunctionInBackground("deleteEvent", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsDelete: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean eventsCreateBean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (eventsCreateBean.getCode() == 0){
                        eventsEventDeleteLiveData.postValue(eventsCreateBean);
                    }else {
                        ToastUtils.showShort(eventsCreateBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getEventsDelete: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 活动截止报名
     */
    public void getEventsEnd(String eventId, String setTo){
        HashMap<String, Object> params = new HashMap();
        params.put("eventId", eventId);//活动id， 必选
        params.put("userId", userId);//请求者id,必选，用于验证权限
        params.put("setTo", setTo);//"on" 设置成允许报名, "off", 设置成截止报名
        ParseCloud.callFunctionInBackground("toggle-apply-event-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsEnd: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean eventsCreateBean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (eventsCreateBean.getCode() == 0){
                        eventsEventEndLiveData.postValue(eventsCreateBean);
                    }else {
                        ToastUtils.showShort(eventsCreateBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getEventsEnd: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 添加评论或回复
     */
    public void getEventsAddComment(String requestType, String eventId, String commentId, String contentString){
        HashMap<String, Object> params = new HashMap();
        params.put("requestType", requestType);//评论或回复,"comment": 评论活动; "reply": 回复评论;必选
        params.put("eventId", eventId);//活动id， 可选(当commentType等于"comment"时,必选)
        params.put("commentId", commentId);//评论id, 可选(当commentType等于"reply"时,必选)
        params.put("userId", userId);//请求者id,
        params.put("contentString", contentString);//评论文字
        ParseCloud.callFunctionInBackground("comment-event-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsAddComment: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean eventsBean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (eventsBean.getCode() == 0){
                        if (requestType.equals("comment")){
                            eventsAddCommentLiveData.postValue(eventsBean);
                        }else {
                            eventsAddReplyLiveData.postValue(eventsBean);
                        }
                    }else {
                        ToastUtils.showShort(eventsBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getEventsAddComment: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 删除评论或回复
     */
    public void getEventsDeleteComment(String requestType, String commentId, String replyCommentId){
        HashMap<String, Object> params = new HashMap();
        params.put("requestType", requestType);//评论或回复,"comment": 评论活动; "reply": 回复评论;必选
        params.put("commentId", commentId);//评论id, 可选(当commentType等于"comment"时,必选)
        params.put("replyCommentId", replyCommentId);//回复ID， 可选(当commentType等于"reply"时,必选)
        params.put("userId", userId);//请求者id,
        ParseCloud.callFunctionInBackground("delete-comment-event-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsDeleteComment: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    EventsCreateBean eventsBean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (eventsBean.getCode() == 0){
                        if (requestType.equals("comment")){
                            eventsDeleteCommentLiveData.postValue(eventsBean);
                        }else {
                            eventsDeleteReplyLiveData.postValue(eventsBean);
                        }
                    }else {
                        ToastUtils.showShort(eventsBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e("getEventsDeleteComment: " + e.getMessage());
                }
            }
        });
    }
}
