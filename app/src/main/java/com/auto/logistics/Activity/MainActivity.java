package com.auto.logistics.Activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.activity_main);
//     首先过得fragment管理器
        fm = getSupportFragmentManager();
//      默认显示的第一个页面
        MessageCenterFragment messageCenterFragment = new MessageCenterFragment();
        fm.beginTransaction()
                .replace(R.id.RL_container, messageCenterFragment)
                .commit();
        TV_Message.setTextColor(Color.parseColor("#00C3C5"));


        params = new AbRequestParams();
        mAbHttpUtil = AbHttpUtil.getInstance(this);
        // getData();

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
//           系统自动条用授权窗口
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
     */


    public void click(View v) {
        switch (v.getId()) {
            case R.id.TV_mineinfo:
                TV_mineinfo.setTextColor(Color.parseColor("#00C3C5"));
                TV_Message.setTextColor(Color.parseColor("#45494A"));
                TV_list.setTextColor(Color.parseColor("#45494A"));
                TV_mydispatching.setTextColor(Color.parseColor("#45494A"));
                MineInfoFragment MineInfoFragment = new MineInfoFragment();
                fm.beginTransaction()
                        .replace(R.id.RL_container, MineInfoFragment)
                        .commit();
                break;
            case R.id.TV_Message:
                TV_Message.setTextColor(Color.parseColor("#00C3C5"));
                TV_list.setTextColor(Color.parseColor("#45494A"));
                TV_mydispatching.setTextColor(Color.parseColor("#45494A"));
                TV_mineinfo.setTextColor(Color.parseColor("#45494A"));
                MessageCenterFragment messageCenterFragment = new MessageCenterFragment();
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
                TransListFragment transListFragment = new TransListFragment();
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
                DispatchFragment dispatchFragment = new DispatchFragment();
                fm.beginTransaction()
                        .replace(R.id.RL_container, dispatchFragment)
                        .commit();
                break;
        }
    }


    //返回键监听
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

    /*
    * Android 6.0 权限问题
    *
    * */
    @Override
    protected void onStart() {
//        判断权限
        judgePermission();
        super.onStart();
    }


}
