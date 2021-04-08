package com.wankrfun.crania.view.mine.about;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineEventsAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

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
    private List<BaseBean> list = new ArrayList<>();

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

        list.add(new BaseBean(0));
        list.add(new BaseBean(0));
        list.add(new BaseBean(0));
        list.add(new BaseBean(0));
        list.add(new BaseBean(0));
        list.add(new BaseBean(0));

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        MineEventsAdapter mineEventsAdapter = new MineEventsAdapter(R.layout.adapter_mine_events, list);
        recyclerView.setAdapter(mineEventsAdapter);

        mineEventsAdapter.setOnItemClickListener((adapter, view, position) -> {
            ActivityUtils.startActivity(MineEventsAddActivity.class);
        });
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
