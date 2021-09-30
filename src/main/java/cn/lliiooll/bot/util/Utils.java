package cn.lliiooll.bot.util;

import com.alibaba.fastjson.JSONValidator;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

public class Utils {

    public static String md5(String str) throws Throwable {
        return Utils.hex(MessageDigest.getInstance("MD5").digest(str.getBytes(StandardCharsets.UTF_8)));
    }

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

    public static String random(int count) {
        String[] zd = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < count; i++) {
            str.append(zd[random.nextInt(zd.length)]);
        }
        return str.toString();
    }

    public static byte[] bytes(InputStream in) throws Throwable {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /*
        byte[] flush = new byte[1024];
        int len = -1;
        while ((len = in.read(flush)) != -1) {
            baos.write(flush, 0, len);
        }
        baos.flush();
        in.close();

         */
        int i = 0;
        while ((i = in.read()) != -1) {
            baos.write(i);
        }
        baos.flush();
        in.close();
        return baos.toByteArray();
    }

    public static String base64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static boolean isBlank(String url) {
        return url.length() == 0;
    }

    public static boolean isJson(String jstr) {
        return JSONValidator.from(jstr).validate();
    }
}
