package com.wankrfun.crania.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.MeetListBean;
import com.wankrfun.crania.bean.WishListBean;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.widget.AnimatorImageView;
import com.youth.banner.adapter.BannerAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineAboutWishAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/2/21 1:24 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/2/21 1:24 PM
 * @UpdateRemark: helper.getPosition() %2 == 0
 * @Version: 1.0
 */
public class MineAboutWishAdapter<T> extends BannerAdapter<T, MineAboutWishAdapter.BannerViewHolder> {
    public Context context;
    private String type, content, color;
    public MineAboutWishAdapter(Context context, List<T> beanList, String type) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(beanList);
        this.context = context;
        this.type = type;
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public MineAboutWishAdapter.BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mine_about_wish, parent,false);
        return new MineAboutWishAdapter.BannerViewHolder(view);
    }

    @Override
    public void onBindView(MineAboutWishAdapter.BannerViewHolder holder, T item, int position, int size) {

        if (item instanceof WishListBean.DataBean.ListBean){
            content = ((WishListBean.DataBean.ListBean) item).getContent();
            color = ((WishListBean.DataBean.ListBean) item).getColor();
        }else if (item instanceof MeetListBean.DataBean.ListBean.WishesBean){
            content = ((MeetListBean.DataBean.ListBean.WishesBean) item).getContent();
            color = ((MeetListBean.DataBean.ListBean.WishesBean) item).getColor();
        }

        if (!TextUtils.isEmpty(type)){
            holder.animatorImageView.setVisibility(View.GONE);
        }else {
            holder.animatorImageView.setVisibility(View.VISIBLE);
        }

        if (content.equals("更多")){
            holder.relativeLayout.setVisibility(View.GONE);
            holder.relativeLayout2.setVisibility(View.VISIBLE);
        }else {
            holder.relativeLayout.setVisibility(View.VISIBLE);
            holder.relativeLayout2.setVisibility(View.GONE);
        }

        holder.appCompatTextView.setText("“" + content + "”");

        switch (color){
            case "E3B492":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card1_5);
                break;
            case "92C1E3":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card2_5);
                break;
            case "92E3B2":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card3_5);
                break;
            case "E392AF":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card4_5);
                break;
            case "E3DB92":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card5_5);
                break;
            case "2AA7ED":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card6_5);
                break;
            case "BB92E3":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card7_5);
                break;
            case "E39292":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card8_5);
                break;
            case "929FE3":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card9_5);
                break;
            case "E392E0":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card10_5);
                break;
            case "E3C992":
                holder.relativeLayout.setBackgroundResource(R.drawable.shape_card11_5);
                break;
        }

        holder.animatorImageView.setOnClickListener(view -> {
            holder.animatorImageView.animator(true);
            if (item instanceof MeetListBean.DataBean.ListBean.WishesBean){
                EventBus.getDefault().post(new EventsEvent(((MeetListBean.DataBean.ListBean.WishesBean) item).getContent()));
            }
        });
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout;
        private RelativeLayout relativeLayout2;
        private AppCompatTextView appCompatTextView;
        private AnimatorImageView animatorImageView;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rl);
            relativeLayout2 = itemView.findViewById(R.id.rl_next);
            appCompatTextView = itemView.findViewById(R.id.tv_title);
            animatorImageView = itemView.findViewById(R.id.iv_high_five);
        }
    }
}
