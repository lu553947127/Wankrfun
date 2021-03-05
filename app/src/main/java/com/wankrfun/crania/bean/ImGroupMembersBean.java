package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: ImGroupMembersBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/20/21 1:53 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/20/21 1:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ImGroupMembersBean {
    /**
     * code : 0
     * data : {"members":[{"sex":"male","nickname":"哈哈","avatar":"http://domestic.media.jiaotangapp.com/78236aabdad0993874831a3af3074e13_image.png?imageslim","userId":"9CvM9jgQm9"},{"sex":"male","nickname":"琛","avatar":"http://domestic.media.jiaotangapp.com/74530412152c2feaf50b6a4bd73049b0_image.png?imageslim","userId":"JTKisuUSeS"},{"sex":"male","nickname":"嘿嘿","avatar":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","userId":"Za2Ya7HuU4"},{"sex":"female","nickname":"小甜甜","avatar":"http://domestic.media.jiaotangapp.com/2a706bdcddeb4496475f47ff0a8f0231_image.png?imageslim","userId":"vLn2F4k1bF"}]}
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
        private List<MembersBean> members;

        public List<MembersBean> getMembers() {
            return members;
        }

        public void setMembers(List<MembersBean> members) {
            this.members = members;
        }

        public static class MembersBean implements Serializable {
            /**
             * sex : male
             * nickname : 哈哈
             * avatar : http://domestic.media.jiaotangapp.com/78236aabdad0993874831a3af3074e13_image.png?imageslim
             * userId : 9CvM9jgQm9
             */

            private String sex;
            private String nickname;
            private String avatar;
            private String userId;

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

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
}
