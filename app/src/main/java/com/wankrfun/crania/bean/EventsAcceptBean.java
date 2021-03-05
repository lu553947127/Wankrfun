package com.wankrfun.crania.bean;

import java.io.Serializable;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: EventsAcceptBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/7/21 11:48 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/7/21 11:48 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsAcceptBean {
    /**
     * code : 0
     * error :
     * data : {"status":true,"msg":"通过成功"}
     */

    private int code;
    private String error;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * status : true
         * msg : 通过成功
         */

        private boolean status;
        private String msg;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
