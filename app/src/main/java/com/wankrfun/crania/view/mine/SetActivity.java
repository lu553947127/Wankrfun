package com.wankrfun.crania.view.mine;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.utils.LoginUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: SetActivity
 * @Description: 系统设置页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 1:31 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 1:31 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SetActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_set;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText("系统设置");
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_user, R.id.tv_agreement, R.id.tv_privacy, R.id.tv_feedback, R.id.tv_out})
    void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_user://账号与安全
                ActivityUtils.startActivity(AccountSecurityActivity.class);
                break;
            case R.id.tv_agreement://用户协议
                bundle.putString("type", "user");
                ActivityUtils.startActivity(bundle, WebViewActivity.class);
                break;
            case R.id.tv_privacy://隐私协议
                bundle.putString("type", "privacy");
                ActivityUtils.startActivity(bundle, WebViewActivity.class);
                break;
            case R.id.tv_feedback://意见反馈
                ActivityUtils.startActivity(bundle, FeedbackActivity.class);
                break;
            case R.id.tv_out://退出登录
                new XPopup.Builder(this).asConfirm(getString(R.string.reminder), getString(R.string.login_out_is), () -> {
                    ToastUtils.showShort(R.string.sign_out);
                    LoginUtils.getExitLogin();
                }).show();
                break;
        }
    }
}
