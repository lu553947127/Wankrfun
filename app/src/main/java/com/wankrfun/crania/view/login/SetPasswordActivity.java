package com.wankrfun.crania.view.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.view.MainActivity;
import com.wankrfun.crania.view.login.first.FirstSetGenderActivity;
import com.wankrfun.crania.viewmodel.LoginViewModel;
import com.wankrfun.crania.widget.GradientTextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.login
 * @ClassName: SetPasswordActivity
 * @Description: 设置密码页
 * @Author: 鹿鸿祥
 * @CreateDate: 1/11/21 3:34 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/11/21 3:34 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SetPasswordActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.et_password)
    AppCompatEditText etPassword;
    @BindView(R.id.et_password_confirm)
    AppCompatEditText etPasswordConfirm;
    @BindView(R.id.tv_login)
    GradientTextView tvLogin;
    private LoginViewModel loginViewModel;
    //密码键盘输入监听状态
    private boolean isPassword = false;
    //密码键盘输入监听状态
    private boolean isPasswordConfirm = false;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_set_password;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(getIntent().getStringExtra("type"))){
            tvTitle.setText(getString(R.string.rebuild_password));
        }

        loginViewModel = getViewModel(LoginViewModel.class);

        //登录返回结果
        loginViewModel.loginLiveData.observe(this, parseUser -> {
            LogUtils.e(parseUser.getUsername());
            LogUtils.e(parseUser.getSessionToken());
            LogUtils.e(parseUser.getObjectId());
//            SPUtils.getInstance().put(SpConfig.TOKEN, parseUser.getSessionToken(), true);
//            ActivityUtils.startActivity(MainActivity.class);
            ToastUtils.showShort(getString(R.string.login_success));
            ActivityUtils.startActivity(FirstSetGenderActivity.class);
        });

        //重置密码返回结果
        loginViewModel.resetPasswordLiveData.observe(this, object -> {
            ToastUtils.showShort(getString(R.string.login_success));
            ActivityUtils.startActivity(FirstSetGenderActivity.class);
        });
    }

    @OnTextChanged(value = R.id.et_password, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputPassword(Editable editable) {
        String password = editable.toString().trim();
        isPassword = password.length() == 8;
        tvLogin.setEnabled(isPassword && isPasswordConfirm);
    }

    @OnTextChanged(value = R.id.et_password_confirm, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputPasswordConfirm(Editable editable) {
        String password = editable.toString().trim();
        isPasswordConfirm = password.length() == 8;
        tvLogin.setEnabled(isPassword && isPasswordConfirm);
    }

    @OnClick({R.id.iv_bar_back,R.id.tv_login})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_login://登录
                if (!TextUtils.isEmpty(getIntent().getStringExtra("type"))){
                    loginViewModel.getResetPassword(getIntent().getStringExtra("phone"), etPassword.getText().toString());
                }else {
                    loginViewModel.getLoginPassword(getIntent().getStringExtra("phone"), etPassword.getText().toString());
                }
                break;
        }
    }
}
