package com.auto.logistics.Fragment;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.math.BigDecimal;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.ab.fragment.AbAlertDialogFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbJsonUtil;
import com.ab.util.AbToastUtil;
import com.auto.logistics.Activity.LoginActivity;
import com.auto.logistics.JavaBean.CarSata;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/21.
 */

public class DispatchFragment extends Fragment implements View.OnClickListener {
    private AbHttpUtil mAbHttpUtil;
    //  private BadgeView  badgeView1,badgeView2,badgeView3,badgeView4,badgeView5;
    private TextView TV_accept, TV_install, TV_send, TV_stop, TV_back,TV_exit;
    private AbRequestParams params;
    private FrameLayout frame1, frame2, frame3, frame4, frame5;
    private BigDecimal bigDecimal ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dispatchlayout, null);
        mAbHttpUtil = AbHttpUtil.getInstance(getActivity());
//        设置超时时间
        mAbHttpUtil.setTimeout(10000);
        params = new AbRequestParams();
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
//     初始化view
        initView(view);
//    设置view事件
        setView();
        return view;
    }
    private void setView() {
        TV_accept.setOnClickListener(this);
        TV_install.setOnClickListener(this);
        TV_send.setOnClickListener(this);
        TV_stop.setOnClickListener(this);
        TV_back.setOnClickListener(this);
        TV_exit.setOnClickListener(this);
    }
    private void initView(View view) {
        //对号
        frame1 = (FrameLayout) view.findViewById(R.id.frame0);
        frame2 = (FrameLayout) view.findViewById(R.id.frame1);
        frame3 = (FrameLayout) view.findViewById(R.id.frame2);
        frame4 = (FrameLayout) view.findViewById(R.id.frame3);
        frame5 = (FrameLayout) view.findViewById(R.id.frame4);

        TV_accept = (TextView) view.findViewById(R.id.TV_accept);
        TV_install = (TextView) view.findViewById(R.id.TV_install);
        TV_send = (TextView) view.findViewById(R.id.TV_send);
        TV_stop = (TextView) view.findViewById(R.id.TV_stop);
        TV_back = (TextView) view.findViewById(R.id.TV_back);
        TV_exit = (TextView) view.findViewById(R.id.TV_exit);
//        badgeView1 = new BadgeView(getActivity(),TV_accept);
//        badgeView2 = new BadgeView(getActivity(),TV_install);
//        badgeView3 = new BadgeView(getActivity(),TV_send);
//        badgeView4 = new BadgeView(getActivity(),TV_stop);
//        badgeView5 = new BadgeView(getActivity(),TV_back);
    }
    public void getData(AbRequestParams params, final int tag) {
        mAbHttpUtil.post(FinalURL.URL + "/CarSign", params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                CarSata catbean = AbJsonUtil.fromJson(s, CarSata.class);
                if (catbean.isSuc()) {
//                   判断显示与隐藏
                    switch (tag) {
                        case 0:
                            frame1.setVisibility(View.VISIBLE);
                            frame2.setVisibility(View.INVISIBLE);
                            frame3.setVisibility(View.INVISIBLE);
                            frame4.setVisibility(View.INVISIBLE);
                            frame5.setVisibility(View.INVISIBLE);
                            // 在Android进行通知处理，首先需要重系统哪里获得通知管理器NotificationManager，它是一个系统Service。
                            NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                            // Notification myNotify = new Notification(R.drawable.message,
                            // "自定义通知：您有新短信息了，请注意查收！", System.currentTimeMillis());
                            Notification myNotify = new Notification();
                            myNotify.icon = R.mipmap.ic_launcher;
                            myNotify.tickerText = "TickerText:您有新短消息，请注意查收！";
                            myNotify.when = System.currentTimeMillis();
                            myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除
                            myNotify.defaults=Notification.DEFAULT_SOUND;
                            myNotify.flags = Notification.FLAG_SHOW_LIGHTS;//开启led提示
                            RemoteViews rv = new RemoteViews(
                                    getContext().getPackageName(),
                                    R.layout.stop_notification);
                            myNotify.ledARGB= Color.parseColor("#00C3C5");
                            myNotify.ledOnMS=300;
                            myNotify.ledOffMS=300;
                            //  rv.setTextViewText(R.id.text_content, "hello wrold!");
                            myNotify.contentView = rv;
//                            Intent intent = new Intent();
//                            intent.setClass(getActivity(),getActivity().getClass());
//                            PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 1,intent, 0);
//                            myNotify.contentIntent = contentIntent;
                            manager.notify(1, myNotify);
                            break;
                        case 1:
                            frame1.setVisibility(View.INVISIBLE);
                            frame2.setVisibility(View.VISIBLE);
                            frame3.setVisibility(View.INVISIBLE);
                            frame4.setVisibility(View.INVISIBLE);
                            frame5.setVisibility(View.INVISIBLE);
                            // 在Android进行通知处理，首先需要重系统哪里获得通知管理器NotificationManager，它是一个系统Service。
                            manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                            // Notification myNotify = new Notification(R.drawable.message,
                            // "自定义通知：您有新短信息了，请注意查收！", System.currentTimeMillis());
                            myNotify = new Notification();
                            myNotify.icon = R.mipmap.ic_launcher;
                            myNotify.tickerText = "TickerText:您有新短消息，请注意查收！";
                            myNotify.when = System.currentTimeMillis();
                            myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除
                            myNotify.flags = Notification.FLAG_SHOW_LIGHTS;//开启led提示
                            myNotify.defaults=Notification.DEFAULT_SOUND;
                            rv = new RemoteViews(
                                    getContext().getPackageName(),
                                    R.layout.wrok_notification);
                            myNotify.ledARGB= Color.parseColor("#00C3C5");
                            myNotify.ledOnMS=300;
                            myNotify.ledOffMS=300;
                            //  rv.setTextViewText(R.id.text_content, "hello wrold!");
                            myNotify.contentView = rv;
//                            intent = new Intent();
//                            intent.setClass(getActivity(),getActivity().getClass());
//                             contentIntent = PendingIntent.getActivity(getActivity(), 1,intent, 0);
//                            myNotify.contentIntent = contentIntent;
                            manager.notify(1, myNotify);
                            break;
                        case 2:
                            frame1.setVisibility(View.INVISIBLE);
                            frame2.setVisibility(View.INVISIBLE);
                            frame3.setVisibility(View.VISIBLE);
                            frame4.setVisibility(View.INVISIBLE);
                            frame5.setVisibility(View.INVISIBLE);
                            break;
                        case 3:
                            frame1.setVisibility(View.INVISIBLE);
                            frame2.setVisibility(View.INVISIBLE);
                            frame3.setVisibility(View.INVISIBLE);
                            frame4.setVisibility(View.VISIBLE);
                            frame5.setVisibility(View.INVISIBLE);
                            break;
                        case 4:
                            frame1.setVisibility(View.INVISIBLE);
                            frame2.setVisibility(View.INVISIBLE);
                            frame3.setVisibility(View.INVISIBLE);
                            frame4.setVisibility(View.INVISIBLE);
                            frame5.setVisibility(View.VISIBLE);
                            break;
                    }
                    AbToastUtil.showToast(getActivity(), "已经修改物流状态");
                }


                String str = catbean.getData().toString();
                Log.i("1111111111", "onSuccess: " + str);
            }

            @Override
            public void onStart() {
                AbDialogUtil.showProgressDialog(getActivity(),-1,"正在验证您的身份信息");
            }

            @Override
            public void onFinish() {
                AbDialogUtil.removeDialog(getActivity());
            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                AbDialogUtil.removeDialog(getActivity());
                AbToastUtil.showToast(getActivity(), "网络不稳定，请重试~");
            }
        });

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TV_stop:
                params.put("State", "0");
                getData(params, 0);
