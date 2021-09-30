package cn.lliiooll.bot.ai.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图片处理请求体
 *
 * @author lliiooll
 */
public class ProcessRequest {
    /**
     * 请求创建时间(通常为 System.currentTimeMillis() / 1000)
     */
    public long CreateTime;
    /**
     * 消息id(通常为 System.currentTimeMillis())
     */
    public long MsgId;
    /**
     * 会话id(通常为一个随机md5)
     */
    public String TraceId;
    /**
     * 请求内容
     *
     * @see ContentData
     */
    public ContentData Content;

    public ProcessRequest(long CreateTime, long MsgId, String TraceId, ContentData Content) {
        this.CreateTime = CreateTime;
        this.MsgId = MsgId;
        this.TraceId = TraceId;
        this.Content = Content;
    }

    /**
     * 用于序列化/反序列化
     */
    public ProcessRequest() {
    }


    /**
     * 图片处理请求内容
     */
    public static class ContentData {
        /**
         * 图片url(通常为在 ImgUploadResponse 中返回的Host+Url)
         */
        public String imageUrl;

        /**
         * 用于序列化/反序列化
         */
        public ContentData() {
        }

        public ContentData(String url) {
            this.imageUrl = url;
        }

        /**
         * 直接实例化idea莫名其妙报错，所以设置此方法
         *
         * @param url 图片url
         * @return ProcessRequest.ContentData 实例
         */
        @JSONField(serialize = false)
        public static ProcessRequest.ContentData getInstance(String url) {
            return new ContentData(url);
        }
    }
}
