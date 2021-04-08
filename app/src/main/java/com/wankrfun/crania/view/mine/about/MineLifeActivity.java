package com.wankrfun.crania.view.mine.about;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineLifeTypeAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineLifeActivity
 * @Description: 我的生活类型选择页面
 * @Author: 鹿鸿祥
 * @CreateDate: 3/23/21 10:00 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/23/21 10:00 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineLifeActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private List<BaseBean> list = new ArrayList<>();

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_life;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        list.add(new BaseBean("心愿", R.drawable.icon_mine_life));
        list.add(new BaseBean("宅家", R.drawable.icon_mine_life2));
        list.add(new BaseBean("玩耍", R.drawable.icon_mine_life3));
        list.add(new BaseBean("穿搭", R.drawable.icon_activity_food));
        list.add(new BaseBean("宠物", R.drawable.icon_mine_life5));
        list.add(new BaseBean("美食", R.drawable.icon_mine_life6));
        list.add(new BaseBean("风景", R.drawable.icon_mine_life7));
        list.add(new BaseBean("其他", R.drawable.icon_mine_life8));

        recyclerView.setLayoutManager(new GridLayoutManager(activity, 4, LinearLayoutManager.VERTICAL, false));
        MineLifeTypeAdapter mineLifeTypeAdapter = new MineLifeTypeAdapter(R.layout.adapter_mine_life_type, list);
        recyclerView.setAdapter(mineLifeTypeAdapter);

        mineLifeTypeAdapter.setOnItemClickListener((adapter, view, position) -> {
            BaseBean baseBean = mineLifeTypeAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putInt("image", baseBean.getImages());
            bundle.putString("title", baseBean.getName());
            ActivityUtils.startActivity(bundle, MineLifeAddActivity.class);
        });
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
