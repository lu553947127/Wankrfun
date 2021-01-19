package com.wankrfun.crania.view.login.first;

import android.os.Bundle;
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
 * @ClassName: FirstSetJobActivity
 * @Description: 设置工作状态页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 9:28 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 9:28 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetJobActivity extends BaseActivity {
    @BindView(R.id.tv_student)
    DrawableCenterTextView tvStudent;
    @BindView(R.id.tv_work)
    DrawableCenterTextView tvWork;
    @BindView(R.id.tv_free)
    DrawableCenterTextView tvFree;
    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_job;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }

    @OnClick({R.id.iv_bar_back,R.id.tv_student,R.id.tv_work,R.id.tv_free})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_student://学生
                tvStudent.setBackgroundResource(R.drawable.shape_yellow_5);
                tvWork.setBackgroundResource(R.drawable.shape_gray_5);
                tvFree.setBackgroundResource(R.drawable.shape_gray_5);
                ActivityUtils.startActivity(FirstSetExpectActivity.class);
                break;
            case R.id.tv_work://工作
                tvStudent.setBackgroundResource(R.drawable.shape_gray_5);
                tvWork.setBackgroundResource(R.drawable.shape_yellow_5);
                tvFree.setBackgroundResource(R.drawable.shape_gray_5);
                ActivityUtils.startActivity(FirstSetExpectActivity.class);
                break;
            case R.id.tv_free://自由职业者
                tvStudent.setBackgroundResource(R.drawable.shape_gray_5);
                tvWork.setBackgroundResource(R.drawable.shape_gray_5);
                tvFree.setBackgroundResource(R.drawable.shape_yellow_5);
                ActivityUtils.startActivity(FirstSetExpectActivity.class);
                break;
        }
    }
}
