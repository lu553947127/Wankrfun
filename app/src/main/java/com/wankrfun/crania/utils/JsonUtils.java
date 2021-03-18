package com.wankrfun.crania.utils;

import com.blankj.utilcode.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: JsonUtils
 * @Description: 解析json数据
 * @Author: 鹿鸿祥
 * @CreateDate: 3/3/21 1:53 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/3/21 1:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JsonUtils {

    /**
     * 取json中的其中一个字符串
     *
     * @param json
     * @param data
     * @return
     */
    public static String getJsonString(String json, String data){
        String str = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        try {
            str = jsonObject.getString(data);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return str;
    }

    /**
     * 取json中的其中一段数据
     *
     * @param json 字符串
     * @return
     */
    public static Map<String, String> getJsonStringMap(String json, String parameter){
        Map<String, String> mapList = new HashMap<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        try {
            String str = jsonObject.getString("data");
            //拿到解析后的map集合
            mapList = getJsonMapList(str, parameter);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return mapList;
    }

    /**
     * 取json中的其中一段数据
     *
     * @param json 字符串
     * @return
     */
    public static List<String> getJsonStringList(String json, String parameter){
        List<String> dateList = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        try {
            String str = jsonObject.getString("data");
//            LogUtils.e(str);
            //拿到解析后的List集合
            dateList = getJsonDateList(str, parameter);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return dateList;
    }

    /**
     * json字符串转换成map集合
     *
     * @param json 字符串
     * @param parameter 参数
     * @return
     */
    public static Map<String, String> getJsonMapList(String json, String parameter){
        Map<String, String> mapList = new HashMap<>();
        JSONObject jsonObject = null;//json数据
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject data= null;
        try {
            data = Objects.requireNonNull(jsonObject).getJSONObject(parameter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 动态获取key值
        if (data != null){
            Iterator<String> iterator = data.keys();//使用迭代器
            while (iterator.hasNext()) {
                String key = iterator.next();//获取key
                String value = null;//获取value
                try {
                    value = data.getString(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                LogUtils.e("key-value","key="+key+" value="+value);
                mapList.put(key,value);
            }
        }
        return mapList;
    }

    /**
     * json字符串转换成List集合
     *
     * @param json 字符串
     * @param parameter 参数
     * @return
     */
    public static List<String> getJsonDateList(String json, String parameter){
//        LogUtils.e(parameter);
        List<String> dateList = new ArrayList<>();
        JSONObject jsonObject = null;//json数据
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject data= null;
        try {
            data = Objects.requireNonNull(jsonObject).getJSONObject(parameter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        LogUtils.e(data);
        // 动态获取key值
        if (data != null){
            Iterator<String> iterator = data.keys();//使用迭代器
            while (iterator.hasNext()) {
                String key = iterator.next();//获取key
                dateList.add(key);
            }
//            LogUtils.e(dateList);
        }
        return dateList;
    }
}
