package com.wankrfun.crania.adapter;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.ExpiredListBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.widget.CircleImageView;
import com.wankrfun.crania.widget.CornerImageView;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineCompleteChildrenAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 1:08 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 1:08 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineCompleteChildrenAdapter extends BaseQuickAdapter<ExpiredListBean, BaseViewHolder> {
    public MineCompleteChildrenAdapter(int layoutResId, @Nullable List<ExpiredListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExpiredListBean item) {
        helper.setText(R.id.tv_title, item.getEventDesc())
                .setText(R.id.tv_name, item.getCreator_name())
                .setImageResource(R.id.iv_sex, item.getCreator_sex().equals("female") ? R.drawable.icon_sex_female : R.drawable.icon_sex_male);

        CircleImageView circleImageView = helper.getView(R.id.iv_avatar);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getCreator_image())
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(circleImageView)
                .build());

        CornerImageView cornerImageView = helper.getView(R.id.iv_image);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getEventImage())
                .placeholder(R.drawable.icon_images_empty)
                .errorPic(R.drawable.icon_images_empty)
                .imageView(cornerImageView)
                .build());

        if (TextUtils.isEmpty(item.getEventImage())){
            cornerImageView.setVisibility(View.GONE);
        }else {
            cornerImageView.setVisibility(View.VISIBLE);
        }

        AppCompatImageView iv_icon = helper.getView(R.id.iv_icon);
        if (!TextUtils.isEmpty(item.getEventTypeIcon())){
            EventsUtils.getEventsIcon(iv_icon, iv_icon, item.getEventType(), item.getEventTypeIcon());
        }else {
            EventsUtils.getEventsIcon(iv_icon, iv_icon, item.getEventType(), "0");
        }
    }
}
