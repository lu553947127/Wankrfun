package com.wankrfun.crania.adapter;

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
 * @ClassName: EventsAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/15/21 3:23 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/15/21 3:23 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {
    public EventsAdapter(int layoutResId, @Nullable List<BaseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
//        helper.setText(R.id.tv_title,item.getName());
//        AppCompatImageView imageView = helper.getView(R.id.iv_image);
//        ImageLoader.load(mContext, new ImageConfig.Builder()
//                .url("")
//                .placeholder(item.getImages())
//                .errorPic(item.getImages())
//                .imageView(imageView)
//                .build());

        RelativeLayout relativeLayout = helper.getView(R.id.rl);
        relativeLayout.getBackground().setAlpha(220);
    }
}
