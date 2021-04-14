package com.wankrfun.crania.widget;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.wankrfun.crania.app.MyApplication;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.widget
 * @ClassName: OnDoubleClickListener
 * @Description: 双击事件的监听
 * @Author: 鹿鸿祥
 * @CreateDate: 4/10/21 11:19 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/10/21 11:19 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("ClickableViewAccessibility")
public class OnDoubleClickListener implements View.OnTouchListener {

    //双击间四百毫秒延时
    private static final int timeout = 400;
    //记录连续点击次数
    private int clickCount = 0;

    private final OnDoubleClickCallBack onDoubleClickCallBack;

    public interface OnDoubleClickCallBack {
        void oneClick();//点击一次的回调
        void doubleClick();//连续点击两次的回调
    }

    public OnDoubleClickListener(OnDoubleClickCallBack onDoubleClickCallBack) {
        this.onDoubleClickCallBack = onDoubleClickCallBack;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clickCount++;
            //延时timeout后执行run方法中的代码
            MyApplication.getMainThreadHandler().postDelayed(() -> {
                if (clickCount == 1) {
                    onDoubleClickCallBack.oneClick();
                } else if (clickCount == 2) {
                    onDoubleClickCallBack.doubleClick();
                }
                MyApplication.getMainThreadHandler().removeCallbacksAndMessages(null);
                //清空handler延时，并防内存泄漏
                //计数清零
                clickCount = 0;
            }, timeout);
        }
        //让点击事件继续传播，方便再给View添加其他事件监听
        return true;
    }
}
