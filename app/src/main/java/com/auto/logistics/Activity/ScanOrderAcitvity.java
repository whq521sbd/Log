package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbJsonUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.JavaBean.TaskInfo;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScanOrderAcitvity extends AbActivity {

    private AbHttpUtil httpUtil;
    private AbRequestParams params;
    private TaskInfo taskInfo;

    //初始化 控件，并绑定点击事件
    @AbIocView(id = R.id.order_goback, click = "click")
    ImageView order_goback;
    @AbIocView(id = R.id.tv_confirm, click = "click")
    TextView tv_confirm;
    @AbIocView(id =  R.id.tv_ordReturn,click = "click")
    TextView tv_ordReturn;
    //相关展示区域控件信息
    @AbIocView(id = R.id.TV_TaskNum)
    TextView TV_TaskNum;
    @AbIocView(id = R.id.TV_Serial)
    TextView TV_Serial;
    @AbIocView(id = R.id.TV_TransNum)
    TextView TV_TransNum;
    @AbIocView(id = R.id.TV_Area)
    TextView TV_Area;
    @AbIocView(id = R.id.TV_GoodsTitle)
    TextView TV_GoodsTitle;
    @AbIocView(id = R.id.TV_Weight)
    TextView TV_Weight;
    @AbIocView(id = R.id.TV_RecPerson)
    TextView TV_RecPerson;
    @AbIocView(id = R.id.TV_RecTel)
    TextView TV_RecTel;
    @AbIocView(id = R.id.TV_RecAddr)
    TextView TV_RecAddr;
    @AbIocView(id = R.id.TV_AccTime)
    TextView TV_AccTime;
    @AbIocView(id = R.id.TV_Booth)
    TextView TV_Booth;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.scanorderlayout);
        params =new AbRequestParams();
        httpUtil=AbHttpUtil.getInstance(ScanOrderAcitvity.this);
        intent = getIntent();
       //Bundle bundle = intent.getExtras();
        taskInfo = (TaskInfo) intent.getSerializableExtra("taskInfo");
        Log.d("ScanOrderAcitvity", "onCreate: "+taskInfo);
        setView(taskInfo);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }



    public void click(View view){
        switch (view.getId()){
            case R.id.order_goback:
                finish();
                break;
            case R.id.tv_ordReturn:
                finish();
                break;
            case R.id.tv_confirm:
                commitData();
        }

    }

    private void commitData() {
        params.put("Token",SharedPreferencesSava.getInstance().getStringValue(ScanOrderAcitvity.this,"Token"));
        params.put("TaskNum",taskInfo.getData().getLogTask_TaskNum());
        params.put("state",3);
        httpUtil.post(FinalURL.URL + "/LogTaskOper", params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                Log.d("ScanOrderAcitvity", "onSuccess: "+s);
                if (s!=null&&!s.equals("")){
                    try {
                        JSONObject object =new JSONObject(s);
                        if (object.getBoolean("Suc")){
                            intent.setClass(ScanOrderAcitvity.this,ScanInstall.class);
                            intent.putExtra("taskInfo",taskInfo);
                            startActivity(intent);
                            finish();
                        }else {
                            AbToastUtil.showToast(ScanOrderAcitvity.this,object.getString("Msg"));
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
                AbToastUtil.showToast(ScanOrderAcitvity.this,"连接网络失败！");
            }
        });


    }


    //   绑定控件
    private void setView(TaskInfo taskInfo) {
        TV_Booth.setText(taskInfo.getData().getLogTask_Booth());
        TV_TaskNum.setText(taskInfo.getData().getLogTask_TaskNum());
        TV_Serial.setText(taskInfo.getData().getLogTask_Serial());
       TV_TransNum.setText(taskInfo.getData().getLogTask_TransNum());
        TV_Area.setText(taskInfo.getData().getLogTask_Area() + taskInfo.getData().getLogTask_Street());
        TV_GoodsTitle.setText(taskInfo.getData().getLogTask_GoodsTitle());
        //TV_Weight.setText(taskInfo.getData().getLogTask_Weight());
        TV_RecPerson.setText(taskInfo.getData().getLogTask_RecPerson());
        TV_RecTel.setText(taskInfo.getData().getLogTask_RecTel());
        TV_RecAddr.setText(taskInfo.getData().getLogTask_RecAddr());
        TV_AccTime.setText(taskInfo.getData().getLogTask_AccTime());
    }
}
