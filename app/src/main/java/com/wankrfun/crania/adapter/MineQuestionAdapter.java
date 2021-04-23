package com.wankrfun.crania.adapter;

import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.QuestionListBean;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineQuestionAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/23/21 10:30 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/23/21 10:30 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineQuestionAdapter extends BaseQuickAdapter<QuestionListBean.DataBean.ListBean, BaseViewHolder> {
    public MineQuestionAdapter(int layoutResId, @Nullable List<QuestionListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionListBean.DataBean.ListBean item) {
        RelativeLayout relativeLayout = helper.getView(R.id.rl);
        relativeLayout.getBackground().setAlpha(60);
        helper.setText(R.id.tv_question, "问：" + item.getQuestion())
                .setText(R.id.tv_answer, "答：" + item.getAnswer());
    }
}
