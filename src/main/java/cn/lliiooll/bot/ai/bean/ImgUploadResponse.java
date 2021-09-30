package cn.lliiooll.bot.ai.bean;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图片上传响应
 *
 * @author lliiooll
 */
public class ImgUploadResponse {
    /**
     * 返回图片所在域名
     */
    public String Host;
    /**
     * 返回图片所在路径
     */
    public String Url;

    /**
     * @return Host + Url
     */
    @JSONField(serialize = false)
    public String getURL() {
        return Host + Url;
    }
}
