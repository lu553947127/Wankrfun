package com.wankrfun.crania.http.retrofit;

import com.blankj.utilcode.util.SPUtils;
import com.wankrfun.crania.base.SpConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.retrofit
 * @ClassName: HttpInterceptorUtil
 * @Description: OkHttpClient网络请求拦截器自定义
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:14 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:14 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HttpInterceptorUtil {
    public static final String TAG = "HttpInterceptorUtil";


    //绑定header拦截器
    public static Interceptor HeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //添加请求头
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder().addHeader("Authorization", SPUtils.getInstance().getString(SpConfig.TOKEN));
                Request request = requestBuilder.build();
//                LogUtils.e(SPUtils.getInstance().getString(SpConfig.TOKEN));
                return chain.proceed(request);
            }
        };
    }
}
