package com.wankrfun.crania.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.AppCompatTextView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: DrawableUtils
 * @Description: 设置drawableIcon
 * @Author: 鹿鸿祥
 * @CreateDate: 1/11/21 9:38 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/11/21 9:38 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("UseCompatLoadingForDrawables")
public class DrawableUtils {

    /**
     * 给textView设置drawableLeft图片
     *
     * @param activity 上下文
     * @param textView 当前控件
     * @param color 颜色
     * @param code 国家简称
     */
    public static void getDrawableRightView(BaseActivity activity , AppCompatTextView textView, int color , String code) {
        Drawable drawable = setNationalFlag(activity,code);
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
        textView.setTextColor(activity.getResources().getColor(color));
    }

    /**
     * 设置各个国家国旗
     *
     * @param activity 上下文
     * @param code 国家简称
     * @return
     */
    public static Drawable setNationalFlag(BaseActivity activity, String code){
        Drawable drawable = null;
        switch (code){
            case "cn":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_china);
                break;
            case "fr":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_france);
                break;
            case "de":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_germany);
                break;
            case "hk":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_hong_kong);
                break;
            case "jp":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_japan);
                break;
            case "kr":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_korea_south);
                break;
            case "pl":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_poland);
                break;
            case "es":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_spain);
                break;
            case "tw":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_taiwan);
                break;
            case "gb":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_united_kingdom);
                break;
            case "us":
                drawable = activity.getResources().getDrawable(R.drawable.svg_icon_united_states_of_america);
                break;
        }
        return drawable;
    }
}
