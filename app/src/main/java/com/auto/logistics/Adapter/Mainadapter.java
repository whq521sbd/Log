package com.auto.logistics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.auto.logistics.JavaBean.Beandata;
import com.auto.logistics.R;
import com.auto.logistics.Utills.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/21.
 */

public class Mainadapter extends BaseAdapter {
    private Context context;

    private ArrayList<String> titlsDatas;

    private List<Beandata> datas;

    private ItemAdapte itemAdapte;

    public static HashMap<Integer,Boolean> isSelected= new HashMap<Integer, Boolean>();


    public Mainadapter(List<Beandata> datas, Context context, ArrayList<String> titlsdatas) {
        this.datas = datas;
        this.context = context;
        this.titlsDatas= titlsdatas;
    }




    @Override
    public int getCount() {
        return titlsDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return titlsDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyHodler  myHodler =  new MyHodler();

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.waybill_item,null);
            myHodler.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            myHodler.item_listview = (ListView) convertView.findViewById(R.id.item_listview);
            myHodler.ck_check = (CheckBox) convertView.findViewById(R.id.ck_check);
            convertView.setTag(myHodler);
        }else {

            myHodler = (MyHodler) convertView.getTag();
        }

        myHodler.tv_title.setText(datas.get(position).getTitle());



        itemAdapte = new ItemAdapte(context,datas.get(position).getLogs(),position);

        myHodler.item_listview.setAdapter(itemAdapte);
        Utility.setListViewHeightBasedOnChildren(myHodler.item_listview);
        final MyHodler finalMyHodler = myHodler;
        myHodler.ck_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton btn, boolean flag) {
                for (int i = 0; i < datas.get(position).getLogs().size(); i++) {
                    itemAdapte.isselect[position][i] = flag;
                }
                itemAdapte = new ItemAdapte(context,datas.get(position).getLogs(),position);
                finalMyHodler.item_listview.setAdapter(itemAdapte);
            }
        });



        return convertView;
    }

  public   class  MyHodler{

  public  TextView tv_title;
  public  ListView item_listview;
  public  CheckBox ck_check;
    }

}
