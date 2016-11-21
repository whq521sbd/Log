package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/7.
 */
public class SendGoodsActivity extends AbActivity {
    @AbIocView(id = R.id.TV_SendRecPerson)
    TextView TV_SendRecPerson;
    @AbIocView(id = R.id.TV_SendRecTel)
    TextView TV_SendRecTel;
    @AbIocView(id = R.id.TV_SendRecAddr)
    TextView TV_SendRecAddr;
    @AbIocView(id = R.id.TV_SendDepTime)
    TextView TV_SendDepTime;
    @AbIocView(id = R.id.TV_SendDepUser)
    TextView TV_SendDepUser;
    @AbIocView(id = R.id.tv_sendgoods, click = "click")
    TextView tv_sendgoods;
    @AbIocView(id = R.id.IV_sendgoodsgoback, click = "click")
    ImageView IV_sendgoodsgoback;
    private LogTaskBean.DataBean.LogsBean logsBean;
    private AbHttpUtil mHttpUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.sendgoodslayout);
        mHttpUtil = AbHttpUtil.getInstance(SendGoodsActivity.this);
//        设置连接超时
        mHttpUtil.setTimeout(10000);
//       获取上个页面的bean
        Intent intent = getIntent();
        logsBean = (LogTaskBean.DataBean.LogsBean) intent.getSerializableExtra("logsBean");
//     注册并设置值
        setView();
    }



    /**
     * 注册控件
     */
    private void setView() {
        TV_SendRecPerson.setText(logsBean.getRecPerson());
        TV_SendRecTel.setText(logsBean.getRecTel());
        TV_SendRecAddr.setText(logsBean.getRecAddr());
        TV_SendDepTime.setText(logsBean.getDepTime());
        TV_SendDepUser.setText(logsBean.getDepUser());
    }


    /**
     * 点击事件
     *
     * @param view
     */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.IV_sendgoodsgoback:
                finish();
                break;
            case R.id.tv_sendgoods:
                AbRequestParams params = new AbRequestParams();
                params.put("Token", SharedPreferencesSava.getInstance().getStringValue(SendGoodsActivity.this, "Token"));
                params.put("TaskNum", logsBean.getTaskNum());
                params.put("state", "9");
                mHttpUtil.post(FinalURL.URL + "/LogTaskOper", params, new AbStringHttpResponseListener() {
                    @Override
                    public void onStart() {
                        AbDialogUtil.showProgressDialog(SendGoodsActivity.this, -1, "正在请求网络，请稍后");
                    }

                    @Override
                    public void onFinish() {
                        AbDialogUtil.removeDialog(SendGoodsActivity.this);
                    }

                    @Override
                    public void onFailure(int i, String s, Throwable throwable) {
                        AbDialogUtil.removeDialog(SendGoodsActivity.this);
                        AbToastUtil.showToast(SendGoodsActivity.this, "访问网络失败，请重试~");
                    }

                    @Override
                    public void onSuccess(int i, String s) {
                        if (s != null) {
                            try {
                                JSONObject object = new JSONObject(s);
                                if (object.getBoolean("Suc")) {
                                    Intent intent = new Intent(SendGoodsActivity.this, ReachAcitvity.class);
                                    intent.putExtra("logsBean", logsBean);
                                    startActivity(intent);

                                } else {
//                                    如果有异常则直接吐司 服务信息
                                    AbToastUtil.showToast(SendGoodsActivity.this, object.getString("Msg"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });


                break;
        }


    }

    /**
     * 返回键监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }


}
