package com.wankrfun.crania.receiver.rongyun;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.dialog.CustomBottomMessageDialog;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver.rongyun
 * @ClassName: CustomPlugin
 * @Description: 加号区域操作设置
 * @Author: 鹿鸿祥
 * @CreateDate: 4/16/21 10:43 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/16/21 10:43 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomPlugin implements IPluginModule {

    @Override
    public Drawable obtainDrawable(Context context) {
        return ContextCompat.getDrawable(context, R.drawable.icon_message_invitation);
    }

    @Override
    public String obtainTitle(Context context) {
        return "邀请";
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        new XPopup.Builder(fragment.getActivity()).asCustom(new CustomBottomMessageDialog(fragment.getContext(), rongExtension.getTargetId())).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
