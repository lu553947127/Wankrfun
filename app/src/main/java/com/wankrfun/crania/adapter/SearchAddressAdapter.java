package com.wankrfun.crania.adapter;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.amap.api.services.core.PoiItem;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.utils.HtmlUtils;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: SearchAddressAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/2/21 4:44 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/2/21 4:44 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SearchAddressAdapter extends BaseQuickAdapter<PoiItem, BaseViewHolder> {
    private String keyword, is_select, type;
    public SearchAddressAdapter(int layoutResId, @Nullable List<PoiItem> data) {
        super(layoutResId, data);
    }

    public SearchAddressAdapter(int layoutResId, @Nullable List<PoiItem> data, String type) {
        super(layoutResId, data);
        this.type = type;
    }

    /**
     * 设置关键字
     *
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 设置选中效果
     * @param is_select
     */
    public void setIsSelect(String is_select) {
        this.is_select = is_select;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiItem item) {
        if (TextUtils.isEmpty(type)){
            helper.setText(R.id.tv_title, StringUtils.isEmpty(item.getSnippet()) ? "找不到位置信息" : HtmlUtils.setSpan(mContext, item.getSnippet(), keyword));
        }else {
            helper.setText(R.id.tv_title, StringUtils.isEmpty(item.getCityName() + "," + item.getProvinceName()) ? "找不到位置信息" : HtmlUtils.setSpan(mContext, item.getCityName() + "," + item.getProvinceName(), keyword));
        }

        AppCompatImageView appCompatImageView = helper.getView(R.id.iv_select);
        if (!TextUtils.isEmpty(is_select)){
            if (is_select.equals(item.getPoiId())){
                appCompatImageView.setVisibility(View.VISIBLE);
                helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.colorPrimaryDark));
            }else {
                appCompatImageView.setVisibility(View.GONE);
                helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.white));
            }
        }else {
            appCompatImageView.setVisibility(View.GONE);
            helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.white));
        }
    }
}
