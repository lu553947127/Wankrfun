package com.wankrfun.crania.view.meet;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.humrousz.sequence.AnimationImageView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineAboutEventsAdapter;
import com.wankrfun.crania.adapter.MineAboutLifeAdapter;
import com.wankrfun.crania.adapter.MineAboutWishAdapter;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseLazyFragment;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.BaseBean;
import com.wankrfun.crania.bean.MeetListBean;
import com.wankrfun.crania.dialog.AnimationDialog;
import com.wankrfun.crania.event.LocationEvent;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.LocationUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.RefreshUtils;
import com.wankrfun.crania.utils.ScrollUtils;
import com.wankrfun.crania.utils.TextViewUtils;
import com.wankrfun.crania.view.mine.about.MineWishActivity;
import com.wankrfun.crania.view.mine.user.UserInfoActivity;
import com.wankrfun.crania.viewmodel.MeetViewModel;
import com.wankrfun.crania.widget.AdaptationScrollView;
import com.wankrfun.crania.widget.CornerImageView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.meet
 * @ClassName: MeetHomeFragment
 * @Description: 遇见fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 4/7/21 1:19 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/7/21 1:19 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetHomeFragment extends BaseLazyFragment {
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @BindView(R.id.scroll)
    AdaptationScrollView adaptationScrollView;
    @BindView(R.id.iv_image)
    CornerImageView cornerImageView;
    @BindView(R.id.rl)
    RelativeLayout relativeLayout;
    @BindView(R.id.iv_like_animation)
    AnimationImageView iv_like_animation;
    @BindView(R.id.tv_name)
    AppCompatTextView tv_name;
    @BindView(R.id.iv_sex)
    AppCompatImageView iv_sex;
    @BindView(R.id.tv_age)
    AppCompatTextView tv_age;
    @BindView(R.id.tv_work)
    AppCompatTextView tv_work;
    @BindView(R.id.tv_constellation)
    AppCompatTextView tv_constellation;
    @BindView(R.id.tv_city)
    AppCompatTextView tv_city;
    @BindView(R.id.iv_icon)
    AppCompatImageView iv_icon;
    @BindView(R.id.tv_title)
    AppCompatTextView tv_title;
    @BindView(R.id.iv_icon2)
    AppCompatImageView iv_icon2;
    @BindView(R.id.tv_title2)
    AppCompatTextView tv_title2;
    @BindView(R.id.ll_tag)
    LinearLayout ll_tag;
    @BindView(R.id.ll_event_tag)
    LinearLayout ll_event_tag;
    @BindView(R.id.ll)
    LinearLayout linearLayout;
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
    private AppCompatTextView tvNum;
    private LottieAnimationView lottieAnimationView;
    private MeetViewModel meetViewModel;
    private List<MeetListBean.DataBean.ListBean> dataList = new ArrayList<>();
    private List<BaseBean> list = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.fragment_meet_home;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        tvNum = getParentFragment().getView().findViewById(R.id.tv_num);
        lottieAnimationView = getParentFragment().getView().findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setAnimation("meet_success.json");

        //滑动监听让添加按钮动画隐藏和显示
        ScrollUtils.OnScrollChangeListener(adaptationScrollView, linearLayout);

        meetViewModel = mActivity.getViewModel(MeetViewModel.class);

        //获取遇见卡片列表返回结果
        meetViewModel.meetListLiveData.observe(this, meetListBean -> {
            mActivity.hideLoading();

            adaptationScrollView.setVisibility(View.VISIBLE);

            tvNum.setText(String.valueOf(meetListBean.getData().getAllowedToday()));

            if (meetListBean.getData().getAllowedToday() >= 10){
                TextViewUtils.setViewLayoutParams(tvNum, 24, 0, 0, 0);
            }else {
                TextViewUtils.setViewLayoutParams(tvNum, 35, 0, 0, 0);
            }

            if (meetListBean.getData().getList().size() != 0){
                rlEmpty.setVisibility(View.GONE);

                dataList = meetListBean.getData().getList();

                ImageLoader.load(mActivity, new ImageConfig.Builder()
                        .url(meetListBean.getData().getList().get(0).getPhoto())
                        .placeholder(R.drawable.icon_empty_meet)
                        .errorPic(R.drawable.icon_empty_meet)
                        .imageView(cornerImageView)
                        .build());
                relativeLayout.getBackground().setAlpha(170);
                tv_name.setText(meetListBean.getData().getList().get(0).getName());
                iv_sex.setImageResource(meetListBean.getData().getList().get(0).getSex().endsWith("female") ? R.drawable.icon_events_female : R.drawable.icon_sex_male);
                tv_age.setText(String.valueOf(NumberUtils.getAge(meetListBean.getData().getList().get(0).getBirthday())));
                if (!TextUtils.isEmpty(meetListBean.getData().getList().get(0).getJob())){
                    switch (meetListBean.getData().getList().get(0).getJob()){
                        case "s:":
                            tv_work.setText("学生");
                            break;
                        case "j:":
                            tv_work.setText("工作");
                            break;
                        case "c:":
                            tv_work.setText("自由职业者");
                            break;
                    }
                }else {
                    tv_work.setText("自由职业者");
                }
                if (!TextUtils.isEmpty(meetListBean.getData().getList().get(0).getBirthday())){
                    tv_constellation.setText(NumberUtils.date2Constellation(meetListBean.getData().getList().get(0).getBirthday()));
                }else {
                    tv_constellation.setText("暂无");
                }

                tv_city.setText(meetListBean.getData().getList().get(0).getAddress());

                ll_tag.getBackground().setAlpha(60);
                ll_event_tag.getBackground().setAlpha(60);
                if (meetListBean.getData().getList().get(0).getTag() != null && meetListBean.getData().getList().get(0).getTag().size() != 0){
                    tv_title.setText(meetListBean.getData().getList().get(0).getTag().get(0));
                    iv_icon.setImageResource(RefreshUtils.setTagIcon(meetListBean.getData().getList().get(0).getTag().get(0)));
                }

                if (meetListBean.getData().getList().get(0).getEvent_tag() != null && meetListBean.getData().getList().get(0).getEvent_tag().size() != 0){
                    tv_title2.setText(meetListBean.getData().getList().get(0).getEvent_tag().get(0));
                    iv_icon2.setImageResource(RefreshUtils.setEventTagIcon(meetListBean.getData().getList().get(0).getEvent_tag().get(0)));
                }
            }else {
                rlEmpty.setVisibility(View.VISIBLE);
            }
        });

        //手势操作: 喜欢或不喜欢成功返回结果
        meetViewModel.meetUserCardLiveData.observe(this, eventsCreateBean -> {
            iv_like_animation.setVisibility(View.GONE);

            meetViewModel.latitude = Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE));
            meetViewModel.longitude = Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE));
            meetViewModel.getMeetList();
            if (!TextUtils.isEmpty(SPUtils.getInstance().getString(SpConfig.CITY))){
                meetViewModel.getMeetUploadCard();
            }

            //匹配成功 弹出匹配成功动画弹窗
            if (eventsCreateBean.getData().isMatching()){
                AnimationDialog animationDialog = new AnimationDialog(mActivity, "matching", eventsCreateBean.getData().getImage());
                animationDialog.showDialog();
            }

            tvNum.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.VISIBLE);

            //3秒后执行Runnable中的run方法
            MyApplication.getMainThreadHandler().postDelayed(() -> {
                tvNum.setVisibility(View.VISIBLE);
                lottieAnimationView.setVisibility(View.GONE);
            }, 3000);
        });

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
    protected void initDataFromService() {
        LocationUtils.getInstance().startLocalService();
        mActivity.showLoading();
    }

    @OnClick({R.id.iv_like_btn, R.id.iv_dislike_btn, R.id.rl_user})
    void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.iv_like_btn://喜欢
                iv_like_animation.setVisibility(View.VISIBLE);
                iv_like_animation.setImageResource(R.drawable.webp_meet_like);
                meetViewModel.getMeetOperateLike(dataList.get(0).getObjectId(), "LIKE", dataList.get(0).getPhoto());
                break;
            case R.id.iv_dislike_btn://不喜欢
                meetViewModel.getMeetOperateLike(dataList.get(0).getObjectId(), "DISLIKE", dataList.get(0).getPhoto());
                break;
            case R.id.rl_user://查看任务详情
                bundle.putString("id", dataList.get(0).getObjectId());
                bundle.putString("sex", dataList.get(0).getSex());
                ActivityUtils.startActivity(bundle, UserInfoActivity.class);
                break;
        }
    }

    /**
     * 定位成功，加载活动列表
     * @param event
     */
    @Subscribe
    public void onEventLocation(LocationEvent event) {
        meetViewModel.latitude = event.getLatitude();
        meetViewModel.longitude = event.getLongitude();
        meetViewModel.getMeetList();
        meetViewModel.getMeetUploadCard();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance().stopLocalService();
    }
}
