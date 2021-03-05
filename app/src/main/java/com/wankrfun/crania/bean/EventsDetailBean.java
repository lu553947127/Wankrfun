package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: EventsDetailBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/5/21 11:24 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/5/21 11:24 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsDetailBean implements Serializable {
    /**
     * code : 0
     * data : {"event":{"activity_time":"2200-01-01T08:30:00.000Z","comment_num":0,"joined_user_num":0,"creator_events":1,"event_contents":"本人lgbt哈 可以一起交友一起密室 下午茶等 介意勿扰","eventCreator":"FGqSWFe1Bg","creator_joinedEvents":1,"groupId":"iKkgDZCFOf","event_address":"北京市","creator_age":19,"creator_job":"s:","event_questions":[],"eventDesc":"本人lgbt哈 可以一起交友一起密室 下午茶等 介意勿扰","eventImage":"http://domestic.media.jiaotangapp.com/d6673b419aa15645feb71a301eff1182_image.png?imageslim","creator_address":"北京市, 中国","objectId":"2vC0wA5yBj","creator_distance":366,"creator_image":"http://domestic.media.jiaotangapp.com/b4f8204b9f2c6fe058134382fc1fc599_image.png?imageslim","images":["http://domestic.media.jiaotangapp.com/d3f109d5f7b8ddaf054f75d77c91862a_image.png?imageslim","http://domestic.media.jiaotangapp.com/823fd7d0b94dab8b76664ecffab4e84b_image.png?imageslim"],"event_num_of_questions":0,"eventLocation":{"latitude":39.904989,"longitude":116.405285},"fav_user_num":0,"eventTypeIcon":"0","eventType":"5","groupImage":"http://domestic.media.jiaotangapp.com/d6673b419aa15645feb71a301eff1182_image.png?imageslim","groupName":"桌游","creator_sex":"male","creator_name":"神仙小乐","creator_constellation":"魔羯座","max_num":0}}
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
         * event : {"activity_time":"2200-01-01T08:30:00.000Z","comment_num":0,"joined_user_num":0,"creator_events":1,"event_contents":"本人lgbt哈 可以一起交友一起密室 下午茶等 介意勿扰","eventCreator":"FGqSWFe1Bg","creator_joinedEvents":1,"groupId":"iKkgDZCFOf","event_address":"北京市","creator_age":19,"creator_job":"s:","event_questions":[],"eventDesc":"本人lgbt哈 可以一起交友一起密室 下午茶等 介意勿扰","eventImage":"http://domestic.media.jiaotangapp.com/d6673b419aa15645feb71a301eff1182_image.png?imageslim","creator_address":"北京市, 中国","objectId":"2vC0wA5yBj","creator_distance":366,"creator_image":"http://domestic.media.jiaotangapp.com/b4f8204b9f2c6fe058134382fc1fc599_image.png?imageslim","images":["http://domestic.media.jiaotangapp.com/d3f109d5f7b8ddaf054f75d77c91862a_image.png?imageslim","http://domestic.media.jiaotangapp.com/823fd7d0b94dab8b76664ecffab4e84b_image.png?imageslim"],"event_num_of_questions":0,"eventLocation":{"latitude":39.904989,"longitude":116.405285},"fav_user_num":0,"eventTypeIcon":"0","eventType":"5","groupImage":"http://domestic.media.jiaotangapp.com/d6673b419aa15645feb71a301eff1182_image.png?imageslim","groupName":"桌游","creator_sex":"male","creator_name":"神仙小乐","creator_constellation":"魔羯座","max_num":0}
         */

        private EventBean event;

        public EventBean getEvent() {
            return event;
        }

        public void setEvent(EventBean event) {
            this.event = event;
        }

        public static class EventBean implements Serializable {
            /**
             * activity_time : 2200-01-01T08:30:00.000Z
             * comment_num : 0
             * joined_user_num : 0
             * creator_events : 1
             * event_contents : 本人lgbt哈 可以一起交友一起密室 下午茶等 介意勿扰
             * eventCreator : FGqSWFe1Bg
             * creator_joinedEvents : 1
             * groupId : iKkgDZCFOf
             * event_address : 北京市
             * creator_age : 19
             * creator_job : s:
             * event_questions : []
             * eventDesc : 本人lgbt哈 可以一起交友一起密室 下午茶等 介意勿扰
             * eventImage : http://domestic.media.jiaotangapp.com/d6673b419aa15645feb71a301eff1182_image.png?imageslim
             * creator_address : 北京市, 中国
             * objectId : 2vC0wA5yBj
             * creator_distance : 366
             * creator_image : http://domestic.media.jiaotangapp.com/b4f8204b9f2c6fe058134382fc1fc599_image.png?imageslim
             * images : ["http://domestic.media.jiaotangapp.com/d3f109d5f7b8ddaf054f75d77c91862a_image.png?imageslim","http://domestic.media.jiaotangapp.com/823fd7d0b94dab8b76664ecffab4e84b_image.png?imageslim"]
             * event_num_of_questions : 0
             * eventLocation : {"latitude":39.904989,"longitude":116.405285}
             * fav_user_num : 0
             * eventTypeIcon : 0
             * eventType : 5
             * groupImage : http://domestic.media.jiaotangapp.com/d6673b419aa15645feb71a301eff1182_image.png?imageslim
             * groupName : 桌游
             * creator_sex : male
             * creator_name : 神仙小乐
             * creator_constellation : 魔羯座
             * max_num : 0
             */

            private String activity_time;
            private int comment_num;
            private int joined_user_num;
            private int creator_events;
            private String event_contents;
            private String eventCreator;
            private int creator_joinedEvents;
            private String groupId;
            private String event_address;
            private int creator_age;
            private String creator_job;
            private String eventDesc;
            private String eventImage;
            private String creator_address;
            private String objectId;
            private int creator_distance;
            private String creator_image;
            private int event_num_of_questions;
            private EventLocationBean eventLocation;
            private int fav_user_num;
            private String eventTypeIcon;
            private String eventType;
            private String groupImage;
            private String groupName;
            private String creator_sex;
            private String creator_name;
            private String creator_constellation;
            private int max_num;
            private String eventSex;
            private List<String> event_questions;
            private List<String> images;

            public String getActivity_time() {
                return activity_time;
            }

            public void setActivity_time(String activity_time) {
                this.activity_time = activity_time;
            }

            public int getComment_num() {
                return comment_num;
            }

            public void setComment_num(int comment_num) {
                this.comment_num = comment_num;
            }

            public int getJoined_user_num() {
                return joined_user_num;
            }

            public void setJoined_user_num(int joined_user_num) {
                this.joined_user_num = joined_user_num;
            }

            public int getCreator_events() {
                return creator_events;
            }

            public void setCreator_events(int creator_events) {
                this.creator_events = creator_events;
            }

            public String getEvent_contents() {
                return event_contents;
            }

            public void setEvent_contents(String event_contents) {
                this.event_contents = event_contents;
            }

            public String getEventCreator() {
                return eventCreator;
            }

            public void setEventCreator(String eventCreator) {
                this.eventCreator = eventCreator;
            }

            public int getCreator_joinedEvents() {
                return creator_joinedEvents;
            }

            public void setCreator_joinedEvents(int creator_joinedEvents) {
                this.creator_joinedEvents = creator_joinedEvents;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getEvent_address() {
                return event_address;
            }

            public void setEvent_address(String event_address) {
                this.event_address = event_address;
            }

            public int getCreator_age() {
                return creator_age;
            }

            public void setCreator_age(int creator_age) {
                this.creator_age = creator_age;
            }

            public String getCreator_job() {
                return creator_job;
            }

            public void setCreator_job(String creator_job) {
                this.creator_job = creator_job;
            }

            public String getEventDesc() {
                return eventDesc;
            }

            public void setEventDesc(String eventDesc) {
                this.eventDesc = eventDesc;
            }

            public String getEventImage() {
                return eventImage;
            }

            public void setEventImage(String eventImage) {
                this.eventImage = eventImage;
            }

            public String getCreator_address() {
                return creator_address;
            }

            public void setCreator_address(String creator_address) {
                this.creator_address = creator_address;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public int getCreator_distance() {
                return creator_distance;
            }

            public void setCreator_distance(int creator_distance) {
                this.creator_distance = creator_distance;
            }

            public String getCreator_image() {
                return creator_image;
            }

            public void setCreator_image(String creator_image) {
                this.creator_image = creator_image;
            }

            public int getEvent_num_of_questions() {
                return event_num_of_questions;
            }

            public void setEvent_num_of_questions(int event_num_of_questions) {
                this.event_num_of_questions = event_num_of_questions;
            }

            public EventLocationBean getEventLocation() {
                return eventLocation;
            }

            public void setEventLocation(EventLocationBean eventLocation) {
                this.eventLocation = eventLocation;
            }

            public int getFav_user_num() {
                return fav_user_num;
            }

            public void setFav_user_num(int fav_user_num) {
                this.fav_user_num = fav_user_num;
            }

            public String getEventTypeIcon() {
                return eventTypeIcon;
            }

            public void setEventTypeIcon(String eventTypeIcon) {
                this.eventTypeIcon = eventTypeIcon;
            }

            public String getEventType() {
                return eventType;
            }

            public void setEventType(String eventType) {
                this.eventType = eventType;
            }

            public String getGroupImage() {
                return groupImage;
            }

            public void setGroupImage(String groupImage) {
                this.groupImage = groupImage;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
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

            public String getCreator_constellation() {
                return creator_constellation;
            }

            public void setCreator_constellation(String creator_constellation) {
                this.creator_constellation = creator_constellation;
            }

            public int getMax_num() {
                return max_num;
            }

            public void setMax_num(int max_num) {
                this.max_num = max_num;
            }

            public String getEventSex() {
                return eventSex;
            }

            public void setEventSex(String eventSex) {
                this.eventSex = eventSex;
            }

            public List<String> getEvent_questions() {
                return event_questions;
            }

            public void setEvent_questions(List<String> event_questions) {
                this.event_questions = event_questions;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public static class EventLocationBean implements Serializable {
                /**
                 * latitude : 39.904989
                 * longitude : 116.405285
                 */

                private double latitude;
                private double longitude;

                public double getLatitude() {
                    return latitude;
                }

                public void setLatitude(double latitude) {
                    this.latitude = latitude;
                }

                public double getLongitude() {
                    return longitude;
                }

                public void setLongitude(double longitude) {
                    this.longitude = longitude;
                }
            }
        }
    }
}
