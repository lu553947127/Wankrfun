package com.wankrfun.crania.utils;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.event.CompressEvent;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: CompressUtils
 * @Description: 图片塔松工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 3/8/21 10:37 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/8/21 10:37 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("CheckResult")
public class CompressUtils {

    /**
     * 通过鲁班压缩图片返回图片路径
     *
     * @param file 文件流
     */
    public static void uploadFileCompress(File file) {
        List<File> list = new ArrayList<>();
        list.add(file);
        //如果uri为空，代表为网络图片，无需压缩，直接增加即可
        Flowable.just(list)
                .observeOn(Schedulers.io())
                .map(list1 -> Luban.with(MyApplication.getInstance())
                        .ignoreBy(1024 * 2)
                        .setTargetDir(PictureUtils.getLuBanPath())
                        .load(list1)
                        .get())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.i(throwable.getMessage());
                })
                .onErrorResumeNext(Flowable.empty())
                .subscribe(files -> EventBus.getDefault().post(new CompressEvent(files.get(0))), throwable -> ToastUtils.showShort("图片过大"));
    }
}
