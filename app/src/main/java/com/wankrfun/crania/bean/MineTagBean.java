package com.wankrfun.crania.bean;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: MineTagBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/3/21 3:43 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/3/21 3:43 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineTagBean {

    private int icon;
    private String title;

    public MineTagBean(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
