package com.wankrfun.crania.view.mine.about;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineWishAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.WishListBean;
import com.wankrfun.crania.event.CardEvent;
import com.wankrfun.crania.utils.DensityUtil;
import com.wankrfun.crania.viewmodel.MineCardViewModel;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineWishActivity
 * @Description: 我的心愿列表页面
 * @Author: 鹿鸿祥
 * @CreateDate: 3/22/21 10:28 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/22/21 10:28 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineWishActivity extends BaseActivity {
    @BindView(R.id.rv)
    SwipeRecyclerView recyclerView;
    //创建侧滑菜单
    private final SwipeMenuCreator creator = (leftMenu, rightMenu, position) -> {
        SwipeMenuItem deleteItem = new SwipeMenuItem(this)
                .setBackgroundColorResource(R.color.color_FF0000)
                .setText("删除")
                .setTextColor(Color.WHITE)
                .setTextSize(14)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                .setWidth(DensityUtil.dp2px(50));
        rightMenu.addMenuItem(deleteItem);
    };
    private MineCardViewModel mineCardViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_wish;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        mineCardViewModel = getViewModel(MineCardViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        MineWishAdapter mineWishAdapter  = new MineWishAdapter(R.layout.adapter_mine_wish, null);
        //设置侧滑菜单列表
        recyclerView.setSwipeMenuCreator(creator);
        //侧滑事件监听回调
        recyclerView.setOnItemMenuClickListener((menuBridge, adapterPosition) -> {
            if (menuBridge.getPosition() == 0) {//删除
                new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "您确定要删除吗", () -> {
                    mineCardViewModel.getWishDelete(mineWishAdapter.getData().get(adapterPosition).getObjectId());
                    mineWishAdapter.remove(adapterPosition);
                    menuBridge.closeMenu();
                }, menuBridge::closeMenu).show();
            }
        });
        recyclerView.setAdapter(mineWishAdapter);

        mineWishAdapter.setOnItemClickListener((adapter, view, position) -> {
            WishListBean.DataBean.ListBean listBean = mineWishAdapter.getData().get(position);
            if (listBean.getContent().equals("更多")){
                ActivityUtils.startActivity(MineWishAddActivity.class);
            }else {
                Bundle bundle = new Bundle();
                bundle.putString("id", listBean.getObjectId());
                bundle.putString("content", listBean.getContent());
                ActivityUtils.startActivity(bundle, MineWishAddActivity.class);
            }
        });

        //获取心愿列表返回结果
        mineCardViewModel.wishListLiveData.observe(this, wishListBean -> {
            List<WishListBean.DataBean.ListBean> wishList = wishListBean.getData().getList();
            if (wishList.size() < 3){
                wishList.add(new WishListBean.DataBean.ListBean("","更多"));
            }
            mineWishAdapter.setNewData(wishListBean.getData().getList());
        });

        //删除心愿列表返回结果
        mineCardViewModel.wishDeleteLiveData.observe(this, challengeStatusBean -> {
            EventBus.getDefault().post(new CardEvent("wish"));
            mineCardViewModel.getWishList(SPUtils.getInstance().getString(SpConfig.USER_ID));
        });

        mineCardViewModel.getWishList(SPUtils.getInstance().getString(SpConfig.USER_ID));
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
