package com.wankrfun.crania.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineAlbumAdapter;
import com.wankrfun.crania.base.BaseFragment;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.UserInfoBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.DrawableUtils;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.utils.ScrollUtils;
import com.wankrfun.crania.view.events.EventsAddActivity;
import com.wankrfun.crania.view.mine.user.ChangeAvatarActivity;
import com.wankrfun.crania.view.mine.user.ChangeBackgroundActivity;
import com.wankrfun.crania.view.mine.user.ChangeUserInfoActivity;
import com.wankrfun.crania.view.mine.MineAboutFragment;
import com.wankrfun.crania.view.mine.MineApplyFragment;
import com.wankrfun.crania.view.mine.MineFavFragment;
import com.wankrfun.crania.view.mine.MineInitiateFragment;
import com.wankrfun.crania.view.mine.set.SetActivity;
import com.wankrfun.crania.viewmodel.MeetViewModel;
import com.wankrfun.crania.viewmodel.MineViewModel;
import com.wankrfun.crania.widget.AdaptationScrollView;
import com.wankrfun.crania.widget.AutoHeightViewPager;
import com.wankrfun.crania.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view
 * @ClassName: MineFragment
 * @Description: ??????fragment
 * @Author: ?????????
 * @CreateDate: 1/14/21 2:25 PM
 * @UpdateUser: ?????????
 * @UpdateDate: 1/14/21 2:25 PM
 * @UpdateRemark: ????????????
 * @Version: 1.0
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.scroll)
    AdaptationScrollView scrollView;
    @BindView(R.id.rl_toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.rl_toolbar_top)
    RelativeLayout rl_toolbar;
    @BindView(R.id.iv_bg)
    AppCompatImageView ivBg;
    @BindView(R.id.tv_like)
    AppCompatTextView tvLike;
    @BindView(R.id.iv_label)
    AppCompatImageView ivLabel;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.iv_avatar2)
    CircleImageView ivAvatar2;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.tv_address)
    AppCompatTextView tvAddress;
    @BindView(R.id.iv_sex)
    AppCompatImageView ivSex;
    @BindView(R.id.tv_constellation)
    AppCompatTextView tvConstellation;
    @BindView(R.id.fl_label)
    FlexboxLayout flLabel;
    @BindView(R.id.rv_album)
    RecyclerView rvAlbum;
    @BindView(R.id.tv_about)
    AppCompatTextView tvAbout;
    @BindView(R.id.tv_initiate)
    AppCompatTextView tvInitiate;
    @BindView(R.id.tv_apply)
    AppCompatTextView tvApply;
    @BindView(R.id.tv_fav)
    AppCompatTextView tvFav;
    @BindView(R.id.view_pager)
    AutoHeightViewPager viewPager;
    private MineViewModel mineViewModel;
    private MeetViewModel meetViewModel;
    private UserInfoBean userInfoBean;
    //???????????????????????????
    private boolean isShow;

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState, View view) {
        Fragment[] fragments = {
                new MineAboutFragment(),
                new MineInitiateFragment(SPUtils.getInstance().getString(SpConfig.USER_ID), "mine", ""),
                new MineApplyFragment(),
                new MineFavFragment()
        };
        ScrollUtils.getViewPagerAddOnPageChangeListener(mActivity, getChildFragmentManager(), viewPager, fragments, tvAbout, tvInitiate, tvApply, tvFav);
        ScrollUtils.getTooBarOnScrollChangeListener(mActivity, scrollView, toolbar, null);

        refresh.setEnableLoadMore(false);
        refresh.setEnableRefresh(true);
        refresh.setEnableOverScrollDrag(true);
        refresh.setEnableOverScrollBounce(true);
        refresh.setOnRefreshListener(refreshLayout -> {
            refreshLayout.finishRefresh(1000);
            mineViewModel.getUserInfo(SPUtils.getInstance().getString(SpConfig.USER_ID));
        });

        rvAlbum.setLayoutManager(new LinearLayoutManager(mActivity ,LinearLayoutManager.HORIZONTAL,false));
        MineAlbumAdapter mineAlbumAdapter = new MineAlbumAdapter(R.layout.adapter_mine_album, null);
        rvAlbum.setAdapter(mineAlbumAdapter);

        mineAlbumAdapter.setOnItemClickListener((adapter, view1, position) -> {
            PictureEnlargeUtils.getPictureEnlargeList(mActivity, userInfoBean.getData().getProfile().getImages(), position);
        });

        mineViewModel = mActivity.getViewModel(MineViewModel.class);
        meetViewModel = mActivity.getViewModel(MeetViewModel.class);

        //??????????????????????????????
        mineViewModel.userInfoLiveData.observe(this, userInfoBean -> {
            this.userInfoBean = userInfoBean;
            ImageLoader.load(mActivity, new ImageConfig.Builder()
                    .url(userInfoBean.getData().getProfile().getBackgroundImage())
                    .imageView(ivBg)
                    .placeholder(R.drawable.icon_bg7)
                    .errorPic(R.drawable.icon_bg7)
                    .build());
            ImageLoader.load(mActivity, new ImageConfig.Builder()
                    .url(userInfoBean.getData().getProfile().getPhoto())
                    .imageView(ivAvatar)
                    .placeholder(R.drawable.rc_default_portrait)
                    .errorPic(R.drawable.rc_default_portrait)
                    .build());
            ImageLoader.load(mActivity, new ImageConfig.Builder()
                    .url(userInfoBean.getData().getProfile().getPhoto())
                    .imageView(ivAvatar2)
                    .placeholder(R.drawable.rc_default_portrait)
                    .errorPic(R.drawable.rc_default_portrait)
                    .build());
            tvLike.setText(userInfoBean.getData().getProfile().getLikeNum() + "?????????");
            tvName.setText(userInfoBean.getData().getProfile().getName());
            tvAddress.setText(userInfoBean.getData().getProfile().getAddress() + "|");
            ivSex.setImageResource(userInfoBean.getData().getProfile().getSex().equals("male") ? R.drawable.icon_sex_male : R.drawable.icon_sex_female);
            tvConstellation.setText("| " + userInfoBean.getData().getProfile().getAge() + "??? |" +
                    userInfoBean.getData().getProfile().getConstellation() + " |" +
                    NumberUtils.getJob(userInfoBean.getData().getProfile().getJob()));
            mineAlbumAdapter.setNewData(userInfoBean.getData().getProfile().getImages());

            EventsUtils.getShowTag(mActivity, flLabel, EventsUtils.getMineTagSmall(userInfoBean));
            if (EventsUtils.getMineTag(userInfoBean).size() > 3){
                ivLabel.setVisibility(View.VISIBLE);
            }else {
                ivLabel.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getPhoto())){
                meetViewModel.getMeetUploadCard();
            }
        });
    }

    @Override
    protected void initDataFromService() {

    }

    @OnClick({R.id.iv_set, R.id.iv_set2, R.id.iv_edit, R.id.iv_edit2, R.id.iv_bg, R.id.iv_bg2, R.id.iv_avatar, R.id.iv_avatar2, R.id.iv_label
            , R.id.tv_about, R.id.tv_initiate, R.id.tv_apply, R.id.tv_fav})
    void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.iv_set://????????????
            case R.id.iv_set2:
                ActivityUtils.startActivity(SetActivity.class);
                break;
            case R.id.iv_edit://??????????????????
            case R.id.iv_edit2:
                ActivityUtils.startActivity(ChangeUserInfoActivity.class);
                break;
            case R.id.iv_bg:
            case R.id.iv_bg2://????????????
                ActivityUtils.startActivity(ChangeBackgroundActivity.class);
                break;
            case R.id.iv_avatar:
            case R.id.iv_avatar2://????????????
                bundle.putString("images", userInfoBean.getData().getProfile().getPhoto());
                ActivityUtils.startActivity(bundle, ChangeAvatarActivity.class);
                break;
            case R.id.iv_label://????????????????????????
                if (!isShow){
                    EventsUtils.getShowTag(mActivity, flLabel, EventsUtils.getMineTag(userInfoBean));
                    ivLabel.setImageResource(R.drawable.icon_mine_up);
                }else {
                    EventsUtils.getShowTag(mActivity, flLabel, EventsUtils.getMineTagSmall(userInfoBean));
                    ivLabel.setImageResource(R.drawable.icon_mine_down);
                }
                isShow = !isShow;
                break;
            case R.id.tv_about://?????????
                viewPager.setCurrentItem(0);
                DrawableUtils.setMineEventsTab(mActivity, 0, tvAbout, tvInitiate, tvApply, tvFav);
                break;
            case R.id.tv_initiate://????????????
                DrawableUtils.setMineEventsTab(mActivity, 1, tvAbout, tvInitiate, tvApply, tvFav);
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_apply://????????????
                DrawableUtils.setMineEventsTab(mActivity, 2, tvAbout, tvInitiate, tvApply, tvFav);
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_fav://????????????
                DrawableUtils.setMineEventsTab(mActivity, 3, tvAbout, tvInitiate, tvApply, tvFav);
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (ScrollUtils.sAlpha != 0 && ScrollUtils.sColor != 0) {
            toolbar.setAlpha(ScrollUtils.sAlpha);
            //??????????????????????????????
            toolbar.setBackgroundColor(ScrollUtils.sColor);
            toolbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mineViewModel.getUserInfo(SPUtils.getInstance().getString(SpConfig.USER_ID));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ScrollUtils.sColor = 0;
        ScrollUtils.sAlpha = 0;
    }
}
