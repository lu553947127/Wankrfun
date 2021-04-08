package com.wankrfun.crania.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.widget.CornerImageView;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineAboutImagesAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/7/21 11:16 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/7/21 11:16 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineAboutImagesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int position;
    public MineAboutImagesAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    /**
     * 设置选中效果
     * @param position
     */
    public void setIsSelect(int position) {
        this.position = position;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (position == helper.getPosition()){
            helper.setVisible(R.id.iv_select, true);
        }else {
            helper.setVisible(R.id.iv_select, false);
        }
        CornerImageView cornerImageView = helper.getView(R.id.iv_image);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item)
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(cornerImageView)
                .build());
    }
}
