package com.wankrfun.crania.utils;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: RefreshUtils
 * @Description: 上拉加载刷新数据工具
 * @Author: 鹿鸿祥
 * @CreateDate: 1/14/21 10:24 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/14/21 10:24 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RefreshUtils {

    /**
     * 上拉加载刷新数据 分页工具
     *
     * @param refresh
     * @param page
     * @param count
     */
    public static void setNoMore(SmartRefreshLayout refresh, int page, int count){
        if (page == 1) {
            if (page * 10 >= count) {
                if (refresh.getState() == RefreshState.None) {
                    refresh.setNoMoreData(true);
                } else {
                    refresh.finishRefreshWithNoMoreData();
                }
            } else {
                refresh.finishRefresh();
            }
        } else {
            if (page * 10 >= count) {
                refresh.finishLoadMoreWithNoMoreData();
            } else {
                refresh.finishLoadMore();
            }
        }
    }

    //期待列表
    public static List getExpectList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean("想恋爱", R.drawable.icon_expect_love));
        list.add(new BaseBean("找小伙伴", R.drawable.icon_expect_partner));
        list.add(new BaseBean("打发时间", R.drawable.icon_expect_time));
        list.add(new BaseBean("组局的人", R.drawable.icon_expect_set));
        list.add(new BaseBean("还没想好", R.drawable.icon_expect_not_did));
        return list;
    }

    //活动类型列表
    public static List getActivityList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean("美食", R.drawable.icon_activity_food));
        list.add(new BaseBean("运动", R.drawable.icon_activity_motion));
        list.add(new BaseBean("游戏", R.drawable.icon_activity_game));
        list.add(new BaseBean("下午茶", R.drawable.icon_activity_tea));
        list.add(new BaseBean("k歌", R.drawable.icon_activity_karaoke));
        list.add(new BaseBean("桌游", R.drawable.icon_activity_board_play));
        list.add(new BaseBean("电影", R.drawable.icon_activity_film));
        list.add(new BaseBean("夜生活", R.drawable.icon_activity_night_life));
        list.add(new BaseBean("其他", R.drawable.icon_activity_not));
        return list;
    }
}
