package com.wankrfun.crania.dialog;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.BottomPopupView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.event.EventsEvent;
import org.greenrobot.eventbus.EventBus;
import fj.edittextcount.lib.FJEditTextCount;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: CustomBottomComment
 * @Description: 自定义评论弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 2/11/21 12:49 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/11/21 12:49 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomBottomComment extends BottomPopupView {
    private FJEditTextCount etComment;
    private AppCompatTextView tvTitle;
    private String type, commentId;
    public CustomBottomComment(@NonNull Context context, String type) {
        super(context);
        this.type = type;
    }

    public CustomBottomComment(@NonNull Context context, String type, String commentId) {
        super(context);
        this.type = type;
        this.commentId = commentId;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_custom_comment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tvTitle = findViewById(R.id.tv_title);
        etComment = findViewById(R.id.et_comment);
        if (type.equals("我要评论")){
            tvTitle.setText(type);
            etComment.setEtHint("请输入你的评论");
        }else {
            tvTitle.setText("回复 @" + type);
            etComment.setEtHint("请输入你的回复");
        }
    }

    @Override
    protected void onShow() {
        super.onShow();
        findViewById(R.id.btn_finish).setOnClickListener(v -> {
            if (TextUtils.isEmpty(etComment.getText())){
                ToastUtils.showShort("内容不能为空哦");
                return;
            }
            if (type.equals("我要评论")){
                EventBus.getDefault().post(new EventsEvent("comment", etComment.getText()));
            }else {
                EventBus.getDefault().post(new EventsEvent("reply", etComment.getText(), commentId));
            }
            dismiss();
        });
    }
}
