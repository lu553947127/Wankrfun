package com.wankrfun.crania.widget.gallery;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.widget.gallery
 * @ClassName: SpeedScroller
 * @Description: 左右滑动变大动画效果viewpager
 * @Author: 鹿鸿祥
 * @CreateDate: 3/31/21 4:41 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/31/21 4:41 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SpeedScroller extends Scroller {
    private int mDuration = 1500;

    public SpeedScroller(Context context) {
        super(context);
    }

    public SpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setmDuration(int time) {
        mDuration = time;
    }

    public int getmDuration() {
        return mDuration;
    }
}
