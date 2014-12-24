package com.example.noprom.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;

/**
 * Created by noprom .
 */
public class HttpUtils {
    // Api 地址
    private static final String URL = "http://www.tuling123.com/openapi/api";
    // Api Key
    private static final String API_KEY = "496841fc1875c6d23cdfddd93370aa64";

    /**
     * 执行GET请求
     * @param msg 请求参数
     * @return GET请求返回的结果
     */
    public static String doGet(String msg) {
        // 返回的结果
        String result = "";
        // 请求的URL地址
        String url = setParams(msg);

        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            java.net.URL urlNet = new java.net.URL(url);

            // 设置HttpURLConnection及其参数
            HttpURLConnection connection = (HttpURLConnection) urlNet.openConnection();
            // 设置最大读入时间
            connection.setReadTimeout(5000);
            // 设置最大连接时间
            connection.setConnectTimeout(5000);
            // 设置请求方式
            connection.setRequestMethod("GET");

            // 拿到服务器返回的流
            inputStream = connection.getInputStream();
            // 读入本地
            int len = -1;
            // 缓冲区
            byte[] buf = new byte[128];
            baos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            // 清空缓冲区
            baos.flush();
            result = new String(baos.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放所有资源
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 设置GET请求参数
     *
     * @param msg 参数信息
     * @return 拼接好的URL
     */
    private static String setParams(String msg) {
        String url = "";
        try {
            url = URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
