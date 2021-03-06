package com.wankrfun.crania.view.meet;

import android.annotation.SuppressLint;
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
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.github.penfeizhou.animation.webp.WebPDrawable;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineAboutEventsAdapter;
import com.wankrfun.crania.adapter.MineAboutLifeAdapter;
import com.wankrfun.crania.adapter.MineAboutWishAdapter;
import com.wankrfun.crania.adapter.TaAboutQuestionsAdapter;
import com.wankrfun.crania.base.BaseLazyFragment;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.MeetListBean;
import com.wankrfun.crania.dialog.AnimationDialog;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.event.LocationEvent;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.AnimatorUtils;
import com.wankrfun.crania.utils.LocationUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.utils.RefreshUtils;
import com.wankrfun.crania.utils.ScrollUtils;
import com.wankrfun.crania.view.events.EventsDetailActivity;
import com.wankrfun.crania.view.mine.user.UserInfoActivity;
import com.wankrfun.crania.viewmodel.MeetViewModel;
import com.wankrfun.crania.widget.AdaptationScrollView;
import com.wankrfun.crania.widget.CornerImageView;
import com.wankrfun.crania.widget.OnDoubleClickListener;
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
 * @Description: ??????fragment
 * @Author: ?????????
 * @CreateDate: 4/7/21 1:19 PM
 * @UpdateUser: ?????????
 * @UpdateDate: 4/7/21 1:19 PM
 * @UpdateRemark: ????????????
 * @Version: 1.0
 */
