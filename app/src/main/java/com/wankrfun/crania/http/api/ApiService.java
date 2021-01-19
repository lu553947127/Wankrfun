package com.wankrfun.crania.http.api;

import com.wankrfun.crania.http.entity.BaseResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.api
 * @ClassName: ApiService
 * @Description: 服务器接口集合
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:04 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:04 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ApiService {
    //获取验证码
    @GET("/login/getcode")
    Flowable<BaseResponse> getcode(
            @Query("phone") String phone
    );
}
