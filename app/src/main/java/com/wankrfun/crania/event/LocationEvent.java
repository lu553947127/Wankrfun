package com.wankrfun.crania.event;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.event
 * @ClassName: LocationEvent
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/19/21 9:01 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/19/21 9:01 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LocationEvent {

    private double longitude;
    private double latitude;

    public LocationEvent(double longitude, double latitude, String city) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;
}
