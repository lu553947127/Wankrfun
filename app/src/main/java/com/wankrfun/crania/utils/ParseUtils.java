package com.wankrfun.crania.utils;

import android.text.TextUtils;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.wankrfun.crania.event.ParseEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: ParseUtils
 * @Description: Parse数据库管理工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 1/8/21 4:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/8/21 4:11 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ParseUtils {

    /**
     * 检索获取用户名称
     *
     * @param phone 用户手机号
     */
    public static void getQueryUserName(String phone){
        ParseQuery<ParseUser> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("phonenumber", phone);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, com.parse.ParseException e) {
                if (e == null) {
                    if (users.size() > 0 && !TextUtils.isEmpty(String.valueOf(users.get(0).get("name")))){
                        EventBus.getDefault().post(new ParseEvent(String.valueOf(users.get(0).get("name"))));
                    }
                } else {
                    EventBus.getDefault().post(new ParseEvent("暂无"));
                }
            }
        });
    }

    /**
     * 获取用户名称
     *
     * @return
     */
    public static String getUserName(){
        String str = null;
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            str = String.valueOf(currentUser.get("name"));
        }else {
            return "暂无";
        }
        return str;
    }

    /**
     * 获取用户头像
     *
     * @return
     */
    public static String getUserPhoto(){
        String str = null;
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseFile parseFile = (ParseFile) currentUser.get("photo");
            str = parseFile.getUrl();
        }else {
            return "";
        }
        return str;
    }

    /**
     * 获取用户性别 male男 female女
     *
     * @return
     */
    public static String getUserSex(){
        String str = null;
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            str = currentUser.get("sex").toString();
        }else {
            return "";
        }
        return str;
    }

    /**
     * 获取用户出生日期 YYYY-MM-dd
     *
     * @return
     */
    public static String getUserBirthday(){
        String str = null;
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            str = currentUser.get("birthday").toString();
        }else {
            return "";
        }
        return str;
    }

    /**
     * 获取用户工作状态 "s": 学生, "j":工作了, "c":自由职业
     *
     * @return
     */
    public static String getUserJob(){
        String str = null;
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            str = currentUser.get("job").toString();
        }else {
            return "";
        }
        return str;
    }

    /**
     * 获取用户期待标签
     *
     * @return
     */
    public static String getUserTag(){
        String str = null;
        List<String> list;
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            list = (List<String>) currentUser.get("tag");
            str = list.get(0);
        }else {
            return "";
        }
        return str;
    }

    /**
     * 获取用户活动标签
     *
     * @return
     */
    public static String getUserEventTag(){
        String str = null;
        List<String> list;
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            list = (List<String>) currentUser.get("event_tag");
            str = list.get(0);
        }else {
            return "";
        }
        return str;
    }

    /**
     * 保存图片
     *
     * @param image 本地图片路径
     * @return
     */
    public static ParseFile setImageFile(File image){
        ParseFile file = new ParseFile(image, "picturePath");
        try {
            file.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 保存图片列表
     *
     * @param list
     * @return
     */
    public static List<ParseFile> setImageFile(List<String> list){
        List<ParseFile> parseFileList = new ArrayList<>();
        parseFileList.clear();
        for (int i = 0; i< list.size(); i++){
            ParseFile file = new ParseFile(new File(list.get(i)), "picturePath");
            try {
                file.save();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            parseFileList.add(file);
        }
        return parseFileList;
    }
}
