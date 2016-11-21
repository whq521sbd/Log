package com.auto.logistics.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.auto.logistics.R;

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
        setview();
    }


    /**
     *  设置控件
     */
    private void setview() {
        RL_guidelayout.setAnimation(animation);
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