package com.wankrfun.crania.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.EventsAdapter;
import com.wankrfun.crania.adapter.EventsTypeAdapter;
import com.wankrfun.crania.base.BaseFragment;
import com.wankrfun.crania.bean.EventsBean;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.event.LocationEvent;
import com.wankrfun.crania.utils.LocationUtils;
import com.wankrfun.crania.utils.ParseUtils;
import com.wankrfun.crania.utils.RefreshUtils;
import com.wankrfun.crania.utils.ScrollUtils;
import com.wankrfun.crania.view.events.EventsAddActivity;
import com.wankrfun.crania.view.events.EventsDetailActivity;
import com.wankrfun.crania.viewmodel.EventsViewModel;
import com.wankrfun.crania.widget.AdaptationScrollView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view
 * @ClassName: HomeFragment
 * @Description: 首页fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 2:22 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 2:22 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.scroll)
    AdaptationScrollView scroll;
    @BindView(R.id.tv_location)
    AppCompatTextView tvLocation;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_user_name)
    AppCompatTextView tvUserName;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv_type)
    RecyclerView recyclerViewType;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_add)
    AppCompatImageView ivAdd;
    private EventsViewModel eventsViewModel;
    private final List<String> strList = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState, View view1) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.black));

        //滑动监听让添加按钮动画隐藏和显示
        ScrollUtils.OnScrollChangeListener(scroll, ivAdd);

        tvUserName.setText(ParseUtils.getUserName());

        recyclerViewType.setLayoutManager(new LinearLayoutManager(mActivity ,LinearLayoutManager.HORIZONTAL,false));
        EventsTypeAdapter eventsTypeAdapter = new EventsTypeAdapter(R.layout.adapter_events_type, RefreshUtils.getActivityListNot());
        recyclerViewType.setAdapter(eventsTypeAdapter);

        eventsTypeAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (!eventsTypeAdapter.getData().get(position).isCheckState()){
                eventsTypeAdapter.getData().get(position).setCheckState(true);
                strList.add(String.valueOf(position));
            }else {
                eventsTypeAdapter.getData().get(position).setCheckState(false);
                strList.remove(0);
            }
            eventsTypeAdapter.notifyDataSetChanged();
            eventsViewModel.getEventsList(strList);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        EventsAdapter eventsAdapter  = new EventsAdapter(R.layout.adapter_events, null);
        eventsAdapter.setEmptyView(R.layout.layout_loading, recyclerView);
        recyclerView.setAdapter(eventsAdapter);
        eventsAdapter.setOnItemClickListener((adapter, view, position) -> {
            EventsBean.DataBean.ListBean listBean = eventsAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.getObjectId());
            bundle.putString("creator", listBean.getEventCreator());
            ActivityUtils.startActivity(bundle, EventsDetailActivity.class);
        });

        eventsViewModel = mActivity.getViewModel(EventsViewModel.class);

        //获取活动列表返回结果
        eventsViewModel.eventsLiveData.observe(this, eventsBean -> {
            if (eventsViewModel.page == 0) {
                eventsAdapter.setNewData(eventsBean.getData().getList());
                eventsAdapter.setEmptyView(R.layout.layout_empty, recyclerView);
            } else {
                eventsAdapter.addData(eventsAdapter.getData().size(), eventsBean.getData().getList());
            }
            RefreshUtils.setNoMore(smartRefreshLayout,eventsViewModel.page, eventsBean.getData().getTotal());
        });

        //刷新和加载
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                eventsViewModel.getEventsList(strList);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                eventsViewModel.getEventsListMore();
            }
        });
    }

    @Override
    protected void initDataFromService() {
        LocationUtils.getInstance().startLocalService();
    }

    @OnClick({R.id.tv_title, R.id.iv_date, R.id.rl_bubbling, R.id.iv_add})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title://切换标题
                new XPopup.Builder(mActivity)
                        .hasShadowBg(false)
                        .atView(tvTitle)
                        .asAttachList(new String[]{getString(R.string.home_release), getString(R.string.home_start), getString(R.string.home_distance)}, null,
                                (position, text) -> {
                                    switch (text){
                                        case "最新发布":
                                            eventsViewModel.menu = "NEWEST";
                                            break;
                                        case "最快开始":
                                            eventsViewModel.menu = "FAST";
                                            break;
                                        case "最快距离":
                                            eventsViewModel.menu = "CLOSEST";
                                            break;
                                    }
                                    tvTitle.setText(text);
                                    eventsViewModel.getEventsList(strList);
                                }).show();
                break;
            case R.id.iv_date://签到（任务中心）

                break;
            case R.id.rl_bubbling://冒泡入口
                ToastUtils.showShort("抱歉，敬请期待");
                break;
            case R.id.iv_add://活动添加
                ActivityUtils.startActivity(EventsAddActivity.class);
                break;
        }
    }

    /**
     * 定位成功，加载活动列表
     * @param event
     */
    @Subscribe
    public void onEventLocation(LocationEvent event) {
        tvLocation.setText(event.getCity());
        eventsViewModel.latitude = event.getLatitude();
        eventsViewModel.longitude = event.getLongitude();
        eventsViewModel.getEventsList(strList);
    }

    /**
     * 活动创建成功，刷新活动列表
     * @param event
     */
    @Subscribe
    public void onEventEvents(EventsEvent event) {
        eventsViewModel.getEventsList(strList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance().stopLocalService();
    }
}
