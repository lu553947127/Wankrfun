package com.wankrfun.crania.base;

import java.io.Serializable;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.base
 * @ClassName: VersionUploadBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/24/21 3:09 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/24/21 3:09 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class VersionUploadBean {
    /**
     * code : 0
     * data : {"description":"","versionName":"","version":"","url":""}
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
        /**
         * description :
         * versionName :
         * version :
         * url :
         */

        private String description;
        private String versionName;
        private String version;
        private String url;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
