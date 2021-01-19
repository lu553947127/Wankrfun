package com.wankrfun.crania.http.entity;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.entity
 * @ClassName: BaseListResponse
 * @Description: data为数组集合
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:11 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:11 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseListResponse<T> {

    private int code;
    private String msg;
    private List<T> data;

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
