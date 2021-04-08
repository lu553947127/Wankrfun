package com.wankrfun.crania.view.mine.set;

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
 * @ClassName: ChangePasswordActivity
 * @Description: 修改密码页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 2:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 2:11 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.tv_bar_right)
    AppCompatTextView tvBarRight;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_change_password;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText("修改密码");
        tvBarRight.setText("完成");
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_bar_right})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_bar_right://保存

                break;
        }
    }
}
