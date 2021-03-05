package com.wankrfun.crania.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.MatchingListBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.widget.CornerImageView;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineMatchingChildrenAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/29/21 1:09 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/29/21 1:09 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineMatchingChildrenAdapter extends BaseQuickAdapter<MatchingListBean, BaseViewHolder> {
    public MineMatchingChildrenAdapter(int layoutResId, @Nullable List<MatchingListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MatchingListBean item) {
        helper.setText(R.id.tv_name,item.getName())
                .setImageResource(R.id.iv_sex, item.getSex().equals("female") ? R.drawable.icon_sex_female : R.drawable.icon_sex_male)
                .addOnClickListener(R.id.iv_message);
        CornerImageView imageView = helper.getView(R.id.iv_image);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getPhoto())
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(imageView)
                .build());
        View view = helper.getView(R.id.view);
        view.getBackground().setAlpha(110);
    }
}
