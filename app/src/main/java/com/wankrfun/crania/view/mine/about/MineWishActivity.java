package com.wankrfun.crania.view.mine.about;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineWishAdapter;
import com.wankrfun.crania.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineWishActivity
 * @Description: 我的心愿列表页面
 * @Author: 鹿鸿祥
 * @CreateDate: 3/22/21 10:28 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/22/21 10:28 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineWishActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_wish;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        list.add("想要练出鱼人线");
        list.add("想吃北京的糖葫芦");
        list.add("更多");

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        MineWishAdapter mineWishAdapter  = new MineWishAdapter(R.layout.adapter_mine_wish, list);
        recyclerView.setAdapter(mineWishAdapter);

        mineWishAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mineWishAdapter.getData().get(position).equals("更多")){
                ActivityUtils.startActivity(MineWishAddActivity.class);
            }else {
                ToastUtils.showShort(mineWishAdapter.getData().get(position));
            }
        });
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
