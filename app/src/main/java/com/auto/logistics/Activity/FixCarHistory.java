package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
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
import com.auto.logistics.Adapter.FixCarAdapter;
import com.auto.logistics.JavaBean.CarSign;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/12/16.
 */
public class FixCarHistory extends AbActivity {
    @AbIocView(id = R.id.LV_fxihistory,itemClick = "click")
    ListView LV_fxihistory;
    @AbIocView(id = R.id.TV_Dataselect,click = "click")
    TextView TV_Dataselect;
    @AbIocView( id = R.id.TV_Qurey,click = "click")
    TextView TV_Qurey;
    @AbIocView( id = R.id.IV_Rback,click = "click")
    ImageView  IV_Rback;
    @AbIocView(id= R.id.TV_fixcarYear)
    TextView TV_fixcarYear;
    @AbIocView( id = R.id.TV_fixcarmonth)
    TextView TV_fixcarmonth;

    private AbHttpUtil httpUtil;
    private AbRequestParams params;
    private ArrayList<CarSign.DataBean.SignCarsBean>  signCarsBeanlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.fixcarhistory);

        httpUtil = AbHttpUtil.getInstance(this);
        params = new AbRequestParams();


        setView();

    }

    private void setView() {
        LV_fxihistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(FixCarHistory.this,FixcarItem.class);
                intent.putExtra("itemBean",signCarsBeanlist.get(position));
                startActivity(intent);
            }
        });

    }

    public void click(View view){
        switch (view.getId()){

            case R.id.TV_Qurey:
                if (checknull()){
                    getdata();
                }

                break;

            case R.id.IV_Rback:
                finish();
                break;
        }

    }

    private boolean checknull() {
        if (AbStrUtil.isEmpty(TV_fixcarYear.getText().toString())&&AbStrUtil.isEmpty(TV_fixcarmonth.getText().toString())){
            AbToastUtil.showToast(FixCarHistory.this,"查询日期不准为空！");
            return false;
        }
        return true;
    }

    public void getdata() {
        StringBuffer buffer =new StringBuffer();
        buffer.append(TV_fixcarYear.getText().toString()+TV_fixcarmonth.getText().toString());
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(FixCarHistory.this,"Token"));
        params.put("curPage",1);
        params.put("stTime",buffer.toString());
        httpUtil.post(FinalURL.URL + "/QryCarSign", params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                    if (s!=null&&!s.equals("")){
                        CarSign carSign =  AbJsonUtil.fromJson(s,CarSign.class);
                        if (carSign.isSuc()){
                            signCarsBeanlist  = (ArrayList<CarSign.DataBean.SignCarsBean>) carSign.getData().getSignCars();
                            FixCarAdapter adapter = new FixCarAdapter(FixCarHistory.this,signCarsBeanlist);
                            LV_fxihistory.setAdapter(adapter);
                        }else {
                            AbToastUtil.showToast(FixCarHistory.this,carSign.getMsg());
                        }

                    }

            }

            @Override
            public void onStart() {
                AbDialogUtil.showProgressDialog(FixCarHistory.this,-1,"正在查询数据...");
            }

            @Override
            public void onFinish() {
                AbDialogUtil.removeDialog(FixCarHistory.this);
            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                AbDialogUtil.removeDialog(FixCarHistory.this);
                AbToastUtil.showToast(FixCarHistory.this,"无法连接网络，查询日期失败！");

            }
        });
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
