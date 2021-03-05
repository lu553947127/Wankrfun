package com.wankrfun.crania.bean;

import java.io.Serializable;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: EventsStateBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/7/21 8:51 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/7/21 8:51 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsStateBean {
    /**
     * code : 0
     * data : {"msg":"查询成功","hint":"","status":"open"}
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
         * msg : 查询成功
         * hint :
         * status : open
         */

        private String msg;
        private String hint;
        private String status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
