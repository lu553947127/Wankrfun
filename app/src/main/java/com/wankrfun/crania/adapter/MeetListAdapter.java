package com.wankrfun.crania.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.bean.MeetListBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.RefreshUtils;
import com.wankrfun.crania.widget.CornerImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MeetListAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/5/21 8:37 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/5/21 8:37 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetListAdapter extends RecyclerView.Adapter {

    private List<MeetListBean.DataBean.ListBean> list;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {

        void onLeftClick(View view, RecyclerView.ViewHolder holder, int position);

        void onRightClick(View view, RecyclerView.ViewHolder holder, int position);

        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 添加并更新数据
     */
    public void setNewData(List<MeetListBean.DataBean.ListBean> data) {
        list = data;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_swipe_fling, parent, false);
        return new MeetListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
//        ImageLoader.load(MyApplication.getInstance().getApplicationContext(), new ImageConfig.Builder()
//                .url(list.get(position).getPhoto())
//                .placeholder(R.drawable.icon_empty_meet)
//                .errorPic(R.drawable.icon_empty_meet)
//                .imageView(((MeetListViewHolder) holder).cornerImageView)
//                .build());
//        //item点击
//        holder.itemView.setOnClickListener(view -> mOnItemClickListener.onItemClick(view, position));
//        //喜欢
//        ((MeetListViewHolder) holder).iv_like_btn.setOnClickListener(v -> mOnItemClickListener.onLeftClick(v, holder, position));
//        //不喜欢
//        ((MeetListViewHolder) holder).iv_dislike_btn.setOnClickListener(v -> mOnItemClickListener.onRightClick(v, holder, position));
//        ((MeetListViewHolder) holder).view.getBackground().setAlpha(110);
//        ((MeetListViewHolder) holder).tv_name.setText(list.get(position).getName());
//        ((MeetListViewHolder) holder).iv_sex.setImageResource(list.get(position).getSex().endsWith("female") ? R.drawable.icon_events_female : R.drawable.icon_sex_male);
//        if (!TextUtils.isEmpty(list.get(position).getJob())){
//            switch (list.get(position).getJob()){
//                case "s:":
//                    ((MeetListViewHolder) holder).tv_work.setText("学生");
//                    break;
//                case "j:":
//                    ((MeetListViewHolder) holder).tv_work.setText("工作");
//                    break;
//                case "c:":
//                    ((MeetListViewHolder) holder).tv_work.setText("自由职业者");
//                    break;
//            }
//        }else {
//            ((MeetListViewHolder) holder).tv_work.setText("自由职业者");
//        }
//        if (!TextUtils.isEmpty(list.get(position).getBirthday())){
//            ((MeetListViewHolder) holder).tv_constellation.setText(NumberUtils.date2Constellation(list.get(position).getBirthday()));
//        }else {
//            ((MeetListViewHolder) holder).tv_constellation.setText("暂无");
//        }
//
//        ((MeetListViewHolder) holder).tv_city.setText(list.get(position).getAddress());
//
//        ((MeetListViewHolder) holder).ll_tag.getBackground().setAlpha(60);
//        ((MeetListViewHolder) holder).ll_event_tag.getBackground().setAlpha(60);
//        if (list.get(position).getTag() != null && list.get(position).getTag().size() != 0){
//            ((MeetListViewHolder) holder).tv_title.setText(list.get(position).getTag().get(0));
//            ((MeetListViewHolder) holder).iv_icon.setImageResource(RefreshUtils.setTagIcon(list.get(position).getTag().get(0)));
//        }
//
//        if (list.get(position).getEvent_tag() != null && list.get(position).getEvent_tag().size() != 0){
//            ((MeetListViewHolder) holder).tv_title2.setText(list.get(position).getEvent_tag().get(0));
//            ((MeetListViewHolder) holder).iv_icon2.setImageResource(RefreshUtils.setEventTagIcon(list.get(position).getEvent_tag().get(0)));
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MeetListViewHolder extends RecyclerView.ViewHolder {
        CornerImageView cornerImageView;
        AppCompatImageView iv_like_btn;
        AppCompatImageView iv_dislike_btn;
        public AppCompatImageView iv_like;
        public AppCompatImageView iv_dislike;
        View view;
        AppCompatTextView tv_name;
        AppCompatImageView iv_sex;
        AppCompatTextView tv_work;
        AppCompatTextView tv_constellation;
        AppCompatTextView tv_city;
        AppCompatImageView iv_icon;
        AppCompatTextView tv_title;
        AppCompatImageView iv_icon2;
        AppCompatTextView tv_title2;
        LinearLayout ll_tag;
        LinearLayout ll_event_tag;

        MeetListViewHolder(View itemView) {
            super(itemView);
            cornerImageView = itemView.findViewById(R.id.iv_image);
            iv_like_btn = itemView.findViewById(R.id.iv_like_btn);
            iv_dislike_btn = itemView.findViewById(R.id.iv_dislike_btn);
            iv_like = itemView.findViewById(R.id.iv_like);
            iv_dislike = itemView.findViewById(R.id.iv_dislike);
            view = itemView.findViewById(R.id.view);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_sex = itemView.findViewById(R.id.iv_sex);
            tv_work = itemView.findViewById(R.id.tv_work);
            tv_constellation = itemView.findViewById(R.id.tv_constellation);
            tv_city = itemView.findViewById(R.id.tv_city);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_icon2 = itemView.findViewById(R.id.iv_icon2);
            tv_title2 = itemView.findViewById(R.id.tv_title2);
            ll_tag = itemView.findViewById(R.id.ll_tag);
            ll_event_tag = itemView.findViewById(R.id.ll_event_tag);
        }
    }
}
