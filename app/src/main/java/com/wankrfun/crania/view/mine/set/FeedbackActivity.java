package com.wankrfun.crania.view.mine.set;

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
 * @ClassName: FeedbackActivity
 * @Description: 意见反馈页面
 * @Author: 鹿鸿祥
 * @CreateDate: 3/17/21 4:41 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/17/21 4:41 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.tv_bar_right)
    AppCompatTextView tvBarRight;
    @BindView(R.id.et_content)
    XEditText etContent;
    private MineViewModel mineViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_feedback;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText("意见反馈");
        tvBarRight.setText("提交");
        KeyboardUtils.showSoftInput(etContent);

        mineViewModel = getViewModel(MineViewModel.class);

        //提交意见反馈成功结果
        mineViewModel.userFeedbackLiveData.observe(this, eventsCreateBean -> {
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
                if (TextUtils.isEmpty(etContent.getTrimmedString())){
                    ToastUtils.showShort("意见反馈输入不能为空哦");
                    return;
                }

                mineViewModel.getFeedback(etContent.getTrimmedString());
                break;
        }
    }
}
