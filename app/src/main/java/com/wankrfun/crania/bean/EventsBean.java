package com.wankrfun.crania.bean;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: EventsBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/23/21 10:34 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/23/21 10:34 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsBean {
    /**
     * code : 0
     * data : {"total":82,"list":[{"activity_time":"2200-01-01T08:30:00.000Z","comment_num":2,"joined_user_num":3,"images":[{"currentTasks":[],"state":{"name":"93fe12094347a0af478e69f99a9d71d4_image.png","url":"http://domestic.media.jiaotangapp.com/93fe12094347a0af478e69f99a9d71d4_image.png?imageslim"},"taskQueue":{"lock":{"sync":{}}}}],"eventCreator":"yxEPQlsxpB","event_contents":"剧本杀密室逃脱组局！！\n有事没事一起刷个本建个群一起玩\n在？玩本吗","event_num_of_questions":0,"eventLocation":{"latitude":39.78862750823742,"longitude":116.56949829105565},"fav_user_num":0,"event_address":"可以商讨","eventTypeIcon":"0","eventType":"5","event_questions":[],"eventImage":"http://domestic.media.jiaotangapp.com/ee68abca12831dd035d88955891c8e9e_image.png?imageslim","eventDesc":"剧本杀密室逃脱组局！！\n有事没事一起刷个本建个群一起玩\n","creator_sex":"female","creator_address":"北京市, 中国","creator_name":"帅7网管","max_num":0,"objectId":"XkjcfZTLuv","creator_image":"http://domestic.media.jiaotangapp.com/7e115fd0b9de8f9091ac34f613b60bea_image.png?imageslim"}]}
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

    public static class DataBean {
        /**
         * total : 82
         * list : [{"activity_time":"2200-01-01T08:30:00.000Z","comment_num":2,"joined_user_num":3,"images":[{"currentTasks":[],"state":{"name":"93fe12094347a0af478e69f99a9d71d4_image.png","url":"http://domestic.media.jiaotangapp.com/93fe12094347a0af478e69f99a9d71d4_image.png?imageslim"},"taskQueue":{"lock":{"sync":{}}}}],"eventCreator":"yxEPQlsxpB","event_contents":"剧本杀密室逃脱组局！！\n有事没事一起刷个本建个群一起玩\n在？玩本吗","event_num_of_questions":0,"eventLocation":{"latitude":39.78862750823742,"longitude":116.56949829105565},"fav_user_num":0,"event_address":"可以商讨","eventTypeIcon":"0","eventType":"5","event_questions":[],"eventImage":"http://domestic.media.jiaotangapp.com/ee68abca12831dd035d88955891c8e9e_image.png?imageslim","eventDesc":"剧本杀密室逃脱组局！！\n有事没事一起刷个本建个群一起玩\n","creator_sex":"female","creator_address":"北京市, 中国","creator_name":"帅7网管","max_num":0,"objectId":"XkjcfZTLuv","creator_image":"http://domestic.media.jiaotangapp.com/7e115fd0b9de8f9091ac34f613b60bea_image.png?imageslim"}]
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

        public static class ListBean {
            /**
             * activity_time : 2200-01-01T08:30:00.000Z
             * comment_num : 2
             * joined_user_num : 3
             * images : [{"currentTasks":[],"state":{"name":"93fe12094347a0af478e69f99a9d71d4_image.png","url":"http://domestic.media.jiaotangapp.com/93fe12094347a0af478e69f99a9d71d4_image.png?imageslim"},"taskQueue":{"lock":{"sync":{}}}}]
             * eventCreator : yxEPQlsxpB
             * event_contents : 剧本杀密室逃脱组局！！
             有事没事一起刷个本建个群一起玩
             在？玩本吗
             * event_num_of_questions : 0
             * eventLocation : {"latitude":39.78862750823742,"longitude":116.56949829105565}
             * fav_user_num : 0
             * event_address : 可以商讨
             * eventTypeIcon : 0
             * eventType : 5
             * event_questions : []
             * eventImage : http://domestic.media.jiaotangapp.com/ee68abca12831dd035d88955891c8e9e_image.png?imageslim
             * eventDesc : 剧本杀密室逃脱组局！！
             有事没事一起刷个本建个群一起玩
             * creator_sex : female
             * creator_address : 北京市, 中国
             * creator_name : 帅7网管
             * max_num : 0
             * objectId : XkjcfZTLuv
             * creator_image : http://domestic.media.jiaotangapp.com/7e115fd0b9de8f9091ac34f613b60bea_image.png?imageslim
             */

            private String activity_time;
            private int comment_num;
            private int joined_user_num;
            private String eventCreator;
            private String event_contents;
            private int event_num_of_questions;
            private EventLocationBean eventLocation;
            private int fav_user_num;
            private String event_address;
            private String eventTypeIcon;
            private String eventType;
            private String eventImage;
            private String eventDesc;
            private String creator_sex;
            private String creator_address;
            private String creator_name;
            private int max_num;
            private String objectId;
            private String creator_image;
            private List<ImagesBean> images;
            private List<String> event_questions;
            private boolean isShow;

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

            public String getEvent_address() {
                return event_address;
            }

            public void setEvent_address(String event_address) {
                this.event_address = event_address;
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

            public String getCreator_address() {
                return creator_address;
            }

            public void setCreator_address(String creator_address) {
                this.creator_address = creator_address;
            }

            public String getCreator_name() {
                return creator_name;
            }

            public void setCreator_name(String creator_name) {
                this.creator_name = creator_name;
            }

            public int getMax_num() {
                return max_num;
            }

            public void setMax_num(int max_num) {
                this.max_num = max_num;
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

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public List<String> getEvent_questions() {
                return event_questions;
            }

            public void setEvent_questions(List<String> event_questions) {
                this.event_questions = event_questions;
            }

            public static class EventLocationBean  {
                /**
                 * latitude : 39.78862750823742
                 * longitude : 116.56949829105565
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

            public static class ImagesBean {
                /**
                 * currentTasks : []
                 * state : {"name":"93fe12094347a0af478e69f99a9d71d4_image.png","url":"http://domestic.media.jiaotangapp.com/93fe12094347a0af478e69f99a9d71d4_image.png?imageslim"}
                 * taskQueue : {"lock":{"sync":{}}}
                 */

                private StateBean state;
                private TaskQueueBean taskQueue;
                private List<?> currentTasks;

                public StateBean getState() {
                    return state;
                }

                public void setState(StateBean state) {
                    this.state = state;
                }

                public TaskQueueBean getTaskQueue() {
                    return taskQueue;
                }

                public void setTaskQueue(TaskQueueBean taskQueue) {
                    this.taskQueue = taskQueue;
                }

                public List<?> getCurrentTasks() {
                    return currentTasks;
                }

                public void setCurrentTasks(List<?> currentTasks) {
                    this.currentTasks = currentTasks;
                }

                public static class StateBean  {
                    /**
                     * name : 93fe12094347a0af478e69f99a9d71d4_image.png
                     * url : http://domestic.media.jiaotangapp.com/93fe12094347a0af478e69f99a9d71d4_image.png?imageslim
                     */

                    private String name;
                    private String url;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }

                public static class TaskQueueBean  {
                    /**
                     * lock : {"sync":{}}
                     */

                    private LockBean lock;

                    public LockBean getLock() {
                        return lock;
                    }

                    public void setLock(LockBean lock) {
                        this.lock = lock;
                    }

                    public static class LockBean  {
                        /**
                         * sync : {}
                         */

                        private SyncBean sync;

                        public SyncBean getSync() {
                            return sync;
                        }

                        public void setSync(SyncBean sync) {
                            this.sync = sync;
                        }

                        public static class SyncBean  {
                        }
                    }
                }
            }

            public boolean isShow() {
                return isShow;
            }

            public void setShow(boolean show) {
                isShow = show;
            }
        }
    }
}
