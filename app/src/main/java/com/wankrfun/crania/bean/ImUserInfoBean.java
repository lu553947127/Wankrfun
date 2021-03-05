package com.wankrfun.crania.bean;

import java.io.Serializable;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: ImUserInfoBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/18/21 9:08 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/18/21 9:08 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ImUserInfoBean {
    /**
     * code : 0
     * data : {"nickname":"嘿嘿","avatar":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","userId":"Za2Ya7HuU4"}
     * error :
     */

    private int code;
    private DataBean data;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class DataBean implements Serializable {
        /**
         * nickname : 嘿嘿
         * avatar : http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim
         * userId : Za2Ya7HuU4
         */

        private String nickname;
        private String avatar;
        private String userId;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
