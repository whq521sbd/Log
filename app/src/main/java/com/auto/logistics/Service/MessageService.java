package com.auto.logistics.Service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/2.
 */

public class MessageService extends Service {

    private AbHttpUtil mAbHttpUtil;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 10010:
                    getData(1);
                    break;
            }
            super.handleMessage(msg);

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mAbHttpUtil = AbHttpUtil.getInstance(getApplicationContext());
        getData(1);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getData(final int curPage) {
        AbRequestParams params = new AbRequestParams();
        params.put("TaskNum", "");
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getApplicationContext(), "Token"));
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        params.put("queryTime", time);
        params.put("curPage", curPage);
        params.put("state", "2");
        mAbHttpUtil.post(FinalURL.URL + "/QryLogTask", params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {//成功
                Intent intent = new Intent();
                intent.putExtra("QryLogTaskInfo",s);
                intent.setAction("com.auto.logistics.messagereceiver");
                sendBroadcast(intent);
            }


            @Override
            public void onFailure(int i, String s, Throwable throwable) {//失败
                AbDialogUtil.removeDialog(getApplicationContext());
                AbToastUtil.showToast(getApplicationContext(), "查询失败，请重试~");
            }

            @Override
            public void onStart() {//开始

            }

            @Override
            public void onFinish() {//完成

                new Thread(new Myrun()).start();
            }

        });
    }


    class Myrun implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                Message msg = new Message();
                msg.what = 10010;
                handler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
