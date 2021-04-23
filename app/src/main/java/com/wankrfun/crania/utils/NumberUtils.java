package com.wankrfun.crania.utils;

import android.annotation.SuppressLint;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.wankrfun.crania.base.BaseActivity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: NumberUtils
 * @Description: 数字工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 1/8/21 3:28 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/8/21 3:28 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("SimpleDateFormat")
public class NumberUtils {

    /**
     * 根据区号判断是否是正确的电话号码
     *
     * @param activity 上下文
     * @param phoneNumber 带国家码的电话号码
     * @param countryCode 默认国家码
     * @return true 合法  false：不合法
     */
    public static boolean isPhoneNumberValid(BaseActivity activity,String phoneNumber, String countryCode){
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.createInstance(activity);
        try{
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, countryCode);
//            LogUtils.e("isPhoneNumberValid: " + phoneUtil.isValidNumber(numberProto));
            return phoneUtil.isValidNumber(numberProto);
        }catch (NumberParseException e){
//            LogUtils.e("isPhoneNumberValid NumberParseException was thrown: " + e.toString());
        }
        return false;
    }

    public static final String[] constellationArray = { "水瓶座", "双鱼座", "牡羊座",
            "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };

    public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22,
            23, 23, 23, 23, 22, 22 };

    /**
     * 根据日期获取星座
     *
     * @param time
     * @return
     */
    public static String date2Constellation(Calendar time) {
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArray[month];
        }
        // default to return 魔羯
        return constellationArray[11];
    }

    /**
     * 根据日期获取星座
     *
     * @param time
     * @return
     */
    public static String date2Constellation(String time) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = simpleDateFormat.parse(time);
            calendar.setTime(date);
            return date2Constellation(calendar);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 特殊日期时间转换
     *
     * @param oldDate
     * @return
     */
    public static String dealDateFormat(String oldDate) {
        Date date1 = null;
        DateFormat df2 = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return df2.format(date1);
    }

    /**
     * 特殊日期时间转换
     *
     * @param oldDate
     * @return
     */
    public static String dealDateFormatNew(String oldDate) {
        Date date1 = null;
        DateFormat df2 = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("MM月dd日 HH:mm");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return df2.format(date1);
    }

    /**
     * 根据日期计算出年龄
     *
     * @param time
     * @return
     */
    public static int getAge(String time) {
        int  age = 0;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay;
        try {
            birthDay = format.parse(time);
            if (cal.before(birthDay)) {
                throw new IllegalArgumentException( "The birthDay is before Now.It's unbelievable!" );
            }
            int  yearNow = cal.get(Calendar.YEAR);
            int  monthNow = cal.get(Calendar.MONTH);
            int  dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
            cal.setTime(birthDay);
            int  yearBirth = cal.get(Calendar.YEAR);
            int  monthBirth = cal.get(Calendar.MONTH);
            int  dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            age = yearNow - yearBirth;
            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth)
                        age--;
                }
                else {
                    age--;
                }
            }
            return  age;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return age;
    }

    /**
     * 计算两个坐标的距离
     *
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return
     */
    public static float getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        LatLng latLng=new LatLng(latitude1,longitude1);
        LatLng latLng2=new LatLng(latitude2,longitude2);
        float distance = AMapUtils.calculateLineDistance(latLng,latLng2);
        float qianmifload =(float) distance/1000;
        return round(qianmifload,2);
    }

    /**
     * 四舍五入到两位小数
     * @param v
     * @param scale
     * @return
     */
    public static float round(float v, int scale) {
        if (scale < 0)
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        BigDecimal bgNum1 = new BigDecimal(Float.toString(v));
        BigDecimal bgNum2 = new BigDecimal("1");
        return bgNum1.divide(bgNum2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
        // return b.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 工作文字转换
     *
     * @param job
     * @return
     */
    public static String getJob(String job){
        String str = null;
        switch (job){
            case "s:":
                str = "学生";
                break;
            case "j:":
                str = "工作";
                break;
            case "c:":
                str = "自由职业者";
                break;
        }
        return str;
    }

    /**
     * 将时间转换为时间戳
     *
     * @param dateString
     * @return
     */
    public static String dateToStamp(String dateString){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dealDateFormat(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        return getTimeAgo(ts, dealDateFormat(dateString));
    }

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    /**
     * 时间戳转换几天前，几分钟前，刚刚等
     *
     * @param time
     * @return
     */
    public static String getTimeAgo(long time, String times) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return "未知时间";
        }

        final long diff = now - time;

        if (diff < MINUTE_MILLIS) {
            return "刚刚";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "1分钟前";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + "分钟前";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "1小时前";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + "小时前";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "昨天";
        } else {
//            return diff / DAY_MILLIS + "天前";
            return times;
        }
    }

    /**
     * 根据时间字符串获取当前天
     *
     * @param dateStr 需要解析的日期字符串
     */
    public static String getTimeDay(String type, String dateStr){
        // 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，baiHH表示小时24小时制，小写的话是12小时制
        // mm，小写，表示分钟，ss表示秒
        Calendar calendar = null;
        SimpleDateFormat format = new SimpleDateFormat(type);
        try {
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dealDateFormat(dateStr));
            // 获取日期实例
            calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
            // 获取年
            int year = calendar.get(Calendar.YEAR);
            // 这里要注意，月份是从0开始。
            int month = calendar.get(Calendar.MONTH);
            // 获取天
            int day = calendar.get(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 根据时间字符串获取当前月
     *
     * @param dateStr 需要解析的日期字符串
     * @return
     */
    public static String getTimeMonth(String type, String dateStr){
        // 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，baiHH表示小时24小时制，小写的话是12小时制
        // mm，小写，表示分钟，ss表示秒
        Calendar calendar = null;
        SimpleDateFormat format = new SimpleDateFormat(type);
        try {
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dealDateFormat(dateStr));
            // 获取日期实例
            calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 判断两个日期相差几天
     *
     * @param startTime
     * @param endTime
     * @param format
     * @return
     */
    public static long dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。");
            if (day>=1) {
                return day;
            }else {
                if (day==0) {
                    return 1;
                }else {
                    return 0;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将list按照指定元素个数(n)分割
     *
     * @param source
     * @param n 每次分割的个数
     * @return
     */
    public static <T> List<List<T>> getBisectionList(List<T> source, int n) {
        if (source == null) {
            return null;
        }

        if (n == 0) {
            return null;
        }
        List<List<T>> result = new ArrayList<List<T>>();
        // 集合长度
        int size = source.size();
        // 余数
        int remaider = size % n;
        // 商
        int number = size / n;

        for (int i = 0; i < number; i++) {
            List<T> value = source.subList(i * n, (i + 1) * n);
            result.add(value);
        }

        if (remaider > 0) {
            List<T> subList = source.subList(size - remaider, size);
            result.add(subList);
        }
        return result;
    }
}
