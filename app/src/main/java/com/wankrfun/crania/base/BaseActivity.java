package com.wankrfun.crania.base;

import android.app.Application;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.Utils;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.receiver.SealNotificationReceiver;
import com.wankrfun.crania.utils.AutoUtils;
import com.wankrfun.crania.utils.NotificationsUtils;
import com.wankrfun.crania.utils.PermissionUtils;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import io.rong.push.RongPushClient;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.base
 * @ClassName: BaseActivity
 * @Description: 公共activity
 * @Author: 鹿鸿祥
 * @CreateDate: 1/6/21 3:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/6/21 3:11 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements IView, BGASwipeBackHelper.Delegate,
        ViewModelProvider.Factory {

    private Unbinder unBinder;
    //透明状态栏开关
    public  boolean isTranslationBar = false;
    private LoadDialog loadDialog;
    private SparseArray<AppCompatDialog> dialogArray = new SparseArray<>();
    public BaseActivity activity;
    //侧滑关闭
    protected BGASwipeBackHelper mSwipeBackHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        activity = BaseActivity.this;
        //适配初始化
        AutoUtils.setCustomDensity(this, getApplication());
        setContentView(initLayoutRes());
        unBinder = ButterKnife.bind(this);
        if (isUseEventBus()) {
            EventBus.getDefault().register(this);
        }

        if (!isTranslationBar) {
            //默认false透明状态栏
            BarUtils.setStatusBarColor(this, ContextCompat.getColor(Utils.getApp(), android.R.color.transparent), false);
            BarUtils.setStatusBarLightMode(this,false);
        } else {
            //true为主题色状态栏
            BarUtils.setStatusBarColor(this, ContextCompat.getColor(Utils.getApp(), R.color.color_111111), false);
            BarUtils.setStatusBarLightMode(this,true);
        }

        //当需要更新 HMS Core ，主动调用 RongPushClient.resolveHMSCoreUpdate() 方法，注意要传递 activity
        if (SealNotificationReceiver.needUpdate) {
            //重置标记位，防止多次弹窗提醒
            SealNotificationReceiver.needUpdate = false;
            RongPushClient.resolveHMSCoreUpdate(this);
        }

        initDataAndEvent(savedInstanceState);
    }

    @Override
    public void showLoading() {
        if (loadDialog == null) {
            loadDialog = new LoadDialog(this);
        }
        if (!loadDialog.isShowing()) {
            loadDialog.showDialog();
        }
    }

    @Override
    public void hideLoading() {
        if (loadDialog != null) {
            if (loadDialog.isShowing()) {
                loadDialog.hideDialog();
            }
        }
    }

    /**
     * 加载状态处理
     */
    public void showPageState(String s) {
        switch (s) {
            case PageState.PAGE_LOADING:
                showLoading();
                break;
            default:
                hideLoading();
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //获取当前焦点控件，如果是EditText,则失去焦点并隐藏软键盘
        View view = getCurrentFocus();
        if (view != null && view instanceof EditText && isShouldHideInput(view, ev)) {
            view.clearFocus();
            KeyboardUtils.hideSoftInput(view);
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 保险起见的dialog关闭，防止内存泄漏
     */
    public void addDialog(AppCompatDialog dialog) {
        dialogArray.put(dialogArray.size(), dialog);
    }

    @Override
    protected void onDestroy() {
        unBinder.unbind();
        if (isUseEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (loadDialog != null) {
            loadDialog.dismiss();
            loadDialog = null;
        }
        for (int i = 0; i < dialogArray.size(); i++) {
            if (dialogArray.get(i) != null) {
                dialogArray.get(i).dismiss();
            }
        }
        dialogArray = null;
        super.onDestroy();
    }

    protected abstract int initLayoutRes();

    /**
     * EventBus开关
     */
    public abstract boolean isUseEventBus();

    protected abstract void initDataAndEvent(Bundle savedInstanceState);

    /**
     * 获取输入框当前的location位置
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) { //如果点击的view是EditText
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //右滑退出重写方法 start
    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }

    //右滑退出重写方法 end


    //ViewModel配置开始
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel = MyApplication.getViewModelCache().get(modelClass.getCanonicalName());

        if (null != viewModel) {
            return (T) viewModel;
        }

        if (AndroidViewModel.class.isAssignableFrom(modelClass)) {
            //noinspection TryWithIdenticalCatches
            try {
                viewModel = modelClass.getConstructor(Application.class).newInstance(getApplication());
                MyApplication.getViewModelCache().put(modelClass.getCanonicalName(), viewModel);
                return (T) viewModel;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (java.lang.InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }

        //noinspection TryWithIdenticalCatches
        try {
            ViewModel model = modelClass.newInstance();
            MyApplication.getViewModelCache().put(modelClass.getCanonicalName(), model);
            return (T) model;
        } catch (java.lang.InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }

    }

    public <V extends ViewModel> V getViewModel(Class clazz) {
        return (V) ViewModelProviders.of(this).get(clazz);
    }
    //ViewModel配置结束

    /**
     * 设置权限拒绝弹起弹窗
     */
    public void showPermissionDialog(String title) {
        new XPopup.Builder(this).asConfirm(getString(R.string.reminder), title, () -> {
            PermissionUtils.toSelfSetting(getApplicationContext());
        }, () -> {

        }).show();
    }

    /**
     * 设置通知权限开启弹窗
     */
    public void showNoticePermissionDialog(String title) {
        SPUtils.getInstance().put(SpConfig.NOTICE_TIME, TimeUtils.getNowString(), true);
        new XPopup.Builder(this).asConfirm(getString(R.string.reminder), title, () -> {
            NotificationsUtils.requestNotify(activity);
        }, () -> {

        }).show();
    }
}
