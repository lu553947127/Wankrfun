package com.wankrfun.crania.view.login.first;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.viewmodel.UserInfoViewModel;
import com.wankrfun.crania.widget.CircleImageView;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

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
    private UserInfoViewModel userInfoViewModel;

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
        userInfoViewModel = getViewModel(UserInfoViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.ALBUM_CODE & resultCode == RESULT_OK) {
            String path = "";
            if (MyApplication.isAndroidQ) {
                LogUtils.e(Matisse.obtainResult(Objects.requireNonNull(data)).get(0));
                path = ImageLoader.getUriRealFilePath(activity,Matisse.obtainResult(data).get(0));
            }else {
                LogUtils.e(Matisse.obtainPathResult(Objects.requireNonNull(data)).get(0));
                path = Matisse.obtainPathResult(Objects.requireNonNull(data)).get(0);
            }
            ivAvatar.setImageBitmap(BitmapFactory.decodeFile(path));
            userInfoViewModel.getUploadFile(new File(path));
        }
    }

    @OnClick({R.id.iv_bar_back,R.id.iv_avatar})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.iv_avatar:
                PermissionUtils.openCameraOfStoragePermission(activity);
                break;
        }
    }
}
