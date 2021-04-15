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
        if (page == 0) {
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
    public static List<BaseBean> getExpectList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean("想恋爱", R.drawable.icon_expect_love));
        list.add(new BaseBean("找小伙伴", R.drawable.icon_expect_partner));
        list.add(new BaseBean("打发时间", R.drawable.icon_expect_time));
        list.add(new BaseBean("组局的人", R.drawable.icon_expect_set));
        list.add(new BaseBean("还没想好", R.drawable.icon_expect_not_did));
        return list;
    }

    /**
     * 设置我的生活瞬间类型icon
     *
     * @param tag
     * @return
     */
    public static int setMineLifeIcon(String tag){
        int icon = 0;
        switch (tag){
            case "mood":
                icon = R.drawable.icon_mine_life;
                break;
            case "homestay":
                icon = R.drawable.icon_mine_life2;
                break;
            case "play":
                icon = R.drawable.icon_mine_life3;
                break;
            case "outfit":
                icon = R.drawable.icon_mine_life4;
                break;
            case "pet":
                icon = R.drawable.icon_mine_life5;
                break;
            case "food":
                icon = R.drawable.icon_mine_life6;
                break;
            case "scene":
                icon = R.drawable.icon_mine_life7;
                break;
            case "other":
                icon = R.drawable.icon_mine_life8;
                break;
        }
        return icon;
    }

    /**
     * 设置我的生活瞬间
     *
     * @param tag
     * @return
     */
    public static String setMineLifeType(String tag){
        String type = "";
        switch (tag){
            case "mood":
                type = "心愿";
                break;
            case "homestay":
                type = "宅家";
                break;
            case "play":
                type = "玩耍";
                break;
            case "outfit":
                type = "穿搭";
                break;
            case "pet":
                type = "宠物";
                break;
            case "food":
                type = "美食";
                break;
            case "scene":
                type = "风景";
                break;
            case "other":
                type = "其他";
                break;
        }
        return type;
    }

    /**
     * 设置我的生活瞬间
     *
     * @param tag
     * @return
     */
    public static String setMineLifeType2(String tag){
        String type = "";
        switch (tag){
            case "心愿":
                type = "mood";
                break;
            case "宅家":
                type = "homestay";
                break;
            case "玩耍":
                type = "play";
                break;
            case "穿搭":
                type = "outfit";
                break;
            case "宠物":
                type = "pet";
                break;
            case "美食":
                type = "food";
                break;
            case "风景":
                type = "scene";
                break;
            case "其他":
                type = "other";
                break;
        }
        return type;
    }

    /**
     * 期待列表显示转换
     *
     * @param tag
     * @return
     */
    public static int setTagIcon(String tag){
        int icon = 0;
        switch (tag){
            case "想恋爱":
            case "#想恋爱":
                icon = R.drawable.icon_expect_love;
                break;
            case "找小伙伴":
            case "#找小伙伴":
                icon = R.drawable.icon_expect_partner;
                break;
            case "打发时间":
            case "#打发时间":
                icon = R.drawable.icon_expect_time;
                break;
            case "组局的人":
            case "#组局的人":
            case "组局约人":
            case "#组局约人":
                icon = R.drawable.icon_expect_set;
                break;
            case "还没想好":
            case "#还没想好":
                icon = R.drawable.icon_expect_not_did;
                break;
        }
        return icon;
    }

    /**
     * 活动类型列表显示转换
     *
     * @param tag
     * @return
     */
    public static int setEventTagIcon(String tag){
        int icon = 0;
        switch (tag){
            case "美食":
            case "#吃饭叫我":
                icon = R.drawable.icon_activity_food;
                break;
            case "运动":
            case "#蹦迪冲鸭":
            case "#运动缺个伴":
                icon = R.drawable.icon_activity_motion;
                break;
            case "游戏":
            case "#玩什么都嗨皮":
            case "#一起来开黑":
                icon = R.drawable.icon_activity_game;
                break;
            case "下午茶":
                icon = R.drawable.icon_activity_tea;
                break;
            case "k歌":
            case "#k歌一起嗨":
                icon = R.drawable.icon_activity_karaoke;
                break;
            case "桌游":
            case "#桌游拉上我":
                icon = R.drawable.icon_activity_board_play;
                break;
            case "电影":
            case "#看电影约我":
                icon = R.drawable.icon_activity_film;
                break;
            case "夜生活":
                icon = R.drawable.icon_activity_night_life;
                break;
            case "其他":
                icon = R.drawable.icon_activity_not;
                break;
        }
        return icon;
    }

    /**
     * 获取随机数颜色
     *
     * @return
     */
    public static String getRandomColor(){
        String color = "";
        List<String> colorList = new ArrayList<>();
        colorList.add("E3B492");
        colorList.add("92C1E3");
        colorList.add("92E3B2");
        colorList.add("E392AF");
        colorList.add("E3DB92");
        colorList.add("2AA7ED");
        colorList.add("BB92E3");
        colorList.add("E39292");
        colorList.add("929FE3");
        colorList.add("E392E0");
        colorList.add("E3C992");
        Random random = new Random();
        int pos = random.nextInt(colorList.size());
        color = colorList.get(pos);
        return color;
    }

    //活动类型列表
    public static List<BaseBean> getActivityList(){
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

    //活动类型列表
    public static List<BaseBean> getActivityListNot(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean("美食", R.drawable.icon_activity_food, R.drawable.icon_activity_food_un));
        list.add(new BaseBean("运动", R.drawable.icon_activity_motion, R.drawable.icon_activity_motion_un));
        list.add(new BaseBean("游戏", R.drawable.icon_activity_game, R.drawable.icon_activity_game_un));
        list.add(new BaseBean("下午茶", R.drawable.icon_activity_tea, R.drawable.icon_activity_tea_un));
        list.add(new BaseBean("k歌", R.drawable.icon_activity_karaoke, R.drawable.icon_activity_karaoke_un));
        list.add(new BaseBean("桌游", R.drawable.icon_activity_board_play, R.drawable.icon_activity_board_play_un));
        list.add(new BaseBean("电影", R.drawable.icon_activity_film, R.drawable.icon_activity_film_un));
        list.add(new BaseBean("夜生活", R.drawable.icon_activity_night_life, R.drawable.icon_activity_night_life_un));
        list.add(new BaseBean("其他", R.drawable.icon_activity_not, R.drawable.icon_activity_not_un));
        return list;
    }



    //个人中心标签列表
    public static List<BaseBean> getActivityLists(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean("想恋爱", R.drawable.icon_activity_food));
        list.add(new BaseBean("吃饭叫我吃饭叫我吃饭叫我", R.drawable.icon_activity_motion));
        list.add(new BaseBean("北京", R.drawable.icon_activity_game));
        list.add(new BaseBean("清华", R.drawable.icon_activity_tea));
        list.add(new BaseBean("喜欢猫", R.drawable.icon_activity_karaoke));
        list.add(new BaseBean("沉迷锻炼", R.drawable.icon_activity_board_play));
        list.add(new BaseBean("古典", R.drawable.icon_activity_film));
        list.add(new BaseBean("夜生活", R.drawable.icon_activity_night_life));
        list.add(new BaseBean("其他", R.drawable.icon_activity_not));
        return list;
    }

    //个人中心背景列表
    public static List<BaseBean> getBgList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean("一", R.drawable.icon_bg));
        list.add(new BaseBean("二", R.drawable.icon_bg1));
        list.add(new BaseBean("三", R.drawable.icon_bg2));
        list.add(new BaseBean("四", R.drawable.icon_bg3));
        list.add(new BaseBean("五", R.drawable.icon_bg4));
        list.add(new BaseBean("六", R.drawable.icon_bg5));
        list.add(new BaseBean("七", R.drawable.icon_bg6));
        list.add(new BaseBean("八", R.drawable.icon_bg7));
        list.add(new BaseBean("九", R.drawable.icon_bg8));
        list.add(new BaseBean("十", R.drawable.icon_bg9));
        list.add(new BaseBean("十一", R.drawable.icon_bg10));
        list.add(new BaseBean("十二", R.drawable.icon_bg11));
        list.add(new BaseBean("十三", R.drawable.icon_bg12));
        list.add(new BaseBean("十四", R.drawable.icon_bg13));
        list.add(new BaseBean("十五", R.drawable.icon_bg14));
        list.add(new BaseBean("十六", R.drawable.icon_bg15));
        return list;
    }

    //活动类型列表
    public static List<BaseBean> getMeetList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean("美食", R.drawable.aaa));
        list.add(new BaseBean("运动", R.drawable.bbb));
        list.add(new BaseBean("下午茶", R.drawable.bbb2));
        list.add(new BaseBean("k歌", R.drawable.bbb3));
        list.add(new BaseBean("桌游", R.drawable.bbb4));
        list.add(new BaseBean("电影", R.drawable.bbb5));
        return list;
    }

    //活动类型列表
    public static List<BaseBean> getActivityListAdd(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean("饭局", R.drawable.icon_activity_food, R.drawable.icon_activity_food2));
        list.add(new BaseBean("运动", R.drawable.icon_activity_motion, R.drawable.icon_activity_motion2));
        list.add(new BaseBean("游戏", R.drawable.icon_activity_game, R.drawable.icon_activity_game2));
        list.add(new BaseBean("下午茶", R.drawable.icon_activity_tea, R.drawable.icon_activity_tea2));
        list.add(new BaseBean("k歌", R.drawable.icon_activity_karaoke, R.drawable.icon_activity_karaoke2));
        list.add(new BaseBean("桌游", R.drawable.icon_activity_board_play, R.drawable.icon_activity_board_play2));
        list.add(new BaseBean("电影", R.drawable.icon_activity_film, R.drawable.icon_activity_film2));
        list.add(new BaseBean("夜生活", R.drawable.icon_activity_night_life, R.drawable.icon_activity_night_life2));
        list.add(new BaseBean("其他", R.drawable.icon_activity_not, R.drawable.icon_activity_not2));
        return list;
    }

    //美食
    public static List<BaseBean> getEventsFoodList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean(R.drawable.icon_activity_food));
        list.add(new BaseBean(R.drawable.icon_activity_food11));
        list.add(new BaseBean(R.drawable.icon_activity_food12));
        list.add(new BaseBean(R.drawable.icon_activity_food13));
        list.add(new BaseBean(R.drawable.icon_activity_food14));
        list.add(new BaseBean(R.drawable.icon_activity_food15));
        list.add(new BaseBean(R.drawable.icon_activity_food16));
        return list;
    }

    //运动
    public static List<BaseBean> getEventsMotionList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean(R.drawable.icon_activity_motion));
        list.add(new BaseBean(R.drawable.icon_activity_motion11));
        list.add(new BaseBean(R.drawable.icon_activity_motion12));
        list.add(new BaseBean(R.drawable.icon_activity_motion13));
        list.add(new BaseBean(R.drawable.icon_activity_motion14));
        list.add(new BaseBean(R.drawable.icon_activity_motion15));
        list.add(new BaseBean(R.drawable.icon_activity_motion16));
        list.add(new BaseBean(R.drawable.icon_activity_motion17));
        list.add(new BaseBean(R.drawable.icon_activity_motion18));
        list.add(new BaseBean(R.drawable.icon_activity_motion19));
        list.add(new BaseBean(R.drawable.icon_activity_motion20));
        list.add(new BaseBean(R.drawable.icon_activity_motion21));
        list.add(new BaseBean(R.drawable.icon_activity_motion22));
        return list;
    }

    //游戏
    public static List<BaseBean> getEventsGameList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean(R.drawable.icon_activity_game));
        list.add(new BaseBean(R.drawable.icon_activity_game11));
        list.add(new BaseBean(R.drawable.icon_activity_game12));
        list.add(new BaseBean(R.drawable.icon_activity_game13));
        return list;
    }

    //下午茶
    public static List<BaseBean> getEventsTeaList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean(R.drawable.icon_activity_tea));
        list.add(new BaseBean(R.drawable.icon_activity_tea11));
        list.add(new BaseBean(R.drawable.icon_activity_tea12));
        list.add(new BaseBean(R.drawable.icon_activity_tea13));
        list.add(new BaseBean(R.drawable.icon_activity_tea14));
        list.add(new BaseBean(R.drawable.icon_activity_tea15));
        return list;
    }

    //k歌
    public static List<BaseBean> getEventsKaraokeList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean(R.drawable.icon_activity_karaoke));
        return list;
    }

    //桌游
    public static List<BaseBean> getEventsBoardPlaList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean(R.drawable.icon_activity_board_play));
        list.add(new BaseBean(R.drawable.icon_activity_board_play11));
        list.add(new BaseBean(R.drawable.icon_activity_board_play12));
        list.add(new BaseBean(R.drawable.icon_activity_board_play13));
        list.add(new BaseBean(R.drawable.icon_activity_board_play14));
        return list;
    }

    //电影
    public static List<BaseBean> getEventsFilmList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean(R.drawable.icon_activity_film));
        return list;
    }

    //夜生活
    public static List<BaseBean> getEventsNightLifeList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean(R.drawable.icon_activity_night_life));
        list.add(new BaseBean(R.drawable.icon_activity_night_life11));
        list.add(new BaseBean(R.drawable.icon_activity_night_life12));
        list.add(new BaseBean(R.drawable.icon_activity_night_life13));
        list.add(new BaseBean(R.drawable.icon_activity_night_life14));
        list.add(new BaseBean(R.drawable.icon_activity_night_life15));
        return list;
    }

    //其他
    public static List<BaseBean> getEventsNotList(){
        List<BaseBean> list =new ArrayList<>();
        list.add(new BaseBean(R.drawable.icon_activity_not));
        list.add(new BaseBean(R.drawable.icon_activity_not11));
        list.add(new BaseBean(R.drawable.icon_activity_not12));
        list.add(new BaseBean(R.drawable.icon_activity_not13));
        list.add(new BaseBean(R.drawable.icon_activity_not14));
        list.add(new BaseBean(R.drawable.icon_activity_not15));
        list.add(new BaseBean(R.drawable.icon_activity_not16));
        list.add(new BaseBean(R.drawable.icon_activity_not17));
        list.add(new BaseBean(R.drawable.icon_activity_not18));
        list.add(new BaseBean(R.drawable.icon_activity_not19));
        return list;
    }
}
