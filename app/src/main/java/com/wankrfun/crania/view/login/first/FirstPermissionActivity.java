package com.wankrfun.crania.view.login.first;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.utils.NotificationsUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.view.MainActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.login.first
 * @ClassName: FirstPermissionActivity
 * @Description: 第一次开启权限页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 2:29 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 2:29 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstPermissionActivity extends BaseActivity {
    @BindView(R.id.iv_notice)
    AppCompatImageView ivNotice;
    @BindView(R.id.iv_location)
    AppCompatImageView ivLocation;

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_permission;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        PermissionUtils.getOpenLocationPermission(activity);
    }

    @OnClick({R.id.iv_notice,R.id.iv_location,R.id.tv_login})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_notice://开启通知权限
                if (!NotificationsUtils.isNotificationEnabled(this)){
                    showNoticePermissionDialog(getString(R.string.permission_notice));
                }
                break;
            case R.id.iv_location://开启定位权限
                if (!PermissionUtils.isCheckPermission(activity)){
                    PermissionUtils.getOpenLocationPermission(activity);
                }
                break;
            case R.id.tv_login://登录
                if (!PermissionUtils.isCheckPermission(activity)){
                    PermissionUtils.getOpenLocationPermission(activity);
                }else {
                    ActivityUtils.startActivity(MainActivity.class);
                }
                break;
        }
    }

    @Subscribe
    public void onEventLocation(String isBoolean) {
        ActivityUtils.startActivity(MainActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NotificationsUtils.isNotificationEnabled(activity)){
            ivNotice.setBackgroundResource(R.drawable.icon_permission_notice);
        }else {
            ivNotice.setBackgroundResource(R.drawable.icon_permission_notice_on);
        }

        if (!PermissionUtils.isCheckPermission(activity)){
            ivLocation.setBackgroundResource(R.drawable.icon_permission_location);
        }else {
            ivLocation.setBackgroundResource(R.drawable.icon_permission_location_on);

        }
    }

    //重写返回键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //取消返回按钮事件
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
