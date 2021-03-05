package com.wankrfun.crania.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.bean
 * @ClassName: EventsCommentsBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2/7/21 8:47 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/7/21 8:47 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsCommentsBean {
    /**
     * code : 0
     * data : {"comments":[{"comment_userName":"好奇小姐","comment_userPhoto":"http://domestic.media.jiaotangapp.com/89e27464598cea97f49fbcec858c732d_image.png?imageslim","replys":[{"reply_user":"WwhqRSQ8N1","reply_userName":"蟹蟹的蟹","reply_userPhoto":"http://domestic.media.jiaotangapp.com/130b3b85113d1d5c95c8dc43c0d9fa65_image.png?imageslim","comment":"pN1bMbKMsu","time":"2021-01-31T13:49:06.320Z","objectId":"R4iYI69DtI","content":"那我挺谢谢你的"}],"comment_user":"0gYceV86aI","time":"2021-01-31T13:48:07.171Z","event":"UqwUM3wnfW","objectId":"pN1bMbKMsu","content":"哄鬼呢..."},{"comment_userName":"好奇小姐","comment_userPhoto":"http://domestic.media.jiaotangapp.com/89e27464598cea97f49fbcec858c732d_image.png?imageslim","replys":[{"reply_user":"WwhqRSQ8N1","reply_userName":"蟹蟹的蟹","reply_userPhoto":"http://domestic.media.jiaotangapp.com/130b3b85113d1d5c95c8dc43c0d9fa65_image.png?imageslim","comment":"jw00pBcOUf","time":"2021-01-31T13:50:50.033Z","objectId":"2umqZ8m2S0","content":"我哄鬼呢 我不配"}],"comment_user":"0gYceV86aI","time":"2021-01-31T13:50:21.354Z","event":"UqwUM3wnfW","objectId":"jw00pBcOUf","content":"哈哈哈哈哈不谢 我愿意当你的钱包呀"}]}
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
        private List<CommentsBean> comments;

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean implements Serializable {
            /**
             * comment_userName : 好奇小姐
             * comment_userPhoto : http://domestic.media.jiaotangapp.com/89e27464598cea97f49fbcec858c732d_image.png?imageslim
             * replys : [{"reply_user":"WwhqRSQ8N1","reply_userName":"蟹蟹的蟹","reply_userPhoto":"http://domestic.media.jiaotangapp.com/130b3b85113d1d5c95c8dc43c0d9fa65_image.png?imageslim","comment":"pN1bMbKMsu","time":"2021-01-31T13:49:06.320Z","objectId":"R4iYI69DtI","content":"那我挺谢谢你的"}]
             * comment_user : 0gYceV86aI
             * time : 2021-01-31T13:48:07.171Z
             * event : UqwUM3wnfW
             * objectId : pN1bMbKMsu
             * content : 哄鬼呢...
             */

            private String comment_userName;
            private String comment_userPhoto;
            private String comment_user;
            private String time;
            private String event;
            private String objectId;
            private String content;
            private List<ReplysBean> replys;

            public String getComment_userName() {
                return comment_userName;
            }

            public void setComment_userName(String comment_userName) {
                this.comment_userName = comment_userName;
            }

            public String getComment_userPhoto() {
                return comment_userPhoto;
            }

            public void setComment_userPhoto(String comment_userPhoto) {
                this.comment_userPhoto = comment_userPhoto;
            }

            public String getComment_user() {
                return comment_user;
            }

            public void setComment_user(String comment_user) {
                this.comment_user = comment_user;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getEvent() {
                return event;
            }

            public void setEvent(String event) {
                this.event = event;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public List<ReplysBean> getReplys() {
                return replys;
            }

            public void setReplys(List<ReplysBean> replys) {
                this.replys = replys;
            }

            public static class ReplysBean implements Serializable {
                /**
                 * reply_user : WwhqRSQ8N1
                 * reply_userName : 蟹蟹的蟹
                 * reply_userPhoto : http://domestic.media.jiaotangapp.com/130b3b85113d1d5c95c8dc43c0d9fa65_image.png?imageslim
                 * comment : pN1bMbKMsu
                 * time : 2021-01-31T13:49:06.320Z
                 * objectId : R4iYI69DtI
                 * content : 那我挺谢谢你的
                 */

                private String reply_user;
                private String reply_userName;
                private String reply_userPhoto;
                private String comment;
                private String time;
                private String objectId;
                private String content;

                public String getReply_user() {
                    return reply_user;
                }

                public void setReply_user(String reply_user) {
                    this.reply_user = reply_user;
                }

                public String getReply_userName() {
                    return reply_userName;
                }

                public void setReply_userName(String reply_userName) {
                    this.reply_userName = reply_userName;
                }

                public String getReply_userPhoto() {
                    return reply_userPhoto;
                }

                public void setReply_userPhoto(String reply_userPhoto) {
                    this.reply_userPhoto = reply_userPhoto;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getObjectId() {
                    return objectId;
                }

                public void setObjectId(String objectId) {
                    this.objectId = objectId;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }
        }
    }
}
