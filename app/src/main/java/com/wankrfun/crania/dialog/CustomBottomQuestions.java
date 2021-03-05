package com.wankrfun.crania.dialog;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.BottomPopupView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.event.EventsEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: CustomBottomQuestions
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/3/21 2:15 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/3/21 2:15 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomBottomQuestions extends BottomPopupView {
    private int type = 1;
    public CustomBottomQuestions(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_custom_questions;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onShow() {
        super.onShow();

        AppCompatEditText et_questions = findViewById(R.id.et_questions);
        AppCompatEditText et_questions2 = findViewById(R.id.et_questions2);
        AppCompatEditText et_questions3 = findViewById(R.id.et_questions3);

        findViewById(R.id.tv_cancel).setOnClickListener(view -> dismiss());

        findViewById(R.id.tv_yes).setOnClickListener(view -> {
            if (TextUtils.isEmpty(et_questions.getText().toString())){
                ToastUtils.showShort("问题不能为空");
                return;
            }
            EventBus.getDefault().post(new EventsEvent("questions", et_questions.getText().toString()));
            dismiss();
        });

        findViewById(R.id.tv_add).setOnClickListener(view -> {
            switch (type){
                case 1:
                    type = 2;
                    et_questions.setVisibility(VISIBLE);
                    et_questions2.setVisibility(VISIBLE);
                    et_questions3.setVisibility(GONE);
                    break;
                case 2:
                    et_questions.setVisibility(VISIBLE);
                    et_questions2.setVisibility(VISIBLE);
                    et_questions3.setVisibility(VISIBLE);
                    findViewById(R.id.tv_add).setVisibility(GONE);
                    break;
            }
        });
    }
}
