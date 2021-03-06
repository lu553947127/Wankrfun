package com.wankrfun.crania.utils;

import android.app.Activity;

import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: PictureEnlargeUtils
 * @Description: 图片放大查看
 * @Author: 鹿鸿祥
 * @CreateDate: 2/2/21 10:24 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/2/21 10:24 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PictureEnlargeUtils {

    /**
     * 单张图片查看
     * @param activity
     * @param picture
     */
    public static void getPictureEnlarge(Activity activity, String picture) {
        ImagePreview.getInstance()
                // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好
                .setContext(activity)
                // 3：只有一张图片的情况，可以直接传入这张图片的url
                .setImage(picture)
                // 是否启用上拉关闭。默认不启用
                .setEnableUpDragClose(true)
                // 是否启用下拉关闭。默认不启用
                .setEnableDragClose(true)
                // 是否显示下载按钮，在页面右下角。默认显示
                .setShowDownButton(true)
                //设置是否显示关闭按钮,在页面左下角。默认显示
                .setShowCloseButton(true)
                //设置下载到的文件夹名称
                .setFolderName("wankrfun/Image")
                //设置加载失败的占位图资源id
                .setErrorPlaceHolder(R.drawable.ic_empty_dracula)
                // 开启预览
                .start();
    }

    /**
     * 多张图片查看
     * @param activity
     * @param list
     * @param position
     */
    public static void getPictureEnlargeList(BaseActivity activity, List<String> list, int position) {
        List<String> imageList = new ArrayList<>(list);
        if (imageList.size() == 0){
            return;
        }
        ImagePreview.getInstance()
                // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好
                .setContext(activity)
                // 从第几张图片开始，索引从0开始哦~
                .setIndex(position)
                // 3：只有一张图片的情况，可以直接传入这张图片的url
                .setImageList(imageList)
                // 是否启用上拉关闭。默认不启用
                .setEnableUpDragClose(true)
                // 是否启用下拉关闭。默认不启用
                .setEnableDragClose(true)
                // 是否显示下载按钮，在页面右下角。默认显示
                .setShowDownButton(true)
                //设置是否显示关闭按钮,在页面左下角。默认显示
                .setShowCloseButton(true)
                //设置下载到的文件夹名称
                .setFolderName("wankrfun/Image")
                //设置加载失败的占位图资源id
                .setErrorPlaceHolder(R.drawable.ic_empty_dracula)
                // 开启预览
                .start();
    }
}

