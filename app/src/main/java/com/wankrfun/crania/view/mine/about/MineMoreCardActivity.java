package com.wankrfun.crania.view.mine.about;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lwj.widget.viewpagerindicator.ViewPagerIndicator;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.ViewPageAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.widget.gallery.ClipViewPager;
import com.wankrfun.crania.widget.gallery.ScalePageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineMoreCardActivity
 * @Description: 创建更多卡片页面
 * @Author: 鹿鸿祥
 * @CreateDate: 3/31/21 10:21 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/31/21 10:21 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineMoreCardActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.view_pager)
    ClipViewPager viewPager;
    @BindView(R.id.indicator_line)
    ViewPagerIndicator viewPagerIndicator;
    private List<Integer> mList = new ArrayList<>();
    private int pos;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_more_card;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        mList.add(R.drawable.icon_more_card);
        mList.add(R.drawable.icon_more_card1);
        mList.add(R.drawable.icon_more_card2);
        mList.add(R.drawable.icon_more_card3);

        viewPager.setSpeedScroller(300);
        findViewById(R.id.rl).setOnTouchListener((v, event) -> viewPager.dispatchTouchEvent(event));
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(mList);
        viewPager.setAdapter(viewPageAdapter);
        viewPager.setPageTransformer(true, new ScalePageTransformer());
        viewPager.setOffscreenPageLimit(mList.size());
        viewPagerIndicator.setViewPager(viewPager);
//        viewPageAdapter.setIsSelect(mList.get(0));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pos = position;
                viewPageAdapter.setIsSelect(mList.get(position));
                switch (position){
                    case 0:
                        tvTitle.setText("写下想要完成的心愿");
                        break;
                    case 1:
                        tvTitle.setText("记录活动的精彩瞬间");
                        break;
                    case 2:
                        tvTitle.setText("分享你的生活状态");
                        break;
                    case 3:
                        tvTitle.setText("说说你的想法");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_add})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_add:
                switch (pos){
                    case 0:
                        ActivityUtils.startActivity(MineWishActivity.class);
                        break;
                    case 1:
                        ActivityUtils.startActivity(MineEventsActivity.class);
                        break;
                    case 2:
                        ActivityUtils.startActivity(MineLifeActivity.class);
                        break;
                    case 3:
                        finish();
                        ToastUtils.showShort("敬请期待");
                        break;
                }
                break;
        }
    }
}
