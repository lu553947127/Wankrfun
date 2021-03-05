package com.wankrfun.crania.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.view.login.LoginActivity;

import java.util.List;

import io.rong.imkit.RongIM;

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
     * 检测是否第一次打开app
     *
     * @return
     */
    public static boolean isFirstApp(BaseActivity activity) {
        SharedUtils sharedUtils = new SharedUtils(activity);
        String first = sharedUtils.getShared(SpConfig.FIRST_APP,"first");
        if (TextUtils.isEmpty(first)) {
            return false;
        }
        return true;
    }

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
     * 检测是否登记用户信息
     *
     * @return
     */
    public static boolean isFirstUser(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseFile parseFile = (ParseFile) currentUser.get("photo");
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("sex")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("birthday")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("job")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("tag")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("event_tag")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(currentUser.getUsername())){
                return false;
            }
            if (StringUtils.isTrimEmpty(String.valueOf(parseFile.getUrl()))){
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 退出账号到登录页面
     *
     * @return
     */
    public static void getExitLogin() {
        SPUtils.getInstance().clear(true);
        ActivityUtils.startActivity(LoginActivity.class);
        ActivityUtils.finishOtherActivities(LoginActivity.class);
        RongIM.getInstance().logout();
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
