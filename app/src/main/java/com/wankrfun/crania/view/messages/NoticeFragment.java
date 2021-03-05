package com.wankrfun.crania.view.messages;

import android.os.Bundle;

import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseLazyFragment;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.messages
 * @ClassName: NoticeFragment
 * @Description: 通知fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 1/20/21 11:20 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/20/21 11:20 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NoticeFragment extends BaseLazyFragment {

    @Override
    protected int initLayout() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataFromService() {

    }
}
