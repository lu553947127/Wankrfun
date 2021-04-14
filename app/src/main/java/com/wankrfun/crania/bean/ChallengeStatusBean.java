package com.wankrfun.crania.bean;

import java.io.Serializable;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: ChallengeStatusBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/14/21 11:50 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/14/21 11:50 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChallengeStatusBean {
    /**
     * code : 0
     * data : {"msg":"","status":true}
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
         * msg :
         * status : true
         */

        private String msg;
        private boolean status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
