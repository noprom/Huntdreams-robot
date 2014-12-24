package com.example.noprom.bean;

/**
 * Created by noprom.
 */
public class Result {
    // 服务器返回码
    private int code;
    // 服务器返回文本
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
