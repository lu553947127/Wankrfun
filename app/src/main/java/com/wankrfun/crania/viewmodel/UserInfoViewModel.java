package com.wankrfun.crania.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.wankrfun.crania.event.ParseEvent;
import com.wankrfun.crania.http.retrofit.BaseRepository;

import java.io.File;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.viewmodel
 * @ClassName: UserInfoViewModel
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 1:53 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 1:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserInfoViewModel extends BaseRepository {

    public MutableLiveData<String> pageStateLiveData;
    public MutableLiveData<String> failStateLiveData;
    public MutableLiveData<ParseEvent> saveUserInfoLiveData;

    public UserInfoViewModel() {
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        saveUserInfoLiveData = new MutableLiveData<>();
    }

    /**
     * 保存用户信息
     *
     * @param phone 账号
     * @param password 密码
     * @param sex 性别
     * @param birthday 出生日期
     * @param job 工作状态
     * @param tag 期待tag
     * @param event_tag 参加活动tag
     */
    public void getSaveUserInfo(String phone, String password, String sex, String birthday, String job, String tag, String event_tag){
        ParseUser user = new ParseUser();
        user.setUsername(phone);
        user.setPassword(password);
        user.put("sex", sex);//male/female
        user.put("birthday", birthday);//YYYY-MM-dd
        user.put("job", job);//"s": 学生, "j":工作了, "c":自由职业
        user.put("tag", tag);//期待
        user.put("event_tag", event_tag);//活动
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    saveUserInfoLiveData.postValue(new ParseEvent(e.getMessage()));
                } else {
                    saveUserInfoLiveData.postValue(new ParseEvent(e.getMessage()));
                }
            }
        });
    }

    /**
     * 上传图片
     *
     * @param image 文件流
     */
    public void getUploadFile(File image){
        LogUtils.e(image);
        ParseFile file = new ParseFile(image, "picturePath");
        // Upload the image into Parse Cloud
        file.saveInBackground();
        // Create a New Class called "ImageUpload" in Parse
        ParseObject imgupload = new ParseObject("Image");
        // Create a column named "ImageName" and set the string
        imgupload.put("Image", "picturePath");
        // Create a column named "ImageFile" and insert the image
        imgupload.put("ImageFile", file);
        // Create the class and the columns
        imgupload.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                LogUtils.e(e);
            }
        });
    }
}
