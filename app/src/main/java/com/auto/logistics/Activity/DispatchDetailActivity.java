package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.R;

/**
 * Created by Administrator on 2016/11/18.
 */
public class DispatchDetailActivity extends AbActivity {
    @AbIocView(id = R.id.IV_dispatchdetaillback, click = "click")
    ImageView IV_dispatchdetaillback;
    @AbIocView(id = R.id.TV_TaskNum)
    TextView TV_TaskNum;
    @AbIocView(id = R.id.TV_Serial)
    TextView TV_Serial;
    @AbIocView(id = R.id.TV_TransNum)
    TextView TV_TransNum;
    @AbIocView(id = R.id.TV_Area)
    TextView TV_Area;
    @AbIocView(id = R.id.TV_Street)
    TextView TV_Street;
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
    @AbIocView(id = R.id.TV_AccUser)
    TextView TV_AccUser;
    @AbIocView(id = R.id.TV_PackTime)
    TextView TV_PackTime;
    @AbIocView(id = R.id.TV_PackUser)
    TextView TV_PackUser;
    @AbIocView(id = R.id.TV_DepTime)
    TextView TV_DepTime;
    @AbIocView(id = R.id.TV_DepUser)
    TextView TV_DepUser;
    @AbIocView(id = R.id.TV_SendTime)
    TextView TV_SendTime;
    @AbIocView(id = R.id.TV_SendUser)
    TextView TV_SendUser;
    @AbIocView(id = R.id.TV_DeliTime)
    TextView TV_DeliTime;
    @AbIocView(id = R.id.TV_DeliUser)
    TextView TV_DeliUser;
    DispatchBean.DataBean.LogsListBean logsListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.dispatchdetaillayout);
        Intent intent = getIntent();
        logsListBean = (DispatchBean.DataBean.LogsListBean) intent.getSerializableExtra("itembean");
        setView();
    }

    private void setView() {
        TV_TaskNum.setText(logsListBean.getTaskNum());
        TV_Serial.setText(logsListBean.getSerial());
        TV_TransNum.setText(logsListBean.getTransNum());
        TV_Area.setText(logsListBean.getArea());
        TV_Street.setText(logsListBean.getStreet());
        TV_GoodsTitle.setText(logsListBean.getGoodsTitle());
        TV_Weight.setText(logsListBean.getWeight());
        TV_RecPerson.setText(logsListBean.getRecPerson());
        TV_RecTel.setText(logsListBean.getRecTel());
        TV_RecAddr.setText(logsListBean.getRecAddr());
        TV_AccTime.setText(logsListBean.getAccTime());
        TV_AccUser.setText(logsListBean.getAccUser());
        TV_PackTime.setText(logsListBean.getPackTime());
        TV_PackUser.setText(logsListBean.getPackUser());
        TV_DepTime.setText(logsListBean.getDepTime());
        TV_DepUser.setText(logsListBean.getDepUser());
        TV_SendTime.setText(logsListBean.getSendTime());
        TV_SendUser.setText(logsListBean.getSendUser());
        TV_DeliTime.setText(logsListBean.getDeliTime());
        TV_DeliUser.setText(logsListBean.getDeliUser());
    }


    public void click(View view) {
        switch (view.getId()) {
            case R.id.IV_dispatchdetaillback:
                finish();
                break;
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


}
