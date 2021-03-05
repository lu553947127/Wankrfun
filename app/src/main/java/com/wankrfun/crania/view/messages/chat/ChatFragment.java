package com.wankrfun.crania.view.messages.chat;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.ConversationListAdapterEx;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseLazyFragment;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.view.meet.MineMatchingActivity;
import com.wankrfun.crania.view.mine.UserInfoActivity;
import com.wankrfun.crania.viewmodel.IMConnectViewModel;

import java.util.Objects;

import butterknife.OnClick;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.messages
 * @ClassName: ChatFragment
 * @Description: 聊天fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 1/20/21 11:19 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/20/21 11:19 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChatFragment extends BaseLazyFragment {
    private IMConnectViewModel imConnectViewModel;
    @Override
    protected int initLayout() {
        return R.layout.fragment_chat;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        FragmentManager fragmentManage = getChildFragmentManager();
        ConversationListFragment conversationListFragment = (ConversationListFragment) fragmentManage.findFragmentById(R.id.conversationlist);
        ConversationListAdapterEx adapterEx = new ConversationListAdapterEx(RongContext.getInstance());
        Objects.requireNonNull(conversationListFragment).setAdapter(adapterEx);
        Uri uri = Uri.parse("rong://" + MyApplication.getInstance().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")//设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//系统
                .build();
        conversationListFragment.setUri(uri);

        imConnectViewModel = mActivity.getViewModel(IMConnectViewModel.class);

        //会话列表热暖头像名称显示
        imConnectViewModel.imUserInfoLiveData.observe(this, imUserInfoBean -> {
            RongIM.getInstance().refreshUserInfoCache(new UserInfo(imUserInfoBean.getData().getUserId(), imUserInfoBean.getData().getNickname(), Uri.parse(imUserInfoBean.getData().getAvatar())));
        });

        //会话列表群组头像名称显示
        imConnectViewModel.imGroupInfoLiveData.observe(this,imGroupInfoBean -> {
//            Group groupInfo = new Group(imGroupInfoBean.getData().getGroupId()
//                    , imGroupInfoBean.getData().getGroupName() + "(" + imGroupInfoBean.getData().getMembersNum() + ")"
//                    , Uri.parse(imGroupInfoBean.getData().getGroupImage()));
            Group groupInfo = new Group(imGroupInfoBean.getData().getGroupId(), imGroupInfoBean.getData().getGroupName(), Uri.parse(imGroupInfoBean.getData().getGroupImage()));
            RongIM.getInstance().refreshGroupInfoCache(groupInfo);
        });

        //根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK
        RongIM.setUserInfoProvider(s -> imConnectViewModel.getImUserInfo(s), true);
        //设置群聊列表数据
        RongIM.setGroupInfoProvider(s -> imConnectViewModel.getImGroupInfo(s), true);

        //设置会话列表头像点击监听
        adapterEx.setOnPortraitItemClick(new ConversationListAdapter.OnPortraitItemClick() {
            @Override
            public void onPortraitItemClick(View view, UIConversation uiConversation) {
                String targetId = uiConversation.getConversationTargetId();
                Conversation.ConversationType conversationType = uiConversation.getConversationType();
                if (targetId != null) {
                    if (conversationType == Conversation.ConversationType.PRIVATE){
                        if (!targetId.equals(SPUtils.getInstance().getString(SpConfig.USER_ID))) {
                            Bundle bundle = new Bundle();
                            bundle.putString("id", targetId);
                            bundle.putString("sex", "male");
                            ActivityUtils.startActivity(bundle, UserInfoActivity.class) ;
                        }
                    }else if (conversationType == Conversation.ConversationType.GROUP){
                        Bundle bundle = new Bundle();
                        bundle.putString("group_id", targetId);
                        ActivityUtils.startActivity(bundle, IMGroupDetailsActivity.class);
                    }
                }
            }

            @Override
            public boolean onPortraitItemLongClick(View view, UIConversation uiConversation) {
                return false;
            }
        });
    }

    @Override
    protected void initDataFromService() {

    }

    @OnClick({R.id.rl_list})
    void onClick() {
        ActivityUtils.startActivity(MineMatchingActivity.class);
    }
}
