package com.wankrfun.crania.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineMoreCardAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/31/21 1:38 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/31/21 1:38 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineMoreCardAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    public MineMoreCardAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        helper.setImageResource(R.id.iv_image, item);
    }
}
