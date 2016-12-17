package com.auto.logistics.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.ab.activity.AbActivity;
import com.auto.logistics.R;
import com.auto.logistics.Service.CarGpsService;
import com.auto.logistics.Utills.NetworkUtils;

import java.io.File;

/**
 * Created by Administrator on 2016/10/20.
 */

public class GuideActivity extends AbActivity {
    private Animation animation;
    private RelativeLayout RL_guidelayout;
    private  static  final  int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.guidelayout);
        animation = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.fade_in);
        initview();

        if (!NetworkUtils.isGpsEnabled(GuideActivity.this)){
            goToMain("GPS开关还没有打开，点击确定去打开吧~");
            return;
        }

        if (!NetworkUtils.isNetworkAvailable(this)){
              goToMain("没有开启网络连接哦，点击确定去打开吧~");
            return;
        }

        setview();

    }


    /*
    * 此方法是判断网络是否打开(wifi、移动蜂窝)
    * */
    private void goToMain(String Msg) {
            // 如果网络不可用，则弹出对话框，对网络进行设置
            AlertDialog.Builder builder = new AlertDialog.Builder(GuideActivity.this)
                    .setTitle("网络提醒")
                    .setMessage(Msg)
                    .setPositiveButton("确定",
                            new android.content.DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        String sdkVersion = android.os.Build.VERSION.SDK;
                                        if (Integer.valueOf(sdkVersion) > 10) {
                                            startActivity(new Intent(
                                                    android.provider.Settings.ACTION_SETTINGS));
                                        } else {
                                            startActivity(new Intent(
                                                    android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                                        }
                                        dialog.cancel();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                    .setNegativeButton("取消", null);
            builder.create().show();

        super.onStart();
    }

    /**
     *  设置控件
     */
    private void setview() {
        //动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

    }

    /**
     * 初始化控件
     */
    private void initview() {
        RL_guidelayout = (RelativeLayout) findViewById(R.id.RL_guidelayout);
        RL_guidelayout.setAnimation(animation);
        //  创建新文件夹
        File file = new File(Environment.getExternalStorageDirectory() + "/BJDLogistics");
        if (!file.exists()) {
            file.mkdirs();//不存在就建一个
        }
    }

}



//        if (ContextCompat.checkSelfPermission(GuideActivity.this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED)
//        {
//
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//        }



//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
//    {
//
//        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
//        {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//             //   callPhone();
//            } else
//            {
//                // Permission Denied
//                Toast.makeText(GuideActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
//            }
//            return;
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }