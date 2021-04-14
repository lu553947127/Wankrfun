package com.wankrfun.crania.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

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
     * 给textView设置drawableRight图片
     * @param context
     * @param attention
     * @param drawableId
     */
    public static void setDrawableRight(Context context, TextView attention, int drawableId) {
        Drawable drawable = context.getResources().getDrawable(drawableId);
        /// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attention.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 给textView设置drawableBottom图片
     * @param activity
     * @param attention
     * @param drawableId
     */
    public static void setDrawableBottom(BaseActivity activity, AppCompatTextView attention, int drawableId) {
        if (drawableId == 0){
            attention.setCompoundDrawables(null, null, null, null);
            return;
        }
        Drawable drawable = activity.getResources().getDrawable(drawableId);
        /// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attention.setCompoundDrawables(null, null, null, drawable);
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

    /**
     * 设置用户详情 tab切换
     *
     * @param activity
     * @param position
     * @param tvAbout
     * @param tvInitiate
     */
    public static void setMineEventsTab(BaseActivity activity, int position, AppCompatTextView tvAbout, AppCompatTextView tvInitiate){
        switch (position){
            case 0:
                DrawableUtils.setDrawableBottom(activity, tvAbout, R.drawable.icon_tab_line);
                DrawableUtils.setDrawableBottom(activity, tvInitiate, 0);
                tvAbout.setTextSize(18);
                tvAbout.setTextColor(activity.getResources().getColor(R.color.white));
                tvInitiate.setTextSize(16);
                tvInitiate.setTextColor(activity.getResources().getColor(R.color.color_494949));
                break;
            case 1:
                DrawableUtils.setDrawableBottom(activity, tvAbout, 0);
                DrawableUtils.setDrawableBottom(activity, tvInitiate, R.drawable.icon_tab_line);
                tvAbout.setTextSize(16);
                tvAbout.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvInitiate.setTextSize(18);
                tvInitiate.setTextColor(activity.getResources().getColor(R.color.white));
                break;
        }
    }

    /**
     * 设置个人中心活动标签tab显示
     *
     * @param activity
     * @param position
     * @param tvInitiate
     * @param tvApply
     * @param tvFav
     */
    public static void setMineEventsTab(BaseActivity activity, int position
            , AppCompatTextView tvAbout, AppCompatTextView tvInitiate, AppCompatTextView tvApply, AppCompatTextView tvFav){
        switch (position){
            case 0:
                DrawableUtils.setDrawableBottom(activity, tvAbout, R.drawable.icon_tab_line);
                DrawableUtils.setDrawableBottom(activity, tvInitiate, 0);
                DrawableUtils.setDrawableBottom(activity, tvApply, 0);
                DrawableUtils.setDrawableBottom(activity, tvFav, 0);
                tvAbout.setTextSize(18);
                tvAbout.setTextColor(activity.getResources().getColor(R.color.white));
                tvInitiate.setTextSize(16);
                tvInitiate.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvApply.setTextSize(16);
                tvApply.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvFav.setTextSize(16);
                tvFav.setTextColor(activity.getResources().getColor(R.color.color_494949));
                break;
            case 1:
                DrawableUtils.setDrawableBottom(activity, tvAbout, 0);
                DrawableUtils.setDrawableBottom(activity, tvInitiate, R.drawable.icon_tab_line);
                DrawableUtils.setDrawableBottom(activity, tvApply, 0);
                DrawableUtils.setDrawableBottom(activity, tvFav, 0);
                tvAbout.setTextSize(16);
                tvAbout.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvInitiate.setTextSize(18);
                tvInitiate.setTextColor(activity.getResources().getColor(R.color.white));
                tvApply.setTextSize(16);
                tvApply.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvFav.setTextSize(16);
                tvFav.setTextColor(activity.getResources().getColor(R.color.color_494949));
                break;
            case 2:
                DrawableUtils.setDrawableBottom(activity, tvAbout, 0);
                DrawableUtils.setDrawableBottom(activity, tvInitiate, 0);
                DrawableUtils.setDrawableBottom(activity, tvApply, R.drawable.icon_tab_line);
                DrawableUtils.setDrawableBottom(activity, tvFav, 0);
                tvAbout.setTextSize(16);
                tvAbout.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvInitiate.setTextSize(16);
                tvInitiate.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvApply.setTextSize(18);
                tvApply.setTextColor(activity.getResources().getColor(R.color.white));
                tvFav.setTextSize(16);
                tvFav.setTextColor(activity.getResources().getColor(R.color.color_494949));
                break;
            case 3:
                DrawableUtils.setDrawableBottom(activity, tvAbout, 0);
                DrawableUtils.setDrawableBottom(activity, tvInitiate, 0);
                DrawableUtils.setDrawableBottom(activity, tvApply, 0);
                DrawableUtils.setDrawableBottom(activity, tvFav, R.drawable.icon_tab_line);
                tvAbout.setTextSize(16);
                tvAbout.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvInitiate.setTextSize(16);
                tvInitiate.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvApply.setTextSize(16);
                tvApply.setTextColor(activity.getResources().getColor(R.color.color_494949));
                tvFav.setTextSize(18);
                tvFav.setTextColor(activity.getResources().getColor(R.color.white));
                break;
        }
    }
}
