package com.wankrfun.crania.view.mine.set;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.BarUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.BuildConfig;
import com.wankrfun.crania.widget.CustomWebView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: WebViewActivity
 * @Description: h5加载页
 * @Author: 鹿鸿祥
 * @CreateDate: 3/4/21 9:21 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/4/21 9:21 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.web_view)
    CustomWebView webView;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_web_view;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        switch (getIntent().getStringExtra("type")){
            case "user":
                webView.loadUrl(BuildConfig.USER_URL);
                tvBarTitle.setText("用户协议");
                break;
            case "privacy":
                webView.loadUrl(BuildConfig.AGREEMENT_URL);
                tvBarTitle.setText("隐私协议");
                break;
        }
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
