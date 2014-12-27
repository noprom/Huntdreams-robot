package com.example.noprom.bean;

import java.util.Date;

/**
 * Created by noprom .
 */
public class ChatMessage {
    // 消息名称
    private String name;
    // 消息内容
    private String msg;
    // 消息类型
    private Type type;
    // 消息时间
    private Date date;

    public ChatMessage(){}
    public ChatMessage(String msg, Type type, Date date) {
        super();
        this.msg = msg;
        this.type = type;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // 类型枚举
    public enum Type {
        INCOMING, OUTCOMING
    }
}
