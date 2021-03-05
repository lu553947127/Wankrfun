package com.wankrfun.crania.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.BuildConfig;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.utils.LoginUtils;
import com.wankrfun.crania.utils.SharedUtils;
import com.wankrfun.crania.widget.CustomWebView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: AgreementDialog
 * @Description: 协议弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 4:34 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 4:34 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint({"StaticFieldLeak","InflateParams","SetTextI18n","CutPasteId"})
public class AgreementDialog {

    private final BaseActivity activity;
    private final Dialog dialog;
    private OnCustomClickListener onCustomClickListener;

    public AgreementDialog(BaseActivity activity) {
        this.activity = activity;
        dialog = new Dialog(activity, R.style.custom_dialog);
    }

    public interface OnCustomClickListener {
        void onClickListener();
    }

    public void setOnCustomClickListener(OnCustomClickListener onCustomClickListener) {
        this.onCustomClickListener = onCustomClickListener;
    }

    // 显示软件更新对话框
    public void showDialog() {
        if (dialog.isShowing()) return;
        dialog.setCancelable(false);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_agreement, null);
        CustomWebView webView = view.findViewById(R.id.web_view);
        TextView tv_agree = view.findViewById(R.id.tv_agree);
        TextView tv_disagree = view.findViewById(R.id.tv_disagree);

        webView.loadUrl(BuildConfig.AGREEMENT_URL);

        SharedUtils sharedUtils = new SharedUtils(activity);

        tv_agree.setOnClickListener(v -> {
            sharedUtils.addShared(SpConfig.FIRST_APP,"1","first");
            onCustomClickListener.onClickListener();
            dialog.dismiss();
        });

        tv_disagree.setOnClickListener(v -> {
            dialog.dismiss();
            LoginUtils.getExitApp(activity);
        });

        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        dialog.setContentView(view, new ViewGroup.MarginLayoutParams(displayMetrics.widthPixels, ViewGroup.MarginLayoutParams.MATCH_PARENT));
        dialog.show();
    }
}
