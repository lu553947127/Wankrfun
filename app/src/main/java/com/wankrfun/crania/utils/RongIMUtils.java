package com.wankrfun.crania.utils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.receiver.rongyun.CustomMessage;

import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.ImageMessage;
import io.rong.message.RichContentMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: RongIMUtils
 * @Description: 融云监听工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 1/20/21 1:54 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/20/21 1:54 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RongIMUtils {

    /**
     * 本地发送自定义消息
     *
     * @param rongExtension
     * @param wish
     */
    private void getSendMessage(RongExtension rongExtension, String wish) {
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMsg(wish);
        customMessage.setBtnTxt("邀约已发出");
        customMessage.setReceiverId(rongExtension.getTargetId());
        customMessage.setSenderId(SPUtils.getInstance().getString(SpConfig.USER_ID));

        Message message = Message.obtain(rongExtension.getTargetId(), Conversation.ConversationType.PRIVATE, customMessage);
        RongIMUtils.sendMessage(message);
    }

    /**
     * 发送自定义消息
     *
     * @param message
     */
    public static void sendMessage(Message message){
        RongIM.getInstance().sendMessage(message, null, null, new IRongCallback.ISendMessageCallback() {
            /**
             * 消息发送前回调, 回调时消息已存储数据库
             * @param message 已存库的消息体
             */
            @Override
            public void onAttached(Message message) {
                LogUtils.e("消息发送前回调","message-------"+new Gson().toJson(message));
            }
            /**
             * 消息发送成功。
             * @param message 发送成功后的消息体
             */
            @Override
            public void onSuccess(Message message) {
                LogUtils.e("消息发送成功","message-------"+new Gson().toJson(message));
            }

            /**
             * 消息发送失败
             * @param message   发送失败的消息体
             * @param errorCode 具体的错误
             */
            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                LogUtils.e("消息发送失败","message-------"+new Gson().toJson(message));
                LogUtils.e("消息发送失败","errorCode-------"+errorCode);
                LogUtils.e("消息发送失败","errorCode-------"+errorCode.getMessage());
                LogUtils.e("消息发送失败","errorCode-------"+errorCode.getValue());
            }
        });
    }

    /**
     * 获取发出去的消息监听
     */
    public static void getSendMessageListener(){
        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {

            /**
             * 消息发送前监听器处理接口（是否发送成功可以从 SentStatus 属性获取）。
             *
             * @param message 发送的消息实例。
             * @return 处理后的消息实例。
             */
            @Override
            public Message onSend(Message message) {
                return message;
            }

            /**
             * 消息在 UI 展示后执行/自己的消息发出后执行,无论成功或失败。
             *
             * @param message              消息实例。
             * @param sentMessageErrorCode 发送消息失败的状态码，消息发送成功 SentMessageErrorCode 为 null。
             * @return true 表示走自己的处理方式，false 走融云默认处理方式。
             */
            @Override
            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {

                if(message.getSentStatus()== Message.SentStatus.FAILED){
                    if(sentMessageErrorCode== RongIM.SentMessageErrorCode.NOT_IN_CHATROOM){
                        //不在聊天室
                    }else if(sentMessageErrorCode== RongIM.SentMessageErrorCode.NOT_IN_DISCUSSION){
                        //不在讨论组
                    }else if(sentMessageErrorCode== RongIM.SentMessageErrorCode.NOT_IN_GROUP){
                        //不在群组
                    }else if(sentMessageErrorCode== RongIM.SentMessageErrorCode.REJECTED_BY_BLACKLIST){
                        //你在他的黑名单中
                    }
                }

                MessageContent messageContent = message.getContent();
                if (messageContent instanceof TextMessage) {//文本消息
                    TextMessage textMessage = (TextMessage) messageContent;
                    LogUtils.e( "onSent-TextMessage:" + textMessage.getContent());
                } else if (messageContent instanceof ImageMessage) {//图片消息
                    ImageMessage imageMessage = (ImageMessage) messageContent;
                    LogUtils.e(  "onSent-ImageMessage:" + imageMessage.getRemoteUri());
                } else if (messageContent instanceof VoiceMessage) {//语音消息
                    VoiceMessage voiceMessage = (VoiceMessage) messageContent;
                    LogUtils.e(  "onSent-voiceMessage:" + voiceMessage.getUri().toString());
                } else if (messageContent instanceof RichContentMessage) {//图文消息
                    RichContentMessage richContentMessage = (RichContentMessage) messageContent;
                    LogUtils.e(  "onSent-RichContentMessage:" + richContentMessage.getContent());
                } else if (messageContent instanceof CustomMessage) {//自定义消息
                    CustomMessage customMessage = (CustomMessage) messageContent;
                    LogUtils.e(  "onSent-CustomMessage:" + customMessage);
                    LogUtils.json(LogUtils.I,new Gson().toJson(customMessage));
                } else {
                    LogUtils.e(  "onSent-其他消息，自己来判断处理");
                }
                return false;
            }
        });
    }

    /**
     * 获取接收到的消息监听
     */
    public static void getReceiveMessageListener(){
        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                LogUtils.e(message.getContent());
                MessageContent messageContent = message.getContent();
                if (messageContent instanceof TextMessage) {//文本消息
                    TextMessage textMessage = (TextMessage) messageContent;
                    LogUtils.e( "onReceive-TextMessage:" + textMessage.getContent());
                } else if (messageContent instanceof ImageMessage) {//图片消息
                    ImageMessage imageMessage = (ImageMessage) messageContent;
                    LogUtils.e(  "onReceive-ImageMessage:" + imageMessage.getRemoteUri());
                } else if (messageContent instanceof VoiceMessage) {//语音消息
                    VoiceMessage voiceMessage = (VoiceMessage) messageContent;
                    LogUtils.e(  "onReceive-voiceMessage:" + voiceMessage.getUri().toString());
                } else if (messageContent instanceof RichContentMessage) {//图文消息
                    RichContentMessage richContentMessage = (RichContentMessage) messageContent;
                    LogUtils.e(  "onReceive-RichContentMessage:" + richContentMessage.getContent());
                } else if (messageContent instanceof CustomMessage) {//自定义消息
                    CustomMessage customMessage = (CustomMessage) messageContent;
                    LogUtils.e(  "onReceive-CustomMessage:" + customMessage);
                    LogUtils.json(LogUtils.I,new Gson().toJson(customMessage));
                } else {
                    LogUtils.e(  "onReceive-其他消息，自己来判断处理");
                }
                return false;
            }
        });
    }
}
