package com.wankrfun.crania.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.EventsCommentsBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.widget.CircleImageView;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: EventsCommentChildrenAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/27/21 3:13 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/27/21 3:13 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsCommentChildrenAdapter extends BaseQuickAdapter<EventsCommentsBean.DataBean.CommentsBean.ReplysBean, BaseViewHolder> {
    public EventsCommentChildrenAdapter(int layoutResId, @Nullable List<EventsCommentsBean.DataBean.CommentsBean.ReplysBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventsCommentsBean.DataBean.CommentsBean.ReplysBean item) {
        helper.setText(R.id.tv_name, item.getReply_userName())
                .setText(R.id.tv_content, item.getContent())
                .setText(R.id.tv_time, NumberUtils.dateToStamp(item.getTime()));
        CircleImageView imageView = helper.getView(R.id.iv_avatar);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getReply_userPhoto())
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(imageView)
                .build());
    }
}
