package com.auto.logistics.Activity;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.ab.activity.AbActivity;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbMd5;
import com.ab.util.AbToastUtil;
import com.auto.logistics.R;

import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.NetworkUtils;

import java.io.File;
import java.util.List;


import com.auto.logistics.Utills.SharedPreferencesSava;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/20.
 */

public class GuideActivity extends AbActivity {
    private Animation animation;
    private RelativeLayout RL_guidelayout;
    private  static  final  int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE=1;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private AbHttpUtil httpUtil;
    private AbRequestParams params = new AbRequestParams();
    private String OldMd5Lacation ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.guidelayout);
        httpUtil = AbHttpUtil.getInstance(GuideActivity.this);
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );    //注册监听函数
        initLocation();
//        百度定位sdk 开启
          mLocationClient.start();

        if (!NetworkUtils.isGpsOPen(GuideActivity.this)){
           goToMain("GPS开关还没有打开，点击确定去打开吧~");
            return;
        }

        if (!NetworkUtils.isNetworkAvailable(this)){
            goToMain("没有开启网络连接哦，点击确定去打开吧~");
            return;
        }

        animation = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.fade_in);
        initview();
        setview();
    }




    //    初始化百度定位sdk
private void initLocation(){
    LocationClientOption option = new LocationClientOption();
    option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
    );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
    option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
    int span=1000*60*3;
    option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
    option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
    option.setOpenGps(true);//可选，默认false,设置是否使用gps
    option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
    option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
    option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
    option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
    option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
    option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
    mLocationClient.setLocOption(option);
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


    //返回键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }





// 百度sdk监听
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
/*
* **********************************************************
* 获取经纬度，并上传
* **********************************************************
* */
            double Longitude = location.getLongitude();

            double Latitude = location.getLatitude();
//            语义化位置
            String LocationDescribe = location.getLocationDescribe();
//            将double 装换成String
            String Lng= String.valueOf(Longitude);
            String Lat = String.valueOf(Latitude);

            String Md5Loacation = AbMd5.MD5(new StringBuffer().append(Lng+Lat).toString());

            if (!Md5Loacation.equals(OldMd5Lacation)){
                //          上传车辆GPS坐标
                UpdataLoction(Longitude,Latitude,LocationDescribe);
            }

            OldMd5Lacation = Md5Loacation;

        }

        private void UpdataLoction(double Longitude,double Latitude,final String LocationDescribe) {
            params.put("Token", SharedPreferencesSava.getInstance().getStringValue(GuideActivity.this,"Token"));
            params.put("Lng",Longitude+"");
            params.put("Lat",Latitude+"");
            httpUtil.post(FinalURL.URL + "/CarGPS", params, new AbStringHttpResponseListener() {
                @Override
                public void onSuccess(int i, String s) {
                    if (s!=null&&!s.equals("")){
//                            {"Suc":true,"Msg":"OK","Data":{}}
                        Log.i("CarGpsService", "onSuccess: "+s);

                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getBoolean("Suc")) {

                                AbToastUtil.showToast(GuideActivity.this,"您现在的位置在："+LocationDescribe);

                            } else {
                                AbToastUtil.showToast(GuideActivity.this,object.getString("Msg"));
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFinish() {

                }

                @Override
                public void onFailure(int i, String s, Throwable throwable) {
                    AbToastUtil.showToast(getApplicationContext(),"无法连接服务器，上传GPS坐标失败！");
                }
            });


        }
    }




}



