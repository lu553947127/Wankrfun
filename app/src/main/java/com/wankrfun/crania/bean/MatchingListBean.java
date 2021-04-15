package com.wankrfun.crania.bean;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: MatchingListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/3/21 1:30 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/3/21 1:30 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MatchingListBean {

    /**
     * sex : female
     * name : vacation
     * photo : http://domestic.media.jiaotangapp.com/48a2957e6e98ff26eeacff3c5c599c6f_image.png?imageslim
     * objectId : crX7Py5q8J
     */

    private String sex;
    private String name;
    private String photo;
    private String objectId;

    public MatchingListBean(String photo, String objectId) {
        this.photo = photo;
        this.objectId = objectId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
