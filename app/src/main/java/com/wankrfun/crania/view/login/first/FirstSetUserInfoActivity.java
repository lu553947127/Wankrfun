package com.wankrfun.crania.view.login.first;

import android.content.Intent;
import android.graphics.BitmapFactory;
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
import com.parse.ParseFile;
import com.wankrfun.crania.R;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.ParseUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.view.MainActivity;
import com.wankrfun.crania.viewmodel.LoginViewModel;
import com.wankrfun.crania.viewmodel.MineViewModel;
import com.wankrfun.crania.viewmodel.UserInfoViewModel;
import com.wankrfun.crania.widget.CircleImageView;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.login.first
 * @ClassName: FirstSetUserInfoActivity
 * @Description: 设置用户信息 头像/名称页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 1:20 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 1:20 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetUserInfoActivity extends BaseActivity {
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_login)
    AppCompatTextView tvLogin;
    @BindView(R.id.et_name)
    AppCompatEditText etName;
    private UserInfoViewModel userInfoViewModel;
    //头像上传状态
    private boolean isAvatar = false;
    //姓名键盘输入监听状态
    private boolean isName = false;
    //图片本地路径
    private String path = "";
    //期待状态数组
    private List<String> tag = new ArrayList<>();
    //活动类型数组
    private List<String> event_tag = new ArrayList<>();
    //图片ParseFile
    private ParseFile parseFile;
    private LoginViewModel loginViewModel;
    private MineViewModel mineViewModel;
    private final List<Object> editImage = new ArrayList<>();

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_user_info;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        tag.add(SPUtils.getInstance().getString(SpConfig.FIRST_TAG));
        event_tag.add(SPUtils.getInstance().getString(SpConfig.EVENT_TAG));

        userInfoViewModel = getViewModel(UserInfoViewModel.class);
        loginViewModel = getViewModel(LoginViewModel.class);
        mineViewModel= getViewModel(MineViewModel.class);

        //保存用户数据返回结果
        userInfoViewModel.saveUserInfoLiveData.observe(this, o ->{
            if (!TextUtils.isEmpty(o.getMessage()) && !o.getMessage().equals("success")){
                ToastUtils.showShort(o.getMessage());
                return;
            }
            loginViewModel.getLoginPassword(SPUtils.getInstance().getString(SpConfig.PHONE), SPUtils.getInstance().getString(SpConfig.PASSWORD));
        });

        //登录返回结果
        loginViewModel.loginLiveData.observe(this, parseUser -> {
            loginViewModel.getInvited(parseUser.getObjectId(), SPUtils.getInstance().getString(SpConfig.INVITE));
            loginViewModel.getSignUp();
            mineViewModel.getUploadPhoto(ParseUtils.setImageFile(new File(path)), parseUser.getObjectId());
            mineViewModel.getUploadImages(editImage, parseUser.getObjectId());
            SPUtils.getInstance().put(SpConfig.USER_ID, parseUser.getObjectId(), true);
            SPUtils.getInstance().put(SpConfig.TOKEN, parseUser.getSessionToken(), true);
            if (PermissionUtils.isCheckPermission(activity)){
                ToastUtils.showShort("注册成功");
                ActivityUtils.startActivity(MainActivity.class);
            }else {
                ActivityUtils.startActivity(FirstPermissionActivity.class);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.ALBUM_CODE & resultCode == RESULT_OK) {
            if (MyApplication.isAndroidQ) {
                LogUtils.e(Matisse.obtainResult(Objects.requireNonNull(data)).get(0));
                path = ImageLoader.getUriRealFilePath(activity,Matisse.obtainResult(data).get(0));
            }else {
                LogUtils.e(Matisse.obtainPathResult(Objects.requireNonNull(data)).get(0));
                path = Matisse.obtainPathResult(Objects.requireNonNull(data)).get(0);
            }
            ivAvatar.setImageBitmap(BitmapFactory.decodeFile(path));
            parseFile = userInfoViewModel.getUploadFile(new File(path));
            editImage.add(ParseUtils.setImageFile(new File(path)));
            isAvatar = true;
            if (isName){
                tvLogin.setVisibility(View.VISIBLE);
            }else {
                tvLogin.setVisibility(View.GONE);
            }
        }
    }

    @OnTextChanged(value = R.id.et_name, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputName(Editable editable) {
        String password = editable.toString().trim();
        isName = !TextUtils.isEmpty(password);
        if (isName && isAvatar){
            tvLogin.setVisibility(View.VISIBLE);
        }else {
            tvLogin.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.iv_bar_back,R.id.iv_avatar,R.id.tv_login})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.iv_avatar://上传头像
                PermissionUtils.openCameraOfStoragePermission(activity);
                break;
            case R.id.tv_login://登录
                if (etName.getText().toString().trim().length() > 15){
                    ToastUtils.showShort("抱歉，您输入的名称过长哦");
                    return;
                }

                userInfoViewModel.getSaveUserInfo(
                        SPUtils.getInstance().getString(SpConfig.PHONE),
                        SPUtils.getInstance().getString(SpConfig.PASSWORD),
                        etName.getText().toString().trim(),
                        SPUtils.getInstance().getString(SpConfig.SEX),
                        SPUtils.getInstance().getString(SpConfig.BIRTHDAY),
                        SPUtils.getInstance().getString(SpConfig.JOB),
                        tag,
                        event_tag,
                        parseFile
                );
                break;
        }
    }
}
