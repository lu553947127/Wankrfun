package com.wankrfun.crania.receiver.rongyun;

import android.content.Context;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.CustomMessageBean;

import java.util.HashMap;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;

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
public class CustomMessageProvider extends IContainerItemProvider.MessageProvider<CustomMessage> {

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_custom_message, viewGroup, false);
        CustomMessageHolder holder = new CustomMessageHolder();
        holder.tv_msg = view.findViewById(R.id.tv_msg);
        holder.tv_btn = view.findViewById(R.id.tv_btn);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, int i, CustomMessage customMessage, UIMessage uiMessage) {
        CustomMessageHolder holder = (CustomMessageHolder) view.getTag();
        holder.tv_btn.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.tv_msg.setText(customMessage.getMsg());
        if (!TextUtils.isEmpty(customMessage.getSenderId()) && customMessage.getSenderId().equals(SPUtils.getInstance().getString(SpConfig.USER_ID))){
            holder.tv_btn.setText("邀约已发出");
        }else {
            holder.tv_btn.setText("点击接收邀约");
        }
    }

    @Override
    public Spannable getContentSummary(CustomMessage customMessage) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, CustomMessage customMessage, UIMessage uiMessage) {
        if (!TextUtils.isEmpty(customMessage.getSenderId()) && !customMessage.getSenderId().equals(SPUtils.getInstance().getString(SpConfig.USER_ID))){
            getAcceptWish(customMessage.getWishId(), customMessage.getInvitationId());
        }
    }

    static class CustomMessageHolder{
        AppCompatTextView tv_msg;
        AppCompatTextView tv_btn;
    }

    /**
     * 接受心愿邀请
     *
     * @param wishId
     * @param interactionId
     */
    private void getAcceptWish(String wishId, String interactionId){
        HashMap<String, Object> params = new HashMap();
        params.put("wishId", wishId);//心愿id,必选
        params.put("interactionId", interactionId);//心愿互动id,必选
        ParseCloud.callFunctionInBackground("accept-wish-invitation-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getAcceptWish: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    CustomMessageBean bean = new Gson().fromJson(new Gson().toJson(object), CustomMessageBean.class);
                    if (bean.getCode() == 0){
                        LogUtils.e(bean.getMsg());
                    }else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }else {
                    LogUtils.e("getAcceptWish: " + e.getMessage());
                }
            }
        });
    }
}
