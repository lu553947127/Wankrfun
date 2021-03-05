package com.wankrfun.crania.receiver;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.LogUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver
 * @ClassName: CrashHandler
 * @Description: 系统发生闪退 完美化解决
 * @Author: 鹿鸿祥
 * @CreateDate: 3/1/21 1:28 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/1/21 1:28 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    //TAG
    public static final String TAG = "CrashHandler";
    //自定义Toast
    private static Toast mCustomToast;
    //提示文字
    private static String mCrashTip = "由于发生了一个未知错误,程序出现异常\n我们对此引起的不便表示抱歉！\nAPP即将在3秒后重启";
    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandler mCrashHandler;
    //程序的App对象
    public Application mApplication;
    //生命周期监听
    MyActivityLifecycleCallbacks mMyActivityLifecycleCallbacks = new MyActivityLifecycleCallbacks();
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap();
    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    //是否是Debug模式
    private boolean mIsDebug;
    //是否重启APP
    private boolean mIsRestartApp;
    //重启APP时间
    private long mRestartTime;
    //重启后的第一个Activity class文件
    private Class mClassOfFirstActivity;
    //是否已经toast
    private boolean hasToast;

    /**
     * 私有构造函数
     */
    private CrashHandler() {

    }

    /**
     * 获取CrashHandler实例 ,单例模式
     *
     * @return
     * @since V1.0
     */
    public static CrashHandler getInstance() {
        if (mCrashHandler == null)
            mCrashHandler = new CrashHandler();
        return mCrashHandler;
    }

    public static void setCloseAnimation(int closeAnimation) {
        MyActivityLifecycleCallbacks.sAnimationId = closeAnimation;
    }

    public static void setCustomToast(Toast customToast) {
        mCustomToast = customToast;
    }

    public static void setCrashTip(String crashTip) {
        mCrashTip = crashTip;
    }

    public void init(Application application, boolean isDebug, boolean isRestartApp, long restartTime, Class classOfFirstActivity) {
        mIsRestartApp = isRestartApp;
        mRestartTime = restartTime;
        mClassOfFirstActivity = classOfFirstActivity;
        initCrashHandler(application, isDebug);
    }

    public void init(Application application, boolean isDebug) {
        initCrashHandler(application, isDebug);
    }

    /**
     * 初始化
     *
     * @since V1.0
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initCrashHandler(Application application, boolean isDebug) {
        mIsDebug = isDebug;
        mApplication = application;
        mApplication.registerActivityLifecycleCallbacks(mMyActivityLifecycleCallbacks);
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        boolean isHandle = handleException(ex);
        if (!isHandle && mDefaultHandler != null) {
            // 如果我们没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                //给Toast留出时间
                Thread.sleep(3800);
            } catch (InterruptedException e) {
                Log.e(TAG, "uncaughtException() InterruptedException:" + e);
            }

            if (mIsRestartApp) {
                //利用系统时钟进行重启任务
                AlarmManager mgr = (AlarmManager) mApplication.getSystemService(Context.ALARM_SERVICE);
                try {
                    Intent intent = new Intent(mApplication, mClassOfFirstActivity);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    PendingIntent restartIntent = PendingIntent.getActivity(mApplication, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + mRestartTime, restartIntent); // x秒钟后重启应用
                } catch (Exception e) {
                    Log.e(TAG, "first class error:" + e);
                }
            }

            mMyActivityLifecycleCallbacks.removeAllActivities();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            System.gc();

        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    @SuppressLint("ShowToast")
    private boolean handleException(Throwable ex) {
        if (!hasToast) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Looper.prepare();
                        Toast toast;
                        if (mCustomToast == null) {
                            toast = Toast.makeText(mApplication, mCrashTip, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                        } else {
                            toast = mCustomToast;
                        }
                        toast.show();
                        Looper.loop();
                        hasToast = true;
                    } catch (Exception e) {
                        Log.e(TAG, "handleException Toast error" + e);
                    }
                }
            }).start();
        }
        if (ex == null) {
            return false;
        }
        if (mIsDebug) {
            // 收集设备参数信息
            collectDeviceInfo();
            // 保存日志文件
            saveCatchInfo2File(ex);
        }
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @since V1.0
     */
    public void collectDeviceInfo() {
        try {
            PackageManager pm = mApplication.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mApplication.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "collectDeviceInfo() an error occured when collect package info NameNotFoundException:");
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.i(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "collectDeviceInfo() an error occured when collect crash info Exception:");
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 文件名称
     */
    private String saveCatchInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        sb.append("------------------------start------------------------------\n");
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        sb.append(getCrashInfo(ex));
        sb.append("\n------------------------end------------------------------");
        saveTextFile(mApplication, sb.toString());
        LogUtils.e(sb.toString());
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".txt";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "crash/";
                File dir = new File(path);
                if (!dir.exists()) dir.mkdirs();
                // 创建新的文件
                if (!dir.exists()) dir.createNewFile();

                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                // 答出log日志到控制台
                LogcatCrashInfo(path + fileName);
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "saveCatchInfo2File() an error occured while writing file... Exception:");
        }
        return null;
    }

    /**
     * 将捕获的导致崩溃的错误信息保存在sdcard 和输出到LogCat中
     *
     * @param fileName
     * @since V1.0
     */
    private void LogcatCrashInfo(String fileName) {
        if (!new File(fileName).exists()) {
            Log.e(TAG, "LogcatCrashInfo() 日志文件不存在");
            return;
        }
        FileInputStream fis = null;
        BufferedReader reader = null;
        String s = null;
        try {
            fis = new FileInputStream(fileName);
            reader = new BufferedReader(new InputStreamReader(fis, "GBK"));
            while (true) {
                s = reader.readLine();
                if (s == null)
                    break;
                Log.e(TAG, s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally { // 关闭流
            try {
                reader.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 得到程序崩溃的详细信息
     */
    public String getCrashInfo(Throwable ex) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        ex.setStackTrace(ex.getStackTrace());
        ex.printStackTrace(printWriter);
        printWriter.close();
        return result.toString();
    }

    /**
     * 保存文本到公共目录(txt文本,其他文件同理)
     * 29 以下，需要提前申请文件读写权限
     * 29及29以上的，不需要权限
     * 保存的文件在 Download 目录下
     *
     * @param mContext 上下文
     * @param content  文本内容
     * @return 文件的 uri
     */
    public static Uri saveTextFile(Context mContext, String content) {
        if (TextUtils.isEmpty(content))
            return null;
        if (Build.VERSION.SDK_INT < 29) {
            if (!isGranted(mContext)) {
                Log.e("FileSaveUtil", "save to file need storage permission");
                return null;
            }
            File destFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), System.currentTimeMillis() + ".txt");
            if (!save(destFile, content))
                return null;
            Uri uri = null;
            if (destFile.exists())
                uri = Uri.parse("file://" + destFile.getAbsolutePath());
            return uri;
        } else {//android Q
            Uri contentUri;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                contentUri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
            } else
                contentUri = MediaStore.Downloads.INTERNAL_CONTENT_URI;
            //创建ContentValues对象，准备插入数据
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Downloads.MIME_TYPE, "text/plain");//文件格式
            contentValues.put(MediaStore.Downloads.DATE_TAKEN, System.currentTimeMillis());
            contentValues.put(MediaStore.Downloads.DISPLAY_NAME, System.currentTimeMillis());//文件名字
            Uri fileUri = mContext.getContentResolver().insert(contentUri, contentValues);
            if (fileUri == null)
                return null;
            OutputStream outputStream = null;
            try {
                outputStream = mContext.getContentResolver().openOutputStream(fileUri);
                if (outputStream != null) {
                    outputStream.write(content.getBytes());
                    outputStream.flush();
                }
                return fileUri;
            } catch (Exception e) {
                e.printStackTrace();
                mContext.getContentResolver().delete(fileUri, null, null);  // 失败的时候，删除此 uri 记录
                return null;
            } finally {
                try {
                    if (outputStream != null)
                        outputStream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    private static boolean save(File file, String content) {
        if (!createFile(file, true)) {
            Log.e("FileSaveUtil", "create or delete file <$file> failed.");
            return false;
        }
        FileOutputStream outStream = null;
        boolean ret;
        try {
            outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.flush();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        } finally {
            try {
                if (outStream != null)
                    outStream.close();
            } catch (IOException e) {
                // ignore
            }
        }
        return ret;
    }

    private static boolean createFile(File file, boolean isDeleteOldFile) {
        if (file == null) return false;
        if (file.exists()) {
            if (isDeleteOldFile) {
                if (!file.delete()) return false;
            } else
                return file.isFile();
        }
        if (!createDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean createDir(File file) {
        if (file == null) return false;
        if (file.exists())
            return file.isDirectory();
        else
            return file.mkdirs();
    }

    private static boolean isGranted(Context context) {
        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

}
