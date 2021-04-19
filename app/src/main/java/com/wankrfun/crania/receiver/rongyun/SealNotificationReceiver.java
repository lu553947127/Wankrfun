package com.wankrfun.crania.receiver.rongyun;

import android.content.Context;

import com.huawei.hms.support.api.entity.core.CommonCode;

import io.rong.push.PushType;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver
 * @ClassName: SealNotificationReceiver
 * @Description: 融云通知接受自定义服务
 * @Author: 鹿鸿祥
 * @CreateDate: 2/5/21 3:05 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/5/21 3:05 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SealNotificationReceiver extends PushMessageReceiver {
    public static boolean needUpdate = false;

    @Override
    public boolean onNotificationMessageArrived(Context context, PushType pushType, PushNotificationMessage message) {
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushType pushType, PushNotificationMessage message) {
        return false;
    }

    //华为获取 token 异常回调此方法
    @Override
    public void onThirdPartyPushState(PushType pushType, String action, long resultCode) {
        super.onThirdPartyPushState(pushType, action, resultCode);
        if (pushType.equals(PushType.HUAWEI)) {
            if (resultCode == CommonCode.ErrorCode.CLIENT_API_INVALID) {
                //设置标记位，引导用户升级
                needUpdate = true;
            }
        }
    }
}
