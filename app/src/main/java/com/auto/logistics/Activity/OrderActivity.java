package com.auto.logistics.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.Adapter.OrderAdapter;
import com.auto.logistics.Fragment.MineInfoFragment;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */
public class OrderActivity extends AbActivity {

    //初始化 控件，并绑定点击事件
    @AbIocView(id = R.id.order_goback, click = "clickMe")
    ImageView order_goback;
    @AbIocView(id = R.id.tv_confirm, click = "clickMe")
    TextView tv_confirm;
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
    @AbIocView(id = R.id.tv_ordReturn, click = "clickMe")
    TextView tv_ordReturn;

    @AbIocView(id = R.id.order_listview)
    ListView order_listview;
    private AbHttpUtil mHttpUtil;
    private LogTaskBean.DataBean.LogsBean logsBean;
    private ArrayList<LogTaskBean.DataBean.LogsBean> newlist;
    private ArrayList<String> TaskNumlist = new ArrayList<>();
    //                添加参数
    AbRequestParams params = new AbRequestParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.orderlayout);
        //首先获得上个页面传过来的bean
        Intent intent = getIntent();
//        newlist = (ArrayList<LogTaskBean.DataBean.LogsBean>) intent.getSerializableExtra("newlist");
//        OrderAdapter orderAdapter = new OrderAdapter(OrderActivity.this,newlist);
//        order_listview.setAdapter(orderAdapter);
//

//        for (int i=0;i<newlist.size();i++){
//            String  TaskNum = newlist.get(i).getTaskNum();
//            params.put("TaskNum",TaskNum);
//        }


        logsBean = (LogTaskBean.DataBean.LogsBean) intent.getSerializableExtra("itembean");
        //给控件设置值
        setView();
        mHttpUtil = AbHttpUtil.getInstance(this);
        mHttpUtil.setTimeout(10000);

    }

    /**
     * @param keyCode
     * @param event   返回键监听
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

    /**
     * 注册控件，设置控件相关信息
     */
    private void setView() {
        TV_Booth.setText(logsBean.getBooth());
        TV_TaskNum.setText(logsBean.getTaskNum());
        TV_Serial.setText(logsBean.getSerial());
        TV_TransNum.setText(logsBean.getTransNum());
        TV_Area.setText(logsBean.getArea() + logsBean.getStreet());
        //TV_Street.setText(logsBean.getStreet());
        TV_GoodsTitle.setText(logsBean.getGoodsTitle());
        TV_Weight.setText(logsBean.getWeight()+"千克");
        TV_RecPerson.setText(logsBean.getRecPerson());
        TV_RecTel.setText(logsBean.getRecTel());
        TV_RecAddr.setText(logsBean.getRecAddr());
        TV_AccTime.setText(logsBean.getAccTime());
    }

    /**
     * click 方法
     *
     * @param v
     */
    public void clickMe(View v) {
        switch (v.getId()) {
            case R.id.tv_ordReturn://返回首页
                finish();

                break;
            case R.id.order_goback://返回
                finish();
                break;
            case R.id.tv_confirm:
                Log.d("OrderActivity", "clickMe: " + SharedPreferencesSava.getInstance().getStringValue(OrderActivity.this, "Token").toString());
                params.put("Token", SharedPreferencesSava.getInstance().getStringValue(OrderActivity.this, "Token"));
                params.put("TaskNum", logsBean.getTaskNum().toString());
                params.put("state", "13");
                mHttpUtil.post(FinalURL.URL + "/LogTaskOper", params, new AbStringHttpResponseListener() {
                    @Override
                    public void onSuccess(int i, String s) {
                        if (s != null) {
                            Log.i("1111", "OrderActivity: " + s.toString());
                            try {
                                //简单判断，如果可以正确得到服务器数据，并且验证为真，则跳转到下一步
                                JSONObject obj = new JSONObject(s);
                                boolean Suc = obj.getBoolean("Suc");
                                if (Suc) {
                                    //携带bean 跳转到装车页面
                                    Intent intent = new Intent(OrderActivity.this, InstallCarActivity.class);
                                    intent.putExtra("logsBean", logsBean);
                                    startActivity(intent);
                                    finish();
                                } else if (obj.getString("Msg").equals("token已失效")) {
                                    AbToastUtil.showToast(OrderActivity.this, "您的账号在其他客户端登录！");
                                    startActivity(new Intent(OrderActivity.this, LoginActivity.class));
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            AbToastUtil.showToast(OrderActivity.this, "服务器开小差，再试一次吧~");
                        }
                    }

                    @Override
                    public void onStart() {//开始请求
                        AbDialogUtil.showProgressDialog(OrderActivity.this, -1, "请稍后...");
                    }

                    @Override
                    public void onFinish() {//请求结束
                        AbDialogUtil.removeDialog(OrderActivity.this);
                    }

                    @Override
                    public void onFailure(int i, String s, Throwable throwable) {//请求失败
                        AbDialogUtil.removeDialog(OrderActivity.this);
                        AbToastUtil.showToast(OrderActivity.this, "网络不稳定，请检查网络连接~");
                    }
                });
                break;
        }

    }


}