package com.wankrfun.crania.http.retrofit;

import com.wankrfun.crania.http.conver.AppConverterFactory;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.retrofit
 * @ClassName: BaseRequest
 * @Description: OkHttpClient网络请求初始化设置
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:13 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:13 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseRequest<T> {

    private static OkHttpClient client;

    static {
        //初始化OkHttp,绑定拦截器事件
        client = new OkHttpClient.Builder()
                .connectTimeout(6000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier((hostname, session) -> true)
                .dns(new ApiDns())
                .addInterceptor(new HttpLogInterceptorUtil())//绑定日志拦截器
                .addNetworkInterceptor(HttpInterceptorUtil.HeaderInterceptor())//绑定header拦截器
//                .addInterceptor(HttpInterceptorUtil.NullResponseInterceptor())//数据拦截器
                .build();

        //http 证书问题
        TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        try {
            SSLContext ssl = SSLContext.getInstance("SSL");
            ssl.init(null, trustManagers, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(ssl.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private T api;

    public BaseRequest(String host, Class clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(AppConverterFactory.create())//设置GSon转换器, 将返回的json数据转为实体
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//设置CallAdapter
                .baseUrl(host)
                .client(client)//设置客户端OkHttp相关参数
                .build();
        api = (T) retrofit.create(clazz);
    }

    public T getApiService() {
        return api;
    }
}
