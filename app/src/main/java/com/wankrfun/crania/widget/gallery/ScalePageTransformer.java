package com.wankrfun.crania.widget.gallery;

import android.os.Build;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.widget.gallery
 * @ClassName: ScalePageTransformer
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/31/21 4:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/31/21 4:11 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ScalePageTransformer implements ViewPager.PageTransformer {

    public static final float MAX_SCALE = 1f;
    public static final float MIN_SCALE = 0.6f;
    /**核心就是实现transformPage(View page, float position)这个方法**/
    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;
        //一个公式
        float scaleValue = MIN_SCALE + tempScale * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
    }
}
