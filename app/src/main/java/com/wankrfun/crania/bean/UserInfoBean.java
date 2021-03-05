package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: UserInfoBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/10/21 10:04 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/10/21 10:04 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserInfoBean {
    /**
     * code : 0
     * data : {"profile":{"birthday":"1993-12-02","images":["http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim"],"sport_tag":"跑步","address":"上海市, 中国","movie_tag":"复件","book_tag":"小说","backgroundImage":"http://domestic.media.jiaotangapp.com/ebb629821a557ace05578893486411b1_file?imageslim","music_tag":"古典","sex":"male","photo":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","likeNum":3,"party_tag":"我是海量","dinner_tag":"减肥中","home_tag":"北京","event_tag":["#吃饭叫我"],"constellation":"射手座","fitness_tag":"沉迷锻炼","name":"嘿嘿","pet_tag":"喜欢猫","tag":["#想恋爱"],"job":"c:","study_Tag":"清华","objectId":"Za2Ya7HuU4","age":27}}
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
         * profile : {"birthday":"1993-12-02","images":["http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim"],"sport_tag":"跑步","address":"上海市, 中国","movie_tag":"复件","book_tag":"小说","backgroundImage":"http://domestic.media.jiaotangapp.com/ebb629821a557ace05578893486411b1_file?imageslim","music_tag":"古典","sex":"male","photo":"http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim","likeNum":3,"party_tag":"我是海量","dinner_tag":"减肥中","home_tag":"北京","event_tag":["#吃饭叫我"],"constellation":"射手座","fitness_tag":"沉迷锻炼","name":"嘿嘿","pet_tag":"喜欢猫","tag":["#想恋爱"],"job":"c:","study_Tag":"清华","objectId":"Za2Ya7HuU4","age":27}
         */

        private ProfileBean profile;

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        public static class ProfileBean implements Serializable {
            /**
             * birthday : 1993-12-02
             * images : ["http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim"]
             * sport_tag : 跑步
             * address : 上海市, 中国
             * movie_tag : 复件
             * book_tag : 小说
             * backgroundImage : http://domestic.media.jiaotangapp.com/ebb629821a557ace05578893486411b1_file?imageslim
             * music_tag : 古典
             * sex : male
             * photo : http://domestic.media.jiaotangapp.com/1e34841e1bb9d026ed5fbb48a6c9374b_image.png?imageslim
             * likeNum : 3
             * party_tag : 我是海量
             * dinner_tag : 减肥中
             * home_tag : 北京
             * event_tag : ["#吃饭叫我"]
             * constellation : 射手座
             * fitness_tag : 沉迷锻炼
             * name : 嘿嘿
             * pet_tag : 喜欢猫
             * tag : ["#想恋爱"]
             * job : c:
             * study_Tag : 清华
             * objectId : Za2Ya7HuU4
             * age : 27
             */

            private String birthday;
            private String sport_tag;
            private String address;
            private String movie_tag;
            private String book_tag;
            private String backgroundImage;
            private String music_tag;
            private String sex;
            private String photo;
            private int likeNum;
            private String party_tag;
            private String dinner_tag;
            private String home_tag;
            private String constellation;
            private String fitness_tag;
            private String name;
            private String pet_tag;
            private String job;
            private String study_Tag;
            private String objectId;
            private int age;
            private List<String> images;
            private List<String> event_tag;
            private List<String> tag;

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getSport_tag() {
                return sport_tag;
            }

            public void setSport_tag(String sport_tag) {
                this.sport_tag = sport_tag;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getMovie_tag() {
                return movie_tag;
            }

            public void setMovie_tag(String movie_tag) {
                this.movie_tag = movie_tag;
            }

            public String getBook_tag() {
                return book_tag;
            }

            public void setBook_tag(String book_tag) {
                this.book_tag = book_tag;
            }

            public String getBackgroundImage() {
                return backgroundImage;
            }

            public void setBackgroundImage(String backgroundImage) {
                this.backgroundImage = backgroundImage;
            }

            public String getMusic_tag() {
                return music_tag;
            }

            public void setMusic_tag(String music_tag) {
                this.music_tag = music_tag;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getLikeNum() {
                return likeNum;
            }

            public void setLikeNum(int likeNum) {
                this.likeNum = likeNum;
            }

            public String getParty_tag() {
                return party_tag;
            }

            public void setParty_tag(String party_tag) {
                this.party_tag = party_tag;
            }

            public String getDinner_tag() {
                return dinner_tag;
            }

            public void setDinner_tag(String dinner_tag) {
                this.dinner_tag = dinner_tag;
            }

            public String getHome_tag() {
                return home_tag;
            }

            public void setHome_tag(String home_tag) {
                this.home_tag = home_tag;
            }

            public String getConstellation() {
                return constellation;
            }

            public void setConstellation(String constellation) {
                this.constellation = constellation;
            }

            public String getFitness_tag() {
                return fitness_tag;
            }

            public void setFitness_tag(String fitness_tag) {
                this.fitness_tag = fitness_tag;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPet_tag() {
                return pet_tag;
            }

            public void setPet_tag(String pet_tag) {
                this.pet_tag = pet_tag;
            }

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getStudy_Tag() {
                return study_Tag;
            }

            public void setStudy_Tag(String study_Tag) {
                this.study_Tag = study_Tag;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public List<String> getEvent_tag() {
                return event_tag;
            }

            public void setEvent_tag(List<String> event_tag) {
                this.event_tag = event_tag;
            }

            public List<String> getTag() {
                return tag;
            }

            public void setTag(List<String> tag) {
                this.tag = tag;
            }
        }
    }
}
