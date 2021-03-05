package com.wankrfun.crania.utils;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.flexbox.FlexboxLayout;
import com.wankrfun.crania.R;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.bean.MineTagBean;
import com.wankrfun.crania.bean.UserInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.utils
 * @ClassName: EventsUtils
 * @Description: 活动类别icon显示
 * @Author: 鹿鸿祥
 * @CreateDate: 2/4/21 2:54 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/4/21 2:54 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsUtils {

    /**
     * 设置活动泪奔icon显示
     *
     * @param iv_type
     * @param iv_type2
     * @param eventType
     * @param eventTypeIcon
     */
    public static void getEventsIcon(AppCompatImageView iv_type, AppCompatImageView iv_type2,
                                     String eventType, String eventTypeIcon){
        switch (eventType){
            case "0":
                getEventsIconFood(eventTypeIcon, iv_type, iv_type2);
                break;
            case "1":
                getEventsIconMotion(eventTypeIcon, iv_type, iv_type2);
                break;
            case "2":
                getEventsIconGame(eventTypeIcon, iv_type, iv_type2);
                break;
            case "3":
                getEventsIconTea(eventTypeIcon, iv_type, iv_type2);
                break;
            case "4":
                iv_type.setImageResource(R.drawable.icon_activity_karaoke);
                iv_type2.setImageResource(R.drawable.icon_activity_karaoke);
                break;
            case "5":
                getEventsIconBoardPlay(eventTypeIcon, iv_type, iv_type2);
                break;
            case "6":
                iv_type.setImageResource(R.drawable.icon_activity_film);
                iv_type2.setImageResource(R.drawable.icon_activity_film);
                break;
            case "7":
                getEventsIconNightLife(eventTypeIcon, iv_type, iv_type2);
                break;
            case "11":
                getEventsIconNot(eventTypeIcon, iv_type, iv_type2);
                break;
        }
    }

    /**
     * 设置活动泪奔icon显示
     *
     * @param tv_type
     * @param tv_type2
     * @param iv_type
     * @param iv_type2
     * @param eventType
     * @param eventTypeIcon
     */
    public static void getEventsIcon(AppCompatTextView tv_type, AppCompatTextView tv_type2,
                                     AppCompatImageView iv_type, AppCompatImageView iv_type2,
                                     String eventType, String eventTypeIcon){
        switch (eventType){
            case "0":
                tv_type.setText("美食");
                tv_type2.setText("美食");
                getEventsIconFood(eventTypeIcon, iv_type, iv_type2);
                break;
            case "1":
                tv_type.setText("运动");
                tv_type2.setText("运动");
                getEventsIconMotion(eventTypeIcon, iv_type, iv_type2);
                break;
            case "2":
                tv_type.setText("游戏");
                tv_type2.setText("游戏");
                getEventsIconGame(eventTypeIcon, iv_type, iv_type2);
                break;
            case "3":
                tv_type.setText("下午茶");
                tv_type2.setText("下午茶");
                getEventsIconTea(eventTypeIcon, iv_type, iv_type2);
                break;
            case "4":
                tv_type.setText("K歌");
                tv_type2.setText("K歌");
                iv_type.setImageResource(R.drawable.icon_activity_karaoke);
                iv_type2.setImageResource(R.drawable.icon_activity_karaoke);
                break;
            case "5":
                tv_type.setText("桌游");
                tv_type2.setText("桌游");
                getEventsIconBoardPlay(eventTypeIcon, iv_type, iv_type2);
                break;
            case "6":
                tv_type.setText("电影");
                tv_type2.setText("电影");
                iv_type.setImageResource(R.drawable.icon_activity_film);
                iv_type2.setImageResource(R.drawable.icon_activity_film);
                break;
            case "7":
                tv_type.setText("夜生活");
                tv_type2.setText("夜生活");
                getEventsIconNightLife(eventTypeIcon, iv_type, iv_type2);
                break;
            case "11":
                tv_type.setText("其他");
                tv_type2.setText("其他");
                getEventsIconNot(eventTypeIcon, iv_type, iv_type2);
                break;
        }
    }

    /**
     * 美食
     *
     * @param eventTypeIcon
     * @param iv_type
     * @param iv_type2
     */
    public static void getEventsIconFood(String eventTypeIcon, AppCompatImageView iv_type, AppCompatImageView iv_type2){
        switch (eventTypeIcon){
            case "0":
                iv_type.setImageResource(R.drawable.icon_activity_food);
                iv_type2.setImageResource(R.drawable.icon_activity_food);
                break;
            case "1":
                iv_type.setImageResource(R.drawable.icon_activity_food11);
                iv_type2.setImageResource(R.drawable.icon_activity_food11);
                break;
            case "2":
                iv_type.setImageResource(R.drawable.icon_activity_food12);
                iv_type2.setImageResource(R.drawable.icon_activity_food12);
                break;
            case "3":
                iv_type.setImageResource(R.drawable.icon_activity_food13);
                iv_type2.setImageResource(R.drawable.icon_activity_food13);
                break;
            case "4":
                iv_type.setImageResource(R.drawable.icon_activity_food14);
                iv_type2.setImageResource(R.drawable.icon_activity_food14);
                break;
            case "5":
                iv_type.setImageResource(R.drawable.icon_activity_food15);
                iv_type2.setImageResource(R.drawable.icon_activity_food15);
                break;
            case "6":
                iv_type.setImageResource(R.drawable.icon_activity_food16);
                iv_type2.setImageResource(R.drawable.icon_activity_food16);
                break;
        }
    }

    /**
     * 运动
     *
     * @param eventTypeIcon
     * @param iv_type
     * @param iv_type2
     */
    public static void getEventsIconMotion(String eventTypeIcon, AppCompatImageView iv_type, AppCompatImageView iv_type2){
        switch (eventTypeIcon){
            case "0":
                iv_type.setImageResource(R.drawable.icon_activity_motion);
                iv_type2.setImageResource(R.drawable.icon_activity_motion);
                break;
            case "1":
                iv_type.setImageResource(R.drawable.icon_activity_motion11);
                iv_type2.setImageResource(R.drawable.icon_activity_motion11);
                break;
            case "2":
                iv_type.setImageResource(R.drawable.icon_activity_motion12);
                iv_type2.setImageResource(R.drawable.icon_activity_motion12);
                break;
            case "3":
                iv_type.setImageResource(R.drawable.icon_activity_motion13);
                iv_type2.setImageResource(R.drawable.icon_activity_motion13);
                break;
            case "4":
                iv_type.setImageResource(R.drawable.icon_activity_motion14);
                iv_type2.setImageResource(R.drawable.icon_activity_motion14);
                break;
            case "5":
                iv_type.setImageResource(R.drawable.icon_activity_motion15);
                iv_type2.setImageResource(R.drawable.icon_activity_food15);
                break;
            case "6":
                iv_type.setImageResource(R.drawable.icon_activity_motion16);
                iv_type2.setImageResource(R.drawable.icon_activity_motion16);
                break;
            case "7":
                iv_type.setImageResource(R.drawable.icon_activity_motion17);
                iv_type2.setImageResource(R.drawable.icon_activity_motion17);
                break;
            case "8":
                iv_type.setImageResource(R.drawable.icon_activity_motion18);
                iv_type2.setImageResource(R.drawable.icon_activity_motion18);
                break;
            case "9":
                iv_type.setImageResource(R.drawable.icon_activity_motion19);
                iv_type2.setImageResource(R.drawable.icon_activity_motion19);
                break;
            case "10":
                iv_type.setImageResource(R.drawable.icon_activity_motion20);
                iv_type2.setImageResource(R.drawable.icon_activity_motion20);
                break;
            case "11":
                iv_type.setImageResource(R.drawable.icon_activity_motion21);
                iv_type2.setImageResource(R.drawable.icon_activity_motion21);
                break;
            case "12":
                iv_type.setImageResource(R.drawable.icon_activity_motion22);
                iv_type2.setImageResource(R.drawable.icon_activity_motion22);
                break;
        }
    }

    /**
     * 游戏
     *
     * @param eventTypeIcon
     * @param iv_type
     * @param iv_type2
     */
    public static void getEventsIconGame(String eventTypeIcon, AppCompatImageView iv_type, AppCompatImageView iv_type2){
        switch (eventTypeIcon){
            case "0":
                iv_type.setImageResource(R.drawable.icon_activity_game);
                iv_type2.setImageResource(R.drawable.icon_activity_game);
                break;
            case "1":
                iv_type.setImageResource(R.drawable.icon_activity_game11);
                iv_type2.setImageResource(R.drawable.icon_activity_game11);
                break;
            case "2":
                iv_type.setImageResource(R.drawable.icon_activity_game12);
                iv_type2.setImageResource(R.drawable.icon_activity_game12);
                break;
            case "3":
                iv_type.setImageResource(R.drawable.icon_activity_game13);
                iv_type2.setImageResource(R.drawable.icon_activity_game13);
                break;
        }
    }

    /**
     * 下午茶
     *
     * @param eventTypeIcon
     * @param iv_type
     * @param iv_type2
     */
    public static void getEventsIconTea(String eventTypeIcon, AppCompatImageView iv_type, AppCompatImageView iv_type2){
        switch (eventTypeIcon){
            case "0":
                iv_type.setImageResource(R.drawable.icon_activity_tea);
                iv_type2.setImageResource(R.drawable.icon_activity_tea);
                break;
            case "1":
                iv_type.setImageResource(R.drawable.icon_activity_tea11);
                iv_type2.setImageResource(R.drawable.icon_activity_tea11);
                break;
            case "2":
                iv_type.setImageResource(R.drawable.icon_activity_tea12);
                iv_type2.setImageResource(R.drawable.icon_activity_tea12);
                break;
            case "3":
                iv_type.setImageResource(R.drawable.icon_activity_tea13);
                iv_type2.setImageResource(R.drawable.icon_activity_tea13);
                break;
            case "4":
                iv_type.setImageResource(R.drawable.icon_activity_tea14);
                iv_type2.setImageResource(R.drawable.icon_activity_tea14);
                break;
            case "5":
                iv_type.setImageResource(R.drawable.icon_activity_tea15);
                iv_type2.setImageResource(R.drawable.icon_activity_tea15);
                break;
        }
    }

    /**
     * 桌游
     *
     * @param eventTypeIcon
     * @param iv_type
     * @param iv_type2
     */
    public static void getEventsIconBoardPlay(String eventTypeIcon, AppCompatImageView iv_type, AppCompatImageView iv_type2){
        switch (eventTypeIcon){
            case "0":
                iv_type.setImageResource(R.drawable.icon_activity_board_play);
                iv_type2.setImageResource(R.drawable.icon_activity_board_play);
                break;
            case "1":
                iv_type.setImageResource(R.drawable.icon_activity_board_play11);
                iv_type2.setImageResource(R.drawable.icon_activity_board_play11);
                break;
            case "2":
                iv_type.setImageResource(R.drawable.icon_activity_board_play12);
                iv_type2.setImageResource(R.drawable.icon_activity_board_play12);
                break;
            case "3":
                iv_type.setImageResource(R.drawable.icon_activity_board_play13);
                iv_type2.setImageResource(R.drawable.icon_activity_board_play13);
                break;
            case "4":
                iv_type.setImageResource(R.drawable.icon_activity_board_play14);
                iv_type2.setImageResource(R.drawable.icon_activity_board_play14);
                break;
        }
    }

    /**
     * 夜生活
     *
     * @param eventTypeIcon
     * @param iv_type
     * @param iv_type2
     */
    public static void getEventsIconNightLife(String eventTypeIcon, AppCompatImageView iv_type, AppCompatImageView iv_type2){
        switch (eventTypeIcon){
            case "0":
                iv_type.setImageResource(R.drawable.icon_activity_night_life);
                iv_type2.setImageResource(R.drawable.icon_activity_night_life);
                break;
            case "1":
                iv_type.setImageResource(R.drawable.icon_activity_night_life11);
                iv_type2.setImageResource(R.drawable.icon_activity_night_life11);
                break;
            case "2":
                iv_type.setImageResource(R.drawable.icon_activity_night_life12);
                iv_type2.setImageResource(R.drawable.icon_activity_night_life12);
                break;
            case "3":
                iv_type.setImageResource(R.drawable.icon_activity_night_life13);
                iv_type2.setImageResource(R.drawable.icon_activity_night_life13);
                break;
            case "4":
                iv_type.setImageResource(R.drawable.icon_activity_night_life14);
                iv_type2.setImageResource(R.drawable.icon_activity_board_play14);
                break;
            case "5":
                iv_type.setImageResource(R.drawable.icon_activity_night_life15);
                iv_type2.setImageResource(R.drawable.icon_activity_night_life15);
                break;
        }
    }

    /**
     * 其他
     *
     * @param eventTypeIcon
     * @param iv_type
     * @param iv_type2
     */
    public static void getEventsIconNot(String eventTypeIcon, AppCompatImageView iv_type, AppCompatImageView iv_type2){
        switch (eventTypeIcon){
            case "0":
                iv_type.setImageResource(R.drawable.icon_activity_not);
                iv_type2.setImageResource(R.drawable.icon_activity_not);
                break;
            case "1":
                iv_type.setImageResource(R.drawable.icon_activity_not11);
                iv_type2.setImageResource(R.drawable.icon_activity_not11);
                break;
            case "2":
                iv_type.setImageResource(R.drawable.icon_activity_not12);
                iv_type2.setImageResource(R.drawable.icon_activity_not12);
                break;
            case "3":
                iv_type.setImageResource(R.drawable.icon_activity_not13);
                iv_type2.setImageResource(R.drawable.icon_activity_not13);
                break;
            case "4":
                iv_type.setImageResource(R.drawable.icon_activity_not14);
                iv_type2.setImageResource(R.drawable.icon_activity_not14);
                break;
            case "5":
                iv_type.setImageResource(R.drawable.icon_activity_not15);
                iv_type2.setImageResource(R.drawable.icon_activity_not15);
                break;
            case "6":
                iv_type.setImageResource(R.drawable.icon_activity_not16);
                iv_type2.setImageResource(R.drawable.icon_activity_not16);
                break;
            case "7":
                iv_type.setImageResource(R.drawable.icon_activity_not17);
                iv_type2.setImageResource(R.drawable.icon_activity_not17);
                break;
            case "8":
                iv_type.setImageResource(R.drawable.icon_activity_not18);
                iv_type2.setImageResource(R.drawable.icon_activity_not18);
                break;
            case "9":
                iv_type.setImageResource(R.drawable.icon_activity_not19);
                iv_type2.setImageResource(R.drawable.icon_activity_not19);
                break;
        }
    }

    /**
     * 设置个人中心的个性标签显示
     *
     * @param userInfoBean
     * @return
     */
    public static List<MineTagBean> getMineTag(UserInfoBean userInfoBean){
        List<MineTagBean> mineTagBeanList = new ArrayList<>();
        mineTagBeanList.clear();
        if (userInfoBean.getData().getProfile().getTag()!= null && userInfoBean.getData().getProfile().getTag().size() > 0){
            mineTagBeanList.add(new MineTagBean(RefreshUtils.setTagIcon(userInfoBean.getData().getProfile().getTag().get(0)), userInfoBean.getData().getProfile().getTag().get(0)));
        }

        if (userInfoBean.getData().getProfile().getEvent_tag()!= null && userInfoBean.getData().getProfile().getEvent_tag().size() > 0){
            mineTagBeanList.add(new MineTagBean(RefreshUtils.setEventTagIcon(userInfoBean.getData().getProfile().getEvent_tag().get(0)), userInfoBean.getData().getProfile().getEvent_tag().get(0)));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getPet_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag, userInfoBean.getData().getProfile().getPet_tag()));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getFitness_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_activity_motion, userInfoBean.getData().getProfile().getFitness_tag()));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getSport_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag1, userInfoBean.getData().getProfile().getSport_tag()));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getParty_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag2, userInfoBean.getData().getProfile().getParty_tag()));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getDinner_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag3, userInfoBean.getData().getProfile().getDinner_tag()));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getHome_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag4, userInfoBean.getData().getProfile().getHome_tag()));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getStudy_Tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag5, userInfoBean.getData().getProfile().getStudy_Tag()));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getMusic_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag6, userInfoBean.getData().getProfile().getMusic_tag()));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getMovie_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag7, userInfoBean.getData().getProfile().getMovie_tag()));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getBook_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag8, userInfoBean.getData().getProfile().getBook_tag()));
        }

        return  mineTagBeanList;
    }

    /**
     * 设置个人中心的个性标签显示只显示3个
     *
     * @param userInfoBean
     * @return
     */
    public static List<MineTagBean> getMineTagSmall(UserInfoBean userInfoBean){
        List<MineTagBean> mineTagBeanList = new ArrayList<>();
        mineTagBeanList.clear();
        if (userInfoBean.getData().getProfile().getTag()!= null && userInfoBean.getData().getProfile().getTag().size() > 0){
            mineTagBeanList.add(new MineTagBean(RefreshUtils.setTagIcon(userInfoBean.getData().getProfile().getTag().get(0)), userInfoBean.getData().getProfile().getTag().get(0)));
        }

        if (userInfoBean.getData().getProfile().getEvent_tag()!= null && userInfoBean.getData().getProfile().getEvent_tag().size() > 0){
            mineTagBeanList.add(new MineTagBean(RefreshUtils.setEventTagIcon(userInfoBean.getData().getProfile().getEvent_tag().get(0)), userInfoBean.getData().getProfile().getEvent_tag().get(0)));
        }

        if (!TextUtils.isEmpty(userInfoBean.getData().getProfile().getPet_tag())){
            mineTagBeanList.add(new MineTagBean(R.drawable.icon_mine_tag, userInfoBean.getData().getProfile().getPet_tag()));
        }

        return  mineTagBeanList;
    }

    /**
     * 显示我的个性标签
     *
     * @param activity
     * @param flLabel
     * @param mineTagBeanList
     */
    public static void getShowTag(BaseActivity activity, FlexboxLayout flLabel, List<MineTagBean> mineTagBeanList){
        flLabel.removeAllViews();
        for (MineTagBean mineTagBean : mineTagBeanList) {
            View view1 =  LayoutInflater.from(activity).inflate(R.layout.adapter_mine_label, flLabel, false);
            AppCompatImageView appCompatImageView = view1.findViewById(R.id.iv_icon);
            AppCompatTextView appCompatTextView = view1.findViewById(R.id.tv_title);
            appCompatImageView.setImageResource(mineTagBean.getIcon());
            appCompatTextView.setText(mineTagBean.getTitle());
            flLabel.addView(view1);
        }
    }
}
