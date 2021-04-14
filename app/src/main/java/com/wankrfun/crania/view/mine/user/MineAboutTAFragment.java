package com.wankrfun.crania.view.mine.user;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineAboutEventsAdapter;
import com.wankrfun.crania.adapter.MineAboutLifeAdapter;
import com.wankrfun.crania.adapter.MineAboutWishAdapter;
import com.wankrfun.crania.base.BaseLazyFragment;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.view.events.EventsDetailActivity;
import com.wankrfun.crania.viewmodel.MineCardViewModel;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import butterknife.BindView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.user
 * @ClassName: MineAboutTAFragment
 * @Description: 关于TA fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 4/10/21 3:50 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/10/21 3:50 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineAboutTAFragment extends BaseLazyFragment {
    @BindView(R.id.ll_wish)
    LinearLayout llWish;
    @BindView(R.id.tv_wish)
    AppCompatTextView tvWish;
    @BindView(R.id.banner_wish)
    Banner bannerWish;
    @BindView(R.id.indicator_wish)
    CircleIndicator indicatorWish;
    @BindView(R.id.ll_life)
    LinearLayout llLife;
    @BindView(R.id.tv_life)
    AppCompatTextView tvLife;
    @BindView(R.id.banner_life)
    Banner bannerLife;
    @BindView(R.id.indicator_life)
    CircleIndicator indicatorLife;
    @BindView(R.id.ll_events)
    LinearLayout llEvents;
    @BindView(R.id.banner_events)
    Banner bannerEvents;
    @BindView(R.id.indicator_events)
    CircleIndicator indicatorEvents;
    @BindView(R.id.rl_card_life)
    RelativeLayout relativeLayoutLife;
    @BindView(R.id.rl_card_events)
    RelativeLayout relativeLayoutEvents;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @BindView(R.id.tv_empty)
    AppCompatTextView tvEmpty;
    private MineCardViewModel mineCardViewModel;
    private String userId;

    public MineAboutTAFragment(String id){
        this.userId = id;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine_about_ta;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        tvWish.setText("TA的心愿");
        tvLife.setText("TA的生活");
        rlEmpty.setVisibility(View.VISIBLE);
        tvEmpty.setText("TA尚未创建任何资料卡片");

        mineCardViewModel = mActivity.getViewModel(MineCardViewModel.class);

        //获取心愿列表数据返回成功结果
        mineCardViewModel.wishListLiveData.observe(mActivity, wishListBean -> {
            if (wishListBean.getData().getList().size() != 0){
                llWish.setVisibility(View.VISIBLE);
                rlEmpty.setVisibility(View.GONE);
                bannerWish.addBannerLifecycleObserver(this)//添加生命周期观察者
                        .setAdapter(new MineAboutWishAdapter(mActivity, wishListBean.getData().getList(), ""))//添加数据
                        .isAutoLoop(false)
                        .setIndicator(indicatorWish, false)
                        .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                        .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                        .setOnBannerListener((data, position) -> {

                        }).start();
            }else {
                llWish.setVisibility(View.GONE);
            }
        });

        //获取生活瞬间列表数据返回结果
        mineCardViewModel.lifeListLiveData.observe(mActivity, mineLifeListBean -> {
            if (mineLifeListBean.getData().getList().size() != 0){
                llLife.setVisibility(View.VISIBLE);
                rlEmpty.setVisibility(View.GONE);
                bannerLife.addBannerLifecycleObserver(this)//添加生命周期观察者
                        .setAdapter(new MineAboutLifeAdapter(mActivity, mineLifeListBean.getData().getList()))//添加数据
                        .isAutoLoop(false)
                        .setIndicator(indicatorLife, false)
                        .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                        .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                        .setOnBannerListener((data, position) -> {
                            PictureEnlargeUtils.getPictureEnlargeList(mActivity, mineLifeListBean.getData().getList().get(position).getImages(), 0);
                        }).start();
            }else {
                llLife.setVisibility(View.GONE);
            }
        });

        //获取活动瞬间数据返回结果
        mineCardViewModel.eventsListLiveData.observe(mActivity, mineEventsListBean -> {
            if (mineEventsListBean.getData().getList().size() != 0){
                llEvents.setVisibility(View.VISIBLE);
                rlEmpty.setVisibility(View.GONE);
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
            }else {
                llEvents.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataFromService() {
        mineCardViewModel.getWishList(userId);
        mineCardViewModel.getLifeList(userId);
        mineCardViewModel.getEventsList(userId);
    }
}
