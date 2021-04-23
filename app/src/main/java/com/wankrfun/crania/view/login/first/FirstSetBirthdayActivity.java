package com.wankrfun.crania.view.login.first;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.widget.GradientTextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.login.first
 * @ClassName: FirstSetBirthdayActivity
 * @Description: 设置出生日期页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/13/21 2:54 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/13/21 2:54 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetBirthdayActivity extends BaseActivity {
    @BindView(R.id.tv_error)
    AppCompatTextView tvError;
    @BindView(R.id.tv_select)
    AppCompatTextView tvSelect;
    @BindView(R.id.tv_next)
    GradientTextView tvNext;
    //年键盘输入监听状态
    private boolean isYear = false;
    //月键盘输入监听状态
    private boolean isMonth = false;
    //日键盘输入监听状态
    private boolean isDay = false;
    //年月日
    private String year, month, day;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_birthday;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString(SpConfig.BIRTHDAY))){
            tvSelect.setText(SPUtils.getInstance().getString(SpConfig.BIRTHDAY));
            tvSelect.setTextColor(getResources().getColor(R.color.white));
            tvNext.setEnabled(true);
        }else {
            tvSelect.setText("请选择出生日期");
            tvSelect.setTextColor(getResources().getColor(R.color.color_545454));
            tvNext.setEnabled(false);
        }
    }

    @OnTextChanged(value = R.id.et_year, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputYear(Editable editable) {
        year = editable.toString().trim();
        isYear = !year.isEmpty();
        getStartUp();
    }

    @OnTextChanged(value = R.id.et_month, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputMonth(Editable editable) {
        month = editable.toString().trim();
        isMonth = !month.isEmpty();
        getStartUp();
    }

    @OnTextChanged(value = R.id.et_day, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputDay(Editable editable) {
        day = editable.toString().trim();
        isDay = !day.isEmpty();
        getStartUp();
    }

    /**
     * 下一步
     */
    private void getStartUp(){
        if (isYear && isMonth && isDay){
            LogUtils.e(RegexUtils.isDate(year + "-" + month + "-" + day));
            if (RegexUtils.isDate(year + "-" + month + "-" + day)){
                SPUtils.getInstance().put(SpConfig.BIRTHDAY, year + "-" + month + "-" + day, true);
                ActivityUtils.startActivity(FirstSetJobActivity.class);
                tvError.setVisibility(View.INVISIBLE);
            }else {
                tvError.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_select, R.id.tv_next})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_select://选择出生日期
                TimePickerView pvTime = new TimePickerBuilder(activity, (date, v) -> {
                    tvSelect.setText(getTime(date));
                    tvSelect.setTextColor(getResources().getColor(R.color.white));
                    SPUtils.getInstance().put(SpConfig.BIRTHDAY, getTime(date), true);
                    tvNext.setEnabled(true);
                })
                        .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setSubmitColor(getResources().getColor(R.color.colorPrimary))//确定按钮文字颜色
                        .setCancelColor(getResources().getColor(R.color.white))//取消按钮文字颜色
                        .setTitleBgColor(0xFF000000)//标题背景颜色 Night mode
                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                        .build();
                pvTime.show();
                break;
            case R.id.tv_next://下一步
                if (TextUtils.isEmpty(SPUtils.getInstance().getString(SpConfig.BIRTHDAY))){
                    return;
                }

                ActivityUtils.startActivity(FirstSetJobActivity.class);
                break;
        }
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
