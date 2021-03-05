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
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineCompleteAdapter;
import com.wankrfun.crania.adapter.MineInitiateAdapter;
import com.wankrfun.crania.base.BaseLazyFragment;
import com.wankrfun.crania.bean.UserEventsListBean;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.view.events.EventsDetailActivity;
import com.wankrfun.crania.viewmodel.MineViewModel;

import org.greenrobot.eventbus.Subscribe;
import butterknife.BindView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: MineInitiateFragment
 * @Description: 我的发布fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 9:54 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 9:54 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineInitiateFragment extends BaseLazyFragment {
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
    AppCompatTextView tvInitiate;
    private MineViewModel mineViewModel;
    private final String id, type, sex;

    public MineInitiateFragment(String id, String type, String sex){
        this.id = id;
        this.type = type;
        this.sex = sex;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine_initiate;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        tvInitiate = mActivity.findViewById(R.id.tv_initiate);
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
        mineViewModel.userEventsListCreatedLiveData.observe(this, userEventsListBean -> {
            if (type.equals("mine")){
                tvInitiate.setText("我的发起 • " + (userEventsListBean.getUserEventsListBean().getData().getTotal()));
            }else {
                tvInitiate.setText(sex.equals("male") ? "他的发起 • " + (userEventsListBean.getUserEventsListBean().getData().getTotal())
                        : "她的发起 • " + (userEventsListBean.getUserEventsListBean().getData().getTotal()));
            }
            if (userEventsListBean.getUserEventsListBean().getData().getActiveList().size() == 0 && userEventsListBean.getList().size() == 0){
                if (type.equals("mine")){
                    tvEmpty.setText("你还没有发起的活动");
                }else {
                    tvEmpty.setText("TA还没有发起的活动");
                }
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
        mineViewModel.getUserEventsListCreated(id);
    }

    /**
     * 活动创建成功，刷新活动列表
     * @param event
     */
    @Subscribe
    public void onEventEvents(EventsEvent event) {
        if (type.equals("mine")){
            mineViewModel.getUserEventsListCreated(id);
        }
    }
}
