package com.auto.logistics.Service;

/**
 * Created by Administrator on 2016/12/30.
 */


import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.ab.util.AbJsonUtil;
import com.ab.util.AbToastUtil;
import com.auto.logistics.JavaBean.Position;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;

import de.greenrobot.event.EventBus;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务,则务必要在 AndroidManifest中声明, 否则无法接受消
 */
public class DemoIntentService extends GTIntentService {
    public DemoIntentService() {
    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }
//    onReceiveMessageData 处理透传消息
    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
      byte[] payload =   msg.getPayload();
        Log.d(TAG, "onReceiveMessageData: "+payload);
        if (payload!=null){
            String json = new String(payload);
            Log.d(TAG, "onReceiveMessageData: "+json );
            Position position =    AbJsonUtil.fromJson(json,Position.class);
            if (position.getPar().getPos()!=null){
                String carlocation =  position.getPar().getPos();
                EventBus.getDefault().post(carlocation);
            }else {
               Toast.makeText(getApplicationContext(),"车位信息解析数据有误",Toast.LENGTH_SHORT).show();
            }
        }
    }
//onReceiveClientId 接收 cid
    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }
//onReceiveOnlineState cid 离线上线通知
    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }
//onReceiveCommandResult 各种事件处理回执
    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }
}
