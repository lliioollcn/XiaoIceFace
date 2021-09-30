package cn.lliiooll.bot.util;

import com.alibaba.fastjson.JSONValidator;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

/**
 * 工具类
 *
 * @author lliiooll
 */
public class Utils {

    /**
     * 计算字符串的md5
     *
     * @param str 字符串
     * @return 字符串的md5
     * @throws Throwable 各种错误
     */
    public static String md5(String str) throws Throwable {
        return Utils.hex(MessageDigest.getInstance("MD5").digest(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 将bytes转为hex
     *
     * @param bytes 字节
     * @return hex
     */
    public static String hex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            String h = Integer.toHexString(b & 0xFF);
            if (h.length() < 2) {
                hex.append("0").append(h);
            } else {
                hex.append(h);
            }
        }
        return hex.toString();
    }

    /**
     * 生成随机字符
     *
     * @param count 字符长度
     * @return 随机字符
     */
    public static String random(int count) {
        String[] zd = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < count; i++) {
            str.append(zd[random.nextInt(zd.length)]);
        }
        return str.toString();
    }

    /**
     * 从输入流中读取字节
     *
     * @param in 输入流
     * @return 字节
     * @throws Throwable 各种错误
     */
    public static byte[] bytes(InputStream in) throws Throwable {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = 0;
        while ((i = in.read()) != -1) {
            baos.write(i);
        }
        baos.flush();
        baos.close();
        in.close();
        return baos.toByteArray();
    }

    /**
     * 将bytes转为base64
     *
     * @param bytes 字节
     * @return base64
     */
    public static String base64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 字符串是否为空
     */
    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断json是否规范
     *
     * @param jstr json字符串
     * @return json是否规范
     */
    public static boolean isJson(String jstr) {
        return JSONValidator.from(jstr).validate();
    }

    /**
     * 从url读取字节
     * @param url url
     * @return 字节
     * @throws Throwable 各种错误
     */
    public static byte[] bytes(String url) throws Throwable {
        return bytes(new URL(url).openConnection().getInputStream());
    }
}
