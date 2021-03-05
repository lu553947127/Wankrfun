package com.wankrfun.crania.view.events;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.EventsAddAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.BaseBean;
import com.wankrfun.crania.dialog.BubbleTipDialog;
import com.wankrfun.crania.utils.RefreshUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.events
 * @ClassName: EventsAddActivity
 * @Description: 活动添加页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/29/21 1:45 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/29/21 1:45 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsAddActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private final List<BaseBean> baseBeanList = new ArrayList<>();
    private int image;
    private String title, type, typeChildren;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_events_add;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        baseBeanList.addAll(RefreshUtils.getActivityListAdd());

        recyclerView.setLayoutManager(new GridLayoutManager(activity, 5, LinearLayoutManager.VERTICAL, false));
        EventsAddAdapter eventsAddAdapter = new EventsAddAdapter(R.layout.adapter_events_add, baseBeanList);
        recyclerView.setAdapter(eventsAddAdapter);

        eventsAddAdapter.setIsSelect(eventsAddAdapter.getData().get(0).getName());

        image = eventsAddAdapter.getData().get(0).getImages();
        title = eventsAddAdapter.getData().get(0).getName();
        type = "0";
        typeChildren = "0";

        eventsAddAdapter.setOnItemClickListener((adapter, view, position) -> {
            eventsAddAdapter.setIsSelect(eventsAddAdapter.getData().get(position).getName());
            image = eventsAddAdapter.getData().get(position).getImages();
            title = eventsAddAdapter.getData().get(position).getName();
            type = String.valueOf(position);
        });

        eventsAddAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            type = String.valueOf(position);
            title = eventsAddAdapter.getData().get(position).getName();
            eventsAddAdapter.setIsSelect(eventsAddAdapter.getData().get(position).getName());
            BubbleTipDialog bubbleTipDialog = new BubbleTipDialog(activity, eventsAddAdapter.getData().get(position).getName()).setClickedView(view);
            bubbleTipDialog.setClickListener((image, pos) -> {
                this.image = image;
                typeChildren = pos;
                baseBeanList.get(position).setImages(image);
                eventsAddAdapter.setNewData(baseBeanList);
                bubbleTipDialog.cancel();
            });
            bubbleTipDialog.show();
            return false;
        });
    }

    @OnClick({R.id.tv_back,R.id.tv_yes})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_yes://下一步
                Bundle bundle = new Bundle();
                bundle.putInt("image", image);
                bundle.putString("title", title);
                bundle.putString("type", type);
                bundle.putString("typeChildren", typeChildren);
                ActivityUtils.startActivity(bundle, EventsAddInfoActivity.class);
                break;
        }
    }
}
