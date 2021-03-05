package com.wankrfun.crania.view.mine;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.MineTagBean;
import com.wankrfun.crania.bean.UserInfoBean;
import com.wankrfun.crania.dialog.CustomBottomJob;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.viewmodel.MineViewModel;
import com.wankrfun.crania.widget.CornerImageView;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: ChangeUserInfoActivity
 * @Description: 修改个人信息
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 2:26 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 2:26 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChangeUserInfoActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.iv_image1)
    CornerImageView ivImage1;
    @BindView(R.id.iv_image2)
    CornerImageView ivImage2;
    @BindView(R.id.iv_image3)
    CornerImageView ivImage3;
    @BindView(R.id.iv_image4)
    CornerImageView ivImage4;
    @BindView(R.id.iv_image5)
    CornerImageView ivImage5;
    @BindView(R.id.iv_image6)
    CornerImageView ivImage6;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.tv_address)
    AppCompatTextView tvAddress;
    @BindView(R.id.tv_birthday)
    AppCompatTextView tvBirthday;
    @BindView(R.id.tv_sex)
    AppCompatTextView tvSex;
    @BindView(R.id.tv_work)
    AppCompatTextView tvWork;
    @BindView(R.id.fl_label)
    FlexboxLayout flLabel;
    private MineViewModel mineViewModel;
    private UserInfoBean userInfoBean;
    //区分图片的个数
    private String type, image1, image2, image3, image4, image5, image6;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_change_user_info;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        tvBarTitle.setText("修改个人信息");

