package com.auto.logistics.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.Adapter.ItemAdapte;
import com.auto.logistics.Adapter.Mainadapter;
import com.auto.logistics.Adapter.WaybillAdapter;
import com.auto.logistics.JavaBean.Beandata;
import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 运单列表页
 * <p>
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
    private Set<String> set = new LinkedHashSet<>();
    Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    private ArrayList<String> titlsDatas = new ArrayList<>();
    private List<Beandata> datas = new ArrayList<>();

    public static String xuanze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waybillstatenoteslayout);
        intent = getIntent();
        logsListBean = (List<LogTaskBean.DataBean.LogsBean>) intent.getSerializableExtra("logsListBean");

        getData();

        state = intent.getIntExtra("state", -1);
        switch (state) {
            case 13:
                tv_wayBilltile.setText("已接单");
                setView(state);
                break;
            case 14:
                tv_wayBilltile.setText("已装车");
                setView(state);
                break;
            case 15:
                tv_wayBilltile.setText("已出发");
                setView(state);
                break;
            case 16:
                tv_wayBilltile.setText("已到达");
                setView(state);
                break;
        }

    }


    private void getData() {
//                遍历数据，用 LinkedHashSet 将标题加入，保证标题不重复
        for (int i = 0; i < logsListBean.size(); i++) {
            set.add(logsListBean.get(i).getComName());
        }

//               Set集合不能用get(i)取值，遍历set，将数据移植到list里面
        for (String s : set) {
            titlsDatas.add(s);
        }

        for (int i = 0; i < titlsDatas.size(); i++) {
            String title = titlsDatas.get(i);
            List<LogTaskBean.DataBean.LogsBean> logs = new ArrayList<>();
            for (int j = 0; j < logsListBean.size(); j++) {
                String name = logsListBean.get(j).getComName();
                if (name.equals(title)) {
                    logs.add(logsListBean.get(j));
                }
            }
            Beandata data = new Beandata();
            data.setTitle(title);
            data.setLogs(logs);
            datas.add(data);
        }


    }


    private void setView(int stata) {
        if (logsListBean != null) {

            Mainadapter mainadapter = new Mainadapter(datas, WaybillStateNotes.this, titlsDatas);

            LV_waybillListView.setAdapter(mainadapter);


            // WaybillAdapter waybillAdapter = new WaybillAdapter(WaybillStateNotes.this, (ArrayList<LogTaskBean.DataBean.LogsBean>) logsListBean,map);
            // LV_waybillListView.setAdapter(waybillAdapter);
//            LV_waybillListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                    WaybillAdapter.ViewHolder holder = (WaybillAdapter.ViewHolder) view.getTag();
//                    holder.ck_itemcheck.toggle();
//                    WaybillAdapter.getIsSelected().put(position, holder.ck_itemcheck.isChecked());
//
//                    if (holder.ck_itemcheck.isChecked()){
//                        newlist.add(logsListBean.get(position));
//                    }else {
//                        newlist.remove(newlist.size()-1);
//                    }
//
//                }
//            });
//
//        } else {
//            AbToastUtil.showToast(WaybillStateNotes.this, "上一页传递参数为空！");
        }
    }


    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_waybillnext:

                for (int i = 0; i < titlsDatas.size(); i++) {
                    for (int j = 0; j < logsListBean.size(); j++) {
                        if (ItemAdapte.isselect[i][j]) {
                            //当checkbox选中时，放入集合里面
                            newlist.add(logsListBean.get(j));
                        }
                    }
                }
//                重置checkbox状态
                ItemAdapte.isselect = new  boolean[55][55];
                intent = new Intent(WaybillStateNotes.this, DispatchDetailActivity.class);
                intent.putExtra("newlist", newlist);
                intent.putExtra("state", state);
                startActivity(intent);
                finish();


                break;
            case R.id.IV_waybillback:
                //                重置checkbox状态
                ItemAdapte.isselect = new  boolean[55][55];
                finish();
                break;
        }
    }


    //返回键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //                重置checkbox状态
            ItemAdapte.isselect = new  boolean[55][55];
            finish();
        }
        return false;
    }


}
