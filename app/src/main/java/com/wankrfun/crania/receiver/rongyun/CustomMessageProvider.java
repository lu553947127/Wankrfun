package com.wankrfun.crania.receiver.rongyun;

import android.content.Context;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;

import com.wankrfun.crania.R;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.MessageContent;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver.rongyun
 * @ClassName: CustomMessageProvider
 * @Description: 自定义消息布局
 * @Author: 鹿鸿祥
 * @CreateDate: 4/19/21 10:18 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/19/21 10:18 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@ProviderTag(messageContent = CustomMessage.class)
public class CustomMessageProvider extends IContainerItemProvider.MessageProvider {

    @Override
    public void bindView(View view, int i, MessageContent messageContent, UIMessage uiMessage) {
        ((AppCompatTextView)(view.findViewById(R.id.tv_btn))).setText(R.string.meet_accept);
    }

    @Override
    public Spannable getContentSummary(MessageContent messageContent) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, MessageContent messageContent, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_custom_message, viewGroup, false);
    }

    @Override
    public void bindView(View view, int i, Object o) {

    }
}
