package cn.lliiooll.bot.ai;

import cn.lliiooll.bot.ai.bean.ImgUploadResponse;
import cn.lliiooll.bot.ai.bean.ProcessRequest;
import cn.lliiooll.bot.ai.bean.ProcessResponse;
import cn.lliiooll.bot.util.Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Headers;

import java.util.HashMap;

/**
 * 小冰颜值识别模块
 *
 * @author lliiooll
 */
public class XiaoIceFaceModule {
    /**
     * 图片地址
     */
    public String url;
    /**
     * 响应json字符串
     */
    public String sourceStr;
    /**
     * 响应json实例
     */
    public JSONObject sourceObj;

    public XiaoIceFaceModule(String url) {
        this.url = url;
    }

    /**
     * 上传并处理图片
     *
     * @return 处理结果
     * @throws Throwable 各种错误
     */
    public ProcessResponse process() throws Throwable {
        String cookies = getCookies();
        Headers headers = getHeaders(cookies);
        ImgUploadResponse iur = Network.post("https://ux.xiaoice.com/api/image/UploadBase64?exp=0", getImgBase64(), headers, ImgUploadResponse.class);
        ProcessRequest request = new ProcessRequest(
                System.currentTimeMillis() / 1000,
                System.currentTimeMillis(),
                Utils.md5(Utils.random(10)),
                ProcessRequest.ContentData.getInstance(iur.getURL())
        );
        String jstr = Network.postJson("https://ux.xiaoice.com/api/imageAnalyze/Process?service=beauty", JSON.toJSONString(request), headers).body().string();
        this.sourceStr = jstr;
        this.sourceObj = JSON.parseObject(sourceStr);
        return JSON.parseObject(jstr, ProcessResponse.class);
    }

    /**
     * 获取请求头
     *
     * @param cookies 获取到的cookie
     * @return 请求头
     */
    private Headers getHeaders(final String cookies) {
        return Headers.of(new HashMap<String, String>() {{
            put("Cookie", cookies);
            put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:89.0) Gecko/20100101 Firefox/89.0");
            put("Referer", "https://ux.xiaoice.com/beautyv3");
        }});
    }

    /**
     * *********重要*********
     * 没cookies不返回任何东西
     *
     * @return cookies 获取到的cookie
     * @throws Throwable 各种错误
     */
    private String getCookies() throws Throwable {
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
        return cookies.toString();
    }

    /**
     * 从url获取图片并转化为base64
     *
     * @return 图片的base64
     * @throws Throwable 各种错误
     */
    public String getImgBase64() throws Throwable {
        if (!Utils.isBlank(url)) {
            return Utils.base64(Utils.bytes(url));
        } else
            throw new RuntimeException("url不能为空");

    }

}
