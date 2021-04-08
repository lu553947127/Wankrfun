package com.wankrfun.crania.utils;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.view.mine.set.WebViewActivity;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: TextViewUtils
 * @Description: 设置TextView中间字体颜色与点击事件
 * @Author: 鹿鸿祥
 * @CreateDate: 3/16/21 4:31 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/16/21 4:31 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TextViewUtils {

    /**
     * 设置TextView中间字体颜色与点击事见
     * start   开始文字的位置 坐标从 0开始
     * end     改变结束的位置 ，并不包括这个位置。
     * 使用BackgroundColorSpan设置背景颜色。
     *
     * @param activity
     * @param textView
     */
    public static void setTextInfo(BaseActivity activity, TextView textView, String content, int userStart, int userEnd, int privacyStart, int privacyEnd) {
        //初始化可以更改内容和标记的文本类
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        //添加需要改变的文本
        spannableStringBuilder.append(content);
        //去掉点击后的背景色
        textView.setHighlightColor(activity.getResources().getColor(android.R.color.transparent));

        //设置用户协议点击
        ClickableSpan firstClickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);//取消点击事见字体的背景颜色
                textPaint.setColor(activity.getResources().getColor(R.color.color_EBBB7D));
            }
            //变色字体点击监听设置
            @Override
            public void onClick(@NonNull View widget) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "user");
                ActivityUtils.startActivity(bundle, WebViewActivity.class);
            }
        };

        //设置隐私协议点击
        ClickableSpan secondClickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);//取消点击事见字体的背景颜色
                textPaint.setColor(activity.getResources().getColor(R.color.color_EBBB7D));
            }
            //变色字体点击监听设置
            @Override
            public void onClick(@NonNull View widget) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "privacy");
                ActivityUtils.startActivity(bundle, WebViewActivity.class);
            }
        };
        spannableStringBuilder.setSpan(firstClickableSpan, userStart, userEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(secondClickableSpan,privacyStart,privacyEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //一定要记得设置，不然点击不生效
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        //最好设置文字
        textView.setText(spannableStringBuilder);
    }

    /**
     * 按键延时工具,用于防止按键连点
     */
    private static final int MIN_CLICK_DELAY_TIME = 3000;
    private static long lastClickTime;
    public static boolean isFastClick(){
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * 代码中设置控件的margin
     *
     * @param view
     * @param left
     * @param right
     * @param top
     * @param bottom
     */
    public static void setViewLayoutParams(View view, int left ,int right ,int top, int bottom){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParams.setMargins(left, top, right, bottom);
        view.setLayoutParams(layoutParams);
    }
}
