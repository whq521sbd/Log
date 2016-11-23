package com.auto.logistics.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ab.view.ioc.AbIocView;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/23.
 */

public class OrderAdapter extends BaseAdapter {
    private ArrayList<LogTaskBean.DataBean.LogsBean> dataList = new ArrayList<>();
    private Context ctx;

    public OrderAdapter(Context ctx, ArrayList<LogTaskBean.DataBean.LogsBean> dataList) {
        this.ctx = ctx;
        this.dataList = dataList;
    }

    // 必须得到真实的数据源项数，否则不能适配数据
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 对于ListView中的数据，显示每行数据都要调用一次该方法convertView和ViewHolder同完成缓存机，以优化性能
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        OrderAdapter.ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.message_item, null);

            holder = new OrderAdapter.ViewHolder();
            // 每项的视图布是一样的,findview
//            holder.tv_GoodsTitle = (TextView) view.findViewById(R.id.tv_GoodsTitle);
//            holder.tv_Weight = (TextView) view.findViewById(R.id.tv_Weight);
//            holder.rightarrow = (ImageView) view.findViewById(R.id.rightarrow);
            holder.TV_TaskNum = (TextView) view.findViewById(R.id.TV_TaskNum);
            holder.TV_Serial = (TextView) view.findViewById(R.id.TV_Serial);
            holder.TV_TransNum = (TextView) view.findViewById(R.id.TV_TransNum);
            holder.TV_Area = (TextView) view.findViewById(R.id.TV_Area);
            holder.TV_GoodsTitle = (TextView) view.findViewById(R.id.TV_GoodsTitle);
            holder.TV_Weight = (TextView) view.findViewById(R.id.TV_Weight);
            holder.TV_RecPerson = (TextView) view.findViewById(R.id.TV_RecPerson);
            holder.TV_RecTel = (TextView) view.findViewById(R.id.TV_RecTel);
            holder.TV_RecAddr = (TextView) view.findViewById(R.id.TV_RecAddr);
            holder.TV_AccTime = (TextView) view.findViewById(R.id.TV_AccTime);

            view.setTag(holder);
        } else {
            holder = (OrderAdapter.ViewHolder) view.getTag();

        }

        // 每项的数据是不一样的，setview
        holder.TV_TaskNum.setText(dataList.get(position).getTaskNum());
        holder.TV_Serial.setText(dataList.get(position).getSerial());
        holder.TV_TransNum.setText(dataList.get(position).getTransNum());
        holder.TV_Area.setText(dataList.get(position).getArea() + dataList.get(position).getStreet());
        //TV_Street.setText(logsBean.getStreet());
        holder.TV_GoodsTitle.setText(dataList.get(position).getGoodsTitle());
        holder.TV_Weight.setText(dataList.get(position).getWeight());
        holder.TV_RecPerson.setText(dataList.get(position).getRecPerson());
        holder.TV_RecTel.setText(dataList.get(position).getRecTel());
        holder.TV_RecAddr.setText(dataList.get(position).getRecAddr());
        holder.TV_AccTime.setText(dataList.get(position).getAccTime());

        return view;
    }

    // 用来实现缓存机制
    private static class ViewHolder {

        public TextView TV_TaskNum;
        public TextView TV_Serial;
        public TextView TV_TransNum;
        public TextView TV_Area;
        public TextView TV_GoodsTitle;
        public TextView TV_Weight;
        public TextView TV_RecPerson;
        public TextView TV_RecTel;
        public TextView TV_RecAddr;
        public TextView TV_AccTime;

    }
}
