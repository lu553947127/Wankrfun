package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: MeetListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/4/21 3:38 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/4/21 3:38 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetListBean {
    /**
     * code : 0
     * data : {"total":5,"allowedToday":15,"list":[{"birthday":"1998-10-26","event_tag":["#玩什么都嗨皮"],"address":"淄博市, 中国","sex":"female","name":"vacation ","photo":"http://domestic.media.jiaotangapp.com/48a2957e6e98ff26eeacff3c5c599c6f_image.png?imageslim","tag":["#还没想好"],"job":"j:","objectId":"crX7Py5q8J"},{"birthday":"1998-03-28","event_tag":["#玩什么都嗨皮"],"address":"泰安市, 中国","sex":"female","name":"Minho","photo":"http://domestic.media.jiaotangapp.com/35fb62525d55ddcdad5e054b2e6b60d5_image.png?imageslim","tag":["#还没想好"],"job":"s:","objectId":"udhM0AU2BJ"},{"birthday":"1997-12-28","event_tag":["#玩什么都嗨皮"],"address":"济南市, 中国","sex":"female","name":"小猴子很淘气","photo":"http://domestic.media.jiaotangapp.com/39553f06dd6d4a7060d0be1e13f29833_image.png?imageslim","tag":["#还没想好"],"job":"s:","objectId":"aCkADkwmX9"},{"birthday":"1995-10-10","event_tag":["#玩什么都嗨皮"],"address":"济南市, 中国","sex":"female","name":"穆羊羊","photo":"http://domestic.media.jiaotangapp.com/c8a70867ef7e1e4d761425d53f0e91b3_image.png?imageslim","tag":["#还没想好"],"job":"j:","objectId":"efAK9aoZCM"},{"birthday":"1993-11-15","event_tag":["#一起来开黑"],"address":"济南市, 中国","sex":"female","name":"Lyq棒棒糖","photo":"http://domestic.media.jiaotangapp.com/ed1c917287d284862e9f3071176e40cc_image.png?imageslim","tag":["#打发时间"],"job":"j:","objectId":"OHEoEhCmc1"}]}
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
         * total : 5
         * allowedToday : 15
         * list : [{"birthday":"1998-10-26","event_tag":["#玩什么都嗨皮"],"address":"淄博市, 中国","sex":"female","name":"vacation ","photo":"http://domestic.media.jiaotangapp.com/48a2957e6e98ff26eeacff3c5c599c6f_image.png?imageslim","tag":["#还没想好"],"job":"j:","objectId":"crX7Py5q8J"},{"birthday":"1998-03-28","event_tag":["#玩什么都嗨皮"],"address":"泰安市, 中国","sex":"female","name":"Minho","photo":"http://domestic.media.jiaotangapp.com/35fb62525d55ddcdad5e054b2e6b60d5_image.png?imageslim","tag":["#还没想好"],"job":"s:","objectId":"udhM0AU2BJ"},{"birthday":"1997-12-28","event_tag":["#玩什么都嗨皮"],"address":"济南市, 中国","sex":"female","name":"小猴子很淘气","photo":"http://domestic.media.jiaotangapp.com/39553f06dd6d4a7060d0be1e13f29833_image.png?imageslim","tag":["#还没想好"],"job":"s:","objectId":"aCkADkwmX9"},{"birthday":"1995-10-10","event_tag":["#玩什么都嗨皮"],"address":"济南市, 中国","sex":"female","name":"穆羊羊","photo":"http://domestic.media.jiaotangapp.com/c8a70867ef7e1e4d761425d53f0e91b3_image.png?imageslim","tag":["#还没想好"],"job":"j:","objectId":"efAK9aoZCM"},{"birthday":"1993-11-15","event_tag":["#一起来开黑"],"address":"济南市, 中国","sex":"female","name":"Lyq棒棒糖","photo":"http://domestic.media.jiaotangapp.com/ed1c917287d284862e9f3071176e40cc_image.png?imageslim","tag":["#打发时间"],"job":"j:","objectId":"OHEoEhCmc1"}]
         */

        private int total;
        private int allowedToday;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getAllowedToday() {
            return allowedToday;
        }

        public void setAllowedToday(int allowedToday) {
            this.allowedToday = allowedToday;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * birthday : 1998-10-26
             * event_tag : ["#玩什么都嗨皮"]
             * address : 淄博市, 中国
             * sex : female
             * name : vacation
             * photo : http://domestic.media.jiaotangapp.com/48a2957e6e98ff26eeacff3c5c599c6f_image.png?imageslim
             * tag : ["#还没想好"]
             * job : j:
             * objectId : crX7Py5q8J
             */

            private String birthday;
            private String address;
            private String sex;
            private String name;
            private String photo;
            private String job;
            private String objectId;
            private List<String> event_tag;
            private List<String> tag;

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
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
