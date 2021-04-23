package com.wankrfun.crania.view.mine.about;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.viewmodel.MineCardViewModel;

import butterknife.BindView;
import butterknife.OnClick;
import fj.edittextcount.lib.FJEditTextCount;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineQuestionAddInfoActivity
 * @Description: 创建问答页面
 * @Author: 鹿鸿祥
 * @CreateDate: 4/23/21 11:13 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/23/21 11:13 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineQuestionAddInfoActivity extends BaseActivity {
    @BindView(R.id.et_comment)
    FJEditTextCount etComment;
    @BindView(R.id.tv_question)
    AppCompatTextView tvQuestion;
    @BindView(R.id.tv_release)
    AppCompatTextView tvRelease;
    private MineCardViewModel mineCardViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_question_add_info;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        etComment.getBackground().setAlpha(60);
        tvRelease.getBackground().setAlpha(60);

        tvQuestion.setText("问：" + getIntent().getStringExtra("question"));

        if (!TextUtils.isEmpty(getIntent().getStringExtra("answer"))){
            etComment.setText(getIntent().getStringExtra("answer"));
        }

        mineCardViewModel = getViewModel(MineCardViewModel.class);

        //创建问答成功返回结果
        mineCardViewModel.questionCreateLiveData.observe(this, challengeStatusBean -> {
            ActivityUtils.finishActivity(MineQuestionAddActivity.class);
            finish();
        });

        //编辑问答成功返回结果
        mineCardViewModel.questionEditLiveData.observe(this, challengeStatusBean -> {
            finish();
        });
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_release})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_release:
                if (TextUtils.isEmpty(etComment.getText())){
                    ToastUtils.showShort("答案不能为空");
                    return;
                }

                if (!TextUtils.isEmpty(getIntent().getStringExtra("answer"))){
                    mineCardViewModel.getQuestionEdit(getIntent().getStringExtra("id"), etComment.getText());
                }else {
                    mineCardViewModel.getQuestionCreate(getIntent().getStringExtra("question"), etComment.getText());
                }
                break;
        }
    }
}
