package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: ChallengeListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/13/21 3:09 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/13/21 3:09 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChallengeListBean {
    /**
     * code : 0
     * data : {"records":[{"createdAt":"2021-02-24T18:30:28.213Z","question":"你更想获得哪种能力?","creatorId":"lIH50PMr6A","isActive":"on","choiceA":"不掉头发","objectId":"WLHNztuI1E","choiceB":"不会超重","chosen":"A","updatedAt":"2021-02-24T18:30:28.213Z"},{"createdAt":"2021-02-24T18:30:43.442Z","question":"你会更容易被人夸:","creatorId":"lIH50PMr6A","isActive":"on","choiceA":"好相处","objectId":"cxzDHcMBrB","choiceB":"善良","chosen":"B","updatedAt":"2021-02-24T18:30:43.442Z"},{"createdAt":"2021-02-24T18:30:14.630Z","question":"你宁愿接受:","creatorId":"lIH50PMr6A","isActive":"on","choiceA":"身无分文","objectId":"yZODoX43D8","choiceB":"没有朋友","chosen":"A","updatedAt":"2021-02-24T18:30:14.630Z"}],"userId":"lIH50PMr6A","isChallengeActive":"on"}
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
         * records : [{"createdAt":"2021-02-24T18:30:28.213Z","question":"你更想获得哪种能力?","creatorId":"lIH50PMr6A","isActive":"on","choiceA":"不掉头发","objectId":"WLHNztuI1E","choiceB":"不会超重","chosen":"A","updatedAt":"2021-02-24T18:30:28.213Z"},{"createdAt":"2021-02-24T18:30:43.442Z","question":"你会更容易被人夸:","creatorId":"lIH50PMr6A","isActive":"on","choiceA":"好相处","objectId":"cxzDHcMBrB","choiceB":"善良","chosen":"B","updatedAt":"2021-02-24T18:30:43.442Z"},{"createdAt":"2021-02-24T18:30:14.630Z","question":"你宁愿接受:","creatorId":"lIH50PMr6A","isActive":"on","choiceA":"身无分文","objectId":"yZODoX43D8","choiceB":"没有朋友","chosen":"A","updatedAt":"2021-02-24T18:30:14.630Z"}]
         * userId : lIH50PMr6A
         * isChallengeActive : on
         */

        private String userId;
        private String isChallengeActive;
        private List<RecordsBean> records;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getIsChallengeActive() {
            return isChallengeActive;
        }

        public void setIsChallengeActive(String isChallengeActive) {
            this.isChallengeActive = isChallengeActive;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean implements Serializable {

            /**
             * createdAt : 2021-02-24T18:30:28.213Z
             * question : 你更想获得哪种能力?
             * creatorId : lIH50PMr6A
             * isActive : on
             * choiceA : 不掉头发
             * objectId : WLHNztuI1E
             * choiceB : 不会超重
             * chosen : A
             * updatedAt : 2021-02-24T18:30:28.213Z
             */

            private String createdAt;
            private String question;
            private String creatorId;
            private String isActive;
            private String choiceA;
            private String objectId;
            private String choiceB;
            private String chosen;
            private String updatedAt;

            public RecordsBean(String question, String choiceA, String objectId, String choiceB, String chosen) {
                this.question = question;
                this.choiceA = choiceA;
                this.objectId = objectId;
                this.choiceB = choiceB;
                this.chosen = chosen;
            }

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

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
            }

            public String getIsActive() {
                return isActive;
            }

            public void setIsActive(String isActive) {
                this.isActive = isActive;
            }

            public String getChoiceA() {
                return choiceA;
            }

            public void setChoiceA(String choiceA) {
                this.choiceA = choiceA;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String getChoiceB() {
                return choiceB;
            }

            public void setChoiceB(String choiceB) {
                this.choiceB = choiceB;
            }

            public String getChosen() {
                return chosen;
            }

            public void setChosen(String chosen) {
                this.chosen = chosen;
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
