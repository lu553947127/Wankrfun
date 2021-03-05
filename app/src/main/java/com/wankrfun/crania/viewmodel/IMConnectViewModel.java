package com.wankrfun.crania.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.EventsCreateBean;
import com.wankrfun.crania.bean.GroupRelationBean;
import com.wankrfun.crania.bean.ImGroupInfoBean;
import com.wankrfun.crania.bean.ImGroupMembersBean;
import com.wankrfun.crania.bean.ImTokenBean;
import com.wankrfun.crania.bean.ImUserInfoBean;
import com.wankrfun.crania.http.retrofit.BaseRepository;

import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.viewmodel
 * @ClassName: IMConnectViewModel
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/20/21 4:03 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/20/21 4:03 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class IMConnectViewModel extends BaseRepository {

    public MutableLiveData<String> pageStateLiveData;
    public MutableLiveData<String> failStateLiveData;
    public MutableLiveData<String> tokenLiveData;
    public MutableLiveData<ImUserInfoBean> imUserInfoLiveData;
    public MutableLiveData<ImGroupInfoBean> imGroupInfoLiveData;
    public MutableLiveData<ImGroupMembersBean> imGroupMembersLiveData;
    public MutableLiveData<EventsCreateBean> imOutGroupLiveData;
    public MutableLiveData<EventsCreateBean> imEditGroupLiveData;
    public MutableLiveData<GroupRelationBean> groupRelationLiveData;
    private final String userId;

    public IMConnectViewModel() {
        userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        tokenLiveData = new MutableLiveData<>();
        imUserInfoLiveData= new MutableLiveData<>();
        imGroupInfoLiveData = new MutableLiveData<>();
        imGroupMembersLiveData = new MutableLiveData<>();
        imOutGroupLiveData = new MutableLiveData<>();
        imEditGroupLiveData = new MutableLiveData<>();
        groupRelationLiveData = new MutableLiveData<>();
    }

    /**
     * 获取融云token
     */
    public void getImToken(){
        HashMap<String, String> params = new HashMap();
        params.put("userId", userId);
        ParseCloud.callFunctionInBackground("chat-token", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    LogUtils.e("getImToken: "+ gson.toJson(object));
                    ImTokenBean imTokenBean = gson.fromJson(gson.toJson(object),ImTokenBean.class);
                    if (imTokenBean.getCode() == 0){
                        SPUtils.getInstance().put(SpConfig.IM_TOKEN, imTokenBean.getData().getToken(), true);
                        connect(imTokenBean.getData().getToken());
                    }else {
                        ToastUtils.showShort(imTokenBean.getError());
                        failStateLiveData.postValue("失败");
                    }
                }else {
                    failStateLiveData.postValue("失败");
                    LogUtils.e( "getImToken: " + e.getMessage());
                }
            }
        });
    }

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    public void connect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus code) {
                //消息数据库打开，可以进入到主页面
            }

            @Override
            public void onSuccess(String s) {
                //连接成功
                LogUtils.i("融云连接成功"+s);
                tokenLiveData.postValue(s);
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode errorCode) {
                LogUtils.i("融云连接失败"+errorCode);
                ToastUtils.showShort("连接失败："+errorCode);
                failStateLiveData.postValue("失败");
                if(errorCode.equals(RongIMClient.ConnectionErrorCode.RC_CONN_TOKEN_INCORRECT)) {
                    //从 APP 服务获取新 token，并重连
                    getImToken();
                } else {
                    //无法连接 IM 服务器，请根据相应的错误码作出对应处理
                    ToastUtils.showShort("连接失败，请联系服务人员");
                }
            }
        });
    }

    /**
     * 获取融云的聊天列表里用户简短信息
     */
    public UserInfo getImUserInfo(String userId){
        HashMap<String, String> params = new HashMap();
        params.put("userId", userId);//活动发起者用户_id,群组成员的第一个是活动发起者(群主),必选
        ParseCloud.callFunctionInBackground("get-brief-info", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    LogUtils.e("getImUserInfo: "+ gson.toJson(object));
                    ImUserInfoBean imUserInfoBean = gson.fromJson(gson.toJson(object),ImUserInfoBean.class);
                    if (imUserInfoBean.getCode() == 0){
                        imUserInfoLiveData.postValue(imUserInfoBean);
                    }else {
                        ToastUtils.showShort(imUserInfoBean.getError());
                    }
                }else {
                    LogUtils.e( "getImUserInfo: " + e.getMessage());
                }
            }
        });
        return null;
    }

    /**
     * 获取群聊人员信息
     */
    public Group getImGroupInfo(String groupId){
        HashMap<String, String> params = new HashMap();
        params.put("groupId", groupId);//活动发起者用户_id,群组成员的第一个是活动发起者(群主),必选
        ParseCloud.callFunctionInBackground("get-group-brief-info", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    LogUtils.e("getImGroupInfo: "+ gson.toJson(object));
                    ImGroupInfoBean imGroupInfoBean = gson.fromJson(gson.toJson(object),ImGroupInfoBean.class);
                    if (imGroupInfoBean.getCode() == 0){
                        imGroupInfoLiveData.postValue(imGroupInfoBean);
                    }else {
                        ToastUtils.showShort(imGroupInfoBean.getError());
                    }
                }else {
                    LogUtils.e( "getImGroupInfo: " + e.getMessage());
                }
            }
        });
        return null;
    }

    /**
     * 获取群聊人员部分信息
     */
    public void getImGroupMembers(String groupId){
        HashMap<String, String> params = new HashMap();
        params.put("groupId", groupId);//活动_id,必选
        ParseCloud.callFunctionInBackground("get-members-brief-info", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    LogUtils.e("getImGroupMembers: "+ gson.toJson(object));
                    ImGroupMembersBean imGroupMembersBean = gson.fromJson(gson.toJson(object),ImGroupMembersBean.class);
                    if (imGroupMembersBean.getCode() == 0){
                        imGroupMembersLiveData.postValue(imGroupMembersBean);
                    }else {
                        ToastUtils.showShort(imGroupMembersBean.getError());
                    }
                }else {
                    LogUtils.e( "getImGroupMembers: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 用户加入群组(活动发起者同意用户的活动申请)
     */
    public void getJoinGroup(String eventId, String userId, String memberId){
        HashMap<String, String> params = new HashMap();
        params.put("eventId", eventId);//活动_id,必选
        params.put("userId", userId);//活动发起者用户_id,群组成员的第一个是活动发起者(群主),必选
        params.put("memberId", memberId);//成员用户_id,必选
        ParseCloud.callFunctionInBackground("chat-join", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    LogUtils.e("getJoinGroup: "+ gson.toJson(object));
//                    ImTokenBean imTokenBean = gson.fromJson(gson.toJson(object),ImTokenBean.class);
                }else {
                    LogUtils.e( "getJoinGroup: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 群成员修改群名称
     */
    public void getGroupUpdateName(String groupId, String userId, String newName){
        HashMap<String, String> params = new HashMap();
        params.put("groupId", groupId);//群_id,必选
        params.put("userId", userId);//群成员,必选
        params.put("newName", newName);//新的名称,必选
        ParseCloud.callFunctionInBackground("chat-updateName", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    LogUtils.e("getGroupUpdateName: "+ gson.toJson(object));
                    EventsCreateBean eventsCreateBean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (eventsCreateBean.getCode() == 0){
                        imEditGroupLiveData.postValue(eventsCreateBean);
                    }else {
                        ToastUtils.showShort(eventsCreateBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e( "getGroupUpdateName: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 退出群聊,创建者不能退出群聊,iOS/Android端规避
     */
    public void getOutGroup(String groupId, String memberId, boolean shouldQuitEvent){
        HashMap<String, Object> params = new HashMap();
        params.put("groupId", groupId);//群_id,必选
        params.put("memberId", memberId);//成员id,必选
        params.put("shouldQuitEvent", shouldQuitEvent);//是否退出关联的活动,默认不退出,true:退出;false:不退出,可选
        ParseCloud.callFunctionInBackground("chat-quit", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    LogUtils.e("getOutGroup: "+ gson.toJson(object));
                    EventsCreateBean eventsCreateBean = new Gson().fromJson(new Gson().toJson(object), EventsCreateBean.class);
                    if (eventsCreateBean.getCode() == 0){
                        imOutGroupLiveData.postValue(eventsCreateBean);
                    }else {
                        ToastUtils.showShort(eventsCreateBean.getData().getMsg());
                    }
                }else {
                    LogUtils.e( "getOutGroup: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取私聊或群聊来源或关系
     */
    public void getGroupRelation(String targetId, String type){
        HashMap<String, Object> params = new HashMap();
        params.put("userId", userId);
        params.put("targetId", targetId);//String, 私聊传私聊对象用户id,群聊传群聊id,必选
        params.put("type", type);//String enum, 私聊传"private",群聊传"group",必选
        ParseCloud.callFunctionInBackground("get-chat-relation", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    LogUtils.e("getGroupRelation: "+ gson.toJson(object));
                    GroupRelationBean groupRelationBean = new Gson().fromJson(new Gson().toJson(object), GroupRelationBean.class);
                    if (groupRelationBean.getCode() == 0){
                        groupRelationLiveData.postValue(groupRelationBean);
                    }else {
                        ToastUtils.showShort(groupRelationBean.getError());
                    }
                }else {
                    LogUtils.e( "getGroupRelation: " + e.getMessage());
                }
            }
        });
    }
}
