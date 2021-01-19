package com.wankrfun.crania.view.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.viewmodel.LoginViewModel;
import com.wankrfun.crania.widget.VerificationCodeInputView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.login
 * @ClassName: VerificationCodeActivity
 * @Description: 获取验证码页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/11/21 9:59 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/11/21 9:59 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class VerificationCodeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_verification_code)
    VerificationCodeInputView tvVerificationCode;
    @BindView(R.id.tv_resend)
    AppCompatTextView tvResend;
    @BindView(R.id.tv_second)
    AppCompatTextView tvSecond;
    private LoginViewModel loginViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_verification_code;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        if (!TextUtils.isEmpty(getIntent().getStringExtra("type"))){
            tvTitle.setText(getString(R.string.verification));
        }

        loginViewModel = getViewModel(LoginViewModel.class);

        //获取验证码返回回调
        loginViewModel.sendCodeLiveData.observe(this, object -> {
            loginViewModel.sendVerificationCode();
        });

        //获取验证码倒计时返回结果
        loginViewModel.timeLiveDataLiveData.observe(this, aLong -> {
            getCode(aLong);
        });

        //验证验证码返回结果
        loginViewModel.verificationCodeLiveData.observe(this, object -> {
            Bundle bundle = new Bundle();
            bundle.putString("type", getIntent().getStringExtra("type"));
            ActivityUtils.startActivity(bundle, SetPasswordActivity.class);
        });

        loginViewModel.getSendCode(getIntent().getStringExtra("phone"));

        //验证码输入完成回调
        tvVerificationCode.setOnCompleteListener(content -> {
            loginViewModel.getVerificationCode(getIntent().getStringExtra("phone"),content);
        });
    }

    //验证码获取成功后操作
    private void getCode(Long aLong) {
        if (aLong == -1) {
            //重新获取
            tvResend.setClickable(true);
            tvResend.setTextColor(getResources().getColor(R.color.white));
            tvSecond.setVisibility(View.GONE);
        } else {
            tvResend.setClickable(false);
            tvResend.setTextColor(getResources().getColor(R.color.color_979797));
            tvSecond.setVisibility(View.VISIBLE);
            tvSecond.setText(getString(R.string.second ,String.valueOf(aLong)));
        }
    }

    @OnClick({R.id.iv_bar_back,R.id.tv_resend})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_resend://重新发送验证码
                loginViewModel.getSendCode(getIntent().getStringExtra("phone"));
                break;
        }
    }
}
