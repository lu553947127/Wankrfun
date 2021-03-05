package com.wankrfun.crania.view.login.first;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.FirstSetExpectAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.utils.RefreshUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.login.first
 * @ClassName: FirstSetExpectActivity
 * @Description: 设置玩趣相投 期待页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 9:48 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 9:48 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetExpectActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_expect;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2, LinearLayoutManager.VERTICAL, false));
        FirstSetExpectAdapter firstSetExpectAdapter = new FirstSetExpectAdapter(R.layout.adapter_first_set_expect, RefreshUtils.getExpectList());
        recyclerView.setAdapter(firstSetExpectAdapter);

        firstSetExpectAdapter.setOnItemClickListener((adapter, view, position) -> {
            SPUtils.getInstance().put(SpConfig.FIRST_TAG, firstSetExpectAdapter.getData().get(position).getName(), true);
            firstSetExpectAdapter.setIsSelect(firstSetExpectAdapter.getData().get(position).getName());
            ActivityUtils.startActivity(FirstSetTypeActivity.class);
        });
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