//                badgeView4.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
//                badgeView4.setText("ok");
//                badgeView4.setTextColor(Color.RED);
//                badgeView1.setVisibility(View.GONE);
//                badgeView2.setVisibility(View.GONE);
//                badgeView3.setVisibility(View.GONE);
//                badgeView4.setVisibility(View.VISIBLE);
//                badgeView5.setVisibility(View.GONE);
                break;


            case R.id.TV_accept:
                params.put("State", "1");
                getData(params, 1);
//                badgeView1.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
//                badgeView1.setText("ok");
//                badgeView1.setTextColor(Color.RED);
//                badgeView1.setVisibility(View.VISIBLE);
//                badgeView2.setVisibility(View.GONE);
//                badgeView3.setVisibility(View.GONE);
//                badgeView4.setVisibility(View.GONE);
//                badgeView5.setVisibility(View.GONE);
                break;
            case R.id.TV_install:
                params.put("State", "2");
                getData(params, 2);
//                badgeView2.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
//                badgeView2.setText("ok");
//                badgeView2.setTextColor(Color.RED);
//                badgeView1.setVisibility(View.GONE);
//                badgeView2.setVisibility(View.VISIBLE);
//                badgeView3.setVisibility(View.GONE);
//                badgeView4.setVisibility(View.GONE);
//                badgeView5.setVisibility(View.GONE);
                break;
            case R.id.TV_send:
                params.put("State", "3");
                getData(params, 3);
//                badgeView3.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
//                badgeView3.setText("ok");
//                badgeView3.setTextColor(Color.RED);
//                badgeView2.setTextColor(Color.RED);
//                badgeView1.setVisibility(View.GONE);
//                badgeView2.setVisibility(View.GONE);
//                badgeView3.setVisibility(View.VISIBLE);
//                badgeView4.setVisibility(View.GONE);
//                badgeView5.setVisibility(View.GONE);
                break;
            case R.id.TV_back:
                params.put("State", "4");
                getData(params, 4);
//                badgeView5.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
//                badgeView5.setText("ok");
//                badgeView5.setTextColor(Color.RED);
//                badgeView1.setVisibility(View.GONE);
//                badgeView2.setVisibility(View.GONE);
//                badgeView3.setVisibility(View.GONE);
//                badgeView4.setVisibility(View.GONE);
//                badgeView5.setVisibility(View.VISIBLE);
                break;

        }

    }


}
