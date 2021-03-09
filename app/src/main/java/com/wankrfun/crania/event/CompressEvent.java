package com.wankrfun.crania.event;

import java.io.File;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.event
 * @ClassName: CompressEvent
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 3/8/21 3:53 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 3/8/21 3:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CompressEvent {

    private File compressFile;

    public CompressEvent(File compressFile) {
        this.compressFile = compressFile;
    }

    public File getCompressFile() {
        return compressFile;
    }

    public void setCompressFile(File compressFile) {
        this.compressFile = compressFile;
    }
}
