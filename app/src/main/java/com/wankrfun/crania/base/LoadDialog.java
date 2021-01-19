package com.wankrfun.crania.base;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ConvertUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.utils.AnimatorUtils;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.base
 * @ClassName: LoadDialog
 * @Description: 公共加载动画弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 9:31 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 9:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoadDialog extends BaseDialog{
    private int showNum = 0;

    public LoadDialog(@NonNull Activity activity) {
        super(activity);
    }

    @Override
    int initLayout() {
        return R.layout.dialog_loading;
    }

    @Override
    void initData() {
        setWidth(ConvertUtils.dp2px(100));
        setHeight(ConvertUtils.dp2px(100));
        setCancelOutside(false);
        AnimatorUtils.enterCustomAnim(this);
    }

    @Override
    void initEvent() {

    }

    @Override
    public BaseDialog showDialog() {
        showNum++;
        if (showNum > 1){
            return this;
        }else {
            return super.showDialog();
        }
    }

    @Override
    public BaseDialog hideDialog() {
        if (showNum > 0){
            showNum--;
        }
        if (showNum > 0){
            return this;
        }else {
            return super.hideDialog();
        }
    }
}
