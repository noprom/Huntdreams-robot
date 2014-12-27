package com.example.noprom.xiaomumu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.noprom.bean.ChatMessage;
import com.example.noprom.bean.ChatMessageAdapter;
import com.example.noprom.utils.HttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    // 所有消息记录
    private ListView mMsgs;
    // 适配器
    private ChatMessageAdapter mAdapter;
    // 适配器数据源
    private List<ChatMessage> mDatas;

    // 编辑框
    private EditText mInputMsg;
    // 发送消息
    private Button mSendMsg;

    // 等待接收，子线程里面完成数据的返回
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // 取出msg
            ChatMessage fromMsg = (ChatMessage) msg.obj;
            // 加入到数据集中
            mDatas.add(fromMsg);
            // 通知Adapter数据集改变
            mAdapter.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        // 初始化数据
        initData();
        // 初始化事件
        initListener();
    }

    /**
     * 初始化事件
     */
    private void initListener() {
        mSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取到发送的消息
                final String toMsg = mInputMsg.getText().toString();
                // 判断是否为空
                if (TextUtils.isEmpty(toMsg)) {
                    Toast.makeText(MainActivity.this, "您发送的消息不能为空~", Toast.LENGTH_LONG).show();
                    return;
                }
                // 设置当前发送的消息，并加入到ListView数据集中
                ChatMessage toMessage = new ChatMessage();
                toMessage.setDate(new Date());
                toMessage.setMsg(toMsg);
                toMessage.setType(ChatMessage.Type.OUTCOMING);
                mDatas.add(toMessage);
                // 通知Adapter数据集改变
                mAdapter.notifyDataSetChanged();
                // 清空输入框
                mInputMsg.setText("");


                // 不为空则发送消息
                new Thread() {
                    public void run() {
                        // 子线程通过网络请求拿到一个ChatMessage
                        ChatMessage fromMsg = HttpUtils.sendMessage(toMsg);
                        // 赋值给OSmsg
                        Message m = Message.obtain();
                        m.obj = fromMsg;
                        // 通过handler将消息发送
                        mHandler.sendMessage(m);
                    }
                }.start();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 准备数据源
        mDatas = new ArrayList<ChatMessage>();
        mDatas.add(new ChatMessage("你好呀，猎梦为您服务", ChatMessage.Type.INCOMING, new Date()));
//        mDatas.add(new ChatMessage("我是noprom，你好呀~", ChatMessage.Type.OUTCOMING, new Date()));
        // 设置适配器
        mAdapter = new ChatMessageAdapter(MainActivity.this, mDatas);
        mMsgs.setAdapter(mAdapter);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mMsgs = (ListView) findViewById(R.id.id_listview_msgs);
        mInputMsg = (EditText) findViewById(R.id.id_input_msg);
        mSendMsg = (Button) findViewById(R.id.id_send_msg);
    }
}
;