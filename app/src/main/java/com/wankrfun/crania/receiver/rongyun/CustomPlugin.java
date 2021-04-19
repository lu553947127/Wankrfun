package com.wankrfun.crania.receiver.rongyun;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.wankrfun.crania.R;

import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver.rongyun
 * @ClassName: CustomPlugin
 * @Description: 加号区域操作设置
 * @Author: 鹿鸿祥
 * @CreateDate: 4/16/21 10:43 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/16/21 10:43 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomPlugin implements IPluginModule {

    @Override
    public Drawable obtainDrawable(Context context) {
        return ContextCompat.getDrawable(context, R.drawable.icon_meet_like_new);
    }

    @Override
    public String obtainTitle(Context context) {
        return "邀请";
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        CustomMessage messageContent = CustomMessage.obtain("自定义消息");
        Message message = Message.obtain(rongExtension.getTargetId(), Conversation.ConversationType.PRIVATE, messageContent);
        RongIM.getInstance().sendMessage(message, "pushContent", "pushData", new IRongCallback.ISendMessageCallback() {
            /**
             * 消息发送前回调, 回调时消息已存储数据库
             * @param message 已存库的消息体
             */
            @Override
            public void onAttached(Message message) {

            }
            /**
             * 消息发送成功。
             * @param message 发送成功后的消息体
             */
            @Override
            public void onSuccess(Message message) {
                LogUtils.e("tag","message-------"+message.getContent());
            }

            /**
             * 消息发送失败
             * @param message   发送失败的消息体
             * @param errorCode 具体的错误
             */
            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
