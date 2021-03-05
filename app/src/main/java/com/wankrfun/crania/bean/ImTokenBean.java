package com.wankrfun.crania.bean;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: ImTokenBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/23/21 10:12 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/23/21 10:12 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ImTokenBean {
    /**
     * code : 0
     * data : {"token":"JxbxdTI4XPraFL1yGa1Ek+3/tg/XMML1Uts715pHCUY=@aqjc.cn.rongnav.com;aqjc.cn.rongcfg.com"}
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

    public static class DataBean {
        /**
         * token : JxbxdTI4XPraFL1yGa1Ek+3/tg/XMML1Uts715pHCUY=@aqjc.cn.rongnav.com;aqjc.cn.rongcfg.com
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
