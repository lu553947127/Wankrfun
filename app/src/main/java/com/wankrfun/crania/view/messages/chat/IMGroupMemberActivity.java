package com.wankrfun.crania.view.messages.chat;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.IMGroupMemberAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.ImGroupMembersBean;
import com.wankrfun.crania.view.mine.user.UserInfoActivity;
import com.wankrfun.crania.viewmodel.IMConnectViewModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.messages.chat
 * @ClassName: IMGroupMemberActivity
 * @Description: 群聊详情人员列表页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2/23/21 10:52 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/23/21 10:52 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class IMGroupMemberActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private IMConnectViewModel imConnectViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_im_group_member;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText("群成员");

        recyclerView.setLayoutManager(new LinearLayoutManager(activity ,LinearLayoutManager.HORIZONTAL,false));
        IMGroupMemberAdapter imGroupMemberAdapter = new IMGroupMemberAdapter(R.layout.adapter_events_sponsor, null, "member");
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
        });

        imConnectViewModel.getImGroupMembers(getIntent().getStringExtra("group_id"));
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
