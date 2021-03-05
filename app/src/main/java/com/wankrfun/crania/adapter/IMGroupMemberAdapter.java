package com.wankrfun.crania.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.ImGroupMembersBean;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.widget.CornerImageView;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: IMGroupMemberAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/20/21 4:15 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/20/21 4:15 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class IMGroupMemberAdapter extends BaseQuickAdapter<ImGroupMembersBean.DataBean.MembersBean, BaseViewHolder> {
    private final String type;
    public IMGroupMemberAdapter(int layoutResId, @Nullable List<ImGroupMembersBean.DataBean.MembersBean> data, String type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, ImGroupMembersBean.DataBean.MembersBean item) {
        helper.setText(R.id.tv_name,item.getNickname())
                .setImageResource(R.id.iv_icon, item.getSex().equals("female") ? R.drawable.icon_sex_female : R.drawable.icon_sex_male);
        CornerImageView imageView = helper.getView(R.id.iv_image);
        ImageLoader.load(mContext, new ImageConfig.Builder()
                .url(item.getAvatar())
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(imageView)
                .build());

        //最多只显示5个
        LinearLayout linearLayout = helper.getView(R.id.ll);
        if (!TextUtils.isEmpty(type) && helper.getAdapterPosition() > 4){
            linearLayout.setVisibility(View.GONE);
        }else {
            linearLayout.setVisibility(View.VISIBLE);
        }
    }
}
