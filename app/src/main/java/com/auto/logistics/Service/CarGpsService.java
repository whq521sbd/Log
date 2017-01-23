package com.auto.logistics.Service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbMd5;
import com.ab.util.AbToastUtil;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

/**
 * Created by Administrator on 2016/12/10.
 */
public class CarGpsService extends Service {
    // 位置
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location ;
    private String oldlocation = "";
    private String contextService = Context.LOCATION_SERVICE;
    private String provider;
    private double lat;
    private double lon;
    private AbHttpUtil httpUtil;
    private AbRequestParams params;
    private double Longitude;
    private double Latitude;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10086:
                    UpdataLoction();
                    Log.d("CarGpsService", "GPS坐标已上传");
                    break;
            }
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CarGpsService", "GPS服务已开启！ ");
        httpUtil = AbHttpUtil.getInstance(getApplicationContext());
        params = new AbRequestParams();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(60 * 1000);
                        Message msg = new Message();
                        msg.what = 10086;
                        handler.sendMessage(msg);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void UpdataLoction() {
//        每次上传之前判断GPS是否打开
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        //locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager = (LocationManager) getSystemService(contextService);
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);// 高精度
            criteria.setAltitudeRequired(false);// 不要求海拔
            criteria.setBearingRequired(false);// 不要求方位
            criteria.setCostAllowed(true);// 允许有花费
            criteria.setPowerRequirement(Criteria.POWER_LOW);// 低功耗
            // 从可用的位置提供器中，匹配以上标准的最佳提供器
            provider = locationManager.getBestProvider(criteria, true);
            // 获得最后一次变化的位置
            location = locationManager.getLastKnownLocation(provider);
            Longitude = location.getLongitude();//经度
            Latitude = location.getLatitude();//纬度


            String  LongLat =  String.valueOf(Longitude)+String.valueOf(Latitude);
            String md5location = AbMd5.MD5(LongLat);
            Log.d("CarGpsService", "UpdataLoction: " + md5location);

            if (!md5location.equals(oldlocation)){

                params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getApplication(), "Token"));
                params.put("Lng", String.valueOf(Longitude));
                params.put("Lat", String.valueOf(Latitude));

                httpUtil.post(FinalURL.URL + "/CarGPS", params, new AbStringHttpResponseListener() {
                    @Override
                    public void onSuccess(int i, String s) {
                        if (s!=null&&!s.equals("")){
//                            {"Suc":true,"Msg":"OK","Data":{}}
                            Log.i("CarGpsService", "onSuccess: "+s);

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

            oldlocation = md5location;


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("提示：");
            builder.setMessage("请打开GPS开关！");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("取消", null);
            final AlertDialog Alert = builder.create();
            Alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            Alert.show();


        }

    }


}
