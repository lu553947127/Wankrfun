package com.wankrfun.crania.adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.BaseBean;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: EventsAddChildrenAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/29/21 3:53 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/29/21 3:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsAddChildrenAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {
    public EventsAddChildrenAdapter(int layoutResId, @Nullable List<BaseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        AppCompatImageView imageView = helper.getView(R.id.iv_image);
        imageView.setImageResource(item.getImages());
    }
}
