package com.wankrfun.crania.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MeetListAdapter;
import com.wankrfun.crania.base.BaseFragment;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.dialog.AnimationDialog;
import com.wankrfun.crania.utils.SlideViewUtils;
import com.wankrfun.crania.view.meet.MineMatchingActivity;
import com.wankrfun.crania.viewmodel.MeetViewModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view
 * @ClassName: MeetFragment
 * @Description: 遇见
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 2:23 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 2:23 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetFragment extends BaseFragment {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_location)
    AppCompatTextView tvLocation;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    private MeetViewModel meetViewModel;

    @Override
    protected int initLayout() {
        return R.layout.fragment_meet;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState, View view) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.black));

        tvLocation.setText(SPUtils.getInstance().getString(SpConfig.CITY));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MeetListAdapter meetListAdapter = new MeetListAdapter();
        recyclerView.setAdapter(meetListAdapter);

        meetViewModel = mActivity.getViewModel(MeetViewModel.class);

        //获取遇见卡片列表返回结果
        meetViewModel.meetListLiveData.observe(this, meetListBean -> {
            tvBarTitle.setText("今日剩余喜欢: " + meetListBean.getData().getAllowedToday());
            meetListAdapter.setNewData(meetListBean.getData().getList());
            if (meetListBean.getData().getList().size() != 0){
                rlEmpty.setVisibility(View.GONE);
            }else {
                rlEmpty.setVisibility(View.VISIBLE);
            }
            SlideViewUtils.getSlideResult(recyclerView, meetListBean.getData().getList(), rlEmpty, meetListAdapter, meetViewModel);
        });

        //手势操作: 喜欢或不喜欢成功返回结果
        meetViewModel.meetUserCardLiveData.observe(this, eventsCreateBean -> {
            meetViewModel.getMeetList();
            if (!TextUtils.isEmpty(SPUtils.getInstance().getString(SpConfig.CITY))){
                meetViewModel.getMeetUploadCard();
            }

            //匹配成功 弹出匹配成功动画弹窗
            if (eventsCreateBean.getData().isMatching()){
                AnimationDialog animationDialog = new AnimationDialog(mActivity, "matching", eventsCreateBean.getData().getImage());
                animationDialog.showDialog();
            }
        });
    }

    @Override
    protected void initDataFromService() {
        meetViewModel.getMeetList();
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString(SpConfig.CITY))){
            meetViewModel.getMeetUploadCard();
        }
    }

    @OnClick({R.id.iv_tab_right})
    void onClick() {
        ActivityUtils.startActivity(MineMatchingActivity.class);
    }
}
