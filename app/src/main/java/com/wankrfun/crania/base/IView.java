package com.wankrfun.crania.base;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.base
 * @ClassName: IView
 * @Description: 动画加载回调
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 9:31 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 9:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IView {
    /**
     * 加载中
     */
    void showLoading();

    /**
     * 隐藏
     */
    void hideLoading();
}
