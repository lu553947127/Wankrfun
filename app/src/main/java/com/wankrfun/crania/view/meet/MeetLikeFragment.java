package com.wankrfun.crania.view.meet;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineMatchingAdapter;
import com.wankrfun.crania.base.BaseLazyFragment;
import com.wankrfun.crania.bean.MatchingListBean;
import com.wankrfun.crania.dialog.AnimationDialog;
import com.wankrfun.crania.viewmodel.MeetViewModel;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.meet
 * @ClassName: MeetLikeFragment
 * @Description: 谁喜欢我fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 4/7/21 1:19 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/7/21 1:19 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetLikeFragment extends BaseLazyFragment {
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private MeetViewModel meetViewModel;

    @Override
    protected int initLayout() {
        return R.layout.fragment_meet_like;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        MineMatchingAdapter mineMatchingAdapter = new MineMatchingAdapter(R.layout.adapter_mine_matching, null);
        recyclerView.setAdapter(mineMatchingAdapter);

        meetViewModel = mActivity.getViewModel(MeetViewModel.class);

        //获取匹配列表数据返回结果
        meetViewModel.whoLikeMeListLiveData.observe(this, eventsEvent -> {
            mineMatchingAdapter.setNewData(eventsEvent.getList());
            mineMatchingAdapter.setMapList(eventsEvent.getMapList());
            mineMatchingAdapter.setType("like");
        });

        //手势操作: 喜欢或不喜欢成功返回结果
        meetViewModel.meetUserCardLiveData.observe(this, eventsCreateBean -> {
            meetViewModel.getWhoLikeMe();

            //匹配成功 弹出匹配成功动画弹窗
            if (eventsCreateBean.getData().isMatching()){
                AnimationDialog animationDialog = new AnimationDialog(mActivity, "matching", eventsCreateBean.getData().getImage());
                animationDialog.showDialog();
            }
        });

        refresh.setEnableLoadMore(false);
        refresh.setOnRefreshListener(refreshLayout -> {
            meetViewModel.getWhoLikeMe();
            refreshLayout.finishRefresh(1000);
        });
    }

    @Override
    protected void initDataFromService() {
        meetViewModel.getWhoLikeMe();
    }

    /**
     * 点击喜欢按钮去匹配成功
     * @param event
     */
    @Subscribe
    public void onOperateLike(MatchingListBean event) {
        meetViewModel.getMeetOperateLike(event.getObjectId(), "LIKE", event.getPhoto(), "");
    }
}
