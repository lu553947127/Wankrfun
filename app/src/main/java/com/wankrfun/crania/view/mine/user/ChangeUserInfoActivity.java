package com.wankrfun.crania.view.mine.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.GridImageAdapter;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.UserInfoBean;
import com.wankrfun.crania.dialog.CustomBottomJob;
import com.wankrfun.crania.event.CompressEvent;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.CompressUtils;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.ParseUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.utils.PictureUtils;
import com.wankrfun.crania.view.mine.user.ChangeAddressActivity;
import com.wankrfun.crania.view.mine.user.ChangeNameActivity;
import com.wankrfun.crania.viewmodel.MineViewModel;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.rv)
    RecyclerView recyclerView;
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
    private GridImageAdapter gridImageAdapter;
    private final List<String> picture_list = new ArrayList<>();
    private final List<Object> editImage = new ArrayList<>();

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

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        gridImageAdapter = new GridImageAdapter(picture_list);
        recyclerView.setAdapter(gridImageAdapter);

        //自定义添加图片九宫格布局 组合监听事件
        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onTakePhotoClick(View view, int position) {
                PermissionUtils.openCameraOfStoragePermission(activity, 6);
            }

            @Override
            public void onItemClick(View view, int position) {
                PictureEnlargeUtils.getPictureEnlargeList(activity, PictureUtils.getImageBitmapAdd(picture_list), position);
            }

            @Override
            public void onItemDelClick(View view, int position) {
                new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "确认要删除该图片？", () -> {
                    picture_list.remove(position);
                    editImage.remove(position);
                    gridImageAdapter.setNewData(picture_list);
                    mineViewModel.getUploadImages(editImage, SPUtils.getInstance().getString(SpConfig.USER_ID));
                }).show();
            }
        });

        mineViewModel = getViewModel(MineViewModel.class);

        //获取用户详情返回结果
        mineViewModel.userInfoLiveData.observe(this, userInfoBean -> {
            this.userInfoBean = userInfoBean;
            tvName.setText(userInfoBean.getData().getProfile().getName());
            tvAddress.setText(userInfoBean.getData().getProfile().getAddress());
            tvBirthday.setText(userInfoBean.getData().getProfile().getBirthday());
            tvSex.setText(userInfoBean.getData().getProfile().getSex().equals("male") ? "男生" : "女生");
            tvWork.setText(NumberUtils.getJob(userInfoBean.getData().getProfile().getJob()));

            picture_list.clear();
            editImage.clear();
            picture_list.addAll(userInfoBean.getData().getProfile().getImages());
            editImage.addAll(userInfoBean.getData().getProfile().getImages());
            gridImageAdapter.setNewData(picture_list);

            EventsUtils.getShowTag(activity, flLabel, EventsUtils.getMineTag(userInfoBean));
        });

        //修改个人职业成功返回结果
        mineViewModel.userUploadJobLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            mineViewModel.getUserInfo(SPUtils.getInstance().getString(SpConfig.USER_ID));
        });

        //修改个人相册图片成功返回结果
        mineViewModel.userUploadImagesLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            mineViewModel.getUserInfo(SPUtils.getInstance().getString(SpConfig.USER_ID));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.ALBUM_CODE & resultCode == RESULT_OK) {
            if (MyApplication.isAndroidQ) {
                if (Matisse.obtainResult(data).size() + picture_list.size() > 6){
                    ToastUtils.showShort(getString(R.string.photo_size_not));
                    return;
                }
                for (int i = 0; i < Matisse.obtainResult(data).size(); i++) {
                    CompressUtils.uploadFileCompress(new File(ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(i))));
                }
            }else {
                if (Matisse.obtainPathResult(data).size()+ picture_list.size() > 6){
                    ToastUtils.showShort(getString(R.string.photo_size_not));
                    return;
                }
                for (int i = 0; i < Matisse.obtainPathResult(data).size(); i++) {
                    CompressUtils.uploadFileCompress(new File(Matisse.obtainPathResult(data).get(i)));
                }
            }
        }
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_name, R.id.tv_address, R.id.tv_work, R.id.rl_tag})
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
        }
    }

    @Subscribe
    public void onEventEvents(EventsEvent event) {
        tvWork.setText(NumberUtils.getJob(event.getTitle()));
        mineViewModel.getUploadJob(event.getTitle());
    }

    @Subscribe
    public void onEventCompress(CompressEvent event) {
        picture_list.add(String.valueOf(event.getCompressFile()));
        editImage.add(ParseUtils.setImageFile(event.getCompressFile()));
        gridImageAdapter.setNewData(picture_list);
        LogUtils.e(picture_list);
        mineViewModel.getUploadImages(editImage, SPUtils.getInstance().getString(SpConfig.USER_ID));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mineViewModel.getUserInfo(SPUtils.getInstance().getString(SpConfig.USER_ID));
    }
}
