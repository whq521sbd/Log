package com.auto.logistics.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/18.
 */

public class DispatchAdapter extends BaseAdapter {
    private ArrayList<LogTaskBean.DataBean.LogsBean> dataList = new ArrayList<LogTaskBean.DataBean.LogsBean>();
    private Context ctx;
    public DispatchAdapter(Context ctx, ArrayList<LogTaskBean.DataBean.LogsBean> dataList) {
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
        DispatchAdapter.ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.message_item, null);

            holder = new DispatchAdapter.ViewHolder();
            // 每项的视图布是一样的,findview
            holder.tv_GoodsTitle = (TextView) view.findViewById(R.id.tv_GoodsTitle);
            holder.tv_Weight = (TextView) view.findViewById(R.id.tv_Weight);
            holder.rightarrow = (ImageView) view.findViewById(R.id.rightarrow);
            holder.tv_Booth = (TextView) view.findViewById(R.id.tv_Booth);

            view.setTag(holder);
        } else {
            holder = (DispatchAdapter.ViewHolder) view.getTag();

        }

        // 每项的数据是不一样的，setview
        holder.tv_GoodsTitle.setText(dataList.get(position).getGoodsTitle());
        holder.tv_Weight.setText(dataList.get(position).getWeight() + "千克");
        holder.tv_Booth.setText(dataList.get(position).getBooth());
//        holder.iv_main_item_sex.setImageDrawable(dataList.get(position).getSex());
        //AbToastUtil.showToast(ctx,dataList.get(position).getRecAddr());
        return view;
    }



    // 用来实现缓存机制
    private static class ViewHolder {
        public TextView tv_GoodsTitle;
        public TextView tv_Weight;
        public ImageView rightarrow;
        public TextView tv_Booth;
    }
}
