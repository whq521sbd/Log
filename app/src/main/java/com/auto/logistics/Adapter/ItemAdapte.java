package com.auto.logistics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.auto.logistics.Activity.WaybillStateNotes;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;

import java.util.List;

/**
 * Created by Administrator on 2017/1/21.
 */

public class ItemAdapte extends BaseAdapter {
    private Context context;
    private List<LogTaskBean.DataBean.LogsBean> datas;

    private boolean flag;
    public static boolean[][] isselect=new boolean[55][55];
    private int pos;

    public ItemAdapte(Context context, List<LogTaskBean.DataBean.LogsBean> datas,int pos) {
        this.context = context;
        this.datas = datas;
        this.pos=pos;
    }
    public void setProducts(List<LogTaskBean.DataBean.LogsBean> datas) {
        this.datas = datas;
    }

    public boolean isselect(boolean flag) {
        this.flag = flag;
        return flag;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ItemHoder itemHoder = new ItemHoder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlistvw_item, null);
            itemHoder.tv_waybillNum = (TextView) convertView.findViewById(R.id.tv_waybillNum);
            itemHoder.tv_GoodsName = (TextView) convertView.findViewById(R.id.tv_GoodsName);
            itemHoder.tv_Booth = (TextView) convertView.findViewById(R.id.tv_Booth);
            itemHoder.tv_ComName = (TextView) convertView.findViewById(R.id.tv_ComName);
            itemHoder.ck_itemcheck = (CheckBox) convertView.findViewById(R.id.ck_itemcheck);
            convertView.setTag(itemHoder);
        }else {
            itemHoder = (ItemHoder) convertView.getTag();
        }

        itemHoder.tv_waybillNum.setText(datas.get(position).getTaskNum());
        itemHoder.tv_GoodsName.setText(datas.get(position).getGoodsTitle());
        itemHoder.tv_Booth.setText(datas.get(position).getBooth());
        itemHoder.tv_ComName.setText(datas.get(position).getComName());

//

        itemHoder.ck_itemcheck.setChecked(isselect[pos][position]);
        itemHoder.ck_itemcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean flag) {
                WaybillStateNotes.xuanze="";
                isselect[pos][position]=flag;
                for(int i=0;i<isselect.length;i++){
                    for(int j=0;j<isselect[i].length;j++){
                        if(isselect[i][j]){
                            WaybillStateNotes.xuanze=WaybillStateNotes.xuanze+i+"-"+j+",";
                        }
                    }
                }
               // WaybillStateNotes.textView.setText(WaybillStateNotes.xuanze);
            }
        });

        for(int i=0;i<isselect.length;i++){
            for(int j=0;j<isselect[i].length;j++){
                if(isselect[i][j]){
                    WaybillStateNotes.xuanze=WaybillStateNotes.xuanze+i+"-"+j+",";
                }
            }
        }
       // WaybillStateNotes.textView.setText(WaybillStateNotes.xuanze);
        return convertView;
    }


    class ItemHoder {
        TextView tv_waybillNum;
        TextView tv_GoodsName;
        TextView tv_Booth;
        TextView tv_ComName;
        CheckBox ck_itemcheck;

    }

}
