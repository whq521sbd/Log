package com.auto.logistics.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;

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

    private int year, month, day;
    private String data;
    private AbRequestParams params;
    private AbHttpUtil mHttpUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.dispatchnoteslayout);
        params = new AbRequestParams();
        mHttpUtil = AbHttpUtil.getInstance(this);
        mHttpUtil.setTimeout(10000);

    }


    public void click(View view) {
        switch (view.getId()) {
            case R.id.TV_Qurey:
                DP_DatePicker.setVisibility(View.GONE);
                getData();
                break;


            case R.id.TV_Dataselect:
                DP_DatePicker.setVisibility(View.VISIBLE);
                initData();
                break;


            case R.id.IV_dispatchgoback:
                finish();
                break;


        }

    }

    private void initData() {
        // 获取当前的年、月、日、小时、分钟
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DP_DatePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TV_Dataselect.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                data = "" + year + monthOfYear + dayOfMonth;

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


    public void getData() {

        if (AbStrUtil.isEmpty(data)) {
            AbToastUtil.showToast(DispatchNotesActivity.this, "请输入要查询的日期");
        } else {
//        TODO:查询接口
            mHttpUtil.post(FinalURL.URL + "", params, new AbStringHttpResponseListener() {
                @Override
                public void onSuccess(int i, String s) {

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
}
