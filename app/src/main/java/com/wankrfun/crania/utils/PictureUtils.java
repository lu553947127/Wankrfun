package com.wankrfun.crania.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: PictureUtils
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/2/21 10:25 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/2/21 10:25 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PictureUtils {

    /**
     * 九宫格添加图片显示（列表带加号的）
     *
     * @param picture_list
     * @return
     */
    public static List<String> getImageBitmapAdd(List<String> picture_list) {
        List<String> list = new ArrayList<>();
        list.clear();
        for (int i = 0; i< picture_list.size(); i++){
            list.add(picture_list.get(i));
        }
        return list;
    }
}
