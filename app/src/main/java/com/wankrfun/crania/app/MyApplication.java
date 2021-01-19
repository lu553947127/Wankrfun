package com.wankrfun.crania.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.ArrayMap;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BuildConfig;
import com.wankrfun.crania.http.api.ApiService;
import com.wankrfun.crania.http.retrofit.BaseRequest;

import java.io.File;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.app
 * @ClassName: MyApplication
 * @Description: app初始化工具
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 9:25 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 9:25 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    public static boolean isAndroidQ = Build.VERSION.SDK_INT >= 29;
    private static ArrayMap<String, ViewModel> sViewModelCache;
    //网络请求
    public static BaseRequest<ApiService> baseHttpRequest;
    //初始化刷新工具
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(android.R.color.transparent, R.color.colorPrimary);//全局设置主题颜色
            return new MaterialHeader(context).setColorSchemeResources(R.color.colorPrimary);//指定为经典Header，默认是贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter footer = new ClassicsFooter(context).setDrawableSize(20).setAccentColorId(R.color.colorPrimary);
            //去掉加载完成字样
            footer.setFinishDuration(0);
            return footer;
        });
        // 设置加载完成文字为空
        ClassicsFooter.REFRESH_FOOTER_FINISH = "";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initParse();
        initUtils((Application) getApplicationContext());
        sViewModelCache = new ArrayMap<>();

        initHttpRequest(BuildConfig.BASE_URL, ApiService.class);

        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(this, null);

        initCrash(getApplicationContext());
    }

    /**
     * 初始化Parse
     */
    private void initParse() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("6a3cbbd0fc5ba3afe5ccac62c9a7b8e2b4b8cd96")
                .clientKey("c03ded6f5a06f3e98f14852e8ac1d71031346ddd")
                .server("http://123.56.136.238:1337/parse")
                .build()
        );
        ParseUser.enableAutomaticUser();
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(acl, true);
    }

    /**
     * 初始化Utils工具
     * @param context
     */
    private static void initUtils(Application context) {
        Utils.init(context);
        LogUtils.getConfig().setGlobalTag(context.getString(R.string.app_name)).setLogSwitch(true);
        ToastUtils.setGravity(Gravity.BOTTOM, 0, ConvertUtils.dp2px(100));
        ToastUtils.setMsgTextSize(13);
        ToastUtils.setMsgColor(context.getResources().getColor(R.color.white));
        if (isAndroidQ) {
            ToastUtils.setBgResource(R.drawable.shape_bg_toast_q);
        }else {
            ToastUtils.setBgResource(R.drawable.shape_bg_toast);
        }
    }

    /**
     * 初始化网络请求
     *
     * @param host
     * @param clazz
     */
    public void initHttpRequest(String host, Class clazz) {
        if (baseHttpRequest == null) {
            if (baseHttpRequest == null) {
                baseHttpRequest = new BaseRequest(host, clazz);
            }
        }
    }

    /**
     * 测试版本收集崩溃日志
     *
     * @param context
     */
    @SuppressLint("MissingPermission")
    private static void initCrash(final Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            CrashUtils.init(externalStorageDirectory.getPath() + "/weibi/crash/");
        }
    }

    //获取到主线程的handler
    public static Handler mMainThreadHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            mListener.handlerMessage(msg);
        }
    };

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    private static HandlerListener mListener;

    public static void setOnHandlerListener(HandlerListener listener) {
        mListener = listener;
    }

    public static HandlerListener getListener() {
        return mListener;
    }

    public interface HandlerListener {
        void handlerMessage(Message msg);
    }


    public static MyApplication getInstance() {
        return instance;
    }

    public static ArrayMap<String, ViewModel> getViewModelCache() {
        return sViewModelCache;
    }
}
