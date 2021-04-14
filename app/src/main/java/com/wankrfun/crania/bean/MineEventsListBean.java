package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: MineEventsListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/9/21 2:38 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/9/21 2:38 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineEventsListBean {
    /**
     * code : 0
     * data : {"total":7,"list":[{"eventId":"gxHJzgNkFq","createdAt":"2021-04-02T06:01:36.304Z","images":["http://domestic.media.jiaotangapp.com/589a35ee61d1c44e6b50e29c6b31b654_BDAB3244-0313-4290-A8A4-23F23114F5D1.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"0","eventType":"7","content":"","objectId":"ibe9C3WOas","updatedAt":"2021-04-02T06:01:36.304Z"},{"eventId":"TOS25vxzPz","createdAt":"2021-04-02T06:01:45.917Z","images":["http://domestic.media.jiaotangapp.com/62579ebf35ce6baa0c307df223f51419_53E5329B-2FA4-4FAA-A5D5-54AC05D9E0F2.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"1","eventType":"7","content":"","objectId":"rkMpSIbE8g","updatedAt":"2021-04-02T06:01:45.917Z"},{"eventId":"TOS25vxzPz","createdAt":"2021-04-02T06:01:53.814Z","images":["http://domestic.media.jiaotangapp.com/51d42dce8c09c90193cab26c8440f5de_BB5DED33-9BC5-4B54-A793-4E0592AADA2B.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"1","eventType":"7","content":"","objectId":"WjTPJVWvXY","updatedAt":"2021-04-02T06:01:53.814Z"},{"eventId":"nFnHUIwwRt","createdAt":"2021-04-02T06:02:38.021Z","images":["http://domestic.media.jiaotangapp.com/174bdaa38582395d8e5d933a61c13153_FCA40DED-EFA2-4959-81D8-325B5044560B.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"0","eventType":"11","content":"","objectId":"qgzMrWFnEz","updatedAt":"2021-04-02T06:02:38.021Z"},{"eventId":"AxyPhOVlYc","createdAt":"2021-04-06T07:04:30.097Z","images":["http://domestic.media.jiaotangapp.com/25e8ea1e14eeeee1ba33140783dbea64_26621CB7-9A37-458C-900B-B35453B1D311.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"2","eventType":"0","content":"","objectId":"buvb1Dhqjv","updatedAt":"2021-04-06T07:04:30.097Z"},{"eventId":"AxyPhOVlYc","createdAt":"2021-04-06T07:04:57.602Z","images":["http://domestic.media.jiaotangapp.com/981c316bcc7e7592e37411bf7cd99677_63F0B751-7A56-414C-B533-1349C6A2ACE3.jpg?imageslim","http://domestic.media.jiaotangapp.com/f0e899290478a5deaeea74f86e1db55e_681DF2BC-7324-4B7D-A1A2-EDC9525CE695.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"2","eventType":"0","content":"","objectId":"xAPgqMZHWl","updatedAt":"2021-04-06T07:04:57.602Z"},{"eventId":"TOS25vxzPz","createdAt":"2021-04-06T07:08:12.650Z","images":["http://domestic.media.jiaotangapp.com/83fca41154e112d11b2d4a6a743eea9e_1C78E5E3-D400-4FA0-87E9-539F82A13C27.jpg?imageslim","http://domestic.media.jiaotangapp.com/84877184d3931e1f6b3771826999806e_AA2BABC6-6FC1-426D-902A-C9CF867C85AE.jpg?imageslim","http://domestic.media.jiaotangapp.com/9db42133142ce3be82fcbc3f339aeeb6_0A9A473B-A9A6-4329-B5CD-6DA6503CA62C.jpg?imageslim","http://domestic.media.jiaotangapp.com/b4db12b633ab219608005de50cee56b6_9DC5300C-00BA-4E23-8621-ABA44791F605.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"1","eventType":"7","content":"","objectId":"sR8uCozaSD","updatedAt":"2021-04-06T07:08:12.650Z"}]}
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
         * total : 7
         * list : [{"eventId":"gxHJzgNkFq","createdAt":"2021-04-02T06:01:36.304Z","images":["http://domestic.media.jiaotangapp.com/589a35ee61d1c44e6b50e29c6b31b654_BDAB3244-0313-4290-A8A4-23F23114F5D1.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"0","eventType":"7","content":"","objectId":"ibe9C3WOas","updatedAt":"2021-04-02T06:01:36.304Z"},{"eventId":"TOS25vxzPz","createdAt":"2021-04-02T06:01:45.917Z","images":["http://domestic.media.jiaotangapp.com/62579ebf35ce6baa0c307df223f51419_53E5329B-2FA4-4FAA-A5D5-54AC05D9E0F2.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"1","eventType":"7","content":"","objectId":"rkMpSIbE8g","updatedAt":"2021-04-02T06:01:45.917Z"},{"eventId":"TOS25vxzPz","createdAt":"2021-04-02T06:01:53.814Z","images":["http://domestic.media.jiaotangapp.com/51d42dce8c09c90193cab26c8440f5de_BB5DED33-9BC5-4B54-A793-4E0592AADA2B.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"1","eventType":"7","content":"","objectId":"WjTPJVWvXY","updatedAt":"2021-04-02T06:01:53.814Z"},{"eventId":"nFnHUIwwRt","createdAt":"2021-04-02T06:02:38.021Z","images":["http://domestic.media.jiaotangapp.com/174bdaa38582395d8e5d933a61c13153_FCA40DED-EFA2-4959-81D8-325B5044560B.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"0","eventType":"11","content":"","objectId":"qgzMrWFnEz","updatedAt":"2021-04-02T06:02:38.021Z"},{"eventId":"AxyPhOVlYc","createdAt":"2021-04-06T07:04:30.097Z","images":["http://domestic.media.jiaotangapp.com/25e8ea1e14eeeee1ba33140783dbea64_26621CB7-9A37-458C-900B-B35453B1D311.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"2","eventType":"0","content":"","objectId":"buvb1Dhqjv","updatedAt":"2021-04-06T07:04:30.097Z"},{"eventId":"AxyPhOVlYc","createdAt":"2021-04-06T07:04:57.602Z","images":["http://domestic.media.jiaotangapp.com/981c316bcc7e7592e37411bf7cd99677_63F0B751-7A56-414C-B533-1349C6A2ACE3.jpg?imageslim","http://domestic.media.jiaotangapp.com/f0e899290478a5deaeea74f86e1db55e_681DF2BC-7324-4B7D-A1A2-EDC9525CE695.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"2","eventType":"0","content":"","objectId":"xAPgqMZHWl","updatedAt":"2021-04-06T07:04:57.602Z"},{"eventId":"TOS25vxzPz","createdAt":"2021-04-06T07:08:12.650Z","images":["http://domestic.media.jiaotangapp.com/83fca41154e112d11b2d4a6a743eea9e_1C78E5E3-D400-4FA0-87E9-539F82A13C27.jpg?imageslim","http://domestic.media.jiaotangapp.com/84877184d3931e1f6b3771826999806e_AA2BABC6-6FC1-426D-902A-C9CF867C85AE.jpg?imageslim","http://domestic.media.jiaotangapp.com/9db42133142ce3be82fcbc3f339aeeb6_0A9A473B-A9A6-4329-B5CD-6DA6503CA62C.jpg?imageslim","http://domestic.media.jiaotangapp.com/b4db12b633ab219608005de50cee56b6_9DC5300C-00BA-4E23-8621-ABA44791F605.jpg?imageslim"],"creatorId":"4GE1YKCnAY","eventIcon":"1","eventType":"7","content":"","objectId":"sR8uCozaSD","updatedAt":"2021-04-06T07:08:12.650Z"}]
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
             * eventId : gxHJzgNkFq
             * createdAt : 2021-04-02T06:01:36.304Z
             * images : ["http://domestic.media.jiaotangapp.com/589a35ee61d1c44e6b50e29c6b31b654_BDAB3244-0313-4290-A8A4-23F23114F5D1.jpg?imageslim"]
             * creatorId : 4GE1YKCnAY
             * eventIcon : 0
             * eventType : 7
             * content :
             * objectId : ibe9C3WOas
             * updatedAt : 2021-04-02T06:01:36.304Z
             */

            private String eventId;
            private String createdAt;
            private String creatorId;
            private String eventIcon;
            private String eventType;
            private String content;
            private String objectId;
            private String updatedAt;
            private List<String> images;

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
            }

            public String getEventIcon() {
                return eventIcon;
            }

            public void setEventIcon(String eventIcon) {
                this.eventIcon = eventIcon;
            }

            public String getEventType() {
                return eventType;
            }

            public void setEventType(String eventType) {
                this.eventType = eventType;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }
    }
}
