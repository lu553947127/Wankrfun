package com.wankrfun.crania.view.events;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.BannerExampleAdapter;
import com.wankrfun.crania.adapter.EventsCommentAdapter;
import com.wankrfun.crania.adapter.EventsParticipantsAdapter;
import com.wankrfun.crania.adapter.EventsSponsorAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.EventsCommentsBean;
import com.wankrfun.crania.bean.EventsDetailBean;
import com.wankrfun.crania.bean.EventsParticipantsBean;
import com.wankrfun.crania.bean.EventsStateBean;
import com.wankrfun.crania.dialog.CustomBottomComment;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.view.mine.UserInfoActivity;
import com.wankrfun.crania.viewmodel.EventsViewModel;
import com.wankrfun.crania.widget.CircleImageView;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.events
 * @ClassName: EventsDetailActivity
 * @Description: 活动详情页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/25/21 3:33 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/25/21 3:33 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("SetTextI18n")
public class EventsDetailActivity extends BaseActivity {
    @BindView(R.id.iv_bar_right)
    AppCompatImageView ivBarRight;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rl_image_not)
    RelativeLayout rlImageNot;
    @BindView(R.id.iv_icon_type)
    AppCompatImageView ivIconType;
    @BindView(R.id.iv_icon)
    AppCompatImageView ivIcon;
    @BindView(R.id.tv_type)
    AppCompatTextView tvType;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_people_num)
    AppCompatTextView tvPeopleNum;
    @BindView(R.id.tv_time)
    AppCompatTextView tvTime;
    @BindView(R.id.tv_address)
    AppCompatTextView tvAddress;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.iv_avatar2)
    CircleImageView ivAvatar2;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.tv_num)
    AppCompatTextView tvNum;
    @BindView(R.id.tv_distance)
    AppCompatTextView tvDistance;
    @BindView(R.id.iv_sex)
    AppCompatImageView ivSex;
    @BindView(R.id.tv_constellation)
    AppCompatTextView tvConstellation;
    @BindView(R.id.ll_sponsor)
    LinearLayout llSponsor;
    @BindView(R.id.tv_sponsor_num)
    AppCompatTextView tvSponsorNum;
    @BindView(R.id.rv_sponsor)
    RecyclerView rvSponsor;
    @BindView(R.id.ll_participants)
    LinearLayout llParticipants;
    @BindView(R.id.rv_participants)
    RecyclerView rvParticipants;
    @BindView(R.id.tv_participants)
    AppCompatTextView tvParticipants;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.tv_comment_num)
    AppCompatTextView tvCommentNum;
    @BindView(R.id.tv_comment_num2)
    AppCompatTextView tvCommentNum2;
    @BindView(R.id.tv_collection)
    AppCompatTextView tvCollection;
    @BindView(R.id.tv_sign)
    AppCompatTextView tvSign;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private EventsViewModel eventsViewModel;
    private EventsDetailBean eventsDetailBean;
    private EventsStateBean eventsStateBean;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_events_detail;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        rvSponsor.setLayoutManager(new LinearLayoutManager(activity ,LinearLayoutManager.HORIZONTAL,false));
        EventsSponsorAdapter eventsSponsorAdapter = new EventsSponsorAdapter(R.layout.adapter_events_sponsor, null);
        rvSponsor.setAdapter(eventsSponsorAdapter);
        eventsSponsorAdapter.setOnItemClickListener((adapter, view, position) -> {
            EventsParticipantsBean.DataBean.ParticipantsBean listBean = eventsSponsorAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.getObjectId());
            bundle.putString("sex", listBean.getSex());
            ActivityUtils.startActivity(bundle, UserInfoActivity.class);
        });

        rvParticipants.setLayoutManager(new LinearLayoutManager(activity ,LinearLayoutManager.HORIZONTAL,false));
        EventsParticipantsAdapter eventsParticipantsAdapter = new EventsParticipantsAdapter(R.layout.adapter_events_sponsor, null);
        rvParticipants.setAdapter(eventsParticipantsAdapter);
        eventsParticipantsAdapter.setOnItemClickListener((adapter, view, position) -> {
            EventsParticipantsBean.DataBean.ParticipantsBean listBean = eventsParticipantsAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.getObjectId());
            bundle.putString("sex", listBean.getSex());
            ActivityUtils.startActivity(bundle, UserInfoActivity.class);
        });

        rvComment.setLayoutManager(new LinearLayoutManager(activity));
        EventsCommentAdapter eventsCommentAdapter = new EventsCommentAdapter(R.layout.adapter_events_comment, null);
        rvComment.setAdapter(eventsCommentAdapter);

        eventsCommentAdapter.setOnItemClickListener((adapter, view, position) -> {
            EventsCommentsBean.DataBean.CommentsBean listBean = eventsCommentAdapter.getData().get(position);
            if (SPUtils.getInstance().getString(SpConfig.USER_ID).equals(listBean.getComment_user())){
                new XPopup.Builder(activity)
                        .atView(view)
                        .asAttachList(new String[]{"回复","删除"}, null,
                                (position1, text) -> {
                                    switch (text){
                                        case "回复":
                                            new XPopup.Builder(activity)
                                                    .autoOpenSoftInput(true)
                                                    .asCustom(new CustomBottomComment(activity, listBean.getComment_userName(), listBean.getObjectId()))
                                                    .show();
                                            break;
                                        case "删除":
                                            new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "您确定要删除这条评论吗", () -> {
                                                eventsCommentAdapter.remove(position);
                                                eventsViewModel.getEventsDeleteComment("comment", listBean.getObjectId(), "");
                                            }).show();
                                            break;
                                    }
                                }).show();
            }else {
                new XPopup.Builder(activity)
                        .autoOpenSoftInput(true)
                        .asCustom(new CustomBottomComment(activity, listBean.getComment_userName(), listBean.getObjectId()))
                        .show();
            }
        });

        if (SPUtils.getInstance().getString(SpConfig.USER_ID).equals(getIntent().getStringExtra("creator"))){
            llBottom.setVisibility(View.VISIBLE);
            tvParticipants.setVisibility(View.VISIBLE);
            ivBarRight.setVisibility(View.VISIBLE);
        }else {
            llBottom.setVisibility(View.VISIBLE);
            tvParticipants.setVisibility(View.GONE);
            ivBarRight.setVisibility(View.GONE);
        }

        eventsViewModel = getViewModel(EventsViewModel.class);

        //获取活动详情返回结果
        eventsViewModel.eventsDetailLiveData.observe(this, eventsDetailBean -> {
            this.eventsDetailBean = eventsDetailBean;
            if (eventsDetailBean.getData().getEvent().getImages().size() == 0 && TextUtils.isEmpty(eventsDetailBean.getData().getEvent().getEventImage())){
                rlImageNot.setVisibility(View.VISIBLE);
                banner.setVisibility(View.GONE);
            }else {
                rlImageNot.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
            }
            EventsUtils.getEventsIcon(tvType, tvType, ivIcon, ivIconType, eventsDetailBean.getData().getEvent().getEventType(), eventsDetailBean.getData().getEvent().getEventTypeIcon());
            tvTitle.setText(eventsDetailBean.getData().getEvent().getEvent_contents());
            tvPeopleNum.setText(eventsDetailBean.getData().getEvent().getMax_num() == 0 ? "参与人数：不限人数" :
                    "参与人数：差" + (eventsDetailBean.getData().getEvent().getMax_num() - eventsDetailBean.getData().getEvent().getJoined_user_num()) + "人");
            tvTime.setText(eventsDetailBean.getData().getEvent().getActivity_time().equals("2200-01-01T08:30:00.000Z") ? "活动时间：不限时间" : "活动时间：" + NumberUtils.dealDateFormat(eventsDetailBean.getData().getEvent().getActivity_time()));
            tvAddress.setText("活动地点：" + eventsDetailBean.getData().getEvent().getEvent_address());
            tvName.setText(eventsDetailBean.getData().getEvent().getCreator_name());
            tvNum.setText("组局" + eventsDetailBean.getData().getEvent().getCreator_joinedEvents()+ "场/参与" + eventsDetailBean.getData().getEvent().getCreator_events() +"场");
            tvDistance.setText(NumberUtils.getDistance(Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)),
                    Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)),
                    eventsDetailBean.getData().getEvent().getEventLocation().getLongitude(),
                    eventsDetailBean.getData().getEvent().getEventLocation().getLatitude()) + "Km | ");
            tvConstellation.setText(" | " +
                    eventsDetailBean.getData().getEvent().getCreator_age() + "岁 ｜" +
                    eventsDetailBean.getData().getEvent().getCreator_constellation() + " ｜" +
                    NumberUtils.getJob(eventsDetailBean.getData().getEvent().getCreator_job()));
            ivSex.setImageResource(eventsDetailBean.getData().getEvent().getCreator_sex().equals("male") ? R.drawable.icon_sex_male : R.drawable.icon_sex_female);
            tvSponsorNum.setText(eventsDetailBean.getData().getEvent().getMax_num() == 0 ? "不限人数局" :
                    "这个" + eventsDetailBean.getData().getEvent().getMax_num() + "人局，还有" + eventsDetailBean.getData().getEvent().getJoined_user_num() + "个位子");
            tvCommentNum.setText("评论(" + eventsDetailBean.getData().getEvent().getComment_num() + ")");
            tvCommentNum2.setText(String.valueOf(eventsDetailBean.getData().getEvent().getComment_num()));
            ImageLoader.load(activity, new ImageConfig.Builder()
                    .url(eventsDetailBean.getData().getEvent().getCreator_image())
                    .imageView(ivAvatar)
                    .placeholder(R.drawable.rc_default_portrait)
                    .errorPic(R.drawable.rc_default_portrait)
                    .build());
            ImageLoader.load(activity, new ImageConfig.Builder()
                    .url(eventsDetailBean.getData().getEvent().getCreator_image())
                    .imageView(ivAvatar2)
                    .placeholder(R.drawable.rc_default_portrait)
                    .errorPic(R.drawable.rc_default_portrait)
                    .build());
            List<String> list = new ArrayList();
            list.add(eventsDetailBean.getData().getEvent().getEventImage());
            list.addAll(eventsDetailBean.getData().getEvent().getImages());
            banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                    .setAdapter(new BannerExampleAdapter(activity, list))//添加数据
                    .setOnBannerListener((data, position) -> {
                        PictureEnlargeUtils.getPictureEnlargeList(activity, list, position);
                    })
                    .start();
        });

        //获取参与人列表数据返回
        eventsViewModel.eventsSponsorLiveData.observe(this, eventsParticipantsBean -> {
            if (eventsParticipantsBean.getData().getParticipants().size() > 0){
                llSponsor.setVisibility(View.VISIBLE);
                eventsSponsorAdapter.setNewData(eventsParticipantsBean.getData().getParticipants());
            }else {
                llSponsor.setVisibility(View.GONE);
            }
        });

        //获取申请人列表数据返回
        eventsViewModel.eventsParticipantsLiveData.observe(this, eventsParticipantsBean -> {
            if (eventsParticipantsBean.getData().getParticipants().size() > 0){
                llParticipants.setVisibility(View.VISIBLE);
                eventsParticipantsAdapter.setNewData(eventsParticipantsBean.getData().getParticipants());
            }else {
                llParticipants.setVisibility(View.GONE);
            }
        });

        //获取活动评论列表数据返回
        eventsViewModel.eventsCommentsLiveData.observe(this, eventsCommentsBean -> {
            eventsCommentAdapter.setNewData(eventsCommentsBean.getData().getComments());
        });

        //查询当前活动可报名的状态
        eventsViewModel.eventsStateLiveData.observe(this, eventsStateBean -> {
            this.eventsStateBean = eventsStateBean;
            switch (eventsStateBean.getData().getStatus()){
                case "open"://报名
                    tvSign.setText("报名");
                    tvSign.setBackgroundResource(R.drawable.shape_yellow_15);
                    tvSign.setTextColor(getResources().getColor(R.color.black));
                    break;
                case "applied"://已报名
                    tvSign.setText("已报名，等待发起人通过中");
                    tvSign.setBackgroundResource(R.drawable.shape_orange_half_15);
                    tvSign.setTextColor(getResources().getColor(R.color.color_EBBB7D));
                    break;
                case "full"://已满员
                    tvSign.setText("已满员");
                    tvSign.setBackgroundResource(R.drawable.shape_orange_half_15);
                    tvSign.setTextColor(getResources().getColor(R.color.color_EBBB7D));
                    break;
                case "closed"://已截止报名
                    tvSign.setText("已截止报名");
                    tvSign.setBackgroundResource(R.drawable.shape_orange_half_15);
                    tvSign.setTextColor(getResources().getColor(R.color.color_EBBB7D));
                    break;
                case "joined"://打开聊天
                    tvSign.setText("打开聊天");
                    tvSign.setBackgroundResource(R.drawable.shape_white_half_15);
                    tvSign.setTextColor(getResources().getColor(R.color.white));
                    break;
                case "ended"://活动已结束
                    tvSign.setText("活动已完成");
                    tvSign.setBackgroundResource(R.drawable.shape_gray_15);
                    tvSign.setTextColor(getResources().getColor(R.color.white));
                    break;
            }
        });

        //申请活动成功返回结果
        eventsViewModel.eventsApplyLiveData.observe(this, eventsApplyBean -> {
            ToastUtils.showShort(eventsApplyBean.getData().getMsg());
            eventsViewModel.getEventsParticipants(getIntent().getStringExtra("id"));
            eventsViewModel.getEventsState(getIntent().getStringExtra("id"));
        });

        //删除活动成功返回结果
        eventsViewModel.eventsEventDeleteLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort("删除活动成功");
            finish();
        });

        //截止活动报名成功返回结果
        eventsViewModel.eventsEventEndLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            eventsViewModel.getEventsDetail(getIntent().getStringExtra("id"));
        });

        //添加评论成功返回结果
        eventsViewModel.eventsAddCommentLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            eventsViewModel.getEventsDetail(getIntent().getStringExtra("id"));
            eventsViewModel.getEventsComments(getIntent().getStringExtra("id"));
        });

        //添加回复成功返回结果
        eventsViewModel.eventsAddReplyLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            eventsViewModel.getEventsComments(getIntent().getStringExtra("id"));
        });

        //删除评论成功返回结果
        eventsViewModel.eventsDeleteCommentLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            eventsViewModel.getEventsDetail(getIntent().getStringExtra("id"));
        });

        //删除回复成功返回结果
        eventsViewModel.eventsDeleteReplyLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
        });

        eventsViewModel.getEventsDetail(getIntent().getStringExtra("id"));

        eventsViewModel.getEventsSponsor(getIntent().getStringExtra("id"));

        eventsViewModel.getEventsParticipants(getIntent().getStringExtra("id"));

        eventsViewModel.getEventsComments(getIntent().getStringExtra("id"));

        eventsViewModel.getEventsState(getIntent().getStringExtra("id"));
    }

    @OnClick({R.id.iv_bar_back, R.id.iv_bar_right, R.id.iv_avatar, R.id.iv_avatar2, R.id.tv_sponsor, R.id.tv_participants, R.id.tv_comment, R.id.tv_sign})
    void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.iv_bar_right://更多
                new XPopup.Builder(activity)
                        .hasShadowBg(false)
                        .atView(view)
                        .asAttachList(new String[]{"分享活动","标记为已完成活动","修改详情","截止报名","删除活动","取消"}, null,
                                (position, text) -> {
                                    switch (text){
                                        case "分享活动":
                                            ToastUtils.showShort("分享成功");
                                            break;
                                        case "标记为已完成活动":
                                            ToastUtils.showShort("标记完成");
                                            break;
                                        case "修改详情":
                                            bundle.putSerializable("info", eventsDetailBean);
                                            ActivityUtils.startActivity(bundle, EventsAddInfoActivity.class);
                                            break;
                                        case "截止报名":
                                            eventsViewModel.getEventsEnd(getIntent().getStringExtra("id"), "off");
                                            break;
                                        case "删除活动":
                                            eventsViewModel.getEventsDelete(getIntent().getStringExtra("id"), true);
                                            break;
                                    }
                                }).show();
                break;
            case R.id.iv_avatar:
            case R.id.iv_avatar2:
                if (SPUtils.getInstance().getString(SpConfig.USER_ID).equals(getIntent().getStringExtra("creator"))){
                    return;
                }
                bundle.putString("id", getIntent().getStringExtra("creator"));
                bundle.putString("sex", eventsDetailBean.getData().getEvent().getCreator_sex());
                ActivityUtils.startActivity(bundle, UserInfoActivity.class);
                break;
            case R.id.tv_sponsor://查看参与人
                bundle.putString("id", getIntent().getStringExtra("id"));
                bundle.putString("creator", getIntent().getStringExtra("creator"));
                ActivityUtils.startActivity(bundle, EventsSelectApplyActivity.class);
                break;
            case R.id.tv_participants://选择申请人
                bundle.putString("id", getIntent().getStringExtra("id"));
                bundle.putString("creator", getIntent().getStringExtra("creator"));
                bundle.putString("type", "participants");
                ActivityUtils.startActivity(bundle, EventsSelectApplyActivity.class);
                break;
            case R.id.tv_comment://评论
                new XPopup.Builder(activity)
                        .autoOpenSoftInput(true)
                        .asCustom(new CustomBottomComment(activity, "我要评论"))
                        .show();
                break;
            case R.id.tv_sign://报名
                switch (eventsStateBean.getData().getStatus()){
                    case "open"://报名
                        eventsViewModel.getEventsApply(getIntent().getStringExtra("id"));
                        break;
                    case "applied"://已报名
                        ToastUtils.showShort("您已经报名成功哦");
                        break;
                    case "full"://已满员
                        ToastUtils.showShort("抱歉，当前活动已经满员啦");
                        break;
                    case "closed"://已截止报名
                        ToastUtils.showShort("抱歉，当前活动已经截止报名了哦");
                        break;
                    case "joined"://打开活动群聊聊天
                        RongIM.getInstance().startGroupChat(activity, eventsDetailBean.getData().getEvent().getGroupId(), eventsDetailBean.getData().getEvent().getGroupName());
                        break;
                }
                break;
        }
    }

    @Subscribe
    public void onEventEvents(EventsEvent event) {
        switch (event.getType()){
            case "comment"://评论
                eventsViewModel.getEventsAddComment("comment", getIntent().getStringExtra("id"), "", event.getTitle());
                break;
            case "reply"://回复
                eventsViewModel.getEventsAddComment("reply", "", event.getId(), event.getTitle());
                break;
            case "delete_reply"://删除回复
                eventsViewModel.getEventsDeleteComment("reply", "", event.getTitle());
                break;
        }
    }
}
