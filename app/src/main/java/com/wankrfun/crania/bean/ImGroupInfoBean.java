package com.wankrfun.crania.bean;

import java.io.Serializable;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: ImGroupInfoBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/19/21 1:48 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/19/21 1:48 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ImGroupInfoBean {
    /**
     * code : 0
     * data : {"membersNum":3,"groupName":"饭局","groupId":"e8TNfO3ibY","groupImage":"http://domestic.media.jiaotangapp.com/44f758d8837b859907cd70e1902e3b6b_image.png?imageslim"}
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
         * membersNum : 3
         * groupName : 饭局
         * groupId : e8TNfO3ibY
         * groupImage : http://domestic.media.jiaotangapp.com/44f758d8837b859907cd70e1902e3b6b_image.png?imageslim
         */

        private int membersNum;
        private String groupName;
        private String groupId;
        private String groupImage;

        public int getMembersNum() {
            return membersNum;
        }

        public void setMembersNum(int membersNum) {
            this.membersNum = membersNum;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGroupImage() {
            return groupImage;
        }

        public void setGroupImage(String groupImage) {
            this.groupImage = groupImage;
        }
    }
}
