package com.wankrfun.crania.bean;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: BaseBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 10:25 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 10:25 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseBean {

    private String name;
    private int images;
    private boolean checkState;

    public BaseBean(String name, int images) {
        this.name = name;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public boolean isCheckState() {
        return checkState;
    }

    public void setCheckState(boolean checkState) {
        this.checkState = checkState;
    }
}
