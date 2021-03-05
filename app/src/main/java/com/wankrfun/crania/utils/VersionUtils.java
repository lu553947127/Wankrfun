package com.wankrfun.crania.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: VersionUtils
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/24/21 3:27 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/24/21 3:27 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class VersionUtils {

    /**
     * 获取版本号
     *
     * @param mContext
     * @return
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
