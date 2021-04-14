package com.wankrfun.crania.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.MineEventsListBean;
import com.wankrfun.crania.bean.MineLifeListBean;
import com.wankrfun.crania.bean.WishListBean;
import com.wankrfun.crania.http.retrofit.BaseRepository;

import java.util.HashMap;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.viewmodel
 * @ClassName: MineCardViewModel
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/9/21 1:47 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/9/21 1:47 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineCardViewModel extends BaseRepository {
    public MutableLiveData<String> pageStateLiveData;
    public MutableLiveData<String> failStateLiveData;
    public MutableLiveData<WishListBean> wishListLiveData;
    public MutableLiveData<MineLifeListBean> lifeListLiveData;
    public MutableLiveData<MineEventsListBean> eventsListLiveData;
    private final String userId;

    public MineCardViewModel() {
        userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        wishListLiveData = new MutableLiveData<>();
        lifeListLiveData = new MutableLiveData<>();
        eventsListLiveData = new MutableLiveData<>();
    }

    /**
     * 获取心愿列表
     */
    public void getWishList(String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        ParseCloud.callFunctionInBackground("fetch-wishes-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getWishList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    WishListBean bean = new Gson().fromJson(new Gson().toJson(object), WishListBean.class);
                    if (bean.getCode() == 0){
                        wishListLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getWishList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取生活瞬间列表
     */
    public void getLifeList(String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        params.put("limit", 9);
        ParseCloud.callFunctionInBackground("fetch-life-moments-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getLifeList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    MineLifeListBean bean = new Gson().fromJson(new Gson().toJson(object), MineLifeListBean.class);
                    if (bean.getCode() == 0){
                        lifeListLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getLifeList: " + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取活动瞬间列表
     */
    public void getEventsList(String userId){
        HashMap<String, Object> params = new HashMap();
        params.put("creatorId", userId);
        params.put("limit", 9);
        ParseCloud.callFunctionInBackground("fetch-event-moments-v001", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    LogUtils.e("getEventsList: "+ new Gson().toJson(object));
                    LogUtils.json(LogUtils.I,new Gson().toJson(object));
                    MineEventsListBean bean = new Gson().fromJson(new Gson().toJson(object), MineEventsListBean.class);
                    if (bean.getCode() == 0){
                        eventsListLiveData.postValue(bean);
                    }else {
                        ToastUtils.showShort(bean.getError());
                    }
                }else {
                    LogUtils.e("getEventsList: " + e.getMessage());
                }
            }
        });
    }
}
