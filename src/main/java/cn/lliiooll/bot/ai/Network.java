package cn.lliiooll.bot.ai;

import cn.lliiooll.bot.util.Utils;
import com.alibaba.fastjson.JSON;
import okhttp3.*;

/**
 * 网络请求类
 *
 * @author lliiooll
 */
public class Network {

    /**
     * 进行post网络请求
     *
     * @param url     要请求的地址
     * @param body    请求体
     * @param headers 请求头
     * @return 响应
     * @throws Throwable 各种错误
     */
    public static Response post(String url, RequestBody body, Headers headers) throws Throwable {
        return new OkHttpClient()
                .newCall(new Request.Builder()
                        .url(url)
                        .headers(headers)
                        .post(body)
                        .build()).execute();
    }

    /**
     * 进行get网络请求
     *
     * @param url 要请求的地址
     * @return 响应
     * @throws Throwable 各种错误
     */
    public static Response get(String url) throws Throwable {
        return new OkHttpClient()
                .newCall(new Request.Builder()
                        .url(url)
                        .get()
                        .build())
                .execute();
    }

    /**
     * 进行post网络请求
     *
     * @param url     要请求的地址
     * @param body    请求体
     * @param headers 请求头
     * @param type    反序列化类型
     * @return 反序列化后的实例
     * @throws Throwable 各种错误
     */
    public static <T> T post(String url, RequestBody body, Headers headers, Class<T> type) throws Throwable {
        Response response = post(url, body, headers);
        String jstr = response.body().string();
        if (!Utils.isBlank(jstr) && Utils.isJson(jstr)) {
            return JSON.parseObject(jstr, type);
        } else {
            return null;
        }
    }

    /**
     * 进行类型为application/x-www-form-urlencoded的post网络请求
     *
     * @param url     要请求的地址
     * @param body    请求体
     * @param headers 请求头
     * @param type    反序列化类型
     * @return 反序列化后的实例
     * @throws Throwable 各种错误
     */
    public static <T> T post(String url, String body, Headers headers, Class<T> type) throws Throwable {
        return post(url, RequestBody.create(body, MediaType.get("application/x-www-form-urlencoded")), headers, type);
    }

    /**
     * 进行类型为application/x-www-form-urlencoded的post网络请求
     *
     * @param url     要请求的地址
     * @param body    请求体
     * @param headers 请求头
     * @return 响应
     * @throws Throwable 各种错误
     */
    public static Response postJson(String url, String body, Headers headers) throws Throwable {
        return post(url, RequestBody.create(body, MediaType.get("application/json;charset=utf-8")), headers);
    }
}
