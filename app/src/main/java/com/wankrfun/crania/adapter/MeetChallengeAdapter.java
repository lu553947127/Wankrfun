package com.wankrfun.crania.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.ChallengeListBean;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MeetChallengeAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/9/21 9:31 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/9/21 9:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetChallengeAdapter extends BaseQuickAdapter<ChallengeListBean.DataBean.RecordsBean, BaseViewHolder> {
    public MeetChallengeAdapter(int layoutResId, @Nullable List<ChallengeListBean.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChallengeListBean.DataBean.RecordsBean item) {
        helper.setText(R.id.tv_title, item.getQuestion())
                .setText(R.id.tv_a, "A." + item.getChoiceA())
                .setText(R.id.tv_b, "B." + item.getChoiceB())
                .setVisible(R.id.iv_a, item.getChosen().equals("A"))
                .setVisible(R.id.iv_b, item.getChosen().equals("B"));
    }
}
