package com.wankrfun.crania.view.meet;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.BarUtils;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MeetChallengeAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.ChallengeListBean;
import com.wankrfun.crania.dialog.MeetChallengeDialog;
import com.wankrfun.crania.utils.DensityUtil;
import com.wankrfun.crania.viewmodel.MeetViewModel;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.meet
 * @ClassName: MeetChallengeActivity
 * @Description: 默契挑战页面
 * @Author: 鹿鸿祥
 * @CreateDate: 4/8/21 2:08 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/8/21 2:08 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetChallengeActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.tv_bar_right)
    AppCompatTextView tvBarRight;
    @BindView(R.id.tv_switch)
    AppCompatTextView tvSwitch;
    @BindView(R.id.tv_content)
    AppCompatTextView tvContent;
    @BindView(R.id.iv_state)
    AppCompatImageView ivState;
    @BindView(R.id.rv)
    SwipeRecyclerView recyclerView;
    //创建侧滑菜单
    private final SwipeMenuCreator creator = (leftMenu, rightMenu, position) -> {
        SwipeMenuItem deleteItem = new SwipeMenuItem(this)
                .setBackgroundColorResource(R.color.color_FF0000)
                .setText("删除")
                .setTextColor(Color.WHITE)
                .setTextSize(14)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                .setWidth(DensityUtil.dp2px(80));
        rightMenu.addMenuItem(deleteItem);
    };
    private boolean isSwitch;
    private MeetViewModel meetViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_meet_challenge;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText("默契挑战");

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        MeetChallengeAdapter meetChallengeAdapter  = new MeetChallengeAdapter(R.layout.adapter_meet_challenge, null);

        //设置侧滑菜单列表
        recyclerView.setSwipeMenuCreator(creator);
        //侧滑事件监听回调
        recyclerView.setOnItemMenuClickListener((menuBridge, adapterPosition) -> {
            if (menuBridge.getPosition() == 0) {//删除
                new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "您确定要删除吗", () -> {
                    meetViewModel.getChallengeDelete(meetChallengeAdapter.getData().get(adapterPosition).getObjectId());
                    meetChallengeAdapter.remove(adapterPosition);
                    menuBridge.closeMenu();
                }, () -> {
                    menuBridge.closeMenu();
                }).show();
            }
        });

        recyclerView.setAdapter(meetChallengeAdapter);

        meetChallengeAdapter.setOnItemClickListener((adapter, view, position) -> {
            ChallengeListBean.DataBean.RecordsBean listBean = meetChallengeAdapter.getData().get(position);
            new XPopup.Builder(activity)
                    .dismissOnTouchOutside(false)
                    .enableDrag(false)
                    .asCustom(new MeetChallengeDialog(activity, meetViewModel, listBean))
                    .show();
        });

        meetViewModel = getViewModel(MeetViewModel.class);

        //获取默契挑战列表数据
        meetViewModel.challengeListLiveData.observe(this, challengeListBean -> {
            meetChallengeAdapter.setNewData(challengeListBean.getData().getRecords());
            getChallengeState(challengeListBean.getData().getRecords());
        });

        //获取默契挑战开关状态返回结果
        meetViewModel.challengeStatusLiveData.observe(this, challengeStatusBean -> {
            isSwitch = challengeStatusBean.getData().isStatus();
            meetViewModel.getChallengeList();
            if (isSwitch){
                tvSwitch.setText("默契挑战已开启");
                tvBarRight.setText("暂停");
            }else {
                tvSwitch.setText("默契挑战已关闭");
                tvBarRight.setText("开启");
            }
        });

        //开启/关闭默契挑战返回结果
        meetViewModel.challengeOpenOfCloseLiveData.observe(this, challengeStatusBean -> {
            meetViewModel.getChallengeStatus();
        });

        //删除默契挑战卡片返回结果
        meetViewModel.challengeDeleteLiveData.observe(this, challengeStatusBean -> {
            meetViewModel.getChallengeList();
        });

        //创建默契挑战卡片成功
        meetViewModel.challengeCreateLiveData.observe(this, challengeStatusBean -> {
            meetViewModel.getChallengeList();
        });

        //编辑默契挑战卡片成功
        meetViewModel.challengeEditLiveData.observe(this, challengeStatusBean -> {
            meetViewModel.getChallengeList();
        });

        meetViewModel.getChallengeStatus();
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_bar_right, R.id.rl_next})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_bar_right:
                if (isSwitch){
                    meetViewModel.getChallengeOpenOfClose("off");
                }else {
                    meetViewModel.getChallengeOpenOfClose("on");
                }
                break;
            case R.id.rl_next:
                new XPopup.Builder(activity)
                        .dismissOnTouchOutside(false)
                        .enableDrag(false)
                        .asCustom(new MeetChallengeDialog(activity, meetViewModel, null))
                        .show();
                break;
        }
    }

    /**
     * 设置默契挑战状态
     */
    private void getChallengeState(List<ChallengeListBean.DataBean.RecordsBean> list) {
        if (isSwitch){
            switch (list.size()){
                case 0:
                    ivState.setImageResource(R.drawable.icon_meet_challenge1);
                    tvContent.setText("开启默契挑战，并补全3张默契卡片，让你脱颖而出吧");
                    break;
                case 1:
                    ivState.setImageResource(R.drawable.icon_meet_challenge2);
                    tvContent.setText("开启默契挑战，并补全2张默契卡片，让你脱颖而出吧");
                    break;
                case 2:
                    ivState.setImageResource(R.drawable.icon_meet_challenge3);
                    tvContent.setText("还差2张默契卡片，让你脱颖而出，更有机会默契匹配");
                    break;
                case 3:
                default:
                    ivState.setImageResource(R.drawable.icon_meet_challenge4);
                    tvContent.setText("其他用户现在可以发现你的默契卡片，有机会默契匹配哦");
                    break;
            }

        }else {
            tvContent.setText("你的默契卡片现在对其他用户隐藏，无法通过默契挑战匹配");
        }
    }

    /**
     * 创建/编辑默契挑战卡片成功
     *
     * @param event
     */
    @Subscribe
    public void onChallengeSuccess(ChallengeListBean.DataBean.RecordsBean event) {
        if (TextUtils.isEmpty(event.getObjectId())){
            meetViewModel.getChallengeCreate(event.getQuestion(), event.getChoiceA(), event.getChoiceB(), event.getChosen());
        }else {
            meetViewModel.getChallengeEdit(event.getObjectId(), event.getQuestion(), event.getChoiceA(), event.getChoiceB(), event.getChosen());
        }
    }
}
