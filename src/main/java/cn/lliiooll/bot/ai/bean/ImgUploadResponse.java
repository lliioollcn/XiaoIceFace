package cn.lliiooll.bot.ai.bean;


import com.alibaba.fastjson.annotation.JSONField;

public class ImgUploadResponse {
    public String Host;
    public String Url;

    @JSONField(serialize = false)
    public String getURL() {
        return Host + Url;
    }
}
