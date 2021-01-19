package com.wankrfun.crania.utils;

import android.app.ActivityManager;
import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: LoginUtils
 * @Description: 登录相关检测工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:34 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:34 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginUtils {

    /**
     * 检测是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        String token = SPUtils.getInstance().getString(SpConfig.TOKEN);
        if (StringUtils.isTrimEmpty(token)) {
            return false;
        }
        return true;
    }

    /**
     * 退出App
     *
     * @return
     */
    public static void getExitApp(BaseActivity activity) {
        // 1. 通过Context获取ActivityManager
        ActivityManager activityManager = (ActivityManager) activity.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        // 2. 通过ActivityManager获取任务栈
        List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
        // 3. 逐个关闭Activity
        for (ActivityManager.AppTask appTask : appTaskList) {
            appTask.finishAndRemoveTask();
        }
    }
}
