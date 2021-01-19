package com.wankrfun.crania.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;

import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.image.MatisseGlideEngine;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;

import java.util.Set;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: PermissionUtils
 * @Description: 权限设置开启工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 9:57 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 9:57 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PermissionUtils {

    /*相册选择后图片 请求码code*/
    public static final int ALBUM_CODE = 1999;

    /**
     * 检测定位权限
     *
     * @param activity 上下文
     */
    public static boolean isCheckPermission(BaseActivity activity){
        return AndPermission.hasPermissions(activity, Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION);
    }

    /**
     * 开启定位权限
     *
     * @param activity 上下文
     */
    public static void getOpenLocationPermission(BaseActivity activity){
        AndPermission.with(activity)
                .runtime()
                .permission(Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION)
                .onGranted(permissions -> {
                    EventBus.getDefault().post("true");
                })
                .onDenied(permissions ->{
                    activity.showPermissionDialog(activity.getResources().getString(R.string.permission_location));
                })
                .start();
    }

    /**
     * 开启相机/本地存储权限
     *
     * @param activity 上下文
     */
    public static void openCameraOfStoragePermission(BaseActivity activity){
        AndPermission.with(activity)
                .runtime()
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.CAMERA)
                .onGranted(permissions -> {
                    openAlbum(activity, MimeType.ofImage(), false, 1);
                })
                .onDenied(permissions ->{
                    activity.showPermissionDialog(activity.getResources().getString(R.string.permission_photo_storage));
                })
                .start();
    }

    /**
     * 打开相册
     *
     * @param activity 上下文
     * @param mimeTypes 文件选择类型
     * @param capture 是否打开相机
     * @param maxSelectable 最大选择图片数
     */
    private static void openAlbum(BaseActivity activity, Set<MimeType> mimeTypes, boolean capture, int maxSelectable) {
        Matisse
                //上下文
                .from(activity)
                /**
                 * 文件选择类型
                 * MimeType.ofAll() 选择全部
                 * MimeType.ofImage() 选择图片
                 * MimeType.ofVideo() 选择视频
                 * MimeType.of(MimeType.JPEG,MimeType.AVI) 自定义选择选择的类型
                 */

                .choose(mimeTypes)
                //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                .showSingleMediaType(true)
                //这两行要连用 是否在选择图片中展示照相
                .capture(capture)
                //适配安卓7.0 FileProvider
                .captureStrategy(new CaptureStrategy(true, "com.wankrfun.crania.FileProvider"))
                //true：选中后显示数字  false：选中后显示对号
                .countable(true)
                //最大选择数量
                .maxSelectable(maxSelectable)
                //选择方向
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                //界面中缩略图的质量
                .thumbnailScale(0.85f)
                /**
                 * 设置显示主题
                 * R.style.Matisse_Dracula 这是自定义主题
                 * R.style.Matisse_Zhihu 蓝色主题
                 * R.style.Matisse_Dracula 黑色主题
                 */
                .theme(R.style.Matisse_Dracula)
                //Glide加载方式
                .imageEngine(new MatisseGlideEngine())
                //请求码
                .forResult(ALBUM_CODE);
    }

    /**
     * 跳转权限设置页
     *
     * @param context
     */
    @SuppressLint("ObsoleteSdkInt")
    public static void toSelfSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(mIntent);
    }
}
