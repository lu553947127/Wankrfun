package com.wankrfun.crania.http.error;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.error
 * @ClassName: ResponseErrorListener
 * @Description: 自定义异常类型
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:12 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:12 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ResponseErrorListener {
    void handlerResponseError(Throwable t);

    ResponseErrorListener EMPTY = t -> {

    };
}
