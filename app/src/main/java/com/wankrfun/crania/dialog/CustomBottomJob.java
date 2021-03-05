package com.wankrfun.crania.dialog;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.BottomPopupView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.event.EventsEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: CustomBottomJob
 * @Description: 自定义选择职业底部弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 2/25/21 8:40 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/25/21 8:40 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomBottomJob extends BottomPopupView {
    private String job;
    private AppCompatImageView iv_student, iv_work, iv_free;
    private AppCompatTextView tv_student, tv_work, tv_free;
    public CustomBottomJob(@NonNull Context context, String job) {
        super(context);
        this.job = job;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_custom_job;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        iv_student = findViewById(R.id.iv_student);
        tv_student = findViewById(R.id.tv_student);
        iv_work = findViewById(R.id.iv_work);
        tv_work = findViewById(R.id.tv_work);
        iv_free = findViewById(R.id.iv_free);
        tv_free = findViewById(R.id.tv_free);
        getJobState();
    }

    @Override
    protected void onShow() {
        super.onShow();

        findViewById(R.id.tv_cancel).setOnClickListener(view -> dismiss());

        findViewById(R.id.tv_yes).setOnClickListener(view -> {
            if (TextUtils.isEmpty(job)){
                ToastUtils.showShort("职业选择不能为空哦");
                return;
            }
            EventBus.getDefault().post(new EventsEvent("job", job));
            dismiss();
        });

        findViewById(R.id.ll_student).setOnClickListener(view -> {
            job = "s:";
            getJobState();
        });

        findViewById(R.id.ll_work).setOnClickListener(view -> {
            job = "j:";
            getJobState();
        });

        findViewById(R.id.ll_free).setOnClickListener(view -> {
            job = "c:";
            getJobState();
        });
    }

    /**
     * 显示选择状态
     */
    public void getJobState(){
        switch (job){
            case "s:":
                findViewById(R.id.ll_student).setBackgroundResource(R.drawable.shape_yellow_5);
                findViewById(R.id.ll_work).setBackgroundResource(R.drawable.shape_gray_5);
                findViewById(R.id.ll_free).setBackgroundResource(R.drawable.shape_gray_5);
                iv_student.setImageResource(R.drawable.icon_job_student2);
                iv_work.setImageResource(R.drawable.icon_job_work1);
                iv_free.setImageResource(R.drawable.icon_job_free1);
                tv_student.setTextColor(getResources().getColor(R.color.color_FEFFD6));
                tv_work.setTextColor(getResources().getColor(R.color.white));
                tv_free.setTextColor(getResources().getColor(R.color.white));
                break;
            case "j:":
                findViewById(R.id.ll_student).setBackgroundResource(R.drawable.shape_gray_5);
                findViewById(R.id.ll_work).setBackgroundResource(R.drawable.shape_yellow_5);
                findViewById(R.id.ll_free).setBackgroundResource(R.drawable.shape_gray_5);
                iv_student.setImageResource(R.drawable.icon_job_student1);
                iv_work.setImageResource(R.drawable.icon_job_work2);
                iv_free.setImageResource(R.drawable.icon_job_free1);
                tv_student.setTextColor(getResources().getColor(R.color.white));
                tv_work.setTextColor(getResources().getColor(R.color.color_FEFFD6));
                tv_free.setTextColor(getResources().getColor(R.color.white));
                break;
            case "c:":
                findViewById(R.id.ll_student).setBackgroundResource(R.drawable.shape_gray_5);
                findViewById(R.id.ll_work).setBackgroundResource(R.drawable.shape_gray_5);
                findViewById(R.id.ll_free).setBackgroundResource(R.drawable.shape_yellow_5);
                iv_student.setImageResource(R.drawable.icon_job_student1);
                iv_work.setImageResource(R.drawable.icon_job_work1);
                iv_free.setImageResource(R.drawable.icon_job_free2);
                tv_student.setTextColor(getResources().getColor(R.color.white));
                tv_work.setTextColor(getResources().getColor(R.color.white));
                tv_free.setTextColor(getResources().getColor(R.color.color_FEFFD6));
                break;
        }
    }
}
