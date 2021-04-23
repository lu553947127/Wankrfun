package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: QuestionTemplateListBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/23/21 10:59 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/23/21 10:59 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class QuestionTemplateListBean {
    /**
     * code : 0
     * data : {"list":["最难忘的一次旅行","最喜欢的季节","偶像是","最爱吃的食物","最喜欢的一首歌","最拿手的料理","介绍一次经历","夸夸我的家乡","最讨厌的他人特质","想改变的现状","最在意的五官"]}
     * error :
     */

    private int code;
    private DataBean data;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class DataBean implements Serializable {
        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
