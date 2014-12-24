package com.example.noprom.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.noprom.utils.HttpUtils;

/**
 * Created by noprom .
 */
public class TestHttpUtils extends AndroidTestCase{
    /**
     * 测试发送消息
     */
    public void testSendInfo(){
        String res = HttpUtils.doGet("给我讲个笑话");
        Log.e("TAG",res);
        res = HttpUtils.doGet("你多大了");
        Log.e("TAG",res);
        res = HttpUtils.doGet("你太帅了！");
        Log.e("TAG",res);
        res = HttpUtils.doGet("今天是星期四了");
        Log.e("TAG",res);
    }
}
