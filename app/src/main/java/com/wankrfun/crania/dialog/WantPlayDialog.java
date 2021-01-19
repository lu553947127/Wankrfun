package com.wankrfun.crania.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.impl.FullScreenPopupView;
import com.wankrfun.crania.R;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: WantPlayDialog
 * @Description: 我今天想玩全屏弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 4:34 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 4:34 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WantPlayDialog extends FullScreenPopupView {
    public WantPlayDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_want_play;
    }
}
