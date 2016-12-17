package com.auto.logistics.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.Adapter.WaybillAdapter;
import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 运单列表页
 *
 * Created by Administrator on 2016/11/28.
 */
public class WaybillStateNotes extends AbActivity {
    @AbIocView(id = R.id.IV_waybillback, click = "click")
    ImageView IV_waybillback;
    @AbIocView(id = R.id.LV_waybillListView)
    ListView LV_waybillListView;
    @AbIocView(id = R.id.tv_wayBilltile)
    TextView tv_wayBilltile;
    @AbIocView(id = R.id.tv_waybillnext, click = "click")
    TextView tv_waybillnext;
    private List<LogTaskBean.DataBean.LogsBean> logsListBean;
    private Intent intent;
    private int state;
    private ArrayList<LogTaskBean.DataBean.LogsBean> newlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waybillstatenoteslayout);
        intent = getIntent();
        logsListBean = (List<LogTaskBean.DataBean.LogsBean>) intent.getSerializableExtra("logsListBean");

        state = intent.getIntExtra("state", -1);
        switch (state) {
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

    private void setView( int stata) {
        if (logsListBean != null) {
            WaybillAdapter waybillAdapter = new WaybillAdapter(WaybillStateNotes.this, (ArrayList<LogTaskBean.DataBean.LogsBean>) logsListBean);
            LV_waybillListView.setAdapter(waybillAdapter);
            LV_waybillListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                   WaybillAdapter.ViewHolder holder = (WaybillAdapter.ViewHolder) view.getTag();
                    holder.ck_itemcheck.toggle();
                    WaybillAdapter.getIsSelected().put(position, holder.ck_itemcheck.isChecked());

                    if (holder.ck_itemcheck.isChecked()){
                        newlist.add(logsListBean.get(position));
                    }else {
                        newlist.remove(newlist.size()-1);
                    }


//                    intent = new Intent(WaybillStateNotes.this, DispatchDetailActivity.class);
//                    //intent.putExtra("itembean", logsListBean.get(position));
//                        intent.putExtra("newlist",newlist);
//                    intent.putExtra("state", stata);
//                    startActivity(intent);
                }
            });

        } else {
            AbToastUtil.showToast(WaybillStateNotes.this, "上一页传递参数为空！");
        }
    }


    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_waybillnext:
                intent = new Intent(WaybillStateNotes.this, DispatchDetailActivity.class);
                intent.putExtra("newlist",newlist);
                intent.putExtra("state", state);
                startActivity(intent);
                finish();


                break;
            case R.id.IV_waybillback:
                finish();
                break;
        }
    }




}
