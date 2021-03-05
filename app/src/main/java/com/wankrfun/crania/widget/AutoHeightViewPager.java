package com.wankrfun.crania.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.LogUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.widget
 * @ClassName: AutoHeightViewPager
 * @Description: NestedScrollView嵌套viewpager不显示内容解决方案
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 11:26 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 11:26 AM
 * @UpdateRemark: 先前在低于Android 9 以下版本开发的时候 在NestedScrollView 控件属性中加入android:fillViewport=“true” 充满布局属性还起作用，之后就无效了。
 * 给viewpager 重写一遍测量，重新给测量高度；在每次测完后将测量得到的高度保存下来，下次viewpager 再切换回来就可以直接设置高度了，就没有必要每次都重新计算一遍高度
 * @Version: 1.0
 */
public class AutoHeightViewPager extends ViewPager {

    private int mHeight = 0;
    private int mCurPosition = 0;//已经获取到的高度下标
    private int mPosition = 0;//当前显示下标
    private HashMap<Integer, Integer> mChildrenViews = new LinkedHashMap<Integer, Integer>();//按下标存储View历史高度
    private HashMap<Integer, Boolean> indexList = new LinkedHashMap<Integer, Boolean>();//记录页面是否存储了高度

    /**
     * 做自适应高度，必须先进行初始化标记
     */
    public void initIndexList(int size) {
        mHeight = 0;
        mCurPosition = 0;
        mPosition = 0;
        for (int i = 0; i < size; i++) {//初始化高度存储状态
            indexList.put(i, false);
        }
    }

    public AutoHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        if (indexList.size() > 0) {
            if (indexList.get(mPosition)) {
                height = mChildrenViews.get(mPosition);
            } else {
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    int h = child.getMeasuredHeight();
                    if (h > height) {
                        height = h;
                    }
                }
                mHeight = height;
            }
        }
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//        LogUtils.d("AutoHeightViewPager", "height=" + height + "  pos=" + mCurPosition);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在viewpager 切换的时候进行更新高度
     */
    public void updateHeight(int current) {
        this.mPosition = current;
        if (indexList.size() > 0) {
//            LogUtils.d("AutoHeightViewPager", "updateHeight 1 current=" + current + "   mCurPosition=" + mCurPosition);
            saveIndexData();
            if (indexList.get(current)) {
                int height = 0;
                if (mChildrenViews.get(current) != null) {
                    height = mChildrenViews.get(current);
                }
//                LogUtils.d("AutoHeightViewPager", "updateHeight 2=" + height + "   pos=" + current);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
                } else {
                    layoutParams.height = height;
                }
                setLayoutParams(layoutParams);
            }
            this.mCurPosition = current;
        }
    }

    /**
     * 保存已经测绘好的高度
     */
    private void saveIndexData() {
        if (!indexList.get(mCurPosition)) {//没保存高度时，保存
            indexList.put(mCurPosition, true);
            mChildrenViews.put(mCurPosition, mHeight);
//            LogUtils.d("AutoHeightViewPager", "saveIndexData 1=" + mHeight + "   mCurPosition=" + mCurPosition);
        }
    }
}
