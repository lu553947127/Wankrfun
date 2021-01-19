package com.wankrfun.crania.http.entity;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.entity
 * @ClassName: BaseResponse
 * @Description: data为object
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:10 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:10 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseResponse<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
