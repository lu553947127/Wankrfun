package com.wankrfun.crania.utils;

import android.app.Dialog;
import android.view.Window;

import com.wankrfun.crania.R;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: AnimatorUtils
 * @Description: 动画工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 9:31 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 9:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AnimatorUtils {

    /**
     * 设置弹窗动画
     * @param dialog
     */
    public static void enterCustomAnim(Dialog dialog){
        Window window = dialog.getWindow();
        if (window != null){
            window.setWindowAnimations(R.style.DialogAnimCustom);
        }
    }
}
