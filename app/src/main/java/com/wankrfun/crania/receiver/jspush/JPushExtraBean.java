package com.wankrfun.crania.receiver.jspush;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver.jspush
 * @ClassName: JPushExtraBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/8/21 9:21 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/8/21 9:21 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JPushExtraBean {
    /**
     * params : {"content":"我是内容","scene":"system","title":"我是题目"}
     */

    private ParamsBean params;

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * content : 我是内容
         * scene : system
         * title : 我是题目
         */

        private String content;
        private String scene;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getScene() {
            return scene;
        }

        public void setScene(String scene) {
            this.scene = scene;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
