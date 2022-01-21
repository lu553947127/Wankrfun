package com.wankrfun.crania.receiver.rongyun;

//import java.util.List;
//import java.util.ListIterator;
//
//import io.rong.imkit.DefaultExtensionModule;
//import io.rong.imkit.plugin.IPluginModule;
//import io.rong.imkit.widget.provider.FilePlugin;
import java.util.List;
import java.util.ListIterator;

import io.rong.imkit.conversation.extension.component.plugin.FilePlugin;
import io.rong.imkit.conversation.extension.component.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.receiver.rongyun
 * @ClassName: CustomExtensionModule
 * @Description: 加号区域内置扩展项
 * @Author: 鹿鸿祥
 * @CreateDate: 4/16/21 9:28 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/16/21 9:28 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
//public class CustomExtensionModule extends DefaultExtensionModule {
//    @Override
//    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
//        List<IPluginModule> pluginModules = super.getPluginModules(conversationType);
//        ListIterator<IPluginModule> iterator = pluginModules.listIterator();
//
//        // 删除扩展项
//        while (iterator.hasNext()) {
//            IPluginModule integer = iterator.next();
//            // 以删除 FilePlugin 为例
//            if (integer instanceof FilePlugin) {
//                iterator.remove();
//            }
//        }
//
//        // 增加扩展项, 以 ImagePlugin 为例
////        pluginModules.add(new DefaultLocationPlugin());
//        pluginModules.add(new CustomPlugin());
//        return pluginModules;
//    }
//}
