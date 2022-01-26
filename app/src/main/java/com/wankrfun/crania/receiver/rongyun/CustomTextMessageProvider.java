package com.wankrfun.crania.receiver.rongyun;

import android.content.Context;
import android.graphics.Paint;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;

import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.dialog.CustomBottomMessageDialog;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver.rongyun
 * @ClassName: CustomTextMessageProvider
 * @Description: 自定义消息布局
 * @Author: 鹿鸿祥
 * @CreateDate: 4/26/21 9:49 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/26/21 9:49 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@ProviderTag(messageContent = CustomTextMessage.class, showPortrait = false, centerInHorizontal = true)
public class CustomTextMessageProvider extends IContainerItemProvider.MessageProvider<CustomTextMessage> {
    private Context context;
    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        this.context = context;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_custom_message_text, viewGroup, false);
        CustomTextMessageHolder holder = new CustomTextMessageHolder();
        holder.tv_msg = view.findViewById(R.id.tv_msg);
        holder.tv_btn = view.findViewById(R.id.tv_btn);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, int i, CustomTextMessage customMessage, UIMessage uiMessage) {
        CustomTextMessageHolder holder = (CustomTextMessageHolder) view.getTag();
        holder.tv_btn.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.tv_msg.setText(customMessage.getMsg());
        holder.tv_btn.setText(customMessage.getBtnTxt());
    }

    @Override
    public Spannable getContentSummary(CustomTextMessage customMessage) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, CustomTextMessage customMessage, UIMessage uiMessage) {
        new XPopup.Builder(context).asCustom(new CustomBottomMessageDialog(context, customMessage.getSenderId())).show();
    }

    static class CustomTextMessageHolder{
        AppCompatTextView tv_msg;
        AppCompatTextView tv_btn;
    }
}
