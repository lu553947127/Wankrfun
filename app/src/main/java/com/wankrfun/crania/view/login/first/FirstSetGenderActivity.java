package com.wankrfun.crania.view.login.first;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.widget.DrawableCenterTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.login.first
 * @ClassName: FirstSetGenderActivity
 * @Description: 设置性别页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/13/21 9:38 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/13/21 9:38 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetGenderActivity extends BaseActivity {
    @BindView(R.id.tv_female)
    DrawableCenterTextView tvFemale;
    @BindView(R.id.tv_male)
    DrawableCenterTextView tvMale;

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_gender;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_female,R.id.tv_male})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_female://女生
                tvFemale.setBackgroundResource(R.drawable.shape_yellow_5);
                tvMale.setBackgroundResource(R.drawable.shape_gray_5);
                ActivityUtils.startActivity(FirstSetBirthdayActivity.class);
                break;
            case R.id.tv_male://男生
                tvMale.setBackgroundResource(R.drawable.shape_yellow_5);
                tvFemale.setBackgroundResource(R.drawable.shape_gray_5);
                ActivityUtils.startActivity(FirstSetBirthdayActivity.class);
                break;
        }
    }

    //重写返回键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //取消返回按钮事件
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
