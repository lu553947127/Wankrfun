package com.wankrfun.crania.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.EventsCommentsBean;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.widget.CircleImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: EventsCommentAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/27/21 2:55 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/27/21 2:55 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsCommentAdapter extends BaseQuickAdapter<EventsCommentsBean.DataBean.CommentsBean, BaseViewHolder>  {
    public EventsCommentAdapter(int layoutResId, @Nullable List<EventsCommentsBean.DataBean.CommentsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventsCommentsBean.DataBean.CommentsBean item) {
        helper.setText(R.id.tv_name, item.getComment_userName())
                .setText(R.id.tv_content, item.getContent())
                .setText(R.id.tv_time, NumberUtils.dateToStamp(item.getTime()));
        CircleImageView imageView = helper.getView(R.id.iv_avatar);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getComment_userPhoto())
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(imageView)
                .build());
        RecyclerView recyclerView = helper.getView(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        EventsCommentChildrenAdapter eventsCommentChildrenAdapter = new EventsCommentChildrenAdapter(R.layout.adapter_events_comment_children, item.getReplys());
        recyclerView.setAdapter(eventsCommentChildrenAdapter);

        eventsCommentChildrenAdapter.setOnItemClickListener((adapter, view, position) -> {
            EventsCommentsBean.DataBean.CommentsBean.ReplysBean listBean = eventsCommentChildrenAdapter.getData().get(position);
            new XPopup.Builder(mContext).asConfirm(mContext.getResources().getString(R.string.reminder), "您确定要删除这条评论吗", () -> {
                eventsCommentChildrenAdapter.remove(position);
                EventBus.getDefault().post(new EventsEvent("delete_reply", listBean.getObjectId()));
            }).show();
        });
    }
}
