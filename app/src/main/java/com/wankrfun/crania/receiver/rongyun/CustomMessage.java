package com.wankrfun.crania.receiver.rongyun;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver.rongyun
 * @ClassName: CustomMessage
 * @Description: 融云自定义消息接收类
 * @Author: 鹿鸿祥
 * @CreateDate: 4/19/21 10:34 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/19/21 10:34 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("ParcelCreator")
@MessageTag(value = "RC:CustomMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomMessage extends MessageContent {
    private static final String TAG ="CustomMessage" ;
    private String content, extra;

    public CustomMessage() {

    }

    public CustomMessage(String content, String extra) {
        this.content = content;
        this.extra = extra;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            if (!TextUtils.isEmpty(getContent())) {
                jsonObj.put("content", getEmotion(getContent()));
            }

            if (!TextUtils.isEmpty(getExtra()))
                jsonObj.put("extra", getExtra());

            if (getJSONUserInfo() != null)
                jsonObj.putOpt("user", getJSONUserInfo());

            if (getJsonMentionInfo() != null) {
                jsonObj.putOpt("mentionedInfo", getJsonMentionInfo());
            }
            // TODO 考虑改成一个字段
            jsonObj.put("isBurnAfterRead", isDestruct());
            jsonObj.put("burnDuration", getDestructTime());
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
        ParcelUtils.writeToParcel(parcel, getExtra());
        ParcelUtils.writeToParcel(parcel, content);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
        ParcelUtils.writeToParcel(parcel, getMentionedInfo());
        ParcelUtils.writeToParcel(parcel, isDestruct() ? 1 : 0);
        ParcelUtils.writeToParcel(parcel, getDestructTime());
    }

    /**
     * 获取消息扩展信息
     *
     * @return 扩展信息
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 设置消息扩展信息
     *
     * @param extra 扩展信息
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * 获取文字消息的内容。
     *
     * @return 文字消息的内容。
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文字消息的内容。
     *
     * @param content 文字消息的内容。
     */
    public void setContent(String content) {
        this.content = content;
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
        setExtra(ParcelUtils.readFromParcel(in));
        setContent(ParcelUtils.readFromParcel(in));
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
        setMentionedInfo(ParcelUtils.readFromParcel(in, MentionedInfo.class));
        setDestruct(ParcelUtils.readIntFromParcel(in) == 1);
        setDestructTime(ParcelUtils.readLongFromParcel(in));
    }

    /**
     * 构造函数。
     *
     * @param content 文字消息的内容。
     */
    public CustomMessage(String content) {
        this.setContent(content);
    }

    @Override
    public List<String> getSearchableWord() {
        List<String> words = new ArrayList<>();
        words.add(content);
        return words;
    }

    public static CustomMessage obtain(String text) {
        CustomMessage model = new CustomMessage();
        model.setContent(text);
        return model;
    }

    private String getEmotion(String content) {

        Pattern pattern = Pattern.compile("\\[/u([0-9A-Fa-f]+)\\]");
        Matcher matcher = pattern.matcher(content);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String matchStr = matcher.group(1);
            int inthex = 0;
            if (matchStr != null) {
                inthex = Integer.parseInt(matchStr, 16);
            }
            matcher.appendReplacement(sb, String.valueOf(Character.toChars(inthex)));
        }

        matcher.appendTail(sb);

        return sb.toString();
    }
}
