package com.wankrfun.crania.view.mine.user;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.ParseUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.viewmodel.MineViewModel;
import com.wankrfun.crania.widget.CircleImageView;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: ChangeAvatarActivity
 * @Description: 更改用户头像页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2/24/21 1:48 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/24/21 1:48 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChangeAvatarActivity extends BaseActivity {
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    //图片本地路径
    private String path = "";
    private MineViewModel mineViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_change_avatar;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        ImageLoader.load(activity, new ImageConfig.Builder()
                .url(getIntent().getStringExtra("images"))
                .imageView(ivAvatar)
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .build());
        mineViewModel = getViewModel(MineViewModel.class);

        //上传头像成功返回结果
        mineViewModel.userUploadPhotoLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            finish();
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
        }
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_complete, R.id.iv_avatar})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_complete://完成
                if (TextUtils.isEmpty(path)){
                    ToastUtils.showShort("您的头像没有变化哦");
                    return;
                }
                mineViewModel.getUploadPhoto(ParseUtils.setImageFile(new File(path)), SPUtils.getInstance().getString(SpConfig.USER_ID));
                break;
            case R.id.iv_avatar://上传头像
                PermissionUtils.openCameraOfStoragePermission(activity);
                break;
        }
    }
}
