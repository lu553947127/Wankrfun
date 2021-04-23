package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: QuestionListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/23/21 10:23 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/23/21 10:23 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class QuestionListBean {
    /**
     * code : 0
     * data : {"total":4,"list":[{"createdAt":"2021-04-23T02:03:40.192Z","question":"最爱吃的食物","answer":"咖喱蛋包饭","creatorId":"bum9MVm6ZK","objectId":"rGU4LFxftU","updatedAt":"2021-04-23T02:03:40.192Z"},{"createdAt":"2021-04-23T02:05:08.699Z","question":"最在意的五官","answer":"眼睛","creatorId":"bum9MVm6ZK","objectId":"jYEX8qFc4A","updatedAt":"2021-04-23T02:05:08.699Z"},{"createdAt":"2021-04-23T02:05:23.240Z","question":"最喜欢的季节","answer":"夏天","creatorId":"bum9MVm6ZK","objectId":"YT5a6a8U1i","updatedAt":"2021-04-23T02:05:23.240Z"},{"createdAt":"2021-04-23T02:05:41.453Z","question":"想改变的现状","answer":"患得患失","creatorId":"bum9MVm6ZK","objectId":"WUpazO1nXs","updatedAt":"2021-04-23T02:05:41.453Z"}]}
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
         * total : 4
         * list : [{"createdAt":"2021-04-23T02:03:40.192Z","question":"最爱吃的食物","answer":"咖喱蛋包饭","creatorId":"bum9MVm6ZK","objectId":"rGU4LFxftU","updatedAt":"2021-04-23T02:03:40.192Z"},{"createdAt":"2021-04-23T02:05:08.699Z","question":"最在意的五官","answer":"眼睛","creatorId":"bum9MVm6ZK","objectId":"jYEX8qFc4A","updatedAt":"2021-04-23T02:05:08.699Z"},{"createdAt":"2021-04-23T02:05:23.240Z","question":"最喜欢的季节","answer":"夏天","creatorId":"bum9MVm6ZK","objectId":"YT5a6a8U1i","updatedAt":"2021-04-23T02:05:23.240Z"},{"createdAt":"2021-04-23T02:05:41.453Z","question":"想改变的现状","answer":"患得患失","creatorId":"bum9MVm6ZK","objectId":"WUpazO1nXs","updatedAt":"2021-04-23T02:05:41.453Z"}]
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
             * createdAt : 2021-04-23T02:03:40.192Z
             * question : 最爱吃的食物
             * answer : 咖喱蛋包饭
             * creatorId : bum9MVm6ZK
             * objectId : rGU4LFxftU
             * updatedAt : 2021-04-23T02:03:40.192Z
             */

            private String createdAt;
            private String question;
            private String answer;
            private String creatorId;
            private String objectId;
            private String updatedAt;

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
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
        }
    }
}
