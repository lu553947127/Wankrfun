package com.wankrfun.crania.receiver.rongyun;

import android.os.Parcel;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver.rongyun
 * @ClassName: CustomTextMessage
 * @Description: 融云自定义消息接收类(文本接收邀请发送模版)
 * @Author: 鹿鸿祥
 * @CreateDate: 4/26/21 9:44 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/26/21 9:44 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@MessageTag(value = "WPromptInvitationMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomTextMessage extends MessageContent {

    private static final String TAG ="CustomTextMessage" ;

    //显示的非下划线文字
    private String msg;
    //显示的下划线按钮文字
    private String btnTxt;
    //心愿主人id，应当不会显示给心愿主人
    private String receiverId;
    //提示收到者id，应当显示本条
    private String senderId;

    public CustomTextMessage(){}

    /**
     * 创建 CustomMessage(byte[] data) 带有 byte[] 的构造方法用于解析消息内容.
     */
    public CustomTextMessage(byte[] data) {
        if (data == null) {
            LogUtils.e(TAG, "data is null ");
            return;
        }
        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
            JSONObject jsonObj = new JSONObject(jsonStr);
            setMsg(jsonObj.getString("msg"));
            setBtnTxt(jsonObj.getString("btnTxt"));
            setReceiverId(jsonObj.getString("receiverId"));
            setSenderId(jsonObj.getString("senderId"));
        } catch (UnsupportedEncodingException | JSONException e) {
            Log.e(TAG, "UnsupportedEncodingException ", e);
        }
    }

    public static CustomTextMessage obtain(String msg, String btnTxt, String receiverId, String senderId, String invitationId) {
        CustomTextMessage customTextMessage = new CustomTextMessage();
        customTextMessage.msg = msg;
        customTextMessage.btnTxt = btnTxt;
        customTextMessage.receiverId = receiverId;
        customTextMessage.senderId = senderId;
        return customTextMessage;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("msg", msg);
            jsonObj.put("btnTxt", btnTxt);
            jsonObj.put("receiverId", receiverId);
            jsonObj.put("senderId", senderId);
        } catch (JSONException e) {
            LogUtils.e(TAG, "JSONException " + e.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            LogUtils.e(TAG, "UnsupportedEncodingException ", e);
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, msg);
        ParcelUtils.writeToParcel(parcel, btnTxt);
        ParcelUtils.writeToParcel(parcel, receiverId);
        ParcelUtils.writeToParcel(parcel, senderId);
    }

    /**
     * 读取接口，目的是要从 Parcel 中构造一个实现了 Parcelable 的类的实例处理。
     */
    public static final Creator<CustomTextMessage> CREATOR = new Creator<CustomTextMessage>() {

        @Override
        public CustomTextMessage createFromParcel(Parcel source) {
            return new CustomTextMessage(source);
        }

        @Override
        public CustomTextMessage[] newArray(int size) {
            return new CustomTextMessage[size];
        }
    };

    /**
     * 构造函数。
     *
     * @param in 初始化传入的 Parcel。
     */
    public CustomTextMessage(Parcel in) {
        setMsg(ParcelUtils.readFromParcel(in));
        setBtnTxt(ParcelUtils.readFromParcel(in));
        setReceiverId(ParcelUtils.readFromParcel(in));
        setSenderId(ParcelUtils.readFromParcel(in));
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBtnTxt() {
        return btnTxt;
    }

    public void setBtnTxt(String btnTxt) {
        this.btnTxt = btnTxt;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
