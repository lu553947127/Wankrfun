package com.wankrfun.crania.view;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.BarUtils;
import com.kcrason.dynamicpagerindicatorlibrary.DynamicPagerIndicator;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.ViewPagerAdapter;
import com.wankrfun.crania.base.BaseFragment;
import com.wankrfun.crania.view.messages.chat.ChatFragment;
import com.wankrfun.crania.view.messages.NoticeFragment;

import butterknife.BindView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view
 * @ClassName: MessagesFragment
 * @Description: 消息fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 2:25 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 2:25 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MessagesFragment extends BaseFragment {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.dynamic_pager_indicator)
    DynamicPagerIndicator dynamicPagerIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected int initLayout() {
        return R.layout.fragment_messages;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState, View view) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.black));
        Fragment[] fragments = {
                new ChatFragment(),
                new NoticeFragment()
        };
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragments, getResources().getStringArray(R.array.messages_list)));
        dynamicPagerIndicator.setViewPager(viewPager);
    }

    @Override
    protected void initDataFromService() {

    }
}
