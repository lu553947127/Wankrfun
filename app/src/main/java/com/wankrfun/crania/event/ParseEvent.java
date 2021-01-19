package com.wankrfun.crania.event;

import com.parse.ParseObject;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.event
 * @ClassName: ParseEvent
 * @Description: Parse传输对象
 * @Author: 鹿鸿祥
 * @CreateDate: 1/11/21 10:06 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/11/21 10:06 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ParseEvent {

    private Object object;
    private ParseObject parseObject;
    private String message;

    public ParseEvent(Object object) {
        this.object = object;
    }

    public ParseEvent(ParseObject parseObject) {
        this.parseObject = parseObject;
    }

    public ParseEvent(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ParseObject getParseObject() {
        return parseObject;
    }

    public void setParseObject(ParseObject parseObject) {
        this.parseObject = parseObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
