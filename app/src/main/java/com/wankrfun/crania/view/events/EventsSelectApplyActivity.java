package com.wankrfun.crania.view.events;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.EventsSelectApplyAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.EventsParticipantsBean;
import com.wankrfun.crania.view.mine.UserInfoActivity;
import com.wankrfun.crania.viewmodel.EventsViewModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.events
 * @ClassName: EventsSelectApplyActivity
 * @Description: 选择申请人/查看参与人页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2/7/21 10:34 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/7/21 10:34 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsSelectApplyActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_events_select_apply;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText(TextUtils.isEmpty(getIntent().getStringExtra("type")) ? "参与人" : "申请人");

        EventsViewModel eventsViewModel = getViewModel(EventsViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        EventsSelectApplyAdapter eventsSelectApplyAdapter = new EventsSelectApplyAdapter(R.layout.adapter_events_select_apply, null, getIntent().getStringExtra("type"));
        recyclerView.setAdapter(eventsSelectApplyAdapter);

        eventsSelectApplyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            EventsParticipantsBean.DataBean.ParticipantsBean listBean = eventsSelectApplyAdapter.getData().get(position);
            switch (view.getId()){
                case R.id.iv_avatar://查看个人详情
                    Bundle bundle = new Bundle();
                    bundle.putString("id", listBean.getObjectId());
                    bundle.putString("sex", listBean.getSex());
                    ActivityUtils.startActivity(bundle, UserInfoActivity.class);
                    break;
                case R.id.tv_adopt://通过活动申请
                    eventsViewModel.getEventsAccept(getIntent().getStringExtra("creator"), listBean.getObjectId(), listBean.getEventId());
                    break;
            }
        });

        //获取申请人列表数据返回
        eventsViewModel.eventsParticipantsLiveData.observe(this, eventsParticipantsBean -> {
            eventsSelectApplyAdapter.setNewData(eventsParticipantsBean.getData().getParticipants());
        });

        //获取参与人列表数据返回
        eventsViewModel.eventsSponsorLiveData.observe(this, eventsParticipantsBean -> {
            eventsSelectApplyAdapter.setNewData(eventsParticipantsBean.getData().getParticipants());
        });

        //申请人通过成功返回结果
        eventsViewModel.eventsAcceptLiveData.observe(this, eventsAcceptBean -> {
            ToastUtils.showShort(eventsAcceptBean.getData().getMsg());
            eventsViewModel.getEventsParticipants(getIntent().getStringExtra("id"));
        });

        if (TextUtils.isEmpty(getIntent().getStringExtra("type"))){
            eventsViewModel.getEventsSponsor(getIntent().getStringExtra("id"));
        }else {
            eventsViewModel.getEventsParticipants(getIntent().getStringExtra("id"));
        }

    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
