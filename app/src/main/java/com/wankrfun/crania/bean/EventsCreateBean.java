package com.wankrfun.crania.bean;

import java.io.Serializable;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: EventsCreateBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/8/21 1:37 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/8/21 1:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsCreateBean {
    /**
     * code : 404
     * data : {"msg":"Param activity_time is required","status":false}
     * error : Network has an error
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
         * msg : Param activity_time is required
         * status : false
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
