package com.wankrfun.crania.view.mine.about;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineQuestionAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.QuestionListBean;
import com.wankrfun.crania.event.CardEvent;
import com.wankrfun.crania.utils.DensityUtil;
import com.wankrfun.crania.viewmodel.MineCardViewModel;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineQuestionActivity
 * @Description: 问答卡片列表页面
 * @Author: 鹿鸿祥
 * @CreateDate: 4/22/21 1:54 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/22/21 1:54 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineQuestionActivity extends BaseActivity {
    @BindView(R.id.rv)
    SwipeRecyclerView recyclerView;
    //创建侧滑菜单
    private final SwipeMenuCreator creator = (leftMenu, rightMenu, position) -> {
        SwipeMenuItem deleteItem = new SwipeMenuItem(this)
                .setBackgroundColorResource(R.color.color_FF0000)
                .setText("删除")
                .setTextColor(Color.WHITE)
                .setTextSize(14)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                .setWidth(DensityUtil.dp2px(50));
        rightMenu.addMenuItem(deleteItem);
    };
    private MineCardViewModel mineCardViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_question;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        MineQuestionAdapter mineQuestionAdapter  = new MineQuestionAdapter(R.layout.adapter_mine_question, null);
        //设置侧滑菜单列表
        recyclerView.setSwipeMenuCreator(creator);
        //侧滑事件监听回调
        recyclerView.setOnItemMenuClickListener((menuBridge, adapterPosition) -> {
            if (menuBridge.getPosition() == 0) {//删除
                new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "您确定要删除吗", () -> {
                    mineCardViewModel.getQuestionDelete(mineQuestionAdapter.getData().get(adapterPosition).getObjectId());
                    mineQuestionAdapter.remove(adapterPosition);
                    menuBridge.closeMenu();
                }, menuBridge::closeMenu).show();
            }
        });
        recyclerView.setAdapter(mineQuestionAdapter);

        mineQuestionAdapter.setOnItemClickListener((adapter, view, position) -> {
            QuestionListBean.DataBean.ListBean listBean = mineQuestionAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.getObjectId());
            bundle.putString("question", listBean.getQuestion());
            bundle.putString("answer", listBean.getAnswer());
            ActivityUtils.startActivity(bundle, MineQuestionAddInfoActivity.class);
        });

        mineCardViewModel = getViewModel(MineCardViewModel.class);

        //获取问答列表返回数据
        mineCardViewModel.questionListLiveData.observe(this, questionListBean -> {
            mineQuestionAdapter.setNewData(questionListBean.getData().getList());
        });

        //删除问题成功返回结果
        mineCardViewModel.questionDeleteLiveData.observe(this, challengeStatusBean -> {
            mineCardViewModel.getQuestionList(SPUtils.getInstance().getString(SpConfig.USER_ID));
        });
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_complete, R.id.tv_add})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_complete://完成
                EventBus.getDefault().post(new CardEvent("questions"));
                finish();
                break;
            case R.id.tv_add://添加问答
                ActivityUtils.startActivity(MineQuestionAddActivity.class);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mineCardViewModel.getQuestionList(SPUtils.getInstance().getString(SpConfig.USER_ID));
    }
}
