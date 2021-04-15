package com.wankrfun.crania.view.mine.about;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.parse.ParseFile;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.GridImageAdapter;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.EventsJoinedListBean;
import com.wankrfun.crania.event.CardEvent;
import com.wankrfun.crania.event.CompressEvent;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.CompressUtils;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.ParseUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.utils.PictureUtils;
import com.wankrfun.crania.viewmodel.MineCardViewModel;
import com.wankrfun.crania.widget.CircleImageView;
import com.wankrfun.crania.widget.CornerImageView;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineEventsAddActivity
 * @Description: 活动瞬间添加页面
 * @Author: 鹿鸿祥
 * @CreateDate: 3/31/21 10:03 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/31/21 10:03 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineEventsAddActivity extends BaseActivity {
    @BindView(R.id.et_events)
    AppCompatEditText etEvents;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_icon)
    AppCompatImageView ivIcon;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.iv_sex)
    AppCompatImageView ivSex;
    @BindView(R.id.iv_image)
    CornerImageView ivImage;
    @BindView(R.id.tv_release)
    AppCompatTextView tvRelease;
    private GridImageAdapter gridImageAdapter;
    private final List<String> picture_list = new ArrayList<>();
    private final List<ParseFile> images = new ArrayList<>();
    private MineCardViewModel mineCardViewModel;
    private EventsJoinedListBean.DataBean.ListBean listBean;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_events_add;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        tvRelease.getBackground().setAlpha(60);
        listBean = (EventsJoinedListBean.DataBean.ListBean) getIntent().getSerializableExtra("bean");

        if (!TextUtils.isEmpty(listBean.getEventTypeIcon())){
            EventsUtils.getEventsIcon(ivIcon, ivIcon, listBean.getEventType(), listBean.getEventTypeIcon());
        }else {
            EventsUtils.getEventsIcon(ivIcon, ivIcon, listBean.getEventType(), "0");
        }

        tvTitle.setText(listBean.getEvent_contents());
        tvTitle.setText(listBean.getCreator_name());
        ivSex.setImageResource(listBean.getCreator_sex().equals("female") ? R.drawable.icon_sex_female : R.drawable.icon_sex_male);
        ImageLoader.load(activity, new ImageConfig.Builder()
                .url(listBean.getCreator_image())
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .imageView(ivAvatar)
                .build());
        ImageLoader.load(activity, new ImageConfig.Builder()
                .url(listBean.getEventImage())
                .placeholder(R.drawable.ic_empty_zhihu)
                .errorPic(R.drawable.ic_empty_zhihu)
                .imageView(ivImage)
                .build());

        if (TextUtils.isEmpty(listBean.getEventImage())){
            ivImage.setVisibility(View.GONE);
        }else {
            ivImage.setVisibility(View.VISIBLE);
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        gridImageAdapter = new GridImageAdapter(picture_list, 9);
        recyclerView.setAdapter(gridImageAdapter);

        //自定义添加图片九宫格布局 组合监听事件
        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onTakePhotoClick(View view, int position) {
                PermissionUtils.openCameraOfStoragePermission(activity, 9);
            }

            @Override
            public void onItemClick(View view, int position) {
                PictureEnlargeUtils.getPictureEnlargeList(activity, PictureUtils.getImageBitmapAdd(picture_list), position);
            }

            @Override
            public void onItemDelClick(View view, int position) {
                new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "确认要删除该图片？", () -> {
                    picture_list.remove(position);
                    images.remove(position);
                    gridImageAdapter.setNewData(picture_list);
                }).show();
            }
        });

        mineCardViewModel = getViewModel(MineCardViewModel.class);

        //添加成功活动瞬间返回结果
        mineCardViewModel.eventsCreateLiveData.observe(this, challengeStatusBean -> {
            EventBus.getDefault().post(new CardEvent("events"));
            ActivityUtils.finishActivity(MineEventsActivity.class);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.ALBUM_CODE & resultCode == RESULT_OK) {
            if (MyApplication.isAndroidQ) {
                if (Matisse.obtainResult(data).size() + picture_list.size() > 9){
                    ToastUtils.showShort(getString(R.string.photo_size_not_9));
                    return;
                }
                for (int i = 0; i < Matisse.obtainResult(data).size(); i++) {
                    CompressUtils.uploadFileCompress(new File(ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(i))));
                }
            }else {
                if (Matisse.obtainPathResult(data).size()+ picture_list.size() > 9){
                    ToastUtils.showShort(getString(R.string.photo_size_not_9));
                    return;
                }
                for (int i = 0; i < Matisse.obtainPathResult(data).size(); i++) {
                    CompressUtils.uploadFileCompress(new File(Matisse.obtainPathResult(data).get(i)));
                }
            }
        }
    }

    @Subscribe
    public void onEventCompress(CompressEvent event) {
        picture_list.add(String.valueOf(event.getCompressFile()));
        gridImageAdapter.setNewData(picture_list);
        images.add(ParseUtils.setImageFile(event.getCompressFile()));
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_release})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_release:

                if (images.size() == 0){
                    ToastUtils.showShort("请至少添加一张图片吧");
                    return;
                }

                mineCardViewModel.getEventsCreate(listBean.getEventCreator(), listBean.getObjectId(), listBean.getEventType(), listBean.getEventTypeIcon(), etEvents.getText().toString().trim() ,images);
                break;
        }
    }
}
