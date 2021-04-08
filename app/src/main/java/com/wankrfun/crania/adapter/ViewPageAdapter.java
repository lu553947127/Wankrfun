package com.wankrfun.crania.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.blankj.utilcode.util.LogUtils;
import com.wankrfun.crania.R;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: ViewPageAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/31/21 2:18 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/31/21 2:18 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewPageAdapter extends RecyclingPagerAdapter {

    private List<Integer> mList;
    private int is_select;

    public ViewPageAdapter(List<Integer> list) {
        mList = list;
    }

    /**
     * 设置选中效果
     * @param is_select
     */
    public void setIsSelect(int is_select) {
        this.is_select = is_select;
        notifyDataSetChanged();
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_view_page, null);
            viewHolder.appCompatImageView = convertView.findViewById(R.id.iv_image);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if (is_select == mList.get(position)){
            switch (position){
                case 0:
                    viewHolder.appCompatImageView.setImageResource(R.drawable.icon_more_card11);
                    break;
                case 1:
                    viewHolder.appCompatImageView.setImageResource(R.drawable.icon_more_card22);
                    break;
                case 2:
                    viewHolder.appCompatImageView.setImageResource(R.drawable.icon_more_card33);
                    break;
            }
        }else {
            viewHolder.appCompatImageView.setImageResource(mList.get(position));
        }

        viewHolder.appCompatImageView.setOnClickListener(v -> {

        });
        return convertView;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    private static class ViewHolder{
        AppCompatImageView appCompatImageView;
    }
}
