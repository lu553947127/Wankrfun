package com.wankrfun.crania.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.EventsParticipantsBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.widget.CornerImageView;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: EventsParticipantsAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/27/21 2:51 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/27/21 2:51 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsParticipantsAdapter extends BaseQuickAdapter<EventsParticipantsBean.DataBean.ParticipantsBean, BaseViewHolder> {
    public EventsParticipantsAdapter(int layoutResId, @Nullable List<EventsParticipantsBean.DataBean.ParticipantsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventsParticipantsBean.DataBean.ParticipantsBean item) {
        helper.setText(R.id.tv_name,item.getName())
                .setImageResource(R.id.iv_icon, item.getSex().equals("female") ? R.drawable.icon_sex_female : R.drawable.icon_sex_male);
        CornerImageView imageView = helper.getView(R.id.iv_image);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getPhoto())
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(imageView)
                .build());
    }
}
