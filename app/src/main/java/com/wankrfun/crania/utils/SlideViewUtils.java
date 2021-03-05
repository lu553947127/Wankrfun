package com.wankrfun.crania.utils;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.wankrfun.crania.adapter.MeetListAdapter;
import com.wankrfun.crania.bean.MeetListBean;
import com.wankrfun.crania.view.mine.UserInfoActivity;
import com.wankrfun.crania.viewmodel.MeetViewModel;
import com.wankrfun.crania.widget.swipe.CardConfig;
import com.wankrfun.crania.widget.swipe.CardItemTouchHelperCallback;
import com.wankrfun.crania.widget.swipe.CardLayoutManager;
import com.wankrfun.crania.widget.swipe.OnSwipeListener;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: SlideViewUtils
 * @Description: 左滑右滑view工具
 * @Author: 鹿鸿祥
 * @CreateDate: 2/5/21 9:54 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/5/21 9:54 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SlideViewUtils {

    /**
     * 设置遇见左滑右滑状态
     *
     * @param recyclerView 设置左滑右滑列表
     * @param dataList 添加左滑右滑数据
     * @param relativeLayout 数据为空展位图显示
     * @param meetListAdapter 左滑右滑列表适配器
     * @param meetViewModel 左滑右滑列表适配器
     */
    public static void getSlideResult(RecyclerView recyclerView, List<MeetListBean.DataBean.ListBean> dataList, RelativeLayout relativeLayout
            , MeetListAdapter meetListAdapter, MeetViewModel meetViewModel){
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), dataList);
        cardCallback.setOnSwipedListener(new OnSwipeListener<MeetListBean.DataBean.ListBean>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                MeetListAdapter.MeetListViewHolder meetListViewHolder = (MeetListAdapter.MeetListViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    meetListViewHolder.iv_dislike.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    meetListViewHolder.iv_like.setAlpha(Math.abs(ratio));
                } else {
                    meetListViewHolder.iv_like.setAlpha(0f);
                    meetListViewHolder.iv_dislike.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, MeetListBean.DataBean.ListBean o, int direction) {
                MeetListAdapter.MeetListViewHolder meetListViewHolder = (MeetListAdapter.MeetListViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                meetListViewHolder.iv_like.setAlpha(0f);
                meetListViewHolder.iv_dislike.setAlpha(0f);

                LogUtils.e("direction" + direction);
                //左滑不喜欢完成事件操作
                if (direction == 1) {
                    meetViewModel.getMeetOperateLike(o.getObjectId(), "DISLIKE");
                    //右滑喜欢完成事件操作
                } else if (direction == 4) {
                    meetViewModel.getMeetOperateLike(o.getObjectId(), "LIKE");
                }
                meetViewModel.getMeetUserCard();
            }

            @Override
            public void onSwipedClear() {
                relativeLayout.setVisibility(View.VISIBLE);
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);

        meetListAdapter.setOnItemClickListener(new MeetListAdapter.OnItemClickListener() {
            @Override
            public void onLeftClick(View view, RecyclerView.ViewHolder holder, int position) {
                cardCallback.onSwiped(holder, 4);
                meetViewModel.getMeetOperateLike(dataList.get(position).getObjectId(), "DISLIKE");
                meetViewModel.getMeetUserCard();
            }

            @Override
            public void onRightClick(View view, RecyclerView.ViewHolder holder, int position) {
                cardCallback.onSwiped(holder, 8);
                meetViewModel.getMeetOperateLike(dataList.get(position).getObjectId(), "LIKE");
                meetViewModel.getMeetUserCard();
            }

            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", dataList.get(position).getObjectId());
                bundle.putString("sex", dataList.get(position).getSex());
                ActivityUtils.startActivity(bundle, UserInfoActivity.class);
            }
        });
    }
}
