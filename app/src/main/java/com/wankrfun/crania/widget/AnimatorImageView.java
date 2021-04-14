package com.wankrfun.crania.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.blankj.utilcode.util.LogUtils;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.widget
 * @ClassName: AnimatorImageView
 * @Description: 帧动画打造动态ImageView
 * @Author: 鹿鸿祥
 * @CreateDate: 4/10/21 3:14 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/10/21 3:14 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AnimatorImageView extends AppCompatImageView {

    public AnimatorImageView(Context context) {
        super(context);
    }

    public AnimatorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getVisibility() == VISIBLE) {
            animator(false);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator(false);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (VISIBLE == visibility) {
            animator(false);
        } else {
            animator(false);
        }
    }

    /**
     * 启动或停止动画
     * @param start
     * true为启动动画； false为停止动画
     */
    public void animator(boolean start) {
        try {
            AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable();
            if (animationDrawable == null) {
                return;
            }
            animationDrawable.stop();
            if (start) {
                animationDrawable.start();
                animationDrawable.setOneShot(true);
            }
        } catch (Exception e) {
            LogUtils.e("AnimatorImageView", e.getMessage());
        }
    }
}
