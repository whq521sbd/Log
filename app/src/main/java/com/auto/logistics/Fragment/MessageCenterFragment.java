package com.auto.logistics.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.auto.logistics.Service.MessageService;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;
import com.auto.logistics.Utills.ToastUtil;
import com.auto.logistics.Utills.ZxingUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/10/27.
 */

public class MessageCenterFragment extends Fragment implements View.OnClickListener {
    private ListView LV_MessageListView;
    private AbHttpUtil mAbHttpUtil;
    private AbRequestParams params;
    private List<LogTaskBean.DataBean.LogsBean> listlogs;
    private MessageAdapter messageAdapter;
    private LogTaskBean dataBean;
    private SwipeRefreshLayout Ab_AbPullToRefreshView;

    private Message message = new Message();
    private Timer timer = new Timer(true);
    private TimerTask timerTask;
    private List<LogTaskBean.DataBean.LogsBean> newlist = new ArrayList<LogTaskBean.DataBean.LogsBean>();
    private ImageView IV_scan;
    private AbHttpUtil httpUtil;
    private String time;
    private LogTaskBean logTaskBean;
    private LogTaskBean.DataBean.LogsBean logsBean;
    private TextView tv_Refreshtitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messagecenterlayout, null);
        mAbHttpUtil = AbHttpUtil.getInstance(getActivity());
        params = new AbRequestParams();
        httpUtil = AbHttpUtil.getInstance(getActivity());
        params = new AbRequestParams();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = format.format(date);
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
        IV_scan.setOnClickListener(this);

        //设置刷新时动画的颜色，可以设置4个
        Ab_AbPullToRefreshView.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        Ab_AbPullToRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
               // listlogs.clear();
                    RefreshData();
            }
        });


//
//        Ab_AbPullToRefreshView.setLoadMoreEnable(false);
////      上拉刷新
//        Ab_AbPullToRefreshView.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(AbPullToRefreshView abPullToRefreshView) {
//                listlogs.clear();
//                getData(1);
//                messageAdapter.notifyDataSetChanged();
//                Ab_AbPullToRefreshView.onHeaderRefreshFinish();
//                startAction(LV_MessageListView);
//            }
//        });

////      下拉加载更多
//        Ab_AbPullToRefreshView.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {
//            @Override
//            public void onFooterLoad(AbPullToRefreshView abPullToRefreshView) {
//                getData(curPage++);
////              重新查询后，将数据加载到新的list中去
//                for (int i = 0; i < listlogs.size(); i++) {
//                    newlist.add(listlogs.get(i));
//                }
//                messageAdapter = new MessageAdapter(getActivity(), (ArrayList<LogTaskBean.DataBean.LogsBean>) newlist);
//                LV_MessageListView.setAdapter(messageAdapter);
//                messageAdapter.notifyDataSetChanged();//刷新适配器
//                Ab_AbPullToRefreshView.onFooterLoadFinish();
//            }
//        });

//      item 监听
        LV_MessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //EventBus.getDefault().post("hide"); //点击之后小圆点隐藏
                LogTaskBean.DataBean.LogsBean logsitemBean = listlogs.get(position);
                Intent intent = new Intent();
                intent.putExtra("itembean", logsitemBean);
                intent.setClass(getActivity(), OrderActivity.class);
                startActivity(intent);
                messageAdapter.notifyDataSetChanged();
            }
        });
    }
//  刷新数据
    private void RefreshData() {
        params.put("TaskNum", "");
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        // Log.d("111", "onClick: " + SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        params.put("queryTime", time);
        params.put("curPage", 1);
        params.put("state", "12");
        mAbHttpUtil.post(FinalURL.URL + "/QryLogTask", params, new AbStringHttpResponseListener() {

            @Override
            public void onSuccess(int i, String s) {//成功
                if (s != null) {
                    dataBean = AbJsonUtil.fromJson(s, LogTaskBean.class);
                    if (dataBean.isSuc()) {

                            listlogs = dataBean.getData().getLogs();
                            //        适配器装载数据源
                            messageAdapter = new MessageAdapter(getActivity(), (ArrayList<LogTaskBean.DataBean.LogsBean>) listlogs);
                            //       listview加载适配器
                            LV_MessageListView.setAdapter(messageAdapter);

                    } else if (dataBean.getMsg().equals("token已失效")) {
                        AbToastUtil.showToast(getActivity(), "您的账号在其他客户端登录！");
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        SharedPreferencesSava.getInstance().savaStringValue(getActivity(), "MDpwd", "");
                    }
                } else {
                    AbToastUtil.showToast(getActivity(), "无数据！");
                }
            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {//失败
                AbDialogUtil.removeDialog(getActivity());
                //AbToastUtil.showToast(getActivity(), "无网络连接，查询订单失败！");
                ToastUtil.showToast(getActivity(),"无网络连接，查询订单失败！",0);
                Ab_AbPullToRefreshView.setRefreshing(false);
                tv_Refreshtitle.setVisibility(View.GONE);
            }

            @Override
            public void onStart() {//开始
                tv_Refreshtitle.setText("正在刷新...");
            }

            @Override
            public void onFinish() {//完成
                Ab_AbPullToRefreshView.setRefreshing(false);
                messageAdapter.notifyDataSetChanged();
                tv_Refreshtitle.setText("刷新成功");
                tv_Refreshtitle.setVisibility(View.GONE);
            }

        });
    }

    //动画
    private void startAction(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 0);
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(getActivity(), android.R.anim.cycle_interpolator);
        translateAnimation.setFillAfter(true);
        view.setAnimation(translateAnimation);
    }

    //初始化listview
    private void initView(View view) {
        IV_scan = (ImageView) view.findViewById(R.id.IV_scan);
        Ab_AbPullToRefreshView = (SwipeRefreshLayout) view.findViewById(R.id.Ab_AbPullToRefreshView);
        LV_MessageListView = (ListView) view.findViewById(R.id.LV_MessageListView);
        tv_Refreshtitle = (TextView) view.findViewById(R.id.tv_Refreshtitle);
    }

    //获取数据
    public void getData(int curPage) {
        params.put("TaskNum", "");
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        // Log.d("111", "onClick: " + SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        params.put("queryTime", time);
        params.put("curPage", curPage);
        params.put("state", "12");
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
                    }
                } else {
                    AbToastUtil.showToast(getActivity(), "无数据！");
                }
            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {//失败

                AbDialogUtil.removeDialog(getActivity());
                ToastUtil.showToast(getContext(),"无网络连接，查询订单失败！",0);
            }

            @Override
            public void onStart() {//开始
//                轮询服务
               // getActivity().startService(new Intent(getActivity(), MessageService.class));
            }

            @Override
            public void onFinish() {//完成
            }

        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IV_scan://扫描
//                扫描之后，关闭页面并联网获取订单状态，根据订单状态跳转相应的页面
                ZxingUtil.getInstance().decode(getActivity());//扫描
                break;
        }
    }

}
