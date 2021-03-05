package com.wankrfun.crania.viewmodel;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.parse.FunctionCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.wankrfun.crania.base.VersionUploadBean;
import com.wankrfun.crania.bean.LoginBean;
import com.wankrfun.crania.event.ParseEvent;
import com.wankrfun.crania.http.retrofit.BaseRepository;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.viewmodel
 * @ClassName: LoginViewModel
 * @Description: 登录ViewModel
 * @Author: 鹿鸿祥
 * @CreateDate: 1/11/21 2:53 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/11/21 2:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginViewModel extends BaseRepository {

    public MutableLiveData<String> pageStateLiveData;
    public MutableLiveData<String> failStateLiveData;
    public MutableLiveData<ParseEvent> verificationPhoneLiveData;
    public MutableLiveData<ParseEvent> sendCodeLiveData;
    public MutableLiveData<Long> timeLiveDataLiveData;
    public MutableLiveData<ParseEvent> verificationCodeLiveData;
    public MutableLiveData<ParseEvent> resetPasswordLiveData;
    public MutableLiveData<ParseUser> loginLiveData;
    public MutableLiveData<VersionUploadBean> versionUploadLiveData;

    public LoginViewModel() {
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        verificationPhoneLiveData = new MutableLiveData<>();
        sendCodeLiveData = new MutableLiveData<>();
        timeLiveDataLiveData = new MutableLiveData<>();
        verificationCodeLiveData = new MutableLiveData<>();
        resetPasswordLiveData = new MutableLiveData<>();
        loginLiveData = new MutableLiveData<>();
        versionUploadLiveData = new MutableLiveData<>();
    }

    /**
     * 验证登录手机号是否为新老用户
     *
     * @param phone 手机号
     */
    public void getVerificationPhone(String phone){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("phonenumber", phone);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    LogUtils.e("getVerificationPhone: "+ parseObject);
                    verificationPhoneLiveData.postValue(new ParseEvent(parseObject));
                } else {
                    verificationPhoneLiveData.postValue(new ParseEvent(e.getMessage()));
                    LogUtils.e( "getVerificationPhone: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     */
    public void getSendCode(String phone){
        HashMap<String, String> params = new HashMap();
        params.put("phonenumber", phone);
        ParseCloud.callFunctionInBackground("sendOTP", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getSendCode: "+ new Gson().toJson(object));
                    LoginBean baseEvent = new Gson().fromJson(new Gson().toJson(object), LoginBean.class);
                    if (baseEvent.getCode() == 0){
                        sendCodeLiveData.postValue(new ParseEvent(object));
                    }else {
                        ToastUtils.showShort(baseEvent.getData().getMsg());
                    }
                }else {
                    sendCodeLiveData.postValue(new ParseEvent(e.getMessage()));
                    LogUtils.e( "getSendCode: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 验证短信验证码
     *
     * @param phone 手机号
     * @param code 验证码
     */
    public void getVerificationCode(String phone, String code){
        HashMap<String, String> params = new HashMap();
        params.put("phonenumber", phone);
        params.put("passcode", code);
        ParseCloud.callFunctionInBackground("verifyOTP", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getVerificationCode: "+ new Gson().toJson(object));
                    LoginBean baseEvent = new Gson().fromJson(new Gson().toJson(object), LoginBean.class);
                    if (baseEvent.getCode() == 0){
                        verificationCodeLiveData.postValue(new ParseEvent(object));
                    }else {
                        ToastUtils.showShort(baseEvent.getData().getMsg());
                    }
                }else {
                    verificationCodeLiveData.postValue(new ParseEvent(e.getMessage()));
                    LogUtils.e( "getVerificationCode: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取验证码倒计时
     */
    @SuppressLint("CheckResult")
    public void sendVerificationCode() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(60)
                .map(aLong -> 59 - aLong)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> timeLiveDataLiveData.postValue(aLong), throwable -> {

                }, () -> {
                    //倒计时结束，重置按钮，并停止获取请求
                    timeLiveDataLiveData.postValue((long) -1);
                });
    }


    /**
     * 重置密码
     *
     * @param phone 手机号
     * @param newPassword 新密码
     */
    public void getResetPassword(String phone, String newPassword){
        HashMap<String, String> params = new HashMap();
        params.put("username", phone);
        params.put("newPassword", newPassword);
        ParseCloud.callFunctionInBackground("changeUserPassword", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getResetPassword: "+ new Gson().toJson(object));
                    LoginBean baseEvent = new Gson().fromJson(new Gson().toJson(object), LoginBean.class);
                    if (baseEvent.getCode() == 0){
                        resetPasswordLiveData.postValue(new ParseEvent(object));
                    }else {
                        ToastUtils.showShort(baseEvent.getData().getMsg());
                    }
                }else {
                    resetPasswordLiveData.postValue(new ParseEvent(e.getMessage()));
                    LogUtils.e( "getResetPassword: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 账号密码登录
     *
     * @param username 账号
     * @param password 密码
     */
    public void getLoginPassword(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    LogUtils.e("getLoginPassword: "+ user);
                    loginLiveData.postValue(user);
                } else {
                    ToastUtils.showShort(e.getMessage());
                }
            }
        });
    }

    /**
     * 注册时填写邀请码时的回传
     *
     * @param rewardUserId 新注册用户的objectId, 可选
     * @param inviteCode 邀请码,必选
     */
    public void getInvited(String rewardUserId, String inviteCode){
        HashMap<String, String> params = new HashMap();
        params.put("rewardUserId", rewardUserId);
        params.put("inviteCode", inviteCode);
        ParseCloud.callFunctionInBackground("invited-sign-up-hanlder", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getInvited: "+ new Gson().toJson(object));
                }else {
                    LogUtils.e( "getInvited: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 注册回传,用户注册成功后调用
     *
     */
    public void getSignUp(){
        HashMap<String, String> params = new HashMap();
        ParseCloud.callFunctionInBackground("android-signup-notice", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getSignUp: "+ new Gson().toJson(object));
                }else {
                    LogUtils.e( "getSignUp: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取版本升级信息
     */
    public void getVersionUpload(){
        HashMap<String, String> params = new HashMap();
        ParseCloud.callFunctionInBackground("upgrade-info", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getVersionUpload: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    VersionUploadBean versionUploadBean = new Gson().fromJson(new Gson().toJson(object), VersionUploadBean.class);
                    if (versionUploadBean.getCode() == 0){
                        versionUploadLiveData.postValue(versionUploadBean);
                    }else {
                        ToastUtils.showShort(versionUploadBean.getError());
                    }
                }else {
                    LogUtils.e("getVersionUpload: " + e.getMessage());
                }
            }
        });
    }
}
