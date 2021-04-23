package com.wankrfun.crania.adapter;

import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: QuestionTemplateAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/23/21 11:01 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/23/21 11:01 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class QuestionTemplateAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public QuestionTemplateAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RelativeLayout relativeLayout = helper.getView(R.id.rl);
        relativeLayout.getBackground().setAlpha(60);
        helper.setText(R.id.tv_title, item);
    }
}
