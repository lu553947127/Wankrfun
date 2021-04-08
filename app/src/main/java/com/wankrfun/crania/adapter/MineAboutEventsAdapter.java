package com.wankrfun.crania.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.BaseBean;
import com.wankrfun.crania.widget.CornerImageView;
import com.youth.banner.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineAboutEventsAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/7/21 9:48 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/7/21 9:48 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineAboutEventsAdapter extends BannerAdapter<BaseBean, MineAboutEventsAdapter.BannerViewHolder> {
    public Context context;
    public MineAboutEventsAdapter(Context context, List<BaseBean> beanList) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(beanList);
        this.context = context;
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public MineAboutEventsAdapter.BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mine_about_events, parent,false);
        return new MineAboutEventsAdapter.BannerViewHolder(view);
    }

    @Override
    public void onBindView(MineAboutEventsAdapter.BannerViewHolder holder, BaseBean item, int position, int size) {
        holder.view.getBackground().setAlpha(110);
        holder.ll_time.getBackground().setAlpha(60);
        holder.ll_events.getBackground().setAlpha(60);

        List<String> list = new ArrayList<>();
        list.add("https://img-blog.csdn.net/20160413112832792?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");
        list.add("https://img-blog.csdn.net/20160413112832792?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");
        list.add("https://img-blog.csdn.net/20160413112832792?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context ,LinearLayoutManager.HORIZONTAL,false));
        MineAboutImagesAdapter mineAboutImagesAdapter = new MineAboutImagesAdapter(R.layout.adapter_mine_about_images, list);
        holder.recyclerView.setAdapter(mineAboutImagesAdapter);
        mineAboutImagesAdapter.setIsSelect(0);

        mineAboutImagesAdapter.setOnItemClickListener((adapter, view, position1) -> {
            mineAboutImagesAdapter.setIsSelect(position1);
        });
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        private CornerImageView iv_image;
        private View view;
        private LinearLayout ll_time;
        private AppCompatTextView tv_month;
        private AppCompatTextView tv_day;
        private LinearLayout ll_events;
        private AppCompatImageView iv_icon;
        private AppCompatTextView tv_title;
        private RecyclerView recyclerView;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            view = itemView.findViewById(R.id.view);
            ll_time = itemView.findViewById(R.id.ll_time);
            tv_month = itemView.findViewById(R.id.tv_month);
            tv_day = itemView.findViewById(R.id.tv_day);
            ll_events = itemView.findViewById(R.id.ll_events);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
            recyclerView = itemView.findViewById(R.id.rv);
        }
    }
}
