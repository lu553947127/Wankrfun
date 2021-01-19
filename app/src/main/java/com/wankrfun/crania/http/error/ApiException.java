package com.wankrfun.crania.http.error;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.error
 * @ClassName: ApiException
 * @Description: 自定义异常类型
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:11 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:11 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ApiException extends RuntimeException {

    private int errorCode;
    private String msg;

    public ApiException(int errorCode, String msg) {
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }
}
