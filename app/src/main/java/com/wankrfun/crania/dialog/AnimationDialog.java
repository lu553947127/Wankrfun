package com.wankrfun.crania.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.airbnb.lottie.LottieAnimationView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.AnimatorUtils;
import com.wankrfun.crania.utils.ParseUtils;
import com.wankrfun.crania.widget.CircleImageView;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: AnimationDialog
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/9/21 1:42 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/9/21 1:42 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint({"StaticFieldLeak","InflateParams","SetTextI18n","CutPasteId"})
public class AnimationDialog {
    private final BaseActivity activity;
    private final Dialog dialog;
    private final String type;
    private String image;

    public AnimationDialog(BaseActivity activity, String type) {
        this.activity = activity;
        this.type = type;
        dialog = new Dialog(activity, R.style.custom_dialog);
    }

    public AnimationDialog(BaseActivity activity, String type, String image) {
        this.activity = activity;
        this.type = type;
        this.image = image;
        dialog = new Dialog(activity, R.style.custom_dialog);
    }

    public void showDialog() {
        if (dialog.isShowing()) return;
        dialog.setCancelable(false);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_animation, null);
        RelativeLayout relativeLayout = view.findViewById(R.id.rl);
        AppCompatTextView appCompatTextView = view.findViewById(R.id.tv_title);
        LottieAnimationView lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
        AppCompatImageView appCompatImageView = view.findViewById(R.id.iv_icon);
        LinearLayout linearLayout = view.findViewById(R.id.ll_people);
        CircleImageView circleImageViewA = view.findViewById(R.id.iv_avatar_a);
        CircleImageView circleImageViewB = view.findViewById(R.id.iv_avatar_b);

        AnimatorUtils.setShowAnimation(relativeLayout, 2000);
        //3秒后执行Runnable中的run方法
        MyApplication.getMainThreadHandler().postDelayed(() -> AnimatorUtils.setHideAnimation(relativeLayout, 3000, dialog), 3000);

        Typeface typeface = Typeface.createFromAsset(activity.getAssets(),"YouSheBiaoTiHei-2.ttf");
        appCompatTextView.setTypeface(typeface);

        ImageLoader.load(activity, new ImageConfig.Builder()
                .url(ParseUtils.getUserPhoto())
                .placeholder(R.drawable.ic_empty_zhihu)
                .errorPic(R.drawable.ic_empty_zhihu)
                .imageView(circleImageViewA)
                .build());

        ImageLoader.load(activity, new ImageConfig.Builder()
                .url(image)
                .placeholder(R.drawable.ic_empty_zhihu)
                .errorPic(R.drawable.ic_empty_zhihu)
                .imageView(circleImageViewB)
                .build());

        switch (type){
            case "sign_up"://报名成功
                appCompatTextView.setText("报名成功");
                lottieAnimationView.setAnimation("sign_up_success.json");
                appCompatImageView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                break;
            case "release"://发布成功
                appCompatTextView.setText("发布成功");
                lottieAnimationView.setAnimation("release_success.json");
                appCompatImageView.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
                break;
            case "team"://组队成功
                appCompatTextView.setText("组队成功浪起来\nCongratulations");
                lottieAnimationView.setAnimation("sign_up_success.json");
                appCompatImageView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                break;
            case "matching"://匹配成功
                appCompatTextView.setText("MATCHED\n匹配达成");
                lottieAnimationView.setAnimation("sign_up_success.json");
                appCompatImageView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                break;
        }
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        dialog.setContentView(view, new ViewGroup.MarginLayoutParams(displayMetrics.widthPixels, ViewGroup.MarginLayoutParams.MATCH_PARENT));
        dialog.show();
    }
}
