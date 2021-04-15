package com.wankrfun.crania.view.mine.about;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.event.CardEvent;
import com.wankrfun.crania.utils.RefreshUtils;
import com.wankrfun.crania.viewmodel.MineCardViewModel;

import org.greenrobot.eventbus.EventBus;

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
    FJEditTextCount etComment;
    @BindView(R.id.tv_release)
    AppCompatTextView tvRelease;
    private MineCardViewModel mineCardViewModel;

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
        etComment.getBackground().setAlpha(60);
        tvRelease.getBackground().setAlpha(60);

        if (!TextUtils.isEmpty(getIntent().getStringExtra("content"))){
            etComment.setText(getIntent().getStringExtra("content"));
        }

        mineCardViewModel = getViewModel(MineCardViewModel.class);

        //添加成功心愿返回结果
        mineCardViewModel.wishCreateLiveData.observe(this, challengeStatusBean -> {
            EventBus.getDefault().post(new CardEvent("wish"));
            ActivityUtils.finishActivity(MineWishActivity.class);
            finish();
        });

        //编辑成功心愿返回结果
        mineCardViewModel.wishEditLiveData.observe(this, challengeStatusBean -> {
            EventBus.getDefault().post(new CardEvent("wish"));
            ActivityUtils.finishActivity(MineWishActivity.class);
            finish();
        });
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_release})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_release:
                if (TextUtils.isEmpty(etComment.getText())){
                    ToastUtils.showShort("心愿不能为空");
                    return;
                }

                if (!TextUtils.isEmpty(getIntent().getStringExtra("content"))){
                    mineCardViewModel.getWishEdit(getIntent().getStringExtra("id"), etComment.getText());
                }else {
                    mineCardViewModel.getWishCreate(etComment.getText(), RefreshUtils.getRandomColor());
                }
                break;
        }
    }
}
