package com.wankrfun.crania.bean;

import java.io.Serializable;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: GroupRelationBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/25/21 1:13 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/25/21 1:13 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GroupRelationBean {
    /**
     * code : 0
     * data : {"reason":"event","event":{"activity_time":"2200-01-01T08:30:00.000Z","eventCreator":"9CvM9jgQm9","event_contents":"哦哦哦哦","eventImage":"","creator_sex":"male","creator_name":"哈哈","objectId":"EVCFnLF661","creator_image":"http://domestic.media.jiaotangapp.com/78236aabdad0993874831a3af3074e13_image.png?imageslim"}}
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
         * reason : event
         * event : {"activity_time":"2200-01-01T08:30:00.000Z","eventCreator":"9CvM9jgQm9","event_contents":"哦哦哦哦","eventImage":"","creator_sex":"male","creator_name":"哈哈","objectId":"EVCFnLF661","creator_image":"http://domestic.media.jiaotangapp.com/78236aabdad0993874831a3af3074e13_image.png?imageslim"}
         */

        private String reason;
        private EventBean event;
        private MatchBean match;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public EventBean getEvent() {
            return event;
        }

        public void setEvent(EventBean event) {
            this.event = event;
        }

        public MatchBean getMatch() {
            return match;
        }

        public void setMatch(MatchBean match) {
            this.match = match;
        }

        public static class EventBean implements Serializable {
            /**
             * activity_time : 2200-01-01T08:30:00.000Z
             * eventCreator : 9CvM9jgQm9
             * event_contents : 哦哦哦哦
             * eventImage :
             * creator_sex : male
             * creator_name : 哈哈
             * objectId : EVCFnLF661
             * creator_image : http://domestic.media.jiaotangapp.com/78236aabdad0993874831a3af3074e13_image.png?imageslim
             */

            private String activity_time;
            private String eventCreator;
            private String event_contents;
            private String eventImage;
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

        public static class MatchBean implements Serializable {
            String user_a_image;
            String user_b_image;
            String title;
            String relation;

            public String getUser_a_image() {
                return user_a_image;
            }

            public void setUser_a_image(String user_a_image) {
                this.user_a_image = user_a_image;
            }

            public String getUser_b_image() {
                return user_b_image;
            }

            public void setUser_b_image(String user_b_image) {
                this.user_b_image = user_b_image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }
        }
    }
}