@SuppressLint("ClickableViewAccessibility")
public class MeetHomeFragment extends BaseLazyFragment {
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @BindView(R.id.tv_empty)
    AppCompatTextView tvEmpty;
    @BindView(R.id.scroll)
    AdaptationScrollView adaptationScrollView;
    @BindView(R.id.iv_image)
    CornerImageView cornerImageView;
    @BindView(R.id.iv_image2)
    CornerImageView cornerImageView2;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.rl_challenge)
    RelativeLayout rlChallenge;
    @BindView(R.id.rl)
    RelativeLayout relativeLayout;
    @BindView(R.id.tv_start_title)
    AppCompatTextView tv_start_title;
    @BindView(R.id.iv_like_animation)
    AppCompatImageView iv_like_animation;
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
    @BindView(R.id.ll_wish)
    LinearLayout llWish;
    @BindView(R.id.banner_wish)
    Banner bannerWish;
    @BindView(R.id.indicator_wish)
    CircleIndicator indicatorWish;
    @BindView(R.id.ll_life)
    LinearLayout llLife;
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
    @BindView(R.id.ll_questions)
    LinearLayout llQuestions;
    @BindView(R.id.banner_questions)
    Banner bannerQuestions;
    @BindView(R.id.indicator_questions)
    CircleIndicator indicatorQuestions;
    @BindView(R.id.ll_tacit_one)
    LinearLayout llTacitOne;
    @BindView(R.id.ll_tacit_two)
    LinearLayout llTacitTwo;
    @BindView(R.id.ll_tacit_three)
    LinearLayout llTacitThree;
    @BindView(R.id.ll_tacit_four)
    LinearLayout llTacitFour;
    @BindView(R.id.iv_star1)
    AppCompatImageView ivStar1;
    @BindView(R.id.iv_star2)
    AppCompatImageView ivStar2;
    @BindView(R.id.iv_star3)
    AppCompatImageView ivStar3;
    @BindView(R.id.tv_star)
    AppCompatTextView tvStar;
    @BindView(R.id.tv_star_title)
    AppCompatTextView tvStarTitle;
    @BindView(R.id.tv_answer_a)
    AppCompatTextView tvAnswerA;
    @BindView(R.id.tv_answer_b)
    AppCompatTextView tvAnswerB;
    @BindView(R.id.tv_result)
    AppCompatTextView tvResult;
    private LottieAnimationView lottieAnimationView;
    private MeetViewModel meetViewModel;
    private List<MeetListBean.DataBean.ListBean> dataList = new ArrayList<>();
    private String wish;//???????????????????????????????????????
    private int pos = 0;//??????????????????
    private int score = 0;//???????????? ????????????
    private List<String> challengeIds = new ArrayList<>();
    private List<Integer> progress = new ArrayList<>();

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
        lottieAnimationView = getParentFragment().getView().findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setAnimation("meet_success.json");
        iv_like_animation.setImageDrawable(WebPDrawable.fromAsset(mActivity, "webp_meet_like.webp"));

        llTacitOne.getBackground().setAlpha(230);
        llTacitTwo.getBackground().setAlpha(230);
        llTacitThree.getBackground().setAlpha(230);

        //????????????????????????????????????????????????
        ScrollUtils.OnScrollChangeListener(adaptationScrollView, linearLayout);

        meetViewModel = mActivity.getViewModel(MeetViewModel.class);

        //????????????????????????????????????
        meetViewModel.meetListLiveData.observe(this, meetListBean -> {
            mActivity.hideLoading();

            if (meetListBean.getData().getAllowedToday() == 15){
                lottieAnimationView.setMinAndMaxFrame(0, 0);
            }else {
                lottieAnimationView.setMinAndMaxFrame((14 - meetListBean.getData().getAllowedToday()) * 7, (14 - meetListBean.getData().getAllowedToday() + 1) * 7);
            }
            lottieAnimationView.playAnimation();

            if (meetListBean.getData().getAllowedToday() != 0){
                if (meetListBean.getData().getList().size() != 0){
                    rlEmpty.setVisibility(View.GONE);
                    adaptationScrollView.setVisibility(View.VISIBLE);

                    dataList = meetListBean.getData().getList();

                    ImageLoader.load(mActivity, new ImageConfig.Builder()
                            .url(meetListBean.getData().getList().get(0).getPersonInfoCard().getPhoto())
                            .placeholder(R.drawable.icon_empty_meet)
                            .errorPic(R.drawable.icon_empty_meet)
                            .imageView(cornerImageView)
                            .build());

                    ImageLoader.load(mActivity, new ImageConfig.Builder()
                            .url(meetListBean.getData().getList().get(0).getPersonInfoCard().getPhoto())
                            .placeholder(R.drawable.icon_empty_meet)
                            .errorPic(R.drawable.icon_empty_meet)
                            .imageView(cornerImageView2)
                            .build());

                    relativeLayout.getBackground().setAlpha(170);
                    if (meetListBean.getData().getList().get(0).getChallenges().size() != 0){
                        relativeLayout.setVisibility(View.VISIBLE);
                        tv_start_title.setText("TA??????????????????????????????TA?????????????????????????????????");
                        pos = 0;
                        getTacitAnswer(dataList.get(0).getChallenges(), pos, "");

                        for (int i = 0; i < dataList.get(0).getChallenges().size(); i++){
                            challengeIds.add(dataList.get(0).getChallenges().get(i).getObjectId());
                        }
                    }else {
                        if (meetListBean.getData().getList().get(0).getPersonInfoCard().getIsmatchChallenger().equals("2")){
                            relativeLayout.setVisibility(View.VISIBLE);
                            tv_start_title.setText("TA???????????????????????????????????????????????????");
                        }else {
                            relativeLayout.setVisibility(View.GONE);
                        }
                    }

                    tv_name.setText(meetListBean.getData().getList().get(0).getPersonInfoCard().getName());
                    iv_sex.setImageResource(meetListBean.getData().getList().get(0).getPersonInfoCard().getSex().endsWith("female") ? R.drawable.icon_events_female : R.drawable.icon_sex_male);
                    tv_age.setText(StringUtils.isTrimEmpty(meetListBean.getData().getList().get(0).getPersonInfoCard().getBirthday()) ? "??????" : NumberUtils.getAge(meetListBean.getData().getList().get(0).getPersonInfoCard().getBirthday()) + "???");
                    if (!TextUtils.isEmpty(meetListBean.getData().getList().get(0).getPersonInfoCard().getJob())){
                        switch (meetListBean.getData().getList().get(0).getPersonInfoCard().getJob()){
                            case "s:":
                                tv_work.setText("??????");
                                break;
                            case "j:":
                                tv_work.setText("??????");
                                break;
                            case "c:":
                                tv_work.setText("???????????????");
                                break;
                        }
                    }else {
                        tv_work.setText("???????????????");
                    }
                    if (!TextUtils.isEmpty(meetListBean.getData().getList().get(0).getPersonInfoCard().getBirthday())){
                        tv_constellation.setText(NumberUtils.date2Constellation(meetListBean.getData().getList().get(0).getPersonInfoCard().getBirthday()));
                    }else {
                        tv_constellation.setText("??????");
                    }

                    tv_city.setText(meetListBean.getData().getList().get(0).getPersonInfoCard().getAddress());

                    ll_tag.getBackground().setAlpha(90);
                    ll_event_tag.getBackground().setAlpha(90);
                    if (meetListBean.getData().getList().get(0).getPersonInfoCard().getTag() != null && meetListBean.getData().getList().get(0).getPersonInfoCard().getTag().size() != 0){
                        tv_title.setText(meetListBean.getData().getList().get(0).getPersonInfoCard().getTag().get(0));
                        iv_icon.setImageResource(RefreshUtils.setTagIcon(meetListBean.getData().getList().get(0).getPersonInfoCard().getTag().get(0)));
                    }

                    if (meetListBean.getData().getList().get(0).getPersonInfoCard().getEvent_tag() != null && meetListBean.getData().getList().get(0).getPersonInfoCard().getEvent_tag().size() != 0){
                        tv_title2.setText(meetListBean.getData().getList().get(0).getPersonInfoCard().getEvent_tag().get(0));
                        iv_icon2.setImageResource(RefreshUtils.setEventTagIcon(meetListBean.getData().getList().get(0).getPersonInfoCard().getEvent_tag().get(0)));
                    }

                    if (meetListBean.getData().getList().get(0).getWishes().size() != 0){
                        llWish.setVisibility(View.VISIBLE);
                        bannerWish.addBannerLifecycleObserver(this)//???????????????????????????
                                .setAdapter(new MineAboutWishAdapter(mActivity, meetListBean.getData().getList().get(0).getWishes(),""))//????????????
                                .isAutoLoop(false)
                                .setIndicator(indicatorWish, false)
                                .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                                .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                                .setOnBannerListener((data, position) -> {

                                }).start();
                    }else {
                        llWish.setVisibility(View.GONE);
                    }

                    if (meetListBean.getData().getList().get(0).getLifeMoments().size() != 0){
                        llLife.setVisibility(View.VISIBLE);
                        bannerLife.addBannerLifecycleObserver(this)//???????????????????????????
                                .setAdapter(new MineAboutLifeAdapter(mActivity, meetListBean.getData().getList().get(0).getLifeMoments()))//????????????
                                .isAutoLoop(false)
                                .setIndicator(indicatorLife, false)
                                .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                                .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                                .setOnBannerListener((data, position) -> {
                                    PictureEnlargeUtils.getPictureEnlargeList(mActivity, meetListBean.getData().getList().get(0).getLifeMoments().get(position).getImages(), 0);
                                }).start();
                    }else {
                        llLife.setVisibility(View.GONE);
                    }

                    if (meetListBean.getData().getList().get(0).getEventMoments().size() !=0){
                        llEvents.setVisibility(View.VISIBLE);
                        bannerEvents.addBannerLifecycleObserver(this)//???????????????????????????
                                .setAdapter(new MineAboutEventsAdapter(mActivity, meetListBean.getData().getList().get(0).getEventMoments()))//????????????
                                .isAutoLoop(false)
                                .setIndicator(indicatorEvents, false)
                                .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                                .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                                .setOnBannerListener((data, position) -> {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", meetListBean.getData().getList().get(0).getEventMoments().get(position).getEventId());
                                    bundle.putString("creator", meetListBean.getData().getList().get(0).getEventMoments().get(position).getCreatorId());
                                    ActivityUtils.startActivity(bundle, EventsDetailActivity.class);
                                }).start();
                    }else {
                        llEvents.setVisibility(View.GONE);
                    }

                    if (meetListBean.getData().getList().get(0).getBioQAs().size() !=0){
                        llQuestions.setVisibility(View.VISIBLE);
                        bannerQuestions.addBannerLifecycleObserver(this)//???????????????????????????
                                .setAdapter(new TaAboutQuestionsAdapter(mActivity, NumberUtils.getBisectionList(meetListBean.getData().getList().get(0).getBioQAs(), 3)))//????????????
                                .isAutoLoop(false)
                                .setIndicator(indicatorQuestions, false)
                                .setIndicatorSelectedColor(mActivity.getResources().getColor(R.color.color_FEFEDA))
                                .setIndicatorNormalColor(mActivity.getResources().getColor(R.color.color_E0E0E0))
                                .setOnBannerListener((data, position) -> {

                                }).start();
                    }else {
                        llQuestions.setVisibility(View.GONE);
                    }
                }else {
                    rlEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText("???????????????????????????");
                }
            }else {
                tvEmpty.setText("???????????????????????????,?????????????????????");
                rlEmpty.setVisibility(View.VISIBLE);
            }
        });

        //????????????: ????????????????????????????????????
        meetViewModel.meetUserCardLiveData.observe(this, eventsCreateBean -> {
            iv_like_animation.setVisibility(View.GONE);
            wish = "";

            if (eventsCreateBean.getCode() != 0){
                ToastUtils.showShort(eventsCreateBean.getError());
                return;
            }

            meetViewModel.latitude = Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE));
            meetViewModel.longitude = Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE));
            meetViewModel.getMeetList();
            meetViewModel.getMeetUserCard();

            //???????????? ??????????????????????????????
            if (eventsCreateBean.getData().isMatching()){
                AnimationDialog animationDialog = new AnimationDialog(mActivity, "matching", eventsCreateBean.getData().getImage());
                animationDialog.showDialog();
            }
        });

        //??????????????????????????????
        rlUser.setOnTouchListener(new OnDoubleClickListener(new OnDoubleClickListener.OnDoubleClickCallBack() {
            @Override
            public void oneClick() {
                Bundle bundle = new Bundle();
                bundle.putString("id", dataList.get(0).getPersonInfoCard().getObjectId());
                bundle.putString("sex", dataList.get(0).getPersonInfoCard().getSex());
                ActivityUtils.startActivity(bundle, UserInfoActivity.class);
            }

            @Override
            public void doubleClick() {
                if (dataList.get(0).getChallenges().size() == 0 && !dataList.get(0).getPersonInfoCard().getIsmatchChallenger().equals("2")){
                    ToastUtils.showShort("TA???????????????????????????????????????");
                    return;
                }
                AnimatorUtils.FlipAnimatorXViewShow(rlUser, rlChallenge, 400);
                linearLayout.setVisibility(View.GONE);
            }
        }));

        //??????????????????????????????
        rlChallenge.setOnTouchListener(new OnDoubleClickListener(new OnDoubleClickListener.OnDoubleClickCallBack() {
            @Override
            public void oneClick() {

            }

            @Override
            public void doubleClick() {
                AnimatorUtils.FlipAnimatorXViewShow(rlChallenge, rlUser, 400);
                linearLayout.setVisibility(View.VISIBLE);
            }
        }));
    }

    @Override
    protected void initDataFromService() {
        if (PermissionUtils.isCheckPermission(mActivity)){
            try {
                LocationUtils.getInstance().startLocalService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.iv_like_btn, R.id.iv_dislike_btn, R.id.ll_tacit_two, R.id.ll_tacit_three, R.id.tv_ok})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_like_btn://??????
                iv_like_animation.setVisibility(View.VISIBLE);
                meetViewModel.getMeetOperateLike(dataList.get(0).getPersonInfoCard().getObjectId(), "LIKE", dataList.get(0).getPersonInfoCard().getPhoto(), wish);
                break;
            case R.id.iv_dislike_btn://?????????
                meetViewModel.getMeetOperateLike(dataList.get(0).getPersonInfoCard().getObjectId(), "DISLIKE", dataList.get(0).getPersonInfoCard().getPhoto(), "");
                break;
            case R.id.ll_tacit_two://??????A
                pos ++;
                getTacitAnswer(dataList.get(0).getChallenges(), pos, "A");
                break;
            case R.id.ll_tacit_three://??????B
                pos ++;
                getTacitAnswer(dataList.get(0).getChallenges(), pos, "B");
                break;
            case R.id.tv_ok://??????
                AnimatorUtils.FlipAnimatorXViewShow(rlChallenge, rlUser, 400);
                linearLayout.setVisibility(View.VISIBLE);
                meetViewModel.latitude = Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE));
                meetViewModel.longitude = Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE));
                meetViewModel.getMeetList();
                meetViewModel.getMeetUserCard();
                break;
        }
    }

    /**
     * ?????????????????????????????????
     * @param event
     */
    @Subscribe
    public void onEventLocation(LocationEvent event) {
        mActivity.showLoading();
        meetViewModel.latitude = event.getLatitude();
        meetViewModel.longitude = event.getLongitude();
        meetViewModel.getMeetList();
    }

    /**
     * ?????????????????????????????????string
     * @param event
     */
    @Subscribe
    public void onWishFiveContent(EventsEvent event) {
        wish = event.getTitle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (PermissionUtils.isCheckPermission(mActivity)){
            LocationUtils.getInstance().stopLocalService();
        }
    }

    /**
     * ????????????????????????
     *
     * @param challenges
     * @param pos
     * @param answer
     */
    private void getTacitAnswer(List<MeetListBean.DataBean.ListBean.ChallengesBean> challenges, int pos, String answer) {
        switch (pos){
            case 0://????????????
                tvStar.setText("??????ta?????????????????????????????????");
                ivStar1.setImageResource(R.drawable.icon_meet_star3);
                ivStar2.setImageResource(R.drawable.icon_meet_star3);
                ivStar3.setImageResource(R.drawable.icon_meet_star3);
                tvStarTitle.setTextSize(16);
                tvStarTitle.setText(challenges.get(pos).getQuestion());
                tvAnswerA.setText(challenges.get(pos).getChoiceA());
                tvAnswerB.setText(challenges.get(pos).getChoiceB());
                llTacitTwo.setVisibility(View.VISIBLE);
                llTacitThree.setVisibility(View.VISIBLE);
                llTacitFour.setVisibility(View.GONE);
                break;
            case 1://????????????
                tvStarTitle.setText(challenges.get(pos).getQuestion());
                tvAnswerA.setText(challenges.get(pos).getChoiceA());
                tvAnswerB.setText(challenges.get(pos).getChoiceB());
                if (challenges.get(0).getChosen().equals(answer)){
                    tvStar.setText("??????????????????????????????????????????");
                    ivStar1.setImageResource(R.drawable.icon_meet_star1);
                    score = 1;
                    progress.add(1);
                }else {
                    score = 0;
                    progress.add(0);
                    tvStar.setText("??????????????????????????????????????????");
                    ivStar1.setImageResource(R.drawable.icon_meet_star2);
                }
                break;
            case 2://????????????
                tvStarTitle.setText(challenges.get(pos).getQuestion());
                tvAnswerA.setText(challenges.get(pos).getChoiceA());
                tvAnswerB.setText(challenges.get(pos).getChoiceB());
                if (challenges.get(1).getChosen().equals(answer)){
                    if (score == 1){
                        score = 2;
                        tvStar.setText("??????????????????????????????ta?????????");
                    }else {
                        score = 1;
                        tvStar.setText("??????????????????????????????????????????");
                    }
                    ivStar2.setImageResource(R.drawable.icon_meet_star1);
                    progress.add(1);
                }else {
                    if (score == 1){
                        score = 1;
                        tvStar.setText("???????????????ta??????????????????");
                    }else {
                        score = 0;
                        tvStar.setText("??????????????????????????????????????????");
                    }
                    ivStar2.setImageResource(R.drawable.icon_meet_star2);
                    progress.add(0);
                }
                break;
            case 3://??????
                if (challenges.get(2).getChosen().equals(answer)){
                    ivStar3.setImageResource(R.drawable.icon_meet_star1);
                    progress.add(1);
                    switch (score){
                        case 2:
                            score = 3;
                            tvStar.setText("?????????????????????????????????");
                            break;
                        case 1:
                            score = 2;
                            tvStar.setText("??????????????????????????????");
                            break;
                        case 0:
                            score = 2;
                            tvStar.setText("?????????????????????????????????");
                            break;
                    }
                }else {
                    ivStar3.setImageResource(R.drawable.icon_meet_star2);
                    progress.add(0);
                    switch (score){
                        case 2:
                            score = 2;
                            tvStar.setText("??????????????????????????????");
                            break;
                        case 1:
                            score = 1;
                            tvStar.setText("?????????????????????????????????");
                            break;
                        case 0:
                            score = 0;
                            tvStar.setText("?????????????????????????????????");
                            break;
                    }
                }

                switch (score){
                    case 0:
                        tvStarTitle.setText("????????????");
                        tvResult.setText("????????????ta?????????????????????\n?????????????????????????????????ta?????????\n???????????????????????????\n?????????????????????????????????");
                        break;
                    case 1:
                        tvStarTitle.setText("????????????");
                        tvResult.setText("????????????ta?????????????????????\n?????????????????????????????????ta?????????\n???????????????????????????\n?????????????????????????????????");
                        break;
                    case 2:
                        tvStarTitle.setText("????????????");
                        tvResult.setText("????????????ta???????????????\nta?????????????????????????????????\n??????????????????????????????????????????");
                        break;
                    case 3:
                        tvStarTitle.setText("????????????");
                        tvResult.setText("????????????ta???????????????\n??????????????????????????????ta???\n????????????????????????ta?????????");
                        break;
                }
                tvStarTitle.setTextSize(25);
                llTacitTwo.setVisibility(View.GONE);
                llTacitThree.setVisibility(View.GONE);
                llTacitFour.setVisibility(View.VISIBLE);

                meetViewModel.getSubmitChallenge(dataList.get(0).getPersonInfoCard().getObjectId(), challengeIds, progress, score);
                break;
        }
    }
}
