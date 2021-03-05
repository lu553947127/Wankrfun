package com.wankrfun.crania.dialog;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import com.lxj.xpopup.core.BottomPopupView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.event.EventsEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: CustomBottomPeople
 * @Description: 自定义选择参与人底部弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 2/3/21 11:20 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/3/21 11:20 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomBottomPeople extends BottomPopupView {
    private String people = "不限人数";
    private String sex = "不限性别";
    private int num = 1;
    public CustomBottomPeople(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_custom_people;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onShow() {
        super.onShow();

        findViewById(R.id.tv_cancel).setOnClickListener(view -> dismiss());

        findViewById(R.id.tv_yes).setOnClickListener(view -> {
            EventBus.getDefault().post(new EventsEvent("people", people + "," + sex, num , sex));
            dismiss();
        });

        AppCompatTextView tv_specific = findViewById(R.id.tv_specific);
        AppCompatTextView tv_discuss = findViewById(R.id.tv_discuss);
        AppCompatTextView tv_num = findViewById(R.id.tv_num);
        AppCompatTextView tv_man = findViewById(R.id.tv_man);
        AppCompatTextView tv_woman = findViewById(R.id.tv_woman);
        AppCompatTextView tv_unlimited = findViewById(R.id.tv_unlimited);

        tv_specific.setOnClickListener(view -> {
            tv_specific.setBackgroundResource(R.drawable.shape_yellow_5);
            tv_discuss.setBackgroundResource(R.drawable.shape_gray_5);
            tv_specific.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tv_discuss.setTextColor(getResources().getColor(R.color.white));
            findViewById(R.id.ll_num).setVisibility(VISIBLE);
            people = num + "人";
            tv_num.setText(String.valueOf(num));
        });

        tv_discuss.setOnClickListener(view -> {
            tv_specific.setBackgroundResource(R.drawable.shape_gray_5);
            tv_discuss.setBackgroundResource(R.drawable.shape_yellow_5);
            tv_specific.setTextColor(getResources().getColor(R.color.white));
            tv_discuss.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            findViewById(R.id.ll_num).setVisibility(GONE);
            people = "不限人数";
        });

        tv_man.setOnClickListener(view -> {
            tv_man.setBackgroundResource(R.drawable.shape_yellow_5);
            tv_woman.setBackgroundResource(R.drawable.shape_gray_5);
            tv_unlimited.setBackgroundResource(R.drawable.shape_gray_5);
            findViewById(R.id.tv_man_tips).setVisibility(VISIBLE);
            findViewById(R.id.tv_woman_tips).setVisibility(INVISIBLE);
            tv_man.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tv_woman.setTextColor(getResources().getColor(R.color.white));
            tv_unlimited.setTextColor(getResources().getColor(R.color.white));
            sex = "限男生";
        });

        tv_woman.setOnClickListener(view -> {
            tv_man.setBackgroundResource(R.drawable.shape_gray_5);
            tv_woman.setBackgroundResource(R.drawable.shape_yellow_5);
            tv_unlimited.setBackgroundResource(R.drawable.shape_gray_5);
            findViewById(R.id.tv_man_tips).setVisibility(INVISIBLE);
            findViewById(R.id.tv_woman_tips).setVisibility(VISIBLE);
            tv_man.setTextColor(getResources().getColor(R.color.white));
            tv_woman.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tv_unlimited.setTextColor(getResources().getColor(R.color.white));
            sex = "限女生";
        });

        tv_unlimited.setOnClickListener(view -> {
            tv_man.setBackgroundResource(R.drawable.shape_gray_5);
            tv_woman.setBackgroundResource(R.drawable.shape_gray_5);
            tv_unlimited.setBackgroundResource(R.drawable.shape_yellow_5);
            findViewById(R.id.tv_man_tips).setVisibility(INVISIBLE);
            findViewById(R.id.tv_woman_tips).setVisibility(INVISIBLE);
            tv_man.setTextColor(getResources().getColor(R.color.white));
            tv_woman.setTextColor(getResources().getColor(R.color.white));
            tv_unlimited.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            sex = "不限性别";
        });

        findViewById(R.id.tv_reduce).setOnClickListener(view -> {
            if (num > 1) num --;
            tv_num.setText(String.valueOf(num));
            people = num + "人";
        });

        findViewById(R.id.tv_add).setOnClickListener(view -> {
            num ++;
            tv_num.setText(String.valueOf(num));
            people = num + "人";
        });
    }

}
