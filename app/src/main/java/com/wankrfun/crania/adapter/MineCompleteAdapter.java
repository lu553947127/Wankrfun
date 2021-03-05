package com.wankrfun.crania.adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.ExpiredListBean;
import com.wankrfun.crania.view.events.EventsDetailActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: MineCompleteAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 12:32 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 12:32 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineCompleteAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Map<String, String> mapList = new HashMap<>();
    public MineCompleteAdapter(int layoutResId, @Nullable List<String> data) {
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

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_time, item);
        List<ExpiredListBean> expiredListBeanList = new Gson().fromJson(mapList.get(item),new TypeToken<List<ExpiredListBean>>(){}.getType());
        RecyclerView recyclerView = helper.getView(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        MineCompleteChildrenAdapter mineCompleteChildrenAdapter = new MineCompleteChildrenAdapter(R.layout.adapter_mine_complete_children, expiredListBeanList);
        recyclerView.setAdapter(mineCompleteChildrenAdapter);

        mineCompleteChildrenAdapter.setOnItemClickListener((adapter, view, position) -> {
            ExpiredListBean listBean = mineCompleteChildrenAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.getObjectId());
            bundle.putString("creator", listBean.getEventCreator());
            ActivityUtils.startActivity(bundle, EventsDetailActivity.class);
        });
    }
}
