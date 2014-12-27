package com.example.noprom.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.noprom.xiaomumu.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by noprom.
 * 数据适配器
 */
public class ChatMessageAdapter extends BaseAdapter {
    // 用来压榨布局
    private LayoutInflater mInflater;
    // 数据集
    private List<ChatMessage> mDatas;

    public ChatMessageAdapter(Context context, List<ChatMessage> mDatas) {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }


    @Override
    public int getItemViewType(int position) {
        // 拿到ChatMessage
        ChatMessage chatMessage = mDatas.get(position);
        // 接收消息则返回0，发送消息返回1
        if (chatMessage.getType() == ChatMessage.Type.INCOMING) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 取得chatMessage
        ChatMessage chatMessage = mDatas.get(position);
        ViewHolder viewHolder = null;

        // 设置convertView
        if (convertView == null) {
            // 设置接受消息的布局
            if (getItemViewType(position) == 0) {
                convertView = mInflater.inflate(R.layout.item_from_msg, parent, false);
                // 设置viewHolder
                viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_from_msg_date);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_from_msg_info);
            } else {
                // 设置发送消息的布局
                convertView = mInflater.inflate(R.layout.item_to_msg, parent, false);
                // 设置viewHolder
                viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_to_msg_date);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_to_msg_info);
            }
            // 存储viewHolder
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        // 将时间转化为字符串
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 设置数据
        viewHolder.mDate.setText(df.format(chatMessage.getDate()));
        viewHolder.mMsg.setText(chatMessage.getMsg());
        return convertView;
    }

    /**
     * ViewHolder，用来提升效率
     */
    private final class ViewHolder {
        // 消息时间
        TextView mDate;
        // 消息内容
        TextView mMsg;
    }
}
;