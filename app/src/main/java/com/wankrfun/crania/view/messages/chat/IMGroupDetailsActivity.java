package com.wankrfun.crania.view.messages.chat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.IMGroupMemberAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.ImGroupMembersBean;
import com.wankrfun.crania.view.mine.user.UserInfoActivity;
import com.wankrfun.crania.viewmodel.IMConnectViewModel;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.messages.chat
 * @ClassName: IMGroupDetailsActivity
 * @Description: 群聊详情页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2/19/21 4:31 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/19/21 4:31 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class IMGroupDetailsActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.sv_not)
    SwitchCompat svNot;
    private IMConnectViewModel imConnectViewModel;
    //当前群主id
    private String groupLeader;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_im_group_details;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));

        recyclerView.setLayoutManager(new LinearLayoutManager(activity ,LinearLayoutManager.HORIZONTAL,false));
        IMGroupMemberAdapter imGroupMemberAdapter = new IMGroupMemberAdapter(R.layout.adapter_events_sponsor, null, "");
        recyclerView.setAdapter(imGroupMemberAdapter);

        imGroupMemberAdapter.setOnItemClickListener((adapter, view, position) -> {
            ImGroupMembersBean.DataBean.MembersBean listBean = imGroupMemberAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.getUserId());
            bundle.putString("sex", listBean.getSex());
            ActivityUtils.startActivity(bundle, UserInfoActivity.class) ;
        });

        imConnectViewModel = getViewModel(IMConnectViewModel.class);
        
        //获取活动详情人员列表返回结果
        imConnectViewModel.imGroupMembersLiveData.observe(this, imGroupMembersBean -> {
            imGroupMemberAdapter.setNewData(imGroupMembersBean.getData().getMembers());
            groupLeader = imGroupMembersBean.getData().getMembers().get(0).getUserId();
        });

        //会话列表群组头像名称显示
        imConnectViewModel.imGroupInfoLiveData.observe(this,imGroupInfoBean -> {
            tvBarTitle.setText(imGroupInfoBean.getData().getGroupName());
            tvName.setText(imGroupInfoBean.getData().getGroupName());
        });

        //修改群聊昵称成功返回结果
        imConnectViewModel.imEditGroupLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort("修改成功");
            imConnectViewModel.getImGroupInfo(getIntent().getStringExtra("group_id"));
        });

        //退出群聊成功返回结果
        imConnectViewModel.imOutGroupLiveData.observe(this, eventsCreateBean -> {
//            RongIM.getInstance().removeConversation(Conversation.ConversationType.GROUP, getIntent().getStringExtra("group_id"), new RongIMClient.ResultCallback<Boolean>() {
//                @Override
//                public void onSuccess(Boolean aBoolean) {
//                    ToastUtils.showShort(eventsCreateBean.getData().getMsg());
//                    ActivityUtils.finishActivity(IMPrivateChatActivity.class);
//                    finish();
//                }
//
//                @Override
//                public void onError(RongIMClient.ErrorCode e) {
//
//                }
//            });
        });

        imConnectViewModel.getImGroupMembers(getIntent().getStringExtra("group_id"));
        imConnectViewModel.getImGroupInfo(getIntent().getStringExtra("group_id"));

        //获取会话提醒状态
        RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP, getIntent().getStringExtra("group_id"), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {

                if (conversationNotificationStatus == Conversation.ConversationNotificationStatus.DO_NOT_DISTURB) {
                    svNot.setChecked(true);
                } else if (conversationNotificationStatus == Conversation.ConversationNotificationStatus.NOTIFY){
                    svNot.setChecked(false);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.e(errorCode.getValue()+errorCode.getMessage());
            }
        });
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_more, R.id.tv_name, R.id.tv_exit})
    void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_more://查看更多
                bundle.putString("group_id", getIntent().getStringExtra("group_id"));
                ActivityUtils.startActivity(bundle, IMGroupMemberActivity.class);
                break;
            case R.id.tv_name://修改群名称
                new XPopup.Builder(activity)
                        .autoOpenSoftInput(true)
                        .isRequestFocus(false)
                        .asInputConfirm("修改群昵称", "", getIntent().getStringExtra("title"), "请输入群昵称", text -> {
                            if (TextUtils.isEmpty(text)){
                                ToastUtils.showShort("群聊名称输入不能为空哦");
                                return;
                            }
                            imConnectViewModel.getGroupUpdateName(getIntent().getStringExtra("group_id"), SPUtils.getInstance().getString(SpConfig.USER_ID), text);
                        }).show();
                break;
            case R.id.tv_exit://退出群聊
                new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "确认要退出群聊吗？", () -> {
                    if (!TextUtils.isEmpty(groupLeader) && groupLeader.equals(SPUtils.getInstance().getString(SpConfig.USER_ID))){
                        ToastUtils.showShort("抱歉，您不能退出群聊哦");
                        return;
                    }
                    imConnectViewModel.getOutGroup(getIntent().getStringExtra("group_id"), SPUtils.getInstance().getString(SpConfig.USER_ID), true);
                }).show();
                break;
        }
    }

    @OnCheckedChanged({R.id.sv_not})
    void OnOnCheckedChanged(CompoundButton view, boolean isChecked) {
        if (isChecked) {
            svNot.setChecked(true);
            setConversationNotification(Conversation.ConversationType.GROUP,getIntent().getStringExtra("group_id"),true);
        } else {
            svNot.setChecked(false);
            setConversationNotification(Conversation.ConversationType.GROUP,getIntent().getStringExtra("group_id"),false);
        }
    }

    /**
     * 设置消息免打扰
     *
     * @param conversationType 会话类型
     * @param targetId 群聊id
     * @param state 是否设置免打扰
     */
    public static void setConversationNotification( Conversation.ConversationType conversationType, String targetId, boolean state) {
        Conversation.ConversationNotificationStatus cns;
        if (state) {
            cns = Conversation.ConversationNotificationStatus.DO_NOT_DISTURB;
        } else {
            cns = Conversation.ConversationNotificationStatus.NOTIFY;
        }
        RongIM.getInstance().setConversationNotificationStatus(conversationType, targetId, cns, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                if (conversationNotificationStatus == Conversation.ConversationNotificationStatus.DO_NOT_DISTURB) {
                    LogUtils.e("设置免打扰成功");
                } else if (conversationNotificationStatus == Conversation.ConversationNotificationStatus.NOTIFY) {
                    LogUtils.e("取消免打扰成功");
                }

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.e("设置失败");
            }
        });
    }
}
