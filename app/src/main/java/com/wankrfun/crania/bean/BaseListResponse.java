package com.wankrfun.crania.bean;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: BaseListResponse
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/23/21 9:56 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/23/21 9:56 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseListResponse<T> {

    private int code;
    private String error;
    private List<T> data;

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
