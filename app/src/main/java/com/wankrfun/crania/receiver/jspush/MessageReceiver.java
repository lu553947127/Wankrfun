package com.wankrfun.crania.receiver.jspush;

import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.utils.SharedUtils;
import com.wankrfun.crania.view.start.StartUpActivity;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver.jspush
 * @ClassName: MessageReceiver
 * @Description: JPush新的tag/alias接口结果返回需要开发者配置一个自定的广播
 * @Author: 鹿鸿祥
 * @CreateDate: 3/8/21 9:20 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/8/21 9:20 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MessageReceiver extends JPushMessageReceiver {
    private static final String TAG = "PushMessageReceiver";
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        LogUtils.e(TAG,"[onMessage] "+customMessage);
        processCustomMessage(context,customMessage);
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        //点击通知
        LogUtils.e(TAG,"[onNotifyMessageOpened] "+message);
        String extras = message.notificationExtras;
        LogUtils.e(TAG,"[onNotifyMessageOpened] "+extras);
        JPushExtraBean extraBean = new Gson().fromJson(extras, JPushExtraBean.class);
        try{
            switch (extraBean.getParams().getScene()){
                case "system"://系统消息
                    ActivityUtils.startActivity(StartUpActivity.class);
                    break;
                case "mechanism"://机构消息
                    ActivityUtils.startActivity(StartUpActivity.class);
                    break;

            }
        }catch (Throwable ignored){

        }
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        LogUtils.e(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);

        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if(nActionExtra==null){
            LogUtils.d(TAG,"ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            LogUtils.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            LogUtils.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            LogUtils.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            LogUtils.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        //接受到通知
        LogUtils.e(TAG,"[onNotifyMessageArrived] "+message);
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        LogUtils.e(TAG,"[onNotifyMessageDismiss] "+message);
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        LogUtils.e(TAG,"[onRegister] "+registrationId);
        //保存极光设备id
        SharedUtils sharedUtils = new SharedUtils(context);
        sharedUtils.addShared(SpConfig.REGISTRATION_ID,registrationId,"message");
    }

    @Override
    public void onConnected(Context context, boolean isConnected) {
        LogUtils.e(TAG,"[onConnected] "+isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        LogUtils.e(TAG,"[onCommandResult] "+cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onCheckTagOperatorResult(Context context,JPushMessage jPushMessage){
//        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context,jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, CustomMessage customMessage) {
//        if (MainActivity.isForeground) {
//            String message = customMessage.message;
//            String extras = customMessage.extra;
//            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//            if (!ExampleUtil.isEmpty(extras)) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (extraJson.length() > 0) {
//                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//                    }
//                } catch (JSONException e) {
//
//                }
//
//            }
//            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//        }
    }
}
