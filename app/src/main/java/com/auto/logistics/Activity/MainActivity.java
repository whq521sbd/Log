package com.auto.logistics.Activity;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.transition.Transition;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.fragment.AbAlertDialogFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.Adapter.MessageAdapter;
import com.auto.logistics.Fragment.DispatchFragment;
import com.auto.logistics.Fragment.MessageCenterFragment;
import com.auto.logistics.Fragment.MineInfoFragment;
import com.auto.logistics.Fragment.TransListFragment;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Service.CarGpsService;
import com.auto.logistics.Service.DemoIntentService;
import com.auto.logistics.Service.DemoPushService;
import com.auto.logistics.Utills.SharedPreferencesSava;
import com.igexin.sdk.PushManager;
import com.readystatesoftware.viewbadger.BadgeView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;




public class MainActivity extends AbActivity {
    private FragmentManager fm;
    //返回键相关参数
    private static Boolean isQuit = false;
    private long mExitTime = 0;
    Timer timer = new Timer();
    private AbRequestParams params;
    private AbHttpUtil mAbHttpUtil;
    private List<LogTaskBean.DataBean.LogsBean> listlogs;
    private MessageAdapter messageAdapter;
    private static Activity instance;
    private static BadgeView badgeView;
    private  MessageCenterFragment messageCenterFragment;
    private  TransListFragment transListFragment;
    private  MineInfoFragment MineInfoFragment;
    private DispatchFragment dispatchFragment;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    /*
    * 初始化控件
    * */
    @AbIocView(id = R.id.TV_Message, click = "click")
    TextView TV_Message;
    @AbIocView(id = R.id.TV_list, click = "click")
    TextView TV_list;
    @AbIocView(id = R.id.TV_mydispatching, click = "click")
    TextView TV_mydispatching;
    @AbIocView(id = R.id.TV_mineinfo, click = "click")
    TextView TV_mineinfo;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.activity_main);

        // 绑定别名
        PushManager.getInstance().bindAlias(this, SharedPreferencesSava.getInstance().getStringValue(this,"username"));

        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);

        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);

        String name =  SharedPreferencesSava.getInstance().getStringValue(this,"username");

        //        注册eventbus:
        EventBus.getDefault().register(this);
       // badgeView = new BadgeView(MainActivity.this, TV_Message);
//        开启Gps上传服务
       // startService(new Intent(MainActivity.this,CarGpsService.class));
        params = new AbRequestParams();
        mAbHttpUtil = AbHttpUtil.getInstance(this);
        //     首先过得fragment管理器
        fm = getSupportFragmentManager();

        if (savedInstanceState != null) {
            messageCenterFragment = (MessageCenterFragment) fm.findFragmentByTag("messageCenterFragment");
            transListFragment = (TransListFragment) fm.findFragmentByTag("transListFragment");
            dispatchFragment = (DispatchFragment) fm.findFragmentByTag("dispatchFragment");
            MineInfoFragment = (com.auto.logistics.Fragment.MineInfoFragment) fm.findFragmentByTag("MineInfoFragment");
        }


//      默认显示的第一个页面
        MessageCenterFragment messageCenterFragment = new MessageCenterFragment();
        fm.beginTransaction()
                .replace(R.id.RL_container, messageCenterFragment)
                .commit();
        TV_Message.setTextColor(Color.parseColor("#00C3C5"));
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }

    // 解除注册eventbus
    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }


