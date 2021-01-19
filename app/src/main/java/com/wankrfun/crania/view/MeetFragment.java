package com.wankrfun.crania.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.EventsTypeAdapter;
import com.wankrfun.crania.adapter.SwipeFlingAdapter;
import com.wankrfun.crania.base.BaseFragment;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.utils.RefreshUtils;
import com.wankrfun.crania.widget.swipe.SwipeFlingAdapterView;

import butterknife.BindView;

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
    @BindView(R.id.swipe_view)
    SwipeFlingAdapterView swipeFlingAdapterView;

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

        SwipeFlingAdapter swipeFlingAdapter = new SwipeFlingAdapter();
        swipeFlingAdapterView.setAdapter(swipeFlingAdapter);

        swipeFlingAdapterView.setIsNeedSwipe(true);
        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {

            }

            @Override
            public void onRightCardExit(Object dataObject) {

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float progress, float scrollXProgress) {

            }
        });

        swipeFlingAdapterView.setOnItemClickListener((event, v, dataObject) -> {

        });
    }

    @Override
    protected void initDataFromService() {

    }
}
