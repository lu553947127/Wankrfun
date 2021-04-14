package com.wankrfun.crania.view.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineAboutEventsAdapter;
import com.wankrfun.crania.adapter.MineAboutLifeAdapter;
import com.wankrfun.crania.adapter.MineAboutWishAdapter;
import com.wankrfun.crania.base.BaseLazyFragment;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.WishListBean;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.view.events.EventsDetailActivity;
import com.wankrfun.crania.view.mine.about.MineEventsActivity;
import com.wankrfun.crania.view.mine.about.MineLifeActivity;
import com.wankrfun.crania.view.mine.about.MineMoreCardActivity;
import com.wankrfun.crania.view.mine.about.MineWishActivity;
import com.wankrfun.crania.viewmodel.MineCardViewModel;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

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
    @BindView(R.id.rl_card_life)
    RelativeLayout relativeLayoutLife;
    @BindView(R.id.rl_card_events)
    RelativeLayout relativeLayoutEvents;
    private MineCardViewModel mineCardViewModel;

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine_about;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        mineCardViewModel = mActivity.getViewModel(MineCardViewModel.class);

        //获取心愿列表数据返回成功结果
        mineCardViewModel.wishListLiveData.observe(mActivity, wishListBean -> {
            List<WishListBean.DataBean.ListBean> wishList = wishListBean.getData().getList();
            wishList.add(new WishListBean.DataBean.ListBean("","更多"));
            bannerWish.addBannerLifecycleObserver(this)//添加生命周期观察者
                    .setAdapter(new MineAboutWishAdapter(mActivity, wishList, "mine"))//添加数据
                    .isAutoLoop(false)
                    .setIndicator(indicatorWish, false)
                    .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                    .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                    .setOnBannerListener((data, position) -> {
                        ActivityUtils.startActivity(MineWishActivity.class);
                    }).start();
        });

        //获取生活瞬间列表数据返回结果
        mineCardViewModel.lifeListLiveData.observe(mActivity, mineLifeListBean -> {
            if (mineLifeListBean.getData().getList().size() != 0){
                relativeLayoutLife.setVisibility(View.GONE);
                bannerLife.setVisibility(View.VISIBLE);
                indicatorLife.setVisibility(View.VISIBLE);
            }else {
                relativeLayoutLife.setVisibility(View.VISIBLE);
                bannerLife.setVisibility(View.GONE);
                indicatorLife.setVisibility(View.GONE);
            }
            bannerLife.addBannerLifecycleObserver(this)//添加生命周期观察者
                    .setAdapter(new MineAboutLifeAdapter(mActivity, mineLifeListBean.getData().getList()))//添加数据
                    .isAutoLoop(false)
                    .setIndicator(indicatorLife, false)
                    .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                    .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                    .setOnBannerListener((data, position) -> {
                        PictureEnlargeUtils.getPictureEnlargeList(mActivity, mineLifeListBean.getData().getList().get(position).getImages(), 0);
                    }).start();
        });

        //获取活动瞬间数据返回结果
        mineCardViewModel.eventsListLiveData.observe(mActivity, mineEventsListBean -> {
            if (mineEventsListBean.getData().getList().size() != 0){
                relativeLayoutEvents.setVisibility(View.GONE);
                bannerEvents.setVisibility(View.VISIBLE);
                indicatorEvents.setVisibility(View.VISIBLE);
            }else {
                relativeLayoutEvents.setVisibility(View.VISIBLE);
                bannerEvents.setVisibility(View.GONE);
                indicatorEvents.setVisibility(View.GONE);
            }
            bannerEvents.addBannerLifecycleObserver(this)//添加生命周期观察者
                    .setAdapter(new MineAboutEventsAdapter(mActivity, mineEventsListBean.getData().getList()))//添加数据
                    .isAutoLoop(false)
                    .setIndicator(indicatorEvents, false)
                    .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                    .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                    .setOnBannerListener((data, position) -> {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", mineEventsListBean.getData().getList().get(position).getEventId());
                        bundle.putString("creator", mineEventsListBean.getData().getList().get(position).getCreatorId());
                        ActivityUtils.startActivity(bundle, EventsDetailActivity.class);
                    }).start();
        });
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataFromService() {
        mineCardViewModel.getWishList(SPUtils.getInstance().getString(SpConfig.USER_ID));
        mineCardViewModel.getLifeList(SPUtils.getInstance().getString(SpConfig.USER_ID));
        mineCardViewModel.getEventsList(SPUtils.getInstance().getString(SpConfig.USER_ID));
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
