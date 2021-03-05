package com.wankrfun.crania.view.login;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.event.ParseEvent;
import com.wankrfun.crania.utils.DrawableUtils;
import com.wankrfun.crania.utils.HtmlUtils;
import com.wankrfun.crania.utils.LoginUtils;
import com.wankrfun.crania.utils.ParseUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.view.MainActivity;
import com.wankrfun.crania.view.login.first.FirstPermissionActivity;
import com.wankrfun.crania.view.login.first.FirstSetGenderActivity;
import com.wankrfun.crania.viewmodel.LoginViewModel;
import com.wankrfun.crania.widget.GradientTextView;
import com.wankrfun.crania.widget.XEditText;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.login
 * @ClassName: LoginPasswordActivity
 * @Description: 密码登录
 * @Author: 鹿鸿祥
 * @CreateDate: 1/9/21 1:25 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/9/21 1:25 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginPasswordActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_phone)
    AppCompatTextView tvPhone;
    @BindView(R.id.et_password)
    XEditText etPassword;
    @BindView(R.id.tv_login)
    GradientTextView tvLogin;
    private LoginViewModel loginViewModel;
    //密码键盘输入监听状态
    private boolean isPassword = false;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_login_password;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        ParseUtils.getQueryUserName(getIntent().getStringExtra("phone"));

        DrawableUtils.getDrawableRightView(activity, tvPhone, R.color.white, getIntent().getStringExtra("abbreviation"));
        tvPhone.setText(getIntent().getStringExtra("phone"));

        loginViewModel = getViewModel(LoginViewModel.class);

        //登录返回结果
        loginViewModel.loginLiveData.observe(this, parseUser -> {
            SPUtils.getInstance().put(SpConfig.USER_ID, parseUser.getObjectId(), true);
            SPUtils.getInstance().put(SpConfig.TOKEN, parseUser.getSessionToken(), true);
            SPUtils.getInstance().put(SpConfig.PHONE, getIntent().getStringExtra("phone"), true);
            SPUtils.getInstance().put(SpConfig.PASSWORD, etPassword.getTrimmedString(), true);
            if (LoginUtils.isFirstUser()){
                if (PermissionUtils.isCheckPermission(activity)){
                    ToastUtils.showShort(getString(R.string.login_success));
                    ActivityUtils.startActivity(MainActivity.class);
                }else {
                    ActivityUtils.startActivity(FirstPermissionActivity.class);
                }
            }else {
                ActivityUtils.startActivity(FirstSetGenderActivity.class);
            }
        });
    }

    @OnTextChanged(value = R.id.et_password, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputPassword(Editable editable) {
        String password = editable.toString().trim();
        isPassword = password.length() >= 8;
        if (isPassword){
            tvLogin.setEnabled(true);
        }else {
            tvLogin.setEnabled(false);
        }
    }

    @OnClick({R.id.iv_bar_back,R.id.tv_forget_password,R.id.tv_login})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_forget_password://忘记密码
                Bundle bundle = new Bundle();
                bundle.putString("type","verification");
                bundle.putString("phone",getIntent().getStringExtra("phone"));
                ActivityUtils.startActivity(bundle,VerificationCodeActivity.class);
                break;
            case R.id.tv_login://登录
                loginViewModel.getLoginPassword(getIntent().getStringExtra("phone"), etPassword.getTrimmedString());
                break;
        }
    }

    @Subscribe
    public void onEventParse(ParseEvent event) {
        tvTitle.setText(HtmlUtils.setSpan(activity, getString(R.string.login_password_title, event.getMessage()), event.getMessage(), R.color.color_F4D8AF));
    }
}
