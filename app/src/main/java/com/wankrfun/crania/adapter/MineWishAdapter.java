package com.wankrfun.crania.adapter;

import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.WishListBean;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineWishAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/22/21 1:46 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/22/21 1:46 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineWishAdapter extends BaseQuickAdapter<WishListBean.DataBean.ListBean, BaseViewHolder> {
    public MineWishAdapter(int layoutResId, @Nullable List<WishListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WishListBean.DataBean.ListBean item) {
        RelativeLayout relativeLayout = helper.getView(R.id.rl);
        relativeLayout.getBackground().setAlpha(60);

        if (item.getContent().equals("更多")){
            if (helper.getPosition() == 0){
                helper.setText(R.id.tv_title, "添加一个心愿")
                        .setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.color_FEFFD6));
            }else {
                helper.setText(R.id.tv_title, "添加下一个心愿")
                        .setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.color_FEFFD6));
            }
            helper.setVisible(R.id.tv_add, true);
        }else {
            helper.setText(R.id.tv_title, item.getContent())
                    .setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.white))
                    .setVisible(R.id.tv_add, false);
        }
    }
}
