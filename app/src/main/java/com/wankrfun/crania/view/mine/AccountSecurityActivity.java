package com.wankrfun.crania.view.mine;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.view.login.SetPasswordActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: AccountSecurityActivity
 * @Description: 账号与安全页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 2:08 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 2:08 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AccountSecurityActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @Override
    protected int initLayoutRes() {
        return R.layout.activity_account_security;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText("账号与安全");
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_password})
    void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_password://修改密码
//                ActivityUtils.startActivity(ChangePasswordActivity.class);
                bundle.putString("type","change");
                bundle.putString("phone", SPUtils.getInstance().getString(SpConfig.PHONE));
                ActivityUtils.startActivity(bundle, SetPasswordActivity.class);
                break;
        }
    }
}
