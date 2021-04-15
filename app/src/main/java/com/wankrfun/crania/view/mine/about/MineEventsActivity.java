package com.wankrfun.crania.view.mine.about;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineEventsAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.EventsJoinedListBean;
import com.wankrfun.crania.viewmodel.MineCardViewModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineEventsActivity
 * @Description: 活动瞬间选择活动页面
 * @Author: 鹿鸿祥
 * @CreateDate: 3/31/21 9:14 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/31/21 9:14 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineEventsActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_events;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        MineEventsAdapter mineEventsAdapter = new MineEventsAdapter(R.layout.adapter_mine_events, null);
        recyclerView.setAdapter(mineEventsAdapter);

        mineEventsAdapter.setOnItemClickListener((adapter, view, position) -> {
            EventsJoinedListBean.DataBean.ListBean listBean = mineEventsAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("bean", listBean);
            ActivityUtils.startActivity(bundle, MineEventsAddActivity.class);
        });

        MineCardViewModel mineCardViewModel = getViewModel(MineCardViewModel.class);

        //获取所有参与过的活动列表
        mineCardViewModel.eventsJoinedListLiveData.observe(this, eventsJoinedListBean -> {
            mineEventsAdapter.setNewData(eventsJoinedListBean.getData().getList());
        });

        mineCardViewModel.getEventsJoinedList();
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
