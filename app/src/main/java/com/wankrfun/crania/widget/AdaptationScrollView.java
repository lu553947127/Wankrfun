package com.wankrfun.crania.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.widget
 * @ClassName: AdaptationScrollView
 * @Description: 重写滑动布局 适配api23以上滑动监听滑动的距离
 * @Author: 鹿鸿祥
 * @CreateDate: 1/15/21 1:19 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/15/21 1:19 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AdaptationScrollView extends NestedScrollView {

    private OnScrollListener listener;

    public AdaptationScrollView(@NonNull Context context) {
        super(context);
    }

    public AdaptationScrollView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptationScrollView(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }

    public interface OnScrollListener{
        void onScroll(float scrollY);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //x0.65 使位移效果更加平滑 解决手指按住停留时抖动问题
        if(listener != null)listener.onScroll(getScrollY() * 0.65f);
    }
}
