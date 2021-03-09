package com.wankrfun.crania.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.UserEventsListBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.widget.CornerImageView;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineInitiateAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 10:29 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 10:29 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineInitiateAdapter extends BaseQuickAdapter<UserEventsListBean.DataBean.ActiveListBean, BaseViewHolder> {
    public MineInitiateAdapter(int layoutResId, @Nullable List<UserEventsListBean.DataBean.ActiveListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserEventsListBean.DataBean.ActiveListBean item) {
        helper.setText(R.id.tv_title,item.getEventDesc())
                .setText(R.id.tv_title2, item.getEventDesc())
                .setText(R.id.tv_time, item.getActivity_time().equals("2200-01-01T08:30:00.000Z") ? "不限时间" : NumberUtils.dealDateFormat(item.getActivity_time()))
                .setText(R.id.tv_time2, item.getActivity_time().equals("2200-01-01T08:30:00.000Z") ? "不限时间" : NumberUtils.dealDateFormat(item.getActivity_time()))
                .setText(R.id.tv_location, item.getEvent_address())
                .setText(R.id.tv_location2, item.getEvent_address())
                .setText(R.id.tv_people_num, item.getJoined_user_num())
                .setText(R.id.tv_people_num2, item.getJoined_user_num())
                .addOnClickListener(R.id.iv_message)
                .addOnClickListener(R.id.iv_message2);

        RelativeLayout relativeLayout = helper.getView(R.id.rl);
        RelativeLayout relativeLayout2 = helper.getView(R.id.rl2);
        if (TextUtils.isEmpty(item.getEventImage())){
            relativeLayout.setVisibility(View.GONE);
            relativeLayout2.setVisibility(View.VISIBLE);
        }else {
            relativeLayout.setVisibility(View.VISIBLE);
            relativeLayout2.setVisibility(View.GONE);
        }

        CornerImageView cornerImageView = helper.getView(R.id.iv_image);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getEventImage())
                .placeholder(R.drawable.ic_empty_zhihu)
                .errorPic(R.drawable.ic_empty_zhihu)
                .imageView(cornerImageView)
                .build());

        AppCompatImageView iv_icon = helper.getView(R.id.iv_icon);
        AppCompatImageView iv_icon2 = helper.getView(R.id.iv_icon2);

        EventsUtils.getEventsIcon( iv_icon, iv_icon2, item.getEventType(), item.getEventTypeIcon());

        AppCompatTextView tv_type = helper.getView(R.id.tv_type);
        AppCompatTextView tv_type2 = helper.getView(R.id.tv_type2);

        if (SPUtils.getInstance().getString(SpConfig.USER_ID).equals(item.getEventCreator())){
            tv_type.setVisibility(View.GONE);
            tv_type2.setVisibility(View.GONE);
        }else {
            switch (item.getUserStatus()){
                case "applied"://已报名
                    tv_type.setVisibility(View.VISIBLE);
                    tv_type2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_type, "等待中")
                            .setText(R.id.tv_type2, "等待中");
                    break;
                case "joined"://已加入
                    tv_type.setVisibility(View.VISIBLE);
                    tv_type2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_type, "已通过")
                            .setText(R.id.tv_type2, "已通过");
                    break;
                case "rejected"://已拒绝
                    tv_type.setVisibility(View.VISIBLE);
                    tv_type2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_type, "被拒绝")
                            .setText(R.id.tv_type2, "被拒绝");
                    break;
                case "not_applied"://未报名
                    tv_type.setVisibility(View.GONE);
                    tv_type2.setVisibility(View.GONE);
                    break;
            }
        }
    }
}
