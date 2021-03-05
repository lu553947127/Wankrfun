package com.wankrfun.crania.bean;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: LoginBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/23/21 10:05 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/23/21 10:05 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginBean {
    /**
     * code : 0
     * data : {"msg":"验证码错误","status":false}
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

    public static class DataBean {
        /**
         * msg : 验证码错误
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
