package com.wankrfun.crania.view.mine;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.BarUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: UserAgreementActivity
 * @Description: 用户协议页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 2:22 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 2:22 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserAgreementActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @Override
    protected int initLayoutRes() {
        return R.layout.activity_user_agreement;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText("用户协议");
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
