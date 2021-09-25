package cn.lliiooll.bot.ai.bean;

import lombok.Data;

@Data
public class ProcessResponse {
    public String msgId;
    public long timestamp;
    public String receiverId;
    public Content content;

    @Data
    public static class Content {
        public String text;
        public String imageUrl;
        public Metadata metadata;
    }

    @Data
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
    }
}
