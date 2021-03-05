package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: EventsParticipantsBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/6/21 11:30 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/6/21 11:30 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsParticipantsBean {
    /**
     * code : 0
     * data : {"numOfUsers":1,"participants":[{"applyId":"dMlVFJDgeh","eventId":"lLQRYM4GPg","sex":"female","name":"满洲里有象","photo":"http://domestic.media.jiaotangapp.com/7eeda700816c432c2aab00a24e2db23a_image.png?imageslim","state":"0","applyDate":"2021-01-31T02:04:49.374Z","objectId":"vxlVFsHKIe"}]}
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
         * numOfUsers : 1
         * participants : [{"applyId":"dMlVFJDgeh","eventId":"lLQRYM4GPg","sex":"female","name":"满洲里有象","photo":"http://domestic.media.jiaotangapp.com/7eeda700816c432c2aab00a24e2db23a_image.png?imageslim","state":"0","applyDate":"2021-01-31T02:04:49.374Z","objectId":"vxlVFsHKIe"}]
         */

        private int numOfUsers;
        private List<ParticipantsBean> participants;

        public int getNumOfUsers() {
            return numOfUsers;
        }

        public void setNumOfUsers(int numOfUsers) {
            this.numOfUsers = numOfUsers;
        }

        public List<ParticipantsBean> getParticipants() {
            return participants;
        }

        public void setParticipants(List<ParticipantsBean> participants) {
            this.participants = participants;
        }

        public static class ParticipantsBean implements Serializable {
            /**
             * applyId : dMlVFJDgeh
             * eventId : lLQRYM4GPg
             * sex : female
             * name : 满洲里有象
             * photo : http://domestic.media.jiaotangapp.com/7eeda700816c432c2aab00a24e2db23a_image.png?imageslim
             * state : 0
             * applyDate : 2021-01-31T02:04:49.374Z
             * objectId : vxlVFsHKIe
             */

            private String applyId;
            private String eventId;
            private String sex;
            private String name;
            private String photo;
            private String state;
            private String applyDate;
            private String objectId;

            public String getApplyId() {
                return applyId;
            }

            public void setApplyId(String applyId) {
                this.applyId = applyId;
            }

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getApplyDate() {
                return applyDate;
            }

            public void setApplyDate(String applyDate) {
                this.applyDate = applyDate;
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
