package com.wankrfun.crania.view.mine;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineAboutEventsAdapter;
import com.wankrfun.crania.adapter.MineAboutLifeAdapter;
import com.wankrfun.crania.adapter.MineAboutWishAdapter;
import com.wankrfun.crania.base.BaseLazyFragment;
import com.wankrfun.crania.bean.BaseBean;
import com.wankrfun.crania.view.mine.about.MineEventsActivity;
import com.wankrfun.crania.view.mine.about.MineLifeActivity;
import com.wankrfun.crania.view.mine.about.MineMoreCardActivity;
import com.wankrfun.crania.view.mine.about.MineWishActivity;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: MineAboutFragment
 * @Description: 关于我fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 3/20/21 10:02 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/20/21 10:02 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineAboutFragment extends BaseLazyFragment {
    @BindView(R.id.banner_wish)
    Banner bannerWish;
    @BindView(R.id.indicator_wish)
    CircleIndicator indicatorWish;
    @BindView(R.id.banner_life)
    Banner bannerLife;
    @BindView(R.id.indicator_life)
    CircleIndicator indicatorLife;
    @BindView(R.id.banner_events)
    Banner bannerEvents;
    @BindView(R.id.indicator_events)
    CircleIndicator indicatorEvents;
    private List<BaseBean> list = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine_about;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        list.add(new BaseBean("\"想去啊上的卡上看到哈\"","E3B492"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","92C1E3"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","92E3B2"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","E392AF"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","E3DB92"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","2AA7ED"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","BB92E3"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","E39292"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","929FE3"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","E392E0"));
        list.add(new BaseBean("想去啊客户搭建可视电话卡圣诞贺卡上的卡上的卡上看到哈","E3C992"));
        list.add(new BaseBean("更多","E3C992"));

        bannerWish.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(new MineAboutWishAdapter(mActivity, list))//添加数据
                .isAutoLoop(false)
                .setIndicator(indicatorWish, false)
                .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                .setOnBannerListener((data, position) -> {
                    if (list.get(position).getName().equals("更多")){
                        ActivityUtils.startActivity(MineWishActivity.class);
                    }else {
                        ToastUtils.showShort(list.get(position).getName());
                    }
                }).start();


        bannerLife.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(new MineAboutLifeAdapter(mActivity, list))//添加数据
                .isAutoLoop(false)
                .setIndicator(indicatorLife, false)
                .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                .setOnBannerListener((data, position) -> {

                }).start();

        bannerEvents.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(new MineAboutEventsAdapter(mActivity, list))//添加数据
                .isAutoLoop(false)
                .setIndicator(indicatorEvents, false)
                .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                .setOnBannerListener((data, position) -> {

                }).start();
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataFromService() {

    }

    @OnClick({R.id.rl_card_life, R.id.rl_card_events, R.id.rl_card_idea, R.id.rl_card_more})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_card_life://我的生活
                ActivityUtils.startActivity(MineLifeActivity.class);
                break;
            case R.id.rl_card_events://活动瞬间
                ActivityUtils.startActivity(MineEventsActivity.class);
                break;
            case R.id.rl_card_idea://我的想法
                ToastUtils.showShort("敬请期待");
                break;
            case R.id.rl_card_more://更多卡片
                ActivityUtils.startActivity(MineMoreCardActivity.class);
                break;
        }
    }
}
