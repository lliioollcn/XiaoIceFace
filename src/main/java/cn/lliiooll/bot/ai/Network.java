package cn.lliiooll.bot.ai;

import cn.lliiooll.bot.util.Utils;
import com.alibaba.fastjson.JSON;
import kotlin.text.StringsKt;
import okhttp3.*;

import java.io.IOException;

public class Network {

    public static Response post(String url, RequestBody body, Headers headers) throws Throwable {
        return new OkHttpClient()
                .newCall(new Request.Builder()
                        .url(url)
                        .headers(headers)
                        .post(body)
                        .build()).execute();
    }

    public static Response get(String url) throws Throwable {
        return new OkHttpClient()
                .newCall(new Request.Builder()
                        .url(url)
                        .get()
                        .build())
                .execute();
    }

    public static <T> T post(String url, RequestBody body, Headers headers, Class<T> type) throws Throwable {
        Response response = post(url, body, headers);
        String jstr = response.body().string();
        System.out.println(jstr);
        if (!Utils.isBlank(jstr) && Utils.isJson(jstr)) {
            return JSON.parseObject(jstr, type);
        } else {
            System.out.println(jstr + " not json");
            return null;
        }
    }


    public static <T> T post(String url, String body, Headers headers, Class<T> type) throws Throwable {
        return post(url, RequestBody.create(body, MediaType.get("application/x-www-form-urlencoded")), headers, type);
    }

    public static Response postJson(String url, String body, Headers headers) throws Throwable {
        return post(url, RequestBody.create(body, MediaType.get("application/json;charset=utf-8")), headers);
    }
}
