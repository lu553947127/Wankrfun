package com.wankrfun.crania.adapter;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.MatchingListBean;
import com.wankrfun.crania.view.mine.user.UserInfoActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineMatchingAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/29/21 1:04 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/29/21 1:04 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineMatchingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Map<String, String> mapList = new HashMap<>();
    private String type;
    public MineMatchingAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    /**
     * 获取匹配数据map集合
     *
     * @param mapList
     */
    public void setMapList(Map<String, String> mapList) {
        this.mapList = mapList;
    }

    /**
     * 判断当前是否为谁喜欢我/匹配列表
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_time, item);
        List<MatchingListBean> matchingListBeanList = new Gson().fromJson(mapList.get(item),new TypeToken<List<MatchingListBean>>(){}.getType());
        RecyclerView recyclerView = helper.getView(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false));
        MineMatchingChildrenAdapter mineMatchingChildrenAdapter = new MineMatchingChildrenAdapter(R.layout.adapter_mine_matching_children, matchingListBeanList);
        recyclerView.setAdapter(mineMatchingChildrenAdapter);
        mineMatchingChildrenAdapter.setType(type);

        mineMatchingChildrenAdapter.setOnItemClickListener((adapter, view, position) -> {
            MatchingListBean listBean = mineMatchingChildrenAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.getObjectId());
            bundle.putString("sex", listBean.getSex());
            ActivityUtils.startActivity(bundle, UserInfoActivity.class);
        });

        mineMatchingChildrenAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (!TextUtils.isEmpty(type)){
                return;
            }
            MatchingListBean listBean = mineMatchingChildrenAdapter.getData().get(position);
            RongIM.getInstance().startPrivateChat(mContext, listBean.getObjectId(), listBean.getName());
        });
    }
}
