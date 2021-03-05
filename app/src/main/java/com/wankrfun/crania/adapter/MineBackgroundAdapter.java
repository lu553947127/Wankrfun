package com.wankrfun.crania.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

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
 * @ClassName: MineBackgroundAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 3:52 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 3:52 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineBackgroundAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {
    private String is_select;
    public MineBackgroundAdapter(int layoutResId, @Nullable List<BaseBean> data) {
        super(layoutResId, data);
    }

    public void setIsSelect(String is_select) {
        this.is_select = is_select;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        RelativeLayout relativeLayout = helper.getView(R.id.rl_select);
        AppCompatImageView appCompatImageView = helper.getView(R.id.iv_select);
        if (!TextUtils.isEmpty(is_select)){
            if (is_select.equals(item.getName())){
                relativeLayout.setVisibility(View.VISIBLE);
                appCompatImageView.setVisibility(View.VISIBLE);
            }else {
                relativeLayout.setVisibility(View.GONE);
                appCompatImageView.setVisibility(View.GONE);
            }
        }else {
            relativeLayout.setVisibility(View.GONE);
            appCompatImageView.setVisibility(View.GONE);
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
