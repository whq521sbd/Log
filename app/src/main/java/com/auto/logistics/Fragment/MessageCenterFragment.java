package com.auto.logistics.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbJsonUtil;
import com.ab.util.AbToastUtil;
import com.auto.logistics.Activity.LoginActivity;
import com.auto.logistics.Activity.OrderActivity;
import com.auto.logistics.Adapter.MessageAdapter;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */

public class MessageCenterFragment extends Fragment {
    private ListView LV_MessageListView;
    private AbHttpUtil mAbHttpUtil;
    private AbRequestParams params;
    private List<LogTaskBean.DataBean.LogsBean> listlogs;
    private MessageAdapter messageAdapter;
    private LogTaskBean dataBean;
    private Intent intent = new Intent();
    private TextView tv_next;
    private ArrayList<LogTaskBean.DataBean.LogsBean> newlist = new ArrayList<>();
    private int sata = 0;
    private LogTaskBean.DataBean.LogsBean logsitemBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messagecenterlayout, null);
        mAbHttpUtil = AbHttpUtil.getInstance(getActivity());
        params = new AbRequestParams();
        initView(view);
        getData();
        setView();
        return view;
    }


    //设置控件
    private void setView() {

//添加动画效果
        TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        translate.setDuration(2000);//动画时间500毫秒
        translate.setFillAfter(true);//动画出来控件可以点击
        tv_next.startAnimation(translate);//开始动画
//点击按钮跳转
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), OrderActivity.class);
                intent.putExtra("newlist", newlist);
                startActivity(intent);
            }
        });


        LV_MessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CheckBox ck_itemcheck = (CheckBox) view.findViewById(R.id.ck_itemcheck);

                listlogs.get(position).setSata(1);

                for (int i = 0; i < listlogs.size(); i++) {
                    if (listlogs.get(position).getSata() == 1) {
                        ck_itemcheck.setChecked(true);
                        logsitemBean = listlogs.get(position);
                        newlist.add(logsitemBean);
                        listlogs.get(position).setSata(0);
                        messageAdapter.notifyDataSetChanged();
                    } else {
                        ck_itemcheck.setChecked(false);
                        newlist.remove(newlist.size());
                        listlogs.get(position).setSata(1);
                        messageAdapter.notifyDataSetChanged();
                    }

                }

//
//                    if (sata == 1) {
//                        sata = 0;
//                        ck_itemcheck.setChecked(true);
//                        logsitemBean = listlogs.get(position);
//                        newlist.add(logsitemBean);
//                    } else {
//                        sata = 1;
//                        ck_itemcheck.setChecked(false);
//
//                        newlist.remove(newlist.size());//删除最后一个
//                    }

                //intent.putExtra("itembean", logsitemBean);
//                intent.setClass(getActivity(), OrderActivity.class);
//                startActivity(intent);
//                listlogs.remove(position);
//                messageAdapter.notifyDataSetChanged();
            }
        });
    }

    //初始化listview
    private void initView(View view) {
        LV_MessageListView = (ListView) view.findViewById(R.id.LV_MessageListView);
        tv_next = (TextView) view.findViewById(R.id.tv_next);
    }

    //获取数据
    public void getData() {
        params.put("TaskNum", "");
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        // Log.d("111", "onClick: " + SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        params.put("queryTime", time);
        params.put("curPage", "1");
        params.put("state", "2");
        mAbHttpUtil.post(FinalURL.URL + "/QryLogTask", params, new AbStringHttpResponseListener() {

            @Override
            public void onSuccess(int i, String s) {//成功
                if (s != null) {
                    dataBean = AbJsonUtil.fromJson(s, LogTaskBean.class);
                    if (dataBean.isSuc()) {

                        for (int j = 0; j < dataBean.getData().getLogs().size(); j++) {
                            listlogs = dataBean.getData().getLogs();
                            //        适配器装载数据源
                            messageAdapter = new MessageAdapter(getActivity(), (ArrayList<LogTaskBean.DataBean.LogsBean>) listlogs);
                            //       listview加载适配器
                            LV_MessageListView.setAdapter(messageAdapter);
                        }
                    } else if (dataBean.getMsg().equals("token已失效")) {
                        AbToastUtil.showToast(getActivity(), "您的账号在其他客户端登录！");
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        SharedPreferencesSava.getInstance().savaStringValue(getActivity(), "MDpwd", "");
                        AbToastUtil.showToast(getActivity(), "数据获取失败，请重试！");
                    }

                } else {
                    AbToastUtil.showToast(getActivity(), "没有返回数据！");
                }
            }


            @Override
            public void onFailure(int i, String s, Throwable throwable) {//失败

                AbDialogUtil.removeDialog(getActivity());
                AbToastUtil.showToast(getActivity(), "查询失败，请重试~");
            }

            @Override
            public void onStart() {//开始

            }

            @Override
            public void onFinish() {//完成

            }


        });
    }
}
