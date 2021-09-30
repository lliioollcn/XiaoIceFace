package cn.lliiooll.bot.ai.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ProcessRequest {
    public long CreateTime;
    public long MsgId;
    public String TraceId;
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


    public static class ContentData {
        public String imageUrl;

        /**
         * 用于序列化/反序列化
         */
        public ContentData() {
        }

        public ContentData(String url) {
            this.imageUrl = url;
        }

        @JSONField(serialize = false)
        public static ProcessRequest.ContentData getInstance(String url) {
            return new ContentData(url);
        }
    }
}
