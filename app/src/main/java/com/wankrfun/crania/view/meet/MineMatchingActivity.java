package com.wankrfun.crania.view.meet;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineMatchingAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.viewmodel.MeetViewModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.meet
 * @ClassName: MineMatchingActivity
 * @Description: 我的匹配列表页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/29/21 11:25 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/29/21 11:25 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineMatchingActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private MeetViewModel meetViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_matching;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText("匹配列表");

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        MineMatchingAdapter mineMatchingAdapter = new MineMatchingAdapter(R.layout.adapter_mine_matching, null);
        mineMatchingAdapter.setEmptyView(R.layout.layout_empty, recyclerView);
        recyclerView.setAdapter(mineMatchingAdapter);

        meetViewModel = getViewModel(MeetViewModel.class);

        //获取匹配列表数据返回结果
        meetViewModel.matchingListLiveData.observe(this, eventsEvent -> {
            mineMatchingAdapter.setNewData(eventsEvent.getList());
            mineMatchingAdapter.setMapList(eventsEvent.getMapList());
        });

        meetViewModel.getMatchingList();

        refresh.setEnableLoadMore(false);
        refresh.setOnRefreshListener(refreshLayout -> {
            meetViewModel.getMatchingList();
            refreshLayout.finishRefresh(1000);
        });
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
