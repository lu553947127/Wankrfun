package com.wankrfun.crania.image;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.OpenableColumns;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.image
 * @ClassName: ImageLoader
 * @Description: 图片加载公共框架
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 11:29 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 11:29 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ImageLoader {

    /**
     * 加载图片
     *
     * @param context 上下文
     * @param config 图片config
     */
    public static void load(Context context, ImageConfig config) {
        RequestOptions options = new RequestOptions()
                .placeholder(config.getPlaceholder())
                .error(config.getErrorPic());
        if (config.isCenterCrop()) {
            options = options.centerCrop();
        }
        if (config.isCircle()) {
            options = options.circleCrop();
        }
        if (config.getImageRadius() > 0) {
            options = options.transform(new RoundedCorners(config.getImageRadius()));
        }
        if (config.getBlurValue() > 0) {
            options = options.transform(new BlurTransformation(config.getBlurValue()));
        }
        RequestManager requestManager = Glide.with(context);
        RequestBuilder<Drawable> requestBuilder = requestManager.load(config.getUrl());
        if (config.isCrossFade()) {
            requestBuilder = requestBuilder.transition(DrawableTransitionOptions.withCrossFade());
        }
        requestBuilder.apply(options).into(config.getImageView());
    }

    /**
     * Uri转换成path路径
     *
     * @param context 上下文
     * @param uri android10后本地返回的图片uri
     * @return
     */
    public static String getUriRealFilePath(Context context, Uri uri) {
        try {
            Cursor returnCursor = context.getContentResolver().query(uri, null, null, null, null);
            int nameIndex = Objects.requireNonNull(returnCursor).getColumnIndex(OpenableColumns.DISPLAY_NAME);
            returnCursor.moveToFirst();
            String name = (returnCursor.getString(nameIndex));
            File file = new File(context.getFilesDir(), name);
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            FileOutputStream outputStream = new FileOutputStream(file);
            int read = 0;
            int maxBufferSize = 1 * 1024 * 1024;
            int bytesAvailable = inputStream.available();
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            final byte[] buffers = new byte[bufferSize];
            while ((read = inputStream.read(buffers)) != -1) {
                outputStream.write(buffers, 0, read);
            }
            returnCursor.close();
            inputStream.close();
            outputStream.close();
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
