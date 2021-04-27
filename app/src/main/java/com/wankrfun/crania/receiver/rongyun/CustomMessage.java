package com.wankrfun.crania.receiver.rongyun;

import android.annotation.SuppressLint;
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
 * @ClassName: CustomMessage
 * @Description: 融云自定义消息接收类(主动邀请发送模版)
 * @Author: 鹿鸿祥
 * @CreateDate: 4/19/21 10:34 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/19/21 10:34 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("ParcelCreator")
@MessageTag(value = "WInvitationMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomMessage extends MessageContent {

    private static final String TAG ="CustomMessage" ;

    //显示的非下划线文字
    private String msg;
    //显示的下划线按钮文字
    private String btnTxt;
    //心愿主人id，应当不会显示给心愿主人
    private String receiverId;
    //提示收到者id，应当显示本条
    private String senderId;
    //互动id， 接受邀约接口用到
    private String invitationId;
    //心愿id
    private String wishId;
    
    public CustomMessage(){}

    /**
     * 创建 CustomMessage(byte[] data) 带有 byte[] 的构造方法用于解析消息内容.
     */
    public CustomMessage(byte[] data) {
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
            setInvitationId(jsonObj.getString("invitationId"));
            setWishId(jsonObj.getString("wishId"));
        } catch (UnsupportedEncodingException | JSONException e) {
            Log.e(TAG, "UnsupportedEncodingException ", e);
        }
    }

    public static CustomMessage obtain(String msg, String btnTxt, String receiverId, String senderId, String invitationId, String wishId) {
        CustomMessage customMessage = new CustomMessage();
        customMessage.msg = msg;
        customMessage.btnTxt = btnTxt;
        customMessage.receiverId = receiverId;
        customMessage.senderId = senderId;
        customMessage.invitationId = invitationId;
        customMessage.wishId = wishId;
        return customMessage;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("msg", msg);
            jsonObj.put("btnTxt", btnTxt);
            jsonObj.put("receiverId", receiverId);
            jsonObj.put("senderId", senderId);
            jsonObj.put("invitationId", invitationId);
            jsonObj.put("wishId", wishId);
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
        ParcelUtils.writeToParcel(parcel, invitationId);
        ParcelUtils.writeToParcel(parcel, wishId);
    }

    /**
     * 读取接口，目的是要从 Parcel 中构造一个实现了 Parcelable 的类的实例处理。
     */
    public static final Creator<CustomMessage> CREATOR = new Creator<CustomMessage>() {

        @Override
        public CustomMessage createFromParcel(Parcel source) {
            return new CustomMessage(source);
        }

        @Override
        public CustomMessage[] newArray(int size) {
            return new CustomMessage[size];
        }
    };

    /**
     * 构造函数。
     *
     * @param in 初始化传入的 Parcel。
     */
    public CustomMessage(Parcel in) {
        setMsg(ParcelUtils.readFromParcel(in));
        setBtnTxt(ParcelUtils.readFromParcel(in));
        setReceiverId(ParcelUtils.readFromParcel(in));
        setSenderId(ParcelUtils.readFromParcel(in));
        setInvitationId(ParcelUtils.readFromParcel(in));
        setWishId(ParcelUtils.readFromParcel(in));
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

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    public String getWishId() {
        return wishId;
    }

    public void setWishId(String wishId) {
        this.wishId = wishId;
    }
}
