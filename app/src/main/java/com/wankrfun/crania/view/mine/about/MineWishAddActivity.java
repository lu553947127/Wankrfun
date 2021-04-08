package com.wankrfun.crania.view.mine.about;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import fj.edittextcount.lib.FJEditTextCount;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineWishAddActivity
 * @Description: 创建心愿页面
 * @Author: 鹿鸿祥
 * @CreateDate: 3/22/21 3:44 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/22/21 3:44 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineWishAddActivity extends BaseActivity {
    @BindView(R.id.et_comment)
    FJEditTextCount fjEditTextCount;
    @BindView(R.id.tv_release)
    AppCompatTextView tvRelease;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_wish_add;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        fjEditTextCount.getBackground().setAlpha(60);
        tvRelease.getBackground().setAlpha(60);
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_release})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_release:
                break;
        }
    }
}
