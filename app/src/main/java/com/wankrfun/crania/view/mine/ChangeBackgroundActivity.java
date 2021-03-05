package com.wankrfun.crania.view.mine;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.MineBackgroundAdapter;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.utils.RefreshUtils;
import com.zhihu.matisse.Matisse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: ChangeBackgroundActivity
 * @Description: 更换背景页面
 * @Author: 鹿鸿祥
 * @CreateDate: 1/28/21 2:44 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/28/21 2:44 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChangeBackgroundActivity extends BaseActivity {
    @BindView(R.id.iv_bg)
    AppCompatImageView ivBg;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    //图片本地路径
    private String path = "";

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_change_background;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        MineBackgroundAdapter mineBackgroundAdapter = new MineBackgroundAdapter(R.layout.adapter_mine_background, RefreshUtils.getBgList());
        recyclerView.setAdapter(mineBackgroundAdapter);

        mineBackgroundAdapter.setOnItemClickListener((adapter, view, position) -> {
            mineBackgroundAdapter.setIsSelect(mineBackgroundAdapter.getData().get(position).getName());
            ivBg.setImageResource(mineBackgroundAdapter.getData().get(position).getImages());
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.ALBUM_CODE & resultCode == RESULT_OK) {
            if (MyApplication.isAndroidQ) {
                LogUtils.e(Matisse.obtainResult(Objects.requireNonNull(data)).get(0));
                path = ImageLoader.getUriRealFilePath(activity,Matisse.obtainResult(data).get(0));
            }else {
                LogUtils.e(Matisse.obtainPathResult(Objects.requireNonNull(data)).get(0));
                path = Matisse.obtainPathResult(Objects.requireNonNull(data)).get(0);
            }
            ivBg.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }

    @OnClick({R.id.tv_back, R.id.tv_yes, R.id.rl_upload_image})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_yes://保存

                break;
            case R.id.rl_upload_image://从相册上传
                PermissionUtils.openCameraOfStoragePermission(activity);
                break;
        }
    }
}
