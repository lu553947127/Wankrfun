package com.wankrfun.crania.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.parse.ParseUser;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.event.LocationEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: LocationUtils
 * @Description: 高德地图定位工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 1/15/21 3:14 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/15/21 3:14 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("StaticFieldLeak")
public class LocationUtils {

    private static AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption = null;
    private final List<LocationEvent> localLocationList = new ArrayList<>();

    private static class LocationHolder {
        private static final LocationUtils INSTANCE = new LocationUtils();
    }

    public static LocationUtils getInstance() {
        return LocationHolder.INSTANCE;
    }


    /**
     * 获取一个本地随机数定位地址
     */
    public void getLocalLocation() throws AMapException {
        localLocationList.add(new LocationEvent(121.433727, 31.197776, "上海"));
        localLocationList.add(new LocationEvent(121.446182, 31.19639, "上海"));
        localLocationList.add(new LocationEvent(121.458897, 31.219871, "上海"));
        localLocationList.add(new LocationEvent(121.474269, 31.230181, "上海"));
        localLocationList.add(new LocationEvent(121.499836, 31.239447, "上海"));
        localLocationList.add(new LocationEvent(121.552193, 31.208895, "上海"));
        localLocationList.add(new LocationEvent(121.666307, 31.141802, "上海"));
        localLocationList.add(new LocationEvent(121.492974, 31.183082, "上海"));
        localLocationList.add(new LocationEvent(121.568407, 31.210071, "上海"));
        localLocationList.add(new LocationEvent(121.582166, 31.219901, "上海"));

        Random random = new Random();
        int pos = random.nextInt(localLocationList.size());
        SPUtils.getInstance().put(SpConfig.LONGITUDE, String.valueOf(localLocationList.get(pos).getLongitude()), true);
        SPUtils.getInstance().put(SpConfig.LATITUDE, String.valueOf(localLocationList.get(pos).getLatitude()), true);
        getAddressChange(localLocationList.get(pos).getLatitude(), localLocationList.get(pos).getLongitude());
    }

    /**
     * 开启定位服务
     */
    public void startLocalService() throws Exception {
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApplication.getInstance());
        //设置定位回调监听
        mLocationOption = getDefaultOption();
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.setLocationListener(location -> {
            if (null != location) {
                if (location.getErrorCode() == 0) {
                    SPUtils.getInstance().put(SpConfig.LONGITUDE, String.valueOf(location.getLongitude()), true);
                    SPUtils.getInstance().put(SpConfig.LATITUDE, String.valueOf(location.getLatitude()), true);
                    LogUtils.e( "longitude："+location.getLongitude()+"latitude："+location.getLatitude());
                    try {
                        getAddressChange(location.getLatitude(), location.getLongitude());
                    } catch (AMapException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                ToastUtils.showShort("定位失败，loc is null");
            }
        });
        mLocationClient.startLocation();
    }

    /**
     * 经纬度反地理编码转换地址
     *
     * @param latitude 纬度
     * @param longitude 经度
     */
    private void getAddressChange(final double latitude, double longitude) throws AMapException {
        GeocodeSearch geocoderSearch = new GeocodeSearch(MyApplication.getInstance());
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {

            @Override
            public void onGeocodeSearched(GeocodeResult result, int rCode) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
                if (rCode == 1000) {
                    if (TextUtils.isEmpty(SPUtils.getInstance().getString(SpConfig.CITY))){
                        ParseUser.getCurrentUser().put("address", result.getRegeocodeAddress().getCity());
                        ParseUser.getCurrentUser().saveInBackground();
                    }
                    SPUtils.getInstance().put(SpConfig.CITY, result.getRegeocodeAddress().getCity(), true);
                    SPUtils.getInstance().put(SpConfig.PROVINCE, result.getRegeocodeAddress().getProvince(), true);
                    EventBus.getDefault().post(new LocationEvent(longitude, latitude, result.getRegeocodeAddress().getCity()));
                } else {
                    LogUtils.e("地理编码", "地址名出错");
                }
            }
        });
        LatLonPoint lp = new LatLonPoint(latitude, longitude);
        RegeocodeQuery query = new RegeocodeQuery(lp, 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
    }

    /**
     * 停止定位
     */
    public void stopLocalService() {
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
            mLocationClient.stopLocation();
            mLocationClient = null;
            mLocationOption = null;
        }
    }

    /**
     * 地图定位属性设置
     *
     * @return
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false(是否进行持续定位)
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }
}
