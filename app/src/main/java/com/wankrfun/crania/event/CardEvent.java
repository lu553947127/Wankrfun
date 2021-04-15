package com.wankrfun.crania.event;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.event
 * @ClassName: CardEvent
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/14/21 3:39 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/14/21 3:39 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CardEvent {

    private String type;

    public CardEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
