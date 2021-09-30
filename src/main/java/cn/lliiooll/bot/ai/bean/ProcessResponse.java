package cn.lliiooll.bot.ai.bean;

public class ProcessResponse {
    public String msgId;
    public long timestamp;
    public String receiverId;
    public Content content;

    /**
     * 用于序列化/反序列化
     */
    public ProcessResponse() {
    }

    public static class Content {
        public String text;
        public String imageUrl;
        public Metadata metadata;

        /**
         * 用于序列化/反序列化
         */
        public Content() {
        }
    }

    public static class Metadata {
        public long FBR_Cnt;
        public String FBR_Mode;
        public String gender;
        public float score;
        public String Comment_Prefix;
        public String isCeleb;
        public boolean enableUserFeedback;
        public String isEmoji;
        public long face_number;
        public String reportImgUrl;
        public String face_points;
        public String AnswerFeed;
        public String w;
        public String aid;

        /**
         * 用于序列化/反序列化
         */
        public Metadata() {

        }
    }
}
