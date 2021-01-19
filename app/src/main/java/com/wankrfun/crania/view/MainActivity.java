package com.wankrfun.crania.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.utils.NotificationsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

        switchFragment(lastFragment,0);lastFragment=0;
        navigation.getMenu().getItem(0).setChecked(true);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (!NotificationsUtils.isNotificationEnabled(this)){
//            showNoticePermissionDialog(getString(R.string.permission_notice));
        }
    }
}
