package com.wankrfun.crania.view;

import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.kcrason.dynamicpagerindicatorlibrary.DynamicPagerIndicator;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.ViewPagerAdapter;
import com.wankrfun.crania.base.BaseFragment;
import com.wankrfun.crania.view.meet.MeetChallengeActivity;
import com.wankrfun.crania.view.meet.MeetHomeFragment;
import com.wankrfun.crania.view.meet.MeetLikeFragment;
import com.wankrfun.crania.viewmodel.MeetViewModel;
import com.wankrfun.crania.widget.CustomVideoView;

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
    @BindView(R.id.tv_tab_right)
    AppCompatTextView tvTabRight;
    @BindView(R.id.video_view)
    CustomVideoView videoView;
    @BindView(R.id.dynamic_pager_indicator)
    DynamicPagerIndicator dynamicPagerIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
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
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(android.R.color.transparent));

        Fragment[] fragments = {
                new MeetHomeFragment(),
                new MeetLikeFragment()
        };
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragments, getResources().getStringArray(R.array.meet_list)));
        dynamicPagerIndicator.setViewPager(viewPager);

        meetViewModel = mActivity.getViewModel(MeetViewModel.class);

        //获取默契挑战开关状态返回结果
        meetViewModel.challengeStatusLiveData.observe(this, challengeStatusBean -> {
            tvTabRight.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            if (challengeStatusBean.getData().isStatus()){
                tvTabRight.setText(getString(R.string.meet_challenge_off));
            }else {
                tvTabRight.setText(getString(R.string.meet_challenge_on));
            }
        });
    }

    @Override
    protected void initDataFromService() {

    }

    @OnClick({R.id.tv_tab_right})
    void onClick() {
        ActivityUtils.startActivity(MeetChallengeActivity.class);
    }

    /**
     * 视频初始化
     */
    private void initVideo() {
        //设置播放加载路径
        videoView.setVideoURI(Uri.parse("android.resource://" + mActivity.getPackageName() + "/" + R.raw.meet_video));
        //播放
        videoView.start();
        //循环播放
        videoView.setOnCompletionListener(mediaPlayer -> videoView.start());
    }

    @Override
    public void onResume() {
        initVideo();
        meetViewModel.getChallengeStatus();
        super.onResume();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    public void onDestroyView() {
        videoView.stopPlayback();
        super.onDestroyView();
    }
}
