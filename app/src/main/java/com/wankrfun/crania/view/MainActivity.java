package com.wankrfun.crania.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.king.app.updater.AppUpdater;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.utils.NotificationsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.VersionUtils;
import com.wankrfun.crania.viewmodel.IMConnectViewModel;
import com.wankrfun.crania.viewmodel.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
//import io.rong.imkit.RongIM;
//import io.rong.imkit.manager.IUnReadMessageObserver;
//import io.rong.imlib.model.Conversation;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.base
 * @ClassName: MainActivity
 * @Description: 首页
 * @Author: 鹿鸿祥
 * @CreateDate: 1/6/21 4:23 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/6/21 4:23 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    List<Fragment> mFragments;
    //用于记录上个选择的Fragment
    private int lastFragment;
//    private IUnReadMessageObserver observer;
    private LoginViewModel loginViewModel;

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        initFragment();
        getBadgeViewInitView();
        loginViewModel = getViewModel(LoginViewModel.class);
        //获取版本升级信息返回结果
        loginViewModel.versionUploadLiveData.observe(this, versionUploadBean -> {
            if (!TextUtils.isEmpty(versionUploadBean.getData().getVersion()) && Integer.parseInt(versionUploadBean.getData().getVersion()) > VersionUtils.getVersionCode(activity)) {
                new XPopup.Builder(activity)
                        .dismissOnTouchOutside(false)
                        .dismissOnBackPressed(false)
                        .asConfirm("新版本更新", versionUploadBean.getData().getDescription(),
                                "取消", "升级", () -> {
                                    if (TextUtils.isEmpty(versionUploadBean.getData().getUrl())){
                                        return;
                                    }
                                    //开始更新
                                    new AppUpdater(activity, versionUploadBean.getData().getUrl()).start();
                                }, null, false)
                        .show();
            }
        });
        IMConnectViewModel imConnectViewModel = getViewModel(IMConnectViewModel.class);
        imConnectViewModel.getImToken();

//        //极光别名设置
//        if (LoginUtils.setJPushAlias(activity)){
//            //保存设备ID返回结果
//            SharedUtils sharedUtils = new SharedUtils(activity);
//            LogUtils.e("设备ID" + sharedUtils.getShared(SpConfig.REGISTRATION_ID,"message"));
//        }
    }

    /**
     * 初始化fragment和fragment数组
     */
    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new MeetFragment());
        mFragments.add(new MessagesFragment());
        mFragments.add(new MineFragment());

        switchFragment(lastFragment,1);lastFragment=1;
        navigation.getMenu().getItem(1).setChecked(true);
        //判断选择的菜单
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    if(lastFragment!=0)switchFragment(lastFragment,0);lastFragment=0;
                    break;
                case R.id.menu_meet:
                    if(lastFragment!=1)switchFragment(lastFragment,1);lastFragment=1;
                    break;
                case R.id.menu_message:
                    if(lastFragment!=2)switchFragment(lastFragment,2);lastFragment=2;
                    break;
                case R.id.menu_mine:
                    if(lastFragment!=3)switchFragment(lastFragment,3);lastFragment=3;
                    break;
                default:
                    break;
            }
            // 这里注意返回true,否则点击失效
            return true;
        });
    }

    /**
     * 切换Fragment
     *
     * @param lastFragment
     * @param index
     */
    private void switchFragment(int lastFragment,int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mFragments.get(lastFragment));//隐藏上个Fragment
        if(!mFragments.get(index).isAdded()) {
            transaction.add(R.id.fragment,mFragments.get(index));
        }
        transaction.show(mFragments.get(index)).commitAllowingStateLoss();
    }

    /**
     * 重写返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //实现只在冷启动时显示启动页，类似微信效果
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //实现只在冷启动时显示启动页，即点击返回键与点击HOME键退出效果一致
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //设置底部消息提醒数字布局
    private RelativeLayout relativeLayout;
    private TextView number;
    private void getBadgeViewInitView() {
        //底部标题栏右上角标设置
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        //这里就是获取所添加的每一个Tab(或者叫menu)，设置在标题栏的位置
        View tab = menuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
        //加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(this).inflate(R.layout.layout_apply_count, menuView, false);
        //添加到Tab上
        itemView.addView(badge);
        //显示角标数字
        relativeLayout = badge.findViewById(R.id.rl);
        //显示/隐藏整个视图
        number = badge.findViewById(R.id.number);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NotificationsUtils.isNotificationEnabled(this)){
            //通知权限开启弹窗 3天只弹出一次
            if (NumberUtils.dateDiff(SPUtils.getInstance().getString(SpConfig.NOTICE_TIME), TimeUtils.getNowString(), "yyyy-MM-dd hh:mm:ss") >= 3){
                showNoticePermissionDialog(getString(R.string.permission_notice));
            }
        }

//        observer = i -> {
//            // i 是未读数量
//            //设置底部角标显示状态
//            if (i < 1) {
//                relativeLayout.setVisibility(View.GONE);
//            } else if (i < 100) {
//                relativeLayout.setVisibility(View.VISIBLE);
//                number.setTextSize(11);
//                number.setText(String.valueOf(i));
//            } else {
//                relativeLayout.setVisibility(View.VISIBLE);
//                number.setTextSize(9);
//                number.setText("99+");
//            }
//        };
//        RongIM.getInstance().addUnReadMessageCountChangedObserver(observer, Conversation.ConversationType.PRIVATE, Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM);
        loginViewModel.getVersionUpload();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RongIM.getInstance().removeUnReadMessageCountChangedObserver(observer);
    }
}
