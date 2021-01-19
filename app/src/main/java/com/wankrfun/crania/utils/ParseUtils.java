package com.wankrfun.crania.utils;

import com.blankj.utilcode.util.LogUtils;
import com.parse.FunctionCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.wankrfun.crania.event.ParseEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;

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

    public static void getSave(File image){

        ParseObject parseObject = new ParseObject("PFUser");
        parseObject.put("user", 1337);
        parseObject.put("playerName", "Sean Plott");
        parseObject.saveInBackground();



    }
}
