package com.wankrfun.crania.widget.swipe;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.widget.swipe
 * @ClassName: OnSwipeListener
 * @Description: 左滑右滑view 状态回调接口
 * @Author: 鹿鸿祥
 * @CreateDate: 2/4/21 4:51 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/4/21 4:51 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface OnSwipeListener<T> {

    /**
     * 卡片还在滑动时回调
     *
     * @param viewHolder 该滑动卡片的viewHolder
     * @param ratio      滑动进度的比例
     * @param direction  卡片滑动的方向，CardConfig.SWIPING_LEFT 为向左滑，CardConfig.SWIPING_RIGHT 为向右滑，
     *                   CardConfig.SWIPING_NONE 为不偏左也不偏右
     */
    void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction);

    /**
     * 卡片完全滑出时回调
     *
     * @param viewHolder 该滑出卡片的viewHolder
     * @param t          该滑出卡片的数据
     * @param direction  卡片滑出的方向，CardConfig.SWIPED_LEFT 为左边滑出；CardConfig.SWIPED_RIGHT 为右边滑出
     */
    void onSwiped(RecyclerView.ViewHolder viewHolder, T t, int direction);

    /**
     * 所有的卡片全部滑出时回调
     */
    void onSwipedClear();

}
