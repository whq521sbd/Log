package com.auto.logistics.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbJsonUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.pullview.AbPullToRefreshView;
import com.auto.logistics.Activity.LoginActivity;
import com.auto.logistics.Activity.OrderActivity;
import com.auto.logistics.Adapter.MessageAdapter;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import java.io.Serializable;
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
    private AbPullToRefreshView Ab_AbPullToRefreshView;
    private int curPage =1;

    List<LogTaskBean.DataBean.LogsBean>newlist = new ArrayList<LogTaskBean.DataBean.LogsBean>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messagecenterlayout, null);
        mAbHttpUtil = AbHttpUtil.getInstance(getActivity());
        params = new AbRequestParams();
        initView(view);

        return view;
    }


    @Override
    public void onStart() {
        getData(1);
        setView();
        super.onStart();
    }

    //设置控件
    private void setView() {
//      上拉刷新
        Ab_AbPullToRefreshView.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView abPullToRefreshView) {
                listlogs.clear();
                getData(1);
                messageAdapter.notifyDataSetChanged();
                Ab_AbPullToRefreshView.onHeaderRefreshFinish();
                startAction(LV_MessageListView);
            }
        });


//      下拉加载更多
        Ab_AbPullToRefreshView.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {
            @Override
            public void onFooterLoad(AbPullToRefreshView abPullToRefreshView) {
                getData(curPage++);
//              重新查询后，将数据加载到新的list中去
                for (int i=0;i<listlogs.size();i++){
                    newlist.add(listlogs.get(i));
                }
                messageAdapter = new MessageAdapter(getActivity(), (ArrayList<LogTaskBean.DataBean.LogsBean>) newlist);
                LV_MessageListView.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();//刷新适配器
                Ab_AbPullToRefreshView.onFooterLoadFinish();
            }
        });

//      item 监听
        LV_MessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogTaskBean.DataBean.LogsBean logsitemBean = listlogs.get(position);
                Intent intent = new Intent();
                intent.putExtra("itembean", logsitemBean);
                intent.setClass(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startAction(View view) {
        TranslateAnimation translateAnimation =new TranslateAnimation(0,100,0,0);
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(getActivity(), android.R.anim.cycle_interpolator);
        translateAnimation.setFillAfter(true);
        view.setAnimation(translateAnimation);
    }

    //初始化listview
    private void initView(View view) {
        Ab_AbPullToRefreshView = (AbPullToRefreshView) view.findViewById(R.id.Ab_AbPullToRefreshView);
        LV_MessageListView = (ListView) view.findViewById(R.id.LV_MessageListView);
    }

    //获取数据
    public void getData(int curPage ) {
        params.put("TaskNum", "");
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        // Log.d("111", "onClick: " + SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        params.put("queryTime", time);
        params.put("curPage", curPage);
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
                        SharedPreferencesSava.getInstance().savaStringValue(getActivity(),"MDpwd","");
                        AbToastUtil.showToast(getActivity(), "数据获取失败，请重试！");
                    }
                } else {
                    AbToastUtil.showToast(getActivity(), "没有返回数据！");
                }
            }
1

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
