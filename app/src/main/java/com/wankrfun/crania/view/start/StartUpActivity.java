package com.wankrfun.crania.view.start;

import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.dialog.AgreementDialog;
import com.wankrfun.crania.utils.LoginUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.view.MainActivity;
import com.wankrfun.crania.view.login.LoginActivity;
import com.wankrfun.crania.view.login.first.FirstPermissionActivity;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.login
 * @ClassName: StartUpActivity
 * @Description: 启动页
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:19 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:19 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class StartUpActivity extends BaseActivity {
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_start_up;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        if (LoginUtils.isFirstApp(activity)){
            getIntoActivity();
        }else {
            //协议弹窗
            AgreementDialog agreementDialog = new AgreementDialog(activity);
            agreementDialog.showDialog();
            agreementDialog.setOnCustomClickListener(this::getIntoActivity);
        }
    }

    //判断跳转
    private void getIntoActivity() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1500);//休眠1.5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //判断是否获取过token
                if (LoginUtils.isLogin()){
                    if (PermissionUtils.isCheckPermission(activity)){
                        ActivityUtils.startActivity(MainActivity.class);
                    }else {
                        ActivityUtils.startActivity(FirstPermissionActivity.class);
                    }
                }else {
                    ActivityUtils.startActivity(LoginActivity.class);
                }

                finish();
            }
        }.start();
    }
}
