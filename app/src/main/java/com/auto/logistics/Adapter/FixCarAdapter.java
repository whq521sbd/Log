package com.auto.logistics.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.auto.logistics.Activity.DetailShow;
import com.auto.logistics.JavaBean.CarSign;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;
import com.bumptech.glide.Glide;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/12/17.
 */

public class FixCarAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CarSign.DataBean.SignCarsBean> datalist;
//    private Intent  imageintent;
//    private String[] imgurls;

    public FixCarAdapter(Context context, ArrayList<CarSign.DataBean.SignCarsBean> list) {

        this.context = context;
        this.datalist = list;

       // imageintent = new Intent(context, DetailShow.class);
    }


    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView( int  position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fixcar_item, null);
            holder.tv_fixdate = (TextView) convertView.findViewById(R.id.tv_fixdate);
           // holder.tv_consoler = (TextView) convertView.findViewById(R.id.tv_consoler);
            holder.tv_marks = (TextView) convertView.findViewById(R.id.tv_marks);
            holder.tv_fixcarstate = (TextView) convertView.findViewById(R.id.tv_fixcarstate);
            //holder.IV_fixcarimg0 = (ImageView) convertView.findViewById(R.id.IV_fixcarimg0);
            //holder.IV_fixcarimg1 = (ImageView) convertView.findViewById(R.id.IV_fixcarimg1);
            //holder.IV_fixcarimg2 = (ImageView) convertView.findViewById(R.id.IV_fixcarimg2);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       // holder.setOnClickListener();
        holder.tv_fixdate.setText(datalist.get(position).getSignTime());
       // holder.tv_consoler.setText(datalist.get(position).getFullName());
        holder.tv_marks.setText(datalist.get(position).getSignText());
        holder.tv_fixcarstate.setText(datalist.get(position).getState());
       //String imgurl = datalist.get(position).getSignImg();

        //imgurls  = imgurl.split(",");
        //      Log.d("FixCarAdapter", "getView: "+imgurls.toString());

//
//        if (!imgurls[0].equals("")&& imgurls.length >0) {
//            switch (imgurls.length) {
//                case 1:
//                    Glide.with(context).load(FinalURL.IMGURL + imgurls[0]).into(holder.IV_fixcarimg0);
//                    holder.IV_fixcarimg0.setVisibility(View.VISIBLE);
//                    /***
//                     * 下面item会服用上面的item，所以需要gone掉不显示的图片
//                     */
//                    holder.IV_fixcarimg1.setVisibility(View.GONE);
//                    holder.IV_fixcarimg2.setVisibility(View.GONE);
//                    break;
//                case 2:
//                    Glide.with(context).load(FinalURL.IMGURL + imgurls[0]).into(holder.IV_fixcarimg0);
//                    Glide.with(context).load(FinalURL.IMGURL + imgurls[1]).into(holder.IV_fixcarimg1);
//                    holder.IV_fixcarimg0.setVisibility(View.VISIBLE);
//                    holder.IV_fixcarimg1.setVisibility(View.VISIBLE);
//                    holder.IV_fixcarimg2.setVisibility(View.GONE);
//                    break;
//                case 3:
//
//                    Glide.with(context).load(FinalURL.IMGURL + imgurls[0]).into(holder.IV_fixcarimg0);
//                    Glide.with(context).load(FinalURL.IMGURL + imgurls[1]).into(holder.IV_fixcarimg1);
//                    Glide.with(context).load(FinalURL.IMGURL + imgurls[2]).into(holder.IV_fixcarimg2);
//                    holder.IV_fixcarimg0.setVisibility(View.VISIBLE);
//                    holder.IV_fixcarimg1.setVisibility(View.VISIBLE);
//                    holder.IV_fixcarimg2.setVisibility(View.VISIBLE);
//
//                    break;
//            }
//        }

        return convertView;
    }



    private class ViewHolder {
        private TextView tv_fixdate;
        private TextView tv_consoler;
        private TextView tv_marks;
        private TextView tv_fixcarstate;

    }


}