//   eventbus 订阅者
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(String message){
//       if (message.equals("show")){
//           badgeView.setText("new");
//           badgeView.setTextSize(8.0f);
//           badgeView.show();
//       }else if (message.equals("hide")){
//           badgeView.hide();
//       }else {
           notifymethod("请开往就绪区", "请将车辆开往就绪区，停车位为："+message.toString(), "您有新的物流信息！");
           new AlertDialog.Builder(this)
                   .setTitle("就绪提示：")
                   .setMessage("请将车辆开往就绪区，车位号为："+message.toString()+",如果没有订单信息请刷新列表！")
                   .setPositiveButton("确定",null)
                   .show();

    //   }
    }

    private void notifymethod(String title, String content, String ticker) {
        mNotificationManager = (NotificationManager) MainActivity.this.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(MainActivity.this);
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
            * Andrroid 6.0 系统，进去页面之后判断WRITE_EXTERNAL_STORAGE，是否打开
            *
            * 因为 STORAGE，是危险权限组，所以如果WRITE_EXTERNAL_STORAGE打开之后
            *
            * 则READ_EXTERNAL_STORAGE权限会自动被系统授权，不需重复判断
            *
            * */
    private void judgePermission() {
//        如果mainfast里面没有WRITE_EXTERNAL_STORAGE权限，则提示用户授权
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
//            toast长时间提醒
            Toast.makeText(MainActivity.this, "此权限为重要权限，请允许授权!", Toast.LENGTH_LONG).show();
//           系统自动调用授权窗口
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }

    }

    /*
    *
    * 授权回调
    *
    * */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//      自定义授权码MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                AbToastUtil.showToast(MainActivity.this, "授权成功！");
                //   callPhone();
            } else {
                // Permission Denied
                //Toast.makeText(MainActivity.this, "授权被拒！", Toast.LENGTH_SHORT).show();

                AbDialogUtil.showAlertDialog(MainActivity.this, "权限提示", "此权限为重要权限，请允许!", new AbAlertDialogFragment.AbDialogOnClickListener() {
                    @Override
                    public void onPositiveClick() {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }

                    @Override
                    public void onNegativeClick() {
                        AbDialogUtil.removeDialog(MainActivity.this);
                    }
                });

            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    /**
     *
     * //图片的下载
     * AbImageLoader mAbImageLoader = AbImageLoader.newInstance(this);
     * mAbImageLoader.setLoadingImage(R.drawable.image_loading);
     * mAbImageLoader.setErrorImage(R.drawable.image_error);
     * mAbImageLoader.setEmptyImage(R.drawable.image_empty);
     *          
     * //原图片的下载
     * mAbImageLoader.setMaxWidth(0);
     * mAbImageLoader.setMaxHeight(0);
     * mAbImageLoader.display(scaleView,imageUrl);
     *          
     * //缩放图片的下载（保持宽高比，计算缩放比例，使一个方向缩放后，另一方向不小与显示的大小的最合适比例）
     * mAbImageLoader.setMaxWidth(150);
     * mAbImageLoader.setMaxHeight(150);
     * mAbImageLoader.display(scaleView2,imageUrl2);
     *          
     * //放大图片的下载
     * mAbImageLoader.setMaxWidth(180);
     * mAbImageLoader.setMaxHeight(180);
     * mAbImageLoader.display(scaleView3,imageUrl3);
     *
     */



    /**
     * @param v 点击事件
     *
     * 点击后动态替换fragment并设置文字选中状态
     *
     */
    public void click(View v) {
        switch (v.getId()) {
            case R.id.TV_mineinfo:
                TV_mineinfo.setTextColor(Color.parseColor("#00C3C5"));
                TV_Message.setTextColor(Color.parseColor("#45494A"));
                TV_list.setTextColor(Color.parseColor("#45494A"));
                TV_mydispatching.setTextColor(Color.parseColor("#45494A"));
                MineInfoFragment= new MineInfoFragment();
                fm.beginTransaction()
                        .replace(R.id.RL_container, MineInfoFragment)
                        .commit();
                break;

            case R.id.TV_Message:
                TV_Message.setTextColor(Color.parseColor("#00C3C5"));
                TV_list.setTextColor(Color.parseColor("#45494A"));
                TV_mydispatching.setTextColor(Color.parseColor("#45494A"));
                TV_mineinfo.setTextColor(Color.parseColor("#45494A"));
                messageCenterFragment= new MessageCenterFragment();
               fm.beginTransaction()
                        .replace(R.id.RL_container, messageCenterFragment)
                        .commit();
                //AbToastUtil.showToast(MainActivity.this, "已经获取焦点1");
                break;

            case R.id.TV_list:
                TV_list.setTextColor(Color.parseColor("#00C3C5"));
                TV_Message.setTextColor(Color.parseColor("#45494A"));
                TV_mydispatching.setTextColor(Color.parseColor("#45494A"));
                TV_mineinfo.setTextColor(Color.parseColor("#45494A"));
                transListFragment  = new TransListFragment();
                fm.beginTransaction()
                        .replace(R.id.RL_container, transListFragment)
                        .commit();
                //AbToastUtil.showToast(MainActivity.this, "已经获取焦点2");
                break;
            case R.id.TV_mydispatching:
                TV_mydispatching.setTextColor(Color.parseColor("#00C3C5"));
                TV_list.setTextColor(Color.parseColor("#45494A"));
                TV_Message.setTextColor(Color.parseColor("#45494A"));
                TV_mineinfo.setTextColor(Color.parseColor("#45494A"));
                dispatchFragment = new DispatchFragment();
                fm.beginTransaction()
                        .replace(R.id.RL_container, dispatchFragment)
                        .commit();
                break;
        }
    }



    /**
     * @param keyCode
     * @param event   返回键监听
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isQuit == false) {
                isQuit = true;
                AbToastUtil.showToast(this, "再按一次返回键退出程序哦~");
                TimerTask task = null;
                task = new TimerTask() {
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }

    /**
     * Android 6.0权限问题
     */
    @Override
    protected void onStart() {
        super.onStart();
//        判断权限
        judgePermission();
    }


}
