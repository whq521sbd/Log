package com.auto.logistics.Fragment;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.icu.math.BigDecimal;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbJsonUtil;
import com.ab.util.AbToastUtil;
import com.auto.logistics.Activity.FixCar;
import com.auto.logistics.JavaBean.CarSata;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by Administrator on 2016/10/21.
 */
public class DispatchFragment extends Fragment implements View.OnClickListener {
    private AbHttpUtil mAbHttpUtil;
    private TextView TV_accept, TV_install, TV_send, TV_stop, TV_back, TV_eating;
    private AbRequestParams params;
    private FrameLayout frame1, frame2, frame3, frame4, frame5, frame6, frame8;
    private BigDecimal bigDecimal;
    private TextView TV_fix, TV_blockup;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private List<FrameLayout> framelists;
    private AbRequestParams paramsCar;
    private int fragmentState = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dispatchlayout, null);
        mAbHttpUtil = AbHttpUtil.getInstance(getActivity());
//        设置超时时间
        mAbHttpUtil.setTimeout(10000);
        params = new AbRequestParams();
        paramsCar = new AbRequestParams();
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        framelists = new ArrayList<FrameLayout>();


//     初始化view
        initView(view);
//    设置view事件
        setView();

        if (savedInstanceState != null) {
            switch (savedInstanceState.getInt("fragmentState", -1)) {
                case 0:
                    frame1.setVisibility(View.VISIBLE);
                    frame2.setVisibility(View.INVISIBLE);
                    frame3.setVisibility(View.INVISIBLE);
                    frame4.setVisibility(View.INVISIBLE);
                    frame5.setVisibility(View.INVISIBLE);
                    frame6.setVisibility(View.INVISIBLE);
                    notifymethod("暂停接单啦！", "师傅辛苦了~", "您有新的物流信息！");
                    alphaAnmation(frame1);

                    break;
                case 1:
                    frame1.setVisibility(View.INVISIBLE);
                    frame2.setVisibility(View.VISIBLE);
                    frame3.setVisibility(View.INVISIBLE);
                    frame4.setVisibility(View.INVISIBLE);
                    frame5.setVisibility(View.INVISIBLE);
                    frame6.setVisibility(View.INVISIBLE);
                    notifymethod("开始接单啦！", "开车请小心~", "您有新的物流信息！");
                    alphaAnmation(frame2);
                    break;
                case 2:
                    frame1.setVisibility(View.INVISIBLE);
                    frame2.setVisibility(View.INVISIBLE);
                    frame3.setVisibility(View.VISIBLE);
                    frame4.setVisibility(View.INVISIBLE);
                    frame5.setVisibility(View.INVISIBLE);
                    frame6.setVisibility(View.INVISIBLE);
                    alphaAnmation(frame3);
                    break;
                case 3:
                    frame1.setVisibility(View.INVISIBLE);
                    frame2.setVisibility(View.INVISIBLE);
                    frame3.setVisibility(View.INVISIBLE);
                    frame4.setVisibility(View.VISIBLE);
                    frame5.setVisibility(View.INVISIBLE);
                    frame6.setVisibility(View.INVISIBLE);
                    alphaAnmation(frame4);
                    break;
                case 4:
                    frame1.setVisibility(View.INVISIBLE);
                    frame2.setVisibility(View.INVISIBLE);
                    frame3.setVisibility(View.INVISIBLE);
                    frame4.setVisibility(View.INVISIBLE);
                    frame5.setVisibility(View.VISIBLE);
                    frame6.setVisibility(View.INVISIBLE);
                    alphaAnmation(frame5);
                    break;
                case 5:
                    frame1.setVisibility(View.INVISIBLE);
                    frame2.setVisibility(View.INVISIBLE);
                    frame3.setVisibility(View.INVISIBLE);
                    frame4.setVisibility(View.INVISIBLE);
                    frame5.setVisibility(View.INVISIBLE);
                    frame6.setVisibility(View.VISIBLE);
                    alphaAnmation(frame6);
                    break;
                case 7:
                    frame1.setVisibility(View.INVISIBLE);
                    frame2.setVisibility(View.INVISIBLE);
                    frame3.setVisibility(View.INVISIBLE);
                    frame4.setVisibility(View.INVISIBLE);
                    frame5.setVisibility(View.INVISIBLE);
                    frame6.setVisibility(View.INVISIBLE);
                    frame8.setVisibility(View.VISIBLE);
                    alphaAnmation(frame8);
                    break;
            }

        }
        return view;
    }

    //    设置控件
    private void setView() {
        TV_accept.setOnClickListener(this);
        TV_install.setOnClickListener(this);
        TV_send.setOnClickListener(this);
        TV_stop.setOnClickListener(this);
        TV_back.setOnClickListener(this);
        TV_eating.setOnClickListener(this);
        TV_fix.setOnClickListener(this);
        TV_blockup.setOnClickListener(this);
    }

    //    初始化控件
    private void initView(View view) {
        //对号
        frame1 = (FrameLayout) view.findViewById(R.id.frame0);
        frame2 = (FrameLayout) view.findViewById(R.id.frame1);
        frame3 = (FrameLayout) view.findViewById(R.id.frame2);
        frame4 = (FrameLayout) view.findViewById(R.id.frame3);
        frame5 = (FrameLayout) view.findViewById(R.id.frame4);
        frame6 = (FrameLayout) view.findViewById(R.id.frame5);
        frame8 = (FrameLayout) view.findViewById(R.id.frame7);
        TV_accept = (TextView) view.findViewById(R.id.TV_accept);
        TV_install = (TextView) view.findViewById(R.id.TV_install);
        TV_send = (TextView) view.findViewById(R.id.TV_send);
        TV_stop = (TextView) view.findViewById(R.id.TV_stop);
        TV_back = (TextView) view.findViewById(R.id.TV_back);
        TV_eating = (TextView) view.findViewById(R.id.TV_eating);
        TV_fix = (TextView) view.findViewById(R.id.TV_fix);
        TV_blockup = (TextView) view.findViewById(R.id.TV_blockup);
        framelists.add(frame1);
        framelists.add(frame2);
        framelists.add(frame3);
        framelists.add(frame4);
        framelists.add(frame5);

    }


    /**
     * 改变物流状态，设置动画效果
     *
     * @param params
     * @param tag    Frame的id
     */
    public void getData(AbRequestParams params, final int tag) {
        mAbHttpUtil.post(FinalURL.URL + "/CarSign", params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                if (s != null && !s.equals("")) {

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
                                frame6.setVisibility(View.INVISIBLE);
                                notifymethod("暂停接单啦！", "师傅辛苦了~", "您有新的物流信息！");
                                alphaAnmation(frame1);

                                break;
                            case 1:
                                frame1.setVisibility(View.INVISIBLE);
                                frame2.setVisibility(View.VISIBLE);
                                frame3.setVisibility(View.INVISIBLE);
                                frame4.setVisibility(View.INVISIBLE);
                                frame5.setVisibility(View.INVISIBLE);
                                frame6.setVisibility(View.INVISIBLE);
                                notifymethod("开始接单啦！", "开车请小心~", "您有新的物流信息！");
                                alphaAnmation(frame2);
                                break;
                            case 2:
                                frame1.setVisibility(View.INVISIBLE);
                                frame2.setVisibility(View.INVISIBLE);
                                frame3.setVisibility(View.VISIBLE);
                                frame4.setVisibility(View.INVISIBLE);
                                frame5.setVisibility(View.INVISIBLE);
                                frame6.setVisibility(View.INVISIBLE);
                                alphaAnmation(frame3);
                                break;
                            case 3:
                                frame1.setVisibility(View.INVISIBLE);
                                frame2.setVisibility(View.INVISIBLE);
                                frame3.setVisibility(View.INVISIBLE);
                                frame4.setVisibility(View.VISIBLE);
                                frame5.setVisibility(View.INVISIBLE);
                                frame6.setVisibility(View.INVISIBLE);
                                alphaAnmation(frame4);
                                break;
                            case 4:
                                frame1.setVisibility(View.INVISIBLE);
                                frame2.setVisibility(View.INVISIBLE);
                                frame3.setVisibility(View.INVISIBLE);
                                frame4.setVisibility(View.INVISIBLE);
                                frame5.setVisibility(View.VISIBLE);
                                frame6.setVisibility(View.INVISIBLE);
                                alphaAnmation(frame5);
                                break;
                            case 5:
                                frame1.setVisibility(View.INVISIBLE);
                                frame2.setVisibility(View.INVISIBLE);
                                frame3.setVisibility(View.INVISIBLE);
                                frame4.setVisibility(View.INVISIBLE);
                                frame5.setVisibility(View.INVISIBLE);
                                frame6.setVisibility(View.VISIBLE);
                                alphaAnmation(frame6);
                                break;
                            case 7:
                                frame1.setVisibility(View.INVISIBLE);
                                frame2.setVisibility(View.INVISIBLE);
                                frame3.setVisibility(View.INVISIBLE);
                                frame4.setVisibility(View.INVISIBLE);
                                frame5.setVisibility(View.INVISIBLE);
                                frame6.setVisibility(View.INVISIBLE);
                                frame8.setVisibility(View.VISIBLE);
                                alphaAnmation(frame8);
                                break;
                        }
                        AbToastUtil.showToast(getActivity(), "已经修改物流状态");
                    }
                    String str = catbean.getData().toString();
                    Log.i("1111111111", "onSuccess: " + str);
                }
            }

            @Override
            public void onStart() {
                AbDialogUtil.showProgressDialog(getActivity(), -1, "正在修改状态");
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

    /*
    * 动画效果
    * */
    private void alphaAnmation(FrameLayout frame) {
//        透明度动画
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(500);
        animation.setRepeatCount(3);
        animation.setRepeatMode(Animation.REVERSE);
        frame.setAnimation(animation);
        animation.startNow();
    }


    private void notifymethod(String title, String content, String ticker) {
        mNotificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getActivity());
        mBuilder.setContentTitle(title)//设置通知栏标题
                .setContentText(content) //设置通知栏显示内容
                .setAutoCancel(true)//自动清空 打开
                //.setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图
                //  .setNumber(number) //设置通知集合的数量
                .setTicker(ticker) //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                //  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(R.mipmap.logsimg)//设置通知小ICON
                .setLights(Color.parseColor("#00C3C5"), 2000, 5000);
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_SHOW_LIGHTS;              //三色灯提醒，在使用三色灯提醒时候必须加该标志符
        // notification.flags = Notification.FLAG_ONGOING_EVENT;          //发起正在运行事件（活动中）
        //notification.flags = Notification.FLAG_INSISTENT;   //让声音、振动无限循环，直到用户响应 （取消或者打开）
        notification.flags = Notification.FLAG_ONLY_ALERT_ONCE;  //发起Notification后，铃声和震动均只执行一次
        notification.flags = Notification.FLAG_AUTO_CANCEL;      //用户单击通知后自动消失
        //notification.flags = Notification.FLAG_NO_CLEAR;          //只有全部清除时，Notification才会清除 ，不清楚该通知(QQ的通知无法清除，就是用的这个)
        // notification.flags = Notification.FLAG_FOREGROUND_SERVICE;    //表示正在运行的服务
        mNotificationManager.notify(1, mBuilder.build());

    }

    /*
    *
    *点击事件
    * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TV_stop://停止接单
                params.put("State", "0");
                getData(params, 0);
                paramsCar.put("State", "N");
                getData(params, 0);
                fragmentState = 0;
                break;
            case R.id.TV_accept://开始接单
                params.put("State", "1");
                getData(params, 1);
                paramsCar.put("State", "N");
                getData(params, 1);
                fragmentState = 1;
                break;
            case R.id.TV_install://正在装车
                params.put("State", "2");
                getData(params, 2);
                paramsCar.put("State", "N");
                getData(params, 2);
                fragmentState = 2;
                break;
            case R.id.TV_send://准备运送
                params.put("State", "3");
                getData(params, 3);
                paramsCar.put("State", "N");
                getData(params, 3);
                fragmentState = 3;
                break;
            case R.id.TV_back://运送返回
                params.put("State", "4");
                getData(params, 4);
                paramsCar.put("State", "N");
                getData(params, 4);
                fragmentState = 4;
                break;
            case R.id.TV_eating:
                params.put("State", "5");//吃饭休息
                getData(params, 5);
                paramsCar.put("State", "S");//吃饭休息是改变车辆状态为暂停
                getData(params, 5);
                fragmentState = 5;
                break;
            case R.id.TV_fix:
                startActivity(new Intent(getActivity(), FixCar.class));
                break;

            case R.id.TV_blockup:
                params.put("State", "E");
                getData(params, 7);
                fragmentState = 7;
                break;


        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fragmentState", fragmentState);

    }
}
