package cn.lliiooll.bot.ai;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.lliiooll.bot.ai.bean.ImgUploadResponse;
import cn.lliiooll.bot.ai.bean.ProcessRequest;
import cn.lliiooll.bot.ai.bean.ProcessResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import okhttp3.Headers;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

@Data
public class XiaoIceFaceModule {
    public String url;
    public String sourceStr;
    public JSONObject sourceObj;

    public XiaoIceFaceModule(String url) {
        this.url = url;
    }

    @SneakyThrows
    public ProcessResponse process() {
        Headers headers = Network.get("https://ux.xiaoice.com/beautyv3").headers();
        StringBuilder cookies = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase("set-cookie")) {
                String value = headers.value(i).split(";")[0];
                if (cookies.toString().isEmpty()) {
                    cookies.append(value);
                } else {
                    cookies.append(";").append(value);
                }
            }
        }
        ImgUploadResponse iur = Network.post("https://ux.xiaoice.com/api/image/UploadBase64?exp=0",
                getFileBase64(),
                Headers.of(new HashMap<String, String>() {{
                    put("Cookie", cookies.toString());
                    put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:89.0) Gecko/20100101 Firefox/89.0");
                    put("Referer", "https://ux.xiaoice.com/beautyv3");
                }}), ImgUploadResponse.class);
        String jstr = Network.postJson("https://ux.xiaoice.com/api/imageAnalyze/Process?service=beauty", Network.gson.toJson(ProcessRequest.builder()
                .CreateTime(System.currentTimeMillis() / 1000)
                .MsgId(System.currentTimeMillis())
                .TraceId(SecureUtil.md5(RandomUtil.randomString(10)))
                .Content(ProcessRequest.Content.builder()
                        .imageUrl(iur.getHost() + iur.getUrl())
                        .build())
                .build()), Headers.of(new HashMap<String, String>() {{
            put("Cookie", cookies.toString());
            put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:89.0) Gecko/20100101 Firefox/89.0");
            put("Referer", "https://ux.xiaoice.com/beautyv3");
        }})).body().string();
        this.setSourceStr(jstr);
        this.setSourceObj(JSON.parseObject(getSourceStr()));
        return Network.gson.fromJson(jstr, ProcessResponse.class);
    }

    @SneakyThrows
    public String getFileBase64() {
        if (!Strings.isNullOrEmpty(url)) {
            return Base64.encode(IoUtil.readBytes(new URL(url).openConnection().getInputStream()));
        } else
            throw new RuntimeException("file、fileName、fileBase64必须至少有一项不为空");

    }

}
