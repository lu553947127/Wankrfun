package com.wankrfun.crania.view.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineCompleteAdapter;
import com.wankrfun.crania.adapter.MineInitiateAdapter;
import com.wankrfun.crania.base.BaseLazyFragment;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.UserEventsListBean;
import com.wankrfun.crania.view.events.EventsDetailActivity;
import com.wankrfun.crania.viewmodel.MineViewModel;

import butterknife.BindView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: MineFavFragment
 * @Description: 我想去的fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 2/25/21 9:17 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/25/21 9:17 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineFavFragment extends BaseLazyFragment {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.rv_complete)
    RecyclerView rvComplete;
    @BindView(R.id.ll_complete)
    LinearLayout llComplete;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @BindView(R.id.tv_empty)
    AppCompatTextView tvEmpty;
    AppCompatTextView tvFav;
    private MineViewModel mineViewModel;

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine_initiate;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        tvFav = mActivity.findViewById(R.id.tv_fav);
        mineViewModel = mActivity.getViewModel(MineViewModel.class);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        MineInitiateAdapter mineInitiateAdapter = new MineInitiateAdapter(R.layout.adapter_mine_initiate, null);
        recyclerView.setAdapter(mineInitiateAdapter);

        mineInitiateAdapter.setOnItemClickListener((adapter, view, position) -> {
            UserEventsListBean.DataBean.ActiveListBean listBean = mineInitiateAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.getObjectId());
            bundle.putString("creator", listBean.getEventCreator());
            ActivityUtils.startActivity(bundle, EventsDetailActivity.class);
        });

        rvComplete.setLayoutManager(new LinearLayoutManager(mContext));
        MineCompleteAdapter mineCompleteAdapter = new MineCompleteAdapter(R.layout.adapter_mine_complete, null);
        rvComplete.setAdapter(mineCompleteAdapter);

        //获取用户发布的活动信息
        mineViewModel.userEventsListFavLiveData.observe(this, userEventsListBean -> {
            tvFav.setText("我的想去 • " + (userEventsListBean.getUserEventsListBean().getData().getTotal()));
            if (userEventsListBean.getUserEventsListBean().getData().getActiveList().size() == 0 && userEventsListBean.getList().size() == 0){
                tvEmpty.setText("你还没有想去的活动");
                rlEmpty.setVisibility(View.VISIBLE);
                llComplete.setVisibility(View.GONE);
            }else {
                rlEmpty.setVisibility(View.GONE);
                if (userEventsListBean.getList().size() == 0){
                    llComplete.setVisibility(View.GONE);
                }else {
                    llComplete.setVisibility(View.VISIBLE);
                }
            }
            mineInitiateAdapter.setNewData(userEventsListBean.getUserEventsListBean().getData().getActiveList());
            mineCompleteAdapter.setNewData(userEventsListBean.getList());
            mineCompleteAdapter.setMapList(userEventsListBean.getMapList());
        });
    }

    @Override
    protected void initDataFromService() {
        mineViewModel.getUserEventsListFav(SPUtils.getInstance().getString(SpConfig.USER_ID));
    }
}
