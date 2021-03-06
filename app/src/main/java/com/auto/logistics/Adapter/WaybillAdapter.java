package com.auto.logistics.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/28.
 */

public class WaybillAdapter extends BaseAdapter {
    private ArrayList<DispatchBean.DataBean.LogsListBean> dataList = new ArrayList<DispatchBean.DataBean.LogsListBean>();
    private Context ctx;

    public WaybillAdapter(Context ctx, ArrayList<DispatchBean.DataBean.LogsListBean> dataList) {
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
        WaybillAdapter.ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.waybillitem, null);

            holder = new WaybillAdapter.ViewHolder();

            holder.tv_waybillNum = (TextView) view.findViewById(R.id.tv_waybillNum);
            holder.tv_GoodsName = (TextView) view.findViewById(R.id.tv_GoodsName);
            holder.tv_Booth = (TextView) view.findViewById(R.id.tv_Booth);


            view.setTag(holder);
        } else {
            holder = (WaybillAdapter.ViewHolder) view.getTag();

        }

        holder.tv_waybillNum.setText(dataList.get(position).getTaskNum());
        holder.tv_GoodsName.setText(dataList.get(position).getGoodsTitle());
        holder.tv_Booth.setText(dataList.get(position).getBooth());

        return view;
    }


    // 用来实现缓存机制
    private static class ViewHolder {
        TextView tv_waybillNum;
        TextView tv_GoodsName;
        TextView tv_Booth;

    }
}
