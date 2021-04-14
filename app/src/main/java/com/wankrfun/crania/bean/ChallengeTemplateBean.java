package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: ChallengeTemplateBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 4/14/21 11:17 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 4/14/21 11:17 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChallengeTemplateBean {
    /**
     * code : 0
     * data : {"list":[{"question":"你更害怕：","choices":["忘记认识的人","被所有人忘记"]},{"question":"你宁愿接受:","choices":["身无分文","没有朋友"]},{"question":"如果穿越到过去，更想去:","choices":["唐朝","汉朝","宋朝"]},{"question":"你愿意把你的爱情交给谁掌管？","choices":["丘比特","月老","孟非","曾经的班主任"]},{"question":"你会更容易被人夸:","choices":["有才气","值得依靠","善良","优雅","好相处"]},{"question":"你更能接受哪种食物？","choices":["甜豆花","咸豆花","甜粽子","咸粽子","菠萝披萨","香菜","臭豆腐","牛肚"]},{"question":"如果变成动物，你更愿意是:","choices":["猫","狗","鸽子","刺猬","河马"]},{"question":"谁在近一年内影响更大:","choices":["肖战","丁真","郑爽","华晨宇","半藏森林"]},{"question":"你更想拥有谁的超(钞)能力：","choices":["钢铁侠","超人","海王","隐身人","路飞","鸣人","一拳超人"]},{"question":"你更青睐的旅游目的地:","choices":["巴黎","普吉岛","伦敦","夏威夷","冰岛","埃及开罗"]},{"question":"你觉得谁更可能是「柯南」最终boss:","choices":["柯南","小兰","阿笠博士","少年侦探团","小五郎","琴酒"]},{"question":"你更想获得哪种能力?","choices":["不掉头发","不长痘痘","永不失眠","不会超重"]},{"question":"如果成为偶像练习生，你的分组是:","choices":["Dance组（跳）","Vocal 组 （唱)","Rap 组(说唱)","篮球 组 (???)"]},{"question":"周末休息，你会选:","choices":["蹦迪到天亮","睡到自然醒","电竞开黑","下厨做菜","撸猫撸狗"]},{"question":"如果可以，你更愿意:","choices":["当人见人爱的海王","做终身伴一人的眷侣"]},{"question":"活动人不齐时，你会:","choices":["跟着拖延等人约","电话招呼大伙","马不停歇拉人，活动必须准时开始"]},{"question":"在这个世界上，如果你可以随便选择一个人和你共进晚餐，你会选谁?","choices":["巴菲特","马云","袁隆平","安吉丽娜朱莉","埃隆马斯克","奥巴马"]},{"question":"你想过怎么出名吗?","choices":["参加达人秀，靠技术出圈","站在北京大学门口喊李雪琴我等着你","去韩国做练习生，成为实力idol"]},{"question":"你觉得在你心里完美的一天是怎样的：","choices":["天气晴朗不用工作","出门买早餐拿了个抽奖，中了100万大奖"]},{"question":"如果有个不会失败的机会，你会选择？","choices":["投资/创业","出道","表白"]},{"question":"看电影容易让你的情绪想哭吗？","choices":["会😭","不会"]},{"question":"一起打游戏时，你更喜欢？","choices":["尽力carry队友飞","稳定躺赢"]},{"question":"谈恋爱时，你会希望另一半的性格和你：","choices":["互补","相似"]},{"question":"旅游时，你更喜欢？","choices":["用心感受","到处拍照"]},{"question":"选一个可以变10倍","choices":["知识","财富"]},{"question":"你更偏向？","choices":["理性","感性"]},{"question":"穿衣风格上你偏向哪方面？","choices":["好看","舒适"]}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * question : 你更害怕：
             * choices : ["忘记认识的人","被所有人忘记"]
             */

            private String question;
            private List<String> choices;

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public List<String> getChoices() {
                return choices;
            }

            public void setChoices(List<String> choices) {
                this.choices = choices;
            }
        }
    }
}
