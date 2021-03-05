package com.wankrfun.crania.view.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.lxj.xpopup.XPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineAlbumAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.UserInfoBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.utils.ScrollUtils;
import com.wankrfun.crania.viewmodel.MeetViewModel;
import com.wankrfun.crania.viewmodel.MineViewModel;
import com.wankrfun.crania.widget.AdaptationScrollView;
import com.wankrfun.crania.widget.AutoHeightViewPager;
import com.wankrfun.crania.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: UserInfoActivity
 * @Description: 用户详情页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/29/21 1:38 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/29/21 1:38 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserInfoActivity extends BaseActivity {
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
    @BindView(R.id.tv_initiate)
    AppCompatTextView tvInitiate;
    @BindView(R.id.tv_apply)
    AppCompatTextView tvApply;
    @BindView(R.id.view_pager)
    AutoHeightViewPager viewPager;
    private MineViewModel mineViewModel;
    private MeetViewModel meetViewModel;
    private UserInfoBean userInfoBean;
    //切换个性标签布尔值
    private boolean isShow;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_user_info;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        Fragment[] fragments = {
                new MineInitiateFragment(getIntent().getStringExtra("id"), "other", getIntent().getStringExtra("sex"))
        };
        ScrollUtils.getViewPagerAddOnPageChangeListener(activity, viewPager, fragments);
        ScrollUtils.getTooBarOnScrollChangeListener(activity, scrollView, toolbar, null);

        refresh.setEnableLoadMore(false);
        refresh.setEnableRefresh(true);
        refresh.setEnableOverScrollDrag(true);
        refresh.setEnableOverScrollBounce(true);
        refresh.setOnRefreshListener(refreshLayout -> {
            refreshLayout.finishRefresh(1000);
            mineViewModel.getUserInfo(getIntent().getStringExtra("id"));
        });

        rvAlbum.setLayoutManager(new LinearLayoutManager(activity ,LinearLayoutManager.HORIZONTAL,false));
        MineAlbumAdapter mineAlbumAdapter = new MineAlbumAdapter(R.layout.adapter_mine_album, null);
        rvAlbum.setAdapter(mineAlbumAdapter);

        mineAlbumAdapter.setOnItemClickListener((adapter, view1, position) -> {
            PictureEnlargeUtils.getPictureEnlargeList(activity, userInfoBean.getData().getProfile().getImages(), position);
        });

        mineViewModel = getViewModel(MineViewModel.class);
        meetViewModel = getViewModel(MeetViewModel.class);

        //获取用户详情返回结果
        mineViewModel.userInfoLiveData.observe(this, userInfoBean -> {
            this.userInfoBean = userInfoBean;
            ImageLoader.load(activity, new ImageConfig.Builder()
                    .url(userInfoBean.getData().getProfile().getBackgroundImage())
                    .imageView(ivBg)
                    .placeholder(R.drawable.icon_bg7)
                    .errorPic(R.drawable.icon_bg7)
                    .build());
            ImageLoader.load(activity, new ImageConfig.Builder()
                    .url(userInfoBean.getData().getProfile().getPhoto())
                    .imageView(ivAvatar)
                    .placeholder(R.drawable.rc_default_portrait)
                    .errorPic(R.drawable.rc_default_portrait)
                    .build());
            ImageLoader.load(activity, new ImageConfig.Builder()
                    .url(userInfoBean.getData().getProfile().getPhoto())
                    .imageView(ivAvatar2)
                    .placeholder(R.drawable.rc_default_portrait)
                    .errorPic(R.drawable.rc_default_portrait)
                    .build());
            tvLike.setText(userInfoBean.getData().getProfile().getLikeNum() + "喜欢" + (userInfoBean.getData().getProfile().getSex().equals("male") ? "他" : "她"));
            tvName.setText(userInfoBean.getData().getProfile().getName());
            tvAddress.setText(userInfoBean.getData().getProfile().getAddress() + "|");
            ivSex.setImageResource(userInfoBean.getData().getProfile().getSex().equals("male") ? R.drawable.icon_sex_male : R.drawable.icon_sex_female);
            tvConstellation.setText("| " + userInfoBean.getData().getProfile().getAge() + "岁 |" +
                    userInfoBean.getData().getProfile().getConstellation() + " |" +
                    NumberUtils.getJob(userInfoBean.getData().getProfile().getJob()));
            mineAlbumAdapter.setNewData(userInfoBean.getData().getProfile().getImages());

            EventsUtils.getShowTag(activity, flLabel, EventsUtils.getMineTagSmall(userInfoBean));
            if (EventsUtils.getMineTag(userInfoBean).size() > 3){
                ivLabel.setVisibility(View.VISIBLE);
            }else {
                ivLabel.setVisibility(View.GONE);
            }
        });

        //解除匹配成功返回结果
        meetViewModel.unMatchingLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            finish();
        });

        mineViewModel.getUserInfo(getIntent().getStringExtra("id"));
    }

    @OnClick({R.id.iv_set, R.id.iv_set2, R.id.iv_more, R.id.iv_more2, R.id.iv_label})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_set:
            case R.id.iv_set2:
                finish();
                break;
            case R.id.iv_more://更多
            case R.id.iv_more2:
                new XPopup.Builder(activity)
                        .hasShadowBg(false)
                        .atView(view)
                        .asAttachList(new String[]{"解除匹配","取消"}, null,
                                (position, text) -> {
                                    switch (text){
                                        case "解除匹配":
                                            meetViewModel.getUnMatching(userInfoBean.getData().getProfile().getObjectId());
                                            break;
                                    }
                                }).show();
                break;
            case R.id.iv_label://个性标签切换数量
                if (!isShow){
                    EventsUtils.getShowTag(activity, flLabel, EventsUtils.getMineTag(userInfoBean));
                }else {
                    EventsUtils.getShowTag(activity, flLabel, EventsUtils.getMineTagSmall(userInfoBean));
                }
                isShow = !isShow;
                break;
        }
    }
}
