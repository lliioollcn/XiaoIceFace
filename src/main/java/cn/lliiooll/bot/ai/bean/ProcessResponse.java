package cn.lliiooll.bot.ai.bean;

/**
 * 图片处理响应体
 *
 * @author lliiooll
 */
public class ProcessResponse {
    /**
     * 在 ProcessRequest 中的TraceId
     */
    public String msgId;
    /**
     * 返回时间
     */
    public long timestamp;
    /**
     * 不知道是啥，通常是null
     */
    public String receiverId;
    /**
     * 返回主体
     *
     * @see Content
     */
    public Content content;

    /**
     * 用于序列化/反序列化
     */
    public ProcessResponse() {
    }

    /**
     * 图片处理返回主体
     */
    public static class Content {
        /**
         * 评价文字
         */
        public String text;
        /**
         * 评分图片
         */
        public String imageUrl;
        /**
         * 返回体元数据
         *
         * @see Metadata
         */
        public Metadata metadata;

        /**
         * 用于序列化/反序列化
         */
        public Content() {
        }
    }

    /**
     * 返回体元数据
     */
    public static class Metadata {
        /**
         * 评价群体数(比如有韩国、中国的评分这里就是2)
         * FBR_Key数字: 代表评分群体，比如: FBR_Key0: 中国00后男性(不唯一)
         * FBR_Id数字: 代表评分群体id，目前不清楚
         * FBR_Score数字: 代表评分群体给出的分数，比如: FBR_Score0: 8.8
         */
        public long FBR_Cnt;
        /**
         * 未知
         */
        public String FBR_Mode;
        /**
         * 貌似是性别
         */
        public String gender;
        /**
         * 得分
         */
        public float score;
        /**
         * 未知
         */
        public String Comment_Prefix;
        public String isCeleb;
        /**
         * 未知
         */
        public boolean enableUserFeedback;
        /**
         * 未知
         */
        public String isEmoji;
        /**
         * 图片中人脸个数
         */
        public long face_number;
        /**
         * 报告图片
         */
        public String reportImgUrl;
        /**
         * 脸部方框点，二维数组形式，例如: [[(245, 222), (383, 222), (245, 360), (383, 360)]]
         */
        public String face_points;
        /**
         * 未知
         */
        public String AnswerFeed;
        /**
         * 未知
         */
        public String w;
        /**
         * 未知
         */
        public String aid;

        /**
         * 用于序列化/反序列化
         */
        public Metadata() {

        }
    }
}
