package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: EventsJoinedListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/14/21 4:14 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/14/21 4:14 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsJoinedListBean {
    /**
     * code : 0
     * data : {"total":1,"list":[{"activity_time":"2200-01-01T08:30:00.000Z","joined_user_num":0,"eventCreator":"7otzDhP4za","event_contents":"游泳小分队集合啦🐶","eventTypeIcon":"10","event_address":"冠俊游泳俱乐部(新虹培训中心)","eventType":"1","createdAt":"2021-04-12T11:47:56.589Z","eventImage":"http://domestic.media.jiaotangapp.com/e7e7bae57b9ee26c1697f37038d3d732_image.png?imageslim","eventDesc":"游泳小分队集合啦🐶","creator_sex":"male","creator_name":"Burning","objectId":"luSegGHtYc","creator_image":"http://domestic.media.jiaotangapp.com/02c7d9b97486e0a4354e7b85e0b70891_image.png?imageslim"}]}
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
         * total : 1
         * list : [{"activity_time":"2200-01-01T08:30:00.000Z","joined_user_num":0,"eventCreator":"7otzDhP4za","event_contents":"游泳小分队集合啦🐶","eventTypeIcon":"10","event_address":"冠俊游泳俱乐部(新虹培训中心)","eventType":"1","createdAt":"2021-04-12T11:47:56.589Z","eventImage":"http://domestic.media.jiaotangapp.com/e7e7bae57b9ee26c1697f37038d3d732_image.png?imageslim","eventDesc":"游泳小分队集合啦🐶","creator_sex":"male","creator_name":"Burning","objectId":"luSegGHtYc","creator_image":"http://domestic.media.jiaotangapp.com/02c7d9b97486e0a4354e7b85e0b70891_image.png?imageslim"}]
         */

        private int total;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * activity_time : 2200-01-01T08:30:00.000Z
             * joined_user_num : 0
             * eventCreator : 7otzDhP4za
             * event_contents : 游泳小分队集合啦🐶
             * eventTypeIcon : 10
             * event_address : 冠俊游泳俱乐部(新虹培训中心)
             * eventType : 1
             * createdAt : 2021-04-12T11:47:56.589Z
             * eventImage : http://domestic.media.jiaotangapp.com/e7e7bae57b9ee26c1697f37038d3d732_image.png?imageslim
             * eventDesc : 游泳小分队集合啦🐶
             * creator_sex : male
             * creator_name : Burning
             * objectId : luSegGHtYc
             * creator_image : http://domestic.media.jiaotangapp.com/02c7d9b97486e0a4354e7b85e0b70891_image.png?imageslim
             */

            private String activity_time;
            private int joined_user_num;
            private String eventCreator;
            private String event_contents;
            private String eventTypeIcon;
            private String event_address;
            private String eventType;
            private String createdAt;
            private String eventImage;
            private String eventDesc;
            private String creator_sex;
            private String creator_name;
            private String objectId;
            private String creator_image;

            public String getActivity_time() {
                return activity_time;
            }

            public void setActivity_time(String activity_time) {
                this.activity_time = activity_time;
            }

            public int getJoined_user_num() {
                return joined_user_num;
            }

            public void setJoined_user_num(int joined_user_num) {
                this.joined_user_num = joined_user_num;
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

            public String getEventTypeIcon() {
                return eventTypeIcon;
            }

            public void setEventTypeIcon(String eventTypeIcon) {
                this.eventTypeIcon = eventTypeIcon;
            }

            public String getEvent_address() {
                return event_address;
            }

            public void setEvent_address(String event_address) {
                this.event_address = event_address;
            }

            public String getEventType() {
                return eventType;
            }

            public void setEventType(String eventType) {
                this.eventType = eventType;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
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

            public String getCreator_sex() {
                return creator_sex;
            }

            public void setCreator_sex(String creator_sex) {
                this.creator_sex = creator_sex;
            }

            public String getCreator_name() {
                return creator_name;
            }

            public void setCreator_name(String creator_name) {
                this.creator_name = creator_name;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String getCreator_image() {
                return creator_image;
            }

            public void setCreator_image(String creator_image) {
                this.creator_image = creator_image;
            }
        }
    }
}
