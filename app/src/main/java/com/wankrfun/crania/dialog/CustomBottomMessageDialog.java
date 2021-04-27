package com.wankrfun.crania.dialog;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lxj.xpopup.core.CenterPopupView;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.CustomMessageBean;
import com.wankrfun.crania.bean.WishListBean;

import java.util.HashMap;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: CustomBottomMessageDialog
 * @Description: 自定义单聊消息发送心愿弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 4/25/21 3:55 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/25/21 3:55 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("ViewConstructor")
public class CustomBottomMessageDialog extends CenterPopupView {
    private final String targetId;
    private AppCompatTextView tv_wish1, tv_wish2, tv_wish3;
    private String wish1, wish2, wish3;
    private String objectId1, objectId2, objectId3;
    public CustomBottomMessageDialog(@NonNull Context context, String targetId) {
        super(context);
        this.targetId = targetId;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_custom_message;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tv_wish1 = findViewById(R.id.tv_wish1);
        tv_wish2 = findViewById(R.id.tv_wish2);
        tv_wish3 = findViewById(R.id.tv_wish3);
        getWishList();
    }

    @Override
    protected void onShow() {
        super.onShow();
        findViewById(R.id.iv_close).setOnClickListener(view -> dismiss());
        findViewById(R.id.tv_yes).setOnClickListener(view -> dismiss());

        tv_wish1.setOnClickListener(view -> {
            getSendWish(objectId1, wish1);
        });

        tv_wish2.setOnClickListener(view -> {
            getSendWish(objectId2, wish2);
        });

        tv_wish3.setOnClickListener(view -> {
            getSendWish(objectId3, wish3);
        });
    }

    /**
     * 获取对方的心愿列表
     */
    private void getWishList(){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", targetId);
        ParseCloud.callFunctionInBackground("fetch-wishes-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getWishList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    WishListBean bean = new Gson().fromJson(new Gson().toJson(object), WishListBean.class);
                    if (bean.getCode() == 0){
                        if (bean.getData().getList().size() != 0){
                            findViewById(R.id.ll_content).setVisibility(VISIBLE);
                            findViewById(R.id.rl_empty).setVisibility(GONE);
                            switch (bean.getData().getList().size()){
                                case 1:
                                    wish1 = bean.getData().getList().get(0).getContent();
                                    objectId1 = bean.getData().getList().get(0).getObjectId();
                                    tv_wish1.setText(wish1);
                                    tv_wish2.setVisibility(GONE);
                                    tv_wish3.setVisibility(GONE);
                                    break;
                                case 2:
                                    wish1 = bean.getData().getList().get(0).getContent();
                                    wish2 = bean.getData().getList().get(1).getContent();
                                    objectId1 = bean.getData().getList().get(0).getObjectId();
                                    objectId2 = bean.getData().getList().get(1).getObjectId();
                                    tv_wish1.setText(wish1);
                                    tv_wish2.setText(wish2);
                                    tv_wish2.setVisibility(VISIBLE);
                                    tv_wish3.setVisibility(GONE);
                                    break;
                                case 3:
                                    wish1 = bean.getData().getList().get(0).getContent();
                                    wish2 = bean.getData().getList().get(1).getContent();
                                    wish3 = bean.getData().getList().get(2).getContent();
                                    objectId1 = bean.getData().getList().get(0).getObjectId();
                                    objectId2 = bean.getData().getList().get(1).getObjectId();
                                    objectId3 = bean.getData().getList().get(2).getObjectId();
                                    tv_wish1.setText(wish1);
                                    tv_wish2.setText(wish2);
                                    tv_wish3.setText(wish3);
                                    tv_wish2.setVisibility(VISIBLE);
                                    tv_wish3.setVisibility(VISIBLE);
                                    break;
                            }
                        }else {
                            findViewById(R.id.ll_content).setVisibility(GONE);
                            findViewById(R.id.rl_empty).setVisibility(VISIBLE);
                        }
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getWishList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 发送心愿计划邀请
     *
     * @param objectId
     * @param wish
     */
    private void getSendWish(String objectId, String wish){
        HashMap<String, Object> params = new HashMap();
        params.put("wishId", objectId);//心愿id,必选
        params.put("wishContent", wish);//心愿内容,必选
        params.put("senderId", SPUtils.getInstance().getString(SpConfig.USER_ID));//发送者id,必选
        params.put("receiverId", targetId);//接收者id,必选
        ParseCloud.callFunctionInBackground("send-wish-invitation-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getSendWish: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    CustomMessageBean bean = new Gson().fromJson(new Gson().toJson(object), CustomMessageBean.class);
                    if (bean.getCode() == 0){
                        LogUtils.e(bean.getMsg());
                        dismiss();
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getSendWish: " + e.getMessage());
                }
            }
        });
    }
}
