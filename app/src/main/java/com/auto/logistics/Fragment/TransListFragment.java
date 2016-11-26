package com.auto.logistics.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbJsonUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.expandtabview.AbTabView1;
import com.auto.logistics.Activity.LoginActivity;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/21.
 */

public class TransListFragment extends Fragment implements View.OnClickListener {
    private TextView TV_query;
    private EditText ED_number;
    private ImageView IV_cleanNum;
    private AbHttpUtil mAbHttpUtil;
    private TextView TV_TaskNum, TV_Serial, TV_TransNum, TV_Area, TV_Street,
            TV_GoodsTitle, TV_Weight, TV_RecPerson, TV_RecTel, TV_RecAddr, TV_AccTime,
            TV_AccUser, TV_PackTime, TV_PackUser, TV_DepTime, TV_DepUser, TV_SendTime,
            TV_SendUser, TV_DeliTime, TV_DeliUser;
    private ScrollView  SV_waybill;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tranlistlayout, null);
        mAbHttpUtil = AbHttpUtil.getInstance(getActivity());
        mAbHttpUtil.setTimeout(10000);
        initView(view);
        setView();

        return view;
    }



    private void initView(View view) {
        TV_query = (TextView) view.findViewById(R.id.TV_query);
        ED_number = (EditText) view.findViewById(R.id.ED_number);
        IV_cleanNum = (ImageView) view.findViewById(R.id.IV_cleanNum);
        TV_TaskNum = (TextView) view.findViewById(R.id.TV_TaskNum);
        TV_Serial = (TextView) view.findViewById(R.id.TV_Serial);
        TV_TransNum= (TextView) view.findViewById(R.id.TV_TransNum);
        TV_Area= (TextView) view.findViewById(R.id.TV_Area);
        TV_Street= (TextView) view.findViewById(R.id.TV_Street);
        TV_GoodsTitle= (TextView) view.findViewById(R.id.TV_GoodsTitle);
        TV_Weight= (TextView) view.findViewById(R.id.TV_Weight);
        TV_RecPerson= (TextView) view.findViewById(R.id.TV_RecPerson);
        TV_RecTel= (TextView) view.findViewById(R.id.TV_RecTel);
        TV_RecAddr= (TextView) view.findViewById(R.id.TV_RecAddr);
        TV_AccTime= (TextView) view.findViewById(R.id.TV_AccTime);
        TV_AccUser= (TextView) view.findViewById(R.id.TV_AccUser);
        TV_PackTime= (TextView) view.findViewById(R.id.TV_PackTime);
        TV_PackUser= (TextView) view.findViewById(R.id.TV_PackUser);
        TV_DepTime= (TextView) view.findViewById(R.id.TV_DepTime);
        TV_DepUser= (TextView) view.findViewById(R.id.TV_DepUser);
        TV_SendTime= (TextView) view.findViewById(R.id.TV_SendTime);
        TV_SendUser= (TextView) view.findViewById(R.id.TV_SendUser);
        TV_DeliTime= (TextView) view.findViewById(R.id.TV_DeliTime);
        TV_DeliUser= (TextView) view.findViewById(R.id.TV_DeliUser);
        SV_waybill = (ScrollView) view.findViewById(R.id.SV_waybill);
    }

    private void setView() {



        TV_query.setOnClickListener(this);
        IV_cleanNum.setOnClickListener(this);
        ED_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    IV_cleanNum.setVisibility(View.VISIBLE);
                } else {
                    IV_cleanNum.setVisibility(View.GONE);
                }

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TV_query://查询按钮
                AbRequestParams params = new AbRequestParams();
                String TransNumber = ED_number.getText().toString().trim();
                if (AbStrUtil.isEmpty(TransNumber)) {
                    AbToastUtil.showToast(getActivity(), "运单编号不能为空哦~");
                } else {
                    params.put("TaskNum", TransNumber);
                    params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
                    Log.d("111", "onClick: " + SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = format.format(date);
                    params.put("queryTime", time);
                    params.put("curPage", "1");
                    params.put("state", "6");
                    mAbHttpUtil.post(FinalURL.URL + "/QryLogTask", params, new AbStringHttpResponseListener() {
                        @Override
                        public void onSuccess(int i, String s) {
                            if (s!=null) {
                                LogTaskBean dataBean = AbJsonUtil.fromJson(s, LogTaskBean.class);
                                if (dataBean.getData().getLogs().size()!=0) {
                                    for (int j = 0; j<dataBean.getData().getLogs().size();j++){
                                        setView(dataBean, j);
                                    }
                                    //显示数据
                                    SV_waybill.setVisibility(View.VISIBLE);
                                } else {
                                    SV_waybill.setVisibility(View.GONE);
                                    AbToastUtil.showToast(getActivity(),"查询不到此单号！");
                                }
                            }else {
                                AbToastUtil.showToast(getActivity(),"接口获取数据失败！");
                            }
                        }

                        @Override
                        public void onStart() {
                            AbDialogUtil.showProgressDialog(getActivity(),-1,"正在查询订单...");
                        }

                        @Override
                        public void onFinish() {
                            AbDialogUtil.removeDialog(getActivity());

                        }

                        @Override
                        public void onFailure(int i, String s, Throwable throwable) {
                            AbDialogUtil.removeDialog(getActivity());
                            AbToastUtil.showToast(getActivity(), "查询失败，请重试~");
                        }
                    });
                }
                break;
            case R.id.IV_cleanNum://清除数据
                ED_number.setText("");
                break;
        }
    }



    /**
     * @param dataBean 解析完成后的数据源实体
     * @param j        Logs的下标
     */
    private void setView(LogTaskBean dataBean, int j) {
        TV_TaskNum.setText(dataBean.getData().getLogs().get(j).getTaskNum());
        TV_Serial.setText(dataBean.getData().getLogs().get(j).getSerial());
        TV_TransNum.setText(dataBean.getData().getLogs().get(j).getTransNum());
        TV_Area.setText(dataBean.getData().getLogs().get(j).getArea());
        TV_Street.setText(dataBean.getData().getLogs().get(j).getStreet());
        TV_GoodsTitle.setText(dataBean.getData().getLogs().get(j).getGoodsTitle());
        TV_Weight.setText(dataBean.getData().getLogs().get(j).getWeight());
        TV_RecPerson.setText(dataBean.getData().getLogs().get(j).getRecPerson());
        TV_RecTel.setText(dataBean.getData().getLogs().get(j).getRecTel());
        TV_RecAddr.setText(dataBean.getData().getLogs().get(j).getRecAddr());
        TV_AccTime.setText(dataBean.getData().getLogs().get(j).getAccTime());
        TV_AccUser.setText(dataBean.getData().getLogs().get(j).getAccUser());
        TV_PackTime.setText(dataBean.getData().getLogs().get(j).getPackTime());
        TV_PackUser.setText(dataBean.getData().getLogs().get(j).getPackUser());
        TV_DepTime .setText(dataBean.getData().getLogs().get(j).getDepTime());
        TV_DepUser.setText(dataBean.getData().getLogs().get(j).getDepUser());
        TV_SendTime.setText(dataBean.getData().getLogs().get(j).getSendTime());
        TV_SendUser.setText(dataBean.getData().getLogs().get(j).getSendUser());
        TV_DeliTime .setText(dataBean.getData().getLogs().get(j).getDeliTime());
        TV_DeliUser.setText(dataBean.getData().getLogs().get(j).getDeliUser());
    }

}


