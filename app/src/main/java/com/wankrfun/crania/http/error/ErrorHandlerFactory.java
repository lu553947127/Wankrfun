package com.wankrfun.crania.http.error;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.error
 * @ClassName: ErrorHandlerFactory
 * @Description: 自定义异常类型
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:11 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:11 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ErrorHandlerFactory {
    private ResponseErrorListener mResponseErrorListener;

    public ErrorHandlerFactory(ResponseErrorListener mResponseErrorListener) {
        this.mResponseErrorListener = mResponseErrorListener;
    }

    /**
     *  处理错误
     * @param throwable
     */
    public void handleError(Throwable throwable) {
        mResponseErrorListener.handlerResponseError(throwable);
    }
}
