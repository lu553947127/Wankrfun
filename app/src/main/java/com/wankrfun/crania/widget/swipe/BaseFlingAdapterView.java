package com.wankrfun.crania.widget.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.widget.swipe
 * @ClassName: BaseFlingAdapterView
 * @Description: 左右侧滑自定义布局
 * @Author: 鹿鸿祥
 * @CreateDate: 1/19/21 9:43 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/19/21 9:43 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseFlingAdapterView extends AdapterView {

    private int heightMeasureSpec;
    private int widthMeasureSpec;

    public BaseFlingAdapterView(Context context) {
        super(context);
    }

    public BaseFlingAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFlingAdapterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setSelection(int i) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;
    }

    public int getWidthMeasureSpec() {
        return widthMeasureSpec;
    }

    public int getHeightMeasureSpec() {
        return heightMeasureSpec;
    }
}
