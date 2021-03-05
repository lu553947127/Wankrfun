package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: UserEventsListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/10/21 8:30 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/10/21 8:30 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserEventsListBean {
    /**
     * error :
     * code : 0
     * data : {"activeList":[{"userStatus":"joined","eventType":"0","activity_time":"2200-01-01T08:30:00.000Z","creator_name":"嘿嘿","eventTypeIcon":"0","creator_sex":"male","creator_image":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","eventCreator":"Za2Ya7HuU4","event_contents":"尝尝东南亚菜","eventImage":"http://domestic.media.jiaotangapp.com/44f758d8837b859907cd70e1902e3b6b_image.png?imageslim","eventDesc":"尝尝东南亚菜","objectId":"AFWrWNEc8i"}],"total":4,"expiredList":[{"userStatus":"joined","eventType":"1","activity_time":"2021-02-08T06:10:28.000Z","creator_name":"嘿嘿","eventTypeIcon":"6","creator_sex":"male","creator_image":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","eventCreator":"Za2Ya7HuU4","event_contents":"一起来运动吧","eventImage":"http://domestic.media.jiaotangapp.com/a29e28264c3fd932e2a1bcc45e7455ad_Screenshot_20201231_165904_com.android.keyguard.jpg?imageslim","eventDesc":"一起来运动吧","objectId":"zy4x73BVTz"},{"userStatus":"joined","eventType":"7","activity_time":"2021-02-08T06:12:00.000Z","creator_name":"嘿嘿","eventTypeIcon":"5","creator_sex":"male","creator_image":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","eventCreator":"Za2Ya7HuU4","event_contents":"一起来嗨皮吧","eventImage":"http://domestic.media.jiaotangapp.com/bd3b55371b4fb4370971edc5f6bde6e8_1583825376391.jpg?imageslim","eventDesc":"一起来嗨皮吧","objectId":"0H5sKFHYCX"},{"userStatus":"joined","eventType":"0","activity_time":"2021-02-08T06:16:39.000Z","creator_name":"嘿嘿","eventTypeIcon":"0","creator_sex":"male","creator_image":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","eventCreator":"Za2Ya7HuU4","event_contents":"一起来没事","eventImage":"","eventDesc":"一起来没事","objectId":"FmEoaX8pH2"}]}
     */

    private String error;
    private int code;
    private DataBean data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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

    public static class DataBean implements Serializable {
        /**
         * activeList : [{"userStatus":"joined","eventType":"0","activity_time":"2200-01-01T08:30:00.000Z","creator_name":"嘿嘿","eventTypeIcon":"0","creator_sex":"male","creator_image":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","eventCreator":"Za2Ya7HuU4","event_contents":"尝尝东南亚菜","eventImage":"http://domestic.media.jiaotangapp.com/44f758d8837b859907cd70e1902e3b6b_image.png?imageslim","eventDesc":"尝尝东南亚菜","objectId":"AFWrWNEc8i"}]
         * total : 4
         * expiredList : [{"userStatus":"joined","eventType":"1","activity_time":"2021-02-08T06:10:28.000Z","creator_name":"嘿嘿","eventTypeIcon":"6","creator_sex":"male","creator_image":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","eventCreator":"Za2Ya7HuU4","event_contents":"一起来运动吧","eventImage":"http://domestic.media.jiaotangapp.com/a29e28264c3fd932e2a1bcc45e7455ad_Screenshot_20201231_165904_com.android.keyguard.jpg?imageslim","eventDesc":"一起来运动吧","objectId":"zy4x73BVTz"},{"userStatus":"joined","eventType":"7","activity_time":"2021-02-08T06:12:00.000Z","creator_name":"嘿嘿","eventTypeIcon":"5","creator_sex":"male","creator_image":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","eventCreator":"Za2Ya7HuU4","event_contents":"一起来嗨皮吧","eventImage":"http://domestic.media.jiaotangapp.com/bd3b55371b4fb4370971edc5f6bde6e8_1583825376391.jpg?imageslim","eventDesc":"一起来嗨皮吧","objectId":"0H5sKFHYCX"},{"userStatus":"joined","eventType":"0","activity_time":"2021-02-08T06:16:39.000Z","creator_name":"嘿嘿","eventTypeIcon":"0","creator_sex":"male","creator_image":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","eventCreator":"Za2Ya7HuU4","event_contents":"一起来没事","eventImage":"","eventDesc":"一起来没事","objectId":"FmEoaX8pH2"}]
         */

        private int total;
        private List<ActiveListBean> activeList;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ActiveListBean> getActiveList() {
            return activeList;
        }

        public void setActiveList(List<ActiveListBean> activeList) {
            this.activeList = activeList;
        }

        public static class ActiveListBean implements Serializable {
            /**
             * userStatus : joined
             * eventType : 0
             * activity_time : 2200-01-01T08:30:00.000Z
             * creator_name : 嘿嘿
             * eventTypeIcon : 0
             * creator_sex : male
             * creator_image : http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim
             * eventCreator : Za2Ya7HuU4
             * event_contents : 尝尝东南亚菜
             * eventImage : http://domestic.media.jiaotangapp.com/44f758d8837b859907cd70e1902e3b6b_image.png?imageslim
             * eventDesc : 尝尝东南亚菜
             * objectId : AFWrWNEc8i
             */

            private String userStatus;
            private String eventType;
            private String activity_time;
            private String creator_name;
            private String eventTypeIcon;
            private String creator_sex;
            private String creator_image;
            private String eventCreator;
            private String event_contents;
            private String eventImage;
            private String eventDesc;
            private String objectId;
            private String event_address;
            private String joined_user_num;

            public String getEvent_address() {
                return event_address;
            }

            public void setEvent_address(String event_address) {
                this.event_address = event_address;
            }

            public String getJoined_user_num() {
                return joined_user_num;
            }

            public void setJoined_user_num(String joined_user_num) {
                this.joined_user_num = joined_user_num;
            }

            public String getUserStatus() {
                return userStatus;
            }

            public void setUserStatus(String userStatus) {
                this.userStatus = userStatus;
            }

            public String getEventType() {
                return eventType;
            }

            public void setEventType(String eventType) {
                this.eventType = eventType;
            }

            public String getActivity_time() {
                return activity_time;
            }

            public void setActivity_time(String activity_time) {
                this.activity_time = activity_time;
            }

            public String getCreator_name() {
                return creator_name;
            }

            public void setCreator_name(String creator_name) {
                this.creator_name = creator_name;
            }

            public String getEventTypeIcon() {
                return eventTypeIcon;
            }

            public void setEventTypeIcon(String eventTypeIcon) {
                this.eventTypeIcon = eventTypeIcon;
            }

            public String getCreator_sex() {
                return creator_sex;
            }

            public void setCreator_sex(String creator_sex) {
                this.creator_sex = creator_sex;
            }

            public String getCreator_image() {
                return creator_image;
            }

            public void setCreator_image(String creator_image) {
                this.creator_image = creator_image;
            }

            public String getEventCreator() {
                return eventCreator;
            }

            public void setEventCreator(String eventCreator) {
                this.eventCreator = eventCreator;
            }

            public String getEvent_contents() {
                return event_contents;
            }

            public void setEvent_contents(String event_contents) {
                this.event_contents = event_contents;
            }

            public String getEventImage() {
                return eventImage;
            }

            public void setEventImage(String eventImage) {
                this.eventImage = eventImage;
            }

            public String getEventDesc() {
                return eventDesc;
            }

            public void setEventDesc(String eventDesc) {
                this.eventDesc = eventDesc;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }
        }
    }
}
