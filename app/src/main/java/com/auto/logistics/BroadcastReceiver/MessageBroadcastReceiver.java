package com.auto.logistics.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ab.util.AbJsonUtil;
import com.ab.util.AbMd5;
import com.auto.logistics.Activity.MainActivity;
import com.auto.logistics.Adapter.MessageAdapter;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 接收消息服务发送的广播，并更新页面
 *
 * Created by Administrator on 2016/12/2.
 */

public class MessageBroadcastReceiver extends BroadcastReceiver {
    private  String TAG = "BroadcastReceiver";
    private LogTaskBean dataBean;
    private List<LogTaskBean.DataBean.LogsBean> listlogs;
    private MessageAdapter messageAdapter;
    private List<LogTaskBean.DataBean.LogsBean> newlist = new ArrayList<>() ;
    @Override
    public void onReceive(Context context, Intent intent) {
//         此时的MessageData 是最新的json
       String MessageData =  intent.getStringExtra("QryLogTaskInfo");

        if (MessageData != null) {
            dataBean = AbJsonUtil.fromJson(MessageData,LogTaskBean.class);

            for ( int i =0;i<dataBean.getData().getLogs().size();i++){
                listlogs =  dataBean.getData().getLogs();
            }

            Log.d(TAG, "onReceive: "+listlogs.size());
//              MD5加密对比内容是否一致
            if (!AbMd5.MD5(String.valueOf(listlogs)).equals(AbMd5.MD5(String.valueOf(newlist)))) {
                MainActivity mainActivity = new MainActivity();
             //   mainActivity.updateUI();
            }


            for (int i = 0; i<listlogs.size();i++){
               newlist.add(listlogs.get(i));
            }


            messageAdapter = new MessageAdapter(context, (ArrayList<LogTaskBean.DataBean.LogsBean>) listlogs);
//            刷新适配器
            messageAdapter.notifyDataSetChanged();





            }
        }

    }

