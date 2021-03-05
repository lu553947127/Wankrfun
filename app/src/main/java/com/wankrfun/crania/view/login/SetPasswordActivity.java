package com.wankrfun.crania.view.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.utils.LoginUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.view.MainActivity;
import com.wankrfun.crania.view.login.first.FirstPermissionActivity;
import com.wankrfun.crania.view.login.first.FirstSetGenderActivity;
import com.wankrfun.crania.viewmodel.LoginViewModel;
import com.wankrfun.crania.widget.GradientTextView;
import com.wankrfun.crania.widget.XEditText;

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
    XEditText etPassword;
    @BindView(R.id.et_password_confirm)
    XEditText etPasswordConfirm;
    @BindView(R.id.tv_login)
    GradientTextView tvLogin;
    @BindView(R.id.tv_invite)
    AppCompatTextView tvInvite;
    private LoginViewModel loginViewModel;
    //密码键盘输入监听状态
    private boolean isPassword = false;
    //密码键盘输入监听状态
    private boolean isPasswordConfirm = false;
    //邀请码
    private String invite;

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
        KeyboardUtils.showSoftInput(etPassword);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("type"))){
            tvTitle.setText(getString(R.string.rebuild_password));
            tvInvite.setVisibility(View.GONE);
            if (getIntent().getStringExtra("type").equals("change")){
                tvLogin.setText("保存");
            }
        }

        loginViewModel = getViewModel(LoginViewModel.class);

        //登录返回结果
        loginViewModel.loginLiveData.observe(this, parseUser -> {
            SPUtils.getInstance().put(SpConfig.USER_ID, parseUser.getObjectId(), true);
            SPUtils.getInstance().put(SpConfig.TOKEN, parseUser.getSessionToken(), true);
            SPUtils.getInstance().put(SpConfig.PHONE, getIntent().getStringExtra("phone"), true);
            SPUtils.getInstance().put(SpConfig.PASSWORD, etPassword.getTrimmedString(), true);
            if (PermissionUtils.isCheckPermission(activity)){
                ToastUtils.showShort(getString(R.string.login_success));
                ActivityUtils.startActivity(MainActivity.class);
            }else {
                ActivityUtils.startActivity(FirstPermissionActivity.class);
            }
        });

        //重置密码返回结果
        loginViewModel.resetPasswordLiveData.observe(this, object -> {
            if (getIntent().getStringExtra("type").equals("change")){
                ToastUtils.showShort("密码修改成功，请您重新登录");
                LoginUtils.getExitLogin();
            }else {
                loginViewModel.getLoginPassword(getIntent().getStringExtra("phone"), etPassword.getTrimmedString());
            }
        });
    }

    @OnTextChanged(value = R.id.et_password, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputPassword(Editable editable) {
        String password = editable.toString().trim();
        isPassword = password.length() >= 8;
        if (isPassword && isPasswordConfirm){
            tvLogin.setEnabled(true);
        }else {
            tvLogin.setEnabled(false);
        }
    }

    @OnTextChanged(value = R.id.et_password_confirm, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputPasswordConfirm(Editable editable) {
        String password = editable.toString().trim();
        isPasswordConfirm = password.length() >= 8;
        if (isPassword && isPasswordConfirm){
            tvLogin.setEnabled(true);
        }else {
            tvLogin.setEnabled(false);
        }
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_login, R.id.tv_invite})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_login://登录

                if (!etPassword.getTrimmedString().equals(etPasswordConfirm.getTrimmedString())){
                    ToastUtils.showShort("两次密码输入的不一致哦");
                    return;
                }

                if (!TextUtils.isEmpty(getIntent().getStringExtra("type"))){
                    loginViewModel.getResetPassword(getIntent().getStringExtra("phone"), etPassword.getTrimmedString());
                }else {
                    SPUtils.getInstance().put(SpConfig.INVITE, invite, true);
                    SPUtils.getInstance().put(SpConfig.PHONE, getIntent().getStringExtra("phone"), true);
                    SPUtils.getInstance().put(SpConfig.PASSWORD, etPassword.getTrimmedString(), true);
                    ActivityUtils.startActivity(FirstSetGenderActivity.class);
                }
                break;
            case R.id.tv_invite://点击输入邀请码
                new XPopup.Builder(activity)
                        .autoOpenSoftInput(true)
                        .isRequestFocus(false)
                        .asInputConfirm("邀请码", "", null, "请输入邀请码", text -> {
                            invite = text;
                        }).show();
                break;
        }
    }
}
