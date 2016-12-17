package com.auto.logistics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.auto.logistics.JavaBean.CarSign;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */

public class FixCarAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CarSign.DataBean.SignCarsBean> datalist;
    private ArrayList<ImageView> imglist = new ArrayList<>();

    public FixCarAdapter(Context context, ArrayList<CarSign.DataBean.SignCarsBean> list) {

        this.context = context;
        this.datalist = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.fixcar_item, null);
            holder.tv_fixdate = (TextView) convertView.findViewById(R.id.tv_fixdate);
            holder.tv_consoler = (TextView) convertView.findViewById(R.id.tv_consoler);
            holder.tv_marks = (TextView) convertView.findViewById(R.id.tv_marks);
            holder.tv_fixcarstate = (TextView) convertView.findViewById(R.id.tv_fixcarstate);
            holder.IV_fixcarimg0 = (ImageView) convertView.findViewById(R.id.IV_fixcarimg0);
            holder.IV_fixcarimg1 = (ImageView) convertView.findViewById(R.id.IV_fixcarimg1);
            holder.IV_fixcarimg2 = (ImageView) convertView.findViewById(R.id.IV_fixcarimg2);


            imglist.add(holder.IV_fixcarimg0);
            imglist.add(holder.IV_fixcarimg1);
            imglist.add(holder.IV_fixcarimg2);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_fixdate.setText(datalist.get(position).getSignTime());
        holder.tv_consoler.setText(datalist.get(position).getFullName());
        holder.tv_marks.setText(datalist.get(position).getSignText());
        holder.tv_fixcarstate.setText(datalist.get(position).getState());
        String imgurl = datalist.get(position).getSignImg();
        String[] imgurls = imgurl.split(",");

        if (!imgurls[0].equals("")) {
            // 给 ImageView 设置一个 tag
            holder.IV_fixcarimg0.setTag(imgurls[0]);
            holder.IV_fixcarimg0.setTag(imgurls[1]);
            holder.IV_fixcarimg0.setTag(imgurls[2]);
            // 预设一个图片
            holder.IV_fixcarimg0.setImageResource(R.mipmap.ic_launcher);
            holder.IV_fixcarimg1.setImageResource(R.mipmap.ic_launcher);
            holder.IV_fixcarimg2.setImageResource(R.mipmap.ic_launcher);
        }


        if (!imgurls[0].equals("")) {
            for (int i = 0; i < imgurls.length; i++) {
                // 通过 tag 来防止图片错位
                if (imglist.get(i).getTag() != null ) {
                    Glide.with(context).load(FinalURL.IMGURL + imgurls[i]).into(imglist.get(i));
                }

            }
        }

        return convertView;
    }


    private class ViewHolder {
        private TextView tv_fixdate, tv_consoler, tv_marks, tv_fixcarstate;
        private ImageView IV_fixcarimg0, IV_fixcarimg1, IV_fixcarimg2;

    }


}
