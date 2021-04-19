package com.wankrfun.crania.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.widget
 * @ClassName: Anticlockwise
 * @Description: 倒计时显示view
 * @Author: 鹿鸿祥
 * @CreateDate: 4/9/21 4:12 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/9/21 4:12 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint({ "ViewConstructor", "SimpleDateFormat" })
public class Anticlockwise extends Chronometer {

    private long mTime;
    private long mNextTime;
    private OnTimeCompleteListener mListener;
    private SimpleDateFormat mTimeFormat;

    public Anticlockwise(Context context) {
        super(context);
    }

    public Anticlockwise(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO 自动生成的构造函数存根
        mTimeFormat = new SimpleDateFormat("HH:mm:ss");
        this.setOnChronometerTickListener(listener);
    }

    /**
     * 重新启动计时
     */
    public void reStart(long _time_s) {
        if (_time_s == -1) {
            mNextTime = mTime;
        } else {
            mTime = mNextTime = _time_s;
        }
        this.start();
    }

    public void reStart() {
        reStart(-1);
    }

    /**
     * 继续计时
     */
    public void onResume() {
        this.start();
    }

    /**
     * 暂停计时
     */
    public void onPause() {
        this.stop();
    }

    /**
     * 设置时间格式
     *
     * @param pattern
     * 计时格式
     */
    public void setTimeFormat(String pattern) {
        mTimeFormat = new SimpleDateFormat(pattern);
    }

    public void setOnTimeCompleteListener(OnTimeCompleteListener l) {
        mListener = l;
    }

    OnChronometerTickListener listener = new OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            if (mNextTime <= 0) {
                if (mNextTime == 0) {
                    Anticlockwise.this.stop();
                    if (null != mListener)
                        mListener.onTimeComplete();
                }
                mNextTime = 0;
                updateTimeText();
                return;
            }
            mNextTime--;
            updateTimeText();
        }
    };

    /**
     * 初始化时间
     * @param _time_s
     */
    public void initTime(long _time_s) {
        mTime = mNextTime = _time_s;
        updateTimeText();
    }

    private void updateTimeText() {
        this.setText(mTimeFormat.format(new Date(mNextTime * 1000)));
    }

    interface OnTimeCompleteListener {
        void onTimeComplete();
    }
}
