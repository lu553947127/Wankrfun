package com.wankrfun.crania.view.mine.about;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.QuestionTemplateAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.viewmodel.MineCardViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineQuestionAddActivity
 * @Description: 获取问答模版页面
 * @Author: 鹿鸿祥
 * @CreateDate: 4/23/21 10:50 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/23/21 10:50 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineQuestionAddActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private MineCardViewModel mineCardViewModel;
    private final List<String> lists = new ArrayList<>();

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_question_add;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        QuestionTemplateAdapter questionTemplateAdapter  = new QuestionTemplateAdapter(R.layout.adapter_question_template, null);
        recyclerView.setAdapter(questionTemplateAdapter);

        questionTemplateAdapter.setOnItemClickListener((adapter, view, position) -> {
            String question = questionTemplateAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("question", question);
            ActivityUtils.startActivity(bundle, MineQuestionAddInfoActivity.class);
        });

        mineCardViewModel = getViewModel(MineCardViewModel.class);

        //获取问答模版列表返回结果
        mineCardViewModel.questionTemplateLiveData.observe(this, questionTemplateListBean -> {
            lists.clear();
            lists.add(getRandomData(questionTemplateListBean.getData().getList()));
            lists.add(getRandomData(questionTemplateListBean.getData().getList()));
            lists.add(getRandomData(questionTemplateListBean.getData().getList()));
            questionTemplateAdapter.setNewData(lists);
        });

        mineCardViewModel.getQuestionTemplateList(SPUtils.getInstance().getString(SpConfig.USER_ID));
    }

    @OnClick({R.id.iv_bar_back, R.id.iv_refresh})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.iv_refresh://换一批
                mineCardViewModel.getQuestionTemplateList(SPUtils.getInstance().getString(SpConfig.USER_ID));
                break;
        }
    }

    /**
     * 获取随机问答
     *
     * @param listBeans
     * @return
     */
    private String getRandomData(List<String> listBeans){
        String title = "";
        Random random = new Random();
        int pos = random.nextInt(listBeans.size());
        title = listBeans.get(pos);
        return title;
    }
}
