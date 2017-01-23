package com.auto.logistics.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/28.
 */

public class WaybillAdapter extends BaseAdapter {
    private ArrayList<LogTaskBean.DataBean.LogsBean> dataList = new ArrayList<>();
    private Context ctx;
    private ListView listView;
    private static HashMap<Integer, Boolean> isSelected;
    Map<Integer,Boolean> map;

    public Map<Integer, Boolean> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Boolean> map) {
        this.map = map;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }


    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        WaybillAdapter.isSelected = isSelected;
    }


    public WaybillAdapter(Context ctx, ArrayList<LogTaskBean.DataBean.LogsBean> dataList,  Map<Integer,Boolean> map ) {
        this.ctx = ctx;
        this.dataList = dataList;
        this.map= map;
        isSelected = new HashMap<Integer, Boolean>();
        init();
        initmap();

    }

    /**
     * 初始化 checkbox的状态 isSelected<0,false>
     * isSelected<1,false>
     * ...
     */
    private void init() {
        for (int i = 0; i < dataList.size(); i++) {
            getIsSelected().put(i, false);
        }

    }


    private void initmap() {
        for (int i = 0; i < dataList.size(); i++) {
            getMap().put(i, false);
        }

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
            holder.ck_itemcheck = (CheckBox) view.findViewById(R.id.ck_itemcheck);
            holder.tv_ComName = (TextView) view.findViewById(R.id.tv_ComName);
            holder.tv_Distance = (TextView) view.findViewById(R.id.tv_Distance);
            holder.tv_wry = (TextView) view.findViewById(R.id.tv_wry);
            holder.LL_group = (LinearLayout) view.findViewById(R.id.LL_group);
            holder.tv_groupname = (TextView) view.findViewById(R.id.tv_groupname);
            holder.ck_check = (CheckBox) view.findViewById(R.id.ck_check);
            view.setTag(holder);
        } else {
            holder = (WaybillAdapter.ViewHolder) view.getTag();
        }





        holder.tv_waybillNum.setText(dataList.get(position).getTaskNum());
        holder.tv_GoodsName.setText(dataList.get(position).getGoodsTitle());
        holder.tv_Booth.setText(dataList.get(position).getBooth());
        holder.tv_ComName.setText(dataList.get(position).getComName());
        holder.tv_Distance.setText(dataList.get(position).getDistance()+"千米");
        holder.tv_groupname.setText(dataList.get(position).getComName());


        holder.ck_itemcheck.setChecked(map.get(position));

//
//        if(position==0){
//            holder.LL_group.setVisibility(View.VISIBLE);
//        }
//
//        if (dataList.get(position).getComName().equals(dataList.get(position-1).getComName())){
//           holder.LL_group.setVisibility(View.GONE);
//
//
//           //View addview = LayoutInflater.from(ctx).inflate(R.layout.addview,null);
//            //TextView addtext = (TextView) addview.findViewById(R.id.TV_addtext);
//            //addtext.setText(dataList.get(position+1).getComName());
//           // listView.addView(addview);
//
//        }

        if(position==0){
            holder.LL_group.setVisibility(View.VISIBLE);
        }else{
            if (dataList.get(position).getComName().equals(dataList.get(position-1).getComName())){
                holder.LL_group.setVisibility(View.GONE);

            }else{
                holder.LL_group.setVisibility(View.VISIBLE);
            }

        }



        switch (dataList.get(position).getLogUrg().toString()){
            case "0":
                holder.tv_wry.setVisibility(View.GONE);
                break;
            case "1":
                holder.tv_wry.setText("一级加急");
                holder.tv_wry.setVisibility(View.VISIBLE);
                break;
            case "2":
                holder.tv_wry.setText("二级加急");
                holder.tv_wry.setVisibility(View.VISIBLE);
                break;
            case "3":
                holder.tv_wry.setText("三级加急");
                holder.tv_wry.setVisibility(View.VISIBLE);
                break;
        }

        return view;
    }


    // 用来实现缓存机制
    public class ViewHolder {
        public TextView tv_waybillNum;
        public TextView tv_GoodsName;
        public TextView tv_Booth;
        public CheckBox ck_itemcheck;
        public TextView tv_ComName;
        public TextView tv_Distance;
        public TextView tv_wry;
        public LinearLayout LL_group;
        public TextView tv_groupname;
        public CheckBox ck_check;

    }
}
