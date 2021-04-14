package com.wankrfun.crania.dialog;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.CenterPopupView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.ChallengeListBean;
import com.wankrfun.crania.bean.ChallengeTemplateBean;
import com.wankrfun.crania.viewmodel.MeetViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: MeetChallengeDialog
 * @Description: 默契卡片添加/编辑弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 4/9/21 10:37 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/9/21 10:37 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetChallengeDialog extends CenterPopupView {
    private Context activity;
    private MeetViewModel meetViewModel;
    private AppCompatTextView tv_content;
    private AppCompatEditText et_a, et_b;
    private AppCompatImageView iv_a, iv_b;
    private List<ChallengeTemplateBean.DataBean.ListBean> listBeans;
    private ChallengeListBean.DataBean.RecordsBean listBean;
    private String question, choiceA, choiceB, chosen, objectId;

    public MeetChallengeDialog(@NonNull Context context, MeetViewModel meetViewModel, ChallengeListBean.DataBean.RecordsBean listBean) {
        super(context);
        this.activity = context;
        this.meetViewModel = meetViewModel;
        this.listBean = listBean;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_meet_challenge;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        AppCompatTextView tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        et_a = findViewById(R.id.et_a);
        et_b = findViewById(R.id.et_b);
        iv_a = findViewById(R.id.iv_a);
        iv_b = findViewById(R.id.iv_b);

        if (listBean == null){
            tv_title.setText("设置默契挑战卡片");
            chosen = "A";
        }else {
            tv_title.setText("编辑默契挑战卡片");
            tv_content.setText(listBean.getQuestion());
            et_a.setText("A." + listBean.getChoiceA());
            et_b.setText("B." + listBean.getChoiceB());
            et_a.setSelection(et_a.length());
            et_b.setSelection(et_b.length());

            objectId = listBean.getObjectId();
            question = listBean.getQuestion();
            chosen = listBean.getChosen();
            if (chosen.equals("A")){
                iv_a.setImageResource(R.drawable.icon_challenge_selected);
                iv_b.setImageResource(R.drawable.icon_challenge_unselected);
            }else {
                iv_a.setImageResource(R.drawable.icon_challenge_unselected);
                iv_b.setImageResource(R.drawable.icon_challenge_selected);
            }
        }

        meetViewModel.getChallengeTemplate();
    }

    @Override
    protected void onShow() {
        super.onShow();

        //获取默契挑战模版返回数据
        meetViewModel.challengeTemplateLiveData.observe((LifecycleOwner) activity, challengeTemplateBean -> {
            listBeans = challengeTemplateBean.getData().getList();
            if (listBean == null){
                getRandomData();
            }
        });

        findViewById(R.id.iv_close).setOnClickListener(view -> dismiss());

        findViewById(R.id.tv_ok).setOnClickListener(view -> {
            choiceA = et_a.getText().toString().trim().replace("A.","");
            choiceB = et_b.getText().toString().trim().replace("B.","");

            if (choiceA.length() > 10){
                ToastUtils.showShort("答案A最多不能超过10个字符");
                return;
            }

            if (choiceB.length() > 10){
                ToastUtils.showShort("答案B最多不能超过10个字符");
                return;
            }
            EventBus.getDefault().post(new ChallengeListBean.DataBean.RecordsBean(question, choiceA, objectId, choiceB, chosen));
            dismiss();
        });

        iv_a.setOnClickListener(view -> {
            chosen = "A";
            iv_a.setImageResource(R.drawable.icon_challenge_selected);
            iv_b.setImageResource(R.drawable.icon_challenge_unselected);
        });

        iv_b.setOnClickListener(view -> {
            chosen = "B";
            iv_a.setImageResource(R.drawable.icon_challenge_unselected);
            iv_b.setImageResource(R.drawable.icon_challenge_selected);
        });

        findViewById(R.id.iv_refresh).setOnClickListener(view -> {
            getRandomData();
        });
    }

    /**
     * 获取随机数数据
     */
    private void getRandomData(){
        Random random = new Random();
        int pos = random.nextInt(listBeans.size());
        tv_content.setText(listBeans.get(pos).getQuestion());
        et_a.setText("A." + listBeans.get(pos).getChoices().get(0));
        et_b.setText("B." + listBeans.get(pos).getChoices().get(1));
        et_a.setSelection(et_a.length());
        et_b.setSelection(et_b.length());
        question = listBeans.get(pos).getQuestion();
    }
}
