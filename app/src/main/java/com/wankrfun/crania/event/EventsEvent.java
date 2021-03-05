package com.wankrfun.crania.event;

import com.wankrfun.crania.bean.UserEventsListBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.event
 * @ClassName: EventsEvent
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/3/21 9:47 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/3/21 9:47 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsEvent {

    private String type;
    private String title;
    private String id;
    private double longitude;
    private double latitude;
    private int num;
    private String sex;

    private List<String> list;
    private Map<String, String> mapList;
    private UserEventsListBean userEventsListBean;

    public EventsEvent() {
    }

    public EventsEvent(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public EventsEvent(List<String> list, Map<String, String> mapList) {
        this.list = list;
        this.mapList = mapList;
    }

    public EventsEvent(List<String> list, Map<String, String> mapList, UserEventsListBean userEventsListBean) {
        this.list = list;
        this.mapList = mapList;
        this.userEventsListBean = userEventsListBean;
    }

    public EventsEvent(String type, String title, String id) {
        this.type = type;
        this.title = title;
        this.id = id;
    }

    public EventsEvent(String type, String title, double longitude, double latitude) {
        this.type = type;
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public EventsEvent(String type, String title, int num, String sex) {
        this.type = type;
        this.title = title;
        this.num = num;
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMapList() {
        return mapList;
    }

    public void setMapList(Map<String, String> mapList) {
        this.mapList = mapList;
    }

    public UserEventsListBean getUserEventsListBean() {
        return userEventsListBean;
    }

    public void setUserEventsListBean(UserEventsListBean userEventsListBean) {
        this.userEventsListBean = userEventsListBean;
    }
}