//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
//        gridImageAdapter = new GridImageAdapter(picture_list);
//        recyclerView.setAdapter(gridImageAdapter);
//
//        //自定义添加图片九宫格布局 组合监听事件
//        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
//            @Override
//            public void onTakePhotoClick(View view, int position) {
//                PermissionUtils.openCameraOfStoragePermission(activity, 6);
//            }
//
//            @Override
//            public void onItemClick(View view, int position) {
//                PictureEnlargeUtils.getPictureEnlargeList(activity, PictureUtils.getImageBitmapAdd(picture_list), position);
//            }
//
//            @Override
//            public void onItemDelClick(View view, int position) {
//                new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "确认要删除该图片？", () -> {
//                    picture_list.remove(position);
//                    gridImageAdapter.setNewData(picture_list);
//                }).show();
//            }
//        });

        mineViewModel = getViewModel(MineViewModel.class);

        //获取用户详情返回结果
        mineViewModel.userInfoLiveData.observe(this, userInfoBean -> {
            this.userInfoBean = userInfoBean;
            tvName.setText(userInfoBean.getData().getProfile().getName());
            tvAddress.setText(userInfoBean.getData().getProfile().getAddress());
            tvBirthday.setText(userInfoBean.getData().getProfile().getBirthday());
            tvSex.setText(userInfoBean.getData().getProfile().getSex().equals("male") ? "男生" : "女生");
            tvWork.setText(NumberUtils.getJob(userInfoBean.getData().getProfile().getJob()));

            if (userInfoBean.getData().getProfile().getImages().size() > 0){
                ImageLoader.load(activity, new ImageConfig.Builder()
                        .url(userInfoBean.getData().getProfile().getImages().get(0))
                        .imageView(ivImage1)
                        .placeholder(R.drawable.shape_gray2_10)
                        .errorPic(R.drawable.shape_gray2_10)
                        .build());
                if (userInfoBean.getData().getProfile().getImages().size() > 1){
                    ImageLoader.load(activity, new ImageConfig.Builder()
                            .url(userInfoBean.getData().getProfile().getImages().get(1))
                            .imageView(ivImage2)
                            .placeholder(R.drawable.shape_gray2_10)
                            .errorPic(R.drawable.shape_gray2_10)
                            .build());
                    if (userInfoBean.getData().getProfile().getImages().size() > 2){
                        ImageLoader.load(activity, new ImageConfig.Builder()
                                .url(userInfoBean.getData().getProfile().getImages().get(2))
                                .imageView(ivImage3)
                                .placeholder(R.drawable.shape_gray2_10)
                                .errorPic(R.drawable.shape_gray2_10)
                                .build());
                        if (userInfoBean.getData().getProfile().getImages().size() > 3){
                            ImageLoader.load(activity, new ImageConfig.Builder()
                                    .url(userInfoBean.getData().getProfile().getImages().get(3))
                                    .imageView(ivImage4)
                                    .placeholder(R.drawable.shape_gray2_10)
                                    .errorPic(R.drawable.shape_gray2_10)
                                    .build());
                            if (userInfoBean.getData().getProfile().getImages().size() > 4){
                                ImageLoader.load(activity, new ImageConfig.Builder()
                                        .url(userInfoBean.getData().getProfile().getImages().get(4))
                                        .imageView(ivImage5)
                                        .placeholder(R.drawable.shape_gray2_10)
                                        .errorPic(R.drawable.shape_gray2_10)
                                        .build());
                                if (userInfoBean.getData().getProfile().getImages().size() > 5){
                                    ImageLoader.load(activity, new ImageConfig.Builder()
                                            .url(userInfoBean.getData().getProfile().getImages().get(5))
                                            .imageView(ivImage6)
                                            .placeholder(R.drawable.shape_gray2_10)
                                            .errorPic(R.drawable.shape_gray2_10)
                                            .build());
                                }else {
                                    ivImage6.setImageResource(R.drawable.shape_gray2_10);
                                }
                            }else {
                                ivImage5.setImageResource(R.drawable.shape_gray2_10);
                            }
                        }else {
                            ivImage4.setImageResource(R.drawable.shape_gray2_10);
                        }
                    }else {
                        ivImage3.setImageResource(R.drawable.shape_gray2_10);
                    }
                }else {
                    ivImage2.setImageResource(R.drawable.shape_gray2_10);
                }
            }else {
                ivImage1.setImageResource(R.drawable.shape_gray2_10);
            }

            for (MineTagBean mineTagBean : EventsUtils.getMineTag(userInfoBean)) {
                View view1 =  LayoutInflater.from(activity).inflate(R.layout.adapter_mine_label, flLabel, false);
                AppCompatImageView appCompatImageView = view1.findViewById(R.id.iv_icon);
                AppCompatTextView appCompatTextView = view1.findViewById(R.id.tv_title);
                appCompatImageView.setImageResource(mineTagBean.getIcon());
                appCompatTextView.setText(mineTagBean.getTitle());
                flLabel.addView(view1);
            }
        });

        //修改个人职业成功返回结果
        mineViewModel.userUploadJobLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            mineViewModel.getUserInfo(SPUtils.getInstance().getString(SpConfig.USER_ID));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.ALBUM_CODE & resultCode == RESULT_OK) {
            if (MyApplication.isAndroidQ) {
                switch (type){
                    case "1":
                        image1 = ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(0));
                        LogUtils.e(image1);
                        ivImage1.setImageBitmap(BitmapFactory.decodeFile(image1));
                        break;
                    case "2":
                        image2 = ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(0));
                        ivImage2.setImageBitmap(BitmapFactory.decodeFile(image2));
                        break;
                    case "3":
                        image3 = ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(0));
                        ivImage3.setImageBitmap(BitmapFactory.decodeFile(image3));
                        break;
                    case "4":
                        image4 = ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(0));
                        ivImage4.setImageBitmap(BitmapFactory.decodeFile(image4));
                        break;
                    case "5":
                        image5 = ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(0));
                        ivImage5.setImageBitmap(BitmapFactory.decodeFile(image5));
                        break;
                    case "6":
                        image6 = ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(0));
                        ivImage6.setImageBitmap(BitmapFactory.decodeFile(image6));
                        break;
                }
            }else {
                switch (type){
                    case "1":
                        image1 = Matisse.obtainPathResult(data).get(0);
                        ivImage1.setImageBitmap(BitmapFactory.decodeFile(image1));
                        break;
                    case "2":
                        image2 = Matisse.obtainPathResult(data).get(0);
                        ivImage2.setImageBitmap(BitmapFactory.decodeFile(image2));
                        break;
                    case "3":
                        image3 = Matisse.obtainPathResult(data).get(0);
                        ivImage3.setImageBitmap(BitmapFactory.decodeFile(image3));
                        break;
                    case "4":
                        image4 = Matisse.obtainPathResult(data).get(0);
                        ivImage4.setImageBitmap(BitmapFactory.decodeFile(image4));
                        break;
                    case "5":
                        image5 = Matisse.obtainPathResult(data).get(0);
                        ivImage5.setImageBitmap(BitmapFactory.decodeFile(image5));
                        break;
                    case "6":
                        image6 = Matisse.obtainPathResult(data).get(0);
                        ivImage6.setImageBitmap(BitmapFactory.decodeFile(image6));
                        break;
                }
            }
        }
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_name, R.id.tv_address, R.id.tv_work, R.id.rl_tag
            , R.id.iv_image1, R.id.iv_image2, R.id.iv_image3, R.id.iv_image4, R.id.iv_image5, R.id.iv_image6})
    void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_name://修改昵称
                bundle.putString("name", userInfoBean.getData().getProfile().getName());
                ActivityUtils.startActivity(bundle, ChangeNameActivity.class);
                break;
            case R.id.tv_address://修改位置
                bundle.putString("address", userInfoBean.getData().getProfile().getAddress());
                ActivityUtils.startActivity(bundle, ChangeAddressActivity.class);
                break;
            case R.id.tv_work://修改职业
                new XPopup.Builder(activity).asCustom(new CustomBottomJob(activity, userInfoBean.getData().getProfile().getJob())).show();
                break;
            case R.id.rl_tag://个性标签
                break;
            case R.id.iv_image1://图片一
                type = "1";
                PermissionUtils.openCameraOfStoragePermission(activity, 1);
                break;
            case R.id.iv_image2://图片二
                type = "2";
                PermissionUtils.openCameraOfStoragePermission(activity, 1);
                break;
            case R.id.iv_image3://图片三
                type = "3";
                PermissionUtils.openCameraOfStoragePermission(activity, 1);
                break;
            case R.id.iv_image4://图片四
                type = "4";
                PermissionUtils.openCameraOfStoragePermission(activity, 1);
                break;
            case R.id.iv_image5://图片五
                type = "5";
                PermissionUtils.openCameraOfStoragePermission(activity, 1);
                break;
            case R.id.iv_image6://图片六
                type = "6";
                PermissionUtils.openCameraOfStoragePermission(activity, 1);
                break;
        }
    }

    @Subscribe
    public void onEventEvents(EventsEvent event) {
        tvWork.setText(NumberUtils.getJob(event.getTitle()));
        mineViewModel.getUploadJob(event.getTitle());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mineViewModel.getUserInfo(SPUtils.getInstance().getString(SpConfig.USER_ID));
    }
}
