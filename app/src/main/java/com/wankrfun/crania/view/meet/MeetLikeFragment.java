package com.wankrfun.crania.view.meet;

import android.os.Bundle;

import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseLazyFragment;

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

    @Override
    protected int initLayout() {
        return R.layout.fragment_meet_like;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }

    @Override
    protected void initDataFromService() {

    }
}
