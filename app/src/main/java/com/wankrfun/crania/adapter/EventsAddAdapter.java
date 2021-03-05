package com.wankrfun.crania.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.BaseBean;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: EventsAddAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/29/21 2:09 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/29/21 2:09 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsAddAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {
    private String is_select;
    private AppCompatImageView imageView;
    public EventsAddAdapter(int layoutResId, @Nullable List<BaseBean> data) {
        super(layoutResId, data);
    }

    /**
     * 单选事件操作
     * @param is_select
     */
    public void setIsSelect(String is_select) {
        this.is_select = is_select;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        helper.setText(R.id.tv_title,item.getName());
        imageView = helper.getView(R.id.iv_image);
        if (!TextUtils.isEmpty(is_select)){
            if (is_select.equals(item.getName())){
                imageView.setImageResource(item.getImages());
                helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.white));
            }else {
                imageView.setImageResource(item.getImagesNot());
                helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.color_9B9CA8));
            }
        }else {
            imageView.setImageResource(item.getImagesNot());
            helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.color_9B9CA8));
        }
    }
}
