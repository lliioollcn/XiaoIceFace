package cn.lliiooll.bot.ai;

import cn.hutool.json.JSONUtil;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import okhttp3.*;

public class Network {
    public static final Gson gson = new GsonBuilder().serializeNulls().create();

    @SneakyThrows
    public static Response post(String url, RequestBody body, Headers headers) {
        return new OkHttpClient()
                .newCall(new Request.Builder()
                        .url(url)
                        .headers(headers)
                        .post(body)
                        .build()).execute();
    }

    @SneakyThrows
    public static Response get(String url) {
        return new OkHttpClient()
                .newCall(new Request.Builder()
                        .url(url)
                        .get()
                        .build())
                .execute();
    }

    @SneakyThrows
    public static <T> T post(String url, RequestBody body, Headers headers, Class<T> type) {
        Response response = post(url, body, headers);
        String jstr = response.body().string();
        System.out.println(jstr);
        if (!Strings.isNullOrEmpty(jstr) && JSONUtil.isJson(jstr)) {
            return gson.fromJson(jstr, type);
        } else {
            return null;
        }
    }


    public static <T> T post(String url, String body, Headers headers, Class<T> type) {
        return post(url, RequestBody.create(body, MediaType.get("application/x-www-form-urlencoded")), headers, type);
    }

    public static Response postJson(String url, String body, Headers headers) {
        return post(url, RequestBody.create(body, MediaType.get("application/json;charset=utf-8")), headers);
    }
}
