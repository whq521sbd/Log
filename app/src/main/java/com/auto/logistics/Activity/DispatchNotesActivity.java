package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbJsonUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.Adapter.DispatchAdapter;
import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 2016/11/15.
 */
public class DispatchNotesActivity extends AbActivity {

    @AbIocView(id = R.id.TV_Dataselect, click = "click")
    TextView TV_Dataselect;
    @AbIocView(id = R.id.IV_dispatchgoback, click = "click")
    ImageView IV_dispatchgoback;
    @AbIocView(id = R.id.DP_DatePicker)
    DatePicker DP_DatePicker;
    @AbIocView(id = R.id.TV_Qurey, click = "click")
    TextView TV_Qurey;
    @AbIocView(id = R.id.LV_DisListView)
    ListView LV_DisListView;

    private int year, month, day;
    private String data;
    private AbRequestParams params;
    private AbHttpUtil mHttpUtil;
    private ArrayList<DispatchBean.DataBean.LogsListBean> logsListBean;
    private  DispatchAdapter dispatchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.dispatchnoteslayout);
        params = new AbRequestParams();
        mHttpUtil = AbHttpUtil.getInstance(this);
        mHttpUtil.setTimeout(10000);
        setView();
    }


    //设置控件
    private void setView() {
        LV_DisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DispatchBean.DataBean.LogsListBean itemBean = logsListBean.get(position);
                Intent intent = new Intent();
                intent.putExtra("itembean", itemBean);
                intent.setClass(DispatchNotesActivity.this, DispatchDetailActivity.class);
                startActivity(intent);
//                logsListBean.remove(position);
//                dispatchAdapter.notifyDataSetChanged();
            }
        });
    }

    /*
    * 点击事件
    *
    *
    * */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.TV_Qurey:
                LV_DisListView.setVisibility(View.VISIBLE);
                DP_DatePicker.setVisibility(View.GONE);
                getData();
                break;


            case R.id.TV_Dataselect:
                LV_DisListView.setVisibility(View.GONE);
                DP_DatePicker.setVisibility(View.VISIBLE);
                initData();
                break;


            case R.id.IV_dispatchgoback:
                finish();
                break;

        }

    }

    /*
    *
    * 初始化DatePicker ，获取年月日
    * */
    private void initData() {
        // 获取当前的年、月、日、小时、分钟
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DP_DatePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TV_Dataselect.setText(year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日");

                data = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;

                params.put("queryTime", data);
            }
        });


    }

    /*
    *
    * 获取数据
    *
    * */
    public void getData() {
        if (AbStrUtil.isEmpty(data)) {
            AbToastUtil.showToast(DispatchNotesActivity.this, "请输入要查询的日期");
        } else {
            params.put("Token", SharedPreferencesSava.getInstance().getStringValue(DispatchNotesActivity.this, "Token"));
            params.put("curPage", 1);
            params.put("state", 6);
            params.put("TaskNum", "");
            mHttpUtil.post(FinalURL.URL + "/QryLogTask", params, new AbStringHttpResponseListener() {
                @Override
                public void onSuccess(int i, String s) {
                    if (s != null) {
                        DispatchBean dispatchBean = AbJsonUtil.fromJson(s, DispatchBean.class);
                        try {
//                            由于返回字段，有可能没有logs字段，所以，判定如果没有logs字段，就没有数据信息，手动抛异常
                            for (int j = 0; j < dispatchBean.getData().getLogs().size(); j++) {
                                logsListBean = (ArrayList<DispatchBean.DataBean.LogsListBean>) dispatchBean.getData().getLogs();
                            }
                            if (logsListBean!=null){
                            dispatchAdapter = new DispatchAdapter(DispatchNotesActivity.this, logsListBean);
                            LV_DisListView.setAdapter(dispatchAdapter);
                            }else {
                                AbToastUtil.showToast(DispatchNotesActivity.this,"没有数据~");
                            }
                        } catch (Exception e) {
                            if (logsListBean!=null){
                                logsListBean.clear();//清空历史数据
                                dispatchAdapter.notifyDataSetChanged();//适配器刷新数据
                            }
                            AbToastUtil.showToast(DispatchNotesActivity.this, "此日期没有数据！");
                            e.printStackTrace();
                        }
                    } else {
                        AbToastUtil.showToast(DispatchNotesActivity.this, "服务器连接异常");
                    }
                }

                @Override
                public void onStart() {
                    AbDialogUtil.showProgressDialog(DispatchNotesActivity.this, -1, "正在查询数据");
                }

                @Override
                public void onFinish() {
                    AbDialogUtil.removeDialog(DispatchNotesActivity.this);
                }

                @Override
                public void onFailure(int i, String s, Throwable throwable) {
                    AbDialogUtil.removeDialog(DispatchNotesActivity.this);
                    AbToastUtil.showToast(DispatchNotesActivity.this, "获取失败，请重试");

                }
            });
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
