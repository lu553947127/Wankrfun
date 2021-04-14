package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: MineLifeListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/9/21 2:37 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/9/21 2:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineLifeListBean {
    /**
     * code : 0
     * data : {"total":1,"list":[{"createdAt":"2021-04-09T05:52:45.700Z","images":["http://domestic.media.jiaotangapp.com/c1367df964de32e46b92c061666b2ad7_F50E3382-5675-4AB7-B87D-E0E63CF4042C.jpg?imageslim"],"creatorId":"4GE1YKCnAY","icon":"homestay","content":"","objectId":"s6EGQDoJBI","updatedAt":"2021-04-09T05:52:45.700Z"}],"status":true}
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
         * list : [{"createdAt":"2021-04-09T05:52:45.700Z","images":["http://domestic.media.jiaotangapp.com/c1367df964de32e46b92c061666b2ad7_F50E3382-5675-4AB7-B87D-E0E63CF4042C.jpg?imageslim"],"creatorId":"4GE1YKCnAY","icon":"homestay","content":"","objectId":"s6EGQDoJBI","updatedAt":"2021-04-09T05:52:45.700Z"}]
         * status : true
         */

        private int total;
        private boolean status;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * createdAt : 2021-04-09T05:52:45.700Z
             * images : ["http://domestic.media.jiaotangapp.com/c1367df964de32e46b92c061666b2ad7_F50E3382-5675-4AB7-B87D-E0E63CF4042C.jpg?imageslim"]
             * creatorId : 4GE1YKCnAY
             * icon : homestay
             * content :
             * objectId : s6EGQDoJBI
             * updatedAt : 2021-04-09T05:52:45.700Z
             */

            private String createdAt;
            private String creatorId;
            private String icon;
            private String content;
            private String objectId;
            private String updatedAt;
            private List<String> images;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
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
