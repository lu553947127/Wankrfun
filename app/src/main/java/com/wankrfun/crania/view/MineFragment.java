package com.wankrfun.crania.view;

import android.os.Bundle;
import android.view.View;

import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseFragment;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view
 * @ClassName: MineFragment
 * @Description: 我的fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 2:25 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 2:25 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineFragment extends BaseFragment {
    @Override
    protected int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState, View view) {

    }

    @Override
    protected void initDataFromService() {

    }
}
