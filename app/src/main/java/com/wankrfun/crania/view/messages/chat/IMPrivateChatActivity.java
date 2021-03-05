package com.wankrfun.crania.view.messages.chat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.GroupRelationBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.view.events.EventsDetailActivity;
import com.wankrfun.crania.view.mine.UserInfoActivity;
import com.wankrfun.crania.viewmodel.EventsViewModel;
import com.wankrfun.crania.viewmodel.IMConnectViewModel;
import com.wankrfun.crania.widget.CircleImageView;
import com.wankrfun.crania.widget.CornerImageView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.messages.chat
 * @ClassName: IMPrivateChatActivity
 * @Description: 单聊页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/26/21 2:00 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/26/21 2:00 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class IMPrivateChatActivity extends BaseActivity implements RongIM.ConversationClickListener{
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.iv_bar_right)
    AppCompatImageView ivBarRight;
    @BindView(R.id.rl_group)
    RelativeLayout rlGroup;
    @BindView(R.id.rl_private)
    RelativeLayout rlPrivate;
    @BindView(R.id.iv_image)
    CornerImageView ivImage;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_time)
    AppCompatTextView tvTime;
    @BindView(R.id.tv_location)
    AppCompatTextView tvLocation;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.iv_sex)
    AppCompatImageView ivSex;
    @BindView(R.id.iv_icon)
    AppCompatImageView ivIcon;
    @BindView(R.id.iv_avatar_a)
    CircleImageView ivAvatarA;
    @BindView(R.id.iv_avatar_b)
    CircleImageView ivAvatarB;
    @BindView(R.id.tv_content)
    AppCompatTextView tvContent;
    private String user_id;
    private GroupRelationBean groupRelationBean;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_im_private_chat;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(activity, ContextCompat.getColor(Utils.getApp(), R.color.color_111111), false);

        IMConnectViewModel imConnectViewModel = getViewModel(IMConnectViewModel.class);
        EventsViewModel eventsViewModel = getViewModel(EventsViewModel.class);

        Conversation.ConversationType mConversationType = Conversation.ConversationType.valueOf(getIntent().getData().getLastPathSegment().toUpperCase(Locale.US));
        user_id = getIntent().getData().getQueryParameter("targetId");
        tvBarTitle.setText(getIntent().getData().getQueryParameter("title"));
        enterFragment(mConversationType, user_id);//加载页面
        //判断是否为群聊还是单聊
        if (mConversationType.getName().equals("group")) {
            ivBarRight.setImageResource(R.drawable.icon_more);
            imConnectViewModel.getGroupRelation(user_id, "group");
        } else if (mConversationType.getName().equals("private")) {
            ivBarRight.setVisibility(View.GONE);
            //刷新好友头像
            imConnectViewModel.getImUserInfo(user_id);
            imConnectViewModel.getImUserInfo(SPUtils.getInstance().getString(SpConfig.USER_ID));
            imConnectViewModel.getGroupRelation(user_id, "private");
        }else {
            ivBarRight.setVisibility(View.GONE);
        }

        //设置会话页面操作监听
        RongIM.setConversationClickListener(this);

        //会话列表头像名称显示
        imConnectViewModel.imUserInfoLiveData.observe(this, imUserInfoBean -> {
            RongIM.getInstance().refreshUserInfoCache(new UserInfo(imUserInfoBean.getData().getUserId(), imUserInfoBean.getData().getNickname(), Uri.parse(imUserInfoBean.getData().getAvatar())));
        });

        //获取私聊或群聊来源或关系成功返回结果
        imConnectViewModel.groupRelationLiveData.observe(this, groupRelationBean -> {
            this.groupRelationBean = groupRelationBean;
            switch (groupRelationBean.getData().getReason()){
                case "event"://群聊
                    rlGroup.setVisibility(View.VISIBLE);
                    rlPrivate.setVisibility(View.GONE);
                    eventsViewModel.getEventsDetail(groupRelationBean.getData().getEvent().getObjectId());
                    break;
                case "matched"://单聊
                    rlGroup.setVisibility(View.GONE);
                    rlPrivate.setVisibility(View.VISIBLE);
                    ImageLoader.load(activity, new ImageConfig.Builder()
                            .url(groupRelationBean.getData().getMatch().getUser_a_image())
                            .imageView(ivAvatarA)
                            .placeholder(R.drawable.rc_default_portrait)
                            .errorPic(R.drawable.rc_default_portrait)
                            .build());
                    ImageLoader.load(activity, new ImageConfig.Builder()
                            .url(groupRelationBean.getData().getMatch().getUser_b_image())
                            .imageView(ivAvatarB)
                            .placeholder(R.drawable.rc_default_portrait)
                            .errorPic(R.drawable.rc_default_portrait)
                            .build());
                    tvContent.setText(groupRelationBean.getData().getMatch().getTitle());
                    break;
            }
        });

        //获取活动详情返回结果
        eventsViewModel.eventsDetailLiveData.observe(this, eventsDetailBean -> {
            if (TextUtils.isEmpty(eventsDetailBean.getData().getEvent().getEventImage())){
                ivImage.setVisibility(View.GONE);
            }else {
                ImageLoader.load(activity, new ImageConfig.Builder()
                        .url(eventsDetailBean.getData().getEvent().getEventImage())
                        .imageView(ivImage)
                        .placeholder(R.drawable.ic_empty_zhihu)
                        .errorPic(R.drawable.ic_empty_zhihu)
                        .build());
            }
            tvTitle.setText(eventsDetailBean.getData().getEvent().getEvent_contents());
            tvTime.setText(eventsDetailBean.getData().getEvent().getActivity_time().equals("2200-01-01T08:30:00.000Z") ? "随时" : NumberUtils.dealDateFormat(eventsDetailBean.getData().getEvent().getActivity_time()));
            tvLocation.setText(eventsDetailBean.getData().getEvent().getEvent_address());
            ImageLoader.load(activity, new ImageConfig.Builder()
                    .url(eventsDetailBean.getData().getEvent().getCreator_image())
                    .imageView(ivAvatar)
                    .placeholder(R.drawable.rc_default_portrait)
                    .errorPic(R.drawable.rc_default_portrait)
                    .build());
            tvName.setText(eventsDetailBean.getData().getEvent().getCreator_name());
            ivSex.setImageResource(eventsDetailBean.getData().getEvent().getCreator_sex().equals("male") ? R.drawable.icon_sex_male : R.drawable.icon_sex_female);
            EventsUtils.getEventsIcon(ivIcon, ivIcon, eventsDetailBean.getData().getEvent().getEventType(), eventsDetailBean.getData().getEvent().getEventTypeIcon());
        });
    }

    //加载会话页面
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {
        LogUtils.e("mTargetId" + mTargetId);
        ConversationFragment fragment = new ConversationFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();
        fragment.setUri(uri);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_im_contact_fragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
        if (conversationType == Conversation.ConversationType.CUSTOMER_SERVICE || conversationType == Conversation.ConversationType.PUBLIC_SERVICE
                || conversationType == Conversation.ConversationType.APP_PUBLIC_SERVICE || conversationType == Conversation.ConversationType.SYSTEM) {
            return false;
        }
        if (userInfo.getUserId() != null) {
            if (!userInfo.getUserId().equals(SPUtils.getInstance().getString(SpConfig.USER_ID))) {
                Bundle bundle = new Bundle();
                bundle.putString("id", userInfo.getUserId());
                bundle.putString("sex", "male");
                ActivityUtils.startActivity(bundle, UserInfoActivity.class) ;
            }
        }
        return true;
    }

    @Override
    public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
        return false;
    }

    @Override
    public boolean onMessageClick(Context context, View view, Message message) {
        return false;
    }

    @Override
    public boolean onMessageLinkClick(Context context, String s, Message message) {
        return false;
    }

    @Override
    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }

    @OnClick({R.id.iv_bar_back, R.id.iv_bar_right, R.id.ll_group})
    void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.iv_bar_right:
                bundle.putString("group_id", user_id);
                ActivityUtils.startActivity(bundle, IMGroupDetailsActivity.class);
                break;
            case R.id.ll_group://查看活动详情
                bundle.putString("id", groupRelationBean.getData().getEvent().getObjectId());
                bundle.putString("creator", groupRelationBean.getData().getEvent().getEventCreator());
                ActivityUtils.startActivity(bundle, EventsDetailActivity.class);
                break;
        }
    }
}
