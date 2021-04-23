package com.wankrfun.crania.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.MeetListBean;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: TaAboutQuestionsAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/23/21 3:54 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/23/21 3:54 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TaAboutQuestionsAdapter extends BannerAdapter<List<MeetListBean.DataBean.ListBean.BioQAsBean>, TaAboutQuestionsAdapter.BannerViewHolder> {
    public Context context;
    public TaAboutQuestionsAdapter(Context context, List<List<MeetListBean.DataBean.ListBean.BioQAsBean>> beanList) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(beanList);
        this.context = context;
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public TaAboutQuestionsAdapter.BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mine_about_questions, parent,false);
        return new TaAboutQuestionsAdapter.BannerViewHolder(view);
    }

    @Override
    public void onBindView(TaAboutQuestionsAdapter.BannerViewHolder holder, List<MeetListBean.DataBean.ListBean.BioQAsBean> item, int position, int size) {
        switch (item.size()){
            case 1:
                holder.tv_question.setText(item.get(0).getQuestion());
                holder.tv_answer.setText(item.get(0).getAnswer());
                holder.tv_question2.setText("尚未添加更多问答");
                holder.tv_answer2.setVisibility(View.INVISIBLE);
                holder.tv_question3.setText("尚未添加更多问答");
                holder.tv_answer3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                holder.tv_question.setText(item.get(0).getQuestion());
                holder.tv_answer.setText(item.get(0).getAnswer());
                holder.tv_question2.setText(item.get(1).getQuestion());
                holder.tv_answer2.setText(item.get(1).getAnswer());
                holder.tv_question3.setText("尚未添加更多问答");
                holder.tv_answer3.setVisibility(View.INVISIBLE);
                break;
            case 3:
                holder.tv_question.setText(item.get(0).getQuestion());
                holder.tv_answer.setText(item.get(0).getAnswer());
                holder.tv_question2.setText(item.get(1).getQuestion());
                holder.tv_answer2.setText(item.get(1).getAnswer());
                holder.tv_question3.setText(item.get(2).getQuestion());
                holder.tv_answer3.setText(item.get(2).getAnswer());
                break;
        }
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_question;
        AppCompatTextView tv_answer;
        AppCompatTextView tv_question2;
        AppCompatTextView tv_answer2;
        AppCompatTextView tv_question3;
        AppCompatTextView tv_answer3;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_question = itemView.findViewById(R.id.tv_question);
            tv_answer = itemView.findViewById(R.id.tv_answer);
            tv_question2 = itemView.findViewById(R.id.tv_question2);
            tv_answer2 = itemView.findViewById(R.id.tv_answer2);
            tv_question3 = itemView.findViewById(R.id.tv_question3);
            tv_answer3 = itemView.findViewById(R.id.tv_answer3);
        }
    }
}
