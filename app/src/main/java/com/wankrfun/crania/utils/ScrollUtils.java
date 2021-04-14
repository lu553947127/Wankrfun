package com.wankrfun.crania.utils;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.ViewPagerAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.widget.AdaptationScrollView;
import com.wankrfun.crania.widget.AutoHeightViewPager;

import java.util.Objects;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: ScrollUtils
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/8/21 9:39 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/8/21 9:39 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ScrollUtils {

    /**
     * NestedScrollView上滑显示 下滑隐藏
     *
     * @param scrollView
     * @param view
     */
    public static void OnScrollChangeListener(NestedScrollView scrollView, View view) {
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private boolean isShow = true;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //上滑 并且 正在显示底部栏
                if (scrollY - oldScrollY > 0 && isShow) {
                    isShow = false;
                    //将Y属性变为底部栏高度  (相当于隐藏了)
                    AnimatorUtils.hideFABAnimation(view);
                } else if (scrollY - oldScrollY < 0 && !isShow) {
                    isShow = true;
                    AnimatorUtils.showFABAnimation(view);
                }
            }
        });
    }

    /**
     * 个人中心tab滑动折叠动画显示
     *
     * @param activity
     * @param scrollView
     * @param toolbar
     * @param view
     */
    public static int sColor;//顶部状态栏和颜色
    public static float sAlpha;//顶部状态栏透明度
    public static void getTooBarOnScrollChangeListener(BaseActivity activity, NestedScrollView scrollView, RelativeLayout toolbar, View view){
        //滑动布局滑动监听
        scrollView.setOnScrollChangeListener(new AdaptationScrollView.OnScrollChangeListener() {
            private int mScrollY_2 = 0;
            private int lastScrollY = 0;
            private int h = DensityUtil.dp2px(70);
            private boolean isShow = true;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY_2 = scrollY > h ? h : scrollY;
                    sAlpha = 1f * mScrollY_2 / h;
                    sColor = ((255 * mScrollY_2 / h) << 24) | ContextCompat.getColor(Objects.requireNonNull(activity), R.color.color_161616) & 0x00ffffff;
                    toolbar.setAlpha(sAlpha);
                    //设置折叠标题背景颜色
                    toolbar.setBackgroundColor(sColor);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                }
                lastScrollY = scrollY;

                //上滑 并且 正在显示底部栏
                if (scrollY - oldScrollY > 0 && isShow) {
                    isShow = false;
                    //将Y属性变为底部栏高度  (相当于隐藏了)
                    AnimatorUtils.hideFABAnimation(view);
                } else if (scrollY - oldScrollY < 0 && !isShow) {
                    isShow = true;
                    AnimatorUtils.showFABAnimation(view);
                }
            }
        });
    }

    /**
     * viewPager滑动布局显示
     *
     * @param activity
     * @param viewPager
     * @param fragments
     */
    public static void getViewPagerAddOnPageChangeListener(BaseActivity activity, AutoHeightViewPager viewPager, Fragment[] fragments
            ,AppCompatTextView tvAbout, AppCompatTextView tvInitiate){
        viewPager.setAdapter(new ViewPagerAdapter(activity.getSupportFragmentManager(), fragments, null));
        //初始化记录是否保存高度的下标
        //注：在重新刷新加载页面的时候，需要对下标进行重新初始化
        viewPager.initIndexList(fragments.length);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //在每次切换的时候更新高度
                viewPager.updateHeight(position);
                DrawableUtils.setMineEventsTab(activity, position, tvAbout, tvInitiate);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * viewPager滑动布局显示
     *
     * @param activity
     * @param fragment
     * @param viewPager
     * @param fragments
     * @param tvInitiate
     * @param tvApply
     * @param tvFav
     */
    public static void getViewPagerAddOnPageChangeListener(BaseActivity activity, FragmentManager fragment, AutoHeightViewPager viewPager, Fragment[] fragments
            ,AppCompatTextView tvAbout, AppCompatTextView tvInitiate, AppCompatTextView tvApply, AppCompatTextView tvFav){
        viewPager.setAdapter(new ViewPagerAdapter(fragment, fragments, null));
        //初始化记录是否保存高度的下标
        //注：在重新刷新加载页面的时候，需要对下标进行重新初始化
        viewPager.initIndexList(fragments.length);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //在每次切换的时候更新高度
                viewPager.updateHeight(position);
                DrawableUtils.setMineEventsTab(activity, position, tvAbout, tvInitiate, tvApply, tvFav);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
