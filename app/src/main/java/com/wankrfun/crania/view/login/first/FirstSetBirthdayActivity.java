package com.wankrfun.crania.view.login.first;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;

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

    @OnClick({R.id.iv_bar_back})
    void onClick() {
      finish();
    }
}
