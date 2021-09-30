package cn.lliiooll.bot.ai;

import cn.lliiooll.bot.ai.bean.ImgUploadResponse;
import cn.lliiooll.bot.ai.bean.ProcessRequest;
import cn.lliiooll.bot.ai.bean.ProcessResponse;
import cn.lliiooll.bot.util.Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Headers;

import java.net.URL;
import java.util.HashMap;

public class XiaoIceFaceModule {
    public String url;
    public String sourceStr;
    public JSONObject sourceObj;

    public XiaoIceFaceModule(String url) {
        this.url = url;
    }

    public ProcessResponse process() throws Throwable {
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
        System.out.println(JSON.toJSON(iur));
        ProcessRequest request = new ProcessRequest(
                System.currentTimeMillis() / 1000,
                System.currentTimeMillis(),
                Utils.md5(Utils.random(10)),
                ProcessRequest.ContentData.getInstance(iur.getURL())

        );
        System.out.println(JSON.toJSON(request));
        String jstr = Network.postJson("https://ux.xiaoice.com/api/imageAnalyze/Process?service=beauty", JSON.toJSONString(
                request
        ), Headers.of(new HashMap<String, String>() {{
            put("Cookie", cookies.toString());
            put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:89.0) Gecko/20100101 Firefox/89.0");
            put("Referer", "https://ux.xiaoice.com/beautyv3");
        }})).body().string();
        this.sourceStr = jstr;
        this.sourceObj = JSON.parseObject(sourceStr);
        System.out.println(this.sourceStr);
        System.out.println(this.sourceObj);
        ProcessResponse r = JSON.parseObject(jstr, ProcessResponse.class);
        System.out.println(r);
        return r;
    }

    public String getFileBase64() throws Throwable {
        if (!Utils.isBlank(url)) {
            return Utils.base64(Utils.bytes(new URL(url).openConnection().getInputStream()));
        } else
            throw new RuntimeException("file、fileName、fileBase64必须至少有一项不为空");

    }

}
