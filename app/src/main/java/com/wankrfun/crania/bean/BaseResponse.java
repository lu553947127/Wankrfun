package com.wankrfun.crania.bean;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.event
 * @ClassName: BaseResponse
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/11/21 4:02 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/11/21 4:02 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseResponse<T>  {

    private int code;
    private T data;
    private String error;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
