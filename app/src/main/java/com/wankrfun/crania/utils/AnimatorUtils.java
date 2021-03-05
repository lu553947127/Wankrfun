package com.wankrfun.crania.utils;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.view.View;
import android.view.ViewConfiguration;
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

    /**
     * 列表滑动按钮隐藏动画
     */
    private static int distance;
    private static boolean visible = true;
    public static void listScrollAnimation(View view, float dy){
        if (distance < -ViewConfiguration.getTouchSlop() && !visible) {
            showFABAnimation(view);
            distance = 0;
            visible = true;
        } else if (distance > ViewConfiguration.getTouchSlop() && visible) {
            hideFABAnimation(view);
            distance = 0;
            visible = false;
        }
        if ((dy > 0 && visible) || (dy < 0 && !visible))//向下滑并且可见  或者  向上滑并且不可见
            distance += dy;
    }

    /**
     * 显示fab动画
     *
     * @param view
     */
    public static void showFABAnimation(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(400).start();
    }

    /**
     * 隐藏fab的动画
     *
     * @param view
     */
    public static void hideFABAnimation(View view){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 0f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 0f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(400).start();
    }
}
