package com.wankrfun.crania.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.MeetListBean;
import com.wankrfun.crania.bean.MineLifeListBean;
import com.wankrfun.crania.utils.PictureUtils;
import com.wankrfun.crania.utils.RefreshUtils;
import com.wankrfun.crania.widget.CornerImageView;
import com.youth.banner.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineAboutLifeAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/7/21 8:59 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/7/21 8:59 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineAboutLifeAdapter<T> extends BannerAdapter<T, MineAboutLifeAdapter.BannerViewHolder> {
    public Context context;
    private String icon, content;
    private List<String> images = new ArrayList<>();
    public MineAboutLifeAdapter(Context context, List<T> beanList) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(beanList);
        this.context = context;
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public MineAboutLifeAdapter.BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mine_about_life, parent,false);
        return new MineAboutLifeAdapter.BannerViewHolder(view);
    }

    @Override
    public void onBindView(MineAboutLifeAdapter.BannerViewHolder holder, T item, int position, int size) {

        if (item instanceof MineLifeListBean.DataBean.ListBean){
            icon = ((MineLifeListBean.DataBean.ListBean) item).getIcon();
            content = ((MineLifeListBean.DataBean.ListBean) item).getContent();
            images = ((MineLifeListBean.DataBean.ListBean) item).getImages();
        }else if (item instanceof MeetListBean.DataBean.ListBean.LifeMomentsBean){
            icon = ((MeetListBean.DataBean.ListBean.LifeMomentsBean) item).getIcon();
            content = ((MeetListBean.DataBean.ListBean.LifeMomentsBean) item).getContent();
            images = ((MeetListBean.DataBean.ListBean.LifeMomentsBean) item).getImages();
        }

        holder.appCompatImageView.setImageResource(RefreshUtils.setMineLifeIcon(icon));
        holder.appCompatTextView.setText(RefreshUtils.setMineLifeType(icon));
        holder.appCompatTextView2.setText(content);
        holder.appCompatTextView2.setVisibility(TextUtils.isEmpty(content) ? View.GONE : View.VISIBLE);

        if (images != null && images.size() > 0){
            switch (images.size()){
                case 1:
                    holder.cornerImageView1.setVisibility(View.VISIBLE);
                    holder.linearLayout2.setVisibility(View.GONE);
                    holder.linearLayout3.setVisibility(View.GONE);
                    holder.linearLayout4.setVisibility(View.GONE);
                    PictureUtils.setImage(context, images.get(0), holder.cornerImageView1);
                    break;
                case 2:
                    holder.cornerImageView1.setVisibility(View.GONE);
                    holder.linearLayout2.setVisibility(View.VISIBLE);
                    holder.linearLayout3.setVisibility(View.GONE);
                    holder.linearLayout4.setVisibility(View.GONE);
                    PictureUtils.setImage(context, images.get(0), holder.cornerImageView2);
                    PictureUtils.setImage(context, images.get(1), holder.cornerImageView3);
                    break;
                case 3:
                    holder.cornerImageView1.setVisibility(View.GONE);
                    holder.linearLayout2.setVisibility(View.GONE);
                    holder.linearLayout3.setVisibility(View.VISIBLE);
                    holder.linearLayout4.setVisibility(View.GONE);
                    PictureUtils.setImage(context, images.get(0), holder.cornerImageView4);
                    PictureUtils.setImage(context, images.get(1), holder.cornerImageView5);
                    PictureUtils.setImage(context, images.get(2), holder.cornerImageView6);
                    break;
                case 4:
                    holder.cornerImageView1.setVisibility(View.GONE);
                    holder.linearLayout2.setVisibility(View.GONE);
                    holder.linearLayout3.setVisibility(View.GONE);
                    holder.linearLayout4.setVisibility(View.VISIBLE);
                    PictureUtils.setImage(context, images.get(0), holder.cornerImageView7);
                    PictureUtils.setImage(context, images.get(1), holder.cornerImageView8);
                    PictureUtils.setImage(context, images.get(2), holder.cornerImageView9);
                    PictureUtils.setImage(context, images.get(3), holder.cornerImageView10);
                    break;
            }
        }
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView appCompatImageView;
        private AppCompatTextView appCompatTextView;
        private AppCompatTextView appCompatTextView2;
        private CornerImageView cornerImageView1;
        private CornerImageView cornerImageView2;
        private CornerImageView cornerImageView3;
        private CornerImageView cornerImageView4;
        private CornerImageView cornerImageView5;
        private CornerImageView cornerImageView6;
        private CornerImageView cornerImageView7;
        private CornerImageView cornerImageView8;
        private CornerImageView cornerImageView9;
        private CornerImageView cornerImageView10;
        private LinearLayout linearLayout2;
        private LinearLayout linearLayout3;
        private LinearLayout linearLayout4;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            appCompatImageView = itemView.findViewById(R.id.iv_icon);
            appCompatTextView = itemView.findViewById(R.id.tv_type);
            appCompatTextView2 = itemView.findViewById(R.id.tv_title);
            cornerImageView1 = itemView.findViewById(R.id.iv_one);
            cornerImageView2 = itemView.findViewById(R.id.iv_two);
            cornerImageView3 = itemView.findViewById(R.id.iv_three);
            cornerImageView4 = itemView.findViewById(R.id.iv_four);
            cornerImageView5 = itemView.findViewById(R.id.iv_five);
            cornerImageView6 = itemView.findViewById(R.id.iv_six);
            cornerImageView7 = itemView.findViewById(R.id.iv_seven);
            cornerImageView8 = itemView.findViewById(R.id.iv_eight);
            cornerImageView9 = itemView.findViewById(R.id.iv_night);
            cornerImageView10 = itemView.findViewById(R.id.iv_ten);
            linearLayout2 = itemView.findViewById(R.id.ll_images_two);
            linearLayout3 = itemView.findViewById(R.id.ll_images_three);
            linearLayout4 = itemView.findViewById(R.id.ll_images_four);
        }
    }
}
