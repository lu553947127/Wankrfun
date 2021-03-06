package com.wankrfun.crania.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.widget.CornerImageView;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: GridImageAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/2/21 10:17 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/2/21 10:17 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GridImageAdapter extends RecyclerView.Adapter<GridImageAdapter.ImageViewHolder> {

    private List<String> mData;
    private int mCountLimit = 6;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {

        void onTakePhotoClick(View view, int position);

        void onItemClick(View view, int position);

        void onItemDelClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public GridImageAdapter(List<String> data) {
        this.mData = data;
    }

    public GridImageAdapter(List<String> data, int mCountLimit) {
        this.mData = data;
        this.mCountLimit = mCountLimit;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_images, null);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        if (position == getItemCount() - 1 && mData.size() < mCountLimit) {
            ImageLoader.load(MyApplication.getInstance().getApplicationContext(), new ImageConfig.Builder()
                    .url("")
                    .imageView(holder.imageView)
                    .placeholder(R.drawable.icon_events_add)
                    .errorPic(R.drawable.icon_events_add)
                    .build());
            holder.delView.setVisibility(View.INVISIBLE);
            holder.imageView.setOnClickListener(v -> mOnItemClickListener.onTakePhotoClick(v, position));
        } else {
            ImageLoader.load(MyApplication.getInstance().getApplicationContext(), new ImageConfig.Builder()
                    .url(mData.get(position))
                    .imageView(holder.imageView)
                    .placeholder(R.drawable.icon_images_empty)
                    .errorPic(R.drawable.icon_images_empty)
                    .build());
            holder.delView.setVisibility(View.VISIBLE);
            holder.imageView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v, position));
            // 删除
            holder.delView.setOnClickListener(v -> mOnItemClickListener.onItemDelClick(v, position));
        }
    }

    /**
     * 添加并更新数据
     */
    public void setNewData(List<String> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // 满 6张图就不让其添加新图
        if (mData != null && mData.size() >= mCountLimit) {
            return mCountLimit;
        } else {
            return mData == null ? 1 : mData.size() + 1;
        }
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        private final CornerImageView imageView;
        private final AppCompatImageView delView;
        private ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_images);
            delView = itemView.findViewById(R.id.iv_delete);
        }
    }
}
