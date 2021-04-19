package com.wankrfun.crania.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.EventsBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.widget.CircleImageView;
import com.wankrfun.crania.widget.CornerImageView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

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
@SuppressLint("CheckResult")
public class EventsAdapter extends BaseQuickAdapter<EventsBean.DataBean.ListBean, BaseViewHolder> {
    public EventsAdapter(int layoutResId, @Nullable List<EventsBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventsBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_title,item.getEvent_contents())
                .setText(R.id.tv_title2, item.getEvent_contents())
                .setText(R.id.tv_location, item.getEvent_address())
                .setText(R.id.tv_location2, item.getEvent_address())
                .setText(R.id.tv_clock, item.getActivity_time().equals("2200-01-01T08:30:00.000Z") ? "不限时间" : NumberUtils.dealDateFormatNew(item.getActivity_time()))
                .setText(R.id.tv_clock2, item.getActivity_time().equals("2200-01-01T08:30:00.000Z") ? "不限时间" : NumberUtils.dealDateFormatNew(item.getActivity_time()))
                .setText(R.id.tv_address, "距离你" + NumberUtils.getDistance(Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)), Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)), item.getEventLocation().getLongitude(), item.getEventLocation().getLatitude()) + "Km")
                .setText(R.id.tv_address2, "距离你" + NumberUtils.getDistance(Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE)), Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE)), item.getEventLocation().getLongitude(), item.getEventLocation().getLatitude()) + "Km")
                .setText(R.id.tv_num, item.getFav_user_num() + "人想去")
                .setText(R.id.tv_num2, item.getFav_user_num() + "人想去")
                .setText(R.id.tv_sing_num, item.getJoined_user_num() + "/" + (item.getMax_num() == 0 ? "不限人数" : item.getMax_num()))
                .setText(R.id.tv_sing_num2, item.getJoined_user_num() + "/" + (item.getMax_num() == 0 ? "不限人数" : item.getMax_num()))
                .setImageResource(R.id.iv_sex, item.getCreator_sex().equals("female") ? R.drawable.icon_sex_female : R.drawable.icon_sex_male)
                .setImageResource(R.id.iv_sex2, item.getCreator_sex().equals("female") ? R.drawable.icon_sex_female : R.drawable.icon_sex_male)
                .addOnClickListener(R.id.tv_sign)
                .addOnClickListener(R.id.tv_sign2);

        CornerImageView cornerImageView = helper.getView(R.id.iv_image);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getEventImage())
                .placeholder(R.drawable.ic_empty_zhihu)
                .errorPic(R.drawable.ic_empty_zhihu)
                .imageView(cornerImageView)
                .build());

        RelativeLayout rl_image = helper.getView(R.id.rl_image);
        RelativeLayout rl_empty = helper.getView(R.id.rl_empty);
        //若活动图片为空 显示文字
        if (TextUtils.isEmpty(item.getEventImage())){
            rl_empty.setVisibility(View.VISIBLE);
            rl_image.setVisibility(View.GONE);
        }else {
            rl_empty.setVisibility(View.GONE);
            rl_image.setVisibility(View.VISIBLE);
        }

        CircleImageView circleImageView = helper.getView(R.id.iv_avatar);
        CircleImageView circleImageView2 = helper.getView(R.id.iv_avatar2);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getCreator_image())
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(circleImageView)
                .build());
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getCreator_image())
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(circleImageView2)
                .build());

        RelativeLayout relativeLayout = helper.getView(R.id.rl);
        relativeLayout.getBackground().setAlpha(220);
        RelativeLayout relativeLayout2 = helper.getView(R.id.rl2);
        relativeLayout2.getBackground().setAlpha(220);

        AppCompatTextView tv_type = helper.getView(R.id.tv_type);
        AppCompatTextView tv_type2 = helper.getView(R.id.tv_type2);
        AppCompatImageView iv_type = helper.getView(R.id.iv_type);
        AppCompatImageView iv_type2 = helper.getView(R.id.iv_type2);

        EventsUtils.getEventsIcon(tv_type, tv_type2, iv_type, iv_type2, item.getEventType(), item.getEventTypeIcon());

        LinearLayout ll_a = helper.getView(R.id.ll_a);
        LinearLayout ll_b = helper.getView(R.id.ll_b);
        LinearLayout ll_a2 = helper.getView(R.id.ll_a2);
        LinearLayout ll_b2 = helper.getView(R.id.ll_b2);

        //开始延时5秒执行，每次间隔5秒循环展示布局
        Observable.interval(5, 15, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            if (!item.isShow()){
                item.setShow(true);
                ll_a.setVisibility(View.GONE);
                ll_b.setVisibility(View.VISIBLE);
                ll_a2.setVisibility(View.GONE);
                ll_b2.setVisibility(View.VISIBLE);
            }else {
                item.setShow(false);
                ll_a.setVisibility(View.VISIBLE);
                ll_b.setVisibility(View.GONE);
                ll_a2.setVisibility(View.VISIBLE);
                ll_b2.setVisibility(View.GONE);
            }
        });

        AppCompatTextView tv_sign = helper.getView(R.id.tv_sign);
        AppCompatTextView tv_sign2 = helper.getView(R.id.tv_sign2);
        if (SPUtils.getInstance().getString(SpConfig.USER_ID).equals(item.getEventCreator())){
            tv_sign.setBackgroundResource(R.drawable.shape_gray_15);
            tv_sign2.setBackgroundResource(R.drawable.shape_gray_15);
        }else {
            tv_sign.setBackgroundResource(R.drawable.shape_yellow_15);
            tv_sign2.setBackgroundResource(R.drawable.shape_yellow_15);
        }

        if (item.isJoined()){
            tv_sign.setText("已报名");
            tv_sign2.setText("已报名");
        }else {
            tv_sign.setText("报名");
            tv_sign2.setText("报名");
        }
    }
}
