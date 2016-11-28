package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

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
    private List<DispatchBean.DataBean.LogsListBean> logsListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waybillstatenoteslayout);
        Intent intent = getIntent();
        logsListBean= (List<DispatchBean.DataBean.LogsListBean>) intent.getSerializableExtra("logsListBean");
        setView();
    }

    private void setView() {
        if (logsListBean!=null){
        WaybillAdapter waybillAdapter = new WaybillAdapter(WaybillStateNotes.this, (ArrayList<DispatchBean.DataBean.LogsListBean>) logsListBean);
        LV_waybillListView.setAdapter(waybillAdapter);
            LV_waybillListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(WaybillStateNotes.this,DispatchDetailActivity.class);
                    intent.putExtra("itembean",logsListBean.get(position));
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
