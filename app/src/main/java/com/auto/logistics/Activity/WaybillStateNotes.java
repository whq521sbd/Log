package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.Adapter.WaybillAdapter;
import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaybillStateNotes extends AbActivity {
    @AbIocView(id = R.id.IV_waybillback,click = "click")
    ImageView IV_waybillback;
    @AbIocView(id = R.id.LV_waybillListView)
     ListView LV_waybillListView;
    @AbIocView(id =R.id.tv_wayBilltile)
    TextView tv_wayBilltile;
    private List<DispatchBean.DataBean.LogsListBean> logsListBean;
    private Intent intent;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waybillstatenoteslayout);
        intent = getIntent();
        logsListBean= (List<DispatchBean.DataBean.LogsListBean>) intent.getSerializableExtra("logsListBean");
        state =  intent.getIntExtra("state",-1);
        switch (state){
            case 3:
                tv_wayBilltile.setText("已接单");
                setView(state);
                break;
            case 4:
                tv_wayBilltile.setText("已装车");
                setView(state);
                break;
            case 5:
                tv_wayBilltile.setText("已出发");
                setView(state);
                break;
            case 6:
                tv_wayBilltile.setText("已到达");
                setView(state);
                break;
        }

    }

    private void setView(final int  stata) {
        if (logsListBean!=null){
        WaybillAdapter waybillAdapter = new WaybillAdapter(WaybillStateNotes.this, (ArrayList<DispatchBean.DataBean.LogsListBean>) logsListBean);
        LV_waybillListView.setAdapter(waybillAdapter);
            LV_waybillListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent.setClass(WaybillStateNotes.this,DispatchDetailActivity.class);
                    //intent = new Intent(WaybillStateNotes.this,DispatchDetailActivity.class);
                    intent.putExtra("itembean",logsListBean.get(position));
                    intent.putExtra("state",stata);
                    startActivity(intent);
                }
            });

        }else {
            AbToastUtil.showToast(WaybillStateNotes.this,"上一页传递参数为空！");
        }
    }


    public void click(View view){
        switch (view.getId()){
            case R.id.IV_waybillback:
                finish();
                break;
        }
    }
}
