package com.wankrfun.crania.event;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.event
 * @ClassName: BaseEvent
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/11/21 4:02 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/11/21 4:02 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseEvent {
    /**
     * code : 402
     * data : {"msg":"验证码错误","status":false}
     * error : OTP_INCORRECT
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
