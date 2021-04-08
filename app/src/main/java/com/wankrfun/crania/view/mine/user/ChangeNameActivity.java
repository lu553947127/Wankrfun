package com.wankrfun.crania.view.mine.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.viewmodel.MineViewModel;
import com.wankrfun.crania.widget.XEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: ChangeNameActivity
 * @Description: 修改用户昵称页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2/23/21 11:50 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/23/21 11:50 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChangeNameActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.tv_bar_right)
    AppCompatTextView tvBarRight;
    @BindView(R.id.et_name)
    XEditText etName;
    private MineViewModel mineViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_change_name;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        KeyboardUtils.showSoftInput(etName);
        tvBarTitle.setText("修改昵称");
        tvBarRight.setText("确定");
        etName.setText(getIntent().getStringExtra("name"));

        mineViewModel = getViewModel(MineViewModel.class);

        //修改昵称成功返回结果
        mineViewModel.userUploadNameLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            finish();
        });
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_bar_right})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_bar_right:
                if (TextUtils.isEmpty(etName.getTrimmedString())){
                    ToastUtils.showShort("昵称不能为空");
                    return;
                }
                mineViewModel.getUploadName(etName.getTrimmedString());
                break;
        }
    }
}
