package com.wankrfun.crania.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.wankrfun.crania.event.ParseEvent;
import com.wankrfun.crania.http.retrofit.BaseRepository;

import java.io.File;
import java.util.List;

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
     * @param name 用户名
     * @param password 密码
     * @param name 用户昵称
     * @param sex 性别
     * @param birthday 出生日期
     * @param job 工作状态
     * @param tag 期待tag
     * @param event_tag 参加活动tag
     */
    public void getSaveUserInfo(String phone, String password, String name, String sex, String birthday, String job, List<String> tag, List<String> event_tag, ParseFile file){
        LogUtils.e(file);
        ParseUser user = new ParseUser();
        user.setUsername(phone);
        user.setPassword(password);
        user.put("phonenumber", phone);//手机号
        user.put("name", name);//用户昵称
        user.put("sex", sex);//male/female
        user.put("birthday", birthday);//YYYY-MM-dd
        user.put("job", job);//"s": 学生, "j":工作了, "c":自由职业
        user.put("tag", tag);//期待
        user.put("event_tag", event_tag);//活动
        user.put("photo", file);//用户头像
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    user.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                saveUserInfoLiveData.postValue(new ParseEvent("success"));
                            }else {
                                LogUtils.e("ParseException:" + e);
                                saveUserInfoLiveData.postValue(new ParseEvent(e.getMessage()));
                            }
                        }
                    });
                } else {
                    LogUtils.e("ParseException:" + e);
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
    public static ParseFile getUploadFile(File image){
        LogUtils.e(image);
        ParseFile file = new ParseFile(image, "picturePath");
        file.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    ToastUtils.showShort("图片上传成功");
                }else {
                    ToastUtils.showShort("图片上传失败");
                }
            }
        });
        return file;
    }
}
