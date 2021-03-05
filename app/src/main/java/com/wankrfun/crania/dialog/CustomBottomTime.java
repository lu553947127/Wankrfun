package com.wankrfun.crania.dialog;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.BottomPopupView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.event.EventsEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: CustomBottomTime
 * @Description: 自定义选择时间底部弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 2/3/21 10:16 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/3/21 10:16 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomBottomTime extends BottomPopupView {
    private Context context;
    private String time = "可以商讨";
    public CustomBottomTime(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_custom_time;
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
            if (StringUtils.isTrimEmpty(time)){
                ToastUtils.showShort("具体时间，不能为空哦");
                return;
            }
            EventBus.getDefault().post(new EventsEvent("time", time));
            dismiss();
        });

        AppCompatTextView tv_specific = findViewById(R.id.tv_specific);
        AppCompatTextView tv_discuss = findViewById(R.id.tv_discuss);
        AppCompatTextView tv_time = findViewById(R.id.tv_time);

        tv_specific.setOnClickListener(view -> {
            tv_specific.setBackgroundResource(R.drawable.shape_yellow_5);
            tv_discuss.setBackgroundResource(R.drawable.shape_gray_5);
            tv_specific.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tv_discuss.setTextColor(getResources().getColor(R.color.white));
            findViewById(R.id.tv_time).setVisibility(VISIBLE);
            time = "";
            tv_time.setText("请选择具体时间");
        });

        tv_discuss.setOnClickListener(view -> {
            tv_specific.setBackgroundResource(R.drawable.shape_gray_5);
            tv_discuss.setBackgroundResource(R.drawable.shape_yellow_5);
            tv_specific.setTextColor(getResources().getColor(R.color.white));
            tv_discuss.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            findViewById(R.id.tv_time).setVisibility(GONE);
            time = "可以商讨";
        });

        tv_time.setOnClickListener(view -> {
            //时间选择器
            TimePickerView pvTime = new TimePickerBuilder(context, (date, v) -> {
                time = getTime(date);
                tv_time.setText(time);
            })
                    .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                    .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                    .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                    .setSubmitColor(context.getResources().getColor(R.color.colorPrimary))//确定按钮文字颜色
                    .setCancelColor(context.getResources().getColor(R.color.white))//取消按钮文字颜色
                    .setTitleBgColor(0xFF000000)//标题背景颜色 Night mode
                    .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                    .build();
            pvTime.show();
        });
    }

    /**
     * 日期转换时间格式
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    private String getTime(Date date) {
        LogUtils.e("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
