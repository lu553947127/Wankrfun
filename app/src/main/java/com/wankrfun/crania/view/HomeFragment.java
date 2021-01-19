package com.wankrfun.crania.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.lxj.xpopup.XPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.EventsAdapter;
import com.wankrfun.crania.adapter.EventsTypeAdapter;
import com.wankrfun.crania.base.BaseFragment;
import com.wankrfun.crania.event.LocationEvent;
import com.wankrfun.crania.utils.LocationUtils;
import com.wankrfun.crania.utils.RefreshUtils;
import com.wankrfun.crania.viewmodel.EventsViewModel;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

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
    @BindView(R.id.tv_location)
    AppCompatTextView tvLocation;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv_type)
    RecyclerView recyclerViewType;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private Map<Integer, String> eventsMap = new HashMap<>();
    private EventsViewModel eventsViewModel;

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
//        new XPopup.Builder(mActivity).dismissOnBackPressed(false).dismissOnTouchOutside(false).asCustom(new WantPlayDialog(mActivity)).show();

        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.black));

        recyclerViewType.setLayoutManager(new LinearLayoutManager(mActivity ,LinearLayoutManager.HORIZONTAL,false));
        EventsTypeAdapter eventsTypeAdapter = new EventsTypeAdapter(R.layout.adapter_events_type, RefreshUtils.getActivityList());
        recyclerViewType.setAdapter(eventsTypeAdapter);

        eventsTypeAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (!eventsTypeAdapter.getData().get(position).isCheckState()){
                eventsTypeAdapter.getData().get(position).setCheckState(true);
                eventsMap.put(position, eventsTypeAdapter.getData().get(position).getName());
            }else {
                eventsTypeAdapter.getData().get(position).setCheckState(false);
                eventsMap.remove(position);
            }
            eventsTypeAdapter.notifyDataSetChanged();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        EventsAdapter eventsAdapter  = new EventsAdapter(R.layout.adapter_events, RefreshUtils.getActivityList());
//        eventsAdapter.setEmptyView(R.layout.layout_empty, recyclerView);
        recyclerView.setAdapter(eventsAdapter);

        eventsViewModel = mActivity.getViewModel(EventsViewModel.class);
    }

    @Override
    protected void initDataFromService() {
        LocationUtils.getInstance().startLocalService();
    }

    @OnClick({R.id.tv_title,R.id.iv_date,R.id.rl_bubbling})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title://切换标题
                new XPopup.Builder(mActivity)
                        .hasShadowBg(false)
                        .atView(tvTitle)
                        .asAttachList(new String[]{getString(R.string.home_release), getString(R.string.home_start), getString(R.string.home_distance)}, null,
                                (position, text) -> {
                                    tvTitle.setText(text);
                                }).show();
                break;
            case R.id.iv_date://签到（任务中心）
                if (eventsMap.size() != 0){
                    StringBuilder matchBuilder = new StringBuilder();
                    StringBuilder matchIdBuilder = new StringBuilder();
                    for (Map.Entry<Integer, String> vo : eventsMap.entrySet()) {
                        matchBuilder.append(vo.getValue()).append(",");
                        matchIdBuilder.append(vo.getKey()).append(",");
                    }

                    LogUtils.e(matchIdBuilder.substring(0, matchIdBuilder.length() - 1));
                }
                break;
            case R.id.rl_bubbling://冒泡入口

                break;
        }
    }

    @Subscribe
    public void onEventLocation(LocationEvent event) {
        tvLocation.setText(event.getCity());
        eventsViewModel.getEventsList(event.getLongitude(),event.getLatitude());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance().stopLocalService();
    }
}
