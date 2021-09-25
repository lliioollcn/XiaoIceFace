package cn.lliiooll.bot.ai.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessRequest {
    private long CreateTime;
    private long MsgId;
    private String TraceId;
    private Content Content;

    @Data
    @Builder
    public static class Content {
        private String imageUrl;
    }
}
