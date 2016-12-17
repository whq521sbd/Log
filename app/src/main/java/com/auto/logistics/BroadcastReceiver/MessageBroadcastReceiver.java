package com.auto.logistics.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ab.util.AbJsonUtil;
import com.ab.util.AbMd5;
import com.auto.logistics.Adapter.MessageAdapter;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.Utills.SharedPreferencesSava;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 接收消息服务发送的广播，并更新页面
 * <p>
 * Created by Administrator on 2016/12/2.
 */
public class MessageBroadcastReceiver extends BroadcastReceiver {
    private String TAG = "BroadcastReceiver";
    private LogTaskBean dataBean;
    private List<LogTaskBean.DataBean.LogsBean> listlogs;
    private MessageAdapter messageAdapter;
    /**
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
//         此时的MessageData 是最新的json
        String MessageData = intent.getStringExtra("QryLogTaskInfo");

        if (MessageData != null) {
            dataBean = AbJsonUtil.fromJson(MessageData, LogTaskBean.class);

            for (int i = 0; i < dataBean.getData().getLogs().size(); i++) {
                listlogs = dataBean.getData().getLogs();
            }

            messageAdapter = new MessageAdapter(context, (ArrayList<LogTaskBean.DataBean.LogsBean>) listlogs);
//            刷新适配器
            messageAdapter.notifyDataSetChanged();

        //    Log.d(TAG, "onReceive: listlogs:" + listlogs.size());
//            将得到的字符串转换成MD5
            String newMessage = AbMd5.MD5(MessageData);

//              对比消息有没有发生变化，MD5加密内容是否与本地内容一致，如果不一致则提示
            if (!newMessage.equals(SharedPreferencesSava.getInstance().getStringValue(context.getApplicationContext(),"newMessage"))) {
                //MainActivity.showBadge(true);//内容不一致，显示提示
                EventBus.getDefault().post("show");
            }
//              将加密的MD5存在本地,方便下次判断
          SharedPreferencesSava.getInstance().savaStringValue(context.getApplicationContext(),"newMessage",newMessage);

        }
    }

}

