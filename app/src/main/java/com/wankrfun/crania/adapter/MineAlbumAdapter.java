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
 * @ClassName: MineAlbumAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 8:52 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 8:52 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineAlbumAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MineAlbumAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        CornerImageView cornerImageView = helper.getView(R.id.iv_image);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item)
                .placeholder(R.drawable.icon_images_empty2)
                .errorPic(R.drawable.icon_images_empty2)
                .imageView(cornerImageView)
                .build());
    }
}
