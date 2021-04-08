package com.wankrfun.crania.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.BaseBean;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineLifeTypeAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/23/21 10:03 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/23/21 10:03 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineLifeTypeAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {
    public MineLifeTypeAdapter(int layoutResId, @Nullable List<BaseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        helper.setText(R.id.tv_title,item.getName())
                .setImageResource(R.id.iv_image, item.getImages());
    }
}
