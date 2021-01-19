package com.wankrfun.crania.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.BaseBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: FirstSetExpectAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 10:09 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 10:09 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetExpectAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {
    private String is_select;
    public FirstSetExpectAdapter(int layoutResId, @Nullable List<BaseBean> data) {
        super(layoutResId, data);
    }

    public void setIsSelect(String is_select) {
        this.is_select = is_select;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        helper.setText(R.id.tv_title,item.getName());
        if (!TextUtils.isEmpty(is_select)){
            if (is_select.equals(item.getName())){
                helper.setBackgroundRes(R.id.rl_select , R.drawable.shape_yellow_5);
            }else {
                helper.setBackgroundRes(R.id.rl_select , R.drawable.shape_gray_5);
            }
        }else {
            helper.setBackgroundRes(R.id.rl_select , R.drawable.shape_gray_5);
        }

        AppCompatImageView imageView = helper.getView(R.id.iv_image);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url("")
                .placeholder(item.getImages())
                .errorPic(item.getImages())
                .imageView(imageView)
                .build());
    }
}
