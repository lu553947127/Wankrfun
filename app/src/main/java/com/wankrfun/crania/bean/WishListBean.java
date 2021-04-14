package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: WishListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/9/21 1:54 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/9/21 1:54 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WishListBean {
    /**
     * code : 0
     * data : {"total":2,"list":[{"createdAt":"2021-03-15T23:04:37.334Z","color":"E39292","creatorId":"4GE1YKCnAY","content":"跑一次马拉松","objectId":"K6YEq7pFRo","status":"0","updatedAt":"2021-03-15T23:04:42.726Z"},{"createdAt":"2021-04-01T01:30:22.708Z","color":"E3B492","creatorId":"4GE1YKCnAY","content":"好","objectId":"V9PH5EFneB","status":"0","updatedAt":"2021-04-01T01:30:22.708Z"}]}
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
         * total : 2
         * list : [{"createdAt":"2021-03-15T23:04:37.334Z","color":"E39292","creatorId":"4GE1YKCnAY","content":"跑一次马拉松","objectId":"K6YEq7pFRo","status":"0","updatedAt":"2021-03-15T23:04:42.726Z"},{"createdAt":"2021-04-01T01:30:22.708Z","color":"E3B492","creatorId":"4GE1YKCnAY","content":"好","objectId":"V9PH5EFneB","status":"0","updatedAt":"2021-04-01T01:30:22.708Z"}]
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

            public ListBean(String color, String content) {
                this.color = color;
                this.content = content;
            }

            /**
             * createdAt : 2021-03-15T23:04:37.334Z
             * color : E39292
             * creatorId : 4GE1YKCnAY
             * content : 跑一次马拉松
             * objectId : K6YEq7pFRo
             * status : 0
             * updatedAt : 2021-03-15T23:04:42.726Z
             */

            private String createdAt;
            private String color;
            private String creatorId;
            private String content;
            private String objectId;
            private String status;
            private String updatedAt;

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }
    }
}
