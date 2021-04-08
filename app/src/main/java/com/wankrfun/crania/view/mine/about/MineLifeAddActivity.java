package com.wankrfun.crania.view.mine.about;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.GridImageAdapter;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.event.CompressEvent;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.CompressUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.utils.PictureUtils;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine.about
 * @ClassName: MineLifeAddActivity
 * @Description: 我的生活创建页面
 * @Author: 鹿鸿祥
 * @CreateDate: 3/23/21 10:27 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/23/21 10:27 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineLifeAddActivity extends BaseActivity {
    @BindView(R.id.iv_icon)
    AppCompatImageView ivIcon;
    @BindView(R.id.tv_type)
    AppCompatTextView tvType;
    @BindView(R.id.et_events)
    AppCompatEditText etEvents;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_release)
    AppCompatTextView tvRelease;
    private GridImageAdapter gridImageAdapter;
    private final List<String> picture_list = new ArrayList<>();

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_mine_life_add;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        ivIcon.setImageResource(getIntent().getIntExtra("image", 0));
        tvType.setText(getIntent().getStringExtra("title"));
        tvRelease.getBackground().setAlpha(60);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        gridImageAdapter = new GridImageAdapter(picture_list, 4);
        recyclerView.setAdapter(gridImageAdapter);

        //自定义添加图片九宫格布局 组合监听事件
        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onTakePhotoClick(View view, int position) {
                PermissionUtils.openCameraOfStoragePermission(activity, 4);
            }

            @Override
            public void onItemClick(View view, int position) {
                PictureEnlargeUtils.getPictureEnlargeList(activity, PictureUtils.getImageBitmapAdd(picture_list), position);
            }

            @Override
            public void onItemDelClick(View view, int position) {
                new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "确认要删除该图片？", () -> {
                    picture_list.remove(position);
                    gridImageAdapter.setNewData(picture_list);
                }).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.ALBUM_CODE & resultCode == RESULT_OK) {
            if (MyApplication.isAndroidQ) {
                if (Matisse.obtainResult(data).size() + picture_list.size() > 4){
                    ToastUtils.showShort(getString(R.string.photo_size_not_4));
                    return;
                }
                for (int i = 0; i < Matisse.obtainResult(data).size(); i++) {
                    CompressUtils.uploadFileCompress(new File(ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(i))));
                }
            }else {
                if (Matisse.obtainPathResult(data).size()+ picture_list.size() > 4){
                    ToastUtils.showShort(getString(R.string.photo_size_not_4));
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
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_release})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_release:
                break;
        }
    }
}
